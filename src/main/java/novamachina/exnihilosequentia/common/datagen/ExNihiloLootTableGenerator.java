package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import novamachina.exnihilosequentia.api.datagen.AbstractLootTableGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloLootTableGenerator extends AbstractLootTableGenerator {

    public ExNihiloLootTableGenerator(DataGenerator generator) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    }

    @Override
    protected void createLootTables() {
        registerSelfDrops();
    }

    private void registerSelfDrops() {
        createSelfDrop(ExNihiloBlocks.BARREL_ACACIA.get());
        createSelfDrop(ExNihiloBlocks.BARREL_BIRCH.get());
        createSelfDrop(ExNihiloBlocks.BARREL_DARK_OAK.get());
        createSelfDrop(ExNihiloBlocks.BARREL_JUNGLE.get());
        createSelfDrop(ExNihiloBlocks.BARREL_OAK.get());
        createSelfDrop(ExNihiloBlocks.BARREL_SPRUCE.get());
        createSelfDrop(ExNihiloBlocks.BARREL_STONE.get());
        createSelfDrop(ExNihiloBlocks.BARREL_CRIMSON.get());
        createSelfDrop(ExNihiloBlocks.BARREL_WARPED.get());
        createSelfDrop(ExNihiloBlocks.CRUCIBLE_FIRED.get());
        createSelfDrop(ExNihiloBlocks.CRUCIBLE_UNFIRED.get());
        createSelfDrop(ExNihiloBlocks.CRUCIBLE_ACACIA.get());
        createSelfDrop(ExNihiloBlocks.CRUCIBLE_BIRCH.get());
        createSelfDrop(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get());
        createSelfDrop(ExNihiloBlocks.CRUCIBLE_JUNGLE.get());
        createSelfDrop(ExNihiloBlocks.CRUCIBLE_OAK.get());
        createSelfDrop(ExNihiloBlocks.CRUCIBLE_SPRUCE.get());
        createSelfDrop(ExNihiloBlocks.CRUCIBLE_CRIMSON.get());
        createSelfDrop(ExNihiloBlocks.CRUCIBLE_WARPED.get());
        createSelfDrop(ExNihiloBlocks.CRUSHED_ANDESITE.get());
        createSelfDrop(ExNihiloBlocks.CRUSHED_DIORITE.get());
        createSelfDrop(ExNihiloBlocks.CRUSHED_END_STONE.get());
        createSelfDrop(ExNihiloBlocks.CRUSHED_GRANITE.get());
        createSelfDrop(ExNihiloBlocks.CRUSHED_NETHERRACK.get());
        createSelfDrop(ExNihiloBlocks.DUST.get());
        createSelfDrop(ExNihiloBlocks.SIEVE_ACACIA.get());
        createSelfDrop(ExNihiloBlocks.SIEVE_BIRCH.get());
        createSelfDrop(ExNihiloBlocks.SIEVE_DARK_OAK.get());
        createSelfDrop(ExNihiloBlocks.SIEVE_JUNGLE.get());
        createSelfDrop(ExNihiloBlocks.SIEVE_OAK.get());
        createSelfDrop(ExNihiloBlocks.SIEVE_SPRUCE.get());
        createSelfDrop(ExNihiloBlocks.SIEVE_CRIMSON.get());
        createSelfDrop(ExNihiloBlocks.SIEVE_WARPED.get());
    }
}
