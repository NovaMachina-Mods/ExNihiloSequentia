package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import novamachina.exnihilosequentia.api.datagen.AbstractLootTableGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloLootTableGenerator extends AbstractLootTableGenerator {

    public ExNihiloLootTableGenerator(DataGenerator generator) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    }

    @Override
    protected void createLootTables() {
        registerSelfDrops();
        registerInfestedLeaves();
    }

    private void registerInfestedLeaves() {
        registerLeaves(ExNihiloBlocks.INFESTING_LEAVES.get());
        registerLeaves(ExNihiloBlocks.INFESTED_LEAVES.get());
    }

    private void registerLeaves(Block block) {
        LootPool.Builder stringPool = createLootPoolBuilder();
        stringPool.setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.STRING));
        //stringPool.when(ExplosionCondition.survivesExplosion());

        LootPool.Builder silkWormPool = createLootPoolBuilder();
        silkWormPool.setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ExNihiloItems.SILKWORM.get()));
        silkWormPool.when(LootItemRandomChanceCondition.randomChance(0.7F));

        register(block, stringPool, silkWormPool);
    }

    private void registerSelfDrops() {
        registerSelfDrop(ExNihiloBlocks.BARREL_ACACIA.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_BIRCH.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_DARK_OAK.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_JUNGLE.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_OAK.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_SPRUCE.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_STONE.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_CRIMSON.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_WARPED.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_FIRED.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_UNFIRED.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_ACACIA.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_BIRCH.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_JUNGLE.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_OAK.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_SPRUCE.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_CRIMSON.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_WARPED.get());
        registerSelfDrop(ExNihiloBlocks.CRUSHED_ANDESITE.get());
        registerSelfDrop(ExNihiloBlocks.CRUSHED_DIORITE.get());
        registerSelfDrop(ExNihiloBlocks.CRUSHED_END_STONE.get());
        registerSelfDrop(ExNihiloBlocks.CRUSHED_GRANITE.get());
        registerSelfDrop(ExNihiloBlocks.CRUSHED_NETHERRACK.get());
        registerSelfDrop(ExNihiloBlocks.DUST.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_ACACIA.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_BIRCH.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_DARK_OAK.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_JUNGLE.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_OAK.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_SPRUCE.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_CRIMSON.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_WARPED.get());
    }
}
