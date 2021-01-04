package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
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
    public ActionResultType onBlockActivated(AbstractBarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        if (ExNihiloRegistries.COMPOST_REGISTRY.containsSolid(player.getHeldItem(handIn).getItem()) && barrelTile
            .addSolid(ExNihiloRegistries.COMPOST_REGISTRY.getSolidAmount(player.getHeldItem(handIn).getItem()))) {
            player.getHeldItem(handIn).shrink(1);
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
    public void read(CompoundNBT nbt) {
        this.currentProgress = nbt.getInt("currentProgress");
    }

    @Override
    public CompoundNBT write() {
        CompoundNBT modeInfo = new CompoundNBT();
        modeInfo.putInt("currentProgress", currentProgress);
        return modeInfo;
    }

    @Override
    protected void spawnParticle(AbstractBarrelTile barrelTile) {
        ((ServerWorld) barrelTile.getWorld())
            .spawnParticle(ParticleTypes.EFFECT,
                barrelTile.getPos().getX() + barrelTile.getWorld().rand.nextDouble(),
                barrelTile.getPos().getY() + barrelTile.getWorld().rand.nextDouble(),
                barrelTile.getPos().getZ() + barrelTile.getWorld().rand.nextDouble(),
                1,
                0.0,
                0.0,
                0.0,
                0.05);
    }

    @Override
    public List<ITextComponent> getWailaInfo(AbstractBarrelTile barrelTile) {
        List<ITextComponent> info = new ArrayList<>();
        if (currentProgress <= 0) {
            info.add(new TranslationTextComponent("waila.barrel.compost", barrelTile
                .getSolidAmount(), AbstractBarrelTile.MAX_SOLID_AMOUNT));
        } else {
            info.add(new TranslationTextComponent("waila.progress", StringUtils
                .formatPercent((float) currentProgress / (Config.getSecondsToCompost() * 20))));
        }
        return info;
    }

    @Override
    public ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack) {
        ItemStack returnStack = stack.copy();
        if (barrelTile.addSolid(ExNihiloRegistries.COMPOST_REGISTRY.getSolidAmount(stack.getItem()))) {
            returnStack.shrink(1);
        }
        return returnStack;
    }
}
