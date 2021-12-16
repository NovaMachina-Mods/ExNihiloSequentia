package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrucibleRecipeSerializer extends RecipeSerializer<CrucibleRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.CRUCIBLE_FIRED.get());
    }

    @Override
    @Nonnull
    public CrucibleRecipe fromNetwork(@Nonnull final ResourceLocation recipeId, @Nonnull final PacketBuffer buffer) {
        @Nonnull final Ingredient input = Ingredient.fromNetwork(buffer);
        final int amount = buffer.readInt();
        @Nonnull final FluidStack fluid = FluidStack.readFromPacket(buffer);
        @Nonnull final CrucibleTypeEnum type = buffer.readEnum(CrucibleTypeEnum.class);
        return new CrucibleRecipe(recipeId, input, amount, fluid, type);
    }

    @Override
    public void toNetwork(@Nonnull final PacketBuffer buffer, @Nonnull final CrucibleRecipe recipe) {
        recipe.getInput().toNetwork(buffer);
        buffer.writeInt(recipe.getAmount());
        recipe.getResultFluid().writeToPacket(buffer);
        buffer.writeEnum(recipe.getCrucibleType());
    }

    @Override
    @Nonnull
    protected CrucibleRecipe readFromJson(@Nonnull final ResourceLocation recipeId, @Nonnull final JsonObject json) {
        @Nonnull final Ingredient input = Ingredient.fromJson(json.get("input"));
        final int amount = json.get("amount").getAsInt();
        @Nullable final FluidStack fluid = FluidStackUtils.jsonDeserializeFluidStack(
                json.get("fluidResult").getAsJsonObject());
        @Nullable final CrucibleTypeEnum typeEnum = CrucibleTypeEnum.getTypeByName(
                json.get("crucibleType").getAsString());
        return new CrucibleRecipe(recipeId, input, amount, fluid, typeEnum);
    }
}
