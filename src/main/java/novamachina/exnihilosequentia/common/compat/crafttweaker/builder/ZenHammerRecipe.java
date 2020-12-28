package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenHammerRecipe")
public class ZenHammerRecipe {

    private HammerRecipe internal;

    private ZenHammerRecipe(ResourceLocation recipeId) {
        this.internal = new HammerRecipe(recipeId, ItemStack.EMPTY, ItemStack.EMPTY);
    }

    @ZenCodeType.Method
    public static ZenHammerRecipe builder(ResourceLocation recipeId) {
        return new ZenHammerRecipe(recipeId);
    }

    @ZenCodeType.Method
    public ZenHammerRecipe setInput(IItemStack input) {
        internal.setInput(input.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenHammerRecipe setOutput(IItemStack output) {
        internal.setOutput(output.getInternal());
        return this;
    }

    public HammerRecipe build() {
        return internal;
    }
}
