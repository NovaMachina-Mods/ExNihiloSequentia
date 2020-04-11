package com.novamachina.ens.common.handler;

import com.novamachina.ens.common.item.tools.crook.CrookBaseItem;
import com.novamachina.ens.common.utility.Constants.ModInfo;
import com.novamachina.ens.common.utility.LogUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID, bus = Bus.FORGE)
public class CrookHandler {

    @SubscribeEvent
    public static void handleEvent(BlockEvent event) {
        LogUtil.info("Received Block Event");
    }

    @SubscribeEvent
    public static void handleCrookEvent(BlockEvent.HarvestDropsEvent event) {
        LogUtil.info("Fired handleCrookEvent");
        if (event.getWorld().isRemote()) {
            return;
        }

        if (event.getHarvester() == null) {
            return;
        }

        if (event.isSilkTouching()) {
            return;
        }

        ItemStack held = event.getHarvester().getHeldItemMainhand();
        if ((held.getItem() instanceof CrookBaseItem)) {
            return;
        }
        LogUtil.info("Recognized crook");

//        List<CrookReward> rewards = ExNihiloRegistryManager.CROOK_REGISTRY.getRewards(event.getState());
//        if (rewards.size() > 0) {
//            event.getDrops().clear();
//            event.setDropChance(1f);
//
//            int fortune = event.getFortuneLevel();
//            for (CrookReward reward : rewards) {
//                if (event.getWorld().rand.nextFloat() <= reward.getChance() + (reward.getFortuneChance() * fortune)) {
//                    event.getDrops().add(reward.getStack().copy());
//                }
//
//            }
//        }

//        if (event.getState().getBlock() instanceof LeavesBlock) //Simulate vanilla drops without firing event
//        {
//            for (int i = 0; i < ModConfig.crooking.numberOfTimesToTestVanillaDrops + 1; i++) {
//                Block block   = event.getState().getBlock();
//                int   fortune = event.getFortuneLevel();
//                @SuppressWarnings("deprecation")
//                java.util.List<ItemStack> items = block.getDrops(event.getWorld(), event.getPos(), event.getState(), fortune);
//                for (ItemStack item : items) {
//                    if (event.getWorld().rand.nextFloat() <= event.getDropChance()) {
//                        Block.spawnAsEntity(event.getWorld(), event.getPos(), item);
//                    }
//                }
//            }
//        }
    }

}
