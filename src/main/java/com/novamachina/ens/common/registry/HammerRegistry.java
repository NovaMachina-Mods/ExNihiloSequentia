package com.novamachina.ens.common.registry;

import com.novamachina.ens.ExNihiloSequentia;
import com.novamachina.ens.common.setup.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class HammerRegistry extends IRegistry<Block> {

    @Override
    protected void useJsonRegistry() {

    }

    @Override
    protected void useDefaultRegistry() {
        if (!ExNihiloSequentia.itemRegistrationFinished) {
            return;
        }

        register(Blocks.STONE.getRegistryName().toString(), Blocks.COBBLESTONE);
        register(Blocks.COBBLESTONE.getRegistryName().toString(), Blocks.GRAVEL);
        register(Blocks.GRAVEL.getRegistryName().toString(), Blocks.SAND);
        register(Blocks.SAND.getRegistryName().toString(), ModBlocks.BLOCK_DUST.get());
        register(Blocks.ANDESITE.getRegistryName().toString(),
            ModBlocks.BLOCK_CRUSHED_ANDESITE.get());
        register(Blocks.DIORITE.getRegistryName().toString(),
            ModBlocks.BLOCK_CRUSHED_DIORITE.get());
        register(Blocks.GRANITE.getRegistryName().toString(),
            ModBlocks.BLOCK_CRUSHED_GRANITE.get());
        register(Blocks.END_STONE.getRegistryName().toString(),
            ModBlocks.BLOCK_CRUSHED_END_STONE.get());
        register(Blocks.NETHERRACK.getRegistryName().toString(),
            ModBlocks.BLOCK_CRUSHED_NETHERRACK.get());
    }

    public Block getResult(String input) {
        return registry.get(input);
    }
}
