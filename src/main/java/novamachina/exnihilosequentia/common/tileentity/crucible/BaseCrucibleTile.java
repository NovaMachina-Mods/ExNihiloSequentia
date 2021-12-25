package novamachina.exnihilosequentia.common.tileentity.crucible;

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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public abstract class BaseCrucibleTile extends TileEntity implements ITickableTileEntity {
    private static final ExNihiloLogger logger = new ExNihiloLogger(BaseCrucibleTile.class);
    private static final String INVENTORY_TAG = "inventory";
    private static final String SOLID_AMOUNT_TAG = "solidAmount";
    private static final String CURRENT_ITEM_TAG = "currentItem";
    private static final String BLOCK_TAG = "block";
    private static final String FLUID_TAG = "fluid";

    protected static final int MAX_FLUID_AMOUNT = Config.getCrucibleNumberOfBuckets() * FluidAttributes.BUCKET_VOLUME;
    protected MeltableItemHandler inventory;
    private final LazyOptional<IItemHandler> inventoryHolder = LazyOptional.of(() -> inventory);
    protected CrucibleFluidHandler tank;
    private final LazyOptional<IFluidHandler> tankHolder = LazyOptional.of(() -> tank);
    protected int ticksSinceLast;
    protected int solidAmount;
    protected ItemStack currentItem;
    protected BaseCrucibleTileState lastSyncedState = null;

    static protected class BaseCrucibleTileState {
        private final Fluid fluid;
        private final int fluidAmount;
        private final Item solid;
        private final int solidAmount;
        private final int heat;

        BaseCrucibleTileState (final BaseCrucibleTile baseCrucibleTile) {
            fluid = baseCrucibleTile.getFluid();
            fluidAmount = baseCrucibleTile.getFluidAmount();
            solid = baseCrucibleTile.inventory.getStackInSlot(0).getItem();
            solidAmount = baseCrucibleTile.getSolidAmount();
            heat = baseCrucibleTile.getHeat();
        }

        @Override
        public boolean equals(Object o) {
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
    public void load(BlockState state, CompoundNBT compound) {
        inventory.deserializeNBT(compound.getCompound(INVENTORY_TAG));
        tank.readFromNBT(compound.getCompound("tank"));
        this.ticksSinceLast = compound.getInt("ticksSinceLast");
        this.solidAmount = compound.getInt(SOLID_AMOUNT_TAG);
        this.currentItem = ItemStack.of(compound.getCompound(CURRENT_ITEM_TAG));
        super.load(state, compound);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.put(INVENTORY_TAG, inventory.serializeNBT());
        compound.put("tank", tank.writeToNBT(new CompoundNBT()));
        compound.putInt("ticksSinceLast", ticksSinceLast);
        compound.putInt(SOLID_AMOUNT_TAG, solidAmount);
        compound.put(CURRENT_ITEM_TAG, currentItem.save(new CompoundNBT()));
        return super.save(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventoryHolder.cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return tankHolder.cast();
        }
        return super.getCapability(cap, side);
    }

    public int getHeat() {
        BlockState source = level.getBlockState(worldPosition.below());
        int blockHeat = ExNihiloRegistries.HEAT_REGISTRY.getHeatAmount(source);
        if(source.getBlock() instanceof FlowingFluidBlock) {
            int level = 8 - source.getValue(BlockStateProperties.LEVEL);
            double partial = (double)blockHeat / 8;
            int returnVal = (int)Math.ceil(partial * level);
            return returnVal;
        }
        return blockHeat;
    }

    public ActionResultType onBlockActivated(PlayerEntity player, Hand handIn,
                                             IFluidHandler handler) {
        logger.debug("Crucible activated");

        ItemStack stack = player.getItemInHand(handIn);
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
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
            setChanged();
            return ActionResultType.SUCCESS;
        }

        CrucibleRecipe recipe = getMeltable();
        if (recipe != null && !tank.isEmpty() && !tank.getFluid().getFluid()
            .isSame(recipe.getResultFluid().getFluid())) {
            return ActionResultType.SUCCESS;
        }

        logger.debug("Inserting item");
        ItemStack addStack = stack.copy();
        addStack.setCount(1);
        ItemStack insertStack = inventory.insertItem(0, addStack, true);
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

    public ResourceLocation getSolidTexture() {
        if (!inventory.getStackInSlot(0).isEmpty()) {
            return inventory.getStackInSlot(0).getItem().getRegistryName();
        }
        if (!currentItem.isEmpty()) {
            return currentItem.getItem().getRegistryName();
        }
        return null;
    }

    public Fluid getFluid() {
        if (!tank.isEmpty()) {
            return tank.getFluid().getFluid();
        }
        return null;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
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
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        CompoundNBT nbt = packet.getTag();
        if (nbt.contains(CURRENT_ITEM_TAG)) {
            currentItem = ItemStack.of((CompoundNBT) nbt.get(CURRENT_ITEM_TAG));
        } else {
            currentItem = ItemStack.EMPTY;
        }

        if (nbt.contains(BLOCK_TAG)) {
            inventory.setStackInSlot(0, ItemStack.of((CompoundNBT) nbt.get(BLOCK_TAG)));
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

    public abstract CrucilbeTypeEnum getCrucibleType();

    private CrucibleRecipe getMeltable() {
        return ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem);
    }

    public int getFluidAmount() {
        return tank.getFluidAmount();
    }

    public ItemStack getCurrentItem(){
        return currentItem;
    }

    public abstract int getSolidAmount();

    public abstract boolean canAcceptFluidTemperature(FluidStack fluidStack);
}
