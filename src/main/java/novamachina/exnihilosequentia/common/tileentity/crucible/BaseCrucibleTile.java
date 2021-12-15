package novamachina.exnihilosequentia.common.tileentity.crucible;

import net.minecraft.nbt.INBT;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.utility.Config;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.common.utility.TankUtil;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public abstract class BaseCrucibleTile extends TileEntity implements ITickableTileEntity {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    @Nonnull private static final String INVENTORY_TAG = "inventory";
    @Nonnull private static final String SOLID_AMOUNT_TAG = "solidAmount";
    @Nonnull private static final String CURRENT_ITEM_TAG = "currentItem";
    @Nonnull private static final String BLOCK_TAG = "block";
    @Nonnull private static final String FLUID_TAG = "fluid";

    protected static final int MAX_FLUID_AMOUNT = Config.getCrucibleNumberOfBuckets() * FluidAttributes.BUCKET_VOLUME;
    @Nonnull protected MeltableItemHandler inventory;
    @Nonnull private final LazyOptional<IItemHandler> inventoryHolder = LazyOptional.of(() -> inventory);
    @Nonnull protected CrucibleFluidHandler tank;
    @Nonnull private final LazyOptional<IFluidHandler> tankHolder = LazyOptional.of(() -> tank);
    protected int ticksSinceLast;
    protected int solidAmount;
    @Nonnull protected ItemStack currentItem;
    @Nullable protected BaseCrucibleTileState lastSyncedState = null;

    static protected class BaseCrucibleTileState {
        @Nullable private final Fluid fluid;
        private final int fluidAmount;
        @Nonnull private final Item solid;
        private final int solidAmount;
        private final int heat;

        BaseCrucibleTileState (@Nonnull final BaseCrucibleTile baseCrucibleTile) {
            fluid = baseCrucibleTile.getFluid();
            fluidAmount = baseCrucibleTile.getFluidAmount();
            solid = baseCrucibleTile.inventory.getStackInSlot(0).getItem();
            solidAmount = baseCrucibleTile.getSolidAmount();
            heat = baseCrucibleTile.getHeat();
        }

        @Override
        public boolean equals(@Nullable final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BaseCrucibleTileState that = (BaseCrucibleTileState) o;
            return fluidAmount == that.fluidAmount
                    && solidAmount == that.solidAmount
                    && heat == that.heat
                    && Objects.equals(fluid, that.fluid)
                    && Objects.equals(solid, that.solid);
        }
    }

    protected BaseCrucibleTile(
        TileEntityType<? extends BaseCrucibleTile> tileEntityType) {
        super(tileEntityType);
        inventory = new MeltableItemHandler(getCrucibleType());
        tank = new CrucibleFluidHandler(this);
        ticksSinceLast = 0;
        solidAmount = 0;
        currentItem = ItemStack.EMPTY;
    }

    @Override
    public void load(@Nonnull final BlockState state, @Nonnull final CompoundNBT compound) {
        inventory.deserializeNBT(compound.getCompound(INVENTORY_TAG));
        tank.readFromNBT(compound.getCompound("tank"));
        ticksSinceLast = compound.getInt("ticksSinceLast");
        solidAmount = compound.getInt(SOLID_AMOUNT_TAG);
        currentItem = ItemStack.of(compound.getCompound(CURRENT_ITEM_TAG));
        super.load(state, compound);
    }

    @Override
    @Nonnull
    public CompoundNBT save(@Nonnull final CompoundNBT compound) {
        compound.put(INVENTORY_TAG, inventory.serializeNBT());
        compound.put("tank", tank.writeToNBT(new CompoundNBT()));
        compound.putInt("ticksSinceLast", ticksSinceLast);
        compound.putInt(SOLID_AMOUNT_TAG, solidAmount);
        compound.put(CURRENT_ITEM_TAG, currentItem.save(new CompoundNBT()));
        return super.save(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventoryHolder.cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return tankHolder.cast();
        }
        return super.getCapability(cap, side);
    }

    public int getHeat() {
        if (level == null) {
            return 0;
        }
        @Nonnull final BlockState source = level.getBlockState(worldPosition.below());
        int blockHeat = ExNihiloRegistries.HEAT_REGISTRY.getHeatAmount(source);
        if(source.getBlock() instanceof FlowingFluidBlock) {
            int level = 8 - source.getValue(BlockStateProperties.LEVEL);
            double partial = (double)blockHeat / 8;
            return (int) Math.ceil(partial * level);
        }
        return blockHeat;
    }

    public ActionResultType onBlockActivated(@Nonnull final PlayerEntity player, @Nonnull final Hand handIn,
                                             @Nonnull final IFluidHandler handler) {
        logger.debug("Crucible activated");

        @Nonnull final ItemStack stack = player.getItemInHand(handIn);
        if (stack.isEmpty()) {
            return ActionResultType.SUCCESS;
        }

        if(TankUtil.drainWaterIntoBottle(this, player, handler)) {
            return ActionResultType.SUCCESS;
        }

        if(TankUtil.drainWaterFromBottle(this, player, handler)) {
            return ActionResultType.SUCCESS;
        }

        boolean result = FluidUtil.interactWithFluidHandler(player, handIn, handler);

        if (result) {
            logger.debug("Fluid handler interaction successful");
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            if (level != null) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
            }
            setChanged();
            return ActionResultType.SUCCESS;
        }

        @Nullable final CrucibleRecipe recipe = getMeltable();
        if (recipe != null && !tank.isEmpty() && !tank.getFluid().getFluid()
            .isSame(recipe.getResultFluid().getFluid())) {
            return ActionResultType.SUCCESS;
        }

        logger.debug("Inserting item");
        @Nonnull final ItemStack addStack = stack.copy();
        addStack.setCount(1);
        @Nonnull final ItemStack insertStack = inventory.insertItem(0, addStack, true);
        if (!ItemStack.matches(addStack, insertStack)) {
            inventory.insertItem(0, addStack, false);

            if (!player.isCreative()) {
                stack.shrink(1);
            }
            setChanged();
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.SUCCESS;
    }

    @Nullable
    public ResourceLocation getSolidTexture() {
        if (!inventory.getStackInSlot(0).isEmpty()) {
            return inventory.getStackInSlot(0).getItem().getRegistryName();
        }
        if (!currentItem.isEmpty()) {
            return currentItem.getItem().getRegistryName();
        }
        return null;
    }

    @Nullable
    public Fluid getFluid() {
        if (!tank.isEmpty()) {
            return tank.getFluid().getFluid();
        }
        return null;
    }

    @Override
    @Nonnull
    public SUpdateTileEntityPacket getUpdatePacket() {
        @Nonnull final CompoundNBT nbt = new CompoundNBT();
        if (!inventory.getStackInSlot(0).isEmpty()) {
            CompoundNBT blockNbt = inventory.getStackInSlot(0).save(new CompoundNBT());
            nbt.put(BLOCK_TAG, blockNbt);
        }
        if (!currentItem.isEmpty()) {
            CompoundNBT currentItemTag = currentItem.save(new CompoundNBT());
            nbt.put(CURRENT_ITEM_TAG, currentItemTag);
        }
        if (!tank.isEmpty()) {
            CompoundNBT fluidNbt = tank.writeToNBT(new CompoundNBT());
            nbt.put(FLUID_TAG, fluidNbt);
        }
        nbt.putInt(SOLID_AMOUNT_TAG, solidAmount);

        return new SUpdateTileEntityPacket(getBlockPos(), -1, nbt);
    }

    @Override
    public void onDataPacket(@Nonnull final NetworkManager net, @Nonnull final SUpdateTileEntityPacket packet) {
        @Nonnull final CompoundNBT nbt = packet.getTag();
        if (nbt.contains(CURRENT_ITEM_TAG)) {
            @Nullable final INBT currentItemTag = nbt.get(CURRENT_ITEM_TAG);
            if (currentItemTag != null) {
                currentItem = ItemStack.of((CompoundNBT) currentItemTag);
            } else {
                currentItem = ItemStack.EMPTY;
            }
        } else {
            currentItem = ItemStack.EMPTY;
        }

        if (nbt.contains(BLOCK_TAG)) {
            @Nullable final INBT blockTag = nbt.get(BLOCK_TAG);
            if (blockTag != null) {
                inventory.setStackInSlot(0, ItemStack.of((CompoundNBT) blockTag));
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

    public float getFluidProportion() {
        return ((float) tank.getFluidAmount()) / tank.getCapacity();
    }

    public float getSolidProportion() {

        try {
            int itemCount =
                inventory.getStackInSlot(0).isEmpty() ? 0 : inventory.getStackInSlot(0).getCount();
            float solidProportion = ((float) itemCount) / 4;

            if (solidAmount > 0) {
                CrucibleRecipe meltable = getMeltable();
                if (meltable != null)
                    solidProportion += ((float) solidAmount) / (4 * meltable.getAmount());
            }
            return solidProportion;
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        }
        return 0;
    }

    public abstract CrucibleTypeEnum getCrucibleType();

    @Nullable
    private CrucibleRecipe getMeltable() {
        return ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem);
    }

    public int getFluidAmount() {
        return tank.getFluidAmount();
    }

    @Nullable
    public ItemStack getCurrentItem(){
        return currentItem;
    }

    public abstract int getSolidAmount();

    public abstract boolean canAcceptFluidTemperature(@Nonnull final FluidStack fluidStack);
}
