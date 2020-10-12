package com.novamachina.exnihilosequentia.common.datagen;

import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import com.novamachina.exnihilosequentia.common.item.resources.EnumResource;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.conditions.SurvivesExplosion;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class LootTables extends LootTableGenerator {

    public LootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void createLootTables() {
        registerSelfDrops();
        registerInfestedLeaves();
    }

    private void registerInfestedLeaves() {
        registerLeaves(ModBlocks.INFESTING_LEAVES.get());
        registerLeaves(ModBlocks.INFESTED_LEAVES.get());
    }

    private void registerLeaves(Block block) {
        LootPool.Builder stringPool = createLootPoolBuilder();
        stringPool.rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.STRING));
        stringPool.acceptCondition(SurvivesExplosion.builder());

        LootPool.Builder silkWormPool = createLootPoolBuilder();
        silkWormPool.rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(EnumResource.SILKWORM.getRegistryObject().get()));
        silkWormPool.acceptCondition(RandomChance.builder(0.7F));

        register(block, stringPool, silkWormPool);
    }

    private void registerSelfDrops() {
        registerSelfDrop(ModBlocks.BARREL_WOOD.get());
        registerSelfDrop(ModBlocks.BARREL_STONE.get());
        registerSelfDrop(ModBlocks.CRUCIBLE_FIRED.get());
        registerSelfDrop(ModBlocks.CRUCIBLE_UNFIRED.get());
        registerSelfDrop(ModBlocks.CRUCIBLE_WOOD.get());
        registerSelfDrop(ModBlocks.CRUSHED_ANDESITE.get());
        registerSelfDrop(ModBlocks.CRUSHED_DIORITE.get());
        registerSelfDrop(ModBlocks.CRUSHED_END_STONE.get());
        registerSelfDrop(ModBlocks.CRUSHED_GRANITE.get());
        registerSelfDrop(ModBlocks.CRUSHED_NETHERRACK.get());
        registerSelfDrop(ModBlocks.DUST.get());
        registerSelfDrop(ModBlocks.SIEVE.get());
    }

    private void registerSelfDrop(Block block) {
        register(block, singleItem(block));
    }

    private void register(Block block, LootPool.Builder... pools) {
        LootTable.Builder builder = LootTable.builder();
        for(LootPool.Builder pool : pools) {
            builder.addLootPool(pool);
        }
        register(block, builder);
    }

    private void register(Block block, LootTable.Builder table) {
        register(block.getRegistryName(), table);
    }

    private void register(ResourceLocation registryName, LootTable.Builder table) {
        if(lootTables.put(toTableLoc(registryName), table.setParameterSet(LootParameterSets.BLOCK).build()) != null) {
            throw new IllegalStateException("Duplicate loot table: " + table);
        }
    }

    private ResourceLocation toTableLoc(ResourceLocation registryName) {
        return new ResourceLocation(registryName.getNamespace(), "blocks/" + registryName.getPath());
    }

    private LootPool.Builder singleItem(IItemProvider in) {
        return createLootPoolBuilder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(in));
    }

    private LootPool.Builder createLootPoolBuilder() {
        return LootPool.builder().acceptCondition(SurvivesExplosion.builder());
    }

    @Override
    public String getName() {
        return "Loot Tables";
    }
}
