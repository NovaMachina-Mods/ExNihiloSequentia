package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import java.util.ArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenSieveRecipe")
public class ZenSieveRecipe {

    private final SieveRecipe internal;

    private ZenSieveRecipe(ResourceLocation recipeId) {
        this.internal = new SieveRecipe(recipeId, Ingredient.EMPTY, ItemStack.EMPTY, new ArrayList<>(), false);
    }

    @ZenCodeType.Method
    public static ZenSieveRecipe builder(ResourceLocation recipeId) {
        return new ZenSieveRecipe(recipeId);
    }

    @ZenCodeType.Method
    public ZenSieveRecipe addDrop(IItemStack drop) {
        internal.setDrop(drop.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenSieveRecipe setInput(IIngredient input) {
        internal.setInput(input.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    public ZenSieveRecipe addRoll(String mesh, float chance) {
        internal.addRoll(mesh, chance);
        return this;
    }

    public SieveRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    public ZenSieveRecipe setWaterlogged() {
        internal.setWaterlogged();
        return this;
    }

}
