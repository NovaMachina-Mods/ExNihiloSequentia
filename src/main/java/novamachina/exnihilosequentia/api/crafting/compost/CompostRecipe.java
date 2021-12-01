package novamachina.exnihilosequentia.api.crafting.compost;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class CompostRecipe extends SerializableRecipe {
    public static final RecipeType<CompostRecipe> RECIPE_TYPE = RecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":compost");
    private static RegistryObject<IRecipeSerializer<CompostRecipe>> serializer;
    private int amount;
    private Ingredient input;

    public CompostRecipe(ResourceLocation id, Ingredient input, int amount) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
    }

    public static RegistryObject<IRecipeSerializer<CompostRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<IRecipeSerializer<CompostRecipe>> serializer) {
        CompostRecipe.serializer = serializer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Ingredient getInput() {
        return input;
    }

    public void setInput(Ingredient input) {
        this.input = input;
    }

    public List<ItemStack> getInputs() {
        return Arrays.asList(input.getItems());
    }

    @Nonnull
    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected IRecipeSerializer<CompostRecipe> getENSerializer() {
        return serializer.get();
    }
}
