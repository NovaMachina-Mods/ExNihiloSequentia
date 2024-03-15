package novamachina.exnihilosequentia.data.recipes;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.world.item.crafting.CrushingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.world.item.crafting.ItemStackWithChance;
import novamachina.novacore.data.recipes.RecipeBuilder;

public class CrushingRecipeBuilder extends RecipeBuilder<CrushingRecipe> {

  private final Ingredient input;
  private List<ItemStackWithChance> drops;

  protected CrushingRecipeBuilder(Ingredient input, ItemStackWithChance... drops) {
    super(EXNRecipeSerializers.CRUSHING_RECIPE_SERIALIZER.recipeSerializer());
    this.input = input;
    this.drops = Lists.newArrayList(drops);
  }

  @Nonnull
  public static CrushingRecipeBuilder crushing(ItemLike input, ItemStackWithChance... drops) {
    return crushing(Ingredient.of(input), drops);
  }

  private static CrushingRecipeBuilder crushing(Ingredient input, ItemStackWithChance... drops) {
    return new CrushingRecipeBuilder(input, drops);
  }

  @Override
  protected CrushingRecipe getRecipe(ResourceLocation resourceLocation) {
    return new CrushingRecipe(input, drops);
  }

  @Override
  protected void validate(ResourceLocation id) {
    Preconditions.checkNotNull(input, "Input cannot be null.");
    Preconditions.checkArgument(!drops.isEmpty(), "Recipe needs at least one drop.");
  }

  public CrushingRecipeBuilder addDrop(ItemStack stack) {
    return addDrop(stack, 1.0F);
  }

  public CrushingRecipeBuilder addDrop(ItemStack stack, float chance) {
    return addDrop(stack, 1, chance);
  }

  public CrushingRecipeBuilder addDrop(ItemStack stack, int count, float chance) {
    this.drops.add(ItemStackWithChance.of(stack, count, chance));
    return this;
  }
}
