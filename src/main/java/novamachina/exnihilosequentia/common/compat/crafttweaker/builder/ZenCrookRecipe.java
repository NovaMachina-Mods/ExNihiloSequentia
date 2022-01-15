package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import java.util.ArrayList;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import org.openzen.zencode.java.ZenCodeType;

import javax.annotation.Nonnull;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenCrookRecipe")
public class ZenCrookRecipe {

    @Nonnull private final CrookRecipe internal;

    private ZenCrookRecipe(@Nonnull final ResourceLocation recipeId) {
        this.internal = new CrookRecipe(recipeId, Ingredient.EMPTY, new ArrayList<>());
    }

    @ZenCodeType.Method
    @Nonnull
    public static ZenCrookRecipe builder(@Nonnull final ResourceLocation recipeId) {
        return new ZenCrookRecipe(recipeId);
    }

    @ZenCodeType.Method
    @Nonnull
    @SuppressWarnings("unused")
    public ZenCrookRecipe addDrop(@Nonnull final IItemStack drop, final float chance) {
        internal.addOutput(drop.getInternal(), chance);
        return this;
    }

    @Nonnull
    public CrookRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    @Nonnull
    public ZenCrookRecipe setInput(@Nonnull final IIngredient input) {
        internal.setInput(input.asVanillaIngredient());
        return this;
    }
}
