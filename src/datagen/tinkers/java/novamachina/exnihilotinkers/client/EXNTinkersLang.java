package novamachina.exnihilotinkers.client;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.api.datagen.AbstractLangGenerator;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import novamachina.exnihilotinkers.common.init.EXNTinkersBlocks;
import novamachina.exnihilotinkers.common.init.EXNTinkersItems;
import novamachina.exnihilotinkers.common.init.tconstruct.EXNTinkersTinkerItems;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;

public class EXNTinkersLang extends AbstractLangGenerator {
    public EXNTinkersLang(DataGenerator gen, String locale) {
        super(gen, EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS, locale);
    }

    private String properNaming(String item) {
        return StringUtils.capitalize(item.replace("_", " "));
    }

    @Override
    protected void addTranslations() {
        for (int i = 0; i < EXNTinkersBlocks.BLOCKS.getEntries().size(); i++) {
            Block block = EXNTinkersBlocks.BLOCKS.getEntries().stream().toList().get(i).get();
            add("block."+ EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS + "." + block.getRegistryName().getPath(), properNaming(block.getRegistryName().getPath()));
        }

        String ITEM_STRING = "item."+ EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS + ".";
        for (int i = 0; i < EXNTinkersItems.ITEMS.getEntries().size(); i++) {
            Item item = EXNTinkersItems.ITEMS.getEntries().stream().toList().get(i).get();
            add(ITEM_STRING + item.getRegistryName().getPath(), properNaming(item.getRegistryName().getPath()));
        }
        add(ITEM_STRING + EXNTinkersTinkerItems.exnCrook.asItem(), properNaming(EXNTinkersTinkerItems.exnCrook.asItem().getRegistryName().getPath().replace("exn_", "")));
        add(ITEM_STRING + EXNTinkersTinkerItems.exnHammer.asItem(), properNaming(EXNTinkersTinkerItems.exnHammer.asItem().getRegistryName().getPath().replace("exn_", "Nihilo ")));
        add(ITEM_STRING + EXNTinkersTinkerItems.crookHead.asItem(), properNaming(EXNTinkersTinkerItems.crookHead.asItem().getRegistryName().getPath()));
        add(ITEM_STRING + EXNTinkersTinkerItems.crookHeadCast.asItem(), properNaming(EXNTinkersTinkerItems.crookHeadCast.asItem().getRegistryName().getPath().replace("_cast", "_gold_cast")));
        add(ITEM_STRING + EXNTinkersTinkerItems.crookHeadCast.getRedSand().asItem(), properNaming(EXNTinkersTinkerItems.crookHeadCast.getRedSand().asItem().getRegistryName().getPath()));
        add(ITEM_STRING + EXNTinkersTinkerItems.crookHeadCast.getSand().asItem(), properNaming(EXNTinkersTinkerItems.crookHeadCast.getSand().asItem().getRegistryName().getPath()));
        add(ITEM_STRING + EXNTinkersTinkerItems.exnCrook.asItem() + ".description", "The Crook is a precision leaf cutting tool, breaking leaves and harvesting silkworms, strings and saplings.");
        add(ITEM_STRING + EXNTinkersTinkerItems.exnHammer.asItem() + ".description", "The Ex Nihilo Hammer is a crushing tool, effective on stones, sand and gravel. It crushes blocks down to dust.");
    }
}
