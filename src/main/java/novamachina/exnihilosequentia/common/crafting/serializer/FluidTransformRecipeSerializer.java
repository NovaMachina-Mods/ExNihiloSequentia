package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidTransformRecipeSerializer extends ExNihiloRecipeSerializer<FluidTransformRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.BARREL_OAK.get());
    }

    @Override
    @Nonnull
    public FluidTransformRecipe fromNetwork(@Nonnull final ResourceLocation recipeId,
                                            @Nonnull final FriendlyByteBuf buffer) {
        @Nonnull final FluidStack fluidInTank = FluidStack.readFromPacket(buffer);
        @Nonnull final Ingredient catalyst = Ingredient.fromNetwork(buffer);
        @Nonnull final FluidStack result = FluidStack.readFromPacket(buffer);
        return new FluidTransformRecipe(recipeId, fluidInTank, catalyst, result);
    }

    @Override
    public void toNetwork(@Nonnull final FriendlyByteBuf buffer, @Nonnull final FluidTransformRecipe recipe) {
        recipe.getFluidInTank().writeToPacket(buffer);
        recipe.getCatalyst().toNetwork(buffer);
        recipe.getResult().writeToPacket(buffer);
    }

    @Override
    @Nonnull
    protected FluidTransformRecipe readFromJson(@Nonnull final ResourceLocation recipeId,
                                                @Nonnull final JsonObject json) {
        @Nullable final FluidStack fluid = FluidStackUtils.jsonDeserializeFluidStack(
                json.get("fluidInTank").getAsJsonObject());
        @Nonnull final Ingredient catalyst = Ingredient.fromJson(json.get("catalyst"));
        @Nullable final FluidStack result = FluidStackUtils.jsonDeserializeFluidStack(
                json.get("result").getAsJsonObject());
        return new FluidTransformRecipe(recipeId, fluid, catalyst, result);
    }
}
