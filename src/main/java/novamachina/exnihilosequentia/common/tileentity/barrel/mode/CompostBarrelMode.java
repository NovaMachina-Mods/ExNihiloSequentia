package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

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
    public ActionResultType onBlockActivated(AbstractBarrelTile barrelTile, Player player, InteractionHand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        if (ExNihiloRegistries.COMPOST_REGISTRY.containsSolid(player.getItemInHand(handIn).getItem()) && barrelTile
                .addSolid(ExNihiloRegistries.COMPOST_REGISTRY.getSolidAmount(player.getItemInHand(handIn).getItem()), false)) {
            player.getItemInHand(handIn).shrink(1);
        }

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
        return ExNihiloRegistries.COMPOST_REGISTRY.containsSolid(stack.getItem());
    }

    @Override
    public void read(CompoundTag nbt) {
        this.currentProgress = nbt.getInt("currentProgress");
    }

    @Override
    public Tag write() {
        CompoundTag modeInfo = new CompoundTag();
        modeInfo.putInt("currentProgress", currentProgress);
        return modeInfo;
    }

    @Override
    protected void spawnParticle(AbstractBarrelTile barrelTile) {
        if (Config.getShowParticles()) {
            ((ServerLevel) barrelTile.level)
                    .sendParticles(ParticleTypes.EFFECT,
                            barrelTile.blockPosition().getX() + barrelTile.level.random.nextDouble(),
                            barrelTile.blockPosition().getY() + barrelTile.level.random.nextDouble(),
                            barrelTile.blockPosition().getZ() + barrelTile.level.random.nextDouble(),
                            1,
                            0.0,
                            0.0,
                            0.0,
                            0.05);
        }
    }

    @Override
    public List<TranslatableComponent> getWailaInfo(AbstractBarrelTile barrelTile) {
        List<TranslatableComponent> info = new ArrayList<>();
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
