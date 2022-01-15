package novamachina.exnihilosequentia.common.compat.jei.fluidtransform;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class FluidTransformJEIRecipe {
    @Nonnull private final ItemStack blockBelow;
    @Nonnull private final FluidStack fluidInBarrel;
    @Nonnull private final FluidStack result;

    public FluidTransformJEIRecipe(@Nonnull final FluidStack fluidInBarrel, @Nonnull final ItemStack blockBelow,
                                   @Nonnull final FluidStack result) {
        this.fluidInBarrel = fluidInBarrel;
        this.blockBelow = blockBelow;
        this.result = result;
    }

    @Nonnull
    public ItemStack getBlockBelow() {
        return blockBelow;
    }

    @Nonnull
    public FluidStack getFluidInBarrel() {
        return fluidInBarrel;
    }

    @Nonnull
    public FluidStack getResult() {
        return result;
    }


}
