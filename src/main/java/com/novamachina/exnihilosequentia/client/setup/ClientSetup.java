package com.novamachina.exnihilosequentia.client.setup;

import com.novamachina.exnihilosequentia.client.render.SieveRender;
import com.novamachina.exnihilosequentia.common.item.ore.OreColor;
import com.novamachina.exnihilosequentia.common.item.ore.OreItem;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Constants.ModInfo.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(ModBlocks.SIEVE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup
            .setRenderLayer(ModBlocks.CRUCIBLE_UNFIRED.get(), RenderType.getCutoutMipped());
        RenderTypeLookup
            .setRenderLayer(ModBlocks.CRUCIBLE_FIRED.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.INFESTED_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.INFESTING_LEAVES.get(), RenderType.getCutout());

        SieveRender.register();
    }

    @SubscribeEvent
    public static void onColorHandlerEvent(ColorHandlerEvent.Item event) {
        for (RegistryObject<OreItem> item : ModItems.chunkMap.values()) {
            event.getItemColors().register(new OreColor(), item.get());
        }
        for (RegistryObject<OreItem> item : ModItems.pieceMap.values()) {
            event.getItemColors().register(new OreColor(), item.get());
        }
    }
}
