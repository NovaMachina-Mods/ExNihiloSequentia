package novamachina.exnihilosequentia.api.crafting.heat;

import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class HeatRecipe extends SerializableRecipe {
    public static IRecipeType<HeatRecipe> TYPE = IRecipeType.register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":heat");
    public static RegistryObject<RecipeSerializer<HeatRecipe>> SERIALIZER;
    private final Block input;
    private final int amount;

    public HeatRecipe(ResourceLocation id, Block input, int amount) {
        super(null, TYPE, id);
        this.input = input;
        this.amount = amount;
    }

    public Block getInput() {
        return input;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    protected RecipeSerializer getENSerializer() {
        return SERIALIZER.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
}
