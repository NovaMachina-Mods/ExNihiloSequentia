package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.item.dolls.DollItem;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.TankUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FluidsBarrelMode extends AbstractBarrelMode {
    public FluidsBarrelMode(@Nonnull final String name) {
        super(name);
    }

    @Override
    public void tick(@Nonnull final AbstractBarrelTile barrelTile) {
        if(barrelTile.getFluidAmount() <= 0) {
            barrelTile.setMode(BarrelModeRegistry.getModeFromName(ExNihiloConstants.BarrelModes.EMPTY));
        }
        if (barrelTile.getFluidAmount() >= AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            if (fluidOnTop(barrelTile)) {
                return;
            }
            @Nullable final World world = barrelTile.getLevel();
            if (world != null) {
                @Nonnull final BlockPos blockPosBelow = barrelTile.getBlockPos().offset(0, -1, 0);
                @Nonnull final Block blockBelow = world.getBlockState(blockPosBelow).getBlock();
                fluidTransform(barrelTile, blockBelow);
            }
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    private boolean doMobSpawn(@Nonnull final AbstractBarrelTile barrelTile, @Nonnull final PlayerEntity player,
                               @Nonnull final Hand handIn) {
        if(barrelTile.getFluidAmount() < AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            return false;
        }

        @Nonnull final Item item = player.getItemInHand(handIn).getItem();

        if (item instanceof DollItem) {
            @Nonnull final DollItem doll = (DollItem) item;
            @Nullable final Fluid barrelFluid = barrelTile.getFluid();
            if (barrelFluid != null && barrelFluid.isSame(doll.getSpawnFluid())) {
                barrelTile.setMode(ExNihiloConstants.BarrelModes.MOB);
                ((MobSpawnBarrelMode) barrelTile.getMode()).setDoll(doll);
                player.getItemInHand(handIn).shrink(1);
                return true;
            }
        }

        return false;
    }

    private boolean fluidTransform(@Nonnull final AbstractBarrelTile barrelTile, @Nonnull final IItemProvider catalyst) {
        @Nullable final Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();

        if (ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.isValidRecipe(fluidInTank, catalyst)) {
            barrelTile.setMode(ExNihiloConstants.BarrelModes.TRANSFORM);
            ((FluidTransformBarrelMode)barrelTile.getMode()).setCatalyst(catalyst);
            return true;
        }
        return false;
    }

    private boolean fluidOnTop(AbstractBarrelTile barrelTile) {
        if(barrelTile.getFluidAmount() < AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            return false;
        }

        @Nullable final World world = barrelTile.getLevel();
        if (world == null)
            return false;

        @Nonnull final Fluid fluidOnTop = world.getFluidState(barrelTile.getBlockPos().offset(0, 1, 0)).getType();
        @Nonnull final Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();

        if (ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.isValidRecipe(fluidInTank, fluidOnTop)) {
            barrelTile.getTank().setFluid(FluidStack.EMPTY);
            barrelTile.getInventory().setStackInSlot(0, ExNihiloRegistries.FLUID_ON_TOP_REGISTRY
                .getResult(fluidInTank, fluidOnTop));
            barrelTile.setMode(ExNihiloConstants.BarrelModes.BLOCK);
            return true;
        }
        return false;
    }

    @Override
    @Nonnull
    public ActionResultType onBlockActivated(@Nonnull final AbstractBarrelTile barrelTile,
                                             @Nonnull final PlayerEntity player, @Nonnull final Hand handIn,
                                             @Nonnull final IFluidHandler fluidHandler,
                                             @Nonnull final IItemHandler itemHandler) {
        @Nonnull final ItemStack stack = player.getItemInHand(handIn);
        if (stack.isEmpty()) {
            return ActionResultType.SUCCESS;
        }

        if(TankUtil.drainWaterIntoBottle(barrelTile, player, fluidHandler)) {
            return ActionResultType.SUCCESS;
        }
        if(TankUtil.drainWaterFromBottle(barrelTile, player, fluidHandler)) {
            return ActionResultType.SUCCESS;
        }

        boolean result = FluidUtil.interactWithFluidHandler(player, handIn, fluidHandler);

        if (result) {
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            @Nullable final World world = barrelTile.getLevel();
            if (world != null) {
                @Nonnull final BlockState blockState = barrelTile.getBlockState();
                world.sendBlockUpdated(barrelTile.getBlockPos(), blockState, blockState, 2);
            }
            barrelTile.setChanged();
            return ActionResultType.SUCCESS;
        }

        if (fluidBlockTransform(barrelTile, player, handIn)) {
            return ActionResultType.SUCCESS;
        }

        @Nonnull final IItemProvider catalyst = player.getItemInHand(handIn).getItem();
        if(fluidTransform(barrelTile, catalyst)) {
            player.getItemInHand(handIn).shrink(1);
        }

        doMobSpawn(barrelTile, player, handIn);

        return ActionResultType.SUCCESS;
    }

    private boolean fluidBlockTransform(@Nonnull final AbstractBarrelTile barrelTile,
                                        @Nonnull final PlayerEntity player, @Nonnull final Hand handIn) {
        if(barrelTile.getFluidAmount() < AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            return false;
        }
        @Nonnull final Fluid fluid = barrelTile.getTank().getFluid().getFluid();
        @Nonnull final Item input = player.getItemInHand(handIn).getItem();
        if (ExNihiloRegistries.FLUID_BLOCK_REGISTRY.isValidRecipe(fluid, input)) {
            barrelTile.getTank().drain(FluidAttributes.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
            barrelTile.getInventory()
                .setStackInSlot(0, new ItemStack(ExNihiloRegistries.FLUID_BLOCK_REGISTRY.getResult(fluid, input)));
            if(!player.isCreative()) {
                player.getItemInHand(handIn).shrink(1);
            }
            barrelTile.setMode(ExNihiloConstants.BarrelModes.BLOCK);
            return true;
        }
        return false;
    }

    @Override
    public boolean canFillWithFluid(@Nonnull final AbstractBarrelTile barrel) {
        return true;
    }

    @Override
    public boolean isEmptyMode() {
        return false;
    }

    @Override
    protected boolean isTriggerItem(@Nonnull final ItemStack stack) {
        return stack.getItem() instanceof BucketItem ||
               ItemStack.isSame(stack, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER));
    }

    @Override
    public void read(@Nonnull final CompoundNBT nbt) {
        // NOOP
    }

    @Override
    @Nonnull
    public CompoundNBT write() {
        return new CompoundNBT();
    }

    @Override
    protected void spawnParticle(@Nonnull final AbstractBarrelTile barrelTile) {
        // NOOP
    }

    @Override
    @Nonnull
    public List<ITextComponent> getWailaInfo(@Nonnull final AbstractBarrelTile barrelTile) {
        @Nonnull final List<ITextComponent> info = new ArrayList<>();

        @Nonnull final String key;
        if(barrelTile.getFluid() == null){
            key = Fluids.EMPTY.defaultFluidState().createLegacyBlock().getBlock().getDescriptionId();
        } else{
            key = barrelTile.getFluid().defaultFluidState().createLegacyBlock().getBlock().getDescriptionId();
        }

        info.add(new TranslationTextComponent("waila.barrel.fluidAmount", new TranslationTextComponent(key), barrelTile.getFluidAmount()));

        return info;
    }

    @Override
    @Nonnull
    public ItemStack handleInsert(@Nonnull final AbstractBarrelTile barrelTile, @Nonnull final ItemStack stack,
                                  final boolean simulate) {
        if(barrelTile.getFluidAmount() < AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            return stack.copy();
        }
        @Nonnull final ItemStack returnStack = stack.copy();
        @Nonnull final Fluid fluid = barrelTile.getTank().getFluid().getFluid();
        @Nonnull final Item input = stack.getItem();
        if (ExNihiloRegistries.FLUID_BLOCK_REGISTRY.isValidRecipe(fluid, input)) {
            if(!simulate) {
                barrelTile.getTank().drain(FluidAttributes.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
                barrelTile.getInventory()
                        .setStackInSlot(0, new ItemStack(ExNihiloRegistries.FLUID_BLOCK_REGISTRY.getResult(fluid, input)));
                barrelTile.setMode(ExNihiloConstants.BarrelModes.BLOCK);
            }
            returnStack.shrink(1);
            return returnStack;
        }

        if (input instanceof DollItem) {
            @Nonnull final DollItem doll = (DollItem) input;
            @Nullable final Fluid barrelFluid = barrelTile.getFluid();
            if (barrelFluid != null && barrelFluid.isSame(doll.getSpawnFluid())) {
                if(!simulate) {
                    barrelTile.setMode(ExNihiloConstants.BarrelModes.MOB);
                    ((MobSpawnBarrelMode) barrelTile.getMode()).setDoll(doll);
                }
                returnStack.shrink(1);
            }
        }
        return returnStack;
    }
}
