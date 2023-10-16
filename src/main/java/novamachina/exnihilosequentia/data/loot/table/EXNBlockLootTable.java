package novamachina.exnihilosequentia.data.loot.table;

import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.data.loot.table.BlockLootTables;

public class EXNBlockLootTable extends BlockLootTables {
  @Override
  protected void generate() {
    add(
        block -> createSingleItemTable(block),
        EXNBlocks.ACACIA_BARREL,
        EXNBlocks.BAMBOO_BARREL,
        EXNBlocks.BIRCH_BARREL,
        EXNBlocks.CHERRY_BARREL,
        EXNBlocks.DARK_OAK_BARREL,
        EXNBlocks.JUNGLE_BARREL,
        EXNBlocks.MANGROVE_BARREL,
        EXNBlocks.OAK_BARREL,
        EXNBlocks.SPRUCE_BARREL,
        EXNBlocks.STONE_BARREL,
        EXNBlocks.CRIMSON_BARREL,
        EXNBlocks.WARPED_BARREL,
        EXNBlocks.FIRED_CRUCIBLE,
        EXNBlocks.UNFIRED_CRUCIBLE,
        EXNBlocks.ACACIA_CRUCIBLE,
        EXNBlocks.BAMBOO_CRUCIBLE,
        EXNBlocks.BIRCH_CRUCIBLE,
        EXNBlocks.CHERRY_CRUCIBLE,
        EXNBlocks.DARK_OAK_CRUCIBLE,
        EXNBlocks.JUNGLE_CRUCIBLE,
        EXNBlocks.MANGROVE_CRUCIBLE,
        EXNBlocks.OAK_CRUCIBLE,
        EXNBlocks.SPRUCE_CRUCIBLE,
        EXNBlocks.CRIMSON_CRUCIBLE,
        EXNBlocks.WARPED_CRUCIBLE,
        EXNBlocks.CRUSHED_ANDESITE,
        EXNBlocks.CRUSHED_BASALT,
        EXNBlocks.CRUSHED_BLACKSTONE,
        EXNBlocks.CRUSHED_CALCITE,
        EXNBlocks.CRUSHED_DEEPSLATE,
        EXNBlocks.CRUSHED_DIORITE,
        EXNBlocks.CRUSHED_DRIPSTONE,
        EXNBlocks.CRUSHED_END_STONE,
        EXNBlocks.CRUSHED_GRANITE,
        EXNBlocks.CRUSHED_NETHERRACK,
        EXNBlocks.CRUSHED_TUFF,
        EXNBlocks.DUST,
        EXNBlocks.ACACIA_SIEVE,
        EXNBlocks.BAMBOO_SIEVE,
        EXNBlocks.BIRCH_SIEVE,
        EXNBlocks.CHERRY_SIEVE,
        EXNBlocks.DARK_OAK_SIEVE,
        EXNBlocks.JUNGLE_SIEVE,
        EXNBlocks.MANGROVE_SIEVE,
        EXNBlocks.OAK_SIEVE,
        EXNBlocks.SPRUCE_SIEVE,
        EXNBlocks.CRIMSON_SIEVE,
        EXNBlocks.WARPED_SIEVE);
  }
}
