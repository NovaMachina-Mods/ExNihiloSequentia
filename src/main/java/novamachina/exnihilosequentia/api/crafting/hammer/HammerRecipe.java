package novamachina.exnihilosequentia.api.crafting.hammer;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class HammerRecipe extends SerializableRecipe {
    public static final IRecipeType<HammerRecipe> RECIPE_TYPE = IRecipeType
        .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":hammer");
    public static final HammerRecipe EMPTY = new HammerRecipe(new ResourceLocation("empty"), ItemStack.EMPTY, ItemStack.EMPTY);
    private static RegistryObject<RecipeSerializer<HammerRecipe>> serializer;
    private final ItemStack input;
    private final ItemStack output;

    public HammerRecipe(ResourceLocation id, ItemStack input, ItemStack output) {
        super(output, RECIPE_TYPE, id);
        this.input = input;
        this.output = output;
    }

    public static RegistryObject<RecipeSerializer<HammerRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<RecipeSerializer<HammerRecipe>> serializer) {
        HammerRecipe.serializer = serializer;
    }

    public ItemStack getInput() {
        return input.copy();
    }

    @Override
    protected RecipeSerializer getENSerializer() {
        return serializer.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output.copy();
    }
}
