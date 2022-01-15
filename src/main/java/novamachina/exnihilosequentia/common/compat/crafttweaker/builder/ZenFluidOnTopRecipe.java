package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import org.openzen.zencode.java.ZenCodeType;

import javax.annotation.Nonnull;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenFluidOnTopRecipe")
public class ZenFluidOnTopRecipe {

    @Nonnull private final FluidOnTopRecipe internal;

    private ZenFluidOnTopRecipe(@Nonnull final ResourceLocation recipeId) {
        this.internal = new FluidOnTopRecipe(recipeId, FluidStack.EMPTY, FluidStack.EMPTY, ItemStack.EMPTY);
    }

    @ZenCodeType.Method
    @Nonnull
    public static ZenFluidOnTopRecipe builder(@Nonnull final ResourceLocation recipeId) {
        return new ZenFluidOnTopRecipe(recipeId);
    }

    @Nonnull
    public FluidOnTopRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    @Nonnull
    public ZenFluidOnTopRecipe setFluidInTank(@Nonnull final IFluidStack fluidInTank) {
        internal.setFluidInTank(fluidInTank.getInternal());
        return this;
    }

    @ZenCodeType.Method
    @Nonnull
    public ZenFluidOnTopRecipe setFluidOnTop(@Nonnull final IFluidStack fluidOnTop) {
        internal.setFluidOnTop(fluidOnTop.getInternal());
        return this;
    }

    @ZenCodeType.Method
    @Nonnull
    public ZenFluidOnTopRecipe setResult(@Nonnull final IItemStack result) {
        internal.setResult(result.getInternal());
        return this;
    }
}
