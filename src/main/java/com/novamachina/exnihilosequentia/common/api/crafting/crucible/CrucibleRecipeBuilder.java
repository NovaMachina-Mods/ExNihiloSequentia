package com.novamachina.exnihilosequentia.common.api.crafting.crucible;

import com.novamachina.exnihilosequentia.common.api.crafting.ExNihiloFinishedRecipe;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.crafting.Ingredient;

public class CrucibleRecipeBuilder extends ExNihiloFinishedRecipe<CrucibleRecipeBuilder> {
    private CrucibleRecipeBuilder() {
        super(CrucibleRecipe.SERIALIZER.get());
    }

    public static CrucibleRecipeBuilder builder() {
        return new CrucibleRecipeBuilder();
    }

    public CrucibleRecipeBuilder input(Ingredient input) {
        return this.addInput(input);
    }

    public CrucibleRecipeBuilder amount(int amount) {
        return addWriter(jsonObj -> jsonObj.addProperty("amount", amount));
    }

    public CrucibleRecipeBuilder fluidResult(Fluid fluidResult) {
        return this.addFluid("fluidResult", fluidResult);
    }

    public CrucibleRecipeBuilder crucibleType(CrucilbeTypeEnum type) {
        return addWriter(jsonObj -> jsonObj.addProperty("crucibleType", type.getName()));
    }
}
