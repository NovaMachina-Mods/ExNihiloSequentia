package novamachina.exnihilosequentia.api.crafting.fluiditem;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

import javax.annotation.Nonnull;

public class FluidItemRecipeBuilder extends ExNihiloFinishedRecipe<FluidItemRecipeBuilder> {
    private FluidItemRecipeBuilder() throws NullPointerException {
        //noinspection ConstantConditions
        super(FluidItemRecipe.getStaticSerializer().get());
    }

    @Nonnull
    public static FluidItemRecipeBuilder builder() {
        return new FluidItemRecipeBuilder();
    }

    @Nonnull
    public FluidItemRecipeBuilder fluidInBarrel(@Nonnull final Fluid fluid) {
        return this.addFluid(fluid);
    }

    @Nonnull
    public FluidItemRecipeBuilder input(@Nonnull final IItemProvider input) {
        return this.addInput(input);
    }

    @Nonnull
    public FluidItemRecipeBuilder input(@Nonnull final Ingredient input) {
        return this.addInput(input);
    }

    @Nonnull
    public FluidItemRecipeBuilder input(@Nonnull final Tags.IOptionalNamedTag<Item> tag) {
        return this.addInput(tag);
    }

    @Nonnull
    public FluidItemRecipeBuilder result(@Nonnull final IItemProvider output) {
        return this.addResult(output);
    }
}
