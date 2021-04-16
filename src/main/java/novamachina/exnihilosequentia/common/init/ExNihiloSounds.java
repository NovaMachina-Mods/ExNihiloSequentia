package novamachina.exnihilosequentia.common.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloSounds {
    private static final ResourceLocation location = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "pebble_throw");
    public static final SoundEvent PEBBLE_THROW = new SoundEvent(location).setRegistryName(location);

    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().register(PEBBLE_THROW);
    }
}