package novamachina.exnihilosequentia.common.utility;

import com.google.gson.JsonElement;
import net.minecraft.item.crafting.Ingredient;

public class IngredientUtils {
    public static boolean areIngredientsEqual(Ingredient i1, Ingredient i2) {
        JsonElement item1 = i1.serialize();
        JsonElement item2 = i2.serialize();

        return item1.equals(item2);
    }
}
