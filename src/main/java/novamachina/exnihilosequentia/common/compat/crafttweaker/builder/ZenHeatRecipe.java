package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.impl.predicate.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenHeatRecipe")
public class ZenHeatRecipe {

    private final HeatRecipe internal;

    private ZenHeatRecipe(ResourceLocation recipeId) {
        this.internal = new HeatRecipe(recipeId, null, 0);
    }

    @ZenCodeType.Method
    public static ZenHeatRecipe builder(ResourceLocation recipeId) {
        return new ZenHeatRecipe(recipeId);
    }

    public HeatRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    public ZenHeatRecipe setAmount(int amount) {
        internal.setAmount(amount);
        return this;
    }

    @ZenCodeType.Method
    public ZenHeatRecipe setBlock(Block input) {
        internal.setInput(input);
        return this;
    }

    @ZenCodeType.Method
    public ZenHeatRecipe setProperties(StatePropertiesPredicate properties) {
        internal.setProperties(properties.toVanilla());
        return this;
    }
}
