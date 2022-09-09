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
import novamachina.exnihilosequentia.common.init.ExNihiloBlockEntities;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
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

    ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.INFESTED_LEAVES.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.INFESTING_LEAVES.get(), RenderType.cutout());

    SieveRender.register(ExNihiloBlockEntities.SIEVE_ENTITY.get());
    BarrelRender.register(ExNihiloBlockEntities.WOODEN_BARREL_ENTITY.get());
    BarrelRender.register(ExNihiloBlockEntities.STONE_BARREL_ENTITY.get());
    CrucibleRender.register(ExNihiloBlockEntities.FIRED_CURICLBE_ENTITY.get());
    CrucibleRender.register(ExNihiloBlockEntities.WOODEN_CRUCIBLE_ENTITY.get());
  }

  private static void registerBarrelRenderLayer() {
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.BARREL_ACACIA.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.BARREL_BIRCH.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.BARREL_DARK_OAK.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.BARREL_JUNGLE.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.BARREL_OAK.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.BARREL_SPRUCE.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.BARREL_CRIMSON.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.BARREL_WARPED.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.BARREL_STONE.get(), RenderType.cutoutMipped());
  }

  private static void registerCrucibleRenderLayer() {
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.CRUCIBLE_ACACIA.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.CRUCIBLE_BIRCH.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.CRUCIBLE_JUNGLE.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.CRUCIBLE_OAK.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.CRUCIBLE_SPRUCE.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.CRUCIBLE_CRIMSON.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.CRUCIBLE_WARPED.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.CRUCIBLE_UNFIRED.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.CRUCIBLE_FIRED.get(), RenderType.cutoutMipped());
  }

  private static void registerSieveRenderLayer() {
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.SIEVE_ACACIA.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.SIEVE_BIRCH.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.SIEVE_DARK_OAK.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.SIEVE_JUNGLE.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(ExNihiloBlocks.SIEVE_OAK.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.SIEVE_SPRUCE.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.SIEVE_CRIMSON.get(), RenderType.cutoutMipped());
    ItemBlockRenderTypes.setRenderLayer(
        ExNihiloBlocks.SIEVE_WARPED.get(), RenderType.cutoutMipped());
  }
}
