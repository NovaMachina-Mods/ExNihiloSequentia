package com.novamachina.ens.common.loot.modifier;

import com.novamachina.ens.common.utility.Constants.ModInfo;
import com.novamachina.ens.common.utility.LogUtil;
import javax.annotation.Nonnull;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ModInfo.MOD_ID, bus = Bus.MOD)
public class LootModifierHandler {

    @SubscribeEvent
    public static void registerModifierSerializers(
        @Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        LogUtil.info("Register Modifiers");
        event.getRegistry()
            .register(new UseHammerModifier.Serializer().setRegistryName("ens", "use_hammer"));
    }
}
