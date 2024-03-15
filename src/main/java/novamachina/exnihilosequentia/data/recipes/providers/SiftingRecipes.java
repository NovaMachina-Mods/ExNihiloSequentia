package novamachina.exnihilosequentia.data.recipes.providers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.exnihilosequentia.data.recipes.SiftingRecipeBuilder;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.Ore;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.data.recipes.ISubRecipeProvider;

public class SiftingRecipes implements ISubRecipeProvider {
  @Override
  public void addRecipes(RecipeOutput consumer) {
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), EXNItems.PEBBLE_STONE)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 1.0F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 1.0F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("pebble_stone"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), EXNItems.PEBBLE_ANDESITE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("pebble_andesite"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), EXNItems.PEBBLE_DIORITE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("pebble_diorite"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), EXNItems.PEBBLE_GRANITE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("pebble_granite"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), EXNItems.PEBBLE_BASALT.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("pebble_basalt"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), EXNItems.PEBBLE_BLACKSTONE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("pebble_blackstone"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), EXNItems.PEBBLE_CALCITE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("pebble_calcite"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), EXNItems.PEBBLE_DEEPSLATE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("pebble_deepslate"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), EXNItems.PEBBLE_DRIPSTONE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("pebble_dripstone"));
    SiftingRecipeBuilder.sifting(
            Ingredient.of(EXNBlocks.CRUSHED_END_STONE), EXNItems.PEBBLE_END_STONE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("pebble_end_stone"));
    SiftingRecipeBuilder.sifting(
            Ingredient.of(EXNBlocks.CRUSHED_NETHERRACK), EXNItems.PEBBLE_NETHERRACK.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("pebble_netherrack"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), EXNItems.PEBBLE_TUFF.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.5F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("pebble_tuff"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), Items.WHEAT_SEEDS)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.7F))
        .build(consumer, siftingLoc("seed_wheat"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), Items.MELON_SEEDS)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.35F))
        .build(consumer, siftingLoc("seed_melon"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), Items.PUMPKIN_SEEDS)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.35F))
        .build(consumer, siftingLoc("seed_pumpkin"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), Items.BEETROOT_SEEDS)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.35F))
        .build(consumer, siftingLoc("seed_beetroot"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), EXNItems.MYCELIUM_SPORE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc("mycelium_spore"));
    SiftingRecipeBuilder.sifting(
            Ingredient.of(EXNBlocks.CRUSHED_NETHERRACK), EXNItems.CRIMSON_NYLIUM_SPORE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc("crimson_nylium_spore"));
    SiftingRecipeBuilder.sifting(
            Ingredient.of(EXNBlocks.CRUSHED_NETHERRACK), EXNItems.WARPED_NYLIUM_SPORE.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc("warped_nylium_spore"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.DIRT), EXNItems.GRASS_SEED.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc("seed_grass"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.MUD), Items.MANGROVE_PROPAGULE, true)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.03F))
        .build(consumer, siftingLoc("mangrove_propagule"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.SAND), Items.COCOA_BEANS)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.03F))
        .build(consumer, siftingLoc("cocoa_beans"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.SAND), Items.PRISMARINE_SHARD, true)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.02F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.04F))
        .build(consumer, siftingLoc("prismarine_shard"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.SAND), Items.PRISMARINE_CRYSTALS, true)
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.01F))
        .build(consumer, siftingLoc("prismarine_crystals"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.GRAVEL), Items.FLINT)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.25F))
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.25F))
        .build(consumer, siftingLoc("flint"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.GRAVEL), Items.COAL)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.125F))
        .build(consumer, siftingLoc("coal"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.GRAVEL), Items.LAPIS_LAZULI)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc("lapis_lazuli"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.GRAVEL), Items.DIAMOND)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.008F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.016F))
        .build(consumer, siftingLoc("diamond"));
    SiftingRecipeBuilder.sifting(Ingredient.of(EXNBlocks.CRUSHED_NETHERRACK), Items.NETHERITE_SCRAP)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.004F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.008F))
        .build(consumer, siftingLoc("netherite_scrap"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.GRAVEL), Items.EMERALD)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.008F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.016F))
        .build(consumer, siftingLoc("emerald"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.SOUL_SAND), Items.QUARTZ)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 1.0F))
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.33F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 1.0F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.8F))
        .build(consumer, siftingLoc("quartz"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.SOUL_SAND), Items.NETHER_WART)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc("nether_wart"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.SOUL_SAND), Items.GHAST_TEAR)
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.02F))
        .build(consumer, siftingLoc("ghast_tear"));
    SiftingRecipeBuilder.sifting(Ingredient.of(EXNBlocks.DUST), Items.BONE_MEAL)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.2F))
        .build(consumer, siftingLoc("bone_meal"));
    SiftingRecipeBuilder.sifting(Ingredient.of(EXNBlocks.DUST), Items.GUNPOWDER)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.07F))
        .build(consumer, siftingLoc("gunpowder"));
    SiftingRecipeBuilder.sifting(Ingredient.of(EXNBlocks.DUST), Items.REDSTONE)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.125F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.25F))
        .build(consumer, siftingLoc("redstone"));
    SiftingRecipeBuilder.sifting(Ingredient.of(EXNBlocks.DUST), Items.GLOWSTONE_DUST)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.0625F))
        .build(consumer, siftingLoc("glowstone"));
    SiftingRecipeBuilder.sifting(Ingredient.of(EXNBlocks.DUST), Items.BLAZE_POWDER)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc("blaze_powder"));
    SiftingRecipeBuilder.sifting(Ingredient.of(EXNBlocks.CRUSHED_END_STONE), Items.ENDER_PEARL)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.005F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.01F))
        .addRoll(new MeshWithChance(EXNItems.MESH_EMERALD.asItem().getType(), 0.015F))
        .addRoll(new MeshWithChance(EXNItems.MESH_NETHERITE.asItem().getType(), 0.02F))
        .build(consumer, siftingLoc("ender_pearl"));

    addIronOres(consumer);
    addGoldOres(consumer);
    addDefaultOres(consumer, EXNItems.COPPER);
    addDefaultOres(consumer, EXNItems.LEAD);
    addDefaultOres(consumer, EXNItems.NICKEL);
    addDefaultOres(consumer, EXNItems.SILVER);
    addDefaultOres(consumer, EXNItems.TIN);
    addDefaultOres(consumer, EXNItems.ALUMINUM);
    addDefaultOres(consumer, EXNItems.PLATINUM);
    addDefaultOres(consumer, EXNItems.URANIUM);
    addDefaultOres(consumer, EXNItems.ZINC);

    addSeeds(Items.DARK_OAK_SAPLING, consumer);
    addSeeds(Items.SPRUCE_SAPLING, consumer);
    addSeeds(Items.BIRCH_SAPLING, consumer);
    addSeeds(Items.JUNGLE_SAPLING, consumer);
    addSeeds(Items.ACACIA_SAPLING, consumer);
    addSeeds(Items.OAK_SAPLING, consumer);
    addSeeds(Items.CHERRY_SAPLING, consumer);
    addSeeds(Items.CARROT, consumer);
    addSeeds(Items.POTATO, consumer);
    addSeeds(Items.SWEET_BERRIES, consumer);
    addSeeds(Items.BAMBOO, consumer);
    addSeeds(Items.FERN, consumer);
    addSeeds(Items.LARGE_FERN, consumer);
    addSeeds(Items.CACTUS, consumer, Blocks.SAND);
    addSeeds(Items.SUGAR_CANE, consumer, Blocks.SAND);
    addWaterSeeds(Items.KELP, consumer);
    addWaterSeeds(Items.SEA_PICKLE, consumer);

    SiftingRecipeBuilder.sifting(Ingredient.of(EXNBlocks.CRUSHED_END_STONE), Items.CHORUS_FLOWER)
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.01f))
        .addRoll(new MeshWithChance(EXNItems.MESH_EMERALD.asItem().getType(), 0.02f))
        .addRoll(new MeshWithChance(EXNItems.MESH_NETHERITE.asItem().getType(), 0.05f))
        .build(consumer, siftingLoc("chorus_flower"));

    getLeavesSaplings()
        .forEach(
            (input, drop) -> {
              final ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(input);
              if (resourceLocation != null) {
                if (Objects.equals(
                    BuiltInRegistries.BLOCK.getKey(input), new ResourceLocation("jungle_leaves"))) {
                  SiftingRecipeBuilder.sifting(Ingredient.of(input), drop)
                      .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.025F))
                      .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
                      .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.075F))
                      .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.1F))
                      .build(consumer, siftingLoc(resourceLocation.getPath()));
                } else {
                  SiftingRecipeBuilder.sifting(Ingredient.of(input), drop)
                      .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
                      .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.1F))
                      .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.15F))
                      .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.2F))
                      .build(consumer, siftingLoc(resourceLocation.getPath()));
                }
              }
            });
    SiftingRecipeBuilder.sifting(Ingredient.of(ItemTags.LEAVES), Items.APPLE)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.1F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.15F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.2F))
        .build(consumer, siftingLoc("apple"));
    SiftingRecipeBuilder.sifting(Ingredient.of(ItemTags.LEAVES), Items.GOLDEN_APPLE)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.001F))
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.003F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.005F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.01F))
        .build(consumer, siftingLoc("golden_apple"));
    SiftingRecipeBuilder.sifting(Ingredient.of(ItemTags.LEAVES), EXNItems.SILKWORM.asItem())
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.025F))
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.1F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.2F))
        .build(consumer, siftingLoc("silkworm"));
    SiftingRecipeBuilder.sifting(
            Ingredient.of(ItemTags.SAND), EXNItems.TUBE_CORAL_LARVA.asItem(), true)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc("seed_blue_coral"));
    SiftingRecipeBuilder.sifting(
            Ingredient.of(ItemTags.SAND), EXNItems.BUBBLE_CORAL_LARVA.asItem(), true)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc("seed_purple_coral"));
    SiftingRecipeBuilder.sifting(
            Ingredient.of(ItemTags.SAND), EXNItems.BRAIN_CORAL_LARVA.asItem(), true)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc("seed_pink_coral"));
    SiftingRecipeBuilder.sifting(
            Ingredient.of(ItemTags.SAND), EXNItems.HORN_CORAL_LARVA.asItem(), true)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc("seed_yellow_coral"));
    SiftingRecipeBuilder.sifting(
            Ingredient.of(ItemTags.SAND), EXNItems.FIRE_CORAL_LARVA.asItem(), true)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc("seed_red_coral"));
    SiftingRecipeBuilder.sifting(Ingredient.of(ItemTags.SAND), Items.SEAGRASS, true)
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc("seagrass"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.COARSE_DIRT), Items.DIRT)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 1F))
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.4F))
        .build(consumer, siftingLoc("dirt"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.COARSE_DIRT), Items.GRAVEL)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.4F))
        .build(consumer, siftingLoc("gravel"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.GRAVEL), Items.AMETHYST_SHARD)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.1F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.15F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.2F))
        .build(consumer, siftingLoc("amethyst_shard"));
  }

  private void addIronOres(RecipeOutput consumer) {
    Item oreItem = EXNItems.IRON.getPieceItem();
    if (oreItem == null) {
      return;
    }
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.GRAVEL), oreItem)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.1F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.15F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.25F))
        .build(consumer, siftingLoc(EXNItems.IRON.getPieceId() + "_gravel"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.SAND), oreItem)
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.5F))
        .build(consumer, siftingLoc(EXNItems.IRON.getPieceId() + "_sand"));
  }

  private void addGoldOres(RecipeOutput consumer) {
    Item oreItem = EXNItems.GOLD.getPieceItem();
    if (oreItem == null) {
      return;
    }
    SiftingRecipeBuilder.sifting(Ingredient.of(EXNBlocks.CRUSHED_NETHERRACK), oreItem)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.25F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.25F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.4F))
        .build(consumer, siftingLoc(EXNItems.GOLD.getPieceId() + "_crushed_netherrack"));
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.GRAVEL), oreItem)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.075F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.15F))
        .build(consumer, siftingLoc(EXNItems.GOLD.getPieceId() + "_gravel"));
  }

  private void addDefaultOres(RecipeOutput consumer, Ore ore) {
    Item oreItem = ore.getPieceItem();
    if (oreItem == null) {
      return;
    }
    SiftingRecipeBuilder.sifting(Ingredient.of(Blocks.GRAVEL), oreItem)
        .addRoll(new MeshWithChance(EXNItems.MESH_FLINT.asItem().getType(), 0.05F))
        .addRoll(new MeshWithChance(EXNItems.MESH_IRON.asItem().getType(), 0.075F))
        .addRoll(new MeshWithChance(EXNItems.MESH_DIAMOND.asItem().getType(), 0.1F))
        .build(consumer, siftingLoc(ore.getPieceId() + "_gravel"));
  }

  private ResourceLocation siftingLoc(String id) {
    return new ResourceLocation(
        ExNihiloSequentia.MOD_ID, "sifting/" + RecipeProviderUtilities.prependRecipePrefix(id));
  }

  private void addSeeds(ItemLike seed, RecipeOutput consumer) {
    addSeeds(seed, consumer, Blocks.DIRT);
  }

  private void addSeeds(ItemLike seed, RecipeOutput consumer, Block inputBlock) {
    ResourceLocation resourceLocation = BuiltInRegistries.ITEM.getKey(seed.asItem());
    SiftingRecipeBuilder.sifting(Ingredient.of(inputBlock), seed)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc(resourceLocation.getPath()));
  }

  private void addWaterSeeds(ItemLike seed, RecipeOutput consumer) {
    ResourceLocation resourceLocation = BuiltInRegistries.ITEM.getKey(seed.asItem());
    SiftingRecipeBuilder.sifting(Ingredient.of(ItemTags.SAND), seed, true)
        .addRoll(new MeshWithChance(EXNItems.MESH_STRING.asItem().getType(), 0.05F))
        .build(consumer, siftingLoc(resourceLocation.getPath()));
  }

  private Map<Block, Item> getLeavesSaplings() {
    Map<Block, Item> saplingMap = new HashMap<>();
    saplingMap.put(Blocks.ACACIA_LEAVES, Items.ACACIA_SAPLING);
    saplingMap.put(Blocks.BIRCH_LEAVES, Items.BIRCH_SAPLING);
    saplingMap.put(Blocks.DARK_OAK_LEAVES, Items.DARK_OAK_SAPLING);
    saplingMap.put(Blocks.JUNGLE_LEAVES, Items.JUNGLE_SAPLING);
    saplingMap.put(Blocks.OAK_LEAVES, Items.OAK_SAPLING);
    saplingMap.put(Blocks.SPRUCE_LEAVES, Items.SPRUCE_SAPLING);
    saplingMap.put(Blocks.MANGROVE_LEAVES, Items.MANGROVE_PROPAGULE);
    saplingMap.put(Blocks.CHERRY_LEAVES, Items.CHERRY_SAPLING);
    return saplingMap;
  }
}
