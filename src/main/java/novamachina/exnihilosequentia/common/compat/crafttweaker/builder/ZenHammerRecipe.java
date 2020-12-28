package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenHammerRecipe")
public class ZenHammerRecipie {

    private HammerRecipe internal;

    private ZenHammerRecipie(ResourceLocation recipeId) {
        this.internal = new HammerRecipe(recipeId, ItemStack.EMPTY, ItemStack.EMPTY);
    }

    @ZenCodeType.Method
    public static ZenHammerRecipie builder(ResourceLocation recipeId) {
        return new ZenHammerRecipie(recipeId);
    }

    @ZenCodeType.Method
    public ZenHammerRecipie setInput(IItemStack input) {
        internal.setInput(input.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenHammerRecipie setOutput(IItemStack output) {
        internal.setOutput(output.getInternal());
        return this;
    }

    public HammerRecipe build() {
        return internal;
    }
}
