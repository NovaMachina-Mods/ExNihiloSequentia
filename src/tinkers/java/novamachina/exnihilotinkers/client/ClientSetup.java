package novamachina.exnihilotinkers.client;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import novamachina.exnihilosequentia.client.render.BarrelRender;
import novamachina.exnihilosequentia.client.render.CrucibleRender;
import novamachina.exnihilosequentia.client.render.SieveRender;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilotinkers.common.init.EXNTinkersBlockEntites;
import novamachina.exnihilotinkers.common.init.EXNTinkersBlocks;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;

@Mod.EventBusSubscriber(modid = EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

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
}
