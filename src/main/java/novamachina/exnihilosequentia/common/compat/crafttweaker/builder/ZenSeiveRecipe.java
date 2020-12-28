package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenSieveRecipe")
public class ZenSeiveRecipe {

    private SieveRecipe internal;

    private ZenSeiveRecipe(ResourceLocation recipeId) {
        this.internal = new SieveRecipe(recipeId, Ingredient.EMPTY, ItemStack.EMPTY, new ArrayList<>(), false);
    }

    @ZenCodeType.Method
    public static ZenSeiveRecipe builder(ResourceLocation recipeId) {
        return new ZenSeiveRecipe(recipeId);
    }

    @ZenCodeType.Method
    public ZenSeiveRecipe addInput(IIngredient input) {
        internal.setInput(input.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    public ZenSeiveRecipe addDrop(IItemStack drop) {
        internal.setDrop(drop.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenSeiveRecipe addRoll(String mesh, float chance) {
        internal.addRoll(mesh, chance);
        return this;
    }

    @ZenCodeType.Method
    public ZenSeiveRecipe setWaterlogged() {
        internal.setWaterlogged();
        return this;
    }

    public SieveRecipe build() {
        return internal;
    }
}
