package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import novamachina.exnihilosequentia.api.datagen.AbstractLangGenerator;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.*;

public class ExNihiloLangGenerator extends AbstractLangGenerator {
    final String locale;
    public ExNihiloLangGenerator(DataGenerator gen, String locale) {
        super(gen, locale);
        this.locale = locale;
    }

    @Override
    protected void addTranslations() {
        // CreativeTab
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
        add(WAILA_LANG + "crucible.no_heat", "No Heat Source");
        // TOP
        add("top.barrel.mode", "Mode: %s");
    }

    private void addMiscEN() {
        // Fluids
        addFluid(Fluids.WITCH_WATER_STILL, "Witch Water");
        addFluid(Fluids.SEA_WATER_STILL, "Sea Water");
        // Tooltips
        add(Tooltips.BEE, "Add to a barrel of witch water to spawn a Bee");
        add(Tooltips.BLAZE, "Add to a barrel of lava to spawn a Blaze");
        add(Tooltips.ENDERMAN, "Add to a barrel of witch water to spawn an Enderman");
        add(Tooltips.GUARDIAN, "Add to a barrel of sea water to spawn a Guardian");
        add(Tooltips.SHULKER, "Add to a barrel of witch water to spawn a Shulker");
        add(ModIds.EX_NIHILO_SEQUENTIA + ".subtitle.pebbleThrow", "Pebble flies");
    }

    private void addItemEN() {
        // Crooks
        addItem(Items.ANDESITE_CROOK, "Andesite Crook");
        addItem(Items.BONE_CROOK, "Bone Crook");
        addItem(Items.BLAZE_CROOK, "Blaze Crook");
        addItem(Items.CLAY_CROOK, "Clay Crook");
        addItem(Items.CLAY_UNCOOKED_CROOK, "Uncooked Clay Crook");
        addItem(Items.DIAMOND_CROOK, "Diamond Crook");
        addItem(Items.DIORITE_CROOK, "Diorite Crook");
        addItem(Items.GOLD_CROOK, "Gold Crook");
        addItem(Items.GRANITE_CROOK, "Granite Crook");
        addItem(Items.IRON_CROOK, "Iron Crook");
        addItem(Items.NETHERRACK_CROOK, "Netherrack Crook");
        addItem(Items.PRISMARINE_CROOK, "Prismarine Crook");
        addItem(Items.PURPUR_CROOK, "Purpur Crook");
        addItem(Items.STONE_CROOK, "Stone Crook");
        addItem(Items.WOOD_CROOK, "Wood Crook");
        // Hammers
        addItem(Items.DIAMOND_HAMMER, "Diamond Hammer");
        addItem(Items.GOLD_HAMMER, "Gold Hammer");
        addItem(Items.IRON_HAMMER, "Iron Hammer");
        addItem(Items.NETHERITE_HAMMER, "Netherite Hammer");
        addItem(Items.STONE_HAMMER, "Stone Hammer");
        addItem(Items.WOOD_HAMMER, "Wood Hammer");
        // Dolls
        addItem(Items.CRAFTING_DOLL, "Procelain Doll");
        addItem("bee_doll", "Buzzing Doll");
        addItem("blaze_doll", "Blazing Doll");
        addItem("enderman_doll", "Creeping Doll");
        addItem("guardian_doll", "Protecting Doll");
        addItem("shulker_doll", "Floating Doll");
        // Chunks
        addChunk(Ore.ALUMINUM, "Raw Aluminum");
        addChunk(Ore.BISMUTH, "Raw Bismuth");
        addChunk(Ore.LEAD, "Raw Lead");
        addChunk(Ore.NICKEL, "Raw Nickel");
        addChunk(Ore.PLATINUM, "Raw Platinum");
        addChunk(Ore.SILVER, "Raw Silver");
        addChunk(Ore.TIN, "Raw Tin");
        addChunk(Ore.URANIUM, "Raw Uranium");
        addChunk(Ore.ZINC, "Raw Zinc");
        // Pieces
        addPiece(Ore.ALUMINUM, "Aluminum Piece");
        addPiece(Ore.BISMUTH, "Bismuth Piece");
        addPiece(Ore.COPPER, "Copper Piece");
        addPiece(Ore.GOLD, "Gold Piece");
        addPiece(Ore.IRON, "Iron Piece");
        addPiece(Ore.LEAD, "Lead Piece");
        addPiece(Ore.NICKEL, "Nickel Piece");
        addPiece(Ore.PLATINUM, "Platinum Piece");
        addPiece(Ore.SILVER, "Silver Piece");
        addPiece(Ore.TIN, "Tin Piece");
        addPiece(Ore.URANIUM, "Uranium Piece");
        addPiece(Ore.ZINC, "Zinc Piece");
        // Ingots
        addIngot(Ore.ALUMINUM, "Aluminum Ingot");
        addIngot(Ore.BISMUTH, "Bismuth Ingot");
        addIngot(Ore.LEAD, "Lead Ingot");
        addIngot(Ore.NICKEL, "Nickel Ingot");
        addIngot(Ore.PLATINUM, "Platinum Ingot");
        addIngot(Ore.SILVER, "Silver Ingot");
        addIngot(Ore.TIN, "Tin Ingot");
        addIngot(Ore.URANIUM, "Uranium Ingot");
        addIngot(Ore.ZINC, "Zinc Ingot");
        // Seeds
        addItem(Items.ANCIENT_SPORE, "Ancient Spores");
        addItem(Items.BLUE_CORAL_LARVAE, "Tube Coral Larvae");
        addItem(Items.GRASS_SEED, "Grass Seeds");
        addItem(Items.PINK_CORAL_LARVAE, "Brain Coral Larvae");
        addItem(Items.PURPLE_CORAL_LARVAE, "Bubble Coral Larvae");
        addItem(Items.RED_CORAL_LARVAE, "Fire Coral Larvae");
        addItem(Items.YELLOW_CORAL_LARVAE, "Horn Coral Larvae");
        // Miscs
        addItem(Items.SILKWORM, "Silkworm");
        addItem(Items.COOKED_SILKWORM, "Cooked Silkworm");
        addItem(Items.SEA_WATER_BUCKET, "Sea Water Bucket");
        addItem(Items.WITCH_WATER_BUCKET, "Witch Water Bucket");
        addItem(Items.PORCELAIN_CLAY, "Procelain Clay");
        addItem(Items.BEEHIVE_FRAME, "Beehive Frame");
        addItem(Items.STONE_STICK, "Stone Stick");
        // Pebbles
        addItem(Items.ANDESITE_PEBBLE, "Andesite Pebble");
        addItem(Items.BASALT_PEBBLE, "Basalt Pebble");
        addItem(Items.BLACKSTONE_PEBBLE, "Blackstone Pebble");
        addItem(Items.DIORITE_PEBBLE, "Diorite Pebble");
        addItem(Items.GRANITE_PEBBLE, "Granite Pebble");
        addItem(Items.STONE_PEBBLE, "Stone Pebble");
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
        addBlock(Blocks.ACACIA_SIEVE, "Acacia Sieve");
        addBlock(Blocks.BIRCH_SIEVE, "Birch Sieve");
        addBlock(Blocks.CRIMSON_SIEVE, "Crimson Sieve");
        addBlock(Blocks.DARK_OAK_SIEVE, "Dark Oak Sieve");
        addBlock(Blocks.JUNGLE_SIEVE, "Jungle Sieve");
        addBlock(Blocks.OAK_SIEVE, "Oak Sieve");
        addBlock(Blocks.SPRUCE_SIEVE, "Spruce Sieve");
        addBlock(Blocks.WARPED_SIEVE, "Warped Sieve");
        // Barrels
        addBlock(Blocks.ACACIA_BARREL, "Acacia Barrel");
        addBlock(Blocks.BIRCH_BARREL, "Birch Barrel");
        addBlock(Blocks.CRIMSON_BARREL, "Crimson Barrel");
        addBlock(Blocks.DARK_OAK_BARREL, "Dark Oak Barrel");
        addBlock(Blocks.JUNGLE_BARREL, "Jungle Barrel");
        addBlock(Blocks.OAK_BARREL, "Oak Barrel");
        addBlock(Blocks.SPRUCE_BARREL, "Spruce Barrel");
        addBlock(Blocks.STONE_BARREL, "Stone Barrel");
        addBlock(Blocks.WARPED_BARREL, "Warped Barrel");
        // Crucibles
        addBlock(Blocks.ACACIA_CRUCIBLE, "Acacia Crucible");
        addBlock(Blocks.BIRCH_CRUCIBLE, "Birch Crucible");
        addBlock(Blocks.CRIMSON_CRUCIBLE, "Crimson Crucible");
        addBlock(Blocks.DARK_OAK_CRUCIBLE, "Dark Oak Crucible");
        addBlock(Blocks.FIRED_CRUCIBLE, "Fired Crucible");
        addBlock(Blocks.JUNGLE_CRUCIBLE, "Jungle Crucible");
        addBlock(Blocks.OAK_CRUCIBLE, "Oak Crucible");
        addBlock(Blocks.SPRUCE_CRUCIBLE, "Spruce Crucible");
        addBlock(Blocks.UNFIRED_CRUCIBLE, "Unfired Crucible");
        addBlock(Blocks.WARPED_CRUCIBLE, "Warped Crucible");
        // Miscs
        addBlock(Blocks.END_CAKE, "End Cake");
        addBlock(Fluids.SEA_WATER_STILL, "Sea Water");
        addBlock(Fluids.WITCH_WATER_STILL, "Witch Water");
    }
}
