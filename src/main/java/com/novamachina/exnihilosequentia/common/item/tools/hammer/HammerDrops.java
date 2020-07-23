package com.novamachina.exnihilosequentia.common.item.tools.hammer;

import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class HammerDrops {

    private static final Map<String, Block> hammerDrops = new HashMap<>();

    public static void initialize() {

        hammerDrops.put(Blocks.STONE.getRegistryName().toString(), Blocks.COBBLESTONE);
        hammerDrops.put(Blocks.COBBLESTONE.getRegistryName().toString(), Blocks.GRAVEL);
        hammerDrops.put(Blocks.GRAVEL.getRegistryName().toString(), Blocks.SAND);
        hammerDrops.put(Blocks.SAND.getRegistryName().toString(), ModBlocks.DUST.get());
        hammerDrops.put(Blocks.ANDESITE.getRegistryName().toString(),
            ModBlocks.CRUSHED_ANDESITE.get());
        hammerDrops.put(Blocks.DIORITE.getRegistryName().toString(),
            ModBlocks.CRUSHED_DIORITE.get());
        hammerDrops.put(Blocks.GRANITE.getRegistryName().toString(),
            ModBlocks.CRUSHED_GRANITE.get());
        hammerDrops.put(Blocks.END_STONE.getRegistryName().toString(),
            ModBlocks.CRUSHED_END_STONE.get());
        hammerDrops.put(Blocks.NETHERRACK.getRegistryName().toString(),
            ModBlocks.CRUSHED_NETHERRACK.get());
    }

    public static Block getResult(String input) {
        return hammerDrops.get(input);
    }
}
