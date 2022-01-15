package novamachina.exnihilosequentia.client.setup;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.client.render.BarrelRender;
import novamachina.exnihilosequentia.client.render.CrucibleRender;
import novamachina.exnihilosequentia.client.render.SieveRender;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.ore.OreColor;
import novamachina.exnihilosequentia.common.item.ore.OreItem;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ClientSetup() {
    }

    public static void init(@Nonnull final FMLClientSetupEvent event) {
        logger.debug("Initializing client renderers");
        registerSieveRenderLayer();
        registerCrucibleRenderLayer();
        registerBarrelRenderLayer();

        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.INFESTED_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.INFESTING_LEAVES.get(), RenderType.cutout());

        SieveRender.register(ExNihiloTiles.SIEVE.get());
        BarrelRender.register(ExNihiloTiles.BARREL_WOOD.get());
        BarrelRender.register(ExNihiloTiles.BARREL_STONE.get());
        CrucibleRender.register(ExNihiloTiles.CRUCIBLE_FIRED.get());
        CrucibleRender.register(ExNihiloTiles.CRUCIBLE_WOOD.get());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onColorHandlerEvent(@Nonnull final ColorHandlerEvent.Item event) {
        logger.debug("Fired ColorHandlerEvent.Item event");

        for (@Nonnull final EnumOre ore : EnumOre.values()) {
            @Nullable final RegistryObject<OreItem> chunkRegistryObject = ore.getChunkItem();
            if(chunkRegistryObject != null && chunkRegistryObject.isPresent()) {
                event.getItemColors().register(new OreColor(), ore.getChunkItem().get());
            } else {
                logger.warn("Missing ore chunk");
            }
            @Nullable final RegistryObject<OreItem> pieceRegistryObject = ore.getPieceItem();
            if(pieceRegistryObject != null && pieceRegistryObject.isPresent()) {
                event.getItemColors().register(new OreColor(), ore.getPieceItem().get());
            } else {
                logger.warn("Missing ore piece");
            }
            if (ore.shouldGenerateIngot()) {
                @Nullable final RegistryObject<OreItem> ingotRegistryObject = ore.getIngotRegistryItem();
                if (ingotRegistryObject != null && ingotRegistryObject.isPresent()) {
                    event.getItemColors().register(new OreColor(), ore.getIngotRegistryItem().get());
                } else {
                    logger.warn("Missing ore ingot");
                }
            }
        }
    }

    private static void registerBarrelRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BARREL_ACACIA.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BARREL_BIRCH.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BARREL_DARK_OAK.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BARREL_JUNGLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BARREL_OAK.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BARREL_SPRUCE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BARREL_CRIMSON.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BARREL_WARPED.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BARREL_STONE.get(), RenderType.cutoutMipped());
    }

    private static void registerCrucibleRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRUCIBLE_ACACIA.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRUCIBLE_BIRCH.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRUCIBLE_JUNGLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRUCIBLE_OAK.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRUCIBLE_SPRUCE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRUCIBLE_CRIMSON.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRUCIBLE_WARPED.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRUCIBLE_UNFIRED.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.CRUCIBLE_FIRED.get(), RenderType.cutoutMipped());
    }

    private static void registerSieveRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.SIEVE_ACACIA.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.SIEVE_BIRCH.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.SIEVE_DARK_OAK.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.SIEVE_JUNGLE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.SIEVE_OAK.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.SIEVE_SPRUCE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.SIEVE_CRIMSON.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.SIEVE_WARPED.get(), RenderType.cutoutMipped());
    }
}
