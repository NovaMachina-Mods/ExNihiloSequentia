package novamachina.exnihilotinkers.client;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import novamachina.exnihilosequentia.client.render.BarrelRender;
import novamachina.exnihilosequentia.client.render.CrucibleRender;
import novamachina.exnihilosequentia.client.render.SieveRender;
import novamachina.exnihilosequentia.common.item.OreItem;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.item.ore.OreColor;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilotinkers.common.init.EXNTinkersBlockEntites;
import novamachina.exnihilotinkers.common.init.EXNTinkersBlocks;
import novamachina.exnihilotinkers.common.init.EXNTinkersItems;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(modid = EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  private ClientSetup() {
  }

  public static void init(final FMLClientSetupEvent event) {
    logger.debug("Initializing client renderers");

    ItemBlockRenderTypes.setRenderLayer(EXNTinkersBlocks.SIEVE_BLOODSHROOM.get(),
        RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(EXNTinkersBlocks.SIEVE_GREENHEART.get(),
        RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(EXNTinkersBlocks.SIEVE_SKYROOT.get(),
        RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(EXNTinkersBlocks.CRUCIBLE_BLOODSHROOM.get(),
        RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(EXNTinkersBlocks.CRUCIBLE_GREENHEART.get(),
        RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(EXNTinkersBlocks.CRUCIBLE_SKYROOT.get(),
        RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(EXNTinkersBlocks.BARREL_BLOODSHROOM.get(),
        RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(EXNTinkersBlocks.BARREL_GREENHEART.get(),
        RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(EXNTinkersBlocks.BARREL_SKYROOT.get(),
        RenderType.cutoutMipped());

    SieveRender.register(EXNTinkersBlockEntites.TINKERS_SIEVES.get());
    BarrelRender.register(EXNTinkersBlockEntites.TINKERS_BARRELS.get());
    CrucibleRender.register(EXNTinkersBlockEntites.TINKERS_CRUCIBLES.get());

  }

  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void onColorHandlerEvent(@Nonnull final ColorHandlerEvent.Item event) {
    logger.debug("Fired ColorHandlerEvent.Item event");

    registerOreColors(EXNTinkersItems.COBALT, event);
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
