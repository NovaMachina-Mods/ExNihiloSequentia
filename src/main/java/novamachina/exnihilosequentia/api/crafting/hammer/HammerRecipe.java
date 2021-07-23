package novamachina.exnihilosequentia.api.crafting.hammer;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fmllegacy.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class HammerRecipe extends SerializableRecipe {
    public static final HammerRecipe EMPTY = new HammerRecipe(new ResourceLocation("empty"), Ingredient.EMPTY, new ArrayList<>());
    public static final RecipeType<HammerRecipe> RECIPE_TYPE = RecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":hammer");
    private static RegistryObject<RecipeSerializer<HammerRecipe>> serializer;
    private Ingredient input;
    private final List<ItemStackWithChance> output;

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

    public void addOutput(ItemStack output) {
        this.output.add(new ItemStackWithChance(output, 1.0F));
    }

    public void addOutput(ItemStack output, float chance) {
        this.output.add(new ItemStackWithChance(output, chance));
    }

    public Ingredient getInput() {
        return input;
    }

    public void setInput(Ingredient input) {
        this.input = input;
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
        return ItemStack.EMPTY;
    }

    @Override
    protected RecipeSerializer<HammerRecipe> getENSerializer() {
        return serializer.get();
    }
}
