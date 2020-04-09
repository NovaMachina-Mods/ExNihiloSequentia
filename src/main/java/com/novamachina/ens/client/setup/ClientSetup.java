package com.novamachina.ens.client.setup;

import com.novamachina.ens.common.utility.Constants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Constants.ModInfo.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup
{
    public static void init(final FMLClientSetupEvent event)
    {
    }
}
