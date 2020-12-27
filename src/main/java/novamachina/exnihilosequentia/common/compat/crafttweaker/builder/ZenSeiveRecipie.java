package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.google.common.base.Preconditions;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenSieveRecipe")
public class ZenSeiveRecipie {

    private SieveRecipe internal;

    private ZenSeiveRecipie(ResourceLocation recipeId) {
        this.internal = new SieveRecipe(recipeId, Ingredient.EMPTY, ItemStack.EMPTY, new ArrayList<>(), false);
    }

    @ZenCodeType.Method
    public static ZenSeiveRecipie builder(ResourceLocation recipeId) {
        return new ZenSeiveRecipie(recipeId);
    }

    @ZenCodeType.Method
    public ZenSeiveRecipie addInput(IIngredient input) {
        internal.setInput(input.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    public ZenSeiveRecipie addDrop(IItemStack drop) {
        internal.setDrop(drop.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenSeiveRecipie addRoll(String mesh, float chance) {
        internal.addRoll(mesh, chance);
        return this;
    }

    @ZenCodeType.Method
    public ZenSeiveRecipie setWaterlogged() {
        internal.setWaterlogged();
        return this;
    }

    public SieveRecipe build() {
        return internal;
    }
}
