package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import java.util.ArrayList;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenCrookRecipe")
public class ZenCrookRecipe {

    private final CrookRecipe internal;

    private ZenCrookRecipe(ResourceLocation recipeId) {
        this.internal = new CrookRecipe(recipeId, Ingredient.EMPTY, new ArrayList<>());
    }

    @ZenCodeType.Method
    public static ZenCrookRecipe builder(ResourceLocation recipeId) {
        return new ZenCrookRecipe(recipeId);
    }

    @ZenCodeType.Method
    public ZenCrookRecipe addDrop(IItemStack drop, float chance) {
        internal.addOutput(drop.getInternal(), chance);
        return this;
    }

    public CrookRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    public ZenCrookRecipe setInput(IIngredient input) {
        internal.setInput(input.asVanillaIngredient());
        return this;
    }
}
