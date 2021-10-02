package novamachina.exnihilosequentia.common.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;

@Mod.EventBusSubscriber(modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExNihiloSounds {
    private static final ResourceLocation location = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "pebble_throw");
    public static final SoundEvent PEBBLE_THROW = new SoundEvent(location).setRegistryName(location);

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().register(PEBBLE_THROW);
    }
}