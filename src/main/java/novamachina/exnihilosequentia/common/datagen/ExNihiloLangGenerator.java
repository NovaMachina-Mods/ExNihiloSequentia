package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import novamachina.exnihilosequentia.api.datagen.AbstractLangGenerator;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.pebbles.EnumPebble;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Items;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Fluids;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Tooltips;

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
        addBlock();
        // Items
        addItem();
        // Compats
        addCompat();
        // Miscs
        addMisc();
    }

    private void addCompat() {
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

    private void addMisc() {
        // Fluids
        addFluid(Fluids.WITCH_WATER_STILL, "Witch Water");
        addFluid(Fluids.SEA_WATER_STILL, "Sea Water");
        addFluid(Fluids.WITCH_WATER_FLOW, "Flowing Witch Water");
        addFluid(Fluids.SEA_WATER_FLOW, "Flowing Sea Water");
        // Tooltips
        add(Tooltips.BEE, "Add to a barrel of witch water to spawn a Bee");
        add(Tooltips.BLAZE, "Add to a barrel of lava to spawn a Blaze");
        add(Tooltips.ENDERMAN, "Add to a barrel of witch water to spawn an Enderman");
        add(Tooltips.GUARDIAN, "Add to a barrel of sea water to spawn a Guardian");
        add(Tooltips.SHULKER, "Add to a barrel of witch water to spawn a Shulker");
        add(ModIds.EX_NIHILO_SEQUENTIA + ".subtitle.pebbleThrow", "Pebble flies");
        add("throwing.pebble", "Flying Pebble");
    }

    private void addItem() {
        // Crooks
        for (EnumCrook crook : EnumCrook.values()) {
            addItemAutoName(crook.crookName);
        }
        // Hammers
        for (EnumHammer hammer : EnumHammer.values()) {
            addItemAutoName(hammer.hammerName);
        }
        // Chunks
        for (EnumOre ore : EnumOre.values()) {
            if (ore.isVanilla()) {
                addPieceAutoName(ore.getOreName());
            } else {
                addOreAutoName(ore.getOreName());
            }
        }
        // Pebbles
        for (EnumPebble pebble : EnumPebble.values()) {
            addItemAutoName(pebble.getName());
        }
        // Meshes
        for (EnumMesh mesh : EnumMesh.values()) {
            addMeshAutoName(mesh.getName());
        }
        // Dolls
        addItem(Items.CRAFTING_DOLL, "Procelain Doll");
        addItem("bee_doll", "Buzzing Doll");
        addItem("blaze_doll", "Blazing Doll");
        addItem("enderman_doll", "Creeping Doll");
        addItem("guardian_doll", "Protecting Doll");
        addItem("shulker_doll", "Floating Doll");
        // Seeds
        for (EnumSeed seeds : EnumSeed.values()) {
            addItemAutoName(seeds.getSeedName());
        }
        // Miscs
        addItem(Items.SILKWORM, "Silkworm");
        addItem(Items.COOKED_SILKWORM, "Cooked Silkworm");
        addItem(Items.SEA_WATER_BUCKET, "Sea Water Bucket");
        addItem(Items.WITCH_WATER_BUCKET, "Witch Water Bucket");
        for (EnumResource miscs : EnumResource.values()) {
            if (!miscs.getResourceName().equals(Items.CRAFTING_DOLL)) {
                addItemAutoName(miscs.getResourceName());
            }
        }
    }

    private void addBlock() {
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
        addBlock(Blocks.SEA_WATER, "Sea Water");
        addBlock(Blocks.WITCH_WATER, "Witch Water");
    }
}