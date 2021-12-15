package novamachina.exnihilosequentia.api.crafting.crucible;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.crafting.Ingredient;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucibleTypeEnum;

public class CrucibleRecipeBuilder extends ExNihiloFinishedRecipe<CrucibleRecipeBuilder> {
    private CrucibleRecipeBuilder() {
        super(CrucibleRecipe.getStaticSerializer().get());
    }

    public static CrucibleRecipeBuilder builder() {
        return new CrucibleRecipeBuilder();
    }

    public CrucibleRecipeBuilder amount(int amount) {
        return addWriter(jsonObj -> jsonObj.addProperty("amount", amount));
    }

    public CrucibleRecipeBuilder crucibleType(CrucibleTypeEnum type) {
        return addWriter(jsonObj -> jsonObj.addProperty("crucibleType", type.getName()));
    }

    public CrucibleRecipeBuilder fluidResult(Fluid fluidResult) {
        return this.addFluid("fluidResult", fluidResult);
    }

    public CrucibleRecipeBuilder input(Ingredient input) {
        return this.addInput(input);
    }
}
