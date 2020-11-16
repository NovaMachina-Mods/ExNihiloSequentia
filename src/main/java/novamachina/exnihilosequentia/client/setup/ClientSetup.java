package novamachina.exnihilosequentia.client.setup;

import novamachina.exnihilosequentia.client.render.BarrelRender;
import novamachina.exnihilosequentia.client.render.CrucibleRender;
import novamachina.exnihilosequentia.client.render.SieveRender;
import novamachina.exnihilosequentia.common.init.ModBlocks;
import novamachina.exnihilosequentia.common.init.ModTiles;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.ore.OreColor;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(modid = Constants.ModIds.EX_NIHILO_SEQUENTIA, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public static void init(final FMLClientSetupEvent event) {
        logger.debug("Initializing client renderers");

        RenderTypeLookup.setRenderLayer(ModBlocks.SIEVE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.CRUCIBLE_UNFIRED.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.CRUCIBLE_FIRED.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.CRUCIBLE_WOOD.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.BARREL_WOOD.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.INFESTED_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.INFESTING_LEAVES.get(), RenderType.getCutout());

        SieveRender.register();
        BarrelRender.register();
        CrucibleRender.register(ModTiles.CRUCIBLE_FIRED.get());
        CrucibleRender.register(ModTiles.CRUCIBLE_WOOD.get());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onColorHandlerEvent(ColorHandlerEvent.Item event) {
        logger.debug("Fired ColorHandlerEvent.Item event");

        for (EnumOre ore : EnumOre.values()) {
            event.getItemColors().register(new OreColor(), ore.getChunkItem().get());
            event.getItemColors().register(new OreColor(), ore.getPieceItem().get());
            if(!ore.isVanilla()) {
                event.getItemColors().register(new OreColor(), ore.getIngotItem().get());
            }
        }
    }
}
