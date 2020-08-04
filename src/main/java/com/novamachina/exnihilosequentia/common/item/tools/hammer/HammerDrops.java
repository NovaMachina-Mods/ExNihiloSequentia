package com.novamachina.exnihilosequentia.common.item.tools.hammer;

import com.novamachina.exnihilosequentia.common.setup.ModBlocks;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class HammerDrops {

    private static final Map<ResourceLocation, ResourceLocation> hammerDrops = new HashMap<>();

    public static void addRecipe(Block input, Block output) {
        addRecipe(input.getRegistryName(), output.getRegistryName());
    }

    private static void addRecipe(ResourceLocation input, ResourceLocation output) {
        hammerDrops.put(input, output);
    }

    public static void initialize() {

        addRecipe(Blocks.STONE, Blocks.COBBLESTONE);
        addRecipe(Blocks.COBBLESTONE, Blocks.GRAVEL);
        addRecipe(Blocks.GRAVEL, Blocks.SAND);
        addRecipe(Blocks.SAND, ModBlocks.DUST.get());
        addRecipe(Blocks.ANDESITE, ModBlocks.CRUSHED_ANDESITE.get());
        addRecipe(Blocks.DIORITE, ModBlocks.CRUSHED_DIORITE.get());
        addRecipe(Blocks.GRANITE, ModBlocks.CRUSHED_GRANITE.get());
        addRecipe(Blocks.END_STONE, ModBlocks.CRUSHED_END_STONE.get());
        addRecipe(Blocks.NETHERRACK, ModBlocks.CRUSHED_NETHERRACK.get());
    }

    public static Block getResult(ResourceLocation input) {
        if(hammerDrops.containsKey(input)) {
            ResourceLocation output = hammerDrops.get(input);
            if(ForgeRegistries.BLOCKS.containsKey(output)) {
                return ForgeRegistries.BLOCKS.getValue(output);
            }
        }
        return ForgeRegistries.BLOCKS.getValue(input);
    }
}
