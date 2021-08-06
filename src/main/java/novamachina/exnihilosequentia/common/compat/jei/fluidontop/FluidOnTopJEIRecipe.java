package novamachina.exnihilosequentia.common.compat.jei.fluidontop;

import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class FluidOnTopJEIRecipe {
    private final FluidStack fluidInBarrel;
    private final FluidStack fluidOnTop;
    private final ItemStack result;

    public FluidOnTopJEIRecipe(FluidStack fluidInBarrel, FluidStack fluidOnTop, ItemStack result) {
        this.fluidInBarrel = fluidInBarrel;
        this.fluidOnTop = fluidOnTop;
        this.result = result;
    }

    public FluidStack getFluidInBarrel() {
        return fluidInBarrel;
    }

    public FluidStack getFluidOnTop() {
        return fluidOnTop;
    }

    public List<FluidStack> getInputs() {
        return ImmutableList.of(fluidInBarrel, fluidOnTop);
    }

    public List<ItemStack> getOutputs() {
        return ImmutableList.of(result);
    }

    public ItemStack getResult() {
        return result;
    }
}
