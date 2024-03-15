package novamachina.exnihilosequentia.world.level.block.entity.mode;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BarrelModeRegistry {

  private static Logger log = LoggerFactory.getLogger(BarrelModeRegistry.class);

  @Nonnull
  private static final Map<String, Supplier<AbstractBarrelMode>> modeNameMap = new HashMap<>();

  @Nonnull
  private static final Map<TriggerType, ArrayList<Supplier<AbstractBarrelMode>>> modeMap =
      new EnumMap<>(TriggerType.class);

  @Nullable
  public static AbstractBarrelMode getModeFromName(@Nonnull final String barrelMode) {
    return modeNameMap.getOrDefault(barrelMode, null).get();
  }

  @Nonnull
  public static List<Supplier<AbstractBarrelMode>> getModes(@Nonnull final TriggerType type) {
    log.debug("Getting barrel mode, Trigger: {}", type);
    return modeMap.get(type);
  }

  public static void initialize() {
    log.debug("Adding barrel modes");
    addMode(() -> new EmptyBarrelMode(ExNihiloConstants.BarrelModes.EMPTY), TriggerType.NONE);
    addMode(() -> new CompostBarrelMode(ExNihiloConstants.BarrelModes.COMPOST), TriggerType.ITEM);
    addMode(() -> new FluidsBarrelMode(ExNihiloConstants.BarrelModes.FLUID), TriggerType.ITEM);
    addMode(() -> new BlockBarrelMode(ExNihiloConstants.BarrelModes.BLOCK), TriggerType.NONE);
    addMode(() -> new MobSpawnBarrelMode(ExNihiloConstants.BarrelModes.MOB), TriggerType.NONE);
    addMode(
        () -> new FluidTransformBarrelMode(ExNihiloConstants.BarrelModes.TRANSFORM),
        TriggerType.NONE);
  }

  public static void addMode(
      @Nonnull final Supplier<AbstractBarrelMode> mode, @Nonnull final TriggerType type) {
    log.debug("Adding mode: {}, Trigger: {}", mode, type);
    @Nullable ArrayList<Supplier<AbstractBarrelMode>> list = modeMap.get(type);
    if (list == null) {
      list = new ArrayList<>();
    }
    list.add(mode);
    modeMap.put(type, list);
    modeNameMap.put(mode.get().getModeName(), mode);
  }

  public enum TriggerType {
    ITEM,
    FLUID,
    NONE
  }
}
