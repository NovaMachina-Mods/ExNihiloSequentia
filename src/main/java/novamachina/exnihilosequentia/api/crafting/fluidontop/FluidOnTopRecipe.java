package novamachina.exnihilosequentia.api.crafting.fluidontop;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;

public class FluidOnTopRecipe extends SerializableRecipe {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    public static final RecipeType<FluidOnTopRecipe> RECIPE_TYPE = RecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_on_top");
    private static RegistryObject<IRecipeSerializer<FluidOnTopRecipe>> serializer;
    private FluidStack fluidInTank;
    private FluidStack fluidOnTop;
    private ItemStack result;

    public FluidOnTopRecipe(ResourceLocation id, FluidStack fluidInTank, FluidStack fluidOnTop, ItemStack result) {
        super(result, RECIPE_TYPE, id);
        this.fluidInTank = fluidInTank;
        this.fluidOnTop = fluidOnTop;
        this.result = result;
    }

    public static RegistryObject<IRecipeSerializer<FluidOnTopRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<IRecipeSerializer<FluidOnTopRecipe>> serializer) {
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

    @Nonnull
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
    protected IRecipeSerializer<FluidOnTopRecipe> getENSerializer() {
        return serializer.get();
    }
}
