package novamachina.exnihilosequentia.api.crafting.fluidontop;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidOnTopRecipe extends SerializableRecipe {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    @Nonnull public static final IRecipeType<FluidOnTopRecipe> RECIPE_TYPE = IRecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_on_top");
    @Nullable private static RegistryObject<RecipeSerializer<FluidOnTopRecipe>> serializer;
    @Nonnull private FluidStack fluidInTank;
    @Nonnull private FluidStack fluidOnTop;
    @Nonnull private ItemStack result;

    public FluidOnTopRecipe(@Nonnull final ResourceLocation id, @Nonnull final FluidStack fluidInTank,
                            @Nonnull final FluidStack fluidOnTop, @Nonnull final ItemStack result) {
        super(result, RECIPE_TYPE, id);
        this.fluidInTank = fluidInTank;
        this.fluidOnTop = fluidOnTop;
        this.result = result;
    }

    @Nullable
    public static RegistryObject<RecipeSerializer<FluidOnTopRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(@Nonnull final RegistryObject<RecipeSerializer<FluidOnTopRecipe>> serializer) {
        FluidOnTopRecipe.serializer = serializer;
    }

    @Nonnull
    public FluidStack getFluidInTank() {
        return fluidInTank;
    }

    public void setFluidInTank(@Nonnull final FluidStack fluidInTank) {
        this.fluidInTank = fluidInTank;
    }

    @Nonnull
    public FluidStack getFluidOnTop() {
        return fluidOnTop;
    }

    public void setFluidOnTop(@Nonnull final FluidStack fluidOnTop) {
        this.fluidOnTop = fluidOnTop;
    }

    @Override
    @Nonnull
    public ItemStack getResultItem() {
        return result.copy();
    }

    public void setResult(@Nonnull final ItemStack result) {
        this.result = result;
    }

    public boolean validInputs(@Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop) {
        return this.fluidInTank.getFluid().isSame(fluidInTank) && this.fluidOnTop.getFluid()
                .isSame(fluidOnTop);
    }

    @Override
    @Nullable
    protected RecipeSerializer<FluidOnTopRecipe> getENSerializer() {
        if (serializer == null)
            return null;
        return serializer.get();
    }
}
