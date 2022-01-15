package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.api.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipeBuilder;
import novamachina.exnihilosequentia.api.datagen.AbstractRecipeGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.dolls.EnumDoll;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.ore.OreItem;
import novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class ExNihiloRecipeGenerator extends AbstractRecipeGenerator {
    @Nonnull private static final String COBBLESTONE = "cobblestone";
    @Nonnull private static final String DOLL_CONDITION = "has_doll";
    @Nonnull private static final String GRAVEL_SUFFIX = "_gravel";
    @Nonnull private static final String LEAVES = "leaves";
    @Nonnull private static final String MATERIAL_CONDITION = "has_material";
    @Nonnull private static final String NETHERRACK = "netherrack";
    @Nonnull private static final String PORCELAIN_CLAY_CONDITION = "has_porcelain_clay";
    @Nonnull private static final Fluid water = Fluids.WATER;
    @Nonnull private static final Fluid lava = Fluids.LAVA;
    @Nonnull private static final Fluid witchwater = ExNihiloFluids.WITCH_WATER.get();
    @Nonnull private static final Fluid seawater = ExNihiloFluids.SEA_WATER.get();

    public ExNihiloRecipeGenerator(@Nonnull final DataGenerator generator) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    }

    @Override
    protected void buildShapelessRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        registerCrooks(consumer);
        registerPebbleBlocks(consumer);
        registerBarrels(consumer);
        registerCrucibles(consumer);
        registerSieves(consumer);
        registerOres(consumer);
        registerHammers(consumer);
        registerDolls(consumer);
        registerMeshes(consumer);
        registerMisc(consumer);
        registerCustomRecipes(consumer);
    }

    private Map<Block, Item> getLeavesSaplings() {
        @Nonnull final Map<Block, Item> saplingMap = new HashMap<>();
        saplingMap.put(Blocks.ACACIA_LEAVES, Items.ACACIA_SAPLING);
        saplingMap.put(Blocks.BIRCH_LEAVES, Items.BIRCH_SAPLING);
        saplingMap.put(Blocks.DARK_OAK_LEAVES, Items.DARK_OAK_SAPLING);
        saplingMap.put(Blocks.JUNGLE_LEAVES, Items.JUNGLE_SAPLING);
        saplingMap.put(Blocks.OAK_LEAVES, Items.OAK_SAPLING);
        saplingMap.put(Blocks.SPRUCE_LEAVES, Items.SPRUCE_SAPLING);

        return saplingMap;
    }

    private void registerBarrels(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        createBarrel(consumer, ExNihiloBlocks.BARREL_STONE, Tags.Items.STONE, Items.STONE_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_ACACIA, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_BIRCH, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_DARK_OAK, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_JUNGLE, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_OAK, Items.OAK_PLANKS, Items.OAK_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_SPRUCE, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_CRIMSON, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
        createBarrel(consumer, ExNihiloBlocks.BARREL_WARPED, Items.WARPED_PLANKS, Items.WARPED_SLAB);
    }

    private void registerCompostRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        createCompostRecipe(consumer, ItemTags.SAPLINGS, 125, "saplings");
        createCompostRecipe(consumer, ItemTags.LEAVES, 125, LEAVES);
        createCompostRecipe(consumer, ItemTags.FLOWERS, 100, "flowers");
        createCompostRecipe(consumer, ItemTags.FISHES, 150, "fishes");
        createCompostRecipe(consumer, ExNihiloTags.MEAT_COOKED, 200, "meat_cooked");
        createCompostRecipe(consumer, ExNihiloTags.MEAT_UNCOOKED, 200, "meat_uncooked");
        createCompostRecipe(consumer, Tags.Items.SEEDS, 80, "seeds");
        createCompostRecipe(consumer, Tags.Items.CROPS_WHEAT, 80, "wheat");
        createCompostRecipe(consumer, Tags.Items.CROPS_CARROT, 100, "carrot");
        createCompostRecipe(consumer, Tags.Items.CROPS_BEETROOT, 100, "beetroot");
        createCompostRecipe(consumer, Tags.Items.CROPS_POTATO, 100, "potato");
        createCompostRecipe(consumer, Tags.Items.CROPS_NETHER_WART, 100, "nether_wart");
        createCompostRecipe(consumer, Tags.Items.EGGS, 80, "eggs");
        createCompostRecipe(consumer, Tags.Items.STRING, 40, "string");
        createCompostRecipe(consumer, Items.ROTTEN_FLESH, 100, "rotten_flesh");
        createCompostRecipe(consumer, Items.SPIDER_EYE, 80, "spider_eye");
        createCompostRecipe(consumer, Items.BREAD, 160, "bread");
        createCompostRecipe(consumer, Blocks.BROWN_MUSHROOM, 100, "brown_mushroom");
        createCompostRecipe(consumer, Blocks.RED_MUSHROOM, 100, "red_mushroom");
        createCompostRecipe(consumer, Items.CRIMSON_FUNGUS, 100, "crimson_fungus");
        createCompostRecipe(consumer, Items.WARPED_FUNGUS, 100, "warped_fungus");
        createCompostRecipe(consumer, Items.PUMPKIN_PIE, 160, "pumpkin_pie");
        createCompostRecipe(consumer, ExNihiloItems.SILKWORM.get(), 40, "silkworm");
        createCompostRecipe(consumer, ExNihiloItems.COOKED_SILKWORM.get(), 40, "cooked_silkworm");
        createCompostRecipe(consumer, Items.APPLE, 100, "apple");
        createCompostRecipe(consumer, Items.MELON_SLICE, 40, "melon_slice");
        createCompostRecipe(consumer, Items.MELON, 1000 / 6, "melon");
        createCompostRecipe(consumer, Items.PUMPKIN, 1000 / 6, "pumpkin");
        createCompostRecipe(consumer, Items.CARVED_PUMPKIN, 1000 / 6, "carved_pumpkin");
        createCompostRecipe(consumer, Items.JACK_O_LANTERN, 1000 / 6, "jack_o_lantern");
        createCompostRecipe(consumer, Items.CACTUS, 100, "cactus");
        createCompostRecipe(consumer, Items.BAKED_POTATO, 150, "baked_potato");
        createCompostRecipe(consumer, Items.POISONOUS_POTATO, 200, "poisonous_potato");
        createCompostRecipe(consumer, Items.LILY_PAD, 100, "lily_pad");
        createCompostRecipe(consumer, Items.VINE, 100, "vine");
        createCompostRecipe(consumer, Items.WEEPING_VINES, 100, "weeping_vine");
        createCompostRecipe(consumer, Items.TWISTING_VINES, 100, "twisting_vine");
        createCompostRecipe(consumer, Items.TALL_GRASS, 100, "tall_grass");
        createCompostRecipe(consumer, Items.SUGAR_CANE, 80, "sugar_cane");
        @Nullable final RegistryObject<Item> grassSeedRegistryObject = EnumResource.GRASS_SEED.getRegistryObject();
        if (grassSeedRegistryObject != null)
            createCompostRecipe(consumer, grassSeedRegistryObject.get(), 100, "grass_seed");
        @Nullable final RegistryObject<Item> ancientSporeRegistryObject = EnumResource.ANCIENT_SPORE.getRegistryObject();
        if (ancientSporeRegistryObject != null)
            createCompostRecipe(consumer, ancientSporeRegistryObject.get(), 100, "ancient_spore");
        createCompostRecipe(consumer, Items.SWEET_BERRIES, 100, "sweet_berries");
    }

    private void registerCrookRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        createCrookRecipes(consumer, ItemTags.LEAVES, ExNihiloItems.SILKWORM.get(), 0.1F, LEAVES);
        createCrookRecipes(consumer, ExNihiloBlocks.INFESTED_LEAVES.get(), ExNihiloItems.SILKWORM.get(), 0.2F, "silkworm");
        createCrookRecipes(consumer, ExNihiloBlocks.INFESTED_LEAVES.get(), Items.STRING, 0.5F, "string");
    }

    private void registerCrooks(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        @Nullable final RegistryObject<Item> andesiteCrook = EnumCrook.ANDESITE.getRegistryObject();
        @Nullable final RegistryObject<Item> andesitePebble = EnumPebbleType.ANDESITE.getRegistryObject();
        if (andesiteCrook != null && andesitePebble != null)
            createCrook(andesiteCrook.get(), andesitePebble.get(), consumer);

        @Nullable final RegistryObject<Item> boneCrook = EnumCrook.BONE.getRegistryObject();
        if (boneCrook != null )
            createCrook(boneCrook.get(), Tags.Items.BONES, consumer);

        @Nullable final RegistryObject<Item> diamondCrook = EnumCrook.DIAMOND.getRegistryObject();
        if (diamondCrook != null)
            createCrook(diamondCrook.get(), Tags.Items.GEMS_DIAMOND, consumer);

        @Nullable final RegistryObject<Item> dioriteCrook = EnumCrook.DIORITE.getRegistryObject();
        @Nullable final RegistryObject<Item> dioritePebble = EnumPebbleType.DIORITE.getRegistryObject();
        if (dioriteCrook != null && dioritePebble != null)
            createCrook(dioriteCrook.get(), dioritePebble.get(), consumer);

        @Nullable final RegistryObject<Item> goldCrook = EnumCrook.GOLD.getRegistryObject();
        if (goldCrook != null)
            createCrook(EnumCrook.GOLD.getRegistryObject().get(), Tags.Items.NUGGETS_GOLD, consumer);

        @Nullable final RegistryObject<Item> graniteCrook = EnumCrook.GRANITE.getRegistryObject();
        @Nullable final RegistryObject<Item> granitePebble = EnumPebbleType.GRANITE.getRegistryObject();
        if (graniteCrook != null && granitePebble != null)
            createCrook(graniteCrook.get(), granitePebble.get(), consumer);

        @Nullable final RegistryObject<Item> ironCrook = EnumCrook.IRON.getRegistryObject();
        if (ironCrook != null)
            createCrook(ironCrook.get(), Tags.Items.NUGGETS_IRON, consumer);

        @Nullable final RegistryObject<Item> stoneCrook = EnumCrook.STONE.getRegistryObject();
        @Nullable final RegistryObject<Item> stonePebble = EnumPebbleType.STONE.getRegistryObject();
        if (stoneCrook != null && stonePebble != null)
            createCrook(EnumCrook.STONE.getRegistryObject().get(), EnumPebbleType.STONE.getRegistryObject().get(), consumer);

        @Nullable final RegistryObject<Item> woodCrook = EnumCrook.WOOD.getRegistryObject();
        if (woodCrook != null)
            createCrook(woodCrook.get(), Tags.Items.RODS_WOODEN, consumer);
    }

    private void registerCrucibles(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_ACACIA, Items.ACACIA_LOG, Items.ACACIA_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_BIRCH, Items.BIRCH_LOG, Items.BIRCH_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_DARK_OAK, Items.DARK_OAK_LOG, Items.DARK_OAK_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_JUNGLE, Items.JUNGLE_LOG, Items.JUNGLE_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_OAK, Items.OAK_LOG, Items.OAK_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_SPRUCE, Items.SPRUCE_LOG, Items.SPRUCE_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_CRIMSON, Items.CRIMSON_STEM, Items.CRIMSON_SLAB);
        createCrucible(consumer, ExNihiloBlocks.CRUCIBLE_WARPED, Items.WARPED_STEM, Items.WARPED_SLAB);
    }

    private void registerCrucibleRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        createFiredCrucibleRecipes(consumer, Blocks.COBBLESTONE, 250,  "cobblestone");
        createFiredCrucibleRecipes(consumer, Blocks.DIORITE, 250,  "diorite");
        createFiredCrucibleRecipes(consumer, Blocks.ANDESITE, 250,  "andesite");
        createFiredCrucibleRecipes(consumer, Blocks.GRANITE, 250, "granite");
        createFiredCrucibleRecipes(consumer, Blocks.STONE, 250, "stone");
        createFiredCrucibleRecipes(consumer, Blocks.GRAVEL, 200, "gravel");
        createFiredCrucibleRecipes(consumer, ExNihiloBlocks.CRUSHED_ANDESITE.get(), 200, "crushed_andesite");
        createFiredCrucibleRecipes(consumer, ExNihiloBlocks.CRUSHED_DIORITE.get(), 200, "crushed_diorite");
        createFiredCrucibleRecipes(consumer, ExNihiloBlocks.CRUSHED_END_STONE.get(), 200, "crushed_end_stone");
        createFiredCrucibleRecipes(consumer, ExNihiloBlocks.CRUSHED_GRANITE.get(), 200, "crushed_granite");
        createFiredCrucibleRecipes(consumer, ExNihiloBlocks.CRUSHED_NETHERRACK.get(), 200, "crushed_netherrack");
        createFiredCrucibleRecipes(consumer, Blocks.SAND, 100, "sand");
        createFiredCrucibleRecipes(consumer, ExNihiloBlocks.DUST.get(), 50, "dust");
        createFiredCrucibleRecipes(consumer, Blocks.NETHERRACK, 1000, "netherrack");
        createFiredCrucibleRecipes(consumer, Blocks.OBSIDIAN, 1000, "obsidian");

        createWaterCrucibleRecipes(consumer, ItemTags.SAPLINGS, 250, "saplings");
        createWaterCrucibleRecipes(consumer, ItemTags.LEAVES, 250, "leaves");
    }

    private void registerCustomRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        registerHammerRecipes(consumer);
        registerCrookRecipes(consumer);
        registerCompostRecipes(consumer);
        registerFluidItemRecipes(consumer);
        registerFluidOnTopRecipes(consumer);
        registerFluidTransformRecipes(consumer);
        registerCrucibleRecipes(consumer);
        registerHeatRecipes(consumer);
        registerSieveRecipes(consumer);
    }

    private void registerDefaultOres(@Nonnull final Consumer<IFinishedRecipe> consumer, @Nonnull final EnumOre ore) {
        @Nullable final RegistryObject<OreItem> oreItem = ore.getPieceItem();
        if (oreItem == null)
            return;
        SieveRecipeBuilder.builder()
                .input(Ingredient.of(Blocks.GRAVEL))
                .addResult(oreItem.get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.1F))
                .build(consumer, sieveLoc(ore.getPieceName() + GRAVEL_SUFFIX));
    }

    private void registerDolls(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        @Nullable final RegistryObject<Item> craftingDoll = EnumResource.CRAFTING_DOLL.getRegistryObject();
        if (craftingDoll == null)
            return;
        ShapedRecipeBuilder.shaped(EnumDoll.SHULKER.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Tags.Items.DYES_PURPLE)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', Tags.Items.END_STONES)
                .define('b', Tags.Items.ENDER_PEARLS)
                .define('m', craftingDoll.get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.Instance.hasItems(craftingDoll.get()))
                .save(consumer, createSaveLocation(EnumDoll.SHULKER.getRegistryObject().getId()));
        ShapedRecipeBuilder.shaped(EnumDoll.GUARDIAN.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Tags.Items.GEMS_PRISMARINE)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', Tags.Items.DUSTS_REDSTONE)
                .define('b', ItemTags.FISHES)
                .define('m', craftingDoll.get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.Instance.hasItems(craftingDoll.get()))
                .save(consumer, createSaveLocation(EnumDoll.GUARDIAN.getRegistryObject().getId()));
        @Nullable final RegistryObject<Item> beehiveFrame = EnumResource.BEEHIVE_FRAME.getRegistryObject();
        if (beehiveFrame != null) {
            ShapedRecipeBuilder.shaped(EnumDoll.BEE.getRegistryObject().get())
                    .pattern("ctc")
                    .pattern("sms")
                    .pattern("cbc")
                    .define('c', Tags.Items.DYES_YELLOW)
                    .define('s', Tags.Items.DUSTS_GLOWSTONE)
                    .define('t', ItemTags.FLOWERS)
                    .define('b', EnumResource.BEEHIVE_FRAME.getRegistryObject().get())
                    .define('m', craftingDoll.get())
                    .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.Instance.hasItems(craftingDoll.get()))
                    .save(consumer, createSaveLocation(EnumDoll.BEE.getRegistryObject().getId()));
        }
        ShapedRecipeBuilder.shaped(EnumDoll.BLAZE.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Items.BLAZE_POWDER)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', Tags.Items.DUSTS_REDSTONE)
                .define('b', Tags.Items.CROPS_NETHER_WART)
                .define('m', craftingDoll.get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.Instance.hasItems(craftingDoll.get()))
                .save(consumer, createSaveLocation(EnumDoll.BLAZE.getRegistryObject().getId()));
        ShapedRecipeBuilder.shaped(EnumDoll.ENDERMAN.getRegistryObject().get())
                .pattern("ctc")
                .pattern("sms")
                .pattern("cbc")
                .define('c', Tags.Items.DYES_BLACK)
                .define('s', Tags.Items.DUSTS_GLOWSTONE)
                .define('t', Tags.Items.DUSTS_REDSTONE)
                .define('b', Tags.Items.CROPS_NETHER_WART)
                .define('m', craftingDoll.get())
                .unlockedBy(DOLL_CONDITION, InventoryChangeTrigger.Instance.hasItems(craftingDoll.get()))
                .save(consumer, createSaveLocation(EnumDoll.ENDERMAN.getRegistryObject().getId()));
    }

    private void registerFluidItemRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        createFluidItemRecipes(consumer, water, ExNihiloBlocks.DUST.get().asItem(), Blocks.CLAY, "clay");
        createFluidItemRecipes(consumer, lava, Tags.Items.DUSTS_REDSTONE, Blocks.NETHERRACK, NETHERRACK);
        createFluidItemRecipes(consumer, lava, Tags.Items.DUSTS_GLOWSTONE, Blocks.END_STONE, "end_stone");
        createFluidItemRecipes(consumer, witchwater, Tags.Items.SAND, Blocks.SOUL_SAND, "soul_sand");
        createFluidItemRecipes(consumer, witchwater, Items.COARSE_DIRT, Blocks.SOUL_SOIL, "soul_soil");
        createFluidItemRecipes(consumer, witchwater, Tags.Items.MUSHROOMS, Blocks.SLIME_BLOCK, "slime");

        @Nullable final RegistryObject<Item> blueCoralSeed = EnumResource.BLUE_CORAL_SEED.getRegistryObject();
        if (blueCoralSeed != null)
            createFluidItemRecipes(consumer, seawater, blueCoralSeed.get(), Blocks.TUBE_CORAL_BLOCK, "tube_coral");

        @Nullable final RegistryObject<Item> redCoralSeed = EnumResource.RED_CORAL_SEED.getRegistryObject();
        if (redCoralSeed != null)
            createFluidItemRecipes(consumer, seawater, redCoralSeed.get(), Blocks.FIRE_CORAL_BLOCK, "fire_coral");

        @Nullable final RegistryObject<Item> pinkCoralSeed = EnumResource.PINK_CORAL_SEED.getRegistryObject();
        if (pinkCoralSeed != null)
            createFluidItemRecipes(consumer, seawater, pinkCoralSeed.get(), Blocks.BRAIN_CORAL_BLOCK, "brain_coral");

        @Nullable final RegistryObject<Item> purpleCoralSeed = EnumResource.PURPLE_CORAL_SEED.getRegistryObject();
        if (purpleCoralSeed != null)
            createFluidItemRecipes(consumer, seawater, purpleCoralSeed.get(), Blocks.BUBBLE_CORAL_BLOCK, "bubble_coral");

        @Nullable final RegistryObject<Item> yellowCoralSeed = EnumResource.YELLOW_CORAL_SEED.getRegistryObject();
        if (yellowCoralSeed != null)
            createFluidItemRecipes(consumer, seawater, yellowCoralSeed.get(), Blocks.HORN_CORAL_BLOCK, "horn_coral");

        @Nullable final RegistryObject<Item> ancientSpore = EnumResource.ANCIENT_SPORE.getRegistryObject();
        if (ancientSpore != null)
            createFluidItemRecipes(consumer, witchwater, ancientSpore.get(), Blocks.BROWN_MUSHROOM_BLOCK, "brown_mushroom");

        createFluidItemRecipes(consumer, witchwater, Blocks.BROWN_MUSHROOM_BLOCK.asItem(), Blocks.RED_MUSHROOM_BLOCK, "red_mushroom");
    }

    private void registerFluidOnTopRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        createFluidOnTopRecipes(consumer, lava, water, Blocks.OBSIDIAN, "obsidian");
        createFluidOnTopRecipes(consumer, water, lava, Blocks.COBBLESTONE, COBBLESTONE);
    }

    private void registerFluidTransformRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        createFluidTransformRecipes(consumer, water, Blocks.MYCELIUM.asItem(), witchwater, "witch_water");
        createFluidTransformRecipes(consumer, water, Tags.Items.SAND, seawater, "sea_water");
    }

    private void registerGoldOres(@Nonnull final Consumer<IFinishedRecipe> consumer, @Nonnull final EnumOre ore) {
        @Nullable final RegistryObject<OreItem> oreItem = ore.getPieceItem();
        if (oreItem == null)
            return;
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.CRUSHED_NETHERRACK.get()))
                .addResult(oreItem.get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.25F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.25F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.4F))
                .build(consumer, sieveLoc(ore.getPieceName() + "_crushed_netherrack"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(oreItem.get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.15F))
                .build(consumer, sieveLoc(ore.getPieceName() + GRAVEL_SUFFIX));
    }

    private void registerHammerRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        createHammerRecipes(consumer, Blocks.STONE, Blocks.COBBLESTONE, COBBLESTONE);
        createHammerRecipes(consumer, Blocks.COBBLESTONE, Blocks.GRAVEL, "gravel");
        createHammerRecipes(consumer, Blocks.GRAVEL, Blocks.SAND, "sand");
        createHammerRecipes(consumer, Blocks.SAND, ExNihiloBlocks.DUST.get(), "dust");
        createHammerRecipes(consumer, Blocks.NETHERRACK, ExNihiloBlocks.CRUSHED_NETHERRACK.get(), NETHERRACK);
        createHammerRecipes(consumer, Blocks.ANDESITE, ExNihiloBlocks.CRUSHED_ANDESITE.get(), "andesite");
        createHammerRecipes(consumer, Blocks.DIORITE, ExNihiloBlocks.CRUSHED_DIORITE.get(), "diorite");
        createHammerRecipes(consumer, Blocks.GRANITE, ExNihiloBlocks.CRUSHED_GRANITE.get(), "granite");
        createHammerRecipes(consumer, Blocks.END_STONE, ExNihiloBlocks.CRUSHED_END_STONE.get(), "end_stone");

        createHammerRecipes(consumer, Blocks.TUBE_CORAL_BLOCK, Blocks.TUBE_CORAL, "tube_coral");
        createHammerRecipes(consumer, Blocks.BRAIN_CORAL_BLOCK, Blocks.BRAIN_CORAL, "brain_coral");
        createHammerRecipes(consumer, Blocks.BUBBLE_CORAL_BLOCK, Blocks.BUBBLE_CORAL, "bubble_coral");
        createHammerRecipes(consumer, Blocks.FIRE_CORAL_BLOCK, Blocks.FIRE_CORAL, "fire_coral");
        createHammerRecipes(consumer, Blocks.HORN_CORAL_BLOCK, Blocks.HORN_CORAL, "horn_coral");
        createHammerRecipes(consumer, Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, "tube_coral_fan");
        createHammerRecipes(consumer, Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, "brain_coral_fan");
        createHammerRecipes(consumer, Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, "bubble_coral_fan");
        createHammerRecipes(consumer, Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, "fire_coral_fan");
        createHammerRecipes(consumer, Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, "horn_coral_fan");
    }

    private void registerHammers(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        @Nullable final RegistryObject<Item> diamondHammer = EnumHammer.DIAMOND.getRegistryObject();
        @Nullable final RegistryObject<Item> netheriteHammer = EnumHammer.NETHERITE.getRegistryObject();
        if (diamondHammer != null) {
            if (netheriteHammer != null)
                SmithingRecipeBuilder
                        .smithing(Ingredient.of(diamondHammer.get()), Ingredient
                                .of(Tags.Items.INGOTS_NETHERITE), netheriteHammer.get())
                        .unlocks("has_diamond_hammer", InventoryChangeTrigger.Instance
                                .hasItems(EnumHammer.DIAMOND.getRegistryObject().get()))
                        .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
                        .save(consumer, createSaveLocation(new ResourceLocation(
                                ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, EnumHammer.NETHERITE.hammerName)));
            createHammer(diamondHammer.get(), Tags.Items.GEMS_DIAMOND, consumer);
        }
        @Nullable final RegistryObject<Item> goldHammer = EnumHammer.GOLD.getRegistryObject();
        if (goldHammer != null)
            createHammer(goldHammer.get(), Tags.Items.INGOTS_GOLD, consumer);

        @Nullable final RegistryObject<Item> ironHammer = EnumHammer.IRON.getRegistryObject();
        if (ironHammer != null)
            createHammer(ironHammer.get(), Tags.Items.INGOTS_IRON, consumer);

        @Nullable final RegistryObject<Item> stoneHammer = EnumHammer.STONE.getRegistryObject();
        if (stoneHammer != null)
            createHammer(stoneHammer.get(), Tags.Items.COBBLESTONE, consumer);

        @Nullable final RegistryObject<Item> woodHammer = EnumHammer.WOOD.getRegistryObject();
        if (woodHammer != null)
            createHammer(woodHammer.get(), ItemTags.PLANKS, consumer);
    }

    private void registerHeatRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        createHeatRecipes(consumer, Blocks.LAVA, 3, "lava");
        createHeatRecipes(consumer, Blocks.FIRE, 4, "fire");
        createHeatRecipes(consumer, Blocks.TORCH, 1, "torch");
        createHeatRecipes(consumer, Blocks.WALL_TORCH, 1, "wall_torch");
        createHeatRecipes(consumer, Blocks.MAGMA_BLOCK, 2, "magma_block");
        createHeatRecipes(consumer, Blocks.GLOWSTONE, 2, "glowstone");
        createHeatRecipes(consumer, Blocks.SHROOMLIGHT, 2, "shroomlight");
        createHeatRecipes(consumer, Blocks.SOUL_FIRE, 4, "soul_fire");

        // Lit blocks
        StatePropertiesPredicate lit = StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.LIT, true).build();
        createHeatRecipes(consumer, Blocks.CAMPFIRE, 4, lit, "campfire");
        createHeatRecipes(consumer, Blocks.SOUL_CAMPFIRE, 4, lit, "soul_campfire");
        createHeatRecipes(consumer, Blocks.FURNACE, 3, lit, "furnace");
        createHeatRecipes(consumer, Blocks.REDSTONE_TORCH, 1, lit, "redstone_torch");
        createHeatRecipes(consumer, Blocks.REDSTONE_WALL_TORCH, 1, lit, "redstone_wall_torch");
    }

    private void registerIronOres(@Nonnull final Consumer<IFinishedRecipe> consumer, @Nonnull final EnumOre ore) {
        @Nullable final RegistryObject<OreItem> oreItem = ore.getPieceItem();
        if (oreItem == null)
            return;
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(oreItem.get())
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.1F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.15F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.25F))
                .build(consumer, sieveLoc(ore.getPieceName() + GRAVEL_SUFFIX));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND))
                .addResult(oreItem.get())
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.5F))
                .build(consumer, sieveLoc(ore.getPieceName() + "_sand"));
    }

    private void registerMeshes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        createMesh(EnumMesh.FLINT.getRegistryObject().get(), EnumMesh.STRING.getRegistryObject()
                .get(), Items.FLINT, consumer);
        createMesh(EnumMesh.IRON.getRegistryObject().get(), EnumMesh.FLINT.getRegistryObject()
                .get(), Tags.Items.INGOTS_IRON, consumer);
        createMesh(EnumMesh.DIAMOND.getRegistryObject().get(), EnumMesh.IRON.getRegistryObject()
                .get(), Tags.Items.GEMS_DIAMOND, consumer);
        createMesh(EnumMesh.EMERALD.getRegistryObject().get(), EnumMesh.DIAMOND.getRegistryObject()
                .get(), Tags.Items.GEMS_EMERALD, consumer);
        SmithingRecipeBuilder
                .smithing(Ingredient.of(EnumMesh.EMERALD.getRegistryObject().get()), Ingredient
                        .of(Tags.Items.INGOTS_NETHERITE), EnumMesh.NETHERITE.getRegistryObject().get())
                .unlocks("has_emerald_mesh", InventoryChangeTrigger.Instance
                        .hasItems(EnumMesh.EMERALD.getRegistryObject().get()))
                .unlocks(MATERIAL_CONDITION, has(Tags.Items.INGOTS_NETHERITE))
                .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, EnumMesh.NETHERITE
                        .getMeshName())));

        ShapedRecipeBuilder.shaped(EnumMesh.STRING.getRegistryObject().get())
                .pattern("iii")
                .pattern("iii")
                .pattern("iii")
                .define('i', Tags.Items.STRING)
                .unlockedBy("has_sieve", InventoryChangeTrigger.Instance.hasItems(ExNihiloBlocks.SIEVE_OAK.get(),
                        ExNihiloBlocks.SIEVE_ACACIA.get(), ExNihiloBlocks.SIEVE_BIRCH.get(), ExNihiloBlocks.SIEVE_JUNGLE.get(),
                        ExNihiloBlocks.SIEVE_DARK_OAK.get(), ExNihiloBlocks.SIEVE_SPRUCE.get(), ExNihiloBlocks.SIEVE_CRIMSON.get(),
                        ExNihiloBlocks.SIEVE_WARPED.get()))
                .save(consumer, createSaveLocation(EnumMesh.STRING.getRegistryObject().getId()));
    }

    private void registerMisc(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        @Nullable final RegistryObject<Item> beehiveFrame = EnumResource.BEEHIVE_FRAME.getRegistryObject();
        @Nullable final ResourceLocation beehive = Blocks.BEEHIVE.getRegistryName();
        if (beehiveFrame != null) {
            if (beehive != null)
                ShapedRecipeBuilder.shaped(Blocks.BEEHIVE)
                        .pattern("xxx")
                        .pattern("fff")
                        .pattern("xxx")
                        .define('x', ItemTags.PLANKS)
                        .define('f', beehiveFrame.get())
                        .unlockedBy("has_frame", InventoryChangeTrigger.Instance
                                .hasItems(beehiveFrame.get()))
                        .save(consumer, createSaveLocation(beehive));

            ShapedRecipeBuilder.shaped(beehiveFrame.get())
                    .pattern("xxx")
                    .pattern("xfx")
                    .pattern("xxx")
                    .define('x', Tags.Items.RODS_WOODEN)
                    .define('f', Tags.Items.STRING)
                    .unlockedBy("has_stick", InventoryChangeTrigger.Instance
                            .hasItems(ItemPredicate.Builder.item().of(Tags.Items.RODS_WOODEN).build()))
                    .unlockedBy("has_string", InventoryChangeTrigger.Instance
                            .hasItems(ItemPredicate.Builder.item().of(Tags.Items.STRING).build()))
                    .save(consumer, createSaveLocation(beehiveFrame.getId()));
        }

        createCookingRecipe(consumer, ExNihiloItems.SILKWORM.get(), ExNihiloItems.COOKED_SILKWORM.get(),
                0.1F, 600,0.1F, 100, "has_silkworm", ExNihiloItems.COOKED_SILKWORM.getId());
        createSmeltingRecipe(consumer, ExNihiloItems.SILKWORM.get(), ExNihiloItems.COOKED_SILKWORM.get(),
                0.1F, 200,0.1F, 100, "has_silkworm", ExNihiloItems.COOKED_SILKWORM.getId());

        createSmeltingRecipe(consumer, ExNihiloBlocks.CRUCIBLE_UNFIRED.get().asItem(), ExNihiloBlocks.CRUCIBLE_FIRED.get().asItem(),
                0.7F, 200, 0.7F, 100, "has_uncooked_crucible", ExNihiloBlocks.CRUCIBLE_FIRED.getId());

        @Nullable final RegistryObject<Item> porcelainClay = EnumResource.PORCELAIN_CLAY.getRegistryObject();
        if (porcelainClay != null)
            ShapedRecipeBuilder.shaped(ExNihiloBlocks.CRUCIBLE_UNFIRED.get())
                    .pattern("c c")
                    .pattern("c c")
                    .pattern("ccc")
                    .define('c', porcelainClay.get())
                    .unlockedBy(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.Instance
                            .hasItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                    .save(consumer, createSaveLocation(ExNihiloBlocks.CRUCIBLE_UNFIRED.getId()));

        @Nullable final RegistryObject<Item> craftingDoll = EnumResource.CRAFTING_DOLL.getRegistryObject();
        if (craftingDoll != null) {
            ShapedRecipeBuilder.shaped(craftingDoll.get(), 4)
                    .pattern("xex")
                    .pattern(" x ")
                    .pattern("x x")
                    .define('x', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                    .define('e', Tags.Items.GEMS_DIAMOND)
                    .unlockedBy(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.Instance
                            .hasItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                    .unlockedBy("has_diamond", InventoryChangeTrigger.Instance
                            .hasItems(ItemPredicate.Builder.item().of(Tags.Items.GEMS_DIAMOND).build()))
                    .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "doll_x4")));

                ShapedRecipeBuilder.shaped(craftingDoll.get(), 6)
                        .pattern("xex")
                        .pattern(" x ")
                        .pattern("x x")
                        .define('x', EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                        .define('e', Tags.Items.GEMS_EMERALD)
                        .unlockedBy(PORCELAIN_CLAY_CONDITION, InventoryChangeTrigger.Instance
                                .hasItems(EnumResource.PORCELAIN_CLAY.getRegistryObject().get()))
                        .unlockedBy("has_emerald", InventoryChangeTrigger.Instance
                                .hasItems(ItemPredicate.Builder.item().of(Tags.Items.GEMS_EMERALD).build()))
                        .save(consumer, createSaveLocation(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "doll_x6")));
        }

        ShapedRecipeBuilder.shaped(ExNihiloBlocks.END_CAKE.get())
                .pattern("ece")
                .pattern("eke")
                .pattern("ece")
                .define('e', Items.ENDER_EYE)
                .define('c', Items.END_CRYSTAL)
                .define('k', Items.CAKE)
                .unlockedBy("has_ender_pearl", InventoryChangeTrigger.Instance.hasItems(Items.ENDER_PEARL))
                .save(consumer, createSaveLocation(ExNihiloBlocks.END_CAKE.getId()));

        ShapelessRecipeBuilder.shapeless(EnumResource.PORCELAIN_CLAY.getRegistryObject().get())
                .requires(ExNihiloTags.CLAY)
                .requires(Items.BONE_MEAL)
                .unlockedBy("has_clay", InventoryChangeTrigger.Instance
                        .hasItems(ItemPredicate.Builder.item().of(ExNihiloTags.CLAY).build()))
                .save(consumer, createSaveLocation(EnumResource.PORCELAIN_CLAY.getRegistryObject().getId()));

        @Nullable final RegistryObject<Item> blackstonePebble = EnumPebbleType.BLACKSTONE.getRegistryObject();
        @Nullable final RegistryObject<OreItem> goldChunk = EnumOre.GOLD.getChunkItem();
        @Nullable final ResourceLocation gildedBlackstoneResourceLocation = Blocks.GILDED_BLACKSTONE.getRegistryName();
        if (blackstonePebble != null && goldChunk != null && gildedBlackstoneResourceLocation != null)
            ShapedRecipeBuilder.shaped(Blocks.GILDED_BLACKSTONE)
                    .pattern("xxx")
                    .pattern("xgx")
                    .pattern("xxx")
                    .define('x', blackstonePebble.get())
                    .define('g', goldChunk.get())
                    .unlockedBy("has_gold", InventoryChangeTrigger.Instance
                            .hasItems(goldChunk.get()))
                    .save(consumer, createSaveLocation(gildedBlackstoneResourceLocation));
        @Nullable final ResourceLocation cryingObsidianResourceLocation = Blocks.CRYING_OBSIDIAN.getRegistryName();
        if (cryingObsidianResourceLocation != null)
            ShapedRecipeBuilder.shaped(Blocks.CRYING_OBSIDIAN)
                    .pattern(" o ")
                    .pattern("obo")
                    .pattern(" o ")
                    .define('b', Items.WATER_BUCKET)
                    .define('o', Blocks.OBSIDIAN)
                    .unlockedBy("has_obsidian", InventoryChangeTrigger.Instance
                            .hasItems(Blocks.OBSIDIAN))
                    .save(consumer, createSaveLocation(cryingObsidianResourceLocation));
        @Nullable final ResourceLocation ancientDebrisResourceLocation = Blocks.ANCIENT_DEBRIS.getRegistryName();
        if (ancientDebrisResourceLocation != null)
            ShapelessRecipeBuilder.shapeless(Blocks.ANCIENT_DEBRIS)
                    .requires(Items.NETHERITE_SCRAP)
                    .requires(Blocks.OBSIDIAN)
                    .unlockedBy("has_scrap", InventoryChangeTrigger.Instance
                            .hasItems(Items.NETHERITE_SCRAP))
                    .save(consumer, createSaveLocation(ancientDebrisResourceLocation));
    }

    private void registerOres(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        for (@Nonnull final EnumOre ore : EnumOre.values()) {
            registerOre(ore, consumer);
            @Nullable final RegistryObject<OreItem> oreChunk = ore.getChunkItem();
            if (!ore.isVanilla() && oreChunk != null) {
                @Nullable Item ingotItem = ore.getIngotItem();
                if (ingotItem == null) {
                    @Nullable final RegistryObject<OreItem> ingotRegistryItem = ore.getIngotRegistryItem();
                    if (ingotRegistryItem != null) {
                        ingotItem = ingotRegistryItem.get();
                    }
                }
                createSmeltingRecipe(consumer, oreChunk.get(), ingotItem,0.7F, 200, 0.7F, 100, CHUNK_CONDITION,
                        new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ore.getIngotName()));
            }
            if (ore.isVanilla()) {
                if (ore == EnumOre.IRON && oreChunk != null) {
                    createSmeltingRecipe(consumer, oreChunk.get(), Items.IRON_INGOT,
                            0.7F, 200, 0.7F, 100, CHUNK_CONDITION,
                            new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "ingot_iron"));
                }
                if (ore == EnumOre.GOLD && oreChunk != null) {
                    createSmeltingRecipe(consumer, oreChunk.get(), Items.GOLD_INGOT,
                            0.7F, 200, 0.7F, 100, CHUNK_CONDITION,
                            new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "ingot_gold"));
                }
            }
        }
    }

    private void registerPebbleBlocks(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        @Nullable final RegistryObject<Item> andesitePebble = EnumPebbleType.ANDESITE.getRegistryObject();
        if (andesitePebble != null)
            createPebbleBlock(Blocks.ANDESITE, andesitePebble.get(), consumer);
        @Nullable final RegistryObject<Item> stonePebble = EnumPebbleType.STONE.getRegistryObject();
        if (stonePebble != null)
            createPebbleBlock(Blocks.COBBLESTONE, stonePebble.get(), consumer);
        @Nullable final RegistryObject<Item> dioritePebble = EnumPebbleType.DIORITE.getRegistryObject();
        if (dioritePebble != null)
            createPebbleBlock(Blocks.DIORITE, dioritePebble.get(), consumer);
        @Nullable final RegistryObject<Item> granitePebble = EnumPebbleType.GRANITE.getRegistryObject();
        if (granitePebble != null)
            createPebbleBlock(Blocks.GRANITE, granitePebble.get(), consumer);
        @Nullable final RegistryObject<Item> basaltPebble = EnumPebbleType.BASALT.getRegistryObject();
        if (basaltPebble != null)
            createPebbleBlock(Blocks.BASALT, basaltPebble.get(), consumer);
        @Nullable final RegistryObject<Item> blackstonePebble = EnumPebbleType.BLACKSTONE.getRegistryObject();
        if (blackstonePebble != null)
            createPebbleBlock(Blocks.BLACKSTONE, blackstonePebble.get(), consumer);
    }

    private void registerSieves(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        createSieve(consumer, ExNihiloBlocks.SIEVE_ACACIA, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_BIRCH, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_DARK_OAK, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_JUNGLE, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_OAK, Items.OAK_PLANKS, Items.OAK_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_SPRUCE, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_CRIMSON, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
        createSieve(consumer, ExNihiloBlocks.SIEVE_WARPED, Items.WARPED_PLANKS, Items.WARPED_SLAB);
    }

    private void registerSieveRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        @Nullable final RegistryObject<Item> stonePebble = EnumPebbleType.STONE.getRegistryObject();
        if (stonePebble != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                    .addResult(stonePebble.get())
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 1.0F))
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 1.0F))
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                    .build(consumer, sieveLoc("pebble_stone"));
        @Nullable final RegistryObject<Item> andesitePebble = EnumPebbleType.ANDESITE.getRegistryObject();
        if (andesitePebble != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                    .addResult(andesitePebble.get())
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                    .build(consumer, sieveLoc("pebble_andesite"));
        @Nullable final RegistryObject<Item> dioritePebble = EnumPebbleType.DIORITE.getRegistryObject();
        if (dioritePebble != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                    .addResult(dioritePebble.get())
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                    .build(consumer, sieveLoc("pebble_diorite"));
        @Nullable final RegistryObject<Item> granitePebble = EnumPebbleType.GRANITE.getRegistryObject();
        if (granitePebble != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                    .addResult(granitePebble.get())
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                    .build(consumer, sieveLoc("pebble_granite"));
        @Nullable final RegistryObject<Item> basaltPebble = EnumPebbleType.BASALT.getRegistryObject();
        if (basaltPebble != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                    .addResult(basaltPebble.get())
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                    .build(consumer, sieveLoc("pebble_basalt"));
        @Nullable final RegistryObject<Item> blackstonePebble = EnumPebbleType.BLACKSTONE.getRegistryObject();
        if (blackstonePebble != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                    .addResult(blackstonePebble.get())
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.5F))
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                    .build(consumer, sieveLoc("pebble_blackstone"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(Items.WHEAT_SEEDS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.7F))
                .build(consumer, sieveLoc("seed_wheat"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(Items.MELON_SEEDS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.35F))
                .build(consumer, sieveLoc("seed_melon"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(Items.PUMPKIN_SEEDS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.35F))
                .build(consumer, sieveLoc("seed_pumpkin"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                .addResult(Items.BEETROOT_SEEDS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.35F))
                .build(consumer, sieveLoc("seed_beetroot"));
        @Nullable final RegistryObject<Item> ancientSpore = EnumResource.ANCIENT_SPORE.getRegistryObject();
        if (ancientSpore != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                    .addResult(ancientSpore.get())
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                    .build(consumer, sieveLoc("ancient_spore"));
        @Nullable final RegistryObject<Item> grassSeed = EnumResource.GRASS_SEED.getRegistryObject();
        if (grassSeed != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                    .addResult(EnumResource.GRASS_SEED.getRegistryObject().get())
                    .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                    .build(consumer, sieveLoc("seed_grass"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND))
                .addResult(Items.COCOA_BEANS)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.03F))
                .build(consumer, sieveLoc("cocoa_beans"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND))
                .addResult(Items.PRISMARINE_SHARD)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.02F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.04F))
                .build(consumer, sieveLoc("prismarine_shard"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND))
                .addResult(Items.PRISMARINE_CRYSTALS)
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.01F))
                .build(consumer, sieveLoc("prismarine_crystals"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(Items.FLINT)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.25F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.25F))
                .build(consumer, sieveLoc("flint"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(Items.COAL)
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.125F))
                .build(consumer, sieveLoc("coal"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(Items.LAPIS_LAZULI)
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .build(consumer, sieveLoc("lapis_lazuli"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(Items.DIAMOND)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.008F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.016F))
                .build(consumer, sieveLoc("diamond"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.CRUSHED_NETHERRACK.get()))
                .addResult(Items.NETHERITE_SCRAP)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.004F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.008F))
                .build(consumer, sieveLoc("netherite_scrap"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.GRAVEL))
                .addResult(Items.EMERALD)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.008F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.016F))
                .build(consumer, sieveLoc("emerald"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SOUL_SAND))
                .addResult(Items.QUARTZ)
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 1.0F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.33F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 1.0F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.8F))
                .build(consumer, sieveLoc("quartz"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SOUL_SAND))
                .addResult(Items.NETHER_WART)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.1F))
                .build(consumer, sieveLoc("nether_wart"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SOUL_SAND))
                .addResult(Items.GHAST_TEAR)
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.02F))
                .build(consumer, sieveLoc("ghast_tear"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
                .addResult(Items.BONE_MEAL)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.2F))
                .build(consumer, sieveLoc("bone_meal"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
                .addResult(Items.GUNPOWDER)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.07F))
                .build(consumer, sieveLoc("gunpowder"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
                .addResult(Items.REDSTONE)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.125F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.25F))
                .build(consumer, sieveLoc("redstone"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
                .addResult(Items.GLOWSTONE_DUST)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.0625F))
                .build(consumer, sieveLoc("glowstone"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
                .addResult(Items.BLAZE_POWDER)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .build(consumer, sieveLoc("blaze_powder"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.CRUSHED_END_STONE.get()))
                .addResult(Items.ENDER_PEARL)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.005F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.01F))
                .addRoll(new MeshWithChance(EnumMesh.EMERALD, 0.015F))
                .addRoll(new MeshWithChance(EnumMesh.NETHERITE, 0.02F))
                .build(consumer, sieveLoc("ender_pearl"));

        for (EnumOre ore : EnumOre.values()) {
            switch (ore) {
                case IRON:
                    registerIronOres(consumer, ore);
                    break;
                case GOLD:
                    registerGoldOres(consumer, ore);
                    break;
                default:
                    registerDefaultOres(consumer, ore);
            }
        }

        for (@Nonnull final EnumSeed seed : EnumSeed.values()) {
            @Nullable final RegistryObject<Item> registryObject = seed.getRegistryObject();
            if (registryObject == null)
                continue;
            if (seed != EnumSeed.SEED_PICKLE && seed != EnumSeed.SEED_KELP) {
                SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.DIRT))
                        .addResult(registryObject.get())
                        .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                        .build(consumer, sieveLoc(seed.getSeedName()));
            } else {
                SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND))
                        .addResult(registryObject.get())
                        .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                        .isWaterlogged()
                        .build(consumer, sieveLoc(seed.getSeedName()));
            }
        }

        getLeavesSaplings().forEach((input, drop) -> {
            @Nullable final ResourceLocation resourceLocation = input.getRegistryName();
            if (resourceLocation != null) {
                if (Objects.equals(input.getRegistryName(), new ResourceLocation("jungle_leaves"))) {
                    SieveRecipeBuilder.builder().input(Ingredient.of(input))
                            .addResult(drop)
                            .addRoll(new MeshWithChance(EnumMesh.STRING, 0.025F))
                            .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                            .addRoll(new MeshWithChance(EnumMesh.IRON, 0.075F))
                            .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.1F))
                            .build(consumer, sieveLoc(resourceLocation.getPath()));
                } else {
                    SieveRecipeBuilder.builder().input(Ingredient.of(input))
                            .addResult(drop)
                            .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                            .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.1F))
                            .addRoll(new MeshWithChance(EnumMesh.IRON, 0.15F))
                            .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.2F))
                            .build(consumer, sieveLoc(resourceLocation.getPath()));
                }
            }
        });
        SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.LEAVES))
                .addResult(Items.APPLE)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.1F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.15F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.2F))
                .build(consumer, sieveLoc("apple"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.LEAVES))
                .addResult(Items.GOLDEN_APPLE)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.001F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.003F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.005F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.01F))
                .build(consumer, sieveLoc("golden_apple"));
        SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.LEAVES))
                .addResult(ExNihiloItems.SILKWORM.get())
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.025F))
                .addRoll(new MeshWithChance(EnumMesh.FLINT, 0.05F))
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.1F))
                .addRoll(new MeshWithChance(EnumMesh.DIAMOND, 0.2F))
                .build(consumer, sieveLoc("silkworm"));
        @Nullable final RegistryObject<Item> blueCoralSeed = EnumResource.BLUE_CORAL_SEED.getRegistryObject();
        if (blueCoralSeed != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.SAND))
                    .addResult(blueCoralSeed.get())
                    .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                    .isWaterlogged()
                    .build(consumer, sieveLoc("seed_blue_coral"));
        @Nullable final RegistryObject<Item> purpleCoralSeed = EnumResource.PURPLE_CORAL_SEED.getRegistryObject();
        if (purpleCoralSeed != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.SAND))
                    .addResult(purpleCoralSeed.get())
                    .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                    .isWaterlogged()
                    .build(consumer, sieveLoc("seed_purple_coral"));
        @Nullable final RegistryObject<Item> pinkCoralSeed = EnumResource.PINK_CORAL_SEED.getRegistryObject();
        if (pinkCoralSeed != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.SAND))
                    .addResult(pinkCoralSeed.get())
                    .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                    .isWaterlogged()
                    .build(consumer, sieveLoc("seed_pink_coral"));
        @Nullable final RegistryObject<Item> yellowCoralSeed = EnumResource.YELLOW_CORAL_SEED.getRegistryObject();
        if (yellowCoralSeed != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.SAND))
                    .addResult(yellowCoralSeed.get())
                    .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                    .isWaterlogged()
                    .build(consumer, sieveLoc("seed_yellow_coral"));
        @Nullable final RegistryObject<Item> redCoralSeed = EnumResource.RED_CORAL_SEED.getRegistryObject();
        if (redCoralSeed != null)
            SieveRecipeBuilder.builder().input(Ingredient.of(ItemTags.SAND))
                    .addResult(redCoralSeed.get())
                    .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                    .isWaterlogged()
                    .build(consumer, sieveLoc("seed_red_coral"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.SAND))
                .addResult(Items.SEAGRASS)
                .addRoll(new MeshWithChance(EnumMesh.IRON, 0.05F))
                .isWaterlogged()
                .build(consumer, sieveLoc("seagrass"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.COARSE_DIRT))
                .addResult(Items.DIRT)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 1F))
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.4F))
                .build(consumer, sieveLoc("dirt"));
        SieveRecipeBuilder.builder().input(Ingredient.of(Blocks.COARSE_DIRT))
                .addResult(Items.GRAVEL)
                .addRoll(new MeshWithChance(EnumMesh.STRING, 0.4F))
                .build(consumer, sieveLoc("gravel"));
    }
}
