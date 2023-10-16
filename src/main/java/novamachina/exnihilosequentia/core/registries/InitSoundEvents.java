package novamachina.exnihilosequentia.core.registries;

import net.minecraft.sounds.SoundEvent;
import novamachina.exnihilosequentia.sounds.EXNSoundEvents;
import novamachina.novacore.core.IRegistry;

public class InitSoundEvents {
  private InitSoundEvents() {}

  public static void init(IRegistry<SoundEvent> registry) {
    for (SoundEvent definition : EXNSoundEvents.getDefinitions()) {
      registry.register(definition);
    }
  }
}
