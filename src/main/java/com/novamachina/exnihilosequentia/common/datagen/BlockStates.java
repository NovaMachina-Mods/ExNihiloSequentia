package com.novamachina.exnihilosequentia.common.datagen;

import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {
    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Constants.ModIds.EX_NIHILO_SEQUENTIA, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.CRUSHED_ANDESITE.get());
        simpleBlock(ModBlocks.CRUSHED_DIORITE.get());
        simpleBlock(ModBlocks.CRUSHED_GRANITE.get());
        simpleBlock(ModBlocks.CRUSHED_NETHERRACK.get());
        simpleBlock(ModBlocks.CRUSHED_END_STONE.get());
        simpleBlock(ModBlocks.DUST.get());
        simpleBlock(ModBlocks.INFESTED_LEAVES.get());
        simpleBlock(ModBlocks.INFESTING_LEAVES.get());
    }
}
