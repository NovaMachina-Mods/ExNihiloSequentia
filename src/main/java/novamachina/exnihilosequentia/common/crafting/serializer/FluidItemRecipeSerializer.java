package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;
import novamachina.novacore.world.item.crafting.NovaRecipeSerializer;

public class FluidItemRecipeSerializer extends NovaRecipeSerializer<FluidItemRecipe> {

  @Override
  @Nonnull
  public FluidItemRecipe fromNetwork(
      @Nonnull final ResourceLocation recipeId, @Nonnull final FriendlyByteBuf buffer) {
    @Nonnull final FluidStack fluid = FluidStack.readFromPacket(buffer);
    @Nonnull final Ingredient input = Ingredient.fromNetwork(buffer);
    @Nonnull final ItemStack output = buffer.readItem();
    return new FluidItemRecipe(recipeId, fluid, input, output);
  }

  @Override
  public void toNetwork(
      @Nonnull final FriendlyByteBuf buffer, @Nonnull final FluidItemRecipe recipe) {
    recipe.getFluidInBarrel().writeToPacket(buffer);
    recipe.getInput().toNetwork(buffer);
    buffer.writeItem(recipe.getResultItem());
  }

  @Override
  @Nonnull
  protected FluidItemRecipe readFromJson(
      @Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json) {
    @Nullable
    final FluidStack fluid =
        FluidStackUtils.jsonDeserializeFluidStack(json.get("fluid").getAsJsonObject());
    @Nonnull final Ingredient input = Ingredient.fromJson(json.get("input"));
    @Nonnull final ItemStack result = readOutput(json.get("result"));
    return new FluidItemRecipe(recipeId, fluid, input, result);
  }
}
