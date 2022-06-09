package novamachina.exnihilosequentia.common.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;

public class ExNihiloSounds {

  private static final DeferredRegister<SoundEvent> SOUND_EVENTS =
      DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ModIds.EX_NIHILO_SEQUENTIA);
  public static final RegistryObject<SoundEvent> PEBBLE_THROW =
      SOUND_EVENTS.register(
          "pebble_throw",
          () ->
              new SoundEvent(
                  new ResourceLocation(
                      ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "pebble_throw")));

  private ExNihiloSounds() {}

  public static void init(IEventBus modEventBus) {
    SOUND_EVENTS.register(modEventBus);
  }
}
