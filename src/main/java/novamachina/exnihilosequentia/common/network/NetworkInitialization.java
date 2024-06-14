package novamachina.exnihilosequentia.common.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterConfigurationTasksEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.network.configuration.OreConfigurationTask;
import novamachina.exnihilosequentia.common.network.handlers.ClientPayloadHandler;
import novamachina.exnihilosequentia.common.network.handlers.ServerPayloadHandler;
import novamachina.exnihilosequentia.common.network.payload.OreAckPayload;
import novamachina.exnihilosequentia.common.network.payload.OreConfigurationPayload;

@EventBusSubscriber(modid = ExNihiloSequentia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkInitialization {
  @SubscribeEvent
  private static void register(RegisterConfigurationTasksEvent event) {
    event.register(new OreConfigurationTask());
  }

  @SubscribeEvent
  private static void register(RegisterPayloadHandlersEvent event) {
    PayloadRegistrar registrar = event.registrar("1").optional();
    registrar
        .configurationToClient(OreConfigurationPayload.TYPE, OreConfigurationPayload.STREAM_CODEC, ClientPayloadHandler::handle)
        .configurationToServer(OreAckPayload.TYPE, OreAckPayload.STREAM_CODEC, ServerPayloadHandler::handle);
  }
}
