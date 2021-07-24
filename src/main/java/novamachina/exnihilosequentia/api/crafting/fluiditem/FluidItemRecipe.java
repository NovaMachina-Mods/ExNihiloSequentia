package novamachina.exnihilosequentia.api.crafting.fluiditem;

import java.util.Arrays;
import java.util.List;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fmllegacy.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class FluidItemRecipe extends SerializableRecipe {
    public static final RecipeType<FluidItemRecipe> RECIPE_TYPE = RecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_item");
    private static RegistryObject<IRecipeSerializer<FluidItemRecipe>> serializer;
    private FluidStack fluid;
    private Ingredient input;
    private ItemStack output;

    public FluidItemRecipe(ResourceLocation id, FluidStack fluid, Ingredient input, ItemStack output) {
        super(output, RECIPE_TYPE, id);
        this.fluid = fluid;
        this.input = input;
        this.output = output;
    }

    public static RegistryObject<IRecipeSerializer<FluidItemRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<IRecipeSerializer<FluidItemRecipe>> serializer) {
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
    protected IRecipeSerializer<FluidItemRecipe> getENSerializer() {
        return serializer.get();
    }
}
