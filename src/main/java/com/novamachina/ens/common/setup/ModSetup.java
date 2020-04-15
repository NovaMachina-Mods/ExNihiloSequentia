package com.novamachina.ens.common.setup;

import com.novamachina.ens.common.loot.condition.UseHammerCondition;
import com.novamachina.ens.common.loot.function.UseHammerFunction;
import com.novamachina.ens.common.registry.CrookRegistry;
import com.novamachina.ens.common.registry.MasterRegistry;
import com.novamachina.ens.common.registry.registryitem.HammerRegistry;
import com.novamachina.ens.common.utility.Constants;
import com.novamachina.ens.common.utility.Constants.ModInfo;
import com.novamachina.ens.common.utility.LogUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ModSetup {

    public static final ItemGroup ITEM_GROUP = new ItemGroup(Constants.ModInfo.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.BLOCK_DUST.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {
        addRegistries();
        LootFunctionManager.registerFunction(new UseHammerFunction.Serializer());
        LootConditionManager.registerCondition(new UseHammerCondition.Serializer());
        //        Networking.registerMessages();
    }

    private static void addRegistries() {
        MasterRegistry.CROOK_REGISTRY  = new CrookRegistry();
        MasterRegistry.HAMMER_REGISTRY = new HammerRegistry();

        MasterRegistry.addRegistry(MasterRegistry.CROOK_REGISTRY);
        MasterRegistry.addRegistry(MasterRegistry.HAMMER_REGISTRY);

        MasterRegistry.initRegistries();
    }
}
