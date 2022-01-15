package novamachina.exnihilosequentia.common.compat.jei.fluidontop;

import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class FluidOnTopJEIRecipe {
    private final FluidStack fluidInBarrel;
    private final FluidStack fluidOnTop;
    private final ItemStack result;

    public FluidOnTopJEIRecipe(@Nonnull final FluidStack fluidInBarrel, @Nonnull final FluidStack fluidOnTop,
                               @Nonnull final ItemStack result) {
        this.fluidInBarrel = fluidInBarrel;
        this.fluidOnTop = fluidOnTop;
        this.result = result;
    }

    @Nonnull
    public FluidStack getFluidInBarrel() {
        return fluidInBarrel;
    }

    @Nonnull
    public FluidStack getFluidOnTop() {
        return fluidOnTop;
    }

    @Nonnull
    public List<FluidStack> getInputs() {
        return ImmutableList.of(fluidInBarrel, fluidOnTop);
    }

    @Nonnull
    public List<ItemStack> getOutputs() {
        return ImmutableList.of(result);
    }

    @Nonnull
    public ItemStack getResult() {
        return result;
    }
}
