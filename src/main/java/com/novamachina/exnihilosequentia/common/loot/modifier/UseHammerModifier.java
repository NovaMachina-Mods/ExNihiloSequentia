package com.novamachina.exnihilosequentia.common.loot.modifier;

import com.google.gson.JsonObject;
import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.item.tools.hammer.HammerBaseItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
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
        if (tool != null && blockState != null) {
            if (tool.getItem() instanceof HammerBaseItem) {
                List<ItemStack> newLoot = new ArrayList<>();
                Block returnBlock = ExNihiloRegistries.HAMMER_REGISTRY
                    .getResult(blockState.getBlock().getRegistryName());
                newLoot.add(new ItemStack(returnBlock));
                return newLoot;
            }
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<UseHammerModifier> {


        @Override
        public UseHammerModifier read(ResourceLocation location, JsonObject object,
                                      ILootCondition[] ailootcondition) {
            return new UseHammerModifier(ailootcondition);
        }
    }
}
