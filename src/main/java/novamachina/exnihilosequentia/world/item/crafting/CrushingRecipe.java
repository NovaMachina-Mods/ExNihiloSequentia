package novamachina.exnihilosequentia.world.item.crafting;

import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.world.item.EXNItems;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CrushingRecipe extends DropListRecipe {

  public static final CrushingRecipe EMPTY = new CrushingRecipe(Ingredient.EMPTY, List.of());

  public CrushingRecipe(Ingredient input, List<ItemStackWithChance> drops) {
    super(input, drops);
  }

  @Override
  @NonNull
  public ItemStack getToastSymbol() {
    return EXNItems.HAMMER_DIAMOND.itemStack();
  }

  @Override
  @NonNull
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.CRUSHING_RECIPE_SERIALIZER.recipeSerializer();
  }

  @Override
  @NonNull
  public RecipeType<?> getType() {
    return EXNRecipeTypes.CRUSHING;
  }
}
