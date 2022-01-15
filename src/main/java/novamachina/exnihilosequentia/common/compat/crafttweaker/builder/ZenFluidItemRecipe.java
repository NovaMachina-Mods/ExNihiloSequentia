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

import javax.annotation.Nonnull;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenFluidItemRecipe")
public class ZenFluidItemRecipe {

    @Nonnull private final FluidItemRecipe internal;

    private ZenFluidItemRecipe(@Nonnull final ResourceLocation recipeId) {
        this.internal = new FluidItemRecipe(recipeId, FluidStack.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
    }

    @ZenCodeType.Method
    @Nonnull
    public static ZenFluidItemRecipe builder(@Nonnull final ResourceLocation recipeId) {
        return new ZenFluidItemRecipe(recipeId);
    }

    @Nonnull
    public FluidItemRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    @Nonnull
    public ZenFluidItemRecipe setFluidInTank(@Nonnull final IFluidStack fluidInTank) {
        internal.setFluid(fluidInTank.getInternal());
        return this;
    }

    @ZenCodeType.Method
    @Nonnull
    @SuppressWarnings("unused")
    public ZenFluidItemRecipe setInputItem(@Nonnull final IIngredient inputItem) {
        internal.setInput(inputItem.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    @Nonnull
    public ZenFluidItemRecipe setResult(@Nonnull final IItemStack result) {
        internal.setOutput(result.getInternal());
        return this;
    }
}
