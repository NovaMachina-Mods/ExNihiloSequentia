package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FluidTransformBarrelMode extends AbstractBarrelMode {
    private int currentProgress;
    private ItemLike catalyst;

    public FluidTransformBarrelMode(String name) {
        super(name);
        currentProgress = 0;
    }

    public void setCatalyst(ItemLike catalyst) {
        this.catalyst = catalyst;
    }

    @Override
    public void tick(AbstractBarrelTile barrelTile) {
        Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();
        currentProgress++;
        spawnParticle(barrelTile);
        if (currentProgress >= Config.getSecondsToFluidTransform() * 20) {
            currentProgress = 0;
            Fluid newFluid = ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.getResult(fluidInTank, catalyst);
            barrelTile.getTank().setFluid(new FluidStack(newFluid, AbstractBarrelTile.MAX_FLUID_AMOUNT));
            barrelTile.setMode(ExNihiloConstants.BarrelModes.FLUID);
        }
    }

    @Override
    public InteractionResult onBlockActivated(AbstractBarrelTile barrelTile, Player player, InteractionHand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        return InteractionResult.PASS;
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
        currentProgress = nbt.getInt("currentProgress");
        catalyst = ItemStack.of(nbt).getItem();
    }

    @Override
    public Tag write() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("currentProgress", currentProgress);
        new ItemStack(catalyst).save(nbt);
        return nbt;
    }

    @Override
    protected void spawnParticle(AbstractBarrelTile barrelTile) {
        ((ServerLevel) barrelTile.getLevel())
            .sendParticles(ParticleTypes.EFFECT,
                barrelTile.getBlockPos().getX() + barrelTile.getLevel().random.nextDouble(),
                barrelTile.getBlockPos().getY() + barrelTile.getLevel().random.nextDouble(),
                barrelTile.getBlockPos().getZ() + barrelTile.getLevel().random.nextDouble(),
                1,
                0.0,
                0.0,
                0.0,
                0.05);
    }

    @Override
    public List<Component> getWailaInfo(AbstractBarrelTile barrelTile) {
        List<Component> info = new ArrayList<>();

        info.add(new TranslatableComponent("waila.progress", StringUtils
            .formatPercent((float) currentProgress / (Config.getSecondsToFluidTransform() * 20))));

        return info;
    }

    @Override
    public ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack, boolean simulate) {
        return stack;
    }
}
