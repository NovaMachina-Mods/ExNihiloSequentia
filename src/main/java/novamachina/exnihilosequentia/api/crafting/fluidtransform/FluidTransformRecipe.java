package novamachina.exnihilosequentia.api.crafting.fluidtransform;

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

public class FluidTransformRecipe extends SerializableRecipe {
    @Nonnull public static final IRecipeType<FluidTransformRecipe> RECIPE_TYPE = IRecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_transform");
    @Nullable private static RegistryObject<RecipeSerializer<FluidTransformRecipe>> serializer;
    @Nonnull private Ingredient catalyst;
    @Nonnull private FluidStack fluidInTank;
    @Nonnull private FluidStack result;

    public FluidTransformRecipe(@Nonnull final ResourceLocation id, @Nonnull final FluidStack fluidInTank,
                                @Nonnull final Ingredient catalyst, @Nonnull final FluidStack result) {
        super(null, RECIPE_TYPE, id);
        this.fluidInTank = fluidInTank;
        this.catalyst = catalyst;
        this.result = result;
    }

    @Nullable
    public static RegistryObject<RecipeSerializer<FluidTransformRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(@Nonnull final RegistryObject<RecipeSerializer<FluidTransformRecipe>> serializer) {
        FluidTransformRecipe.serializer = serializer;
    }

    @Nonnull
    public Ingredient getCatalyst() {
        return catalyst;
    }

    public void setCatalyst(@Nonnull final Ingredient catalyst) {
        this.catalyst = catalyst;
    }

    @Nonnull
    public FluidStack getFluidInTank() {
        return fluidInTank;
    }

    public void setFluidInTank(@Nonnull final FluidStack fluidInTank) {
        this.fluidInTank = fluidInTank;
    }

    @Override
    @Nonnull
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Nonnull
    public FluidStack getResult() {
        return result;
    }

    public void setResult(@Nonnull final FluidStack result) {
        this.result = result;
    }

    @Override
    @Nullable
    protected RecipeSerializer<FluidTransformRecipe> getENSerializer() {
        if (serializer == null)
            return null;
        return serializer.get();
    }
}
