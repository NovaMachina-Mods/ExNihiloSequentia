package novamachina.exnihilosequentia.api.crafting.hammer;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
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

    public HammerRecipeBuilder addDrop(Item drop) {
        return addDrop(drop, 1, 1.0F);
    }

    public HammerRecipeBuilder addDrop(Item drop, int count) {
        return addDrop(drop, count, 1.0F);
    }

    public HammerRecipeBuilder addDrop(Item drop, float chance) {
        return addDrop(drop, 1, chance);
    }

    public HammerRecipeBuilder addDrop(Item drop, int count, float chance) {
        return this.addResult(new ItemStackWithChance(new ItemStack(drop, count), chance));
    }

    public HammerRecipeBuilder input(Ingredient input) {
        return this.addInput(input);
    }

    public HammerRecipeBuilder input(Item input) {
        return this.input(Ingredient.of(input));
    }
}
