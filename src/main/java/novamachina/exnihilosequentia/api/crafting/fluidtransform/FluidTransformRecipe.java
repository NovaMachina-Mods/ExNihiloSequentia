package novamachina.exnihilosequentia.api.crafting.fluidtransform;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidTransformRecipe extends SerializableRecipe {
    public static RecipeType<FluidTransformRecipe> RECIPE_TYPE;
    @Nullable private static RegistryObject<ExNihiloRecipeSerializer<FluidTransformRecipe>> serializer;
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

    public static RegistryObject<ExNihiloRecipeSerializer<FluidTransformRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(@Nonnull final RegistryObject<ExNihiloRecipeSerializer<FluidTransformRecipe>> serializer) {
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
    protected ExNihiloRecipeSerializer<FluidTransformRecipe> getENSerializer() {
        if (serializer == null)
            return null;
        return serializer.get();
    }
}
