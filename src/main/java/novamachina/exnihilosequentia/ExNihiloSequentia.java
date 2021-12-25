package novamachina.exnihilosequentia;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import novamachina.exnihilosequentia.client.setup.ClientSetup;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.loot.modifier.UseCrookModifier;
import novamachina.exnihilosequentia.common.loot.modifier.UseHammerModifier;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

@Mod(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
public class ExNihiloSequentia {
    private static final ExNihiloLogger logger = new ExNihiloLogger(ExNihiloSequentia.class);

    public ExNihiloSequentia() {
        logger.debug("Starting Ex Nihilo: Sequentia");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "-common.toml"));
        ExNihiloInitialization.init(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ExNihiloInitialization::setupNonTagBasedRegistries);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ExNihiloInitialization::registerTOP);
    }

    @EventBusSubscriber(modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, bus = Bus.MOD)
    public static class EventHandlers {
        private EventHandlers() {
        }

        @SubscribeEvent
        public static void registerModifierSerializers(
            @Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            logger.debug("Registering Loot Modifiers");
            event.getRegistry()
                .register(new UseHammerModifier.Serializer()
                    .setRegistryName(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "use_hammer"));
            event.getRegistry()
                    .register(new UseCrookModifier.Serializer()
                            .setRegistryName(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "use_crook"));
        }
    }
}
