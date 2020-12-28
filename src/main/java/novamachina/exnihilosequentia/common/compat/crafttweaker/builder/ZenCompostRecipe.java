package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenCompostRecipe")
public class ZenCompostRecipe {

    private CompostRecipe internal;

    private ZenCompostRecipe(ResourceLocation recipeId) {
        this.internal = new CompostRecipe(recipeId, Ingredient.EMPTY, 0);
    }

    @ZenCodeType.Method
    public static ZenCompostRecipe builder(ResourceLocation recipeId) {
        return new ZenCompostRecipe(recipeId);
    }

    @ZenCodeType.Method
    public ZenCompostRecipe setInput(IIngredient input) {
        internal.setInput(input.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    public ZenCompostRecipe setAmount(int amount) {
        internal.setAmount(amount);
        return this;
    }

    public CompostRecipe build() {
        return internal;
    }
}
