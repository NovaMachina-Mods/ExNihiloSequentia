package com.novamachina.exnihilosequentia.common.api.crafting.hammer;

import com.novamachina.exnihilosequentia.common.api.crafting.ExNihiloFinishedRecipe;
import net.minecraft.block.Block;
import net.minecraft.util.IItemProvider;

public class HammerRecipeBuilder extends ExNihiloFinishedRecipe<HammerRecipeBuilder> {

    private HammerRecipeBuilder() {
        super(HammerRecipe.SERIALIZER.get());
    }

    public HammerRecipeBuilder result(IItemProvider result) {
        return this.addResult(result);
    }
    public HammerRecipeBuilder input(Block input) {
        return this.addInput(input);
    }

    public static HammerRecipeBuilder builder() {
        return new HammerRecipeBuilder();
    }
}