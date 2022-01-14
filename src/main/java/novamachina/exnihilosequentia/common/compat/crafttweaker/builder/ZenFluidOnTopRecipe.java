package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenFluidOnTopRecipe")
public class ZenFluidOnTopRecipe {

    private final FluidOnTopRecipe internal;

    private ZenFluidOnTopRecipe(ResourceLocation recipeId) {
        this.internal = new FluidOnTopRecipe(recipeId, FluidStack.EMPTY, FluidStack.EMPTY, ItemStack.EMPTY);
    }

    @ZenCodeType.Method
    public static ZenFluidOnTopRecipe builder(ResourceLocation recipeId) {
        return new ZenFluidOnTopRecipe(recipeId);
    }

    public FluidOnTopRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    public ZenFluidOnTopRecipe setFluidInTank(IFluidStack fluidInTank) {
        internal.setFluidInTank(fluidInTank.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenFluidOnTopRecipe setFluidOnTop(IFluidStack fluidOnTop) {
        internal.setFluidOnTop(fluidOnTop.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenFluidOnTopRecipe setResult(IItemStack result) {
        internal.setResult(result.getInternal());
        return this;
    }

}
