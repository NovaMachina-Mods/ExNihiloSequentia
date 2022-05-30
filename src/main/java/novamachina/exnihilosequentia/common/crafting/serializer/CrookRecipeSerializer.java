package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.CrookBaseItem;

public class CrookRecipeSerializer extends ExNihiloRecipeSerializer<CrookRecipe> {

  @Override
  @Nullable
  public ItemStack getIcon() {
    @Nullable final RegistryObject<CrookBaseItem> woodCrook = ExNihiloItems.CROOK_WOOD;
    if (woodCrook != null) {
      return new ItemStack(woodCrook.get());
    }
    return null;
  }

  @Override
  @Nonnull
  public CrookRecipe fromNetwork(@Nonnull final ResourceLocation recipeId,
      @Nonnull final FriendlyByteBuf buffer) {
    final int outputCount = buffer.readInt();
    @Nonnull final List<ItemStackWithChance> output = new ArrayList<>(outputCount);
    for (int i = 0; i < outputCount; i++) {
      output.add(ItemStackWithChance.read(buffer));
    }
    @Nonnull final Ingredient input = Ingredient.fromNetwork(buffer);
    return new CrookRecipe(recipeId, input, output);
  }

  @Override
  public void toNetwork(@Nonnull final FriendlyByteBuf buffer, @Nonnull final CrookRecipe recipe) {
    buffer.writeInt(recipe.getOutput().size());
    for (@Nonnull final ItemStackWithChance stack : recipe.getOutput()) {
      stack.write(buffer);
    }
    recipe.getInput().toNetwork(buffer);
  }

  @Override
  @Nonnull
  protected CrookRecipe readFromJson(@Nonnull final ResourceLocation recipeId,
      @Nonnull final JsonObject json) {
    @Nonnull final Ingredient input = Ingredient.fromJson(json.get("input"));
    @Nonnull final JsonArray results = json.getAsJsonArray("results");
    @Nonnull final List<ItemStackWithChance> output = new ArrayList<>(results.size());
    for (int i = 0; i < results.size(); i++) {
      output.add(ItemStackWithChance.deserialize(results.get(i)));
    }
    return new CrookRecipe(recipeId, input, output);
  }
}
