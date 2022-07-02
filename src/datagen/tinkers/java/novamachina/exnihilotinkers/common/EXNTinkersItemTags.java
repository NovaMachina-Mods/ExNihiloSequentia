package novamachina.exnihilotinkers.common;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractItemTagGenerator;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilotinkers.common.init.EXNTinkersItems;
import novamachina.exnihilotinkers.common.init.tconstruct.EXNTinkersTinkerItems;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import novamachina.exnihilotinkers.common.utility.EXNTinkersTags;
import slimeknights.tconstruct.common.TinkerTags;

public class EXNTinkersItemTags extends AbstractItemTagGenerator {

  public EXNTinkersItemTags(DataGenerator generator, BlockTagsProvider blockTagsProvider,
      ExistingFileHelper existingFileHelper) {
    super(generator, blockTagsProvider, EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS,
        existingFileHelper);
  }

  @Override
  protected void addTags() {
    registerOre(EXNTinkersItems.COBALT, new ExNihiloTags.OreTag(EXNTinkersItems.COBALT));
    this.tag(ExNihiloTags.CROOK).add(EXNTinkersTinkerItems.exnCrook.asItem());
    this.tag(ExNihiloTags.HAMMER).add(EXNTinkersTinkerItems.exnHammer.asItem());
    this.tag(TinkerTags.Items.MODIFIABLE).add(EXNTinkersTinkerItems.exnHammer.asItem(), EXNTinkersTinkerItems.exnCrook.asItem());
    this.tag(TinkerTags.Items.HARVEST).add(EXNTinkersTinkerItems.exnCrook.get());
    this.tag(TinkerTags.Items.STONE_HARVEST).add(EXNTinkersTinkerItems.exnHammer.get());
    this.tag(TinkerTags.Items.MELEE_OR_HARVEST).add(EXNTinkersTinkerItems.exnHammer.get());
    this.tag(TinkerTags.Items.DURABILITY).add(EXNTinkersTinkerItems.exnCrook.get(), EXNTinkersTinkerItems.exnHammer.get());
    this.tag(TinkerTags.Items.MULTIPART_TOOL).add(EXNTinkersTinkerItems.exnCrook.get(), EXNTinkersTinkerItems.exnHammer.get());
    this.tag(TinkerTags.Items.TOOL_PARTS).add(EXNTinkersTinkerItems.crookHead.get());
    this.tag(TinkerTags.Items.GOLD_CASTS).add(EXNTinkersTinkerItems.crookHeadCast.get());
    this.tag(TinkerTags.Items.RED_SAND_CASTS).add(EXNTinkersTinkerItems.crookHeadCast.getRedSand());
    this.tag(TinkerTags.Items.SAND_CASTS).add(EXNTinkersTinkerItems.crookHeadCast.getSand());
    this.tag(EXNTinkersTags.MULTI_USE).add(EXNTinkersTinkerItems.crookHeadCast.get());
    this.tag(EXNTinkersTags.SINGLE_USE).add(EXNTinkersTinkerItems.crookHeadCast.getSand(), EXNTinkersTinkerItems.crookHeadCast.getRedSand());
  }
}
