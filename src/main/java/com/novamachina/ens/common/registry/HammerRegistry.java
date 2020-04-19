package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class HammerRegistry extends IRegistry<Block> {

    @Override
    protected void useJsonRegistry() {

    }

    @Override
    public void register(String key, Block value) {
        registry.put(key, value);
    }

    @Override
    protected void useDefaultRegistry() {
        register(Blocks.STONE.getRegistryName().toString(), Blocks.COBBLESTONE);
        register(Blocks.COBBLESTONE.getRegistryName().toString(), Blocks.GRAVEL);
        register(Blocks.GRAVEL.getRegistryName().toString(), Blocks.SAND);
        register(Blocks.SAND.getRegistryName().toString(), Registration.BLOCK_DUST.get());
        register(Blocks.ANDESITE.getRegistryName().toString(),
            Registration.BLOCK_CRUSHED_ANDESITE.get());
        register(Blocks.DIORITE.getRegistryName().toString(),
            Registration.BLOCK_CRUSHED_DIORITE.get());
        register(Blocks.GRANITE.getRegistryName().toString(),
            Registration.BLOCK_CRUSHED_GRANITE.get());
        register(Blocks.END_STONE.getRegistryName().toString(),
            Registration.BLOCK_CRUSHED_END_STONE.get());
        register(Blocks.NETHERRACK.getRegistryName().toString(),
            Registration.BLOCK_CRUSHED_NETHERRACK.get());
    }

    public Block getResult(String input) {
        return registry.get(input);
    }
}
