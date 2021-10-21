package novamachina.exnihilosequentia.api.crafting.heat;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class HeatRecipe extends SerializableRecipe {
    public static final IRecipeType<HeatRecipe> RECIPE_TYPE = IRecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":heat");
    private static RegistryObject<RecipeSerializer<HeatRecipe>> serializer;
    private int amount;
    private Block input;
    private StatePropertiesPredicate properties;

    public HeatRecipe(ResourceLocation id, Block input, int amount) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
        this.properties = null;
    }

    public HeatRecipe(ResourceLocation id, Block input, int amount, StatePropertiesPredicate properties) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
        this.properties = properties;
    }

    public static RegistryObject<RecipeSerializer<HeatRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<RecipeSerializer<HeatRecipe>> serializer) {
        HeatRecipe.serializer = serializer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Block getInput() {
        return input;
    }

    public StatePropertiesPredicate getProperties() {
        return this.properties;
    }

    public void setInput(Block input) {
        this.input = input;
    }

    public void setProperties(StatePropertiesPredicate properties) {
        this.properties = properties;
    }

    public boolean isMatch(BlockState state) {
        return state.getBlock().getRegistryName().equals(this.input.getRegistryName())
                && (this.properties == null || this.properties.matches(state));
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected RecipeSerializer<HeatRecipe> getENSerializer() {
        return serializer.get();
    }
}
