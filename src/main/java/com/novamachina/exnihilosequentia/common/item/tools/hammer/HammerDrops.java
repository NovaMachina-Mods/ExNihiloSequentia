package com.novamachina.exnihilosequentia.common.item.tools.hammer;

import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class HammerDrops {

    private static final Map<String, Block> hammerDrops = new HashMap<>();
    private static       boolean            initialized = false;

    public static void useDefaults() {

        hammerDrops.put(Blocks.STONE.getRegistryName().toString(), Blocks.COBBLESTONE);
        hammerDrops.put(Blocks.COBBLESTONE.getRegistryName().toString(), Blocks.GRAVEL);
        hammerDrops.put(Blocks.GRAVEL.getRegistryName().toString(), Blocks.SAND);
        hammerDrops.put(Blocks.SAND.getRegistryName().toString(), ModBlocks.BLOCK_DUST.get());
        hammerDrops.put(Blocks.ANDESITE.getRegistryName().toString(),
            ModBlocks.BLOCK_CRUSHED_ANDESITE.get());
        hammerDrops.put(Blocks.DIORITE.getRegistryName().toString(),
            ModBlocks.BLOCK_CRUSHED_DIORITE.get());
        hammerDrops.put(Blocks.GRANITE.getRegistryName().toString(),
            ModBlocks.BLOCK_CRUSHED_GRANITE.get());
        hammerDrops.put(Blocks.END_STONE.getRegistryName().toString(),
            ModBlocks.BLOCK_CRUSHED_END_STONE.get());
        hammerDrops.put(Blocks.NETHERRACK.getRegistryName().toString(),
            ModBlocks.BLOCK_CRUSHED_NETHERRACK.get());
    }

    public static Block getResult(String input) {
        if (!initialized) {
            useDefaults();
            initialized = true;
        }
        return hammerDrops.get(input);
    }
}
