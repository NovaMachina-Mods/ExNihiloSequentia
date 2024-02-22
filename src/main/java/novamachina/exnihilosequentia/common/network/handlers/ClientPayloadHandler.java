package novamachina.exnihilosequentia.common.network.handlers;

import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.ConfigurationPayloadContext;
import novamachina.exnihilosequentia.common.network.payload.OreAckPayload;
import novamachina.exnihilosequentia.common.network.payload.OreConfigurationPayload;
import novamachina.exnihilosequentia.world.item.Ore;
import org.slf4j.Logger;

public class ClientPayloadHandler {
  private static final ClientPayloadHandler INSTANCE = new ClientPayloadHandler();
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ClientPayloadHandler.class);

  public static ClientPayloadHandler getInstance() {
    return INSTANCE;
  }

  public void handle(OreConfigurationPayload payload, ConfigurationPayloadContext context) {
    log.info("Received ore list");
    context
        .workHandler()
        .submitAsync(
            () -> {
              Ore.updateEnabledOres(payload);
            })
        .exceptionally(
            e -> {
              context
                  .packetHandler()
                  .disconnect(
                      Component.literal(
                          "Connection closed - [Ex Nihilo: Sequentia] Failed to synchronize ore list from server."));
              return null;
            })
        .thenAccept(
            v -> {
              context.replyHandler().send(new OreAckPayload());
            });
  }
}
