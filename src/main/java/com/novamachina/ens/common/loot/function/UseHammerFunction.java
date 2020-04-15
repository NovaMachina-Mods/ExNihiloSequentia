package com.novamachina.ens.common.loot.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.novamachina.ens.common.item.tools.hammer.HammerBaseItem;
import com.novamachina.ens.common.registry.MasterRegistry;
import com.novamachina.ens.common.utility.LogUtil;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class UseHammerFunction extends LootFunction {

    private UseHammerFunction(ILootCondition[] conditionsIn) {
        super(conditionsIn);
        LogUtil.info("Register Hammer Function");
    }

    @Override
    protected ItemStack doApply(ItemStack stack, LootContext context) {
        LogUtil.info("Ran Hammer Function");
        BlockState state     = context.get(LootParameters.BLOCK_STATE);
        ItemStack  itemstack = context.get(LootParameters.TOOL);

        if (itemstack != null && state != null) {
            if (itemstack.getItem() instanceof HammerBaseItem) {
                LogUtil.info("Used Hammer");
                return new ItemStack(MasterRegistry.HAMMER_REGISTRY.getResult(state.getBlock()));
            }
        }

        return stack;
    }

    public static class Serializer extends LootFunction.Serializer<UseHammerFunction> {

        public Serializer() {
            super(new ResourceLocation("ens:use_hammer_fun"), UseHammerFunction.class);
            LogUtil.info("UseHammerFunction.Serializer called");
        }

        @Override
        public UseHammerFunction deserialize(JsonObject object,
            JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
            LogUtil.info("UseHammerFunction deserialize");
            return new UseHammerFunction(conditionsIn);
        }
    }
}
