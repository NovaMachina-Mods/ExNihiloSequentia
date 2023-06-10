package novamachina.exnihilosequentia.common.crafting.fluiditem;

import javax.annotation.Nonnull;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.common.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;

public class FluidItemRecipeBuilder extends ExNihiloFinishedRecipe<FluidItemRecipeBuilder> {

  private FluidItemRecipeBuilder() throws NullPointerException {
    //noinspection ConstantConditions
    super(EXNRecipeSerializers.FLUID_ITEM_RECIPE_SERIALIZER);
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
