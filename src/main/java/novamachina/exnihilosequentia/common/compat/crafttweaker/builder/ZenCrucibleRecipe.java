package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucibleTypeEnum;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenCrucibleRecipe")
public class ZenCrucibleRecipe {

    private final CrucibleRecipe internal;

    private ZenCrucibleRecipe(ResourceLocation recipeId) {
        this.internal = new CrucibleRecipe(recipeId, Ingredient.EMPTY, 0, FluidStack.EMPTY, CrucibleTypeEnum.WOOD);
    }

    @ZenCodeType.Method
    public static ZenCrucibleRecipe builder(ResourceLocation recipeId) {
        return new ZenCrucibleRecipe(recipeId);
    }

    public CrucibleRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    public ZenCrucibleRecipe setAmount(int amount) {
        internal.setAmount(amount);
        return this;
    }

    @ZenCodeType.Method
    public ZenCrucibleRecipe setCrucibleType(String crucibleType) {
        internal.setCrucibleType(crucibleType);
        return this;
    }

    @ZenCodeType.Method
    public ZenCrucibleRecipe setInput(IIngredient input) {
        internal.setInput(input.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    public ZenCrucibleRecipe setResultFluid(IFluidStack fluid) {
        internal.setResultFluid(fluid.getInternal());
        return this;
    }

}
