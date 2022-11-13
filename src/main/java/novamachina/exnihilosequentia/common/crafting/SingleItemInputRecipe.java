package novamachina.exnihilosequentia.common.crafting;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public abstract class SingleItemInputRecipe extends SerializableRecipe {
    @Nonnull
    protected Ingredient input;

    protected SingleItemInputRecipe(@Nullable ItemStack outputDummy, @Nonnull Ingredient input, @Nonnull RecipeType<?> type, @Nonnull ResourceLocation id) {
        super(outputDummy, type, id);
        this.input = input;
    }

    public final void setInput(@Nonnull Ingredient input) {
        this.input = input;
    }

    @Nonnull
    public final Ingredient getInput() {
        return input;
    }

    @Nonnull
    public final List<ItemStack> getInputs() {
        return Arrays.asList(input.getItems());
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.EMPTY, input);
    }
}
