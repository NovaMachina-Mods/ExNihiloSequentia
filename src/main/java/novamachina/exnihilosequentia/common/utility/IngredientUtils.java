package novamachina.exnihilosequentia.common.utility;

import com.google.gson.JsonElement;
import net.minecraft.item.crafting.Ingredient;
import org.apache.logging.log4j.LogManager;

public class IngredientUtils {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private IngredientUtils() {
    }

    public static boolean areIngredientsEqual(Ingredient i1, Ingredient i2) {
        JsonElement item1;
        JsonElement item2;
        try {
            item1 = i1.serialize();
            item2 = i2.serialize();
        } catch (Exception e) {
            logger.debug("Cannot compare ingredients");
            logger.debug("Ingredient 1: " + i1.toString());
            logger.debug("Ingredient 2: " + i2.toString());
            throw e;
        }

        return item1.equals(item2);
    }
}
