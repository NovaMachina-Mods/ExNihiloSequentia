package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import com.google.common.base.Preconditions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompostBarrelMode extends AbstractBarrelMode {
    private int currentProgress;

    public CompostBarrelMode(String name) {
        super(name);
        currentProgress = 0;
    }

    @Override
    public void tick(AbstractBarrelTile barrelTile) {
        if (barrelTile.getSolidAmount() >= AbstractBarrelTile.MAX_SOLID_AMOUNT && barrelTile.getInventory()
                .getStackInSlot(0)
                .isEmpty()) {
            currentProgress++;
            spawnParticle(barrelTile);
            if (currentProgress >= Config.getSecondsToCompost() * 20) {
                currentProgress = 0;
                barrelTile.getInventory()
                        .setStackInSlot(0, new ItemStack(ForgeRegistries.BLOCKS.getValue(Blocks.DIRT.getRegistryName())));
                barrelTile.removeSolid(barrelTile.getSolidAmount());
                barrelTile.setMode(ExNihiloConstants.BarrelModes.BLOCK);
            }
        }
    }

    @Override
    public InteractionResult onBlockActivated(AbstractBarrelTile barrelTile, Player player, InteractionHand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        if (ExNihiloRegistries.COMPOST_REGISTRY.containsSolid(player.getItemInHand(handIn).getItem()) && barrelTile
                .addSolid(ExNihiloRegistries.COMPOST_REGISTRY.getSolidAmount(player.getItemInHand(handIn).getItem()), false)) {
            player.getItemInHand(handIn).shrink(1);
        }

        return InteractionResult.SUCCESS;
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
        return ExNihiloRegistries.COMPOST_REGISTRY.containsSolid(stack.getItem());
    }

    @Override
    public void read(CompoundTag nbt) {
        this.currentProgress = nbt.getInt("currentProgress");
    }

    @Override
    public CompoundTag write() {
        CompoundTag modeInfo = new CompoundTag();
        modeInfo.putInt("currentProgress", currentProgress);
        return modeInfo;
    }

    @Override
    protected void spawnParticle(AbstractBarrelTile barrelTile) {
        if (Config.getShowParticles()) {
            ServerLevel level = (ServerLevel) barrelTile.getLevel();
            Preconditions.checkNotNull(level, "Level is null.");
            level.sendParticles(ParticleTypes.ASH,
                    barrelTile.getBlockPos().getX() + (.2d + (.8d - .2d) * level.random.nextDouble()),
                    barrelTile.getBlockPos().getY() + 1.2d,
                    barrelTile.getBlockPos().getZ() + (.2d + (.8d - .2d) * level.random.nextDouble()),
                    1,
                    0,
                    0,
                    0,
                    0.01);
        }
    }

    @Override
    public List<Component> getWailaInfo(AbstractBarrelTile barrelTile) {
        List<Component> info = new ArrayList<>();
        if (currentProgress <= 0) {
            info.add(new TranslatableComponent("waila.barrel.compost", barrelTile
                    .getSolidAmount(), AbstractBarrelTile.MAX_SOLID_AMOUNT));
        } else {
            info.add(new TranslatableComponent("waila.progress", StringUtils
                    .formatPercent((float) currentProgress / (Config.getSecondsToCompost() * 20))));
        }
        return info;
    }

    @Override
    public ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack, boolean simulate) {
        if (barrelTile.addSolid(ExNihiloRegistries.COMPOST_REGISTRY.getSolidAmount(stack.getItem()), simulate)) {
            ItemStack returnStack = stack.copy();
            returnStack.shrink(1);
            return returnStack;
        }
        return stack;
    }
}
