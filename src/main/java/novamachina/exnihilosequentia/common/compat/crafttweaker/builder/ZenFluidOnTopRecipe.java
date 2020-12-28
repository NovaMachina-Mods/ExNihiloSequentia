package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenFluidTransformRecipe")
public class ZenFluidTransformRecipie {

    private FluidTransformRecipe internal;

    private ZenFluidTransformRecipie(ResourceLocation recipeId) {
        this.internal = new FluidTransformRecipe(recipeId, FluidStack.EMPTY, Ingredient.EMPTY, FluidStack.EMPTY);
    }

    @ZenCodeType.Method
    public static ZenFluidTransformRecipie builder(ResourceLocation recipeId) {
        return new ZenFluidTransformRecipie(recipeId);
    }

    @ZenCodeType.Method
    public ZenFluidTransformRecipie setFluidInTank(IFluidStack fluidInTank) {
        internal.setFluidInTank(fluidInTank.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenFluidTransformRecipie setBlockBelow(IIngredient blockBelow) {
        internal.setBlockBelow(blockBelow.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    public ZenFluidTransformRecipie setResult(IFluidStack result) {
        internal.setResult(result.getInternal());
        return this;
    }

    public FluidTransformRecipe build() {
        return internal;
    }
}
