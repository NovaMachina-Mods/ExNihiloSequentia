package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.api.datagen.AbstractItemTagGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
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
        registerSeeds();
        registerMeats();
        registerHammers();
        registerCrooks();
        registerMisc();
        registerCrooks();
        registerFiredBarrels();
        registerFiredCrucibles();
        registerSieves();
        registerWoodenBarrels();
        registerWoodenCrucibles();
    }

    private void registerCrooks() {
        for (EnumCrook crook : EnumCrook.values()) {
            tag(ExNihiloTags.CROOK).add(crook.getRegistryObject().get());
        }
    }

    private void registerCooked() {
        tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_CHICKEN);
        tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_COD);
        tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_MUTTON);
        tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_PORKCHOP);
        tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_RABBIT);
        tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_SALMON);
        tag(ExNihiloTags.MEAT_COOKED).add(Items.COOKED_BEEF);
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

    private void registerSeeds() {
        for (EnumSeed seed : EnumSeed.values()) {
            tag(Tags.Items.SEEDS).add(seed.getRegistryObject().get());
        }
    }

    private void registerUncooked() {
        tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.CHICKEN);
        tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.COD);
        tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.MUTTON);
        tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.PORKCHOP);
        tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.RABBIT);
        tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.SALMON);
        tag(ExNihiloTags.MEAT_UNCOOKED).add(Items.BEEF);
    }

    private void registerWoodenCrucibles() {
        tag(ExNihiloTags.WOODEN_CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_ACACIA.get().asItem());
        tag(ExNihiloTags.WOODEN_CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_BIRCH.get().asItem());
        tag(ExNihiloTags.WOODEN_CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get().asItem());
        tag(ExNihiloTags.WOODEN_CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_JUNGLE.get().asItem());
        tag(ExNihiloTags.WOODEN_CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_SPRUCE.get().asItem());
        tag(ExNihiloTags.WOODEN_CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_OAK.get().asItem());
    }

    private void registerFiredCrucibles() {
        tag(ExNihiloTags.FIRED_CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_WARPED.get().asItem());
        tag(ExNihiloTags.FIRED_CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_CRIMSON.get().asItem());
        tag(ExNihiloTags.FIRED_CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_FIRED.get().asItem());
    }

    private void registerWoodenBarrels() {
        tag(ExNihiloTags.WOODEN_BARREL).add(ExNihiloBlocks.BARREL_ACACIA.get().asItem());
        tag(ExNihiloTags.WOODEN_BARREL).add(ExNihiloBlocks.BARREL_BIRCH.get().asItem());
        tag(ExNihiloTags.WOODEN_BARREL).add(ExNihiloBlocks.BARREL_DARK_OAK.get().asItem());
        tag(ExNihiloTags.WOODEN_BARREL).add(ExNihiloBlocks.BARREL_JUNGLE.get().asItem());
        tag(ExNihiloTags.WOODEN_BARREL).add(ExNihiloBlocks.BARREL_SPRUCE.get().asItem());
        tag(ExNihiloTags.WOODEN_BARREL).add(ExNihiloBlocks.BARREL_OAK.get().asItem());
    }

    private void registerFiredBarrels() {
        tag(ExNihiloTags.FIRED_BARREL).add(ExNihiloBlocks.BARREL_STONE.get().asItem());
    }

    private void registerSieves() {
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_WARPED.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_CRIMSON.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_OAK.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_SPRUCE.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_JUNGLE.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_DARK_OAK.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_ACACIA.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_BIRCH.get().asItem());
    }
}
