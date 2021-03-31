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
        registerTags();
    }

    protected void registerTags() {
        registerWoodenCrucibles();
        registerFiredCrucibles();
        registerWoodenBarrels();
        registerFiredBarrels();
        registerSieves();
    }

    private void registerWoodenCrucibles() {
        tag(ExNihiloTags.WOODEN_CRUCIBLE_BLOCK).add(ExNihiloBlocks.CRUCIBLE_ACACIA.get());
        tag(ExNihiloTags.WOODEN_CRUCIBLE_BLOCK).add(ExNihiloBlocks.CRUCIBLE_BIRCH.get());
        tag(ExNihiloTags.WOODEN_CRUCIBLE_BLOCK).add(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get());
        tag(ExNihiloTags.WOODEN_CRUCIBLE_BLOCK).add(ExNihiloBlocks.CRUCIBLE_JUNGLE.get());
        tag(ExNihiloTags.WOODEN_CRUCIBLE_BLOCK).add(ExNihiloBlocks.CRUCIBLE_SPRUCE.get());
        tag(ExNihiloTags.WOODEN_CRUCIBLE_BLOCK).add(ExNihiloBlocks.CRUCIBLE_OAK.get());
    }

    private void registerFiredCrucibles() {
        tag(ExNihiloTags.FIRED_CRUCIBLE_BLOCK).add(ExNihiloBlocks.CRUCIBLE_WARPED.get());
        tag(ExNihiloTags.FIRED_CRUCIBLE_BLOCK).add(ExNihiloBlocks.CRUCIBLE_CRIMSON.get());
        tag(ExNihiloTags.FIRED_CRUCIBLE_BLOCK).add(ExNihiloBlocks.CRUCIBLE_FIRED.get());
    }

    private void registerWoodenBarrels() {
        tag(ExNihiloTags.WOODEN_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_ACACIA.get());
        tag(ExNihiloTags.WOODEN_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_BIRCH.get());
        tag(ExNihiloTags.WOODEN_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_DARK_OAK.get());
        tag(ExNihiloTags.WOODEN_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_JUNGLE.get());
        tag(ExNihiloTags.WOODEN_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_SPRUCE.get());
        tag(ExNihiloTags.WOODEN_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_OAK.get());
    }

    private void registerFiredBarrels() {
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_STONE.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_BLACK.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_BLUE.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_BROWN.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_CYAN.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_GRAY.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_GREEN.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_LIGHT_BLUE.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_LIGHT_GRAY.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_LIME.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_MAGENTA.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_ORANGE.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_PINK.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_PURPLE.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_YELLOW.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_RED.get());
        tag(ExNihiloTags.FIRED_BARREL_BLOCK).add(ExNihiloBlocks.BARREL_GLASS_WHITE.get());
    }

    private void registerSieves() {
        tag(ExNihiloTags.SIEVE_BLOCK).add(ExNihiloBlocks.SIEVE_WARPED.get());
        tag(ExNihiloTags.SIEVE_BLOCK).add(ExNihiloBlocks.SIEVE_CRIMSON.get());
        tag(ExNihiloTags.SIEVE_BLOCK).add(ExNihiloBlocks.SIEVE_OAK.get());
        tag(ExNihiloTags.SIEVE_BLOCK).add(ExNihiloBlocks.SIEVE_SPRUCE.get());
        tag(ExNihiloTags.SIEVE_BLOCK).add(ExNihiloBlocks.SIEVE_JUNGLE.get());
        tag(ExNihiloTags.SIEVE_BLOCK).add(ExNihiloBlocks.SIEVE_DARK_OAK.get());
        tag(ExNihiloTags.SIEVE_BLOCK).add(ExNihiloBlocks.SIEVE_ACACIA.get());
        tag(ExNihiloTags.SIEVE_BLOCK).add(ExNihiloBlocks.SIEVE_BIRCH.get());
    }
}
