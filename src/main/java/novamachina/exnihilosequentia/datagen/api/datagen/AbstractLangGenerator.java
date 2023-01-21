package novamachina.exnihilosequentia.datagen.api.datagen;

import java.util.Objects;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.StringUtils;

public class AbstractLangGenerator extends LanguageProvider {

  final String ITEM_LANG;

  public AbstractLangGenerator(DataGenerator gen, String modid, String locale) {
    super(gen, modid, locale);
    this.ITEM_LANG = "item." + modid + ".";
    ;
  }

  @Override
  protected void addTranslations() {}

  protected String properNaming(String item) {
    return StringUtils.capitalize(item.replace("_", " "));
  }

  /**
   * @param item need item to be used in lang file
   * @param name set name of item that is shown ingame
   */
  protected void addItem(String item, String name) {
    add(ITEM_LANG + item, name);
  }

  /**
   * @param item need item to be used in lang file, auto names it + capitalize it fully
   */
  protected void addItemAutoName(String item) {
    add(ITEM_LANG + item, properNaming(item));
  }

  /**
   * @param item need item to be used in lang file, auto names it + capitalize it fully
   */
  protected void addChunkAutoName(String item) {
    final String CHUNK_LANG = ITEM_LANG + "raw_";
    add(CHUNK_LANG + item, properNaming("raw_" + item));
  }

  /**
   * @param item need item to be used in lang file, auto names it + capitalize it fully
   */
  protected void addPieceAutoName(String item) {
    add(ITEM_LANG + item + "_pieces", properNaming(item + "_pieces"));
  }

  /**
   * @param item need item to be used in lang file, auto names it + capitalize it fully
   */
  protected void addIngotAutoName(String item) {
    add(ITEM_LANG + item + "_ingot", properNaming(item + "_ingot"));
  }
  /**
   * @param item need item to be used in lang file, auto names it + capitalize it fully
   */
  protected void addNuggetAutoName(String item) {
    add(ITEM_LANG + item + "_nugget", properNaming(item + "_nugget"));
  }

  /**
   * @param mesh need mesh to be used in lang file, auto names it + capitalize it fully
   */
  protected void addMeshAutoName(String mesh) {
    if (Objects.equals(mesh, "none")) {
      return;
    }
    add(ITEM_LANG + mesh, properNaming(mesh));
  }

  /**
   * @param fluid need fluid to be used in lang file
   * @param name set name of fluid that is shown ingame
   */
  protected void addFluid(String fluid, String name) {
    final String FLUID_LANG = "fluid_type." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ".";
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

  /**
   * @param block need block to be used in lang file, auto names it + capitalize it fully
   */
  protected void addBlockAutoName(String block) {
    final String BLOCK_LANG = "block." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ".";
    add(BLOCK_LANG + block, properNaming(block));
  }

  /**
   * @param ore need ore to be used in lang file, auto names it + capitalize it fully
   */
  protected void addOreAutoName(Ore ore) {
    if (ore.getRawOreItem().left().isPresent()) {
      addChunkAutoName(ore.getOreName());
    }
    addPieceAutoName(ore.getOreName());
    if (ore.getIngotItem().left().isPresent()) {
      addIngotAutoName(ore.getOreName());
    }
    if (ore.getNuggetItem().left().isPresent()) {
      addNuggetAutoName(ore.getOreName());
    }
  }
}
