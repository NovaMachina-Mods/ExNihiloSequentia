package novamachina.exnihilosequentia.common.utility;

import java.text.DecimalFormat;
import java.text.Format;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.text.WordUtils;

public class StringUtils {

  private static final Format PERCENT_FORMAT = new DecimalFormat("#.##%");

  private StringUtils() {
  }

  public static String formatPercent(@Nonnull final Float num) {
    return PERCENT_FORMAT.format(num);
  }

  public static String capitalize(@Nonnull final String input) {
    //noinspection deprecation
    return WordUtils.capitalizeFully(input);
  }
}
