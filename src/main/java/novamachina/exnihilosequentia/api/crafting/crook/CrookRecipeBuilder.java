package novamachina.exnihilosequentia.api.crafting.crook;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;

import javax.annotation.Nonnull;

public class CrookRecipeBuilder extends ExNihiloFinishedRecipe<CrookRecipeBuilder> {
    private CrookRecipeBuilder() throws NullPointerException {
        super(CrookRecipe.getStaticSerializer().get());
        setMultipleResults(Integer.MAX_VALUE);
    }

    public static CrookRecipeBuilder builder() {
        return new CrookRecipeBuilder();
    }

    @Nonnull
    public CrookRecipeBuilder addDrop(@Nonnull final ItemLike drop, final float chance) {
        return this.addResult(new ItemStackWithChance(new ItemStack(drop), chance));
    }

    @Nonnull
    public CrookRecipeBuilder input(@Nonnull final Block input) {
        return this.addInput(input);
    }

    @Nonnull
    public CrookRecipeBuilder input(@Nonnull final TagKey<Item> tag) {
        return this.addInput(tag);
    }
}
