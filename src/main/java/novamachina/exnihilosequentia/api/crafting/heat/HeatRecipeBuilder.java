package novamachina.exnihilosequentia.api.crafting.heat;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.item.crafting.Ingredient;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

public class HeatRecipeBuilder extends ExNihiloFinishedRecipe<HeatRecipeBuilder> {
    private HeatRecipeBuilder() {
        super(HeatRecipe.getStaticSerializer().get());
    }

    public static HeatRecipeBuilder builder() {
        return new HeatRecipeBuilder();
    }

    public HeatRecipeBuilder amount(int amount) {
        return addWriter(jsonObj -> jsonObj.addProperty("amount", amount));
    }

    public HeatRecipeBuilder input(Ingredient input) {
        return this.addInput(input);
    }

    public HeatRecipeBuilder input(Block block) {
        return this.addBlock(block);
    }

    public HeatRecipeBuilder properties(StatePropertiesPredicate properties) {
        return this.addWriter(jsonObj -> jsonObj.add("state", properties.serializeToJson()));
    }
}
