package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.api.datagen.AbstractItemTagGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ExNihiloItemTagsGenerator extends AbstractItemTagGenerator {
    public ExNihiloItemTagsGenerator(@Nonnull final DataGenerator generator,
                                     @Nonnull final BlockTagsProvider blockTagsProvider,
                                     @Nonnull final ExistingFileHelper existingFileHelper) {
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
        registerWoodenBarrel();
        registerWoodenCrucible();
        registerWoodenSieve();
    }

    private void registerCrooks() {
        for (@Nonnull final EnumCrook crook : EnumCrook.values()) {
            @Nullable final RegistryObject<Item> registryObject = crook.getRegistryObject();
            if (registryObject == null)
                continue;
            tag(ExNihiloTags.CROOK).add(registryObject.get());
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
        for (@Nonnull final EnumHammer hammer : EnumHammer.values()) {
            @Nullable final RegistryObject<Item> registryObject = hammer.getRegistryObject();
            if (registryObject == null)
                continue;
            tag(ExNihiloTags.HAMMER).add(registryObject.get());
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
        for (@Nonnull final EnumOre ore : EnumOre.values()) {
            ExNihiloTags.OreTag tags = ExNihiloTags.getOreTags(ore);
            registerOre(ore, tags);
        }
    }

    private void registerSeeds() {
        for (@Nonnull final EnumSeed seed : EnumSeed.values()) {
            @Nullable final RegistryObject<Item> registryObject = seed.getRegistryObject();
            if (registryObject == null)
                continue;
            tag(Tags.Items.SEEDS).add(registryObject.get());
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

    private void registerWoodenCrucible() {
        tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_ACACIA.get().asItem());
        tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_BIRCH.get().asItem());
        tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get().asItem());
        tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_JUNGLE.get().asItem());
        tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_OAK.get().asItem());
        tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_SPRUCE.get().asItem());
        tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_CRIMSON.get().asItem());
        tag(ExNihiloTags.CRUCIBLE).add(ExNihiloBlocks.CRUCIBLE_WARPED.get().asItem());
    }

    private void registerWoodenBarrel() {
        tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_ACACIA.get().asItem());
        tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_BIRCH.get().asItem());
        tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_DARK_OAK.get().asItem());
        tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_JUNGLE.get().asItem());
        tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_OAK.get().asItem());
        tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_SPRUCE.get().asItem());
        tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_CRIMSON.get().asItem());
        tag(ExNihiloTags.BARREL).add(ExNihiloBlocks.BARREL_WARPED.get().asItem());
    }

    private void registerWoodenSieve() {
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_ACACIA.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_BIRCH.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_DARK_OAK.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_JUNGLE.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_OAK.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_SPRUCE.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_CRIMSON.get().asItem());
        tag(ExNihiloTags.SIEVE).add(ExNihiloBlocks.SIEVE_WARPED.get().asItem());
    }
}
