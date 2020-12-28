package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenCrucibleRecipe")
public class ZenCrucibleRecipe {

    private CrucibleRecipe internal;

    private ZenCrucibleRecipe(ResourceLocation recipeId) {
        this.internal = new CrucibleRecipe(recipeId, Ingredient.EMPTY, 0, FluidStack.EMPTY, CrucilbeTypeEnum.WOOD);
    }

    @ZenCodeType.Method
    public static ZenCrucibleRecipe builder(ResourceLocation recipeId) {
        return new ZenCrucibleRecipe(recipeId);
    }

    @ZenCodeType.Method
    public ZenCrucibleRecipe setInput(IIngredient input) {
        internal.setInput(input.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    public ZenCrucibleRecipe setAmount(int amount) {
        internal.setAmount(amount);
        return this;
    }

    @ZenCodeType.Method
    public ZenCrucibleRecipe setResultFluid(IFluidStack fluid) {
        internal.setResultFluid(fluid.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenCrucibleRecipe setCrucibleType(String crucibleType) {
        internal.setCrucibleType(crucibleType);
        return this;
    }

    public CrucibleRecipe build() {
        return internal;
    }
}
