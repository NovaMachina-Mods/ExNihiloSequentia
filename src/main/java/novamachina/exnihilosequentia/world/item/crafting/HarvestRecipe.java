package novamachina.exnihilosequentia.world.item.crafting;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.world.item.EXNItems;
import org.checkerframework.checker.nullness.qual.NonNull;

public class HarvestRecipe extends DropListRecipe {
  public HarvestRecipe(ResourceLocation id, Ingredient input, ItemStackWithChance... drops) {
    super(id, input, drops);
  }

  @Override
  @NonNull
  public ItemStack getToastSymbol() {
    return EXNItems.CROOK_WOOD.itemStack();
  }

  @Override
  @NonNull
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.HARVEST_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<?> getType() {
    return EXNRecipeTypes.HARVEST;
  }
}
