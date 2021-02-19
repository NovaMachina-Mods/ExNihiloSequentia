package novamachina.exnihilosequentia.client.setup;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import novamachina.exnihilosequentia.client.render.BarrelRender;
import novamachina.exnihilosequentia.client.render.CrucibleRender;
import novamachina.exnihilosequentia.client.render.SieveRender;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.ore.OreColor;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ClientSetup() {
    }

    public static void init(final FMLClientSetupEvent event) {
        logger.debug("Initializing client renderers");

        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.SIEVE_ACACIA.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.SIEVE_BIRCH.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.SIEVE_DARK_OAK.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.SIEVE_JUNGLE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.SIEVE_SPRUCE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.SIEVE_OAK.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.CRUCIBLE_UNFIRED.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.CRUCIBLE_FIRED.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.CRUCIBLE_ACACIA.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.CRUCIBLE_BIRCH.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.CRUCIBLE_JUNGLE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.CRUCIBLE_SPRUCE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.CRUCIBLE_OAK.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.BARREL_ACACIA.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.BARREL_BIRCH.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.BARREL_DARK_OAK.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.BARREL_JUNGLE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.BARREL_SPRUCE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.BARREL_OAK.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.INFESTED_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ExNihiloBlocks.INFESTING_LEAVES.get(), RenderType.getCutout());

        SieveRender.register();
        BarrelRender.register();
        CrucibleRender.register(ExNihiloTiles.CRUCIBLE_FIRED.get());
        CrucibleRender.register(ExNihiloTiles.CRUCIBLE_WOOD.get());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onColorHandlerEvent(ColorHandlerEvent.Item event) {
        logger.debug("Fired ColorHandlerEvent.Item event");

        for (EnumOre ore : EnumOre.values()) {
            if(ore.getChunkItem().isPresent()) {
                event.getItemColors().register(new OreColor(), ore.getChunkItem().get());
            } else {
                logger.warn("Missing ore chunk");
            }
            if(ore.getPieceItem().isPresent()) {
                event.getItemColors().register(new OreColor(), ore.getPieceItem().get());
            } else {
                logger.warn("Missing ore piece");
            }
            if (ore.shouldGenerateIngot()) {
                if(ore.getIngotRegistryItem().isPresent()) {
                    event.getItemColors().register(new OreColor(), ore.getIngotRegistryItem().get());
                } else {
                    logger.warn("Missing ore ingot");
                }
            }
        }
    }
}
