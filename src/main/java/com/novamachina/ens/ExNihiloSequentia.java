package com.novamachina.ens;

import com.novamachina.ens.client.proxy.ClientProxy;
import com.novamachina.ens.client.setup.ClientSetup;
import com.novamachina.ens.common.proxy.IProxy;
import com.novamachina.ens.common.proxy.ServerProxy;
import com.novamachina.ens.common.setup.ModSetup;
import com.novamachina.ens.common.setup.Registration;
import com.novamachina.ens.common.utility.Config;
import com.novamachina.ens.common.utility.Constants;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.ModInfo.MOD_ID)
public class ExNihiloSequentia {
  public static final IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

  public ExNihiloSequentia() {
    ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

    Registration.init();

    FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
  }
}
