package com.novamachina.exnihilosequentia;

import com.novamachina.exnihilosequentia.client.setup.ClientSetup;
import com.novamachina.exnihilosequentia.common.loot.modifier.UseHammerModifier;
import com.novamachina.exnihilosequentia.common.setup.ModInitialization;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.Constants.ModInfo;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import javax.annotation.Nonnull;
import java.nio.file.Path;

@Mod(Constants.ModInfo.MOD_ID)
public class ExNihiloSequentia {

    public ExNihiloSequentia() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
//        Path p = FMLPaths.getOrCreateGameRelativePath(FMLPaths.CONFIGDIR.get().resolve("Ex Nihilo: Sequentia Json folder"), "exnihilosequentia");

        ModInitialization.init(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModInitialization::setupNonTagBasedRegistries);
    }

    @EventBusSubscriber(modid = ModInfo.MOD_ID, bus = Bus.MOD)
    public static class EventHandlers {

        @SubscribeEvent
        public static void registerModifierSerializers(
            @Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            LogUtil.info("Register Modifiers");
            event.getRegistry()
                .register(new UseHammerModifier.Serializer()
                    .setRegistryName(ModInfo.MOD_ID, "use_hammer"));
        }
    }

    @EventBusSubscriber(modid = ModInfo.MOD_ID, bus = Bus.FORGE)
    public static class PlayerMessages {
        @SubscribeEvent
        public static void sendDevelopmentMessage(PlayerEvent.PlayerLoggedInEvent event) {
            event.getPlayer().sendMessage(new TranslationTextComponent("message.new_mod"));
            event.getPlayer().sendMessage(new TranslationTextComponent("message.issues"));
            event.getPlayer().sendMessage(new TranslationTextComponent("message.issues_link")
                .setStyle(new Style().setUnderlined(true)
                    .setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/NovaMachina/ExNihiloSequentia/issues"))));
        }
    }
}
