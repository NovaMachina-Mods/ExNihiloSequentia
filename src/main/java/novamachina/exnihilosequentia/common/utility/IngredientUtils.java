package novamachina.exnihilosequentia.common.utility;

import com.google.gson.JsonElement;
import net.minecraft.item.crafting.Ingredient;
import org.apache.logging.log4j.LogManager;

public class IngredientUtils {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public static boolean areIngredientsEqual(Ingredient i1, Ingredient i2) {
//        logger.debug("ingredient1: " + i1);
        JsonElement item1 = i1.serialize();
//        logger.debug("item1: " + item1);
//        logger.debug("ingredient2: " + i2);
        JsonElement item2 = i2.serialize();
//        logger.debug("item2: " + item2);

        return item1.equals(item2);
    }
}
