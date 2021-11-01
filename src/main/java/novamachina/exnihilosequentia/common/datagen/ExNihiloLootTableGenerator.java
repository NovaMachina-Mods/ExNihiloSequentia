package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import novamachina.exnihilosequentia.api.datagen.AbstractLootTableGenerator;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;

public class ExNihiloLootTableGenerator extends AbstractLootTableGenerator {

    public ExNihiloLootTableGenerator(DataGenerator generator) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    }

    @Override
    protected void createLootTables() {
        registerSelfDrops();
    }

    private void registerSelfDrops() {
        createSelfDrop(ExNihiloBlocks.ACACIA_BARREL.get());
        createSelfDrop(ExNihiloBlocks.BIRCH_BARREL.get());
        createSelfDrop(ExNihiloBlocks.DARK_OAK_BARREL.get());
        createSelfDrop(ExNihiloBlocks.JUNGLE_BARREL.get());
        createSelfDrop(ExNihiloBlocks.OAK_BARREL.get());
        createSelfDrop(ExNihiloBlocks.SPRUCE_BARREL.get());
        createSelfDrop(ExNihiloBlocks.STONE_BARREL.get());
        createSelfDrop(ExNihiloBlocks.CRIMSON_BARREL.get());
        createSelfDrop(ExNihiloBlocks.WARPED_BARREL.get());
        createSelfDrop(ExNihiloBlocks.FIRED_CRUCIBLE.get());
        createSelfDrop(ExNihiloBlocks.UNFIRED_CRUCIBLE.get());
        createSelfDrop(ExNihiloBlocks.ACACIA_CRUCIBLE.get());
        createSelfDrop(ExNihiloBlocks.BIRCH_CRUCIBLE.get());
        createSelfDrop(ExNihiloBlocks.DARK_OAK_CRUCIBLE.get());
        createSelfDrop(ExNihiloBlocks.JUNGLE_CRUCIBLE.get());
        createSelfDrop(ExNihiloBlocks.OAK_CRUCIBLE.get());
        createSelfDrop(ExNihiloBlocks.SPRUCE_CRUCIBLE.get());
        createSelfDrop(ExNihiloBlocks.CRIMSON_CRUCIBLE.get());
        createSelfDrop(ExNihiloBlocks.WARPED_CRUCIBLE.get());
        createSelfDrop(ExNihiloBlocks.CRUSHED_ANDESITE.get());
        createSelfDrop(ExNihiloBlocks.CRUSHED_DIORITE.get());
        createSelfDrop(ExNihiloBlocks.CRUSHED_END_STONE.get());
        createSelfDrop(ExNihiloBlocks.CRUSHED_GRANITE.get());
        createSelfDrop(ExNihiloBlocks.CRUSHED_NETHERRACK.get());
        createSelfDrop(ExNihiloBlocks.DUST.get());
        createSelfDrop(ExNihiloBlocks.ACACIA_SIEVE.get());
        createSelfDrop(ExNihiloBlocks.BIRCH_SIEVE.get());
        createSelfDrop(ExNihiloBlocks.DARK_OAK_SIEVE.get());
        createSelfDrop(ExNihiloBlocks.JUNGLE_SIEVE.get());
        createSelfDrop(ExNihiloBlocks.OAK_SIEVE.get());
        createSelfDrop(ExNihiloBlocks.SPRUCE_SIEVE.get());
        createSelfDrop(ExNihiloBlocks.CRIMSON_SIEVE.get());
        createSelfDrop(ExNihiloBlocks.WARPED_SIEVE.get());
    }
}
