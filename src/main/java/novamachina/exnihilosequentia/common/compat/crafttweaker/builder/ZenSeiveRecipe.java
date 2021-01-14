package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenSieveRecipe")
public class ZenSeiveRecipe {

    private final SieveRecipe internal;

    private ZenSeiveRecipe(ResourceLocation recipeId) {
        this.internal = new SieveRecipe(recipeId, Ingredient.EMPTY, ItemStack.EMPTY, new ArrayList<>(), false);
    }

    @ZenCodeType.Method
    public static ZenSeiveRecipe builder(ResourceLocation recipeId) {
        return new ZenSeiveRecipe(recipeId);
    }

    @ZenCodeType.Method
    public ZenSeiveRecipe addDrop(IItemStack drop) {
        internal.setDrop(drop.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenSeiveRecipe setInput(IIngredient input) {
        internal.setInput(input.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    public ZenSeiveRecipe addRoll(String mesh, float chance) {
        internal.addRoll(mesh, chance);
        return this;
    }

    public SieveRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    public ZenSeiveRecipe setWaterlogged() {
        internal.setWaterlogged();
        return this;
    }
}
