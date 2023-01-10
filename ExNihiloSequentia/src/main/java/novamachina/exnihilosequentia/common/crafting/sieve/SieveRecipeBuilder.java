package novamachina.exnihilosequentia.common.crafting.sieve;

import com.google.gson.JsonArray;
import javax.annotation.Nonnull;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.common.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class SieveRecipeBuilder extends ExNihiloFinishedRecipe<SieveRecipeBuilder> {

  @Nonnull private final JsonArray meshArray = new JsonArray();

  private SieveRecipeBuilder() throws NullPointerException {
    //noinspection ConstantConditions
    super(ExNihiloSerializers.SIEVE_RECIPE_SERIALIZER.get());

    addWriter(jsonObject -> jsonObject.add("rolls", meshArray));
  }

  @Nonnull
  public static SieveRecipeBuilder builder() {
    return new SieveRecipeBuilder();
  }

  @Nonnull
  public SieveRecipeBuilder addRoll(@Nonnull final MeshWithChance mesh) {
    meshArray.add(mesh.serialize());
    return this;
  }

  @Nonnull
  public SieveRecipeBuilder drop(@Nonnull final ItemLike drop) {
    return this.addResult(drop);
  }

  @Nonnull
  public SieveRecipeBuilder input(@Nonnull final Ingredient input) {
    return this.addInput(input);
  }

  @Nonnull
  public SieveRecipeBuilder isWaterlogged() {
    return this.addBoolean("waterlogged", true);
  }
}
