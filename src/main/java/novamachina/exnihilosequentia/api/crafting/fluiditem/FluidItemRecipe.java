package novamachina.exnihilosequentia.api.crafting.fluiditem;

import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.List;

public class FluidItemRecipe extends SerializableRecipe {
    public static final IRecipeType<FluidItemRecipe> RECIPE_TYPE = IRecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_item");
    private static RegistryObject<RecipeSerializer<FluidItemRecipe>> serializer;
    private final FluidStack fluid;
    private final Ingredient input;
    private final ItemStack output;

    public FluidItemRecipe(ResourceLocation id, FluidStack fluid, Ingredient input, ItemStack output) {
        super(output, RECIPE_TYPE, id);
        this.fluid = fluid;
        this.input = input;
        this.output = output;
    }

    public static RegistryObject<RecipeSerializer<FluidItemRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<RecipeSerializer<FluidItemRecipe>> serializer) {
        FluidItemRecipe.serializer = serializer;
    }

    @Override
    protected RecipeSerializer getENSerializer() {
        return serializer.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    public FluidStack getFluidInBarrel() {
        return fluid;
    }

    public Ingredient getInput() {
        return input;
    }

    public List<ItemStack> getInputs() {
        return Arrays.asList(input.getMatchingStacks());
    }

    public boolean validInputs(Fluid fluid, Item input) {
        return this.fluid.getFluid().isEquivalentTo(fluid) && this.input.test(new ItemStack(input));
    }
}
