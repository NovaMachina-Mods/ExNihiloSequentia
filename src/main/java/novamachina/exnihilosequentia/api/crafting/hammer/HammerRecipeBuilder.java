package novamachina.exnihilosequentia.api.crafting.hammer;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;

public class HammerRecipeBuilder extends ExNihiloFinishedRecipe<HammerRecipeBuilder> {

    private HammerRecipeBuilder() {
        super(HammerRecipe.getStaticSerializer().get());
        setMultipleResults(Integer.MAX_VALUE);
    }

    public static HammerRecipeBuilder builder() {
        return new HammerRecipeBuilder();
    }

    public HammerRecipeBuilder addDrop(ItemLike drop) {
        return addDrop(drop, 1, 1.0F);
    }

    public HammerRecipeBuilder addDrop(ItemLike drop, int count) {
        return addDrop(drop, count, 1.0F);
    }

    public HammerRecipeBuilder addDrop(ItemLike drop, float chance) {
        return addDrop(drop, 1, chance);
    }

    public HammerRecipeBuilder addDrop(ItemLike drop, int count, float chance) {
        return this.addResult(new ItemStackWithChance(new ItemStack(drop, count), chance));
    }

    public HammerRecipeBuilder input(Ingredient input) {
        return this.addInput(input);
    }

    public HammerRecipeBuilder input(ItemLike input) {
        return this.input(Ingredient.of(input));
    }
}
