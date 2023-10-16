package novamachina.exnihilosequentia;

import static novamachina.exnihilosequentia.ExNihiloSequentia.MOD_ID;

import net.minecraft.SharedConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import novamachina.exnihilosequentia.client.setup.ClientSetup;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.core.registries.InitBlockEntityTypes;
import novamachina.exnihilosequentia.core.registries.InitBlocks;
import novamachina.exnihilosequentia.core.registries.InitCreativeTabs;
import novamachina.exnihilosequentia.core.registries.InitFluidTypes;
import novamachina.exnihilosequentia.core.registries.InitFluids;
import novamachina.exnihilosequentia.core.registries.InitItems;
import novamachina.exnihilosequentia.core.registries.InitLootModifiers;
import novamachina.exnihilosequentia.core.registries.InitRecipeSerializers;
import novamachina.exnihilosequentia.core.registries.InitRecipeTypes;
import novamachina.exnihilosequentia.core.registries.InitSoundEvents;
import novamachina.exnihilosequentia.core.registries.InitStats;
import novamachina.exnihilosequentia.init.ExNihiloInitialization;
import novamachina.novacore.bootstrap.ForgeBlockEntityTypeRegistry;
import novamachina.novacore.bootstrap.ForgeBlockRegistry;
import novamachina.novacore.bootstrap.ForgeCreativeModeTabRegistry;
import novamachina.novacore.bootstrap.ForgeFluidRegistry;
import novamachina.novacore.bootstrap.ForgeFluidTypeRegistry;
import novamachina.novacore.bootstrap.ForgeItemRegistry;
import novamachina.novacore.bootstrap.ForgeLootModifierRegistry;
import novamachina.novacore.bootstrap.ForgeRecipeSerializerRegistry;
import novamachina.novacore.bootstrap.ForgeRecipeTypeRegistry;
import novamachina.novacore.bootstrap.ForgeSoundEventRegistry;
import novamachina.novacore.bootstrap.ForgeStatRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MOD_ID)
public class ExNihiloSequentia {
  private static Logger log = LoggerFactory.getLogger(ExNihiloSequentia.class);

  public static final String MOD_ID = "exnihilosequentia";

  public ExNihiloSequentia() {
    SharedConstants.IS_RUNNING_IN_IDE = true;
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    Config.loadConfig(
        Config.COMMON_CONFIG,
        FMLPaths.CONFIGDIR
            .get()
            .resolve(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "-common.toml"));

    log.debug("Starting Ex Nihilo: Sequentia");

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
              if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK.key())) {
                InitBlocks.init(new ForgeBlockRegistry());
              }
              if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK_ENTITY_TYPE.key())) {
                InitBlockEntityTypes.init(new ForgeBlockEntityTypeRegistry());
              }
              if (event.getRegistryKey().equals(BuiltInRegistries.FLUID.key())) {
                InitFluidTypes.init(new ForgeFluidTypeRegistry());
                InitFluids.init(new ForgeFluidRegistry());
              }
              if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
                InitItems.init(new ForgeItemRegistry());
              }
              if (event.getRegistryKey().equals(BuiltInRegistries.RECIPE_SERIALIZER.key())) {
                InitRecipeSerializers.init(new ForgeRecipeSerializerRegistry());
              }
              if (event.getRegistryKey().equals(BuiltInRegistries.RECIPE_TYPE.key())) {
                InitRecipeTypes.init(new ForgeRecipeTypeRegistry());
              }
              if (event.getRegistryKey().equals(BuiltInRegistries.SOUND_EVENT.key())) {
                InitSoundEvents.init(new ForgeSoundEventRegistry());
              }
              if (event.getRegistryKey().equals(BuiltInRegistries.STAT_TYPE.key())) {
                InitStats.init(new ForgeStatRegistry());
              }
              if (event.getRegistryKey().equals(BuiltInRegistries.CREATIVE_MODE_TAB.key())) {
                InitCreativeTabs.init(new ForgeCreativeModeTabRegistry());
              }
              if (event
                  .getRegistryKey()
                  .equals(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS)) {
                InitLootModifiers.init(new ForgeLootModifierRegistry());
              }
            });
  }

  public static ResourceLocation makeId(String id) {
    return new ResourceLocation(MOD_ID, id);
  }
}
