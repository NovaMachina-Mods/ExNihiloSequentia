package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenCompostRecipe")
public class ZenCompostRecipe {

    private final CompostRecipe internal;

    private ZenCompostRecipe(ResourceLocation recipeId) {
        this.internal = new CompostRecipe(recipeId, Ingredient.EMPTY, 0);
    }

    @ZenCodeType.Method
    public static ZenCompostRecipe builder(ResourceLocation recipeId) {
        return new ZenCompostRecipe(recipeId);
    }

    public CompostRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    public ZenCompostRecipe setAmount(int amount) {
        internal.setAmount(amount);
        return this;
    }

    @ZenCodeType.Method
    public ZenCompostRecipe setInput(IIngredient input) {
        internal.setInput(input.asVanillaIngredient());
        return this;
    }
}
