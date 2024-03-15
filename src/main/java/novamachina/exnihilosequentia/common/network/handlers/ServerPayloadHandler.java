package novamachina.exnihilosequentia.common.network.handlers;

import net.neoforged.neoforge.network.handling.ConfigurationPayloadContext;
import novamachina.exnihilosequentia.common.network.configuration.OreConfigurationTask;
import novamachina.exnihilosequentia.common.network.payload.OreAckPayload;
import org.slf4j.Logger;

public class ServerPayloadHandler {
  private static final ServerPayloadHandler INSTANCE = new ServerPayloadHandler();
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ServerPayloadHandler.class);

  public static ServerPayloadHandler getInstance() {
    return INSTANCE;
  }

  public void handle(OreAckPayload payload, ConfigurationPayloadContext context) {
    log.info("Client received ore list");
    context.taskCompletedHandler().onTaskCompleted(OreConfigurationTask.TYPE);
  }
}
