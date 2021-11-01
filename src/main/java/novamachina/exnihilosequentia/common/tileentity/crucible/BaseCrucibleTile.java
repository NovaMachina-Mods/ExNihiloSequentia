package novamachina.exnihilosequentia.common.tileentity.crucible;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.api.utility.Config;
import novamachina.exnihilosequentia.api.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.common.utility.TankUtil;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public abstract class BaseCrucibleTile extends BlockEntity {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
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

    protected BaseCrucibleTile(BlockEntityType<? extends BaseCrucibleTile> tileEntityType, BlockPos pos, BlockState state) {
        super(tileEntityType, pos, state);
        inventory = new MeltableItemHandler(getCrucibleType());
        tank = new CrucibleFluidHandler(this);
        ticksSinceLast = 0;
        solidAmount = 0;
        currentItem = ItemStack.EMPTY;
    }

    @Override
    public void load(CompoundTag compound) {
        inventory.deserializeNBT(compound.getCompound(INVENTORY_TAG));
        tank.readFromNBT(compound.getCompound("tank"));
        this.ticksSinceLast = compound.getInt("ticksSinceLast");
        this.solidAmount = compound.getInt(SOLID_AMOUNT_TAG);
        this.currentItem = ItemStack.of(compound.getCompound(CURRENT_ITEM_TAG));
        super.load(compound);
    }

    @Nonnull
    @Override
    public CompoundTag save(CompoundTag compound) {
        compound.put(INVENTORY_TAG, inventory.serializeNBT());
        compound.put("tank", tank.writeToNBT(new CompoundTag()));
        compound.putInt("ticksSinceLast", ticksSinceLast);
        compound.putInt(SOLID_AMOUNT_TAG, solidAmount);
        compound.put(CURRENT_ITEM_TAG, currentItem.save(new CompoundTag()));
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

    public abstract int getHeat();

    public InteractionResult onBlockActivated(Player player, InteractionHand handIn,
                                             IFluidHandler handler) {
        logger.debug("Crucible activated");

        ItemStack stack = player.getItemInHand(handIn);
        if (stack.isEmpty()) {
            return InteractionResult.SUCCESS;
        }

        if(TankUtil.drainWaterIntoBottle(this, player, handler)) {
            return InteractionResult.SUCCESS;
        }

        if(TankUtil.drainWaterFromBottle(this, player, handler)) {
            return InteractionResult.SUCCESS;
        }

        boolean result = FluidUtil.interactWithFluidHandler(player, handIn, handler);

        if (result) {
            logger.debug("Fluid handler interaction successful");
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            assert level != null;
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
            setChanged();
            return InteractionResult.SUCCESS;
        }

        CrucibleRecipe recipe = getMeltable();
        if (recipe != null && !tank.isEmpty() && !tank.getFluid().getFluid()
            .isSame(recipe.getResultFluid().getFluid())) {
            return InteractionResult.SUCCESS;
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
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
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
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag nbt = new CompoundTag();
        if (!inventory.getStackInSlot(0).isEmpty()) {
            CompoundTag blockNbt = inventory.getStackInSlot(0).save(new CompoundTag());
            nbt.put(BLOCK_TAG, blockNbt);
        }
        if (!currentItem.isEmpty()) {
            CompoundTag currentItemTag = currentItem.save(new CompoundTag());
            nbt.put(CURRENT_ITEM_TAG, currentItemTag);
        }
        if (!tank.isEmpty()) {
            CompoundTag fluidNbt = tank.writeToNBT(new CompoundTag());
            nbt.put(FLUID_TAG, fluidNbt);
        }
        nbt.putInt(SOLID_AMOUNT_TAG, solidAmount);

        return new ClientboundBlockEntityDataPacket(getBlockPos(), -1, nbt);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
        CompoundTag nbt = packet.getTag();
        if (nbt.contains(CURRENT_ITEM_TAG)) {
            currentItem = ItemStack.of((CompoundTag) Objects.requireNonNull(nbt.get(CURRENT_ITEM_TAG)));
        } else {
            currentItem = ItemStack.EMPTY;
        }

        if (nbt.contains(BLOCK_TAG)) {
            inventory.setStackInSlot(0, ItemStack.of((CompoundTag) Objects.requireNonNull(nbt.get(BLOCK_TAG))));
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
                solidProportion += ((float) solidAmount) / (4 * meltable.getAmount());
            }
            return solidProportion;
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        }
        return 0;
    }

    public abstract CrucibleTypeEnum getCrucibleType();

    private CrucibleRecipe getMeltable() {
        return ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(currentItem.getItem());
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
