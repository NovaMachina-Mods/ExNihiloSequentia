package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.blockentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

public class CrucibleRecipeSerializer extends ExNihiloRecipeSerializer<CrucibleRecipe> {

  @Override
  public ItemStack getIcon() {
    return new ItemStack(ExNihiloBlocks.CRUCIBLE_FIRED.get());
  }

  @Override
  @Nonnull
  public CrucibleRecipe fromNetwork(@Nonnull final ResourceLocation recipeId,
      @Nonnull final FriendlyByteBuf buffer) {
    @Nonnull final Ingredient input = Ingredient.fromNetwork(buffer);
    final int amount = buffer.readInt();
    @Nonnull final FluidStack fluid = FluidStack.readFromPacket(buffer);
    @Nonnull final CrucibleTypeEnum type = buffer.readEnum(CrucibleTypeEnum.class);
    return new CrucibleRecipe(recipeId, input, amount, fluid, type);
  }

  @Override
  public void toNetwork(@Nonnull final FriendlyByteBuf buffer,
      @Nonnull final CrucibleRecipe recipe) {
    recipe.getInput().toNetwork(buffer);
    buffer.writeInt(recipe.getAmount());
    recipe.getResultFluid().writeToPacket(buffer);
    buffer.writeEnum(recipe.getCrucibleType());
  }

  @Override
  @Nonnull
  protected CrucibleRecipe readFromJson(@Nonnull final ResourceLocation recipeId,
      @Nonnull final JsonObject json) {
    @Nonnull final Ingredient input = Ingredient.fromJson(json.get("input"));
    final int amount = json.get("amount").getAsInt();
    @Nullable final FluidStack fluid = FluidStackUtils.jsonDeserializeFluidStack(
        json.get("fluidResult").getAsJsonObject());
    @Nullable final CrucibleTypeEnum typeEnum = CrucibleTypeEnum.getTypeByName(
        json.get("crucibleType").getAsString());
    return new CrucibleRecipe(recipeId, input, amount, fluid, typeEnum);
  }
}
