package com.novamachina.ens.common.loot.condition;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.novamachina.ens.common.item.tools.hammer.HammerBaseItem;
import com.novamachina.ens.common.utility.LogUtil;
import java.util.Set;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameter;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class UseHammerCondition implements ILootCondition {

    public UseHammerCondition() {
    }

    public Set<LootParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(LootParameters.TOOL);
    }

    public boolean test(LootContext p_test_1_) {
        LogUtil.info("Check Used Hammer");
        ItemStack itemstack = p_test_1_.get(LootParameters.TOOL);
        return itemstack != null && itemstack.getItem() instanceof HammerBaseItem;
    }

    public static ILootCondition.IBuilder builder() {
        return UseHammerCondition::new;
    }

    public static class Serializer extends ILootCondition.AbstractSerializer<UseHammerCondition> {

        public Serializer() {
            super(new ResourceLocation("ens:use_hammer_cond"), UseHammerCondition.class);
        }

        public void serialize(JsonObject json, UseHammerCondition value,
            JsonSerializationContext context) {

        }

        public UseHammerCondition deserialize(JsonObject json, JsonDeserializationContext context) {
            return new UseHammerCondition();
        }
    }

}
