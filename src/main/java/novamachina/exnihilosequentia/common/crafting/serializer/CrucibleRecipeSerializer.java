package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

import javax.annotation.Nonnull;

public class CrucibleRecipeSerializer extends IRecipeSerializer<CrucibleRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.FIRED_CRUCIBLE.get());
    }

    @Override
    public CrucibleRecipe fromNetwork(@Nonnull ResourceLocation recipeId, @Nonnull FriendlyByteBuf buffer) {
        Ingredient input = Ingredient.fromNetwork(buffer);
        int amount = buffer.readInt();
        FluidStack fluid = FluidStack.readFromPacket(buffer);
        CrucibleTypeEnum type = buffer.readEnum(CrucibleTypeEnum.class);
        return new CrucibleRecipe(recipeId, input, amount, fluid, type);
    }

    @Override
    public void toNetwork(@Nonnull FriendlyByteBuf buffer, CrucibleRecipe recipe) {
        recipe.getInput().toNetwork(buffer);
        buffer.writeInt(recipe.getAmount());
        recipe.getResultFluid().writeToPacket(buffer);
        buffer.writeEnum(recipe.getCrucibleType());
    }

    @Override
    protected CrucibleRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient input = Ingredient.fromJson(json.get("input"));
        int amount = json.get("amount").getAsInt();
        FluidStack fluid = FluidStackUtils.jsonDeserializeFluidStack(json.get("fluidResult").getAsJsonObject());
        CrucibleTypeEnum typeEnum = CrucibleTypeEnum.getTypeByName(json.get("crucibleType").getAsString());
        return new CrucibleRecipe(recipeId, input, amount, fluid, typeEnum);
    }
}
