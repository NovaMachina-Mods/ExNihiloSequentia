package novamachina.exnihilosequentia;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
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
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.client.setup.ClientSetup;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.loot.modifier.UseCrookModifier;
import novamachina.exnihilosequentia.common.loot.modifier.UseHammerModifier;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;

@Mod(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
public class ExNihiloSequentia {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    @Nonnull private static final String MODID = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA;
    public ExNihiloSequentia() {
        logger.debug("Starting Ex Nihilo: Sequentia");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));
        ExNihiloInitialization.init(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ExNihiloInitialization::setupNonTagBasedRegistries);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ExNihiloInitialization::registerTOP);
    }

    @EventBusSubscriber(modid = MODID, bus = Bus.MOD)
    public static class EventHandlers {
        private EventHandlers() {
        }

        @SubscribeEvent
        public static void registerModifierSerializers(
            @Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            logger.debug("Registering Loot Modifiers");
            event.getRegistry()
                .register(new UseHammerModifier.Serializer()
                    .setRegistryName(MODID, "use_hammer"));
            event.getRegistry()
                    .register(new UseCrookModifier.Serializer()
                            .setRegistryName(MODID, "use_crook"));
        }

        @SubscribeEvent
        public static void registerRecipeTypes(RegistryEvent.Register<Block> ignored) {
            CompostRecipe.RECIPE_TYPE = register("compost");
            CrookRecipe.RECIPE_TYPE = register("crook");
            CrucibleRecipe.RECIPE_TYPE = register("crucible");
            FluidItemRecipe.RECIPE_TYPE = register("fluid_item");
            FluidOnTopRecipe.RECIPE_TYPE = register("fluid_on_top");
            FluidTransformRecipe.RECIPE_TYPE = register("fluid_transform");
            HammerRecipe.RECIPE_TYPE = register("hammer");
            HeatRecipe.RECIPE_TYPE = register("heat");
            SieveRecipe.RECIPE_TYPE = register("sieve");
        }

        private static <T extends Recipe<?>> RecipeType<T> register(String path)
        {
            ResourceLocation name = new ResourceLocation(MODID, path);
            return Registry.register(Registry.RECIPE_TYPE, name, new RecipeType<T>()
            {
                @Override
                public String toString()
                {
                    return name.toString();
                }
            });
        }
    }
}
