package novamachina.exnihilosequentia.api.crafting.crook;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;

import javax.annotation.Nonnull;

public class CrookRecipeBuilder extends ExNihiloFinishedRecipe<CrookRecipeBuilder> {
    private CrookRecipeBuilder() throws NullPointerException {
        //noinspection ConstantConditions
        super(CrookRecipe.getStaticSerializer().get());
        setMultipleResults(Integer.MAX_VALUE);
    }

    public static CrookRecipeBuilder builder() {
        return new CrookRecipeBuilder();
    }

    @Nonnull
    public CrookRecipeBuilder addDrop(@Nonnull final IItemProvider drop, final float chance) {
        return this.addResult(new ItemStackWithChance(new ItemStack(drop), chance));
    }

    @Nonnull
    public CrookRecipeBuilder input(@Nonnull final Block input) {
        return this.addInput(input);
    }

    @Nonnull
    public CrookRecipeBuilder input(@Nonnull final ITag.INamedTag<Item> tag) {
        return this.addInput(tag);
    }
}
