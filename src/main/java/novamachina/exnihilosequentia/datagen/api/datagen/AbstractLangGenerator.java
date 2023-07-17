package novamachina.exnihilosequentia.datagen.api.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;

public class AbstractLangGenerator extends LanguageProvider {

  private final String ITEM_LANG;
  private final String BLOCK_LANG;
  private final String FLUID_LANG;

  public AbstractLangGenerator(DataGenerator gen, String modId, String locale) {
    super(gen, modId, locale);
    this.ITEM_LANG = String.format("item.%s.", modId);
    this.BLOCK_LANG = String.format("block.%s.", modId);
    this.FLUID_LANG = String.format("fluid_type.%s.", modId);
    ;
  }

  @Override
  protected void addTranslations() {}

  protected String properNaming(String item) {
    return StringUtils.capitalize(item.replace("_", " "));
  }

  /**
   * @param item need item to be used in lang file, auto names it + capitalize it fully
   */
  protected void addItemAutoName(String item) {
    add(ITEM_LANG + item, properNaming(item));
  }

  protected void addItemName(ItemDefinition<? extends Item> definition) {
    add(ITEM_LANG + definition.getId().getPath(), definition.getEnglishName());
  }

  /**
   * @param item need item to be used in lang file, auto names it + capitalize it fully
   */
  protected void addPieceAutoName(String item) {
    add(ITEM_LANG + item + "_pieces", properNaming(item + "_pieces"));
  }

  /**
   * @param fluid need fluid to be used in lang file
   * @param name set name of fluid that is shown ingame
   */
  protected void addFluidName(String fluid, String name) {
    add(FLUID_LANG + fluid, name);
  }

  protected void addBlockName(BlockDefinition<? extends Block> definition) {
    add(BLOCK_LANG + definition.getId().getPath(), definition.getEnglishName());
  }
}
