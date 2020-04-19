package com.novamachina.ens.common.setup;

import com.novamachina.ens.common.registry.MasterRegistry;
import com.novamachina.ens.common.utility.Constants;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

    public static final ItemGroup ITEM_GROUP = new ItemGroup(Constants.ModInfo.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.BLOCK_DUST.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {
        MasterRegistry.getInstance();
        //        Networking.registerMessages();
    }
}