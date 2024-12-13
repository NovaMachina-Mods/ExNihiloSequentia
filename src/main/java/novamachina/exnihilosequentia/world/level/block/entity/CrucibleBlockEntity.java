package novamachina.exnihilosequentia.world.level.block.entity;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.StringRepresentable;
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
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.capability.MeltableItemHandler;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;
import novamachina.exnihilosequentia.world.level.material.capability.CrucibleFluidHandler;
import novamachina.novacore.util.TankUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CrucibleBlockEntity extends BlockEntity {

  private static final String CURRENT_ITEM = "currentItem";
  private static Logger log = LoggerFactory.getLogger(CrucibleBlockEntity.class);

  public static final int MAX_FLUID_AMOUNT =
      Config.getCrucibleNumberOfBuckets() * FluidType.BUCKET_VOLUME;
  @Nonnull private static final String BLOCK_TAG = "block";
  @Nonnull private static final String FLUID_TAG = "fluid";
  @Nonnull private static final String INVENTORY_TAG = "inventory";
  @Nonnull private static final String SOLID_AMOUNT_TAG = "solidAmount";
  @Nullable protected BaseCrucibleTileState lastSyncedState = null;
  protected int solidAmount;
  protected int ticksSinceLast;
  protected ItemStack currentItem = ItemStack.EMPTY;

  protected CrucibleBlockEntity(
      BlockEntityType<? extends CrucibleBlockEntity> tileEntityType,
      BlockPos pos,
      BlockState state) {
    super(tileEntityType, pos, state);
    ticksSinceLast = 0;
    solidAmount = 0;
  }

  public abstract boolean canAcceptFluidTemperature(@Nonnull final FluidStack fluidStack);

  public abstract CrucibleType getCrucibleType();

  @Nonnull
  public ItemStack getCurrentItem() {
    return currentItem;
  }

  @Nullable
  public Fluid getFluid() {
    if (!CrucibleFluidHandler.getHandler(this).isEmpty()) {
      return CrucibleFluidHandler.getHandler(this).getFluid().getFluid();
    }
    return null;
  }

  public int getFluidAmount() {
    return CrucibleFluidHandler.getHandler(this).getFluidAmount();
  }

  public float getFluidProportion() {
    return ((float) CrucibleFluidHandler.getHandler(this).getFluidAmount())
        / CrucibleFluidHandler.getHandler(this).getCapacity();
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
    return ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(getCurrentItem().getItem());
  }

  public abstract int getSolidAmount();

  public float getSolidProportion() {
    int itemCount = MeltableItemHandler.getHandler(this).getStackInSlot(0).getCount();
    AtomicReference<Float> returnValue = new AtomicReference<>(0.0f);
    if (itemCount > 0) {
      getMeltable()
          .ifPresent(
              recipe -> {
                int amountPerItem = recipe.getResultFluid().getAmount();
                int maxSolid = amountPerItem * 4;
                int totalSolid = (amountPerItem * itemCount) + solidAmount;
                returnValue.set((float) totalSolid / maxSolid);
              });
    }

    return returnValue.get();
  }

  @Override
  @Nonnull
  public ClientboundBlockEntityDataPacket getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
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

    Optional<MeltingRecipe> recipe = getMeltable();
    if (recipe.isPresent()
        && !CrucibleFluidHandler.getHandler(this).isEmpty()
        && !CrucibleFluidHandler.getHandler(this)
            .getFluid()
            .getFluid()
            .isSame(recipe.get().getResultFluid().getFluid())) {
      return InteractionResult.SUCCESS;
    }

    log.debug("Inserting item");
    @Nonnull final ItemStack addStack = stack.copy();
    addStack.setCount(1);
    @Nonnull
    final ItemStack insertStack =
        MeltableItemHandler.getHandler(this).insertItem(0, addStack, true);
    if (!ItemStack.matches(addStack, insertStack)) {
      MeltableItemHandler.getHandler(this).insertItem(0, addStack, false);

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
      @Nonnull final Connection net,
      @Nonnull final ClientboundBlockEntityDataPacket packet,
      HolderLookup.Provider lookupProvider) {
    log.info("IN CRUCIBLE ONDATAPACKET");
    super.onDataPacket(net, packet, lookupProvider);
  }

  @Override
  public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
    log.info("IN CRUCIBLE GETUPDATETAG");
    @Nonnull final CompoundTag nbt = new CompoundTag();
    saveAdditional(nbt, provider);
    return nbt;
  }

  @Override
  public void saveAdditional(@Nonnull final CompoundTag compound, HolderLookup.Provider provider) {
    log.info("IN CRUCIBLE SAVE ADDITIONAL");
    compound.put(INVENTORY_TAG, MeltableItemHandler.getHandler(this).serializeNBT(provider));
    if (!currentItem.isEmpty()) {
      compound.put(CURRENT_ITEM, currentItem.save(provider));
    }
    compound.put(
        "tank", CrucibleFluidHandler.getHandler(this).writeToNBT(provider, new CompoundTag()));
    compound.putInt("ticksSinceLast", ticksSinceLast);
    compound.putInt(SOLID_AMOUNT_TAG, solidAmount);
    super.saveAdditional(compound, provider);
  }

  @Override
  public void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
    log.info("IN CRUCIBLE LOAD");
    MeltableItemHandler.getHandler(this)
        .deserializeNBT(provider, compound.getCompound(INVENTORY_TAG));
    if (compound.contains(CURRENT_ITEM)) {
      ItemStack.parse(provider, compound.get(CURRENT_ITEM))
          .ifPresent((itemStack -> currentItem = itemStack));
    } else {
      setCurrentItem(ItemStack.EMPTY);
    }
    CrucibleFluidHandler.getHandler(this).readFromNBT(provider, compound.getCompound("tank"));
    ticksSinceLast = compound.getInt("ticksSinceLast");
    setSolidAmount(compound.getInt(SOLID_AMOUNT_TAG));
    super.loadAdditional(compound, provider);
  }

  protected void setCurrentItem(ItemStack itemStack) {
    currentItem = itemStack;
  }

  public void tickServer() {
    if (level == null || level.isClientSide) {
      return;
    }

    MeltableItemHandler.getHandler(this)
        .setCrucibleHasRoom(
            CrucibleFluidHandler.getHandler(this).getFluidAmount() < MAX_FLUID_AMOUNT);
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
      this.setChanged();
    }
  }

  protected void tickCrucible() {
    int heat = getHeat();
    if (heat <= 0) {
      return;
    }
    if (solidAmount > 0) {
      if (heat > solidAmount) {
        heat = solidAmount;
      }
      processSolid(heat);
      return;
    }
    if (solidAmount <= 0) {
      consumeNewSolid();
    }
    //    if (solidAmount <= 0) {
    //      if (!MeltableItemHandler.getHandler(this).getStackInSlot(0).isEmpty()) {
    //        consumeNewSolid();
    //      } else {
    //        return;
    //      }
    //    }

    //    if (!getCurrentItem().isEmpty()) {
    //      addFluid(heat);
    //    }
    //
    //    if(MeltableItemHandler.getHandler(this).getStackInSlot(0).isEmpty() && solidAmount <=0 ) {
    //      setCurrentItem(ItemStack.EMPTY);
    //    }
  }

  private void processSolid(int heat) {
    ExNihiloRegistries.CRUCIBLE_REGISTRY
        .findRecipe(getCurrentItem().getItem())
        .ifPresent(
            recipe -> {
              FluidStack fluidStack = new FluidStack(recipe.getResultFluid().getFluid(), heat);
              int filled =
                  CrucibleFluidHandler.getHandler(this)
                      .fill(fluidStack, IFluidHandler.FluidAction.EXECUTE);
              solidAmount -= filled;
            });
  }

  private void addFluid(int heat) {
    while (heat > solidAmount && !getCurrentItem().isEmpty()) {
      final Optional<MeltingRecipe> recipe =
          ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(getCurrentItem().getItem());
      if (recipe.isPresent()) {
        //        setSolidAmount(solidAmount + recipe.get().getResultFluid().getAmount());
        getCurrentItem().shrink(1);

        if (getCurrentItem().isEmpty()) {
          MeltableItemHandler.getHandler(this).setStackInSlot(0, ItemStack.EMPTY);
        }
      }
    }
  }

  public void setSolidAmount(int newValue) {
    log.info("New Solid Amount: {}", newValue);
    solidAmount = newValue;
  }

  private void consumeNewSolid() {
    ExNihiloRegistries.CRUCIBLE_REGISTRY
        .findRecipe(MeltableItemHandler.getHandler(this).getStackInSlot(0).getItem())
        .ifPresent(recipe -> {
          setSolidAmount(recipe.getResultFluid().getAmount());
          setCurrentItem(MeltableItemHandler.getHandler(this).getStackInSlot(0).copy());
          currentItem.setCount(1);
          MeltableItemHandler.getHandler(this).getStackInSlot(0).shrink(1);
        });



    if (MeltableItemHandler.getHandler(this).getStackInSlot(0).isEmpty()) {
      MeltableItemHandler.getHandler(this).setStackInSlot(0, ItemStack.EMPTY);
    }
  }

  protected static class BaseCrucibleTileState {

    @Nullable private final Fluid fluid;
    private final int fluidAmount;
    private final int heat;
    @Nonnull private final Item solid;
    private final int solidAmount;

    BaseCrucibleTileState(@Nonnull final CrucibleBlockEntity crucibleBlockEntity) {
      fluid = crucibleBlockEntity.getFluid();
      fluidAmount = crucibleBlockEntity.getFluidAmount();
      solid = MeltableItemHandler.getHandler(crucibleBlockEntity).getStackInSlot(0).getItem();
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

  public enum CrucibleType implements StringRepresentable {
    WOOD("wood", 0),
    FIRED("fired", 1);

    public static final StringRepresentable.EnumCodec<CrucibleType> CODEC =
        StringRepresentable.fromEnum(CrucibleType::values);

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

    @Override
    public String getSerializedName() {
      return this.name;
    }
  }
}
