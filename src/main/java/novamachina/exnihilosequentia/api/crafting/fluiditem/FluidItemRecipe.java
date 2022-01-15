package novamachina.exnihilosequentia.api.crafting.fluiditem;

import java.util.Arrays;
import java.util.List;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidItemRecipe extends SerializableRecipe {
    @Nonnull public static final IRecipeType<FluidItemRecipe> RECIPE_TYPE = IRecipeType.register(
            ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_item");
    @Nullable private static RegistryObject<RecipeSerializer<FluidItemRecipe>> serializer;
    @Nonnull private FluidStack fluid;
    @Nonnull private Ingredient input;
    @Nonnull private ItemStack output;

    public FluidItemRecipe(@Nonnull final ResourceLocation id, @Nonnull final FluidStack fluid,
                           @Nonnull final Ingredient input, @Nonnull final ItemStack output) {
        super(output, RECIPE_TYPE, id);
        this.fluid = fluid;
        this.input = input;
        this.output = output;
    }

    @Nullable
    public static RegistryObject<RecipeSerializer<FluidItemRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(@Nonnull final RegistryObject<RecipeSerializer<FluidItemRecipe>> serializer) {
        FluidItemRecipe.serializer = serializer;
    }

    @Nonnull
    public FluidStack getFluidInBarrel() {
        return fluid;
    }

    @Nonnull
    public Ingredient getInput() {
        return input;
    }

    public void setInput(@Nonnull final Ingredient input) {
        this.input = input;
    }

    @Nonnull
    public List<ItemStack> getInputs() {
        return Arrays.asList(input.getItems());
    }

    @Override
    @Nonnull
    public ItemStack getResultItem() {
        return output;
    }

    public void setFluid(@Nonnull final FluidStack fluidInBarrel) {
        this.fluid = fluidInBarrel;
    }

    public void setOutput(@Nonnull final ItemStack output) {
        this.output = output;
    }

    public boolean validInputs(@Nonnull final Fluid fluid, @Nonnull final Item input) {
        return this.fluid.getFluid().isSame(fluid) && this.input.test(new ItemStack(input));
    }

    @Override
    @Nullable
    protected RecipeSerializer<FluidItemRecipe> getENSerializer() {
        if (serializer == null)
            return null;
        return serializer.get();
    }
}
