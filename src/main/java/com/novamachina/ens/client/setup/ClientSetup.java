package com.novamachina.ens.client.setup;

import com.novamachina.ens.common.item.ore.OreColor;
import com.novamachina.ens.common.item.ore.OreItem;
import com.novamachina.ens.common.setup.Registration;
import com.novamachina.ens.common.utility.Constants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Constants.ModInfo.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void onColorHandlerEvent(ColorHandlerEvent.Item event) {
        for (RegistryObject<OreItem> item : Registration.chunkMap.values()) {
            event.getItemColors().register(new OreColor(), item.get());
        }
        for (RegistryObject<OreItem> item : Registration.pieceMap.values()) {
            event.getItemColors().register(new OreColor(), item.get());
        }
    }
}
