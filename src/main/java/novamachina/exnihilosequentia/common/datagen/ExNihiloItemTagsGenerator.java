package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.api.datagen.AbstractItemTagGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

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
		tag(ExNihiloTags.STONE_STICK).add(EnumResource.STONE_STICK.getRegistryObject().get());
        tag(Tags.Items.RODS).add(EnumResource.STONE_STICK.getRegistryObject().get());
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
        tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_ACACIA.get().asItem(), ExNihiloBlocks.CRUCIBLE_BIRCH.get().asItem(),
                ExNihiloBlocks.CRUCIBLE_DARK_OAK.get().asItem(), ExNihiloBlocks.CRUCIBLE_JUNGLE.get().asItem(),
                ExNihiloBlocks.CRUCIBLE_OAK.get().asItem(), ExNihiloBlocks.CRUCIBLE_SPRUCE.get().asItem(),
                ExNihiloBlocks.CRUCIBLE_CRIMSON.get().asItem(), ExNihiloBlocks.CRUCIBLE_WARPED.get().asItem());
    }

    private void registerWoodenBarrel() {
        tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_ACACIA.get().asItem(), ExNihiloBlocks.BARREL_BIRCH.get().asItem(),
                ExNihiloBlocks.BARREL_DARK_OAK.get().asItem(), ExNihiloBlocks.BARREL_JUNGLE.get().asItem(),
                ExNihiloBlocks.BARREL_OAK.get().asItem(), ExNihiloBlocks.BARREL_SPRUCE.get().asItem(),
                ExNihiloBlocks.BARREL_CRIMSON.get().asItem(), ExNihiloBlocks.BARREL_WARPED.get().asItem());
    }

    private void registerWoodenSieve() {
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_ACACIA.get().asItem(), ExNihiloBlocks.SIEVE_BIRCH.get().asItem(),
                ExNihiloBlocks.SIEVE_DARK_OAK.get().asItem(), ExNihiloBlocks.SIEVE_JUNGLE.get().asItem(),
                ExNihiloBlocks.SIEVE_OAK.get().asItem(), ExNihiloBlocks.SIEVE_SPRUCE.get().asItem(),
                ExNihiloBlocks.SIEVE_CRIMSON.get().asItem(), ExNihiloBlocks.SIEVE_WARPED.get().asItem());
    }
}
