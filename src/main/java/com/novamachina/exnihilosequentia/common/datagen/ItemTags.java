package com.novamachina.exnihilosequentia.common.datagen;

import com.novamachina.exnihilosequentia.common.api.ExNihiloTags;
import com.novamachina.exnihilosequentia.common.item.ore.EnumOre;
import com.novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTags extends ItemTagsProvider {
    public ItemTags(DataGenerator generator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, Constants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        registerOres();
        registerSeeds();
        registerMeats();
    }

    private void registerMeats() {
        registerUncooked();
        registerCooked();
    }

    private void registerCooked() {
        func_240522_a_(ExNihiloTags.MEAT_COOKED).func_240532_a_(Items.COOKED_CHICKEN);
        func_240522_a_(ExNihiloTags.MEAT_COOKED).func_240532_a_(Items.COOKED_COD);
        func_240522_a_(ExNihiloTags.MEAT_COOKED).func_240532_a_(Items.COOKED_MUTTON);
        func_240522_a_(ExNihiloTags.MEAT_COOKED).func_240532_a_(Items.COOKED_PORKCHOP);
        func_240522_a_(ExNihiloTags.MEAT_COOKED).func_240532_a_(Items.COOKED_RABBIT);
        func_240522_a_(ExNihiloTags.MEAT_COOKED).func_240532_a_(Items.COOKED_SALMON);
        func_240522_a_(ExNihiloTags.MEAT_COOKED).func_240532_a_(Items.COOKED_BEEF);
    }

    private void registerUncooked() {
        func_240522_a_(ExNihiloTags.MEAT_UNCOOKED).func_240532_a_(Items.CHICKEN);
        func_240522_a_(ExNihiloTags.MEAT_UNCOOKED).func_240532_a_(Items.COD);
        func_240522_a_(ExNihiloTags.MEAT_UNCOOKED).func_240532_a_(Items.MUTTON);
        func_240522_a_(ExNihiloTags.MEAT_UNCOOKED).func_240532_a_(Items.PORKCHOP);
        func_240522_a_(ExNihiloTags.MEAT_UNCOOKED).func_240532_a_(Items.RABBIT);
        func_240522_a_(ExNihiloTags.MEAT_UNCOOKED).func_240532_a_(Items.SALMON);
        func_240522_a_(ExNihiloTags.MEAT_UNCOOKED).func_240532_a_(Items.BEEF);
    }

    private void registerSeeds() {
        for(EnumSeed seed : EnumSeed.values()) {
            func_240522_a_(Tags.Items.SEEDS).func_240532_a_(seed.getRegistryObject().get());
        }
    }

    private void registerOres() {
        for(EnumOre ore : EnumOre.values()) {
            ExNihiloTags.OreTag tags = ExNihiloTags.getOreTags(ore);
            if(!ore.isVanilla()) {
                Item ingot = ore.getIngotItem().get();
                Item chunk = ore.getChunkItem().get();

                func_240522_a_(tags.getIngotTag()).func_240532_a_(ingot);
                func_240522_a_(Tags.Items.INGOTS).func_240531_a_(tags.getIngotTag());
                func_240522_a_(tags.getOreTag()).func_240532_a_(chunk);
                func_240522_a_(Tags.Items.ORES).func_240531_a_(tags.getOreTag());
            } else {
                Item chunk = ore.getChunkItem().get();
                func_240522_a_(tags.getOreTag()).func_240532_a_(chunk);
                func_240522_a_(Tags.Items.ORES).func_240531_a_(tags.getOreTag());
            }
        }
    }
}
