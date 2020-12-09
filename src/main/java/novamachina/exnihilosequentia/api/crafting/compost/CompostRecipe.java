package novamachina.exnihilosequentia.api.crafting.compost;

import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.List;

public class CompostRecipe extends SerializableRecipe {
    public static final IRecipeType<CompostRecipe> RECIPE_TYPE = IRecipeType
        .register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":compost");
    private static RegistryObject<RecipeSerializer<CompostRecipe>> serializer;
    private final Ingredient input;
    private final int amount;
    public CompostRecipe(ResourceLocation id, Ingredient input, int amount) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
    }

    public static RegistryObject<RecipeSerializer<CompostRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<RecipeSerializer<CompostRecipe>> serializer) {
        CompostRecipe.serializer = serializer;
    }

    public Ingredient getInput() {
        return input;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    protected RecipeSerializer getENSerializer() {
        return serializer.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    public List<ItemStack> getInputs() {
        return Arrays.asList(input.getMatchingStacks());
    }
}
