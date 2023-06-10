package novamachina.exnihilosequentia;

import static novamachina.exnihilosequentia.ExNihiloSequentia.MOD_ID;

import com.mojang.logging.LogUtils;
import javax.annotation.Nonnull;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import novamachina.exnihilosequentia.client.setup.ClientSetup;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.init.*;

@Mod(MOD_ID)
public class ExNihiloSequentia {

  public static final String MOD_ID = "exnihilosequentia";

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  public ExNihiloSequentia() {
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    Config.loadConfig(
        Config.COMMON_CONFIG,
        FMLPaths.CONFIGDIR
            .get()
            .resolve(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "-common.toml"));

    logger.debug("Starting Ex Nihilo: Sequentia");

    ExNihiloInitialization.init(FMLJavaModLoadingContext.get().getModEventBus());
    FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
    FMLJavaModLoadingContext.get()
        .getModEventBus()
        .addListener(ExNihiloInitialization::setupNonTagBasedRegistries);
    FMLJavaModLoadingContext.get()
        .getModEventBus()
        .addListener(ExNihiloInitialization::registerTOP);

    FMLJavaModLoadingContext.get()
        .getModEventBus()
        .addListener(
            (RegisterEvent event) -> {
              if (event.getRegistryKey().equals(Registry.BLOCK_REGISTRY)) {
                InitBlocks.init(ForgeRegistries.BLOCKS);
              }
              if (event.getRegistryKey().equals(Registry.BLOCK_ENTITY_TYPE_REGISTRY)) {
                InitBlockEntityTypes.init(ForgeRegistries.BLOCK_ENTITY_TYPES);
              }
              if (event.getRegistryKey().equals(Registry.FLUID_REGISTRY)) {
                InitFluidTypes.init(ForgeRegistries.FLUID_TYPES.get());
                InitFluids.init(ForgeRegistries.FLUIDS);
              }
              if (event.getRegistryKey().equals(Registry.ITEM_REGISTRY)) {
                InitItems.init(ForgeRegistries.ITEMS);
              }
              if (event.getRegistryKey().equals(Registry.RECIPE_SERIALIZER_REGISTRY)) {
                InitRecipeSerializers.init(ForgeRegistries.RECIPE_SERIALIZERS);
              }
              if (event.getRegistryKey().equals(Registry.RECIPE_TYPE_REGISTRY)) {
                InitRecipeTypes.init(ForgeRegistries.RECIPE_TYPES);
              }
              if (event.getRegistryKey().equals(Registry.SOUND_EVENT_REGISTRY)) {
                InitSoundEvents.init(ForgeRegistries.SOUND_EVENTS);
              }
              if (event.getRegistryKey().equals(Registry.CUSTOM_STAT_REGISTRY)) {
                InitStats.init();
              }
            });
  }

  public static ResourceLocation makeId(String id) {
    return new ResourceLocation(MOD_ID, id);
  }
}
