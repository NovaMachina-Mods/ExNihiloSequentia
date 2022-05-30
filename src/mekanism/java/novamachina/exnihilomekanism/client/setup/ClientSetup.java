package novamachina.exnihilomekanism.client.setup;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import novamachina.exnihilomekanism.common.init.ExNihiloMekanismItems;
import novamachina.exnihilomekanism.common.utility.ExNihiloMekanismConstants;
import novamachina.exnihilosequentia.common.item.OreItem;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.item.ore.OreColor;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(modid = ExNihiloMekanismConstants.ModIds.EX_NIHILO_MEKANISM, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  private ClientSetup() {
  }

  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void onColorHandlerEvent(@Nonnull final ColorHandlerEvent.Item event) {
    logger.debug("Fired ColorHandlerEvent.Item event");

    registerOreColors(ExNihiloMekanismItems.OSMIUM, event);
  }

  private static void registerOreColors(Ore ore, ColorHandlerEvent.Item event) {
    @Nullable final OreItem chunkRegistryObject = ore.getRawOreItem();
    if (chunkRegistryObject != null) {
      event.getItemColors().register(new OreColor(), ore.getRawOreItem());
    } else {
      logger.warn("Missing ore chunk");
    }
    @Nullable final OreItem pieceRegistryObject = ore.getPieceItem();
    if (pieceRegistryObject != null) {
      event.getItemColors().register(new OreColor(), ore.getPieceItem());
    } else {
      logger.warn("Missing ore piece");
    }
    if (ore.getIngotItem() != null) {
      @Nullable final Item ingotRegistryObject = ore.getIngotItem();
      if (ingotRegistryObject != null) {
        event.getItemColors().register(new OreColor(), ore.getIngotItem());
      } else {
        logger.warn("Missing ore ingot");
      }
    }
  }
}
