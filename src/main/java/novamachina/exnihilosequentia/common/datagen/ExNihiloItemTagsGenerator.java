package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractItemTagGenerator;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.api.utility.ExNihiloTags;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;

public class ExNihiloItemTagsGenerator extends AbstractItemTagGenerator {
    public ExNihiloItemTagsGenerator(DataGenerator generator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    @Override
    protected void addTags() {
        registerOres();
        registerMeats();
        registerHammers();
        registerCrooks();
        registerMisc();
        registerWoodenBarrel();
        registerWoodenCrucible();
        registerWoodenSieve();
    }

    private void registerCrooks() {
        for (EnumCrook crook : EnumCrook.values()) {
            tag(ExNihiloTags.CROOK).add(crook.getRegistryObject().get());
        }
    }

    private void registerCooked() {
        tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_CHICKEN, Items.COOKED_COD, Items.COOKED_MUTTON, Items.COOKED_PORKCHOP,
                Items.COOKED_RABBIT, Items.COOKED_SALMON, Items.COOKED_BEEF);
    }

    private void registerHammers() {
        for (EnumHammer hammer : EnumHammer.values()) {
            tag(ExNihiloTags.HAMMER).add(hammer.getRegistryObject().get());
        }
    }

    private void registerMeats() {
        registerUncooked();
        registerCooked();
    }

    private void registerMisc() {
        tag(ExNihiloTags.CLAY).add(Items.CLAY_BALL);
    }

    private void registerOres() {
        for (EnumOre ore : EnumOre.values()) {
            ExNihiloTags.OreTag tags = ExNihiloTags.getOreTags(ore);
            registerOre(ore, tags);
        }
    }

    private void registerUncooked() {
        tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.CHICKEN, Items.COD, Items.MUTTON, Items.PORKCHOP, Items.RABBIT, Items.SALMON, Items.BEEF);
    }

    private void registerWoodenCrucible() {
        tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.ACACIA_CRUCIBLE.get().asItem(), ExNihiloBlocks.BIRCH_CRUCIBLE.get().asItem(),
                ExNihiloBlocks.DARK_OAK_CRUCIBLE.get().asItem(), ExNihiloBlocks.JUNGLE_CRUCIBLE.get().asItem(),
                ExNihiloBlocks.OAK_CRUCIBLE.get().asItem(), ExNihiloBlocks.SPRUCE_CRUCIBLE.get().asItem(),
                ExNihiloBlocks.CRIMSON_CRUCIBLE.get().asItem(), ExNihiloBlocks.WARPED_CRUCIBLE.get().asItem());
    }

    private void registerWoodenBarrel() {
        tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.ACACIA_BARREL.get().asItem(), ExNihiloBlocks.BIRCH_BARREL.get().asItem(),
                ExNihiloBlocks.DARK_OAK_BARREL.get().asItem(), ExNihiloBlocks.JUNGLE_BARREL.get().asItem(),
                ExNihiloBlocks.OAK_BARREL.get().asItem(), ExNihiloBlocks.SPRUCE_BARREL.get().asItem(),
                ExNihiloBlocks.CRIMSON_BARREL.get().asItem(), ExNihiloBlocks.WARPED_BARREL.get().asItem());
    }

    private void registerWoodenSieve() {
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.ACACIA_SIEVE.get().asItem(), ExNihiloBlocks.BIRCH_SIEVE.get().asItem(),
                ExNihiloBlocks.DARK_OAK_SIEVE.get().asItem(), ExNihiloBlocks.JUNGLE_SIEVE.get().asItem(),
                ExNihiloBlocks.OAK_SIEVE.get().asItem(), ExNihiloBlocks.SPRUCE_SIEVE.get().asItem(),
                ExNihiloBlocks.CRIMSON_SIEVE.get().asItem(), ExNihiloBlocks.WARPED_SIEVE.get().asItem());
    }
}
