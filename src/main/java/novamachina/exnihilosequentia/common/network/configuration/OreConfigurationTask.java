package novamachina.exnihilosequentia.common.network.configuration;

import java.util.function.Consumer;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.network.ConfigurationTask;
import net.neoforged.neoforge.network.configuration.ICustomConfigurationTask;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.network.payload.OreConfigurationPayload;
import novamachina.exnihilosequentia.world.item.Ore;
import org.slf4j.Logger;

public record OreConfigurationTask() implements ICustomConfigurationTask {

  public static final ConfigurationTask.Type TYPE =
      new ConfigurationTask.Type(ExNihiloSequentia.makeId("ore_list"));
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(OreConfigurationTask.class);

  @Override
  public void run(Consumer<CustomPacketPayload> sender) {
    log.info("Sending ore list to client");
    OreConfigurationPayload payload = new OreConfigurationPayload(Ore.getEnabledOres());
    sender.accept(payload);
  }

  @Override
  public Type type() {
    return TYPE;
  }
}
