package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.impl.blocks.MCBlock;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenHeatRecipe")
public class ZenHeatRecipie {

    private HeatRecipe internal;

    private ZenHeatRecipie(ResourceLocation recipeId) {
        this.internal = new HeatRecipe(recipeId, null, 0);
    }

    @ZenCodeType.Method
    public static ZenHeatRecipie builder(ResourceLocation recipeId) {
        return new ZenHeatRecipie(recipeId);
    }

    @ZenCodeType.Method
    public ZenHeatRecipie setBlock(MCBlock input) {
        internal.setInput(input.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenHeatRecipie setAmount(int amount) {
        internal.setAmount(amount);
        return this;
    }

    public HeatRecipe build() {
        return internal;
    }
}
