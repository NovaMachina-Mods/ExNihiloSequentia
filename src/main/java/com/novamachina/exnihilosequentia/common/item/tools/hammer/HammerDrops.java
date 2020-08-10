package com.novamachina.exnihilosequentia.common.item.tools.hammer;

import com.novamachina.exnihilosequentia.common.setup.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class HammerDrops extends AbstractModRegistry {

    private final Map<ResourceLocation, ResourceLocation> hammerDrops = new HashMap<>();

    public HammerDrops(ModRegistries.ModBus bus) {
        bus.register(this);
    }

    public void addRecipe(Block input, Block output) {
        addRecipe(input.getRegistryName(), output.getRegistryName());
    }

    private void addRecipe(ResourceLocation input, ResourceLocation output) {
        hammerDrops.put(input, output);
    }

    public Block getResult(ResourceLocation input) {
        if(hammerDrops.containsKey(input)) {
            ResourceLocation output = hammerDrops.get(input);
            if(ForgeRegistries.BLOCKS.containsKey(output)) {
                return ForgeRegistries.BLOCKS.getValue(output);
            }
        }
        return ForgeRegistries.BLOCKS.getValue(input);
    }

    @Override
    public void clear() {
        hammerDrops.clear();
    }

    @Override
    public Object toJSONReady() {
        return null;
    }

    @Override
    protected void useDefaults() {
        addRecipe(Blocks.STONE, Blocks.COBBLESTONE);
        addRecipe(Blocks.COBBLESTONE, Blocks.GRAVEL);
        addRecipe(Blocks.GRAVEL, Blocks.SAND);
        addRecipe(Blocks.SAND, ModBlocks.DUST.get());
        addRecipe(Blocks.ANDESITE, ModBlocks.CRUSHED_ANDESITE.get());
        addRecipe(Blocks.DIORITE, ModBlocks.CRUSHED_DIORITE.get());
        addRecipe(Blocks.GRANITE, ModBlocks.CRUSHED_GRANITE.get());
        addRecipe(Blocks.END_STONE, ModBlocks.CRUSHED_END_STONE.get());
        addRecipe(Blocks.NETHERRACK, ModBlocks.CRUSHED_NETHERRACK.get());
        addRecipe(Blocks.TUBE_CORAL_BLOCK, Blocks.TUBE_CORAL);
        addRecipe(Blocks.BRAIN_CORAL_BLOCK, Blocks.BRAIN_CORAL);
        addRecipe(Blocks.BUBBLE_CORAL_BLOCK, Blocks.BUBBLE_CORAL);
        addRecipe(Blocks.FIRE_CORAL_BLOCK, Blocks.FIRE_CORAL);
        addRecipe(Blocks.HORN_CORAL_BLOCK, Blocks.HORN_CORAL);
        addRecipe(Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN);
        addRecipe(Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN);
        addRecipe(Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN);
        addRecipe(Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN);
        addRecipe(Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN);
    }

    @Override
    protected void useJson() {
        useDefaults();
    }
}
