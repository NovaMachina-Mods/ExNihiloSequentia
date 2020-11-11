package novamachina.exnihilosequentia.common.utility;

import java.text.DecimalFormat;
import java.text.Format;

public class StringUtils {
    private static final Format PERCENT_FORMAT = new DecimalFormat("#.##%");

    public static String formatPercent(Float num) {
        return PERCENT_FORMAT.format(num);
    }
}
