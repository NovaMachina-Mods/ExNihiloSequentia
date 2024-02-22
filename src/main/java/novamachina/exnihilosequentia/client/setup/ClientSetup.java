package novamachina.exnihilosequentia.client.setup;

import java.util.Calendar;
import javax.annotation.Nonnull;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.client.render.BarrelRender;
import novamachina.exnihilosequentia.client.render.CrucibleRender;
import novamachina.exnihilosequentia.client.render.SieveRender;
import novamachina.exnihilosequentia.world.item.EXNItems;
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

    ItemProperties.register(
        EXNItems.CROOK_WOOD.asItem(),
        new ResourceLocation(ExNihiloSequentia.MOD_ID, "holiday"),
        (itemStack, clientLevel, livingEntity, i) -> {
          Calendar calendar = Calendar.getInstance();
          if (calendar.get(Calendar.MONTH) + 1 == 10) {
            return 0.10F;
          } else if (calendar.get(Calendar.MONTH) + 1 == 12
              && calendar.get(Calendar.DAY_OF_MONTH) >= 24
              && calendar.get(Calendar.DAY_OF_MONTH) <= 26) {
            return 0.12F;
          }
          return 0.0F;
        });
  }
}
