package novamachina.exnihilosequentia.common.crafting.heat;

import javax.annotation.Nonnull;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class HeatRecipeBuilder extends ExNihiloFinishedRecipe<HeatRecipeBuilder> {

  private HeatRecipeBuilder() throws NullPointerException {
    //noinspection ConstantConditions
    super(ExNihiloSerializers.HEAT_RECIPE_SERIALIZER.get());
  }

  public static HeatRecipeBuilder builder() {
    return new HeatRecipeBuilder();
  }

  @Nonnull
  public HeatRecipeBuilder amount(final int amount) {
    return addWriter(jsonObj -> jsonObj.addProperty("amount", amount));
  }

  @Nonnull
  public HeatRecipeBuilder input(@Nonnull final Ingredient input) {
    return this.addInput(input);
  }

  @Nonnull
  public HeatRecipeBuilder input(@Nonnull final Block block) {
    return this.addBlock(block);
  }

  @Nonnull
  public HeatRecipeBuilder properties(@Nonnull final StatePropertiesPredicate properties) {
    return this.addWriter(jsonObj -> jsonObj.add("state", properties.serializeToJson()));
  }
}
