package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
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

    public abstract InteractionResult onBlockActivated(AbstractBarrelTile barrelTile, Player player, InteractionHand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler);

    public abstract boolean canFillWithFluid(AbstractBarrelTile barrel);

    public abstract boolean isEmptyMode();

    protected abstract boolean isTriggerItem(ItemStack stack);

    public abstract void read(CompoundTag nbt);

    public abstract CompoundTag write();

    protected abstract void spawnParticle(AbstractBarrelTile barrelTile);

    public abstract List<Component> getWailaInfo(AbstractBarrelTile barrelTile);

    public abstract ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack, boolean simulate);
}
