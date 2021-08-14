package novamachina.exnihilosequentia;

import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLPaths;
import novamachina.exnihilosequentia.client.setup.ClientSetup;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import novamachina.exnihilosequentia.common.item.tools.crook.CrookBaseItem;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.loot.modifier.UseCrookModifier;
import novamachina.exnihilosequentia.common.loot.modifier.UseHammerModifier;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import java.util.Random;

@Mod(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
public class ExNihiloSequentia {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public ExNihiloSequentia() {
        logger.debug("Starting Ex Nihilo: Sequentia");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "-common.toml"));
        ExNihiloInitialization.init(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ExNihiloInitialization::setupNonTagBasedRegistries);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ExNihiloInitialization::registerTOP);
        MinecraftForge.EVENT_BUS.addListener(EventHandlers::onBlockBreak);
        MinecraftForge.EVENT_BUS.addListener(EventHandlers::blockBreakSpeed);
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

        public static void onBlockBreak(BlockEvent.BreakEvent event) {
            if (!event.getWorld().isClientSide() && !event.getPlayer().isCreative() && event.getState().getBlock() == Blocks.STONE) {
                ItemStack held = event.getPlayer().getMainHandItem();
                if (!(event.getPlayer() instanceof FakePlayer) && held.isEmpty()) {
                    int j = new Random().nextInt(3);
                    ItemStack stack = EnumPebbleType.STONE.getRegistryObject().get().getDefaultInstance();
                    for (int i = 0; i <= j; i++) {
                        event.getWorld().addFreshEntity(new ItemEntity((World) event.getWorld(), event.getPos().getX() + .5, event.getPos().getY() + .5, event.getPos().getZ() + .5, stack));
                    }
                } else if (!(event.getPlayer() instanceof FakePlayer) && held.getItem() instanceof CrookBaseItem) {
                    int j = new Random().nextInt(3) + 3;
                    ItemStack stack = EnumPebbleType.STONE.getRegistryObject().get().getDefaultInstance();
                    for (int i = 0; i <= j; i++) {
                        event.getWorld().addFreshEntity(new ItemEntity((World) event.getWorld(), event.getPos().getX() + .5, event.getPos().getY() + .5, event.getPos().getZ() + .5, stack));
                    }
                }
            }
        }

        public static void blockBreakSpeed(PlayerEvent.BreakSpeed event) {
            if (event.getState().getBlock() == Blocks.STONE) {
                double speed = 5D;
                if (event.getPlayer().getMainHandItem().isEmpty()) {
                    event.setNewSpeed((float) speed);
                } else if (event.getPlayer().getMainHandItem().getItem() instanceof CrookBaseItem) {
                    speed = speed * 3;
                    event.setNewSpeed((float) speed);
                }
            }
        }
    }
}
