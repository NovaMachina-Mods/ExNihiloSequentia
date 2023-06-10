package novamachina.exnihilosequentia.common.crafting.hammer;

import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.common.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.common.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;

public class HammerRecipeBuilder extends ExNihiloFinishedRecipe<HammerRecipeBuilder> {

  private HammerRecipeBuilder() throws NullPointerException {
    //noinspection ConstantConditions
    super(EXNRecipeSerializers.HAMMER_RECIPE_SERIALIZER);
    setMultipleResults(Integer.MAX_VALUE);
  }

  @Nonnull
  public static HammerRecipeBuilder builder() {
    return new HammerRecipeBuilder();
  }

  @Nonnull
  public HammerRecipeBuilder addDrop(@Nonnull final ItemLike drop) {
    return addDrop(drop, 1, 1.0F);
  }

  @Nonnull
  public HammerRecipeBuilder addDrop(@Nonnull final ItemLike drop, final int count) {
    return addDrop(drop, count, 1.0F);
  }

  @Nonnull
  public HammerRecipeBuilder addDrop(@Nonnull final ItemLike drop, final float chance) {
    return addDrop(drop, 1, chance);
  }

  @Nonnull
  public HammerRecipeBuilder addDrop(
      @Nonnull final ItemLike drop, final int count, final float chance) {
    return this.addResult(new ItemStackWithChance(new ItemStack(drop, count), chance));
  }

  @Nonnull
  public HammerRecipeBuilder input(@Nonnull final Ingredient input) {
    return this.addInput(input);
  }

  @Nonnull
  public HammerRecipeBuilder input(@Nonnull final ItemLike input) {
    return this.input(Ingredient.of(input));
  }
}
