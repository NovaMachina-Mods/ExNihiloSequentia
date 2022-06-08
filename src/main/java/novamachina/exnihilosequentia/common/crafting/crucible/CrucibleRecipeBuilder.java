package novamachina.exnihilosequentia.common.crafting.crucible;

import javax.annotation.Nonnull;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.common.blockentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.common.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class CrucibleRecipeBuilder extends ExNihiloFinishedRecipe<CrucibleRecipeBuilder> {

  private CrucibleRecipeBuilder() throws NullPointerException {
    //noinspection ConstantConditions
    super(ExNihiloSerializers.CRUCIBLE_RECIPE_SERIALIZER.get());
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
