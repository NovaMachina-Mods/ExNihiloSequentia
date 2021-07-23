package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractBlockStateGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;

public class ExNihiloBlockStateGenerator extends AbstractBlockStateGenerator {
    private static final String MODID = ModIds.EX_NIHILO_SEQUENTIA;

    public ExNihiloBlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MODID, exFileHelper);
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
        createBarrel(ExNihiloBlocks.BARREL_STONE.get(), new ResourceLocation(MODID, "block/stone_barrel"));
        createBarrel(ExNihiloBlocks.BARREL_ACACIA.get(), new ResourceLocation(MODID, "block/acacia_barrel"));
        createBarrel(ExNihiloBlocks.BARREL_BIRCH.get(), new ResourceLocation(MODID, "block/birch_barrel"));
        createBarrel(ExNihiloBlocks.BARREL_DARK_OAK.get(), new ResourceLocation(MODID, "block/dark_oak_barrel"));
        createBarrel(ExNihiloBlocks.BARREL_JUNGLE.get(), new ResourceLocation(MODID, "block/jungle_barrel"));
        createBarrel(ExNihiloBlocks.BARREL_OAK.get(), new ResourceLocation(MODID, "block/oak_barrel"));
        createBarrel(ExNihiloBlocks.BARREL_SPRUCE.get(), new ResourceLocation(MODID, "block/spruce_barrel"));
        createBarrel(ExNihiloBlocks.BARREL_CRIMSON.get(), new ResourceLocation(MODID, "block/crimson_barrel"));
        createBarrel(ExNihiloBlocks.BARREL_WARPED.get(), new ResourceLocation(MODID, "block/warped_barrel"));
    }

    private void registerCrucibles() {
        createCrucible(ExNihiloBlocks.CRUCIBLE_FIRED.get(), new ResourceLocation(MODID, "block/fired_crucible"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_UNFIRED.get(), new ResourceLocation(MODID, "block/unfired_crucible"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_ACACIA.get(), new ResourceLocation(MODID, "block/acacia_crucible"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_BIRCH.get(), new ResourceLocation(MODID, "block/birch_crucible"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(), new ResourceLocation(MODID, "block/dark_oak_crucible"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_JUNGLE.get(), new ResourceLocation(MODID, "block/jungle_crucible"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_OAK.get(), new ResourceLocation(MODID, "block/oak_crucible"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_SPRUCE.get(), new ResourceLocation(MODID, "block/spruce_crucible"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_CRIMSON.get(), new ResourceLocation(MODID, "block/crimson_crucible"));
        createCrucible(ExNihiloBlocks.CRUCIBLE_WARPED.get(), new ResourceLocation(MODID, "block/warped_crucible"));
    }

    private void registerFluids() {
        registerFluid(ExNihiloFluids.WITCH_WATER.get());
        registerFluid(ExNihiloFluids.SEA_WATER.get());
    }

    private void registerSieves() {
        createSieve(ExNihiloBlocks.SIEVE_ACACIA.get(), new ResourceLocation(MODID, "block/acacia_sieve"));
        createSieve(ExNihiloBlocks.SIEVE_BIRCH.get(), new ResourceLocation(MODID, "block/birch_sieve"));
        createSieve(ExNihiloBlocks.SIEVE_DARK_OAK.get(), new ResourceLocation(MODID, "block/dark_oak_sieve"));
        createSieve(ExNihiloBlocks.SIEVE_JUNGLE.get(), new ResourceLocation(MODID, "block/jungle_sieve"));
        createSieve(ExNihiloBlocks.SIEVE_OAK.get(), new ResourceLocation(MODID, "block/oak_sieve"));
        createSieve(ExNihiloBlocks.SIEVE_SPRUCE.get(), new ResourceLocation(MODID, "block/spruce_sieve"));
        createSieve(ExNihiloBlocks.SIEVE_CRIMSON.get(), new ResourceLocation(MODID, "block/crimson_sieve"));
        createSieve(ExNihiloBlocks.SIEVE_WARPED.get(), new ResourceLocation(MODID, "block/warped_sieve"));
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
