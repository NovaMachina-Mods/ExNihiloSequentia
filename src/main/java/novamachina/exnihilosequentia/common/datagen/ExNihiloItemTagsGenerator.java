package novamachina.exnihilosequentia.common.datagen;

import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ExNihiloItemTagsGenerator extends ItemTagsProvider {
    public ExNihiloItemTagsGenerator(DataGenerator generator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        registerOres();
        registerSeeds();
        registerMeats();
        registerHammers();
        registerMisc();
    }

    private void registerMisc() {
        getOrCreateBuilder(ExNihiloTags.CLAY).addItemEntry(Items.CLAY_BALL);
    }

    private void registerHammers() {
        for(EnumHammer hammer : EnumHammer.values()) {
            getOrCreateBuilder(ExNihiloTags.HAMMER).addItemEntry(hammer.getRegistryObject().get());
        }
    }

    private void registerMeats() {
        registerUncooked();
        registerCooked();
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

    private void registerUncooked() {
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.CHICKEN);
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.COD);
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.MUTTON);
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.PORKCHOP);
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.RABBIT);
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.SALMON);
        getOrCreateBuilder(ExNihiloTags.MEAT_UNCOOKED).addItemEntry(Items.BEEF);
    }

    private void registerSeeds() {
        for(EnumSeed seed : EnumSeed.values()) {
            getOrCreateBuilder(Tags.Items.SEEDS).addItemEntry(seed.getRegistryObject().get());
        }
    }

    private void registerOres() {
        for(EnumOre ore : EnumOre.values()) {
            ExNihiloTags.OreTag tags = ExNihiloTags.getOreTags(ore);
            if(!ore.isVanilla()) {
                Item ingot = ore.getIngotItem().get();
                Item chunk = ore.getChunkItem().get();

                getOrCreateBuilder(tags.getIngotTag()).addItemEntry(ingot);
                getOrCreateBuilder(Tags.Items.INGOTS).addTag(tags.getIngotTag());
                getOrCreateBuilder(tags.getOreTag()).addItemEntry(chunk);
                getOrCreateBuilder(Tags.Items.ORES).addTag(tags.getOreTag());
            } else {
                Item chunk = ore.getChunkItem().get();
                getOrCreateBuilder(tags.getOreTag()).addItemEntry(chunk);
                getOrCreateBuilder(Tags.Items.ORES).addTag(tags.getOreTag());
            }
        }
    }
}
