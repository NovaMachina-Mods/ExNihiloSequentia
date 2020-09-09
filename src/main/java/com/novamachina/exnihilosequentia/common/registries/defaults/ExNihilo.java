package com.novamachina.exnihilosequentia.common.registries.defaults;

import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import com.novamachina.exnihilosequentia.common.init.ModFluids;
import com.novamachina.exnihilosequentia.common.init.ModItems;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.ore.EnumOre;
import com.novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import com.novamachina.exnihilosequentia.common.item.resources.EnumResource;
import com.novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import com.novamachina.exnihilosequentia.common.registries.barrel.compost.CompostRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.fluid.FluidBlockTransformRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.fluid.FluidOnTopRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.transform.FluidTransformRegistry;
import com.novamachina.exnihilosequentia.common.registries.crook.CrookRegistry;
import com.novamachina.exnihilosequentia.common.registries.crucible.CrucibleRegistry;
import com.novamachina.exnihilosequentia.common.registries.crucible.HeatRegistry;
import com.novamachina.exnihilosequentia.common.registries.hammer.HammerRegistry;
import com.novamachina.exnihilosequentia.common.registries.sieve.SieveRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ExNihilo implements IDefaultRegistry {
    @Override
    public void registerCrook(CrookRegistry registry) {
        registry.addDrop(EnumResource.SILKWORM.getRegistryObject().get(), 0.1F);
    }

    @Override
    public void registerHammer(HammerRegistry registry) {
        registry.addRecipe(Blocks.STONE, Blocks.COBBLESTONE);
        registry.addRecipe(Blocks.COBBLESTONE, Blocks.GRAVEL);
        registry.addRecipe(Blocks.GRAVEL, Blocks.SAND);
        registry.addRecipe(Blocks.SAND, ModBlocks.DUST.get());
        registry.addRecipe(Blocks.ANDESITE, ModBlocks.CRUSHED_ANDESITE.get());
        registry.addRecipe(Blocks.DIORITE, ModBlocks.CRUSHED_DIORITE.get());
        registry.addRecipe(Blocks.GRANITE, ModBlocks.CRUSHED_GRANITE.get());
        registry.addRecipe(Blocks.END_STONE, ModBlocks.CRUSHED_END_STONE.get());
        registry.addRecipe(Blocks.NETHERRACK, ModBlocks.CRUSHED_NETHERRACK.get());
        registry.addRecipe(Blocks.TUBE_CORAL_BLOCK, Blocks.TUBE_CORAL);
        registry.addRecipe(Blocks.BRAIN_CORAL_BLOCK, Blocks.BRAIN_CORAL);
        registry.addRecipe(Blocks.BUBBLE_CORAL_BLOCK, Blocks.BUBBLE_CORAL);
        registry.addRecipe(Blocks.FIRE_CORAL_BLOCK, Blocks.FIRE_CORAL);
        registry.addRecipe(Blocks.HORN_CORAL_BLOCK, Blocks.HORN_CORAL);
        registry.addRecipe(Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN);
        registry.addRecipe(Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN);
        registry.addRecipe(Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN);
        registry.addRecipe(Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN);
        registry.addRecipe(Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN);
    }

    @Override
    public void registerCompost(CompostRegistry registry) {
        registry.addSolid(new ResourceLocation("minecraft:saplings"), 125);
        registry.addSolid(new ResourceLocation("minecraft:leaves"), 125);
        registry.addSolid(new ResourceLocation("minecraft:flowers"), 100);
        registry.addSolid(new ResourceLocation("minecraft:fishes"), 150);
        registry.addSolid(new ResourceLocation("forge:meat_cooked"), 200);
        registry.addSolid(new ResourceLocation("forge:meat_uncooked"), 200);
        registry.addSolid(new ResourceLocation("forge:seeds"), 80);
        registry.addSolid(new ResourceLocation("forge:crops/wheat"), 80);
        registry.addSolid(new ResourceLocation("forge:crops/carrot"), 100);
        registry.addSolid(new ResourceLocation("forge:crops/beetroot"), 100);
        registry.addSolid(new ResourceLocation("forge:crops/potato"), 100);
        registry.addSolid(new ResourceLocation("forge:crops/nether_wart"), 100);
        registry.addSolid(new ResourceLocation("forge:eggs"), 80);
        registry.addSolid(new ResourceLocation("forge:string"), 40);
        registry.addSolid(Items.ROTTEN_FLESH, 100);
        registry.addSolid(Items.SPIDER_EYE, 80);
        registry.addSolid(Items.BREAD, 160);
        registry.addSolid(Blocks.BROWN_MUSHROOM, 100);
        registry.addSolid(Blocks.RED_MUSHROOM, 100);
        registry.addSolid(Items.PUMPKIN_PIE, 160);
        registry.addSolid(EnumResource.SILKWORM.getRegistryObject().get(), 40);
        registry.addSolid(ModItems.COOKED_SILKWORM.get(), 40);
        registry.addSolid(Items.APPLE, 100);
        registry.addSolid(Items.MELON_SLICE, 40);
        registry.addSolid(Blocks.MELON, 1000 / 6);
        registry.addSolid(Blocks.PUMPKIN, 1000 / 6);
        registry.addSolid(Blocks.CARVED_PUMPKIN, 1000 / 6);
        registry.addSolid(Blocks.JACK_O_LANTERN, 1000 / 6);
        registry.addSolid(Blocks.CACTUS, 100);
        registry.addSolid(Items.BAKED_POTATO, 150);
        registry.addSolid(Items.POISONOUS_POTATO, 200);
        registry.addSolid(Blocks.LILY_PAD, 100);
        registry.addSolid(Blocks.VINE, 100);
        registry.addSolid(Blocks.TALL_GRASS, 100);
        registry.addSolid(Blocks.SUGAR_CANE, 80);
    }

    @Override
    public void registerFluidBlock(FluidBlockTransformRegistry registry) {
        registry.addRecipe(Fluids.WATER, ModBlocks.DUST.get(), Blocks.CLAY);
        registry.addRecipe(Fluids.LAVA, Items.REDSTONE, Blocks.NETHERRACK);
        registry.addRecipe(Fluids.LAVA, Items.GLOWSTONE_DUST, Blocks.END_STONE);
        registry.addRecipe(ModFluids.WITCH_WATER.get(), Blocks.SAND, Blocks.SOUL_SAND);
        registry.addRecipe(ModFluids.WITCH_WATER.get(), Blocks.RED_MUSHROOM, Blocks.SLIME_BLOCK);
        registry.addRecipe(ModFluids.WITCH_WATER.get(), Blocks.BROWN_MUSHROOM, Blocks.SLIME_BLOCK);
        registry.addRecipe(ModFluids.SEA_WATER.get(), EnumResource.BLUE_CORAL_SEED.getRegistryObject()
            .get(), Blocks.TUBE_CORAL_BLOCK);
        registry.addRecipe(ModFluids.SEA_WATER.get(), EnumResource.RED_CORAL_SEED.getRegistryObject()
            .get(), Blocks.FIRE_CORAL_BLOCK);
        registry.addRecipe(ModFluids.SEA_WATER.get(), EnumResource.PINK_CORAL_SEED.getRegistryObject()
            .get(), Blocks.BRAIN_CORAL_BLOCK);
        registry.addRecipe(ModFluids.SEA_WATER.get(), EnumResource.PURPLE_CORAL_SEED.getRegistryObject()
            .get(), Blocks.BUBBLE_CORAL_BLOCK);
        registry.addRecipe(ModFluids.SEA_WATER.get(), EnumResource.YELLOW_CORAL_SEED.getRegistryObject()
            .get(), Blocks.HORN_CORAL_BLOCK);
    }

    @Override
    public void registerFluidOnTop(FluidOnTopRegistry registry) {
        registry.addRecipe(Fluids.LAVA, Fluids.WATER, Blocks.OBSIDIAN);
        registry.addRecipe(Fluids.WATER, Fluids.LAVA, Blocks.COBBLESTONE);
    }

    @Override
    public void registerFluidTransform(FluidTransformRegistry registry) {
        registry.addRecipe(Fluids.WATER, Blocks.MYCELIUM, ModFluids.WITCH_WATER.get());
        registry.addRecipe(Fluids.WATER, Blocks.SAND, ModFluids.SEA_WATER.get());
    }

    @Override
    public void registerFiredCrucible(CrucibleRegistry registry) {
        registry.addMeltable(Blocks.COBBLESTONE, 250, Fluids.LAVA, CrucilbeTypeEnum.FIRED);
        registry.addMeltable(Blocks.DIORITE, 250, Fluids.LAVA, CrucilbeTypeEnum.FIRED);
        registry.addMeltable(Blocks.ANDESITE, 250, Fluids.LAVA, CrucilbeTypeEnum.FIRED);
        registry.addMeltable(Blocks.GRANITE, 250, Fluids.LAVA, CrucilbeTypeEnum.FIRED);
        registry.addMeltable(Blocks.STONE, 250, Fluids.LAVA, CrucilbeTypeEnum.FIRED);
        registry.addMeltable(Blocks.GRAVEL, 200, Fluids.LAVA, CrucilbeTypeEnum.FIRED);
        registry.addMeltable(Blocks.SAND, 100, Fluids.LAVA, CrucilbeTypeEnum.FIRED);
        registry.addMeltable(ModBlocks.DUST.get(), 50, Fluids.LAVA, CrucilbeTypeEnum.FIRED);
        registry.addMeltable(Blocks.NETHERRACK, 1000, Fluids.LAVA, CrucilbeTypeEnum.FIRED);
        registry.addMeltable(Blocks.OBSIDIAN, 1000, Fluids.LAVA, CrucilbeTypeEnum.FIRED);
    }

    @Override
    public void registerWoodCrucible(CrucibleRegistry registry) {
        registry
            .addMeltable(new ResourceLocation("minecraft:saplings"), 250, new ResourceLocation("minecraft:water"), CrucilbeTypeEnum.WOOD);
        registry
            .addMeltable(new ResourceLocation("minecraft:leaves"), 250, new ResourceLocation("minecraft:water"), CrucilbeTypeEnum.WOOD);
    }

    @Override
    public void registerHeat(HeatRegistry registry) {
        registry.addHeatSource(Blocks.LAVA, 3);
        registry.addHeatSource(Blocks.FIRE, 4);
        registry.addHeatSource(Blocks.TORCH, 1);
        registry.addHeatSource(Blocks.WALL_TORCH, 1);
        registry.addHeatSource(Blocks.MAGMA_BLOCK, 2);
        registry.addHeatSource(Blocks.GLOWSTONE, 2);
    }

    @Override
    public void registerSieve(SieveRegistry registry) {
        // Stone Pebble
        registry.addDrop(Blocks.DIRT,
            EnumPebbleType.STONE.getRegistryObject().get(), 1.0F, EnumMesh.STRING, false);
        registry.addDrop(Blocks.DIRT,
            EnumPebbleType.STONE.getRegistryObject().get(), 1.0F, EnumMesh.STRING, false);
        registry.addDrop(Blocks.DIRT,
            EnumPebbleType.STONE.getRegistryObject().get(), 0.5F, EnumMesh.STRING, false);
        registry.addDrop(Blocks.DIRT,
            EnumPebbleType.STONE.getRegistryObject().get(), 0.5F, EnumMesh.STRING, false);
        registry.addDrop(Blocks.DIRT,
            EnumPebbleType.STONE.getRegistryObject().get(), 0.1F, EnumMesh.STRING, false);
        registry.addDrop(Blocks.DIRT,
            EnumPebbleType.STONE.getRegistryObject().get(), 0.1F, EnumMesh.STRING, false);

        // Andesite Pebble
        registry.addDrop(Blocks.DIRT,
            EnumPebbleType.ANDESITE.getRegistryObject().get(), 0.5F, EnumMesh.STRING, false);
        registry.addDrop(Blocks.DIRT,
            EnumPebbleType.ANDESITE.getRegistryObject().get(), 0.1F, EnumMesh.STRING, false);

        // Diorite Pebble
        registry.addDrop(Blocks.DIRT,
            EnumPebbleType.DIORITE.getRegistryObject().get(), 0.5F, EnumMesh.STRING, false);
        registry.addDrop(Blocks.DIRT,
            EnumPebbleType.DIORITE.getRegistryObject().get(), 0.1F, EnumMesh.STRING, false);

        // Granite Pebble
        registry.addDrop(Blocks.DIRT,
            EnumPebbleType.GRANITE.getRegistryObject().get(), 0.5F, EnumMesh.STRING, false);
        registry.addDrop(Blocks.DIRT,
            EnumPebbleType.GRANITE.getRegistryObject().get(), 0.1F, EnumMesh.STRING, false);

        // Vanilla Seeds
        registry.addDrop(Blocks.DIRT, Items.WHEAT_SEEDS, 0.7F, EnumMesh.STRING, false);
        registry.addDrop(Blocks.DIRT, Items.MELON_SEEDS, 0.35F, EnumMesh.STRING, false);
        registry.addDrop(Blocks.DIRT, Items.PUMPKIN_SEEDS, 0.35F, EnumMesh.STRING, false);

        // Ancient Spores
        registry.addDrop(Blocks.DIRT,
            EnumResource.ANCIENT_SPORE.getRegistryObject().get(), 0.05F, EnumMesh.STRING, false);

        // Grass Seeds
        registry.addDrop(Blocks.DIRT, EnumResource.GRASS_SEED.getRegistryObject().get(), 0.05F, EnumMesh.STRING, false);

        // Misc Vanilla Drops
        registry.addDrop(Blocks.SAND, Items.COCOA_BEANS, 0.03F, EnumMesh.STRING, false);
        registry.addDrop(Blocks.SAND, Items.PRISMARINE_SHARD, 0.02F, EnumMesh.DIAMOND, false);

        // Flint
        registry.addDrop(Blocks.GRAVEL, Items.FLINT, 0.25F, EnumMesh.STRING, false);
        registry.addDrop(Blocks.GRAVEL, Items.FLINT, 0.25F, EnumMesh.FLINT, false);

        // Coal
        registry.addDrop(Blocks.GRAVEL, Items.COAL, 0.125F, EnumMesh.FLINT, false);

        registry.addDrop(Blocks.GRAVEL, Items.LAPIS_LAZULI, 0.05F, EnumMesh.FLINT, false);

        // Diamond
        registry.addDrop(Blocks.GRAVEL, Items.DIAMOND, 0.008F, EnumMesh.IRON, false);
        registry.addDrop(Blocks.GRAVEL, Items.DIAMOND, 0.016F, EnumMesh.DIAMOND, false);

        // Emerald
        registry.addDrop(Blocks.GRAVEL, Items.EMERALD, 0.008F, EnumMesh.IRON, false);
        registry.addDrop(Blocks.GRAVEL, Items.EMERALD, 0.016F, EnumMesh.DIAMOND, false);

        // Quartz
        registry.addDrop(Blocks.SOUL_SAND, Items.QUARTZ, 1.0F, EnumMesh.FLINT, false);
        registry.addDrop(Blocks.SOUL_SAND, Items.QUARTZ, 0.33F, EnumMesh.FLINT, false);
        registry.addDrop(Blocks.SOUL_SAND, Items.QUARTZ, 1.0F, EnumMesh.DIAMOND, false);
        registry.addDrop(Blocks.SOUL_SAND, Items.QUARTZ, 0.8F, EnumMesh.DIAMOND, false);

        // Nether Wart
        registry.addDrop(Blocks.SOUL_SAND, Items.NETHER_WART, 0.1F, EnumMesh.STRING, false);

        // Ghast Tear
        registry.addDrop(Blocks.SOUL_SAND, Items.GHAST_TEAR, 0.02F, EnumMesh.DIAMOND, false);

        registry.addDrop(ModBlocks.DUST.get(), Items.BONE_MEAL, 0.2F, EnumMesh.STRING, false);

        // Gunpowder
        registry.addDrop(ModBlocks.DUST.get(), Items.GUNPOWDER, 0.07F, EnumMesh.STRING, false);

        // Redstone
        registry.addDrop(ModBlocks.DUST.get(), Items.REDSTONE, 0.125F, EnumMesh.IRON, false);
        registry.addDrop(ModBlocks.DUST.get(), Items.REDSTONE, 0.25F, EnumMesh.DIAMOND, false);

        // Glowstone
        registry.addDrop(ModBlocks.DUST.get(), Items.GLOWSTONE_DUST, 0.0625F, EnumMesh.IRON, false);

        // Blaze Powder
        registry.addDrop(ModBlocks.DUST.get(), Items.BLAZE_POWDER, 0.05F, EnumMesh.IRON, false);

        // Ores
        for (EnumOre ore : EnumOre.values()) {
            switch (ore) {
                case IRON: {
                    registry.addMultiMeshDrop(Blocks.GRAVEL,
                        ore.getPieceItem().get(), null, 0.1F, 0.15F, 0.25F, false);
                    registry.addDrop(Blocks.SAND, ore.getPieceItem()
                        .get(), 0.5F, EnumMesh.DIAMOND, false);
                    break;
                }
                case GOLD: {
                    registry.addMultiMeshDrop(
                        ModBlocks.CRUSHED_NETHERRACK.get(),
                        ore.getPieceItem().get(), null, 0.25F, 0.25F, 0.4F, false);
                    registry.addMultiMeshDrop(Blocks.GRAVEL,
                        ore.getPieceItem().get(), null, 0.05F, 0.075F, 0.15F, false);
                    break;
                }
            }
        }

        // Seeds
        for (EnumSeed seed : EnumSeed.values()) {
            if (seed != EnumSeed.SEED_PICKLE && seed != EnumSeed.SEED_KELP) {
                registry.addDrop(Blocks.DIRT, seed.getRegistryObject()
                    .get(), 0.05F, EnumMesh.STRING, false);
            } else {
                registry
                    .addDrop(Blocks.SAND, seed.getRegistryObject().get(), 0.05F, EnumMesh.STRING, true);
            }
        }

        getLeavesSaplings().forEach((input, drop) -> {
            LeavesBlock leavesBlock = (LeavesBlock) input;
            if (leavesBlock.getRegistryName().equals(new ResourceLocation("jungle_leaves"))) {
                registry.addMultiMeshDrop(leavesBlock, drop, 0.025F, 0.05F, 0.075F, 0.1F, false);
            } else {
                registry.addMultiMeshDrop(leavesBlock, drop, 0.05F, 0.10F, 0.15F, 0.2F, false);
            }

            // Apple
            registry.addMultiMeshDrop(leavesBlock, Items.APPLE, 0.05F, 0.10F, 0.15F, 0.20F, false);

            // Golden Apple
            registry.addMultiMeshDrop(leavesBlock, Items.GOLDEN_APPLE, 0.001F, 0.003F, 0.005F, 0.01F, false);

            // Silk Worm
            registry.addMultiMeshDrop(leavesBlock,
                EnumResource.SILKWORM.getRegistryObject().get(), 0.025F, 0.05F, 0.1F, 0.2F, false);
        });

        // Coral Seeds
        registry.addDrop(Blocks.SAND, EnumResource.BLUE_CORAL_SEED.getRegistryObject()
            .get(), 0.05F, EnumMesh.IRON, true);
        registry.addDrop(Blocks.SAND, EnumResource.PURPLE_CORAL_SEED.getRegistryObject()
            .get(), 0.05F, EnumMesh.IRON, true);
        registry.addDrop(Blocks.SAND, EnumResource.PINK_CORAL_SEED.getRegistryObject()
            .get(), 0.05F, EnumMesh.IRON, true);
        registry.addDrop(Blocks.SAND, EnumResource.YELLOW_CORAL_SEED.getRegistryObject()
            .get(), 0.05F, EnumMesh.IRON, true);
        registry.addDrop(Blocks.SAND, EnumResource.RED_CORAL_SEED.getRegistryObject()
            .get(), 0.05F, EnumMesh.IRON, true);
    }

    private Map<Block, Item> getLeavesSaplings() {
        Map<Block, Item> saplingMap = new HashMap<>();
        saplingMap.put(Blocks.ACACIA_LEAVES, Items.ACACIA_SAPLING);
        saplingMap.put(Blocks.BIRCH_LEAVES, Items.BIRCH_SAPLING);
        saplingMap.put(Blocks.DARK_OAK_LEAVES, Items.DARK_OAK_SAPLING);
        saplingMap.put(Blocks.JUNGLE_LEAVES, Items.JUNGLE_SAPLING);
        saplingMap.put(Blocks.OAK_LEAVES, Items.OAK_SAPLING);
        saplingMap.put(Blocks.SPRUCE_LEAVES, Items.SPRUCE_SAPLING);

        return saplingMap;
    }
}
