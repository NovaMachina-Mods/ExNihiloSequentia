package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class AbstractLangGenerator extends LanguageProvider {
    final String ITEM_LANG = "item." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ".";

    public AbstractLangGenerator(DataGenerator gen, String locale) {
        super(gen, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, locale);
    }

    @Override
    protected void addTranslations() {}

    protected void addItem(String item, String name) {
        add(ITEM_LANG + item, name);
    }

    protected void addChunk(String item, String name) {
        final String CHUNK_LANG = ITEM_LANG + "chunk_";
        add(CHUNK_LANG + item, name);
    }

    protected void addPiece(String item, String name) {
        final String PIECE_LANG = ITEM_LANG + "piece_";
        add(PIECE_LANG + item, name);
    }

    protected void addIngot(String item, String name) {
        final String INGOT_LANG = ITEM_LANG + "ingot_";
        add(INGOT_LANG + item, name);
    }

    protected void addSeeds(String item, String name) {
        final String SEED_LANG = ITEM_LANG + "seed_";
        add(SEED_LANG + item, name);
    }

    protected void addMesh(String mesh, String name) {
        final String MESH_LANG = ITEM_LANG + "mesh_";
        add(MESH_LANG + mesh, name);
    }

    protected void addFluid(String fluid, String name) {
        final String FLUID_LANG = "fluid." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ".";
        add(FLUID_LANG + fluid, name);
    }

    protected void addBlock(String block, String name) {
        final String BLOCK_LANG = "block." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ".";
        add(BLOCK_LANG + block, name);
    }

}
