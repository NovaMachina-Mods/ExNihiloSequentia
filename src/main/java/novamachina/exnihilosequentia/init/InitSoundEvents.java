package novamachina.exnihilosequentia.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;
import novamachina.exnihilosequentia.sounds.EXNSoundEvents;

import java.util.Map;

public class InitSoundEvents {
  private InitSoundEvents() {}

  public static void init(IForgeRegistry<SoundEvent> registry) {
    for (Map.Entry<ResourceLocation, SoundEvent> definition : EXNSoundEvents.getDefinitions().entrySet()) {
      registry.register(definition.getKey(), definition.getValue());
    }
  }
}
