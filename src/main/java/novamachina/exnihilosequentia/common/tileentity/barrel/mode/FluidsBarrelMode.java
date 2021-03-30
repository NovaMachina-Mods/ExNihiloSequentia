package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.item.dolls.DollItem;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.BarrelModes;

import java.util.ArrayList;
import java.util.List;

public class FluidsBarrelMode extends AbstractBarrelMode {
    public FluidsBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick(AbstractBarrelTile barrelTile) {
        if(barrelTile.getFluidAmount() <= 0) {
            barrelTile.setMode(BarrelModeRegistry.getModeFromName(BarrelModes.EMPTY));
        }
        if (barrelTile.getFluidAmount() >= AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            if (fluidOnTop(barrelTile)) {
                return;
            }
            Block blockBelow = barrelTile.getWorld().getBlockState(barrelTile.getPos().add(0, -1, 0)).getBlock();
            fluidTransform(barrelTile, blockBelow);
        }
    }

    private boolean doMobSpawn(AbstractBarrelTile barrelTile, PlayerEntity player, Hand handIn) {
        if(barrelTile.getFluidAmount() < AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            return false;
        }

        Item item = player.getHeldItem(handIn).getItem();

        if (item instanceof DollItem) {
            DollItem doll = (DollItem) item;
            if (barrelTile.getFluid().isEquivalentTo(doll.getSpawnFluid())) {
                barrelTile.setMode(BarrelModes.MOB);
                ((MobSpawnBarrelMode) barrelTile.getMode()).setDoll(doll);
                player.getHeldItem(handIn).shrink(1);
                return true;
            }
        }

        return false;
    }

    private boolean fluidTransform(AbstractBarrelTile barrelTile, IItemProvider catalyst) {

        Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();

        if (ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.isValidRecipe(fluidInTank, catalyst)) {
            barrelTile.setMode(BarrelModes.TRANSFORM);
            ((FluidTransformBarrelMode)barrelTile.getMode()).setCatalyst(catalyst);
            return true;
        }
        return false;
    }

    private boolean fluidOnTop(AbstractBarrelTile barrelTile) {
        if(barrelTile.getFluidAmount() < AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            return false;
        }
        Fluid fluidOnTop = barrelTile.getWorld().getFluidState(barrelTile.getPos().add(0, 1, 0)).getFluid();
        Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();

        if (ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.isValidRecipe(fluidInTank, fluidOnTop)) {
            barrelTile.getTank().setFluid(FluidStack.EMPTY);
            barrelTile.getInventory().setStackInSlot(0, ExNihiloRegistries.FLUID_ON_TOP_REGISTRY
                .getResult(fluidInTank, fluidOnTop));
            barrelTile.setMode(BarrelModes.BLOCK);
            return true;
        }
        return false;
    }

    @Override
    public ActionResultType onBlockActivated(AbstractBarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        ItemStack stack = player.getHeldItem(handIn);
        if (stack.isEmpty()) {
            return ActionResultType.SUCCESS;
        }

        boolean result = FluidUtil.interactWithFluidHandler(player, handIn, fluidHandler);

        if (result) {
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            barrelTile.getWorld()
                .notifyBlockUpdate(barrelTile.getPos(), barrelTile.getBlockState(), barrelTile.getBlockState(), 2);
            barrelTile.markDirty();
            return ActionResultType.SUCCESS;
        }

        if (fluidBlockTransform(barrelTile, player, handIn)) {
            return ActionResultType.SUCCESS;
        }

        IItemProvider catalyst = player.getHeldItem(handIn).getItem();
        if(fluidTransform(barrelTile, catalyst)) {
            player.getHeldItem(handIn).shrink(1);
        }

        doMobSpawn(barrelTile, player, handIn);

        return ActionResultType.SUCCESS;
    }

    private boolean fluidBlockTransform(AbstractBarrelTile barrelTile, PlayerEntity player, Hand handIn) {
        if(barrelTile.getFluidAmount() < AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            return false;
        }
        Fluid fluid = barrelTile.getTank().getFluid().getFluid();
        Item input = player.getHeldItem(handIn).getItem();
        if (ExNihiloRegistries.FLUID_BLOCK_REGISTRY.isValidRecipe(fluid, input)) {
            barrelTile.getTank().drain(FluidAttributes.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
            barrelTile.getInventory()
                .setStackInSlot(0, new ItemStack(ExNihiloRegistries.FLUID_BLOCK_REGISTRY.getResult(fluid, input)));
            if(!player.isCreative()) {
                player.getHeldItem(handIn).shrink(1);
            }
            barrelTile.setMode(BarrelModes.BLOCK);
            return true;
        }
        return false;
    }

    @Override
    public boolean canFillWithFluid(AbstractBarrelTile barrel) {
        return true;
    }

    @Override
    public boolean isEmptyMode() {
        return false;
    }

    @Override
    protected boolean isTriggerItem(ItemStack stack) {
        return false;
    }

    @Override
    public void read(CompoundNBT nbt) {
        // NOOP
    }

    @Override
    public CompoundNBT write() {
        return new CompoundNBT();
    }

    @Override
    protected void spawnParticle(AbstractBarrelTile barrelTile) {
        // NOOP
    }

    @Override
    public List<ITextComponent> getWailaInfo(AbstractBarrelTile barrelTile) {
        List<ITextComponent> info = new ArrayList<>();


        String key;
        if(barrelTile.getFluid() == null){
            key = Fluids.EMPTY.getDefaultState().getBlockState().getBlock().getTranslationKey();
        } else{
            key = barrelTile.getFluid().getDefaultState().getBlockState().getBlock().getTranslationKey();
        }

        info.add(new TranslationTextComponent("waila.barrel.fluidAmount", new TranslationTextComponent(key), barrelTile.getFluidAmount()));

        return info;
    }

    @Override
    public ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack) {
        if(barrelTile.getFluidAmount() < AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            return stack.copy();
        }
        ItemStack returnStack = stack.copy();
        Fluid fluid = barrelTile.getTank().getFluid().getFluid();
        Item input = stack.getItem();
        if (ExNihiloRegistries.FLUID_BLOCK_REGISTRY.isValidRecipe(fluid, input)) {
            barrelTile.getTank().drain(FluidAttributes.BUCKET_VOLUME, IFluidHandler.FluidAction.EXECUTE);
            barrelTile.getInventory()
                .setStackInSlot(0, new ItemStack(ExNihiloRegistries.FLUID_BLOCK_REGISTRY.getResult(fluid, input)));
            barrelTile.setMode(BarrelModes.BLOCK);
            returnStack.shrink(1);
            return returnStack;
        }

        if (input instanceof DollItem) {
            DollItem doll = (DollItem) input;
            if (barrelTile.getFluid().isEquivalentTo(doll.getSpawnFluid())) {
                barrelTile.setMode(BarrelModes.MOB);
                ((MobSpawnBarrelMode) barrelTile.getMode()).setDoll(doll);
                returnStack.shrink(1);
            }
        }
        return returnStack;
    }
}
