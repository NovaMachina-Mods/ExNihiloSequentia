package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractBlockStateGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

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
        createBarrel(ExNihiloBlocks.BARREL_STONE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stone"));
        createBarrel(ExNihiloBlocks.BARREL_ACACIA.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/acacia_planks"));
        createBarrel(ExNihiloBlocks.BARREL_BIRCH.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/birch_planks"));
        createBarrel(ExNihiloBlocks.BARREL_DARK_OAK.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/dark_oak_planks"));
        createBarrel(ExNihiloBlocks.BARREL_JUNGLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/jungle_planks"));
        createBarrel(ExNihiloBlocks.BARREL_OAK.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/oak_planks"));
        createBarrel(ExNihiloBlocks.BARREL_SPRUCE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/spruce_planks"));
        createBarrel(ExNihiloBlocks.BARREL_CRIMSON.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/crimson_planks"));
        createBarrel(ExNihiloBlocks.BARREL_WARPED.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/warped_planks"));
    }

    private void registerCrucibles() {
        createCrucible(ExNihiloBlocks.CRUCIBLE_FIRED.get(), new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/crucible_fired"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_UNFIRED.get(), new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/crucible_unfired"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_ACACIA.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/acacia_log"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_BIRCH.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/birch_log"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/dark_oak_log"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_JUNGLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/jungle_log"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_OAK.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/oak_log"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_SPRUCE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/spruce_log"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_CRIMSON.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/crimson_stem"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_WARPED.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/warped_stem"));
    }

    private void registerFluids() {
        registerFluid(ExNihiloFluids.WITCH_WATER.get());
        registerFluid(ExNihiloFluids.SEA_WATER.get());
    }

    private void registerSieves() {
        createSieve(ExNihiloBlocks.SIEVE_ACACIA.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_acacia_log"));
        createSieve(ExNihiloBlocks.SIEVE_BIRCH.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_birch_log"));
        createSieve(ExNihiloBlocks.SIEVE_DARK_OAK.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_dark_oak_log"));
        createSieve(ExNihiloBlocks.SIEVE_JUNGLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_jungle_log"));
        createSieve(ExNihiloBlocks.SIEVE_OAK.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_oak_log"));
        createSieve(ExNihiloBlocks.SIEVE_SPRUCE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_spruce_log"));
        createSieve(ExNihiloBlocks.SIEVE_CRIMSON.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_crimson_stem"));
        createSieve(ExNihiloBlocks.SIEVE_WARPED.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stripped_warped_stem"));
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
