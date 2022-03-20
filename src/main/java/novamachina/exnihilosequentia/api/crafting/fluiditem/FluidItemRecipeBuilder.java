package novamachina.exnihilosequentia.api.crafting.fluiditem;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

import javax.annotation.Nonnull;

public class FluidItemRecipeBuilder extends ExNihiloFinishedRecipe<FluidItemRecipeBuilder> {
    private FluidItemRecipeBuilder() throws NullPointerException {
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
    public FluidItemRecipeBuilder input(@Nonnull final ItemLike input) {
        return this.addInput(input);
    }

    @Nonnull
    public FluidItemRecipeBuilder input(@Nonnull final Ingredient input) {
        return this.addInput(input);
    }

    @Nonnull
    public FluidItemRecipeBuilder input(@Nonnull final TagKey<Item> tag) {
        return this.addInput(tag);
    }

    @Nonnull
    public FluidItemRecipeBuilder result(@Nonnull final ItemLike output) {
        return this.addResult(output);
    }
}
