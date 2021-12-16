package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import com.google.common.base.Preconditions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FluidTransformBarrelMode extends AbstractBarrelMode {
    private int currentProgress;
    @Nullable private IItemProvider catalyst;

    public FluidTransformBarrelMode(String name) {
        super(name);
        currentProgress = 0;
    }

    public void setCatalyst(@Nonnull final IItemProvider catalyst) {
        this.catalyst = catalyst;
    }

    @Override
    public void tick(@Nonnull final AbstractBarrelTile barrelTile) {
        @Nonnull final Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();
        currentProgress++;
        spawnParticle(barrelTile);
        if (currentProgress >= Config.getSecondsToFluidTransform() * 20) {
            currentProgress = 0;
            @Nonnull final Fluid newFluid = ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.getResult(fluidInTank, catalyst);
            barrelTile.getTank().setFluid(new FluidStack(newFluid, AbstractBarrelTile.MAX_FLUID_AMOUNT));
            barrelTile.setMode(ExNihiloConstants.BarrelModes.FLUID);
        }
    }

    @Override
    @Nonnull
    public ActionResultType onBlockActivated(@Nonnull final AbstractBarrelTile barrelTile,
                                             @Nonnull final PlayerEntity player, @Nonnull final Hand handIn,
                                             @Nonnull final IFluidHandler fluidHandler,
                                             @Nonnull final IItemHandler itemHandler) {
        return ActionResultType.PASS;
    }

    @Override
    public boolean canFillWithFluid(@Nonnull final AbstractBarrelTile barrel) {
        return false;
    }

    @Override
    public boolean isEmptyMode() {
        return false;
    }

    @Override
    protected boolean isTriggerItem(@Nonnull final ItemStack stack) {
        return false;
    }

    @Override
    public void read(@Nonnull final CompoundNBT nbt) {
        currentProgress = nbt.getInt("currentProgress");
        catalyst = ItemStack.of(nbt).getItem();
    }

    @Override
    @Nonnull
    public CompoundNBT write() {
        @Nonnull final CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("currentProgress", currentProgress);
        new ItemStack(catalyst).save(nbt);
        return nbt;
    }

    @Override
    protected void spawnParticle(@Nonnull final AbstractBarrelTile barrelTile) throws NullPointerException  {
        @Nullable final ServerWorld level = (ServerWorld) barrelTile.getLevel();
        Preconditions.checkNotNull(level, "Level is null.");
        level.sendParticles(ParticleTypes.EFFECT,
                barrelTile.getBlockPos().getX() + barrelTile.getLevel().random.nextDouble(),
                barrelTile.getBlockPos().getY() + barrelTile.getLevel().random.nextDouble(),
                barrelTile.getBlockPos().getZ() + barrelTile.getLevel().random.nextDouble(),
                1,
                0,
                0,
                0,
                0.05);
    }

    @Nonnull
    @Override
    public List<ITextComponent> getWailaInfo(@Nonnull final AbstractBarrelTile barrelTile) {
        @Nullable final List<ITextComponent> info = new ArrayList<>();

        info.add(new TranslationTextComponent("waila.progress", StringUtils
                .formatPercent((float) currentProgress / (Config.getSecondsToFluidTransform() * 20))));

        return info;
    }

    @Nonnull
    @Override
    public ItemStack handleInsert(@Nonnull final AbstractBarrelTile barrelTile, @Nonnull final ItemStack stack,
                                  final boolean simulate) {
        return stack;
    }
}
