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

public class FluidTransformRecipe extends SerializableRecipe {
    public static final IRecipeType<FluidTransformRecipe> RECIPE_TYPE = IRecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_transform");
    private static RegistryObject<RecipeSerializer<FluidTransformRecipe>> serializer;
    private Ingredient catalyst;
    private FluidStack fluidInTank;
    private FluidStack result;

    public FluidTransformRecipe(ResourceLocation id, FluidStack fluidInTank, Ingredient catalyst, FluidStack result) {
        super(null, RECIPE_TYPE, id);
        this.fluidInTank = fluidInTank;
        this.catalyst = catalyst;
        this.result = result;
    }

    public static RegistryObject<RecipeSerializer<FluidTransformRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<RecipeSerializer<FluidTransformRecipe>> serializer) {
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
    protected RecipeSerializer<FluidTransformRecipe> getENSerializer() {
        return serializer.get();
    }
}
