package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EmptyBarrelMode extends AbstractBarrelMode {
    public EmptyBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick(AbstractBarrelTile barrelTile) {
        // NOOP
    }

    @Override
    public ActionResultType onBlockActivated(AbstractBarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        if (!player.getItemInHand(handIn).isEmpty()) {

            ItemStack stack = player.getItemInHand(handIn);
            List<Supplier<AbstractBarrelMode>> modes = BarrelModeRegistry.getModes(BarrelModeRegistry.TriggerType.ITEM);
            for (Supplier<AbstractBarrelMode> mode : modes) {
                if (mode.get().isTriggerItem(stack)) {
                    barrelTile.setMode(mode.get());
                    barrelTile.getMode().onBlockActivated(barrelTile, player, handIn, fluidHandler, itemHandler);
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean canFillWithFluid(AbstractBarrelTile barrel) {
        return true;
    }

    @Override
    public boolean isEmptyMode() {
        return true;
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
        return new ArrayList<>();
    }

    @Override
    public ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack, boolean simulate) {
        if(ExNihiloRegistries.COMPOST_REGISTRY.containsSolid(stack.getItem())) {
            barrelTile.setMode(ExNihiloConstants.BarrelModes.COMPOST);
            ItemStack returnStack = barrelTile.getMode().handleInsert(barrelTile, stack, simulate);
            if(simulate) {
                barrelTile.setMode(ExNihiloConstants.BarrelModes.EMPTY);
            }
            return returnStack;
        }
        return stack;
    }
}