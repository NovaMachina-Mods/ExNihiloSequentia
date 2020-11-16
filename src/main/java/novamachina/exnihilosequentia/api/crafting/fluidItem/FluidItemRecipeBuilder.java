package novamachina.exnihilosequentia.api.crafting.fluidItem;

import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;

public class FluidItemRecipeBuilder extends ExNihiloFinishedRecipe<FluidItemRecipeBuilder> {
    private FluidItemRecipeBuilder() {
        super(FluidItemRecipe.SERIALIZER.get());
    }

    public static FluidItemRecipeBuilder builder() {
        return new FluidItemRecipeBuilder();
    }

    public FluidItemRecipeBuilder fluidInBarrel(Fluid fluid) {
        return this.addFluid(fluid);
    }

    public FluidItemRecipeBuilder input(IItemProvider input) {
        return this.addInput(input);
    }

    public FluidItemRecipeBuilder input(Ingredient input) {
        return this.addInput(input);
    }

    public FluidItemRecipeBuilder result(IItemProvider output) {
        return this.addResult(output);
    }

    public FluidItemRecipeBuilder input(Tags.IOptionalNamedTag<Item> tag) {
        return this.addInput(tag);
    }
}
