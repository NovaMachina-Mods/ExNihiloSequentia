package novamachina.exnihilosequentia.api.crafting.crook;

import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
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

    public CrookRecipeBuilder addDrop(Item drop, float chance) {
        return this.addResult(new ItemStackWithChance(new ItemStack(drop), chance));
    }

    public CrookRecipeBuilder input(Block input) {
        return this.addInput(Ingredient.of(input));
    }

    public CrookRecipeBuilder input(Tag.Named<Item> tag) {
        return this.addInput(tag);
    }
}
