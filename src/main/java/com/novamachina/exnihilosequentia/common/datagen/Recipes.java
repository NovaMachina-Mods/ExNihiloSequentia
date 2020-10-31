package com.novamachina.exnihilosequentia.common.datagen;

import com.novamachina.exnihilosequentia.common.api.crafting.crook.CrookRecipe;
import com.novamachina.exnihilosequentia.common.api.crafting.crook.CrookRecipeBuilder;
import com.novamachina.exnihilosequentia.common.api.crafting.hammer.HammerRecipeBuilder;
import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import com.novamachina.exnihilosequentia.common.init.ModItems;
import com.novamachina.exnihilosequentia.common.item.dolls.DollEnum;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.ore.EnumOre;
import com.novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import com.novamachina.exnihilosequentia.common.item.resources.EnumResource;
import com.novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import com.novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        registerCrooks(consumer);
        registerPebbleBlocks(consumer);
        registerBarrels(consumer);
        registerOres(consumer);
        registerHammers(consumer);
        registerDolls(consumer);
        registerMeshes(consumer);
        registerMisc(consumer);
        
        registerCustomRecipes(consumer);
    }

    private void registerCustomRecipes(Consumer<IFinishedRecipe> consumer) {
        registerHammerRecipes(consumer);
        registerCrookRecipes(consumer);
    }

    private void registerCrookRecipes(Consumer<IFinishedRecipe> consumer) {
        CrookRecipeBuilder.builder().input(ItemTags.LEAVES).addDrop(EnumResource.SILKWORM.getRegistryObject().get(), 0.1F).build(consumer, crookLoc("leaves"));
    }

    private ResourceLocation crookLoc(String id) {
        return new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "crook/" + id);
    }

    private void registerHammerRecipes(Consumer<IFinishedRecipe> consumer) {
        HammerRecipeBuilder.builder().input(Blocks.STONE).result(Blocks.COBBLESTONE).build(consumer, hammerLoc("cobblestone"));
        HammerRecipeBuilder.builder().input(Blocks.COBBLESTONE).result(Blocks.GRAVEL).build(consumer, hammerLoc("gravel"));
        HammerRecipeBuilder.builder().input(Blocks.GRAVEL).result(Blocks.SAND).build(consumer, hammerLoc("sand"));
        HammerRecipeBuilder.builder().input(Blocks.SAND).result(ModBlocks.DUST.get()).build(consumer, hammerLoc("dust"));
        HammerRecipeBuilder.builder().input(Blocks.NETHERRACK).result(ModBlocks.CRUSHED_NETHERRACK.get()).build(consumer, hammerLoc("netherrack"));
        HammerRecipeBuilder.builder().input(Blocks.ANDESITE).result(ModBlocks.CRUSHED_ANDESITE.get()).build(consumer, hammerLoc("andesite"));
        HammerRecipeBuilder.builder().input(Blocks.DIORITE).result(ModBlocks.CRUSHED_DIORITE.get()).build(consumer, hammerLoc("diorite"));
        HammerRecipeBuilder.builder().input(Blocks.GRANITE).result(ModBlocks.CRUSHED_GRANITE.get()).build(consumer, hammerLoc("granite"));
        HammerRecipeBuilder.builder().input(Blocks.END_STONE).result(ModBlocks.CRUSHED_END_STONE.get()).build(consumer, hammerLoc("end_stone"));

        HammerRecipeBuilder.builder().input(Blocks.TUBE_CORAL_BLOCK).result(Blocks.TUBE_CORAL).build(consumer, hammerLoc("tube_coral"));
        HammerRecipeBuilder.builder().input(Blocks.BRAIN_CORAL_BLOCK).result(Blocks.BRAIN_CORAL).build(consumer, hammerLoc("brain_coral"));
        HammerRecipeBuilder.builder().input(Blocks.BUBBLE_CORAL_BLOCK).result(Blocks.BUBBLE_CORAL).build(consumer, hammerLoc("bubble_coral"));
        HammerRecipeBuilder.builder().input(Blocks.FIRE_CORAL_BLOCK).result(Blocks.FIRE_CORAL).build(consumer, hammerLoc("fire_coral"));
        HammerRecipeBuilder.builder().input(Blocks.HORN_CORAL_BLOCK).result(Blocks.HORN_CORAL).build(consumer, hammerLoc("horn_coral"));
        HammerRecipeBuilder.builder().input(Blocks.TUBE_CORAL).result(Blocks.TUBE_CORAL_FAN).build(consumer, hammerLoc("tube_coral_fan"));
        HammerRecipeBuilder.builder().input(Blocks.BRAIN_CORAL).result(Blocks.BRAIN_CORAL_FAN).build(consumer, hammerLoc("brain_coral_fan"));
        HammerRecipeBuilder.builder().input(Blocks.BUBBLE_CORAL).result(Blocks.BUBBLE_CORAL_FAN).build(consumer, hammerLoc("bubble_coral_fan"));
        HammerRecipeBuilder.builder().input(Blocks.FIRE_CORAL).result(Blocks.FIRE_CORAL_FAN).build(consumer, hammerLoc("fire_coral_fan"));
        HammerRecipeBuilder.builder().input(Blocks.HORN_CORAL).result(Blocks.HORN_CORAL_FAN).build(consumer, hammerLoc("horn_coral_fan"));
    }

    private ResourceLocation hammerLoc(String id) {
        return new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "hammer/" + id);
    }

    private void registerMisc(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(Blocks.BEEHIVE)
            .patternLine("xxx")
            .patternLine("fff")
            .patternLine("xxx")
            .key('x', ItemTags.PLANKS)
            .key('f', EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
            .addCriterion("has_frame", InventoryChangeTrigger.Instance
                .forItems(EnumResource.BEEHIVE_FRAME.getRegistryObject().get()))
            .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
            .patternLine("xxx")
            .patternLine("xfx")
            .patternLine("xxx")
            .key('x', Items.STICK)
            .key('f', Items.STRING)
            .addCriterion("has_stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
            .addCriterion("has_string", InventoryChangeTrigger.Instance.forItems(Items.STRING))
            .build(consumer);

        CookingRecipeBuilder.smeltingRecipe(Ingredient
            .fromItems(EnumResource.SILKWORM.getRegistryObject().get()), ModItems.COOKED_SILKWORM.get(), 0.1F, 200)
            .addCriterion("has_silkworm", InventoryChangeTrigger.Instance
                .forItems(EnumResource.SILKWORM.getRegistryObject().get()))
            .build(consumer);

        CookingRecipeBuilder
            .smeltingRecipe(Ingredient.fromItems(ModBlocks.CRUCIBLE_UNFIRED.get()), ModBlocks.CRUCIBLE_FIRED
                .get(), 0.7F, 200)
            .addCriterion("has_uncooked_crucible", InventoryChangeTrigger.Instance
                .forItems(ModBlocks.CRUCIBLE_UNFIRED.get()))
            .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.CRUCIBLE_UNFIRED.get())
            .patternLine("c c")
            .patternLine("c c")
            .patternLine("ccc")
            .key('c', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
            .addCriterion("has_porcelain_clay", InventoryChangeTrigger.Instance
                .forItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
            .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.CRUCIBLE_WOOD.get())
            .patternLine("c c")
            .patternLine("clc")
            .patternLine("s s")
            .key('c', ItemTags.LOGS)
            .key('l', ItemTags.WOODEN_SLABS)
            .key('s', Items.STICK)
            .addCriterion("has_logs", hasItem(ItemTags.LOGS))
            .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(EnumResource.CRAFTING_DOLL.getRegistryObject().get(), 4)
            .patternLine("xex")
            .patternLine(" x ")
            .patternLine("x x")
            .key('x', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
            .key('e', Items.DIAMOND)
            .addCriterion("has_porcelain_clay", InventoryChangeTrigger.Instance
                .forItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
            .addCriterion("has_diamond", InventoryChangeTrigger.Instance.forItems(Items.DIAMOND))
            .build(consumer, new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "doll_x4"));

        ShapedRecipeBuilder.shapedRecipe(EnumResource.CRAFTING_DOLL.getRegistryObject().get(), 6)
            .patternLine("xex")
            .patternLine(" x ")
            .patternLine("x x")
            .key('x', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
            .key('e', Items.EMERALD)
            .addCriterion("has_porcelain_clay", InventoryChangeTrigger.Instance
                .forItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
            .addCriterion("has_emerald", InventoryChangeTrigger.Instance.forItems(Items.EMERALD))
            .build(consumer, new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "doll_x6"));

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.END_CAKE.get())
            .patternLine("ece")
            .patternLine("eke")
            .patternLine("ece")
            .key('e', Items.ENDER_EYE)
            .key('c', Items.END_CRYSTAL)
            .key('k', Items.CAKE)
            .addCriterion("has_ender_pearl", InventoryChangeTrigger.Instance.forItems(Items.ENDER_PEARL))
            .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
            .addIngredient(Items.CLAY_BALL)
            .addIngredient(Items.BONE_MEAL)
            .addCriterion("has_clay", InventoryChangeTrigger.Instance.forItems(Items.CLAY_BALL))
            .build(consumer);
    }

    private void registerMeshes(Consumer<IFinishedRecipe> consumer) {
        registerMesh(EnumMesh.FLINT.getRegistryObject().get(), EnumMesh.STRING.getRegistryObject()
            .get(), Items.FLINT, consumer);
        registerMesh(EnumMesh.IRON.getRegistryObject().get(), EnumMesh.FLINT.getRegistryObject()
            .get(), Items.IRON_INGOT, consumer);
        registerMesh(EnumMesh.DIAMOND.getRegistryObject().get(), EnumMesh.IRON.getRegistryObject()
            .get(), Items.DIAMOND, consumer);

        ShapedRecipeBuilder.shapedRecipe(EnumMesh.STRING.getRegistryObject().get())
            .patternLine("iii")
            .patternLine("iii")
            .patternLine("iii")
            .key('i', Items.STRING)
            .addCriterion("has_sieve", InventoryChangeTrigger.Instance.forItems(ModBlocks.SIEVE.get()))
            .build(consumer);
    }

    private void registerMesh(Item output, Item inputMesh, Item inputItem, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
            .patternLine("i i")
            .patternLine("imi")
            .patternLine("i i")
            .key('i', inputItem)
            .key('m', inputMesh)
            .addCriterion("has_mesh", InventoryChangeTrigger.Instance.forItems(inputMesh))
            .build(consumer);
    }

    private void registerDolls(Consumer<IFinishedRecipe> consumer) {
        registerDoll(DollEnum.BEE.getRegistryObject()
            .get(), Items.YELLOW_DYE, Items.GLOWSTONE_DUST, ItemTags.FLOWERS, EnumResource.BEEHIVE_FRAME
            .getRegistryObject().get(), consumer);
        registerDoll(DollEnum.BLAZE.getRegistryObject()
            .get(), Items.BLAZE_POWDER, Items.GLOWSTONE_DUST, Items.REDSTONE, Items.NETHER_WART, consumer);
        registerDoll(DollEnum.ENDERMAN.getRegistryObject()
            .get(), Items.BLACK_DYE, Items.GLOWSTONE_DUST, Items.REDSTONE, Items.NETHER_WART, consumer);
        registerDoll(DollEnum.GUARDIAN.getRegistryObject()
            .get(), Items.PRISMARINE, Items.GLOWSTONE_DUST, Items.REDSTONE, ItemTags.FISHES, consumer);
        registerDoll(DollEnum.SHULKER.getRegistryObject()
            .get(), Items.PURPLE_DYE, Items.GLOWSTONE_DUST, Items.END_STONE, Items.ENDER_PEARL, consumer);
    }

    private void registerDoll(Item output, Item corners, Item sides, Item top, ITag.INamedTag<Item> bottom, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
            .patternLine("ctc")
            .patternLine("sms")
            .patternLine("cbc")
            .key('c', corners)
            .key('t', top)
            .key('s', sides)
            .key('b', bottom)
            .key('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
            .addCriterion("has_doll", InventoryChangeTrigger.Instance
                .forItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
            .build(consumer);
    }

    private void registerDoll(Item output, Item corners, Item sides, Item top, Item bottom, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
            .patternLine("ctc")
            .patternLine("sms")
            .patternLine("cbc")
            .key('c', corners)
            .key('t', top)
            .key('s', sides)
            .key('b', bottom)
            .key('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
            .addCriterion("has_doll", InventoryChangeTrigger.Instance
                .forItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
            .build(consumer);
    }

    private void registerDoll(Item output, Item corners, Item sides, ITag.INamedTag<Item> top, Item bottom, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
            .patternLine("ctc")
            .patternLine("sms")
            .patternLine("cbc")
            .key('c', corners)
            .key('t', top)
            .key('s', sides)
            .key('b', bottom)
            .key('m', EnumResource.CRAFTING_DOLL.getRegistryObject().get())
            .addCriterion("has_doll", InventoryChangeTrigger.Instance
                .forItems(EnumResource.CRAFTING_DOLL.getRegistryObject().get()))
            .build(consumer);
    }

    private void registerHammers(Consumer<IFinishedRecipe> consumer) {
        registerHammer(EnumHammer.DIAMOND.getRegistryObject().get(), Items.DIAMOND, consumer);
        registerHammer(EnumHammer.GOLD.getRegistryObject().get(), Items.GOLD_INGOT, consumer);
        registerHammer(EnumHammer.IRON.getRegistryObject().get(), Items.IRON_INGOT, consumer);
        registerHammer(EnumHammer.STONE.getRegistryObject().get(), Tags.Items.COBBLESTONE, consumer);
        registerHammer(EnumHammer.WOOD.getRegistryObject().get(), ItemTags.PLANKS, consumer);
    }

    private void registerHammer(Item output, ITag.INamedTag<Item> input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
            .patternLine(" x ")
            .patternLine(" -x")
            .patternLine("-  ")
            .key('x', input)
            .key('-', Items.STICK)
            .addCriterion("has_stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
            .addCriterion("has_material", hasItem(input))
            .build(consumer);
    }

    private void registerHammer(Item output, Item input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output)
            .patternLine(" x ")
            .patternLine(" -x")
            .patternLine("-  ")
            .key('x', input)
            .key('-', Items.STICK)
            .addCriterion("has_stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
            .addCriterion("has_material", InventoryChangeTrigger.Instance.forItems(input))
            .build(consumer);
    }

    // TODO: Vanilla Metals don't generate recipes
    private void registerOres(Consumer<IFinishedRecipe> consumer) {
        for (EnumOre ore : EnumOre.values()) {
            registerOre(ore, consumer);
            if(!ore.isVanilla()) {
                registerSmelting(ore, consumer);
            }
            if(ore.isVanilla()) {
                if(ore == EnumOre.IRON) {
                    CookingRecipeBuilder
                        .smeltingRecipe(Ingredient.fromItems(ore.getChunkItem().get()), Items.IRON_INGOT, 0.7F, 200)
                        .addCriterion("has_chunk", InventoryChangeTrigger.Instance.forItems(ore.getChunkItem().get()))
                        .build(consumer);
                }
                if(ore == EnumOre.GOLD) {
                    CookingRecipeBuilder
                        .smeltingRecipe(Ingredient.fromItems(ore.getChunkItem().get()), Items.GOLD_INGOT, 0.7F, 200)
                        .addCriterion("has_chunk", InventoryChangeTrigger.Instance.forItems(ore.getChunkItem().get()))
                        .build(consumer);
                }
            }
        }
    }

    private void registerSmelting(EnumOre ore, Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder
            .smeltingRecipe(Ingredient.fromItems(ore.getChunkItem().get()), ore.getIngotItem().get(), 0.7F, 200)
            .addCriterion("has_chunk", InventoryChangeTrigger.Instance.forItems(ore.getChunkItem().get()))
            .build(consumer);
    }

    private void registerOre(EnumOre ore, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ore.getChunkItem().get())
            .patternLine("xx")
            .patternLine("xx")
            .key('x', ore.getPieceItem().get())
            .setGroup(Constants.ModIds.EX_NIHILO_SEQUENTIA)
            .addCriterion("has_piece", InventoryChangeTrigger.Instance.forItems(ore.getPieceItem().get()))
            .build(consumer);
    }

    private void registerBarrels(Consumer<IFinishedRecipe> consumer) {
        registerBarrel(ModBlocks.BARREL_STONE.get(), Blocks.STONE, Blocks.STONE_SLAB, consumer);
        registerBarrel(ModBlocks.BARREL_WOOD.get(), ItemTags.PLANKS, ItemTags.WOODEN_SLABS, consumer);
    }

    private void registerBarrel(Block result, ITag.INamedTag<Item> walls, ITag.INamedTag<Item> base, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(result)
            .patternLine("x x")
            .patternLine("x x")
            .patternLine("x-x")
            .key('x', walls)
            .key('-', base)
            .setGroup(Constants.ModIds.EX_NIHILO_SEQUENTIA)
            .addCriterion("has_walls", hasItem(walls))
            .addCriterion("has_base", hasItem(base))
            .build(consumer);
    }

    private void registerBarrel(Block result, Block walls, Block base, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(result)
            .patternLine("x x")
            .patternLine("x x")
            .patternLine("x-x")
            .key('x', walls)
            .key('-', base)
            .setGroup(Constants.ModIds.EX_NIHILO_SEQUENTIA)
            .addCriterion("has_walls", InventoryChangeTrigger.Instance.forItems(walls))
            .addCriterion("has_base", InventoryChangeTrigger.Instance.forItems(base))
            .build(consumer);
    }

    private void registerPebbleBlocks(Consumer<IFinishedRecipe> consumer) {
        registerPebbleBlock(Blocks.ANDESITE, EnumPebbleType.ANDESITE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.COBBLESTONE, EnumPebbleType.STONE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.DIORITE, EnumPebbleType.DIORITE.getRegistryObject().get(), consumer);
        registerPebbleBlock(Blocks.GRANITE, EnumPebbleType.GRANITE.getRegistryObject().get(), consumer);
    }

    private void registerPebbleBlock(Block result, Item input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(result)
            .patternLine("xx")
            .patternLine("xx")
            .key('x', input)
            .setGroup(Constants.ModIds.EX_NIHILO_SEQUENTIA)
            .addCriterion("has_pebble", InventoryChangeTrigger.Instance.forItems(input))
            .build(consumer);
    }

    private void registerCrooks(Consumer<IFinishedRecipe> consumer) {
        registerCrook(EnumCrook.ANDESITE.getRegistryObject().get(), EnumPebbleType.ANDESITE.getRegistryObject()
            .get(), consumer);
        registerCrook(EnumCrook.BONE.getRegistryObject().get(), Items.BONE, consumer);
        registerCrook(EnumCrook.DIAMOND.getRegistryObject().get(), Items.DIAMOND, consumer);
        registerCrook(EnumCrook.DIORITE.getRegistryObject().get(), EnumPebbleType.DIORITE.getRegistryObject()
            .get(), consumer);
        registerCrook(EnumCrook.GOLD.getRegistryObject().get(), Items.GOLD_NUGGET, consumer);
        registerCrook(EnumCrook.GRANITE.getRegistryObject().get(), EnumPebbleType.GRANITE.getRegistryObject()
            .get(), consumer);
        registerCrook(EnumCrook.IRON.getRegistryObject().get(), Items.IRON_NUGGET, consumer);
        registerCrook(EnumCrook.STONE.getRegistryObject().get(), EnumPebbleType.STONE.getRegistryObject()
            .get(), consumer);
        registerCrook(EnumCrook.WOOD.getRegistryObject().get(), Items.STICK, consumer);
    }

    private void registerCrook(Item result, Item input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(result)
            .patternLine("xx")
            .patternLine(" x")
            .patternLine(" x")
            .key('x', input)
            .setGroup(Constants.ModIds.EX_NIHILO_SEQUENTIA)
            .addCriterion("has_pebble", InventoryChangeTrigger.Instance.forItems(input))
            .build(consumer);
    }
}
