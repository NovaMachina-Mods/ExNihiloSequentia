package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.IIngredient;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenFluidTransformRecipe")
public class ZenFluidTransformRecipe {

    private FluidTransformRecipe internal;

    private ZenFluidTransformRecipe(ResourceLocation recipeId) {
        this.internal = new FluidTransformRecipe(recipeId, FluidStack.EMPTY, Ingredient.EMPTY, FluidStack.EMPTY);
    }

    @ZenCodeType.Method
    public static ZenFluidTransformRecipe builder(ResourceLocation recipeId) {
        return new ZenFluidTransformRecipe(recipeId);
    }

    @ZenCodeType.Method
    public ZenFluidTransformRecipe setFluidInTank(IFluidStack fluidInTank) {
        internal.setFluidInTank(fluidInTank.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenFluidTransformRecipe setBlockBelow(IIngredient blockBelow) {
        internal.setBlockBelow(blockBelow.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    public ZenFluidTransformRecipe setResult(IFluidStack result) {
        internal.setResult(result.getInternal());
        return this;
    }

    public FluidTransformRecipe build() {
        return internal;
    }
}
