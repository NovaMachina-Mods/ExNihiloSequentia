package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.IIngredient;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucibleTypeEnum;
import org.openzen.zencode.java.ZenCodeType;

import javax.annotation.Nonnull;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenCrucibleRecipe")
public class ZenCrucibleRecipe {

    @Nonnull private final CrucibleRecipe internal;

    private ZenCrucibleRecipe(@Nonnull final ResourceLocation recipeId) {
        this.internal = new CrucibleRecipe(recipeId, Ingredient.EMPTY, 0, FluidStack.EMPTY, CrucibleTypeEnum.WOOD);
    }

    @ZenCodeType.Method
    @Nonnull
    public static ZenCrucibleRecipe builder(@Nonnull final ResourceLocation recipeId) {
        return new ZenCrucibleRecipe(recipeId);
    }

    @Nonnull
    public CrucibleRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    @Nonnull
    public ZenCrucibleRecipe setAmount(final int amount) {
        internal.setAmount(amount);
        return this;
    }

    @ZenCodeType.Method
    @Nonnull
    public ZenCrucibleRecipe setCrucibleType(@Nonnull final String crucibleType) {
        internal.setCrucibleType(crucibleType);
        return this;
    }

    @ZenCodeType.Method
    @Nonnull
    public ZenCrucibleRecipe setInput(@Nonnull final IIngredient input) {
        internal.setInput(input.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    @Nonnull
    @SuppressWarnings("unused")
    public ZenCrucibleRecipe setResultFluid(@Nonnull final IFluidStack fluid) {
        internal.setResultFluid(fluid.getInternal());
        return this;
    }
}
