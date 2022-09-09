package novamachina.exnihilosequentia.common.utility;

import com.mojang.logging.LogUtils;
import java.util.Arrays;
import javax.annotation.Nonnull;
import net.minecraft.world.item.crafting.Ingredient;

public class IngredientUtils {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  private IngredientUtils() {}

  public static boolean areIngredientsEqual(
      @Nonnull final Ingredient i1, @Nonnull final Ingredient i2) {
    @Nonnull final String item1;
    @Nonnull final String item2;
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
