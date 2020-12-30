package novamachina.exnihilosequentia.api.crafting.hammer;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import java.util.ArrayList;
import java.util.List;

public class HammerRecipe extends SerializableRecipe {
    public static final IRecipeType<HammerRecipe> RECIPE_TYPE = IRecipeType
        .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":hammer");
    public static final HammerRecipe EMPTY = new HammerRecipe(new ResourceLocation("empty"), Ingredient.EMPTY, new ArrayList<>());
    private static RegistryObject<RecipeSerializer<HammerRecipe>> serializer;
    private Ingredient input;
    private List<ItemStackWithChance> output;

    public HammerRecipe(ResourceLocation id, Ingredient input, List<ItemStackWithChance> output) {
        super(ItemStack.EMPTY, RECIPE_TYPE, id);
        this.input = input;
        this.output = output;
    }

    public static RegistryObject<RecipeSerializer<HammerRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<RecipeSerializer<HammerRecipe>> serializer) {
        HammerRecipe.serializer = serializer;
    }

    public Ingredient getInput() {
        return input;
    }

    public void setInput(Ingredient input) {
        this.input = input;
    }

    @Override
    protected RecipeSerializer<HammerRecipe> getENSerializer() {
        return serializer.get();
    }

    public List<ItemStackWithChance> getOutput() {
        return output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    public void addOutput(ItemStack output) {
        this.output.add(new ItemStackWithChance(output, 1.0F));
    }

    public void addOutput(ItemStack output, float chance) {
        this.output.add(new ItemStackWithChance(output, chance));
    }

    public List<ItemStack> getOutputsWithoutChance() {
        List<ItemStack> returnList = new ArrayList<>();
        output.forEach(stack -> returnList.add(stack.getStack()));
        return returnList;
    }
}
