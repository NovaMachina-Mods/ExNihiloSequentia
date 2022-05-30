package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

public class FluidOnTopRecipeSerializer extends ExNihiloRecipeSerializer<FluidOnTopRecipe> {

  @Override
  public ItemStack getIcon() {
    return new ItemStack(ExNihiloBlocks.BARREL_OAK.get());
  }

  @Override
  @Nonnull
  public FluidOnTopRecipe fromNetwork(@Nonnull final ResourceLocation recipeId,
      @Nonnull final FriendlyByteBuf buffer) {
    @Nonnull final FluidStack fluidInTank = FluidStack.readFromPacket(buffer);
    @Nonnull final FluidStack fluidOnTop = FluidStack.readFromPacket(buffer);
    @Nonnull final ItemStack result = buffer.readItem();
    return new FluidOnTopRecipe(recipeId, fluidInTank, fluidOnTop, result);
  }

  @Override
  public void toNetwork(@Nonnull final FriendlyByteBuf buffer,
      @Nonnull final FluidOnTopRecipe recipe) {
    recipe.getFluidInTank().writeToPacket(buffer);
    recipe.getFluidOnTop().writeToPacket(buffer);
    buffer.writeItem(recipe.getResultItem());
  }

  @Override
  @Nonnull
  protected FluidOnTopRecipe readFromJson(@Nonnull final ResourceLocation recipeId,
      @Nonnull final JsonObject json) {
    @Nullable final FluidStack fluidInTank = FluidStackUtils.jsonDeserializeFluidStack(
        json.get("fluidInTank").getAsJsonObject());
    @Nullable final FluidStack fluidOnTop = FluidStackUtils.jsonDeserializeFluidStack(
        json.get("fluidOnTop").getAsJsonObject());
    @Nonnull final ItemStack result = readOutput(json.get("result"));
    return new FluidOnTopRecipe(recipeId, fluidInTank, fluidOnTop, result);
  }
}
