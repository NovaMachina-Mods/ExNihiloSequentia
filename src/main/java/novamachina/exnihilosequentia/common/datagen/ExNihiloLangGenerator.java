package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.*;

import java.util.Arrays;

public class ExNihiloLangGenerator extends LanguageProvider {
    public ExNihiloLangGenerator(DataGenerator gen, String locale) {
        super(gen, ModIds.EX_NIHILO_SEQUENTIA, locale);
    }

    @Override
    protected void addTranslations() {
        String locale = this.getName().replace("Languages: ", "");

        switch (locale)
        {
            case "en_us":
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
                break;
            default:
                break;
        }
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
        final String FLUID_LANG = "fluid." + ModIds.EX_NIHILO_SEQUENTIA + ".";
        // Fluids
        add(FLUID_LANG + Fluids.WITCH_WATER, "Witchwater");
        add(FLUID_LANG + Fluids.SEA_WATER, "Sea Water");
        // Tooltips
        add(Tooltips.BEE, "Add to a barrel of witchwater to spawn a Bee");
        add(Tooltips.BLAZE, "Add to a barrel of lava to spawn a Blaze");
        add(Tooltips.ENDERMAN, "Add to a barrel of witchwater to spawn an Enderman");
        add(Tooltips.GUARDIAN, "Add to a barrel of sea water to spawn a Guardian");
        add(Tooltips.SHULKER, "Add to a barrel of witchwater to spawn a Shulker");
    }

    private void addItemEN() {
        final String ITEM_LANG = "item." + ModIds.EX_NIHILO_SEQUENTIA + ".";
        final String SEED_LANG = ITEM_LANG + "seed_";
        final String PIECE_LANG = ITEM_LANG + "piece_";
        final String CHUNK_LANG = ITEM_LANG + "chunk_";
        final String INGOT_LANG = ITEM_LANG + "ingot_";
        final String MESH_LANG = ITEM_LANG + "mesh_";
        // Crooks
        add(ITEM_LANG + Items.CROOK_ANDESITE, "Andesite Crook");
        add(ITEM_LANG + Items.CROOK_BONE, "Bone Crook");
        add(ITEM_LANG + Items.CROOK_BLAZE, "Blaze Crook");
        add(ITEM_LANG + Items.CROOK_CLAY, "Clay Crook");
        add(ITEM_LANG + Items.CROOK_CLAY_UNCOOKED, "Uncooked Clay Crook");
        add(ITEM_LANG + Items.CROOK_DIAMOND, "Diamond Crook");
        add(ITEM_LANG + Items.CROOK_DIORITE, "Diorite Crook");
        add(ITEM_LANG + Items.CROOK_GOLD, "Gold Crook");
        add(ITEM_LANG + Items.CROOK_GRANITE, "Granite Crook");
        add(ITEM_LANG + Items.CROOK_IRON, "Iron Crook");
        add(ITEM_LANG + Items.CROOK_NETHERRACK, "Netherrack Crook");
        add(ITEM_LANG + Items.CROOK_PRISMARINE, "Prismarine Crook");
        add(ITEM_LANG + Items.CROOK_PURPUR, "Purpur Crook");
        add(ITEM_LANG + Items.CROOK_STONE, "Stone Crook");
        add(ITEM_LANG + Items.CROOK_WOOD, "Wood Crook");
        // Hammers
        add(ITEM_LANG + Items.HAMMER_DIAMOND, "Diamond Hammer");
        add(ITEM_LANG + Items.HAMMER_GOLD, "Gold Hammer");
        add(ITEM_LANG + Items.HAMMER_IRON, "Iron Hammer");
        add(ITEM_LANG + Items.HAMMER_NETHERITE, "Netherite Hammer");
        add(ITEM_LANG + Items.HAMMER_STONE, "Stone Hammer");
        add(ITEM_LANG + Items.HAMMER_WOOD, "Wood Hammer");
        // Dolls
        add(ITEM_LANG + Items.CRAFTING_DOLL, "Procelain Doll");
        add(ITEM_LANG + "doll_bee", "Buzzing Doll");
        add(ITEM_LANG + "doll_blaze", "Blazing Doll");
        add(ITEM_LANG + "doll_enderman", "Creeping Doll");
        add(ITEM_LANG + "doll_guardian", "Protecting Doll");
        add(ITEM_LANG + "doll_shulker", "Floating Doll");
        // Chunks
        add(CHUNK_LANG + Ore.ALUMINUM, "Aluminum Ore Chunk");
        add(CHUNK_LANG + Ore.BISMUTH, "Bismuth Ore Chunk");
        add(CHUNK_LANG + Ore.COPPER, "Copper Ore Chunk");
        add(CHUNK_LANG + Ore.GOLD, "Gold Ore Chunk");
        add(CHUNK_LANG + Ore.IRON, "Iron Ore Chunk");
        add(CHUNK_LANG + Ore.LEAD, "Lead Ore Chunk");
        add(CHUNK_LANG + Ore.NICKEL, "Nickel Ore Chunk");
        add(CHUNK_LANG + Ore.PLATINUM, "Platinum Ore Chunk");
        add(CHUNK_LANG + Ore.SILVER, "Silver Ore Chunk");
        add(CHUNK_LANG + Ore.TIN, "Tin Ore Chunk");
        add(CHUNK_LANG + Ore.URANIUM, "Uranium Ore Chunk");
        add(CHUNK_LANG + Ore.ZINC, "Zinc Ore Chunk");
        // Pieces
        add(PIECE_LANG + Ore.ALUMINUM, "Aluminum Ore Piece");
        add(PIECE_LANG + Ore.BISMUTH, "Bismuth Ore Piece");
        add(PIECE_LANG + Ore.COPPER, "Copper Ore Piece");
        add(PIECE_LANG + Ore.GOLD, "Gold Ore Piece");
        add(PIECE_LANG + Ore.IRON, "Iron Ore Piece");
        add(PIECE_LANG + Ore.LEAD, "Lead Ore Piece");
        add(PIECE_LANG + Ore.NICKEL, "Nickel Ore Piece");
        add(PIECE_LANG + Ore.PLATINUM, "Platinum Ore Piece");
        add(PIECE_LANG + Ore.SILVER, "Silver Ore Piece");
        add(PIECE_LANG + Ore.TIN, "Tin Ore Piece");
        add(PIECE_LANG + Ore.URANIUM, "Uranium Ore Piece");
        add(PIECE_LANG + Ore.ZINC, "Zinc Ore Piece");
        // Ingots
        add(INGOT_LANG + Ore.ALUMINUM, "Aluminum Ingot");
        add(INGOT_LANG + Ore.BISMUTH, "Bismuth Ingot");
        add(INGOT_LANG + Ore.COPPER, "Copper Ingot");
        add(INGOT_LANG + Ore.LEAD, "Lead Ingot");
        add(INGOT_LANG + Ore.NICKEL, "Nickel Ingot");
        add(INGOT_LANG + Ore.PLATINUM, "Platinum Ingot");
        add(INGOT_LANG + Ore.SILVER, "Silver Ingot");
        add(INGOT_LANG + Ore.TIN, "Tin Ingot");
        add(INGOT_LANG + Ore.URANIUM, "Uranium Ingot");
        add(INGOT_LANG + Ore.ZINC, "Zinc Ingot");
        // Seeds
        add(ITEM_LANG + Items.ANCIENT_SPORE, "Ancient Spores");
        add(ITEM_LANG + Items.BLUE_CORAL_SEED, "Tube Coral Seed");
        add(ITEM_LANG + Items.GRASS_SEED, "Grass Seeds");
        add(ITEM_LANG + Items.PINK_CORAL_SEED, "Brain Coral Seed");
        add(ITEM_LANG + Items.PURPLE_CORAL_SEED, "Bubble Coral Seed");
        add(ITEM_LANG + Items.RED_CORAL_SEED, "Fire Coral Seed");
        add(SEED_LANG + Items.SEED_ACACIA, "Acacia Seed");
        add(SEED_LANG + Items.SEED_BAMBOO, "Bamboo Seed");
        add(SEED_LANG + Items.SEED_BIRCH, "Birch Seeds");
        add(SEED_LANG + Items.SEED_CACTUS, "Cactus Seeds");
        add(SEED_LANG + Items.SEED_CARROT, "Carrot Seeds");
        add(SEED_LANG + Items.SEED_DARK_OAK, "Dark Oak Seed");
        add(SEED_LANG + Items.SEED_FERN, "Fern Seed");
        add(SEED_LANG + Items.SEED_JUNGLE, "Jungle Seed");
        add(SEED_LANG + Items.SEED_KELP, "Kelp Seeds");
        add(SEED_LANG + Items.SEED_LARGE_FERN, "Large Fern Seed");
        add(SEED_LANG + Items.SEED_OAK, "Oak Seed");
        add(SEED_LANG + Items.SEED_PICKLE, "Sea Pickle Egg");
        add(SEED_LANG + Items.SEED_POTATO, "Potato Seeds");
        add(SEED_LANG + Items.SEED_SPRUCE, "Spruce Seed");
        add(SEED_LANG + Items.SEED_SUGARCANE, "Sugarcane Seeds");
        add(SEED_LANG + Items.SEED_SWEET_BERRY, "Sweet Berry Seeds");
        add(ITEM_LANG + Items.YELLOW_CORAL_SEED, "Horn Coral Seed");
        // Miscs
        add(ITEM_LANG + Items.SILKWORM, "Silkworm");
        add(ITEM_LANG + Items.COOKED_SILKWORM, "Cooked Silkworm");
        add(ITEM_LANG + Items.SEA_WATER_BUCKET, "Sea Water Bucket");
        add(ITEM_LANG + Items.WITCH_WATER_BUCKET, "Witchwater Bucket");
        add(ITEM_LANG + Items.PORCELAIN_CLAY, "Procelain Clay");
        add(ITEM_LANG + Items.BEEHIVE_FRAME, "Beehive Frame");
        // Pebbles
        add(ITEM_LANG + Items.PEBBLE_ANDESITE, "Andesite Pebble");
        add(ITEM_LANG + "pebble_basalt", "Basalt Pebble");
        add(ITEM_LANG + "pebble_blackstone", "Blackstone Pebble");
        add(ITEM_LANG + Items.PEBBLE_DIORITE, "Diorite Pebble");
        add(ITEM_LANG + Items.PEBBLE_GRANITE, "Granite Pebble");
        add(ITEM_LANG + Items.PEBBLE_STONE, "Stone Pebble");
        // Meshes
        add(MESH_LANG + net.minecraft.item.Items.STRING, "String Mesh");
        add(MESH_LANG + "flint", "Flint Mesh");
        add(MESH_LANG + "iron", "Iron Mesh");
        add(MESH_LANG + "diamond", "Diamond Mesh");
        add(MESH_LANG + "emerald", "Emerald Mesh");
        add(MESH_LANG + "netherite", "Netherite Mesh");
    }

    private void addBlockEN() {
        final String LANG_BLOCK = "block." + ModIds.EX_NIHILO_SEQUENTIA + ".";

        // Normal Blocks
        add(LANG_BLOCK + Blocks.DUST, "Dust");
        add(LANG_BLOCK + Blocks.CRUSHED_ANDESITE, "Crushed Andesite");
        add(LANG_BLOCK + Blocks.CRUSHED_DIORITE, "Crushed Diorite");
        add(LANG_BLOCK + Blocks.CRUSHED_END_STONE, "Crushed End Stone");
        add(LANG_BLOCK + Blocks.CRUSHED_GRANITE, "Crushed Granite");
        add(LANG_BLOCK + Blocks.CRUSHED_NETHERRACK, "Crushed Netherrack");
        // Leaves
        add(LANG_BLOCK + Blocks.INFESTING_LEAVES, "Infesting Leaves");
        add(LANG_BLOCK + Blocks.INFESTED_LEAVES, "Infested Leaves");
        // Sieves
        add(LANG_BLOCK + Blocks.SIEVE_ACACIA, "Acacia Sieve");
        add(LANG_BLOCK + Blocks.SIEVE_BIRCH, "Birch Sieve");
        add(LANG_BLOCK + Blocks.SIEVE_CRIMSON, "Crimson Sieve");
        add(LANG_BLOCK + Blocks.SIEVE_DARK_OAK, "Dark Oak Sieve");
        add(LANG_BLOCK + Blocks.SIEVE_JUNGLE, "Jungle Sieve");
        add(LANG_BLOCK + Blocks.SIEVE_OAK, "Oak Sieve");
        add(LANG_BLOCK + Blocks.SIEVE_SPRUCE, "Spruce Sieve");
        add(LANG_BLOCK + Blocks.SIEVE_WARPED, "Warped Sieve");
        // Barrels
        add(LANG_BLOCK + Blocks.BARREL_ACACIA, "Acacia Barrel");
        add(LANG_BLOCK + Blocks.BARREL_BIRCH, "Birch Barrel");
        add(LANG_BLOCK + Blocks.BARREL_CRIMSON, "Crimson Barrel");
        add(LANG_BLOCK + Blocks.BARREL_DARK_OAK, "Dark Oak Barrel");
        add(LANG_BLOCK + Blocks.BARREL_JUNGLE, "Jungle Barrel");
        add(LANG_BLOCK + Blocks.BARREL_OAK, "Oak Barrel");
        add(LANG_BLOCK + Blocks.BARREL_SPRUCE, "Spruce Barrel");
        add(LANG_BLOCK + Blocks.BARREL_STONE, "Stone Barrel");
        add(LANG_BLOCK + Blocks.BARREL_WARPED, "Warped Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS, "Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_BLACK, "Black Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_BLUE, "Blue Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_BROWN, "Brown Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_CYAN, "Cyan Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_GRAY, "Gray Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_GREEN, "Green Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_LIGHT_BLUE, "Light Blue Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_LIGHT_GRAY, "Light Gray Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_LIME, "Lime Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_MAGENTA, "Magenta Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_ORANGE, "Orange Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_PINK, "Pink Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_PURPLE, "Purple Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_RED, "Red Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_WHITE, "White Stained Glass Barrel");
        add(LANG_BLOCK + Blocks.BARREL_GLASS_YELLOW, "Yellow Stained Glass Barrel");
        // Crucibles
        add(LANG_BLOCK + Blocks.CRUCIBLE_ACACIA, "Acacia Crucible");
        add(LANG_BLOCK + Blocks.CRUCIBLE_BIRCH, "Birch Crucible");
        add(LANG_BLOCK + Blocks.CRUCIBLE_CRIMSON, "Crimson Crucible");
        add(LANG_BLOCK + Blocks.CRUCIBLE_DARK_OAK, "Dark Oak Crucible");
        add(LANG_BLOCK + Blocks.CRUCIBLE_FIRED, "Fired Crucible");
        add(LANG_BLOCK + Blocks.CRUCIBLE_JUNGLE, "Jungle Crucible");
        add(LANG_BLOCK + Blocks.CRUCIBLE_OAK, "Oak Crucible");
        add(LANG_BLOCK + Blocks.CRUCIBLE_SPRUCE, "Spruce Crucible");
        add(LANG_BLOCK + Blocks.CRUCIBLE_UNFIRED, "Unfired Crucible");
        add(LANG_BLOCK + Blocks.CRUCIBLE_WARPED, "Warped Crucible");
        // Miscs
        add(LANG_BLOCK + Blocks.END_CAKE, "End Cake");
        add(LANG_BLOCK + Blocks.SEA_WATER, "Sea Water");
        add(LANG_BLOCK + Blocks.WITCH_WATER, "Witchwater");
    }
}