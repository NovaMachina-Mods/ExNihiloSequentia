package novamachina.exnihilosequentia.common.compat.jei.fluidtransform;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class FluidTransformJEIRecipe {
    private final ItemStack blockBelow;
    private final FluidStack fluidInBarrel;
    private final FluidStack result;

    public FluidTransformJEIRecipe(FluidStack fluidInBarrel, ItemStack blockBelow, FluidStack result) {
        this.fluidInBarrel = fluidInBarrel;
        this.blockBelow = blockBelow;
        this.result = result;
    }

    public ItemStack getBlockBelow() {
        return blockBelow;
    }

    public FluidStack getFluidInBarrel() {
        return fluidInBarrel;
    }

    public FluidStack getResult() {
        return result;
    }


}
