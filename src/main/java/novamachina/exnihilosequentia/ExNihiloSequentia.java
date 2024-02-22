package novamachina.exnihilosequentia;

import static novamachina.exnihilosequentia.ExNihiloSequentia.MOD_ID;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
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
import novamachina.novacore.bootstrap.NeoforgeBlockEntityTypeRegistry;
import novamachina.novacore.bootstrap.NeoforgeBlockRegistry;
import novamachina.novacore.bootstrap.NeoforgeCreativeModeTabRegistry;
import novamachina.novacore.bootstrap.NeoforgeFluidRegistry;
import novamachina.novacore.bootstrap.NeoforgeFluidTypeRegistry;
import novamachina.novacore.bootstrap.NeoforgeItemRegistry;
import novamachina.novacore.bootstrap.NeoforgeLootModifierRegistry;
import novamachina.novacore.bootstrap.NeoforgeRecipeSerializerRegistry;
import novamachina.novacore.bootstrap.NeoforgeRecipeTypeRegistry;
import novamachina.novacore.bootstrap.NeoforgeSoundEventRegistry;
import novamachina.novacore.bootstrap.NeoforgeStatRegistry;
import org.slf4j.Logger;

@Mod(MOD_ID)
public class ExNihiloSequentia {

  public static final String MOD_ID = "exnihilosequentia";
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExNihiloSequentia.class);

  public ExNihiloSequentia(IEventBus modEventBus) {
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    Config.loadConfig(
        Config.COMMON_CONFIG,
        FMLPaths.CONFIGDIR
            .get()
            .resolve(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "-common.toml"));

    log.debug("Starting Ex Nihilo: Sequentia");

    ExNihiloInitialization.init(modEventBus);
    modEventBus.addListener(ClientSetup::init);

    modEventBus.addListener(
        (RegisterEvent event) -> {
          if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK.key())) {
            InitBlocks.init(new NeoforgeBlockRegistry());
          }
          if (event.getRegistryKey().equals(BuiltInRegistries.BLOCK_ENTITY_TYPE.key())) {
            InitBlockEntityTypes.init(new NeoforgeBlockEntityTypeRegistry());
          }
          if (event.getRegistryKey().equals(BuiltInRegistries.FLUID.key())) {
            InitFluidTypes.init(new NeoforgeFluidTypeRegistry());
            InitFluids.init(new NeoforgeFluidRegistry());
          }
          if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
            InitItems.init(new NeoforgeItemRegistry());
          }
          if (event.getRegistryKey().equals(BuiltInRegistries.RECIPE_SERIALIZER.key())) {
            InitRecipeSerializers.init(new NeoforgeRecipeSerializerRegistry());
          }
          if (event.getRegistryKey().equals(BuiltInRegistries.RECIPE_TYPE.key())) {
            InitRecipeTypes.init(new NeoforgeRecipeTypeRegistry());
          }
          if (event.getRegistryKey().equals(BuiltInRegistries.SOUND_EVENT.key())) {
            InitSoundEvents.init(new NeoforgeSoundEventRegistry());
          }
          if (event.getRegistryKey().equals(BuiltInRegistries.STAT_TYPE.key())) {
            InitStats.init(new NeoforgeStatRegistry());
          }
          if (event.getRegistryKey().equals(BuiltInRegistries.CREATIVE_MODE_TAB.key())) {
            InitCreativeTabs.init(new NeoforgeCreativeModeTabRegistry());
          }
          if (event
              .getRegistryKey()
              .equals(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS.key())) {
            InitLootModifiers.init(new NeoforgeLootModifierRegistry());
          }
        });
  }

  public static ResourceLocation makeId(String id) {
    return new ResourceLocation(MOD_ID, id);
  }
}
