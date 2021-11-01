package novamachina.exnihilosequentia.client.setup;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.client.render.BarrelRender;
import novamachina.exnihilosequentia.client.render.CrucibleRender;
import novamachina.exnihilosequentia.client.render.SieveRender;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.ore.OreColor;
import novamachina.exnihilosequentia.api.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ClientSetup() {
    }

    public static void init(final FMLClientSetupEvent event) {
        logger.debug("Initializing client renderers");
        registerSieveRenderLayer();
        registerCrucibleRenderLayer();
        registerBarrelRenderLayer();

        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.INFESTED_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.INFESTING_LEAVES.get(), RenderType.cutout());

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
            if(ore.getPieceItem().isPresent()) {
                event.getItemColors().register(new OreColor(), ore.getPieceItem().get());
            } else {
                logger.warn("Missing ore piece");
            }
            if (!ore.isVanilla()) {
                if (ore.getRawOreItem().isPresent()) {
                    event.getItemColors().register(new OreColor(), ore.getRawOreItem().get());
                } else {
                    logger.warn("Missing ore chunk");
                }
                if (ore.shouldGenerateIngot()) {
                    if (ore.getIngotRegistryItem().isPresent()) {
                        event.getItemColors().register(new OreColor(), ore.getIngotRegistryItem().get());
                    } else {
                        logger.warn("Missing ore ingot");
                    }
                }
            }
        }
    }

    private static void registerBarrelRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.ACACIA_BARREL.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BIRCH_BARREL.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.DARK_OAK_BARREL.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.JUNGLE_BARREL.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.OAK_BARREL.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.SPRUCE_BARREL.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRIMSON_BARREL.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.WARPED_BARREL.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.STONE_BARREL.get(), RenderType.cutoutMipped());
    }

    private static void registerCrucibleRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.ACACIA_CRUCIBLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BIRCH_CRUCIBLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.DARK_OAK_CRUCIBLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.JUNGLE_CRUCIBLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.OAK_CRUCIBLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.SPRUCE_CRUCIBLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRIMSON_CRUCIBLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.WARPED_CRUCIBLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.UNFIRED_CRUCIBLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.FIRED_CRUCIBLE.get(), RenderType.cutoutMipped());
    }

    private static void registerSieveRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.ACACIA_SIEVE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BIRCH_SIEVE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.DARK_OAK_SIEVE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.JUNGLE_SIEVE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.OAK_SIEVE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.SPRUCE_SIEVE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRIMSON_SIEVE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.WARPED_SIEVE.get(), RenderType.cutoutMipped());
    }
}
