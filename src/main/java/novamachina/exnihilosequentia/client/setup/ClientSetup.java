package novamachina.exnihilosequentia.client.setup;

import javax.annotation.Nonnull;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.client.renderer.BarrelRender;
import novamachina.exnihilosequentia.client.renderer.CrucibleRender;
import novamachina.exnihilosequentia.client.renderer.SieveRender;
import novamachina.exnihilosequentia.client.renderer.item.properties.Holiday;
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;
import org.slf4j.Logger;

public class ClientSetup {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ClientSetup.class);

  private ClientSetup() {}

  public static void init(@Nonnull final FMLClientSetupEvent event) {
    log.debug("Initializing client renderers");

    SieveRender.register(EXNBlockEntityTypes.SIEVE_ENTITY.getType());
    BarrelRender.register(EXNBlockEntityTypes.WOODEN_BARREL_ENTITY.getType());
    BarrelRender.register(EXNBlockEntityTypes.STONE_BARREL_ENTITY.getType());
    CrucibleRender.register(EXNBlockEntityTypes.FIRED_CRUCIBLE_ENTITY.getType());
    CrucibleRender.register(EXNBlockEntityTypes.WOODEN_CRUCIBLE_ENTITY.getType());
  }
}
