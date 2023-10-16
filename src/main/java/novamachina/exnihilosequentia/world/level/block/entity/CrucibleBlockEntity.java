package novamachina.exnihilosequentia.world.level.block.entity;

import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.capability.MeltableItemHandler;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;
import novamachina.exnihilosequentia.world.level.material.capability.CrucibleFluidHandler;
import novamachina.novacore.util.TankUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CrucibleBlockEntity extends BlockEntity {

  private static Logger log = LoggerFactory.getLogger(CrucibleBlockEntity.class);

  public static final int MAX_FLUID_AMOUNT =
      Config.getCrucibleNumberOfBuckets() * FluidType.BUCKET_VOLUME;
  @Nonnull private static final String BLOCK_TAG = "block";
  @Nonnull private static final String CURRENT_ITEM_TAG = "currentItem";
  @Nonnull private static final String FLUID_TAG = "fluid";
  @Nonnull private static final String INVENTORY_TAG = "inventory";
  @Nonnull private static final String SOLID_AMOUNT_TAG = "solidAmount";
  @Nonnull protected ItemStack currentItem;
  @Nonnull protected MeltableItemHandler inventory;

  @Nonnull
  private final LazyOptional<IItemHandler> inventoryHolder = LazyOptional.of(() -> inventory);

  @Nullable protected BaseCrucibleTileState lastSyncedState = null;
  protected int solidAmount;
  @Nonnull protected CrucibleFluidHandler tank;
  @Nonnull private final LazyOptional<IFluidHandler> tankHolder = LazyOptional.of(() -> tank);
  protected int ticksSinceLast;

  protected CrucibleBlockEntity(
      BlockEntityType<? extends CrucibleBlockEntity> tileEntityType,
      BlockPos pos,
      BlockState state) {
    super(tileEntityType, pos, state);
    inventory = new MeltableItemHandler(getCrucibleType());
    tank = new CrucibleFluidHandler(this);
    ticksSinceLast = 0;
    solidAmount = 0;
    currentItem = ItemStack.EMPTY;
  }

  public abstract boolean canAcceptFluidTemperature(@Nonnull final FluidStack fluidStack);

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(
      @Nonnull final Capability<T> cap, @Nullable final Direction side) {
    if (cap == ForgeCapabilities.ITEM_HANDLER) {
      return inventoryHolder.cast();
    }
    if (cap == ForgeCapabilities.FLUID_HANDLER) {
      return tankHolder.cast();
    }
    return super.getCapability(cap, side);
  }

  public abstract CrucibleType getCrucibleType();

  @Nonnull
  public ItemStack getCurrentItem() {
    return currentItem;
  }

  @Nullable
  public Fluid getFluid() {
    if (!tank.isEmpty()) {
      return tank.getFluid().getFluid();
    }
    return null;
  }

  public int getFluidAmount() {
    return tank.getFluidAmount();
  }

  public float getFluidProportion() {
    return ((float) tank.getFluidAmount()) / tank.getCapacity();
  }

  public int getHeat() {
    if (level == null) {
      return 0;
    }
    @Nonnull final BlockState source = level.getBlockState(worldPosition.below());
    final int blockHeat = ExNihiloRegistries.HEAT_REGISTRY.getHeatAmount(source);
    if (source.getBlock() instanceof LiquidBlock) {
      final int level = 8 - source.getValue(BlockStateProperties.LEVEL);
      final double partial = (double) blockHeat / 8;
      return (int) Math.ceil(partial * level);
    }
    return blockHeat;
  }

  private Optional<MeltingRecipe> getMeltable() {
    return ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(currentItem.getItem());
  }

  public abstract int getSolidAmount();

  public float getSolidProportion() {

    try {
      int itemCount =
          inventory.getStackInSlot(0).isEmpty() ? 0 : inventory.getStackInSlot(0).getCount();
      float solidProportion = ((float) itemCount) / 4;

      if (solidAmount > 0) {
        Optional<MeltingRecipe> meltable = getMeltable();
        if (meltable.isPresent()) {
          solidProportion +=
              ((float) solidAmount) / (4 * meltable.get().getResultFluid().getAmount());
        }
      }
      return solidProportion;
    } catch (NullPointerException e) {
      log.error(e.getMessage());
    }
    return 0;
  }

  @Override
  @Nonnull
  public ClientboundBlockEntityDataPacket getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }

  @Override
  public CompoundTag getUpdateTag() {
    @Nonnull final CompoundTag nbt = new CompoundTag();
    if (!inventory.getStackInSlot(0).isEmpty()) {
      @Nonnull final CompoundTag blockNbt = inventory.getStackInSlot(0).save(new CompoundTag());
      nbt.put(BLOCK_TAG, blockNbt);
    }
    if (!currentItem.isEmpty()) {
      @Nonnull final CompoundTag currentItemTag = currentItem.save(new CompoundTag());
      nbt.put(CURRENT_ITEM_TAG, currentItemTag);
    }
    if (!tank.isEmpty()) {
      @Nonnull final CompoundTag fluidNbt = tank.writeToNBT(new CompoundTag());
      nbt.put(FLUID_TAG, fluidNbt);
    }
    nbt.putInt(SOLID_AMOUNT_TAG, solidAmount);
    return nbt;
  }

  @Override
  public void load(@Nonnull final CompoundTag compound) {
    inventory.deserializeNBT(compound.getCompound(INVENTORY_TAG));
    tank.readFromNBT(compound.getCompound("tank"));
    ticksSinceLast = compound.getInt("ticksSinceLast");
    solidAmount = compound.getInt(SOLID_AMOUNT_TAG);
    currentItem = ItemStack.of(compound.getCompound(CURRENT_ITEM_TAG));
    super.load(compound);
  }

  public InteractionResult onBlockActivated(
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn,
      @Nonnull final IFluidHandler handler) {
    log.debug("Crucible activated");

    @Nonnull final ItemStack stack = player.getItemInHand(handIn);
    if (stack.isEmpty()) {
      return InteractionResult.SUCCESS;
    }

    if (TankUtil.drainWaterIntoBottle(this, player, handler)) {
      return InteractionResult.SUCCESS;
    }

    if (TankUtil.drainWaterFromBottle(this, player, handler)) {
      return InteractionResult.SUCCESS;
    }

    boolean result = FluidUtil.interactWithFluidHandler(player, handIn, handler);

    if (result) {
      log.debug("Fluid handler interaction successful");
      if (!player.isCreative()) {
        stack.shrink(1);
      }
      if (level != null) {
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
      }
      setChanged();
      return InteractionResult.SUCCESS;
    }

    @Nullable final Optional<MeltingRecipe> recipe = getMeltable();
    if (recipe.isPresent()
        && !tank.isEmpty()
        && !tank.getFluid().getFluid().isSame(recipe.get().getResultFluid().getFluid())) {
      return InteractionResult.SUCCESS;
    }

    log.debug("Inserting item");
    @Nonnull final ItemStack addStack = stack.copy();
    addStack.setCount(1);
    @Nonnull final ItemStack insertStack = inventory.insertItem(0, addStack, true);
    if (!ItemStack.matches(addStack, insertStack)) {
      inventory.insertItem(0, addStack, false);

      if (!player.isCreative()) {
        stack.shrink(1);
      }
      setChanged();
      tickCrucible();
      return InteractionResult.SUCCESS;
    }
    return InteractionResult.SUCCESS;
  }

  @Override
  public void onDataPacket(
      @Nonnull final Connection net, @Nonnull final ClientboundBlockEntityDataPacket packet) {
    @Nonnull final CompoundTag nbt = packet.getTag();
    if (nbt.contains(CURRENT_ITEM_TAG)) {
      @Nullable final Tag currentItemTag = nbt.get(CURRENT_ITEM_TAG);
      if (currentItemTag != null) {
        currentItem = ItemStack.of((CompoundTag) currentItemTag);
      } else {
        currentItem = ItemStack.EMPTY;
      }
    } else {
      currentItem = ItemStack.EMPTY;
    }

    if (nbt.contains(BLOCK_TAG)) {
      @Nullable final Tag blockTag = nbt.get(BLOCK_TAG);
      if (blockTag != null) {
        inventory.setStackInSlot(0, ItemStack.of((CompoundTag) blockTag));
      } else {
        inventory.setStackInSlot(0, ItemStack.EMPTY);
      }
    } else {
      inventory.setStackInSlot(0, ItemStack.EMPTY);
    }

    if (nbt.contains(FLUID_TAG)) {
      tank.readFromNBT(nbt.getCompound(FLUID_TAG));
    } else {
      tank.setFluid(FluidStack.EMPTY);
    }
    solidAmount = nbt.getInt(SOLID_AMOUNT_TAG);
  }

  @Override
  public void saveAdditional(@Nonnull final CompoundTag compound) {
    compound.put(INVENTORY_TAG, inventory.serializeNBT());
    compound.put("tank", tank.writeToNBT(new CompoundTag()));
    compound.putInt("ticksSinceLast", ticksSinceLast);
    compound.putInt(SOLID_AMOUNT_TAG, solidAmount);
    compound.put(CURRENT_ITEM_TAG, currentItem.save(new CompoundTag()));
  }

  public void tickServer() {
    if (level == null || level.isClientSide) {
      return;
    }

    inventory.setCrucibleHasRoom(tank.getFluidAmount() < MAX_FLUID_AMOUNT);
    ticksSinceLast++;

    if (ticksSinceLast >= Config.getTicksBetweenMelts()) {
      ticksSinceLast = 0;
      tickCrucible();
    }
    updateCurrentState();
  }

  private void updateCurrentState() {
    @Nonnull final BaseCrucibleTileState currentState = new BaseCrucibleTileState(this);
    if (!currentState.equals(lastSyncedState)) {
      level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
      lastSyncedState = currentState;
    }
  }

  protected void tickCrucible() {
    int heat = getHeat();
    if (heat <= 0) {
      return;
    }
    if (solidAmount <= 0) {
      if (!inventory.getStackInSlot(0).isEmpty()) {
        consumeNewSolid();
      } else {
        return;
      }
    }

    if (!inventory.getStackInSlot(0).isEmpty()
        && inventory.getStackInSlot(0).is(currentItem.getItem())) {
      addFluid(heat);
    }

    if (heat > solidAmount) {
      heat = solidAmount;
    }

    if (heat > 0
        && ExNihiloRegistries.CRUCIBLE_REGISTRY.isMeltable(
            currentItem.getItem(), getCrucibleType().getLevel())) {
      processSolid(heat);
    }
  }

  protected abstract void processSolid(int heat);

  private void addFluid(int heat) {
    while (heat > solidAmount && !inventory.getStackInSlot(0).isEmpty()) {
      final Optional<MeltingRecipe> recipe =
          ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(currentItem.getItem());
      if (recipe.isPresent()) {
        solidAmount += recipe.get().getResultFluid().getAmount();
        inventory.getStackInSlot(0).shrink(1);

        if (inventory.getStackInSlot(0).isEmpty()) {
          inventory.setStackInSlot(0, ItemStack.EMPTY);
        }
      }
    }
  }

  protected abstract void consumeNewSolid();

  protected static class BaseCrucibleTileState {

    @Nullable private final Fluid fluid;
    private final int fluidAmount;
    private final int heat;
    @Nonnull private final Item solid;
    private final int solidAmount;

    BaseCrucibleTileState(@Nonnull final CrucibleBlockEntity crucibleBlockEntity) {
      fluid = crucibleBlockEntity.getFluid();
      fluidAmount = crucibleBlockEntity.getFluidAmount();
      solid = crucibleBlockEntity.inventory.getStackInSlot(0).getItem();
      solidAmount = crucibleBlockEntity.getSolidAmount();
      heat = crucibleBlockEntity.getHeat();
    }

    @Override
    public boolean equals(@Nullable final Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      BaseCrucibleTileState that = (BaseCrucibleTileState) o;
      return fluidAmount == that.fluidAmount
          && solidAmount == that.solidAmount
          && heat == that.heat
          && Objects.equals(fluid, that.fluid)
          && Objects.equals(solid, that.solid);
    }

    @Override
    public int hashCode() {
      return Objects.hash(fluid, fluidAmount, solid, solidAmount, heat);
    }
  }

  public enum CrucibleType {
    WOOD("wood", 0),
    FIRED("fired", 1);

    @Nonnull
    public String getName() {
      return name;
    }

    @Nonnull private final String name;
    private final int level;

    CrucibleType(@Nonnull final String name, final int level) {
      this.name = name;
      this.level = level;
    }

    public static CrucibleType getTypeByName(@Nonnull final String name) {
      for (@Nonnull final CrucibleType type : CrucibleType.values()) {
        if (type.name.equals(name)) {
          return type;
        }
      }
      // TODO this one needs to be changed due to crash with mekanism pipes and fired crucibles
      return null;
    }

    public int getLevel() {
      return level;
    }
  }
}
