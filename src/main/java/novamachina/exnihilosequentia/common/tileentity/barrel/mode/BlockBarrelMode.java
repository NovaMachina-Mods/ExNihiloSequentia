package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

public class BlockBarrelMode extends AbstractBarrelMode {
    public BlockBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick(AbstractBarrelTile barrelTile) {
        // NOOP
    }

    @Override
    public ActionResultType onBlockActivated(AbstractBarrelTile barrelTile, Player player, InteractionHand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        barrelTile.level
            .addFreshEntity(new ItemEntity(barrelTile.level, barrelTile.blockPosition().getX() + 0.5F, barrelTile.blockPosition()
                .getY() + 0.5F,barrelTile.blockPosition().getZ() + 0.5F, new ItemStack(barrelTile.getInventory().getStackInSlot(0)
                .getItem())));
        barrelTile.getInventory().setStackInSlot(0, ItemStack.EMPTY);
        barrelTile.setMode(ExNihiloConstants.BarrelModes.EMPTY);
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean canFillWithFluid(AbstractBarrelTile barrel) {
        return false;
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
    public void read(CompoundTag nbt) {
        // NOOP
    }

    @Override
    public Tag write() {
        return new CompoundTag();
    }

    @Override
    protected void spawnParticle(AbstractBarrelTile barrelTile) {
        // NOOP
    }

    @Override
    public List<TranslatableComponent> getWailaInfo(AbstractBarrelTile barrelTile) {
        List<TranslatableComponent> info = new ArrayList<>();

        info.add(new TranslatableComponent("waila.barrel.block", new TranslatableComponent(barrelTile.getInventory().getStackInSlot(0).getDescriptionId())));

        return info;
    }

    @Override
    public ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack, boolean simulate) {
        return stack;
    }
}
