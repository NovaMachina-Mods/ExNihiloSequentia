package novamachina.exnihilosequentia.api.crafting.heat;

import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class HeatRecipe extends SerializableRecipe {
    public static final IRecipeType<HeatRecipe> RECIPE_TYPE = IRecipeType.register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":heat");
    private static RegistryObject<RecipeSerializer<HeatRecipe>> serializer;
    private final Block input;
    private final int amount;

    public HeatRecipe(ResourceLocation id, Block input, int amount) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
    }

    public static RegistryObject<RecipeSerializer<HeatRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<RecipeSerializer<HeatRecipe>> serializer) {
        HeatRecipe.serializer = serializer;
    }

    public Block getInput() {
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
}
