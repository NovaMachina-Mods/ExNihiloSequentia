package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import novamachina.exnihilosequentia.api.datagen.AbstractLangGenerator;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.*;

public class ExNihiloLangGenerator extends AbstractLangGenerator {
    public ExNihiloLangGenerator(DataGenerator gen, String locale) {
        super(gen, locale);
    }

    @Override
    protected void addTranslations() {
        // ItemGroup
        add("itemGroup." + ModIds.EX_NIHILO_SEQUENTIA, "Ex Nihilo: Sequentia");
        // Blocks
        addBlockEN();
        // Items
        addItemEN();
        // Compats
        addCompatEN();
        // Miscs
        addMiscEN();
    }

    private void addCompatEN() {
        final String WAILA_LANG = "waila.";
        // JEI
        add("jei.sieve.dropChance", "Drop Chance");
        // WAILA/HYWLA
        add(WAILA_LANG + "progress", "Progress: %s");
        add(WAILA_LANG + "barrel.fluidAmount", "Fluid(%s): %s mB");
        add(WAILA_LANG + "barrel.solidAmount", "Solid(%s): %s / %s");
        add(WAILA_LANG + "barrel.block", "Block: %s");
        add(WAILA_LANG + "barrel.compost", "Compost: %s / %s");
        add(WAILA_LANG + "sieve.block", "Sifting: %s");
        add(WAILA_LANG + "sieve.mesh", "Mesh: %s");
        add(WAILA_LANG + "crucible.fluid", "Fluid(%s): %s mB");
        add(WAILA_LANG + "crucible.solid", "Solid(%s): %s");
        add(WAILA_LANG + "crucible.heat", "Heat: %s");
        // TOP
        add("top.barrel.mode", "Mode: %s");
    }

    private void addMiscEN() {
        // Fluids
        addFluid(Fluids.WITCH_WATER, "Witchwater");
        addFluid(Fluids.SEA_WATER, "Sea Water");
        // Tooltips
        add(Tooltips.BEE, "Add to a barrel of witchwater to spawn a Bee");
        add(Tooltips.BLAZE, "Add to a barrel of lava to spawn a Blaze");
        add(Tooltips.ENDERMAN, "Add to a barrel of witchwater to spawn an Enderman");
        add(Tooltips.GUARDIAN, "Add to a barrel of sea water to spawn a Guardian");
        add(Tooltips.SHULKER, "Add to a barrel of witchwater to spawn a Shulker");
        add(ModIds.EX_NIHILO_SEQUENTIA + ".subtitle.pebbleThrow", "Pebble flies");
    }

    private void addItemEN() {
        // Crooks
        addItem(Items.CROOK_ANDESITE, "Andesite Crook");
        addItem(Items.CROOK_BONE, "Bone Crook");
        addItem(Items.CROOK_BLAZE, "Blaze Crook");
        addItem(Items.CROOK_CLAY, "Clay Crook");
        addItem(Items.CROOK_CLAY_UNCOOKED, "Uncooked Clay Crook");
        addItem(Items.CROOK_DIAMOND, "Diamond Crook");
        addItem(Items.CROOK_DIORITE, "Diorite Crook");
        addItem(Items.CROOK_GOLD, "Gold Crook");
        addItem(Items.CROOK_GRANITE, "Granite Crook");
        addItem(Items.CROOK_IRON, "Iron Crook");
        addItem(Items.CROOK_NETHERRACK, "Netherrack Crook");
        addItem(Items.CROOK_PRISMARINE, "Prismarine Crook");
        addItem(Items.CROOK_PURPUR, "Purpur Crook");
        addItem(Items.CROOK_STONE, "Stone Crook");
        addItem(Items.CROOK_WOOD, "Wood Crook");
        // Hammers
        addItem(Items.HAMMER_DIAMOND, "Diamond Hammer");
        addItem(Items.HAMMER_GOLD, "Gold Hammer");
        addItem(Items.HAMMER_IRON, "Iron Hammer");
        addItem(Items.HAMMER_NETHERITE, "Netherite Hammer");
        addItem(Items.HAMMER_STONE, "Stone Hammer");
        addItem(Items.HAMMER_WOOD, "Wood Hammer");
        // Dolls
        addItem(Items.CRAFTING_DOLL, "Procelain Doll");
        addItem("doll_bee", "Buzzing Doll");
        addItem("doll_blaze", "Blazing Doll");
        addItem("doll_enderman", "Creeping Doll");
        addItem("doll_guardian", "Protecting Doll");
        addItem("doll_shulker", "Floating Doll");
        // Chunks
        addChunk(Ore.ALUMINUM, "Aluminum Ore Chunk");
        addChunk(Ore.BISMUTH, "Bismuth Ore Chunk");
        addChunk(Ore.COPPER, "Copper Ore Chunk");
        addChunk(Ore.GOLD, "Gold Ore Chunk");
        addChunk(Ore.IRON, "Iron Ore Chunk");
        addChunk(Ore.LEAD, "Lead Ore Chunk");
        addChunk(Ore.NICKEL, "Nickel Ore Chunk");
        addChunk(Ore.PLATINUM, "Platinum Ore Chunk");
        addChunk(Ore.SILVER, "Silver Ore Chunk");
        addChunk(Ore.TIN, "Tin Ore Chunk");
        addChunk(Ore.URANIUM, "Uranium Ore Chunk");
        addChunk(Ore.ZINC, "Zinc Ore Chunk");
        // Pieces
        addPiece(Ore.ALUMINUM, "Aluminum Ore Piece");
        addPiece(Ore.BISMUTH, "Bismuth Ore Piece");
        addPiece(Ore.COPPER, "Copper Ore Piece");
        addPiece(Ore.GOLD, "Gold Ore Piece");
        addPiece(Ore.IRON, "Iron Ore Piece");
        addPiece(Ore.LEAD, "Lead Ore Piece");
        addPiece(Ore.NICKEL, "Nickel Ore Piece");
        addPiece(Ore.PLATINUM, "Platinum Ore Piece");
        addPiece(Ore.SILVER, "Silver Ore Piece");
        addPiece(Ore.TIN, "Tin Ore Piece");
        addPiece(Ore.URANIUM, "Uranium Ore Piece");
        addPiece(Ore.ZINC, "Zinc Ore Piece");
        // Ingots
        addIngot(Ore.ALUMINUM, "Aluminum Ingot");
        addIngot(Ore.BISMUTH, "Bismuth Ingot");
        addIngot(Ore.COPPER, "Copper Ingot");
        addIngot(Ore.LEAD, "Lead Ingot");
        addIngot(Ore.NICKEL, "Nickel Ingot");
        addIngot(Ore.PLATINUM, "Platinum Ingot");
        addIngot(Ore.SILVER, "Silver Ingot");
        addIngot(Ore.TIN, "Tin Ingot");
        addIngot(Ore.URANIUM, "Uranium Ingot");
        addIngot(Ore.ZINC, "Zinc Ingot");
        // Seeds
        addItem(Items.ANCIENT_SPORE, "Ancient Spores");
        addItem(Items.BLUE_CORAL_SEED, "Tube Coral Seed");
        addItem(Items.GRASS_SEED, "Grass Seeds");
        addItem(Items.PINK_CORAL_SEED, "Brain Coral Seed");
        addItem(Items.PURPLE_CORAL_SEED, "Bubble Coral Seed");
        addItem(Items.RED_CORAL_SEED, "Fire Coral Seed");
        addItem(Items.YELLOW_CORAL_SEED, "Horn Coral Seed");
        addSeeds(Items.SEED_ACACIA, "Acacia Seed");
        addSeeds(Items.SEED_BAMBOO, "Bamboo Seed");
        addSeeds(Items.SEED_BIRCH, "Birch Seeds");
        addSeeds(Items.SEED_CACTUS, "Cactus Seeds");
        addSeeds(Items.SEED_CARROT, "Carrot Seeds");
        addSeeds(Items.SEED_DARK_OAK, "Dark Oak Seed");
        addSeeds(Items.SEED_FERN, "Fern Seed");
        addSeeds(Items.SEED_JUNGLE, "Jungle Seed");
        addSeeds(Items.SEED_KELP, "Kelp Seeds");
        addSeeds(Items.SEED_LARGE_FERN, "Large Fern Seed");
        addSeeds(Items.SEED_OAK, "Oak Seed");
        addSeeds(Items.SEED_PICKLE, "Sea Pickle Egg");
        addSeeds(Items.SEED_POTATO, "Potato Seeds");
        addSeeds(Items.SEED_SPRUCE, "Spruce Seed");
        addSeeds(Items.SEED_SUGARCANE, "Sugarcane Seeds");
        addSeeds(Items.SEED_SWEET_BERRY, "Sweet Berry Seeds");
        // Miscs
        addItem(Items.SILKWORM, "Silkworm");
        addItem(Items.COOKED_SILKWORM, "Cooked Silkworm");
        addItem(Items.SEA_WATER_BUCKET, "Sea Water Bucket");
        addItem(Items.WITCH_WATER_BUCKET, "Witchwater Bucket");
        addItem(Items.PORCELAIN_CLAY, "Procelain Clay");
        addItem(Items.BEEHIVE_FRAME, "Beehive Frame");
        // Pebbles
        addItem(Items.PEBBLE_ANDESITE, "Andesite Pebble");
        addItem(Items.PEBBLE_BASALT, "Basalt Pebble");
        addItem(Items.PEBBLE_BLACKSTONE, "Blackstone Pebble");
        addItem(Items.PEBBLE_DIORITE, "Diorite Pebble");
        addItem(Items.PEBBLE_GRANITE, "Granite Pebble");
        addItem(Items.PEBBLE_STONE, "Stone Pebble");
        // Meshes
        addMesh("string", "String Mesh");
        addMesh("flint", "Flint Mesh");
        addMesh("iron", "Iron Mesh");
        addMesh("diamond", "Diamond Mesh");
        addMesh("emerald", "Emerald Mesh");
        addMesh("netherite", "Netherite Mesh");
    }

    private void addBlockEN() {
        // Normal Blocks
        addBlock(Blocks.DUST, "Dust");
        addBlock(Blocks.CRUSHED_ANDESITE, "Crushed Andesite");
        addBlock(Blocks.CRUSHED_DIORITE, "Crushed Diorite");
        addBlock(Blocks.CRUSHED_END_STONE, "Crushed End Stone");
        addBlock(Blocks.CRUSHED_GRANITE, "Crushed Granite");
        addBlock(Blocks.CRUSHED_NETHERRACK, "Crushed Netherrack");
        // Leaves
        addBlock(Blocks.INFESTING_LEAVES, "Infesting Leaves");
        addBlock(Blocks.INFESTED_LEAVES, "Infested Leaves");
        // Sieves
        addBlock(Blocks.SIEVE_ACACIA, "Acacia Sieve");
        addBlock(Blocks.SIEVE_BIRCH, "Birch Sieve");
        addBlock(Blocks.SIEVE_CRIMSON, "Crimson Sieve");
        addBlock(Blocks.SIEVE_DARK_OAK, "Dark Oak Sieve");
        addBlock(Blocks.SIEVE_JUNGLE, "Jungle Sieve");
        addBlock(Blocks.SIEVE_OAK, "Oak Sieve");
        addBlock(Blocks.SIEVE_SPRUCE, "Spruce Sieve");
        addBlock(Blocks.SIEVE_WARPED, "Warped Sieve");
        // Barrels
        addBlock(Blocks.BARREL_ACACIA, "Acacia Barrel");
        addBlock(Blocks.BARREL_BIRCH, "Birch Barrel");
        addBlock(Blocks.BARREL_CRIMSON, "Crimson Barrel");
        addBlock(Blocks.BARREL_DARK_OAK, "Dark Oak Barrel");
        addBlock(Blocks.BARREL_JUNGLE, "Jungle Barrel");
        addBlock(Blocks.BARREL_OAK, "Oak Barrel");
        addBlock(Blocks.BARREL_SPRUCE, "Spruce Barrel");
        addBlock(Blocks.BARREL_STONE, "Stone Barrel");
        addBlock(Blocks.BARREL_WARPED, "Warped Barrel");
        addBlock(Blocks.BARREL_GLASS, "Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_BLACK, "Black Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_BLUE, "Blue Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_BROWN, "Brown Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_CYAN, "Cyan Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_GRAY, "Gray Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_GREEN, "Green Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_LIGHT_BLUE, "Light Blue Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_LIGHT_GRAY, "Light Gray Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_LIME, "Lime Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_MAGENTA, "Magenta Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_ORANGE, "Orange Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_PINK, "Pink Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_PURPLE, "Purple Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_RED, "Red Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_WHITE, "White Stained Glass Barrel");
        addBlock(Blocks.BARREL_GLASS_YELLOW, "Yellow Stained Glass Barrel");
        // Crucibles
        addBlock(Blocks.CRUCIBLE_ACACIA, "Acacia Crucible");
        addBlock(Blocks.CRUCIBLE_BIRCH, "Birch Crucible");
        addBlock(Blocks.CRUCIBLE_CRIMSON, "Crimson Crucible");
        addBlock(Blocks.CRUCIBLE_DARK_OAK, "Dark Oak Crucible");
        addBlock(Blocks.CRUCIBLE_FIRED, "Fired Crucible");
        addBlock(Blocks.CRUCIBLE_JUNGLE, "Jungle Crucible");
        addBlock(Blocks.CRUCIBLE_OAK, "Oak Crucible");
        addBlock(Blocks.CRUCIBLE_SPRUCE, "Spruce Crucible");
        addBlock(Blocks.CRUCIBLE_UNFIRED, "Unfired Crucible");
        addBlock(Blocks.CRUCIBLE_WARPED, "Warped Crucible");
        // Miscs
        addBlock(Blocks.END_CAKE, "End Cake");
        addBlock(Blocks.SEA_WATER, "Sea Water");
        addBlock(Blocks.WITCH_WATER, "Witchwater");
    }
}