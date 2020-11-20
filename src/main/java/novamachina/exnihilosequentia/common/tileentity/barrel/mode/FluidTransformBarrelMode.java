package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.Constants;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

public class FluidTransformBarrelMode extends AbstractBarrelMode {
    private int currentProgress;

    public FluidTransformBarrelMode(String name) {
        super(name);
        currentProgress = 0;
    }

    @Override
    public void tick(AbstractBarrelTile barrelTile) {
        Block blockBelow = barrelTile.getWorld().getBlockState(barrelTile.getPos().add(0, -1, 0)).getBlock();
        Fluid fluidInTank = barrelTile.getTank().getFluid().getFluid();
        if (ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.isValidRecipe(fluidInTank, blockBelow)) {
            currentProgress++;
            spawnParticle(barrelTile);
            if (currentProgress >= Config.SECONDS_TO_FLUID_TRANSFORM.get() * 20) {
                currentProgress = 0;
                Fluid newFluid = ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.getResult(fluidInTank, blockBelow);
                barrelTile.getTank().setFluid(new FluidStack(newFluid, AbstractBarrelTile.MAX_FLUID_AMOUNT));
                barrelTile.setMode(Constants.BarrelModes.FLUID);
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(AbstractBarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        return ActionResultType.PASS;
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
        currentProgress = nbt.getInt("currentProgress");
    }

    @Override
    public CompoundNBT write() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("currentProgress", currentProgress);
        return nbt;
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

        info.add(new TranslationTextComponent("waila.progress", StringUtils
            .formatPercent((float) currentProgress / (Config.SECONDS_TO_FLUID_TRANSFORM.get() * 20))));

        return info;
    }

    @Override
    public ItemStack handleInsert(AbstractBarrelTile barrelTile, ItemStack stack) {
        return stack;
    }
}
