package novamachina.exnihilosequentia.client.setup;

import java.util.Calendar;
import javax.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.client.render.BarrelRender;
import novamachina.exnihilosequentia.client.render.CrucibleRender;
import novamachina.exnihilosequentia.client.render.SieveRender;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;

@Slf4j
@Mod.EventBusSubscriber(
    modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
    value = Dist.CLIENT,
    bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

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
        new ResourceLocation(ExNihiloSequentia.MOD_ID, "halloween"),
        (itemStack, clientLevel, livingEntity, i) -> {
          Calendar calendar = Calendar.getInstance();
          if (calendar.get(Calendar.MONTH) + 1 == 10) {
            return 1.0F;
          }
          return 0.0F;
        });
  }
}
