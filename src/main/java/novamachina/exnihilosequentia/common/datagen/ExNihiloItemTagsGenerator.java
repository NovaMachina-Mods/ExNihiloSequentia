package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.api.datagen.AbstractItemTagGenerator;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloItemTagsGenerator extends AbstractItemTagGenerator {
    public ExNihiloItemTagsGenerator(DataGenerator generator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        registerOres();
        registerSeeds();
        registerMeats();
        registerHammers();
        registerCrooks();
        registerMisc();
    }

    private void registerCrooks() {
        for (EnumCrook crook : EnumCrook.values()) {
            getOrCreateBuilder(ExNihiloTags.CROOK).addItemEntry(crook.getRegistryObject().get());
        }
    }

    private void registerCooked() {
        getOrCreateBuilder(ExNihiloTags.MEAT_COOKED).addItemEntry(Items.COOKED_CHICKEN);
        getOrCreateBuilder(ExNihiloTags.MEAT_COOKED).addItemEntry(Items.COOKED_COD);
        getOrCreateBuilder(ExNihiloTags.MEAT_COOKED).addItemEntry(Items.COOKED_MUTTON);
        getOrCreateBuilder(ExNihiloTags.MEAT_COOKED).addItemEntry(Items.COOKED_PORKCHOP);
        getOrCreateBuilder(ExNihiloTags.MEAT_COOKED).addItemEntry(Items.COOKED_RABBIT);
        getOrCreateBuilder(ExNihiloTags.MEAT_COOKED).addItemEntry(Items.COOKED_SALMON);
        getOrCreateBuilder(ExNihiloTags.MEAT_COOKED).addItemEntry(Items.COOKED_BEEF);
    }

    private void registerHammers() {
        for (EnumHammer hammer : EnumHammer.values()) {
            getOrCreateBuilder(ExNihiloTags.HAMMER).addItemEntry(hammer.getRegistryObject().get());
        }
    }

    private void registerMeats() {
        registerUncooked();
        registerCooked();
    }

    private void registerMisc() {
        getOrCreateBuilder(ExNihiloTags.CLAY).addItemEntry(Items.CLAY_BALL);
    }

    private void registerOres() {
        for (EnumOre ore : EnumOre.values()) {
            ExNihiloTags.OreTag tags = ExNihiloTags.getOreTags(ore);
            registerOre(ore, tags);
        }
    }

    private void registerSeeds() {
        for (EnumSeed seed : EnumSeed.values()) {
            getOrCreateBuilder(Tags.Items.SEEDS).addItemEntry(seed.getRegistryObject().get());
        }
    }

    private void registerUncooked() {
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.CHICKEN);
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.COD);
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.MUTTON);
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.PORKCHOP);
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.RABBIT);
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.SALMON);
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.BEEF);
    }
}
