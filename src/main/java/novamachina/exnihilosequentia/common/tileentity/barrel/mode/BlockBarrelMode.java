package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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
    }

    @Override
    public ActionResultType onBlockActivated(AbstractBarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        barrelTile.getWorld()
            .addEntity(new ItemEntity(barrelTile.getWorld(), barrelTile.getPos().getX() + 0.5F, barrelTile.getPos()
                .getY() + 0.5F,
                barrelTile.getPos().getZ() + 0.5F, new ItemStack(barrelTile.getInventory().getStackInSlot(0)
                .getItem())));
        barrelTile.getInventory().setStackInSlot(0, ItemStack.EMPTY);
        barrelTile.setMode(Constants.BarrelModes.EMPTY);
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
    public void read(CompoundNBT nbt) {

    }

    @Override
    public CompoundNBT write() {
        return new CompoundNBT();
    }

    @Override
    protected void spawnParticle(AbstractBarrelTile barrelTile) {

    }

    @Override
    public List<ITextComponent> getWailaInfo(AbstractBarrelTile barrelTile) {
        List<ITextComponent> info = new ArrayList<>();

        String block = I18n.format(barrelTile.getInventory().getStackInSlot(0).getTranslationKey());

        info.add(new TranslationTextComponent("waila.barrel.block", block));

        return info;
    }

    @Override
    public ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack) {
        return stack;
    }
}
