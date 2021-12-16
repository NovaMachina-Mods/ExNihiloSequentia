package novamachina.exnihilosequentia.api.crafting.hammer;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;

import javax.annotation.Nonnull;

public class HammerRecipeBuilder extends ExNihiloFinishedRecipe<HammerRecipeBuilder> {

    private HammerRecipeBuilder() throws NullPointerException {
        //noinspection ConstantConditions
        super(HammerRecipe.getStaticSerializer().get());
        setMultipleResults(Integer.MAX_VALUE);
    }

    @Nonnull
    public static HammerRecipeBuilder builder() {
        return new HammerRecipeBuilder();
    }

    @Nonnull
    public HammerRecipeBuilder addDrop(@Nonnull final IItemProvider drop) {
        return addDrop(drop, 1, 1.0F);
    }

    @Nonnull
    public HammerRecipeBuilder addDrop(@Nonnull final IItemProvider drop, final int count) {
        return addDrop(drop, count, 1.0F);
    }

    @Nonnull
    public HammerRecipeBuilder addDrop(@Nonnull final IItemProvider drop, final float chance) {
        return addDrop(drop, 1, chance);
    }

    @Nonnull
    public HammerRecipeBuilder addDrop(@Nonnull final IItemProvider drop, final int count, final float chance) {
        return this.addResult(new ItemStackWithChance(new ItemStack(drop, count), chance));
    }

    @Nonnull
    public HammerRecipeBuilder input(@Nonnull final Ingredient input) {
        return this.addInput(input);
    }

    @Nonnull
    public HammerRecipeBuilder input(@Nonnull final IItemProvider input) {
        return this.input(Ingredient.of(input));
    }
}
