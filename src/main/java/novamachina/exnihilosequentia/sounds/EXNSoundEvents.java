package novamachina.exnihilosequentia.sounds;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import novamachina.exnihilosequentia.ExNihiloSequentia;

public class EXNSoundEvents {

  private static final Map<ResourceLocation, SoundEvent> SOUND_EVENTS = new HashMap<>();

  public static Map<ResourceLocation, SoundEvent> getDefinitions() {
    return SOUND_EVENTS;
  }

  public static final SoundEvent PEBBLE_THROW =
      register(
          ExNihiloSequentia.makeId("pebble_throw"),
          () -> new SoundEvent(ExNihiloSequentia.makeId("pebble_throw")));

  private EXNSoundEvents() {}

  public static SoundEvent register(ResourceLocation id, Supplier<SoundEvent> supplier) {
    SoundEvent event = supplier.get();
    SOUND_EVENTS.put(id, event);
    return event;
  }
}
