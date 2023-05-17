package novamachina.exnihilosequentia.datagen.common;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.datagen.api.datagen.AbstractLootTableGenerator;

public class ExNihiloLootTableGenerator extends AbstractLootTableGenerator {

  public ExNihiloLootTableGenerator(@Nonnull final DataGenerator generator) {
    super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
  }

  @Override
  protected void createLootTables() {
    registerSelfDrops();
  }

  private void registerSelfDrops() {
    registerSelfDrop(ExNihiloBlocks.BARREL_ACACIA.get());
    registerSelfDrop(ExNihiloBlocks.BARREL_BIRCH.get());
    registerSelfDrop(ExNihiloBlocks.BARREL_DARK_OAK.get());
    registerSelfDrop(ExNihiloBlocks.BARREL_JUNGLE.get());
    registerSelfDrop(ExNihiloBlocks.BARREL_MANGROVE.get());
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
    registerSelfDrop(ExNihiloBlocks.CRUCIBLE_MANGROVE.get());
    registerSelfDrop(ExNihiloBlocks.CRUCIBLE_OAK.get());
    registerSelfDrop(ExNihiloBlocks.CRUCIBLE_SPRUCE.get());
    registerSelfDrop(ExNihiloBlocks.CRUCIBLE_CRIMSON.get());
    registerSelfDrop(ExNihiloBlocks.CRUCIBLE_WARPED.get());
    registerSelfDrop(ExNihiloBlocks.CRUSHED_ANDESITE.get());
    registerSelfDrop(ExNihiloBlocks.CRUSHED_BASALT.get());
    registerSelfDrop(ExNihiloBlocks.CRUSHED_BLACKSTONE.get());
    registerSelfDrop(ExNihiloBlocks.CRUSHED_CALCITE.get());
    registerSelfDrop(ExNihiloBlocks.CRUSHED_DEEPSLATE.get());
    registerSelfDrop(ExNihiloBlocks.CRUSHED_DIORITE.get());
    registerSelfDrop(ExNihiloBlocks.CRUSHED_DRIPSTONE.get());
    registerSelfDrop(ExNihiloBlocks.CRUSHED_END_STONE.get());
    registerSelfDrop(ExNihiloBlocks.CRUSHED_GRANITE.get());
    registerSelfDrop(ExNihiloBlocks.CRUSHED_NETHERRACK.get());
    registerSelfDrop(ExNihiloBlocks.CRUSHED_TUFF.get());
    registerSelfDrop(ExNihiloBlocks.DUST.get());
    registerSelfDrop(ExNihiloBlocks.SIEVE_ACACIA.get());
    registerSelfDrop(ExNihiloBlocks.SIEVE_BIRCH.get());
    registerSelfDrop(ExNihiloBlocks.SIEVE_DARK_OAK.get());
    registerSelfDrop(ExNihiloBlocks.SIEVE_JUNGLE.get());
    registerSelfDrop(ExNihiloBlocks.SIEVE_MANGROVE.get());
    registerSelfDrop(ExNihiloBlocks.SIEVE_OAK.get());
    registerSelfDrop(ExNihiloBlocks.SIEVE_SPRUCE.get());
    registerSelfDrop(ExNihiloBlocks.SIEVE_CRIMSON.get());
    registerSelfDrop(ExNihiloBlocks.SIEVE_WARPED.get());
  }
}
