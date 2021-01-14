package novamachina.exnihilosequentia.api.crafting.crook;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;

public class CrookRecipeBuilder extends ExNihiloFinishedRecipe<CrookRecipeBuilder> {
    private CrookRecipeBuilder() {
        super(CrookRecipe.getStaticSerializer().get());
        setMultipleResults(Integer.MAX_VALUE);
    }

    public static CrookRecipeBuilder builder() {
        return new CrookRecipeBuilder();
    }

    public CrookRecipeBuilder addDrop(IItemProvider drop, float chance) {
        return this.addResult(new ItemStackWithChance(new ItemStack(drop), chance));
    }

    public CrookRecipeBuilder input(Block input) {
        return this.addInput(input);
    }

    public CrookRecipeBuilder input(ITag.INamedTag<Item> tag) {
        return this.addInput(tag);
    }
}
