package novamachina.exnihilosequentia.api.crafting.compost;

import java.util.Arrays;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CompostRecipe extends SerializableRecipe {
    @Nonnull public static final IRecipeType<CompostRecipe> RECIPE_TYPE = IRecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":compost");
    @Nullable private static RegistryObject<RecipeSerializer<CompostRecipe>> serializer;
    private int amount;
    @Nonnull private Ingredient input;

    public CompostRecipe(@Nonnull final ResourceLocation id, @Nonnull final Ingredient input, final int amount) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
    }

    public static RegistryObject<RecipeSerializer<CompostRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(@Nonnull final RegistryObject<RecipeSerializer<CompostRecipe>> serializer) {
        CompostRecipe.serializer = serializer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    @Nonnull
    public Ingredient getInput() {
        return input;
    }

    public void setInput(@Nonnull final Ingredient input) {
        this.input = input;
    }

    @Nonnull
    public List<ItemStack> getInputs() {
        return Arrays.asList(input.getItems());
    }

    @Override
    @Nonnull
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    @Nullable
    protected RecipeSerializer<CompostRecipe> getENSerializer() {
        if (serializer != null)
            return serializer.get();
        return null;
    }
}
