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

public class FluidOnTopRecipe extends SerializableRecipe {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    public static final IRecipeType<FluidOnTopRecipe> RECIPE_TYPE = IRecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_on_top");
    private static RegistryObject<RecipeSerializer<FluidOnTopRecipe>> serializer;
    private FluidStack fluidInTank;
    private FluidStack fluidOnTop;
    private ItemStack result;

    public FluidOnTopRecipe(ResourceLocation id, FluidStack fluidInTank, FluidStack fluidOnTop, ItemStack result) {
        super(result, RECIPE_TYPE, id);
        this.fluidInTank = fluidInTank;
        this.fluidOnTop = fluidOnTop;
        this.result = result;
    }

    public static RegistryObject<RecipeSerializer<FluidOnTopRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<RecipeSerializer<FluidOnTopRecipe>> serializer) {
        FluidOnTopRecipe.serializer = serializer;
    }

    public FluidStack getFluidInTank() {
        return fluidInTank;
    }

    public void setFluidInTank(FluidStack fluidInTank) {
        this.fluidInTank = fluidInTank;
    }

    public FluidStack getFluidOnTop() {
        return fluidOnTop;
    }

    public void setFluidOnTop(FluidStack fluidOnTop) {
        this.fluidOnTop = fluidOnTop;
    }

    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    public void setResult(ItemStack result) {
        this.result = result;
    }

    public boolean validInputs(Fluid fluidInTank, Fluid fluidOnTop) {
        return this.fluidInTank.getFluid().isSame(fluidInTank) && this.fluidOnTop.getFluid()
                .isSame(fluidOnTop);
    }

    @Override
    protected RecipeSerializer<FluidOnTopRecipe> getENSerializer() {
        return serializer.get();
    }
}
