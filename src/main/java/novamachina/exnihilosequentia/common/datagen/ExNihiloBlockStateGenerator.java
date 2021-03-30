package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractBlockStateGenerator;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import static net.minecraft.block.CakeBlock.BITES;

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
        registerCake();
        registerFluids();
    }

    private String getRegistryName(Block b) {
        return b.getRegistryName().toString();
    }

    private void registerBarrel(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block), modLoc("block/barrel"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, texture));

        simpleItemBlock(block, model.model);
    }

    private void registerBarrels() {
        registerBarrel(ExNihiloBlocks.BARREL_STONE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/stone"));
        registerBarrel(ExNihiloBlocks.BARREL_ACACIA.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/acacia_planks"));
        registerBarrel(ExNihiloBlocks.BARREL_BIRCH.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/birch_planks"));
        registerBarrel(ExNihiloBlocks.BARREL_DARK_OAK.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/dark_oak_planks"));
        registerBarrel(ExNihiloBlocks.BARREL_JUNGLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/jungle_planks"));
        registerBarrel(ExNihiloBlocks.BARREL_SPRUCE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/spruce_planks"));
        registerBarrel(ExNihiloBlocks.BARREL_WOOD.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/oak_planks"));
        registerBarrel(ExNihiloBlocks.BARREL_CRIMSON.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/crimson_planks"));
        registerBarrel(ExNihiloBlocks.BARREL_WARPED.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/warped_planks"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_YELLOW.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/yellow_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_WHITE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/white_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_RED.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/red_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_PURPLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/purple_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_PINK.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/pink_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_ORANGE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/orange_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_MAGENTA.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/magenta_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_LIME.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/lime_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_LIGHT_GRAY.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/light_gray_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_LIGHT_BLUE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/light_blue_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_GREEN.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/green_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_GRAY.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/gray_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_CYAN.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/cyan_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_BROWN.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/brown_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_BLUE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/blue_stained_glass"));
        registerBarrel(ExNihiloBlocks.BARREL_GLASS_BLACK.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/black_stained_glass"));
    }

    private void registerCake() {
        VariantBlockStateBuilder builder = getVariantBuilder(ExNihiloBlocks.END_CAKE.get());

        for (int i = 1; i < 7; i++) {
            VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
            ConfiguredModel model = new ConfiguredModel(models().getExistingFile(new ResourceLocation("exnihilosequentia:block/cake_slice" + i)));
            partialBlockstate.with(BITES, i).addModels(model);
        }
        VariantBlockStateBuilder.PartialBlockstate partialBlockstate = builder.partialState();
        ConfiguredModel model = new ConfiguredModel(models().getExistingFile(new ResourceLocation("exnihilosequentia:block/cake_uneaten")));
        partialBlockstate.with(BITES, 0).addModels(model);
    }

    private void registerCrucible(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block), modLoc("block/crucible"))
                .texture(PARTICLE_TAG, texture)
                .texture("top", texture)
                .texture("bottom", texture)
                .texture("side", texture)
                .texture("inside", texture));

        simpleItemBlock(block, model.model);
    }

    private void registerCrucibles() {

        registerCrucible(ExNihiloBlocks.CRUCIBLE_FIRED.get(), new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/crucible_fired"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_UNFIRED.get(), new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/crucible_unfired"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_ACACIA.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/acacia_log"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_BIRCH.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/birch_log"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/dark_oak_log"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_JUNGLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/jungle_log"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_SPRUCE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/spruce_log"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_WOOD.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/oak_log"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_CRIMSON.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/crimson_stem"));
        registerCrucible(ExNihiloBlocks.CRUCIBLE_WARPED.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/warped_stem"));
    }

    private void registerFluids() {
        registerFluid(ExNihiloFluids.WITCH_WATER.get());
        registerFluid(ExNihiloFluids.SEA_WATER.get());
    }

    private void registerSieve(Block block, ResourceLocation texture) {
        ConfiguredModel model = new ConfiguredModel(models().withExistingParent(getRegistryName(block), modLoc("block/sieve_base"))
                .texture("texture", texture)
                .texture(PARTICLE_TAG, texture));
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        MultiPartBlockStateBuilder.PartBuilder partBuilder = builder.part().modelFile(model.model).addModel();
        partBuilder.end();

        for (EnumMesh mesh : EnumMesh.values()) {
            if (mesh != EnumMesh.NONE) {
                partBuilder = builder.part()
                        .modelFile(new ModelFile.ExistingModelFile(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/" + mesh.getMeshName()), models().existingFileHelper)).addModel();
                partBuilder.condition(BlockSieve.MESH, mesh);
                partBuilder.end();
            }
        }
        simpleBlockItem(block, model.model);
    }

    private void registerSieves() {
        registerSieve(ExNihiloBlocks.SIEVE_ACACIA.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/acacia_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_BIRCH.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/birch_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_DARK_OAK.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/dark_oak_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_JUNGLE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/jungle_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_SPRUCE.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/spruce_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_WOOD.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/oak_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_CRIMSON.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/crimson_planks"));
        registerSieve(ExNihiloBlocks.SIEVE_WARPED.get(), new ResourceLocation(ExNihiloConstants.ModIds.MINECRAFT, "block/warped_planks"));
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
