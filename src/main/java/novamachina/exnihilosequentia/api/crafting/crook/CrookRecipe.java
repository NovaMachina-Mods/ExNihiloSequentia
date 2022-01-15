package novamachina.exnihilosequentia.api.crafting.crook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrookRecipe extends SerializableRecipe {
    @Nonnull public static final RecipeType<CrookRecipe> RECIPE_TYPE = RecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":crook");
    @Nullable private static RegistryObject<ExNihiloRecipeSerializer<CrookRecipe>> serializer;
    @Nonnull private Ingredient input;
    @Nonnull private final List<ItemStackWithChance> output;

    public CrookRecipe(@Nonnull final ResourceLocation id, @Nonnull final Ingredient input,
                       @Nonnull final List<ItemStackWithChance> output) {
        super(output.isEmpty() ? ItemStack.EMPTY : output.get(0).getStack(), RECIPE_TYPE, id);
        this.input = input;
        this.output = output;
    }

    @Nullable
    public static RegistryObject<ExNihiloRecipeSerializer<CrookRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(@Nonnull final RegistryObject<ExNihiloRecipeSerializer<CrookRecipe>> serializer) {
        CrookRecipe.serializer = serializer;
    }

    public void addOutput(@Nonnull final ItemStack item, final float chance) {
        output.add(new ItemStackWithChance(item, chance));
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

    @Nonnull
    public List<ItemStackWithChance> getOutput() {
        return output;
    }

    @Nonnull
    public List<ItemStack> getOutputsWithoutChance() {
        @Nonnull final List<ItemStack> returnList = new ArrayList<>();
        output.forEach(stack -> returnList.add(stack.getStack()));
        return returnList;
    }

    @Override
    @Nonnull
    public ItemStack getResultItem() {
        return output.isEmpty() ? ItemStack.EMPTY : output.get(0).getStack().copy();
    }

    @Override
    @Nullable
    protected ExNihiloRecipeSerializer<CrookRecipe> getENSerializer() {
        if (serializer == null)
            return null;
        return serializer.get();
    }
}
