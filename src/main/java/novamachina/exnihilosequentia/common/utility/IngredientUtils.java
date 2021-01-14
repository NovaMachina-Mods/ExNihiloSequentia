package novamachina.exnihilosequentia.common.utility;

import java.util.Arrays;
import net.minecraft.item.crafting.Ingredient;
import org.apache.logging.log4j.LogManager;

public class IngredientUtils {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private IngredientUtils() {
    }

    public static boolean areIngredientsEqual(Ingredient i1, Ingredient i2) {
        String item1;
        String item2;
        try {
            item1 = Arrays.toString(i1.getMatchingStacks());
            item2 = Arrays.toString(i2.getMatchingStacks());
        } catch (Exception e) {
            logger.debug("Cannot compare ingredients");
            logger.debug("Ingredient 1: " + Arrays.toString(i1.getMatchingStacks()));
            logger.debug("Ingredient 2: " + Arrays.toString(i2.getMatchingStacks()));
            logger.debug(e.getMessage());
            return false;
        }

        return item1.equals(item2);
    }
}
