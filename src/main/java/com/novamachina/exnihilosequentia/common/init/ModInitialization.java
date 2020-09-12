package com.novamachina.exnihilosequentia.common.init;

import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.compat.top.CompatTOP;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.mode.BarrelModeRegistry;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Constants.ModIds.EX_NIHILO_SEQUENTIA, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModInitialization {

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
    }

    @SubscribeEvent
    public static void setupNonTagBasedRegistries(FMLCommonSetupEvent event) {
        BarrelModeRegistry.initialize();
    }

    @SubscribeEvent
    public static void onServerStart(FMLServerStartingEvent event) {
        ModCommands.register(event.getCommandDispatcher());

        if (Config.USE_JSON_REGISTRIES.get()) {
            ExNihiloRegistries.BUS.useJson();
        } else {
            ExNihiloRegistries.BUS.useDefault();
        }
    }

    @SubscribeEvent
    public static void clearRegistriesOnServerExit(FMLServerStoppedEvent event) {
        ExNihiloRegistries.BUS.clearRegistries();
    }

    @SubscribeEvent
    public static void registerTOP(InterModEnqueueEvent event) {
        if (ModList.get().isLoaded(Constants.ModIds.TOP)) {
            CompatTOP.register();
        }
    }
}
