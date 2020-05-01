package com.novamachina.ens.common.setup;

import com.novamachina.ens.common.utility.Constants;
import com.novamachina.ens.common.utility.LogUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ObjectHolder;

public class ModInitialization {

    public static final ItemGroup ITEM_GROUP = new ItemGroup(Constants.ModInfo.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.BLOCK_DUST.get());
        }
    };

    @ObjectHolder("ens:use_hammer")
    public static GlobalLootModifierSerializer<?> HAMMER_MODIFIER = null;

    public static void init(IEventBus modEventBus) {
        LogUtil.info("Registration init");
        ModBlocks.init(modEventBus);
        ModItems.init(modEventBus);
    }
}
