package novamachina.exnihilosequentia.datagen.common;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.datagen.api.datagen.AbstractLootTableGenerator;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

public class ExNihiloLootTableGenerator extends AbstractLootTableGenerator {

  public ExNihiloLootTableGenerator(@Nonnull final DataGenerator generator) {
    super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
  }

  @Override
  protected void createLootTables() {
    registerSelfDrops();
  }

  private void registerSelfDrops() {
    registerSelfDrop(EXNBlocks.ACACIA_BARREL.block());
    registerSelfDrop(EXNBlocks.BIRCH_BARREL.block());
    registerSelfDrop(EXNBlocks.DARK_OAK_BARREL.block());
    registerSelfDrop(EXNBlocks.JUNGLE_BARREL.block());
    registerSelfDrop(EXNBlocks.MANGROVE_BARREL.block());
    registerSelfDrop(EXNBlocks.OAK_BARREL.block());
    registerSelfDrop(EXNBlocks.SPRUCE_BARREL.block());
    registerSelfDrop(EXNBlocks.STONE_BARREL.block());
    registerSelfDrop(EXNBlocks.CRIMSON_BARREL.block());
    registerSelfDrop(EXNBlocks.WARPED_BARREL.block());
    registerSelfDrop(EXNBlocks.FIRED_CRUCIBLE.block());
    registerSelfDrop(EXNBlocks.UNFIRED_CRUCIBLE.block());
    registerSelfDrop(EXNBlocks.ACACIA_CRUCIBLE.block());
    registerSelfDrop(EXNBlocks.BIRCH_CRUCIBLE.block());
    registerSelfDrop(EXNBlocks.DARK_OAK_CRUCIBLE.block());
    registerSelfDrop(EXNBlocks.JUNGLE_CRUCIBLE.block());
    registerSelfDrop(EXNBlocks.MANGROVE_CRUCIBLE.block());
    registerSelfDrop(EXNBlocks.OAK_CRUCIBLE.block());
    registerSelfDrop(EXNBlocks.SPRUCE_CRUCIBLE.block());
    registerSelfDrop(EXNBlocks.CRIMSON_CRUCIBLE.block());
    registerSelfDrop(EXNBlocks.WARPED_CRUCIBLE.block());
    registerSelfDrop(EXNBlocks.CRUSHED_ANDESITE.block());
    registerSelfDrop(EXNBlocks.CRUSHED_BASALT.block());
    registerSelfDrop(EXNBlocks.CRUSHED_BLACKSTONE.block());
    registerSelfDrop(EXNBlocks.CRUSHED_CALCITE.block());
    registerSelfDrop(EXNBlocks.CRUSHED_DEEPSLATE.block());
    registerSelfDrop(EXNBlocks.CRUSHED_DIORITE.block());
    registerSelfDrop(EXNBlocks.CRUSHED_DRIPSTONE.block());
    registerSelfDrop(EXNBlocks.CRUSHED_END_STONE.block());
    registerSelfDrop(EXNBlocks.CRUSHED_GRANITE.block());
    registerSelfDrop(EXNBlocks.CRUSHED_NETHERRACK.block());
    registerSelfDrop(EXNBlocks.CRUSHED_TUFF.block());
    registerSelfDrop(EXNBlocks.DUST.block());
    registerSelfDrop(EXNBlocks.ACACIA_SIEVE.block());
    registerSelfDrop(EXNBlocks.BIRCH_SIEVE.block());
    registerSelfDrop(EXNBlocks.DARK_OAK_SIEVE.block());
    registerSelfDrop(EXNBlocks.JUNGLE_SIEVE.block());
    registerSelfDrop(EXNBlocks.MANGROVE_SIEVE.block());
    registerSelfDrop(EXNBlocks.OAK_SIEVE.block());
    registerSelfDrop(EXNBlocks.SPRUCE_SIEVE.block());
    registerSelfDrop(EXNBlocks.CRIMSON_SIEVE.block());
    registerSelfDrop(EXNBlocks.WARPED_SIEVE.block());
  }
}
