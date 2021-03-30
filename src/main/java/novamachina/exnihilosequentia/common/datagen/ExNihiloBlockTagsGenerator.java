package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nullable;

public class ExNihiloBlockTagsGenerator extends BlockTagsProvider {
    public ExNihiloBlockTagsGenerator(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        registerWoodenCrucibles();
        registerFiredCrucibles();
        registerWoodenBarrels();
        registerFiredBarrels();
        registerSieves();
    }

    private void registerWoodenCrucibles() {
        getOrCreateBuilder(ExNihiloTags.WOODEN_CRUCIBLE_BLOCK).addItemEntry(ExNihiloBlocks.CRUCIBLE_ACACIA.get());
        getOrCreateBuilder(ExNihiloTags.WOODEN_CRUCIBLE_BLOCK).addItemEntry(ExNihiloBlocks.CRUCIBLE_BIRCH.get());
        getOrCreateBuilder(ExNihiloTags.WOODEN_CRUCIBLE_BLOCK).addItemEntry(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get());
        getOrCreateBuilder(ExNihiloTags.WOODEN_CRUCIBLE_BLOCK).addItemEntry(ExNihiloBlocks.CRUCIBLE_JUNGLE.get());
        getOrCreateBuilder(ExNihiloTags.WOODEN_CRUCIBLE_BLOCK).addItemEntry(ExNihiloBlocks.CRUCIBLE_SPRUCE.get());
        getOrCreateBuilder(ExNihiloTags.WOODEN_CRUCIBLE_BLOCK).addItemEntry(ExNihiloBlocks.CRUCIBLE_WOOD.get());
    }

    private void registerFiredCrucibles() {
        getOrCreateBuilder(ExNihiloTags.FIRED_CRUCIBLE_BLOCK).addItemEntry(ExNihiloBlocks.CRUCIBLE_WARPED.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_CRUCIBLE_BLOCK).addItemEntry(ExNihiloBlocks.CRUCIBLE_CRIMSON.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_CRUCIBLE_BLOCK).addItemEntry(ExNihiloBlocks.CRUCIBLE_FIRED.get());
    }

    private void registerWoodenBarrels() {
        getOrCreateBuilder(ExNihiloTags.WOODEN_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_ACACIA.get());
        getOrCreateBuilder(ExNihiloTags.WOODEN_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_BIRCH.get());
        getOrCreateBuilder(ExNihiloTags.WOODEN_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_DARK_OAK.get());
        getOrCreateBuilder(ExNihiloTags.WOODEN_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_JUNGLE.get());
        getOrCreateBuilder(ExNihiloTags.WOODEN_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_SPRUCE.get());
        getOrCreateBuilder(ExNihiloTags.WOODEN_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_WOOD.get());
    }

    private void registerFiredBarrels() {
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_STONE.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_BLACK.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_BLUE.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_BROWN.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_CYAN.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_GRAY.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_GREEN.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_LIGHT_BLUE.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_LIGHT_GRAY.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_LIME.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_MAGENTA.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_ORANGE.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_PINK.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_PURPLE.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_YELLOW.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_RED.get());
        getOrCreateBuilder(ExNihiloTags.FIRED_BARREL_BLOCK).addItemEntry(ExNihiloBlocks.BARREL_GLASS_WHITE.get());
    }

    private void registerSieves() {
        getOrCreateBuilder(ExNihiloTags.SIEVE_BLOCK).addItemEntry(ExNihiloBlocks.SIEVE_WARPED.get());
        getOrCreateBuilder(ExNihiloTags.SIEVE_BLOCK).addItemEntry(ExNihiloBlocks.SIEVE_CRIMSON.get());
        getOrCreateBuilder(ExNihiloTags.SIEVE_BLOCK).addItemEntry(ExNihiloBlocks.SIEVE_WOOD.get());
        getOrCreateBuilder(ExNihiloTags.SIEVE_BLOCK).addItemEntry(ExNihiloBlocks.SIEVE_SPRUCE.get());
        getOrCreateBuilder(ExNihiloTags.SIEVE_BLOCK).addItemEntry(ExNihiloBlocks.SIEVE_JUNGLE.get());
        getOrCreateBuilder(ExNihiloTags.SIEVE_BLOCK).addItemEntry(ExNihiloBlocks.SIEVE_DARK_OAK.get());
        getOrCreateBuilder(ExNihiloTags.SIEVE_BLOCK).addItemEntry(ExNihiloBlocks.SIEVE_ACACIA.get());
        getOrCreateBuilder(ExNihiloTags.SIEVE_BLOCK).addItemEntry(ExNihiloBlocks.SIEVE_BIRCH.get());
    }
}
