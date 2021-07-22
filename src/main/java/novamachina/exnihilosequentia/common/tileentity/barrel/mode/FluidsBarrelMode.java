package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
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

import java.util.ArrayList;
import java.util.List;

public class FluidsBarrelMode extends AbstractBarrelMode {
    public FluidsBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick(AbstractBarrelTile barrelTile) {
        if(barrelTile.getFluidAmount() <= 0) {
            barrelTile.setMode(BarrelModeRegistry.getModeFromName(ExNihiloConstants.BarrelModes.EMPTY));
        }
        if (barrelTile.getFluidAmount() >= AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            if (fluidOnTop(barrelTile)) {
                return;
            }
            Block blockBelow = barrelTile.level.getBlockState(barrelTile.blockPosition().offset(0, -1, 0)).getBlock();
            fluidTransform(barrelTile, blockBelow);
        }
    }

    private boolean doMobSpawn(AbstractBarrelTile barrelTile, Player player, InteractionHand handIn) {
        if(barrelTile.getFluidAmount() < AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            return false;
        }

        Item item = player.getItemInHand(handIn).getItem();

        if (item instanceof DollItem) {
            DollItem doll = (DollItem) item;
            if (barrelTile.getFluid().isSame(doll.getSpawnFluid())) {
                barrelTile.setMode(ExNihiloConstants.BarrelModes.MOB);
                ((MobSpawnBarrelMode) barrelTile.getMode()).setDoll(doll);
                player.getItemInHand(handIn).shrink(1);
                return true;
            }
        }

        return false;
    }

    private boolean fluidTransform(AbstractBarrelTile barrelTile, Block catalyst) {

        Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();

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
        Fluid fluidOnTop = barrelTile.level.getFluidState(barrelTile.blockPosition().offset(0, 1, 0)).getType();
        Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();

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
    public ActionResultType onBlockActivated(AbstractBarrelTile barrelTile, Player player, InteractionHand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        ItemStack stack = player.getItemInHand(handIn);
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
            barrelTile.getLevel()
                .sendBlockUpdated(barrelTile.getBlockPos(), barrelTile.getBlockState(), barrelTile.getBlockState(), 2);
            barrelTile.setChanged();
            return ActionResultType.SUCCESS;
        }

        if (fluidBlockTransform(barrelTile, player, handIn)) {
            return ActionResultType.SUCCESS;
        }

        IItemProvider catalyst = player.getItemInHand(handIn).getItem();
        if(fluidTransform(barrelTile, catalyst)) {
            player.getItemInHand(handIn).shrink(1);
        }

        doMobSpawn(barrelTile, player, handIn);

        return ActionResultType.SUCCESS;
    }

    private boolean fluidBlockTransform(AbstractBarrelTile barrelTile, PlayerEntity player, Hand handIn) {
        if(barrelTile.getFluidAmount() < AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            return false;
        }
        Fluid fluid = barrelTile.getTank().getFluid().getFluid();
        Item input = player.getItemInHand(handIn).getItem();
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
    public boolean canFillWithFluid(AbstractBarrelTile barrel) {
        return true;
    }

    @Override
    public boolean isEmptyMode() {
        return false;
    }

    @Override
    protected boolean isTriggerItem(ItemStack stack) {
        return stack.getItem() instanceof BucketItem ||
               ItemStack.isSame(stack, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER));
    }

    @Override
    public void read(CompoundTag nbt) {
        // NOOP
    }

    @Override
    public Tag write() {
        return new CompoundNBT();
    }

    @Override
    protected void spawnParticle(AbstractBarrelTile barrelTile) {
        // NOOP
    }

    @Override
    public List<TranslatableComponent> getWailaInfo(AbstractBarrelTile barrelTile) {
        List<ITextComponent> info = new ArrayList<>();


        String key;
        if(barrelTile.getFluid() == null){
            key = Fluids.EMPTY.defaultFluidState().createLegacyBlock().getBlock().getDescriptionId();
        } else{
            key = barrelTile.getFluid().defaultFluidState().createLegacyBlock().getBlock().getDescriptionId();
        }

        info.add(new TranslationTextComponent("waila.barrel.fluidAmount", new TranslationTextComponent(key), barrelTile.getFluidAmount()));

        return info;
    }

    @Override
    public ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack, boolean simulate) {
        if(barrelTile.getFluidAmount() < AbstractBarrelTile.MAX_FLUID_AMOUNT) {
            return stack.copy();
        }
        ItemStack returnStack = stack.copy();
        Fluid fluid = barrelTile.getTank().getFluid().getFluid();
        Item input = stack.getItem();
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
            DollItem doll = (DollItem) input;
            if (barrelTile.getFluid().isSame(doll.getSpawnFluid())) {
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
