package novamachina.exnihilosequentia.api.crafting.fluiditem;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import java.util.Arrays;
import java.util.List;

public class FluidItemRecipe extends SerializableRecipe {
    public static final IRecipeType<FluidItemRecipe> RECIPE_TYPE = IRecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_item");
    private static RegistryObject<RecipeSerializer<FluidItemRecipe>> serializer;
    private FluidStack fluid;
    private Ingredient input;
    private ItemStack output;

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

    public FluidStack getFluidInBarrel() {
        return fluid;
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

    @Override
    public ItemStack getResultItem() {
        return output;
    }

    public void setFluid(FluidStack fluidInBarrel) {
        this.fluid = fluidInBarrel;
    }

    public void setOutput(ItemStack output) {
        this.output = output;
    }

    public boolean validInputs(Fluid fluid, Item input) {
        return this.fluid.getFluid().isSame(fluid) && this.input.test(new ItemStack(input));
    }

    @Override
    protected RecipeSerializer<FluidItemRecipe> getENSerializer() {
        return serializer.get();
    }
}
