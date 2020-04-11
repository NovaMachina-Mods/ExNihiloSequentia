package com.novamachina.ens.common.setup;

import com.novamachina.ens.common.utility.Constants;
import com.novamachina.ens.common.utility.Constants.ModInfo;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID, bus = Bus.FORGE)
public class ModSetup {

    public static final ItemGroup ITEM_GROUP = new ItemGroup(Constants.ModInfo.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.BLOCK_DUST.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {
        addRegistries();
        //        Networking.registerMessages();
    }

    private static void addRegistries() {

    }
}
