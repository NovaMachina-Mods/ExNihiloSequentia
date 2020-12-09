package novamachina.exnihilosequentia.api.crafting.fluidtransform;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.Constants;

public class FluidTransformRecipe extends SerializableRecipe {
    public static final IRecipeType<FluidTransformRecipe> RECIPE_TYPE = IRecipeType
        .register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_transform");
    private static RegistryObject<RecipeSerializer<FluidTransformRecipe>> serializer;
    private final FluidStack fluidInTank;
    private final Ingredient blockBelow;
    private final FluidStack result;

    public FluidTransformRecipe(ResourceLocation id, FluidStack fluidInTank, Ingredient blockBelow, FluidStack result) {
        super(null, RECIPE_TYPE, id);
        this.fluidInTank = fluidInTank;
        this.blockBelow = blockBelow;
        this.result = result;
    }

    public static RegistryObject<RecipeSerializer<FluidTransformRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<RecipeSerializer<FluidTransformRecipe>> serializer) {
        FluidTransformRecipe.serializer = serializer;
    }

    public FluidStack getFluidInTank() {
        return fluidInTank;
    }

    public Ingredient getBlockBelow() {
        return blockBelow;
    }

    public FluidStack getResult() {
        return result;
    }

    @Override
    protected RecipeSerializer getENSerializer() {
        return serializer.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
}
