package novamachina.exnihilosequentia.client.setup;

import com.mojang.logging.LogUtils;
import javax.annotation.Nonnull;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import novamachina.exnihilosequentia.client.render.BarrelRender;
import novamachina.exnihilosequentia.client.render.CrucibleRender;
import novamachina.exnihilosequentia.client.render.SieveRender;
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

@Mod.EventBusSubscriber(
    modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
    value = Dist.CLIENT,
    bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  private ClientSetup() {}

  public static void init(@Nonnull final FMLClientSetupEvent event) {
    logger.debug("Initializing client renderers");
    registerSieveRenderLayer();
    registerCrucibleRenderLayer();
    registerBarrelRenderLayer();

    ItemBlockRenderTypes.setRenderLayer(EXNBlocks.INFESTED_LEAVES.block(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(EXNBlocks.INFESTING_LEAVES.block(), RenderType.cutout());

    SieveRender.register(EXNBlockEntityTypes.SIEVE_ENTITY.getType());
    BarrelRender.register(EXNBlockEntityTypes.WOODEN_BARREL_ENTITY.getType());
    BarrelRender.register(EXNBlockEntityTypes.STONE_BARREL_ENTITY.getType());
    CrucibleRender.register(EXNBlockEntityTypes.FIRED_CRUCIBLE_ENTITY.getType());
    CrucibleRender.register(EXNBlockEntityTypes.WOODEN_CRUCIBLE_ENTITY.getType());
  }

  private static void registerBarrelRenderLayer() {
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.ACACIA_BARREL.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.BIRCH_BARREL.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.DARK_OAK_BARREL.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.JUNGLE_BARREL.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.MANGROVE_BARREL.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(EXNBlocks.OAK_BARREL.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.SPRUCE_BARREL.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.CRIMSON_BARREL.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.WARPED_BARREL.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.STONE_BARREL.block(), RenderType.cutoutMipped());
  }

  private static void registerCrucibleRenderLayer() {
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.ACACIA_CRUCIBLE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.BIRCH_CRUCIBLE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.DARK_OAK_CRUCIBLE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.JUNGLE_CRUCIBLE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.MANGROVE_CRUCIBLE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.OAK_CRUCIBLE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.SPRUCE_CRUCIBLE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.CRIMSON_CRUCIBLE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.WARPED_CRUCIBLE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.UNFIRED_CRUCIBLE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.FIRED_CRUCIBLE.block(), RenderType.cutoutMipped());
  }

  private static void registerSieveRenderLayer() {
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.ACACIA_SIEVE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.BIRCH_SIEVE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.DARK_OAK_SIEVE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.JUNGLE_SIEVE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.MANGROVE_SIEVE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(EXNBlocks.OAK_SIEVE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.SPRUCE_SIEVE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.CRIMSON_SIEVE.block(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        EXNBlocks.WARPED_SIEVE.block(), RenderType.cutoutMipped());
  }
}
