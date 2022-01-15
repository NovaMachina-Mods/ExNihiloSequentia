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

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EmptyBarrelMode extends AbstractBarrelMode {
    public EmptyBarrelMode(@Nonnull final String name) {
        super(name);
    }

    @Override
    public void tick(@Nonnull final AbstractBarrelTile barrelTile) {
        // NOOP
    }

    @Override
    @Nonnull
    public ActionResultType onBlockActivated(@Nonnull final AbstractBarrelTile barrelTile,
                                             @Nonnull final PlayerEntity player, @Nonnull final Hand handIn,
                                             @Nonnull final IFluidHandler fluidHandler,
                                             @Nonnull final IItemHandler itemHandler) {
        if (!player.getItemInHand(handIn).isEmpty()) {

            @Nonnull final ItemStack stack = player.getItemInHand(handIn);
            @Nonnull final List<Supplier<AbstractBarrelMode>> modes = BarrelModeRegistry.getModes(BarrelModeRegistry.TriggerType.ITEM);
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
    public boolean canFillWithFluid(@Nonnull final AbstractBarrelTile barrel) {
        return true;
    }

    @Override
    public boolean isEmptyMode() {
        return true;
    }

    @Override
    protected boolean isTriggerItem(@Nonnull final ItemStack stack) {
        return false;
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
        return new ArrayList<>();
    }

    @Override
    @Nonnull
    public ItemStack handleInsert(@Nonnull final AbstractBarrelTile barrelTile, @Nonnull final ItemStack stack,
                                  final boolean simulate) {
        if(ExNihiloRegistries.COMPOST_REGISTRY.containsSolid(stack.getItem())) {
            barrelTile.setMode(ExNihiloConstants.BarrelModes.COMPOST);
            @Nonnull final ItemStack returnStack = barrelTile.getMode().handleInsert(barrelTile, stack, simulate);
            if(simulate) {
                barrelTile.setMode(ExNihiloConstants.BarrelModes.EMPTY);
            }
            return returnStack;
        }
        return stack;
    }
}
