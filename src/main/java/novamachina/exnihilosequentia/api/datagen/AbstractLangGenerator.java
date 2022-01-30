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

    /**
     * @param item need item to be used in lang file
     * @param name set name of item that is shown ingame
     */
    protected void addItem(String item, String name) {
        add(ITEM_LANG + item, name);
    }

    /**
     * @param item need item to be used in lang file
     * @param name set name of item that is shown ingame
     */
    protected void addChunk(String item, String name) {
        final String CHUNK_LANG = ITEM_LANG + "raw_";
        add(CHUNK_LANG + item, name);
    }

    /**
     * @param item need item to be used in lang file
     * @param name set name of item that is shown ingame
     */
    protected void addPiece(String item, String name) {
        add(ITEM_LANG + item + "_piece", name);
    }

    /**
     * @param item need item to be used in lang file
     * @param name set name of item that is shown ingame
     */
    protected void addIngot(String item, String name) {
        add(ITEM_LANG + item + "_ingot", name);
    }

    /**
     * @param mesh need mesh to be used in lang file
     * @param name set name of mesh that is shown ingame
     */
    protected void addMesh(String mesh, String name) {
        add(ITEM_LANG + mesh + "_mesh", name);
    }

    /**
     * @param fluid need fluid to be used in lang file
     * @param name set name of fluid that is shown ingame
     */
    protected void addFluid(String fluid, String name) {
        final String FLUID_LANG = "fluid." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ".";
        add(FLUID_LANG + fluid, name);
    }

    /**
     * @param block need block to be used in lang file
     * @param name set name of block that is shown ingame
     */
    protected void addBlock(String block, String name) {
        final String BLOCK_LANG = "block." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ".";
        add(BLOCK_LANG + block, name);
    }

}