package novamachina.exnihilosequentia.api.crafting.fluidtransform;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fmllegacy.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;

import javax.annotation.Nonnull;

public class FluidTransformRecipe extends SerializableRecipe {
    public static final RecipeType<FluidTransformRecipe> RECIPE_TYPE = RecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_transform");
    private static RegistryObject<IRecipeSerializer<FluidTransformRecipe>> serializer;
    private Ingredient catalyst;
    private FluidStack fluidInTank;
    private FluidStack result;

    public FluidTransformRecipe(ResourceLocation id, FluidStack fluidInTank, Ingredient catalyst, FluidStack result) {
        super(null, RECIPE_TYPE, id);
        this.fluidInTank = fluidInTank;
        this.catalyst = catalyst;
        this.result = result;
    }

    public static RegistryObject<IRecipeSerializer<FluidTransformRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<IRecipeSerializer<FluidTransformRecipe>> serializer) {
        FluidTransformRecipe.serializer = serializer;
    }

    public Ingredient getCatalyst() {
        return catalyst;
    }

    public void setCatalyst(Ingredient catalyst) {
        this.catalyst = catalyst;
    }

    public FluidStack getFluidInTank() {
        return fluidInTank;
    }

    public void setFluidInTank(FluidStack fluidInTank) {
        this.fluidInTank = fluidInTank;
    }

    @Nonnull
    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    public FluidStack getResult() {
        return result;
    }

    public void setResult(FluidStack result) {
        this.result = result;
    }

    @Override
    protected IRecipeSerializer<FluidTransformRecipe> getENSerializer() {
        return serializer.get();
    }
}
