package novamachina.exnihilosequentia.api.crafting.hammer;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
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

    public HammerRecipeBuilder addDrop(IItemProvider drop) {
        return addDrop(drop, 1, 1.0F);
    }

    public HammerRecipeBuilder addDrop(IItemProvider drop, int count) {
        return addDrop(drop, count, 1.0F);
    }

    public HammerRecipeBuilder addDrop(IItemProvider drop, float chance) {
        return addDrop(drop, 1, chance);
    }

    public HammerRecipeBuilder addDrop(IItemProvider drop, int count, float chance) {
        return this.addResult(new ItemStackWithChance(new ItemStack(drop, count), chance));
    }

    public HammerRecipeBuilder input(Ingredient input) {
        return this.addInput(input);
    }

    public HammerRecipeBuilder input(IItemProvider input) {
        return this.input(Ingredient.fromItems(input));
    }
}
