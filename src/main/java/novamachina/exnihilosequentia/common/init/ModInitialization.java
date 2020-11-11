package novamachina.exnihilosequentia.common.init;

import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.compat.ore.IOreCompat;
import novamachina.exnihilosequentia.common.compat.Create;
import novamachina.exnihilosequentia.common.compat.ExNihilo;
import novamachina.exnihilosequentia.common.compat.ImmersiveEngineering;
import novamachina.exnihilosequentia.common.compat.Mekanism;
import novamachina.exnihilosequentia.common.compat.ThermalExpansion;
import novamachina.exnihilosequentia.common.compat.top.CompatTOP;
import novamachina.exnihilosequentia.common.crafting.RecipeReloadListener;
import novamachina.exnihilosequentia.common.tileentity.barrel.mode.BarrelModeRegistry;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.DataPackRegistries;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Constants.ModIds.EX_NIHILO_SEQUENTIA, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModInitialization {
    private static final List<IOreCompat> oreCompats = new ArrayList<>();

    public static final ItemGroup ITEM_GROUP = new ItemGroup(Constants.ModIds.EX_NIHILO_SEQUENTIA) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.SIEVE.get());
        }
    };

    @ObjectHolder(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":use_hammer")
    public static GlobalLootModifierSerializer<?> HAMMER_MODIFIER = null;

    public static void init(IEventBus modEventBus) {
        ModBlocks.init(modEventBus);
        ModItems.init(modEventBus);
        ModTiles.init(modEventBus);
        ModFluids.init(modEventBus);
        ModSerializers.init(modEventBus);
    }

    @SubscribeEvent
    public static void setupNonTagBasedRegistries(FMLCommonSetupEvent event) {
        BarrelModeRegistry.initialize();
    }

    @SubscribeEvent
    public static void onServerStart(FMLServerStartingEvent event) {
        registerOreCompat();
        activateOreCompat();
    }

    @SubscribeEvent
    public static void registerTOP(InterModEnqueueEvent event) {
        if (ModList.get().isLoaded(Constants.ModIds.TOP)) {
            CompatTOP.register();
        }
    }


    public  static void addReloadListeners(AddReloadListenerEvent event) {
        DataPackRegistries dataPackRegistries = event.getDataPackRegistries();
        event.addListener(new RecipeReloadListener(dataPackRegistries));
    }

    private static void activateOreCompat() {
        oreCompats.forEach(IOreCompat::activateOres);
    }

    private static void registerOreCompat() {
        oreCompats.add(new ExNihilo());
        if (ModList.get().isLoaded(Constants.ModIds.THERMAL_EXPANSION) || Config.ENABLE_THERMAL.get()) {
            oreCompats.add(new ThermalExpansion());
        }
        if (ModList.get().isLoaded(Constants.ModIds.IMMERSIVE_ENGINEERING) || Config.ENABLE_IMMERSIVE.get()) {
            oreCompats.add(new ImmersiveEngineering());
        }
        if (ModList.get().isLoaded(Constants.ModIds.MEKANISM) || Config.ENABLE_MEKANISM.get()) {
            oreCompats.add(new Mekanism());
        }
        if (ModList.get().isLoaded(Constants.ModIds.CREATE) || Config.ENABLE_CREATE.get()) {
            oreCompats.add(new Create());
        }
    }
}
