package com.novamachina.exnihilosequentia.common.loot.modifier;

import com.google.gson.JsonObject;
import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.item.tools.hammer.HammerBaseItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class UseHammerModifier extends LootModifier {

    public UseHammerModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        ItemStack tool = context.get(LootParameters.TOOL);
        BlockState blockState = context.get(LootParameters.BLOCK_STATE);
        List<ItemStack> newLoot = new ArrayList<>();
        if (tool != null && blockState != null) {
            if (tool.getItem() instanceof HammerBaseItem) {
                ItemStack returnStack = ExNihiloRegistries.HAMMER_REGISTRY.getResult(blockState.getBlock().getRegistryName());
                if(returnStack != ItemStack.EMPTY){
                    newLoot.add(returnStack);
                }
            }
        }
        if(newLoot.size() > 0) {
            generatedLoot = newLoot;
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<UseHammerModifier> {


        @Override
        public UseHammerModifier read(ResourceLocation location, JsonObject object,
                                      ILootCondition[] ailootcondition) {
            return new UseHammerModifier(ailootcondition);
        }

        @Override
        public JsonObject write(UseHammerModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
