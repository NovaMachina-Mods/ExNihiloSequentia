package com.novamachina.ens.common.setup;

import com.novamachina.ens.common.utility.Constants.ModInfo;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID, bus = Bus.FORGE)
public class ModSetup {

    public static void init(final FMLCommonSetupEvent event) {
        //        Networking.registerMessages();
    }
}
