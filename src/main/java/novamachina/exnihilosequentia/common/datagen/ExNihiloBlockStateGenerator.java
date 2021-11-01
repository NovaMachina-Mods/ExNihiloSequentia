package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractBlockStateGenerator;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;

public class ExNihiloBlockStateGenerator extends AbstractBlockStateGenerator {

    public ExNihiloBlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerSimpleBlocks();
        registerSieves();
        registerBarrels();
        registerCrucibles();
        registerCakes();
        registerFluids();
    }

    private void registerCakes() {
        createCake(ExNihiloBlocks.END_CAKE.get());
    }

    private void registerBarrels() {
        createBarrel(ExNihiloBlocks.STONE_BARREL.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stone"));
        createBarrel(ExNihiloBlocks.ACACIA_BARREL.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/acacia_planks"));
        createBarrel(ExNihiloBlocks.BIRCH_BARREL.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/birch_planks"));
        createBarrel(ExNihiloBlocks.DARK_OAK_BARREL.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/dark_oak_planks"));
        createBarrel(ExNihiloBlocks.JUNGLE_BARREL.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/jungle_planks"));
        createBarrel(ExNihiloBlocks.OAK_BARREL.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/oak_planks"));
        createBarrel(ExNihiloBlocks.SPRUCE_BARREL.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/spruce_planks"));
        createBarrel(ExNihiloBlocks.CRIMSON_BARREL.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/crimson_planks"));
        createBarrel(ExNihiloBlocks.WARPED_BARREL.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/warped_planks"));
    }

    private void registerCrucibles() {
        createCrucible(ExNihiloBlocks.FIRED_CRUCIBLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/fired_crucible"));
        createCrucible(ExNihiloBlocks.UNFIRED_CRUCIBLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/unfired_crucible"));
        createCrucible(ExNihiloBlocks.ACACIA_CRUCIBLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/acacia_log"));
        createCrucible(ExNihiloBlocks.BIRCH_CRUCIBLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/birch_log"));
        createCrucible(ExNihiloBlocks.DARK_OAK_CRUCIBLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/dark_oak_log"));
        createCrucible(ExNihiloBlocks.JUNGLE_CRUCIBLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/jungle_log"));
        createCrucible(ExNihiloBlocks.OAK_CRUCIBLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/oak_log"));
        createCrucible(ExNihiloBlocks.SPRUCE_CRUCIBLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/spruce_log"));
        createCrucible(ExNihiloBlocks.CRIMSON_CRUCIBLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/crimson_stem"));
        createCrucible(ExNihiloBlocks.WARPED_CRUCIBLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/warped_stem"));
    }

    private void registerFluids() {
        createFluid(ExNihiloFluids.WITCH_WATER.get());
        createFluid(ExNihiloFluids.SEA_WATER.get());
    }

    private void registerSieves() {
        createSieve(ExNihiloBlocks.ACACIA_SIEVE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_acacia_log"));
        createSieve(ExNihiloBlocks.BIRCH_SIEVE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_birch_log"));
        createSieve(ExNihiloBlocks.DARK_OAK_SIEVE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_dark_oak_log"));
        createSieve(ExNihiloBlocks.JUNGLE_SIEVE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_jungle_log"));
        createSieve(ExNihiloBlocks.OAK_SIEVE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_oak_log"));
        createSieve(ExNihiloBlocks.SPRUCE_SIEVE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_spruce_log"));
        createSieve(ExNihiloBlocks.CRIMSON_SIEVE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_crimson_stem"));
        createSieve(ExNihiloBlocks.WARPED_SIEVE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_warped_stem"));
    }

    private void registerSimpleBlocks() {
        basicBlock(ExNihiloBlocks.CRUSHED_ANDESITE.get());
        basicBlock(ExNihiloBlocks.CRUSHED_DIORITE.get());
        basicBlock(ExNihiloBlocks.CRUSHED_GRANITE.get());
        basicBlock(ExNihiloBlocks.CRUSHED_NETHERRACK.get());
        basicBlock(ExNihiloBlocks.CRUSHED_END_STONE.get());
        basicBlock(ExNihiloBlocks.DUST.get());
        basicBlock(ExNihiloBlocks.INFESTED_LEAVES.get());
        basicBlock(ExNihiloBlocks.INFESTING_LEAVES.get());
    }
}
