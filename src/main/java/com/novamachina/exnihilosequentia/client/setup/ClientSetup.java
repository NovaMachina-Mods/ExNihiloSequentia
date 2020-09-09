package com.novamachina.exnihilosequentia.client.setup;

import com.novamachina.exnihilosequentia.client.render.BarrelRender;
import com.novamachina.exnihilosequentia.client.render.CrucibleRender;
import com.novamachina.exnihilosequentia.client.render.SieveRender;
import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import com.novamachina.exnihilosequentia.common.init.ModTiles;
import com.novamachina.exnihilosequentia.common.item.ore.EnumModdedOre;
import com.novamachina.exnihilosequentia.common.item.ore.EnumOre;
import com.novamachina.exnihilosequentia.common.item.ore.OreColor;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Constants.ModIds.EX_NIHILO_SEQUENTIA, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
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
        for (EnumOre ore : EnumOre.values()) {
            event.getItemColors().register(new OreColor(), ore.getChunkItem().get());
            event.getItemColors().register(new OreColor(), ore.getPieceItem().get());
        }

        for (EnumModdedOre ore : EnumModdedOre.values()) {
            event.getItemColors().register(new OreColor(), ore.getChunkItem().get());
            event.getItemColors().register(new OreColor(), ore.getPieceItem().get());
            event.getItemColors().register(new OreColor(), ore.getIngotItem().get());
        }
    }
}
