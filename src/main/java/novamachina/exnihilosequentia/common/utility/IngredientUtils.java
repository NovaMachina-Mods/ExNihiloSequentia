package novamachina.exnihilosequentia.common.utility;

import java.util.Arrays;
import net.minecraft.item.crafting.Ingredient;

public class IngredientUtils {
    private static final ExNihiloLogger logger = new ExNihiloLogger(IngredientUtils.class);

    private IngredientUtils() {
    }

    public static boolean areIngredientsEqual(Ingredient i1, Ingredient i2) {
        String item1;
        String item2;
        try {
            item1 = Arrays.toString(i1.getItems());
            item2 = Arrays.toString(i2.getItems());
        } catch (Exception e) {
            logger.debug("Cannot compare ingredients");
            logger.debug("Ingredient 1: " + Arrays.toString(i1.getItems()));
            logger.debug("Ingredient 2: " + Arrays.toString(i2.getItems()));
            logger.debug(e.getMessage());
            return false;
        }

        return item1.equals(item2);
    }
}
