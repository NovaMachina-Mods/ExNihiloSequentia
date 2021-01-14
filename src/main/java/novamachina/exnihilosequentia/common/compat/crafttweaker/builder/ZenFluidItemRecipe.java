package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenFluidItemRecipe")
public class ZenFluidItemRecipe {

    private final FluidItemRecipe internal;

    private ZenFluidItemRecipe(ResourceLocation recipeId) {
        this.internal = new FluidItemRecipe(recipeId, FluidStack.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
    }

    @ZenCodeType.Method
    public static ZenFluidItemRecipe builder(ResourceLocation recipeId) {
        return new ZenFluidItemRecipe(recipeId);
    }

    public FluidItemRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    public ZenFluidItemRecipe setFluidInTank(IFluidStack fluidInTank) {
        internal.setFluid(fluidInTank.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenFluidItemRecipe setInputItem(IIngredient inputItem) {
        internal.setInput(inputItem.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    public ZenFluidItemRecipe setResult(IItemStack result) {
        internal.setOutput(result.getInternal());
        return this;
    }
}
