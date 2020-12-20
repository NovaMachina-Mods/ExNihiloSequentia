package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BarrelModeRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private static final Map<String, Supplier<AbstractBarrelMode>> modeNameMap = new HashMap<>();
    private static final Map<TriggerType, ArrayList<Supplier<AbstractBarrelMode>>> modeMap = new EnumMap<>(TriggerType.class);

    public static AbstractBarrelMode getModeFromName(String barrelMode) {
        return modeNameMap.getOrDefault(barrelMode, null).get();
    }

    public static List<Supplier<AbstractBarrelMode>> getModes(TriggerType type) {
        logger.debug("Getting barrel mode, Trigger: " + type);
        return modeMap.get(type);
    }

    public static void initialize() {
        logger.debug("Adding barrel modes");
        addMode(() -> new EmptyBarrelMode(ExNihiloConstants.BarrelModes.EMPTY), TriggerType.NONE);
        addMode(() -> new CompostBarrelMode(ExNihiloConstants.BarrelModes.COMPOST), TriggerType.ITEM);
        addMode(() -> new FluidsBarrelMode(ExNihiloConstants.BarrelModes.FLUID), TriggerType.FLUID);
        addMode(() -> new BlockBarrelMode(ExNihiloConstants.BarrelModes.BLOCK), TriggerType.NONE);
        addMode(() -> new MobSpawnBarrelMode(ExNihiloConstants.BarrelModes.MOB), TriggerType.NONE);
        addMode(() -> new FluidTransformBarrelMode(ExNihiloConstants.BarrelModes.TRANSFORM), TriggerType.NONE);
    }

    public static void addMode(Supplier<AbstractBarrelMode> mode, TriggerType type) {
        logger.debug("Adding mode: " + mode.toString() + ", Trigger: " + type);
        ArrayList<Supplier<AbstractBarrelMode>> list = modeMap.get(type);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(mode);
        modeMap.put(type, list);
        modeNameMap.put(mode.get().getModeName(), mode);
    }

    public enum TriggerType {
        ITEM, FLUID, NONE
    }
}
