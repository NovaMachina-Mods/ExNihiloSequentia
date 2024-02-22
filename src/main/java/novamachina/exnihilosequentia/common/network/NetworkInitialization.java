package novamachina.exnihilosequentia.common.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.OnGameConfigurationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.network.configuration.OreConfigurationTask;
import novamachina.exnihilosequentia.common.network.handlers.ClientPayloadHandler;
import novamachina.exnihilosequentia.common.network.handlers.ServerPayloadHandler;
import novamachina.exnihilosequentia.common.network.payload.OreAckPayload;
import novamachina.exnihilosequentia.common.network.payload.OreConfigurationPayload;

@Mod.EventBusSubscriber(modid = ExNihiloSequentia.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkInitialization {
  @SubscribeEvent
  private static void register(RegisterPayloadHandlerEvent event) {
    IPayloadRegistrar registrar =
        event.registrar(ExNihiloSequentia.MOD_ID).versioned("1.0.0").optional();
    registrar
        .configuration(
            OreConfigurationPayload.ID,
            OreConfigurationPayload::new,
            handler -> handler.client(ClientPayloadHandler.getInstance()::handle))
        .configuration(
            OreAckPayload.ID,
            OreAckPayload::new,
            handler -> handler.server(ServerPayloadHandler.getInstance()::handle));
  }

  @SubscribeEvent
  private static void configureModdedClient(OnGameConfigurationEvent event) {
    event.register(new OreConfigurationTask());
  }
}
