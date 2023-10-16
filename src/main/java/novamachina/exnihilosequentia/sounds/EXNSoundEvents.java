package novamachina.exnihilosequentia.sounds;

import java.util.List;
import net.minecraft.sounds.SoundEvent;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.novacore.core.registries.SoundEventRegistry;

public class EXNSoundEvents {
  private static final SoundEventRegistry SOUND_EVENTS =
      new SoundEventRegistry(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);

  public static List<SoundEvent> getDefinitions() {
    return SOUND_EVENTS.getRegistry();
  }

  public static final SoundEvent PEBBLE_THROW = SOUND_EVENTS.soundEvent("pebble_throw");

  private EXNSoundEvents() {}
}
