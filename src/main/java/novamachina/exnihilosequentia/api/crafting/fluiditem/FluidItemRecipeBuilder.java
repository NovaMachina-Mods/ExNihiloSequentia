package novamachina.exnihilosequentia.api.crafting.fluiditem;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.Tags;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

public class FluidItemRecipeBuilder extends ExNihiloFinishedRecipe<FluidItemRecipeBuilder> {
    private FluidItemRecipeBuilder() {
        super(FluidItemRecipe.getStaticSerializer().get());
    }

    public static FluidItemRecipeBuilder builder() {
        return new FluidItemRecipeBuilder();
    }

    public FluidItemRecipeBuilder fluidInBarrel(Fluid fluid) {
        return this.addFluid(fluid);
    }

    public FluidItemRecipeBuilder input(ItemLike input) {
        return this.addInput(input);
    }

    public FluidItemRecipeBuilder input(Ingredient input) {
        return this.addInput(input);
    }

    public FluidItemRecipeBuilder input(Tags.IOptionalNamedTag<Item> tag) {
        return this.addInput(tag);
    }

    public FluidItemRecipeBuilder result(ItemLike output) {
        return this.addResult(output);
    }
}
