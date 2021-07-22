package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

public abstract class AbstractBarrelMode {
    private final String modeName;

    protected AbstractBarrelMode(String name) {
        this.modeName = name;
    }

    public String getModeName() {
        return modeName;
    }

    public abstract void tick(AbstractBarrelTile barrelTile);

    public abstract ActionResultType onBlockActivated(AbstractBarrelTile barrelTile, Player player, InteractionHand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler);

    public abstract boolean canFillWithFluid(AbstractBarrelTile barrel);

    public abstract boolean isEmptyMode();

    protected abstract boolean isTriggerItem(ItemStack stack);

    public abstract void read(CompoundTag nbt);

    public abstract Tag write();

    protected abstract void spawnParticle(AbstractBarrelTile barrelTile);

    public abstract List<TranslatableComponent> getWailaInfo(AbstractBarrelTile barrelTile);

    public abstract ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack, boolean simulate);
}
