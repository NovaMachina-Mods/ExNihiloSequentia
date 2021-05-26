package novamachina.exnihilosequentia.api.crafting.heat;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import java.util.HashMap;
import java.util.Map;

public class HeatRecipe extends SerializableRecipe {
    public static final IRecipeType<HeatRecipe> RECIPE_TYPE = IRecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":heat");
    private static RegistryObject<RecipeSerializer<HeatRecipe>> serializer;
    private int amount;
    private Block input;

    private boolean isLiquid = false;
    private Map<String, Integer> heatByLevel = new HashMap<>();

    public HeatRecipe(ResourceLocation id, Block input, int amount) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
        this.heatByLevel.put("*", amount);
    }

    public HeatRecipe(ResourceLocation id, Block input, int amount, boolean isLiquid, Map<String, Integer> heatByLevel){
        this(id, input, amount);
        this.isLiquid = isLiquid;
        this.heatByLevel = heatByLevel;
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

    public void setInput(Block input) {
        this.input = input;
    }

    public boolean isLiquid() {
        return this.isLiquid;
    }

    public void setIsLiquid(boolean isLiquid) {
        this.isLiquid = isLiquid;
    }

    public Map<String, Integer> getHeatByLevel() {
        return this.heatByLevel;
    }

    public void setHeatByLevel(Map<String, Integer> heatByLevel) {
        this.heatByLevel = heatByLevel;
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
