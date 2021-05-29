package novamachina.exnihilosequentia.api.crafting.heat;

import com.google.gson.Gson;
import net.minecraft.block.Block;
import net.minecraft.item.crafting.Ingredient;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

import java.util.Map;

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

    public HeatRecipeBuilder liquid(boolean isLiquid) {
        return addWriter(jsonObj -> jsonObj.addProperty("isLiquid", isLiquid));
    }

    public HeatRecipeBuilder heatByLevel(Map<String, Integer> heatByLevel) {
        return this.addWriter(jsonObj -> jsonObj.add("heatByLevel", new Gson().toJsonTree(heatByLevel)));
    }
}
