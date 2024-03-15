package novamachina.exnihilosequentia.data.recipes.providers;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.data.recipes.CompostRecipeBuilder;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.novacore.data.recipes.ISubRecipeProvider;

public class CompostRecipes implements ISubRecipeProvider {
  @Override
  public void addRecipes(RecipeOutput consumer) {
    CompostRecipeBuilder.composting(ItemTags.SAPLINGS, 125).build(consumer, compostLoc("saplings"));
    CompostRecipeBuilder.composting(ItemTags.LEAVES, 125).build(consumer, compostLoc("leaves"));
    CompostRecipeBuilder.composting(ItemTags.FLOWERS, 100).build(consumer, compostLoc("flowers"));
    CompostRecipeBuilder.composting(ItemTags.FISHES, 150).build(consumer, compostLoc("fishes"));
    CompostRecipeBuilder.composting(ExNihiloTags.MEAT_COOKED, 200)
        .build(consumer, compostLoc("meat_cooked"));
    CompostRecipeBuilder.composting(ExNihiloTags.MEAT_UNCOOKED, 200)
        .build(consumer, compostLoc("meat_uncooked"));
    CompostRecipeBuilder.composting(Tags.Items.SEEDS, 80).build(consumer, compostLoc("seeds"));
    CompostRecipeBuilder.composting(Tags.Items.CROPS_WHEAT, 80)
        .build(consumer, compostLoc("wheat"));
    CompostRecipeBuilder.composting(Tags.Items.CROPS_CARROT, 100)
        .build(consumer, compostLoc("carrot"));
    CompostRecipeBuilder.composting(Tags.Items.CROPS_BEETROOT, 100)
        .build(consumer, compostLoc("beetroot"));
    CompostRecipeBuilder.composting(Tags.Items.CROPS_POTATO, 100)
        .build(consumer, compostLoc("potato"));
    CompostRecipeBuilder.composting(Tags.Items.CROPS_NETHER_WART, 100)
        .build(consumer, compostLoc("nether_wart"));
    CompostRecipeBuilder.composting(Tags.Items.EGGS, 80).build(consumer, compostLoc("eggs"));
    CompostRecipeBuilder.composting(Tags.Items.STRING, 40).build(consumer, compostLoc("string"));
    CompostRecipeBuilder.composting(Items.ROTTEN_FLESH, 100)
        .build(consumer, compostLoc("rotten_flesh"));
    CompostRecipeBuilder.composting(Items.SPIDER_EYE, 80).build(consumer, compostLoc("spider_eye"));
    CompostRecipeBuilder.composting(Items.BREAD, 160).build(consumer, compostLoc("bread"));
    CompostRecipeBuilder.composting(Blocks.BROWN_MUSHROOM, 100)
        .build(consumer, compostLoc("brown_mushroom"));
    CompostRecipeBuilder.composting(Blocks.RED_MUSHROOM, 100)
        .build(consumer, compostLoc("red_mushroom"));
    CompostRecipeBuilder.composting(Items.CRIMSON_FUNGUS, 100)
        .build(consumer, compostLoc("crimson_fungus"));
    CompostRecipeBuilder.composting(Items.WARPED_FUNGUS, 100)
        .build(consumer, compostLoc("warped_fungus"));
    CompostRecipeBuilder.composting(Items.PUMPKIN_PIE, 160)
        .build(consumer, compostLoc("pumpkin_pie"));
    CompostRecipeBuilder.composting(EXNItems.SILKWORM.asItem(), 40)
        .build(consumer, compostLoc("silkworm"));
    CompostRecipeBuilder.composting(EXNItems.COOKED_SILKWORM.asItem(), 40)
        .build(consumer, compostLoc("cooked_silkworm"));
    CompostRecipeBuilder.composting(Items.APPLE, 100).build(consumer, compostLoc("apple"));
    CompostRecipeBuilder.composting(Items.MELON_SLICE, 40)
        .build(consumer, compostLoc("melon_slice"));
    CompostRecipeBuilder.composting(Items.MELON, 1000 / 6).build(consumer, compostLoc("melon"));
    CompostRecipeBuilder.composting(Items.PUMPKIN, 1000 / 6).build(consumer, compostLoc("pumpkin"));
    CompostRecipeBuilder.composting(Items.CARVED_PUMPKIN, 1000 / 6)
        .build(consumer, compostLoc("carved_pumpkin"));
    CompostRecipeBuilder.composting(Items.JACK_O_LANTERN, 1000 / 6)
        .build(consumer, compostLoc("jack_o_lantern"));
    CompostRecipeBuilder.composting(Items.CACTUS, 100).build(consumer, compostLoc("cactus"));
    CompostRecipeBuilder.composting(Items.BAKED_POTATO, 150)
        .build(consumer, compostLoc("baked_potato"));
    CompostRecipeBuilder.composting(Items.POISONOUS_POTATO, 200)
        .build(consumer, compostLoc("poisonous_potato"));
    CompostRecipeBuilder.composting(Items.LILY_PAD, 100).build(consumer, compostLoc("lily_pad"));
    CompostRecipeBuilder.composting(Items.VINE, 100).build(consumer, compostLoc("vine"));
    CompostRecipeBuilder.composting(Items.WEEPING_VINES, 100)
        .build(consumer, compostLoc("weeping_vine"));
    CompostRecipeBuilder.composting(Items.TWISTING_VINES, 100)
        .build(consumer, compostLoc("twisting_vine"));
    CompostRecipeBuilder.composting(Items.TALL_GRASS, 100)
        .build(consumer, compostLoc("tall_grass"));
    CompostRecipeBuilder.composting(Items.SUGAR_CANE, 80).build(consumer, compostLoc("sugar_cane"));
    CompostRecipeBuilder.composting(EXNItems.GRASS_SEED.asItem(), 100)
        .build(consumer, compostLoc("grass_seed"));
    CompostRecipeBuilder.composting(EXNItems.MYCELIUM_SPORE.asItem(), 100)
        .build(consumer, compostLoc("mycelium_spore"));
    CompostRecipeBuilder.composting(EXNItems.CRIMSON_NYLIUM_SPORE.asItem(), 100)
        .build(consumer, compostLoc("crimson_nylium_spore"));
    CompostRecipeBuilder.composting(EXNItems.WARPED_NYLIUM_SPORE.asItem(), 100)
        .build(consumer, compostLoc("warped_nylium_spore"));
    CompostRecipeBuilder.composting(Items.SWEET_BERRIES, 100)
        .build(consumer, compostLoc("sweet_berries"));
    CompostRecipeBuilder.composting(Items.SPORE_BLOSSOM, 100)
        .build(consumer, compostLoc("spore_blossom"));
    CompostRecipeBuilder.composting(Items.KELP, 100).build(consumer, compostLoc("kelp"));
    CompostRecipeBuilder.composting(Items.MANGROVE_ROOTS, 100)
        .build(consumer, compostLoc("mangrove_roots"));
    CompostRecipeBuilder.composting(Items.CRIMSON_ROOTS, 100)
        .build(consumer, compostLoc("crimson_roots"));
    CompostRecipeBuilder.composting(Items.WARPED_ROOTS, 100)
        .build(consumer, compostLoc("warped_roots"));
    CompostRecipeBuilder.composting(Items.HANGING_ROOTS, 100)
        .build(consumer, compostLoc("hanging_roots"));
    CompostRecipeBuilder.composting(Items.BIG_DRIPLEAF, 100)
        .build(consumer, compostLoc("big_dripleaf"));
    CompostRecipeBuilder.composting(Items.SMALL_DRIPLEAF, 100)
        .build(consumer, compostLoc("small_dripleaf"));
    CompostRecipeBuilder.composting(Items.FERN, 100).build(consumer, compostLoc("small_fern"));
    CompostRecipeBuilder.composting(Items.LARGE_FERN, 100)
        .build(consumer, compostLoc("large_fern"));
    CompostRecipeBuilder.composting(Items.SEAGRASS, 100).build(consumer, compostLoc("sea_grass"));
    CompostRecipeBuilder.composting(Items.BAMBOO, 100).build(consumer, compostLoc("bamboo"));
    CompostRecipeBuilder.composting(Items.GLOW_BERRIES, 100)
        .build(consumer, compostLoc("glow_berries"));
  }

  protected ResourceLocation compostLoc(String id) {
    return new ResourceLocation(
        ExNihiloSequentia.MOD_ID, "compost/" + RecipeProviderUtilities.prependRecipePrefix(id));
  }
}
