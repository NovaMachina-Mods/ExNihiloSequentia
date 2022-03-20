package novamachina.exnihilosequentia.api.crafting.crucible;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucibleTypeEnum;

import javax.annotation.Nonnull;

public class CrucibleRecipeBuilder extends ExNihiloFinishedRecipe<CrucibleRecipeBuilder> {
    private CrucibleRecipeBuilder() throws NullPointerException {
        super(CrucibleRecipe.getStaticSerializer().get());
    }

    @Nonnull
    public static CrucibleRecipeBuilder builder() {
        return new CrucibleRecipeBuilder();
    }

    @Nonnull
    public CrucibleRecipeBuilder amount(final int amount) {
        return addWriter(jsonObj -> jsonObj.addProperty("amount", amount));
    }

    @Nonnull
    public CrucibleRecipeBuilder crucibleType(@Nonnull final CrucibleTypeEnum type) {
        return addWriter(jsonObj -> jsonObj.addProperty("crucibleType", type.getName()));
    }

    @Nonnull
    public CrucibleRecipeBuilder fluidResult(@Nonnull final Fluid fluidResult) {
        return this.addFluid("fluidResult", fluidResult);
    }

    @Nonnull
    public CrucibleRecipeBuilder input(@Nonnull final Ingredient input) {
        return this.addInput(input);
    }
}
