package novamachina.exnihilosequentia.common.tileentity.barrel.mode;

import novamachina.exnihilosequentia.common.utility.Constants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BarrelModeRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private static final Map<String, Supplier<AbstractBarrelMode>> modeNameMap = new HashMap<>();
    private static final Map<TriggerType, ArrayList<Supplier<AbstractBarrelMode>>> modeMap = new EnumMap<>(TriggerType.class);

    public static AbstractBarrelMode getModeFromName(String barrelMode) {
        return modeNameMap.getOrDefault(barrelMode, null).get();
    }

    public static ArrayList<Supplier<AbstractBarrelMode>> getModes(TriggerType type) {
        logger.debug("Getting barrel mode, Trigger: " + type);
        return modeMap.get(type);
    }

    public static void initialize() {
        logger.debug("Adding barrel modes");
        addMode(() -> new EmptyBarrelMode(Constants.BarrelModes.EMPTY), TriggerType.NONE);
        addMode(() -> new CompostBarrelMode(Constants.BarrelModes.COMPOST), TriggerType.ITEM);
        addMode(() -> new FluidsBarrelMode(Constants.BarrelModes.FLUID), TriggerType.FLUID);
        addMode(() -> new BlockBarrelMode(Constants.BarrelModes.BLOCK), TriggerType.NONE);
        addMode(() -> new MobSpawnBarrelMode(Constants.BarrelModes.MOB), TriggerType.NONE);
        addMode(() -> new FluidTransformBarrelMode(Constants.BarrelModes.TRANSFORM), TriggerType.NONE);
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
