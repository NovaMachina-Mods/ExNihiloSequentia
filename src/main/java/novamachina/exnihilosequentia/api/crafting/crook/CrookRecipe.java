package novamachina.exnihilosequentia.api.crafting.crook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class CrookRecipe extends SerializableRecipe {
    public static final IRecipeType<CrookRecipe> RECIPE_TYPE = IRecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":crook");
    private static RegistryObject<RecipeSerializer<CrookRecipe>> serializer;
    private Ingredient input;
    private final List<ItemStackWithChance> output;

    public CrookRecipe(ResourceLocation id, Ingredient input, List<ItemStackWithChance> output) {
        super(output.isEmpty() ? ItemStack.EMPTY : output.get(0).getStack(), RECIPE_TYPE, id);
        this.input = input;
        this.output = output;
    }

    public static RegistryObject<RecipeSerializer<CrookRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<RecipeSerializer<CrookRecipe>> serializer) {
        CrookRecipe.serializer = serializer;
    }

    public void addOutput(ItemStack item, float chance) {
        output.add(new ItemStackWithChance(item, chance));
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

    public List<ItemStackWithChance> getOutput() {
        return output;
    }

    public List<ItemStack> getOutputsWithoutChance() {
        List<ItemStack> returnList = new ArrayList<>();
        output.forEach(stack -> returnList.add(stack.getStack()));
        return returnList;
    }

    @Override
    public ItemStack getResultItem() {
        return output.isEmpty() ? ItemStack.EMPTY : output.get(0).getStack().copy();
    }

    @Override
    protected RecipeSerializer<CrookRecipe> getENSerializer() {
        return serializer.get();
    }
}
