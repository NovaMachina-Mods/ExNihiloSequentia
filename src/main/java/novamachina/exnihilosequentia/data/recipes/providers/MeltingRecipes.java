package novamachina.exnihilosequentia.data.recipes.providers;

import java.util.function.Consumer;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.data.recipes.MeltingRecipeBuilder;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity.CrucibleType;
import novamachina.novacore.data.recipes.ISubRecipeProvider;

public class MeltingRecipes implements ISubRecipeProvider {
  @Override
  public void addRecipes(Consumer<FinishedRecipe> consumer) {
    MeltingRecipeBuilder.melting(
            Blocks.COBBLESTONE, new FluidStack(Fluids.LAVA, 250), CrucibleType.FIRED)
        .build(consumer, meltingLoc("cobblestone"));
    MeltingRecipeBuilder.melting(
            Blocks.ANDESITE, new FluidStack(Fluids.LAVA, 250), CrucibleType.FIRED)
        .build(consumer, meltingLoc("andesite"));
    MeltingRecipeBuilder.melting(
            Blocks.BASALT, new FluidStack(Fluids.LAVA, 250), CrucibleType.FIRED)
        .build(consumer, meltingLoc("basalt"));
    MeltingRecipeBuilder.melting(
            Blocks.BLACKSTONE, new FluidStack(Fluids.LAVA, 250), CrucibleType.FIRED)
        .build(consumer, meltingLoc("blackstone"));
    MeltingRecipeBuilder.melting(
            Blocks.CALCITE, new FluidStack(Fluids.LAVA, 250), CrucibleType.FIRED)
        .build(consumer, meltingLoc("calcite"));
    MeltingRecipeBuilder.melting(
            Blocks.DEEPSLATE, new FluidStack(Fluids.LAVA, 250), CrucibleType.FIRED)
        .build(consumer, meltingLoc("deepslate"));
    MeltingRecipeBuilder.melting(
            Blocks.DIORITE, new FluidStack(Fluids.LAVA, 250), CrucibleType.FIRED)
        .build(consumer, meltingLoc("diorite"));
    MeltingRecipeBuilder.melting(
            Blocks.DRIPSTONE_BLOCK, new FluidStack(Fluids.LAVA, 250), CrucibleType.FIRED)
        .build(consumer, meltingLoc("dripstone"));
    MeltingRecipeBuilder.melting(
            Blocks.END_STONE, new FluidStack(Fluids.LAVA, 250), CrucibleType.FIRED)
        .build(consumer, meltingLoc("end_stone"));
    MeltingRecipeBuilder.melting(
            Blocks.GRANITE, new FluidStack(Fluids.LAVA, 250), CrucibleType.FIRED)
        .build(consumer, meltingLoc("granite"));
    MeltingRecipeBuilder.melting(Blocks.TUFF, new FluidStack(Fluids.LAVA, 250), CrucibleType.FIRED)
        .build(consumer, meltingLoc("tuff"));
    MeltingRecipeBuilder.melting(Blocks.STONE, new FluidStack(Fluids.LAVA, 250), CrucibleType.FIRED)
        .build(consumer, meltingLoc("stone"));
    MeltingRecipeBuilder.melting(
            Blocks.GRAVEL, new FluidStack(Fluids.LAVA, 200), CrucibleType.FIRED)
        .build(consumer, meltingLoc("gravel"));
    MeltingRecipeBuilder.melting(
            EXNBlocks.CRUSHED_ANDESITE, new FluidStack(Fluids.LAVA, 200), CrucibleType.FIRED)
        .build(consumer, meltingLoc("crushed_andesite"));
    MeltingRecipeBuilder.melting(
            EXNBlocks.CRUSHED_BASALT, new FluidStack(Fluids.LAVA, 200), CrucibleType.FIRED)
        .build(consumer, meltingLoc("crushed_basalt"));
    MeltingRecipeBuilder.melting(
            EXNBlocks.CRUSHED_BLACKSTONE, new FluidStack(Fluids.LAVA, 200), CrucibleType.FIRED)
        .build(consumer, meltingLoc("crushed_blackstone"));
    MeltingRecipeBuilder.melting(
            EXNBlocks.CRUSHED_CALCITE, new FluidStack(Fluids.LAVA, 200), CrucibleType.FIRED)
        .build(consumer, meltingLoc("crushed_calcite"));
    MeltingRecipeBuilder.melting(
            EXNBlocks.CRUSHED_DEEPSLATE, new FluidStack(Fluids.LAVA, 200), CrucibleType.FIRED)
        .build(consumer, meltingLoc("crushed_deepslate"));
    MeltingRecipeBuilder.melting(
            EXNBlocks.CRUSHED_DIORITE, new FluidStack(Fluids.LAVA, 200), CrucibleType.FIRED)
        .build(consumer, meltingLoc("crushed_diorite"));
    MeltingRecipeBuilder.melting(
            EXNBlocks.CRUSHED_DRIPSTONE, new FluidStack(Fluids.LAVA, 200), CrucibleType.FIRED)
        .build(consumer, meltingLoc("crushed_dripstone"));
    MeltingRecipeBuilder.melting(
            EXNBlocks.CRUSHED_END_STONE, new FluidStack(Fluids.LAVA, 200), CrucibleType.FIRED)
        .build(consumer, meltingLoc("crushed_end_stone"));
    MeltingRecipeBuilder.melting(
            EXNBlocks.CRUSHED_GRANITE, new FluidStack(Fluids.LAVA, 200), CrucibleType.FIRED)
        .build(consumer, meltingLoc("crushed_granite"));
    MeltingRecipeBuilder.melting(
            EXNBlocks.CRUSHED_NETHERRACK, new FluidStack(Fluids.LAVA, 200), CrucibleType.FIRED)
        .build(consumer, meltingLoc("crushed_netherrack"));
    MeltingRecipeBuilder.melting(
            EXNBlocks.CRUSHED_TUFF, new FluidStack(Fluids.LAVA, 200), CrucibleType.FIRED)
        .build(consumer, meltingLoc("crushed_tuff"));
    MeltingRecipeBuilder.melting(Blocks.SAND, new FluidStack(Fluids.LAVA, 100), CrucibleType.FIRED)
        .build(consumer, meltingLoc("sand"));
    MeltingRecipeBuilder.melting(
            EXNBlocks.DUST, new FluidStack(Fluids.LAVA, 50), CrucibleType.FIRED)
        .build(consumer, meltingLoc("dust"));
    MeltingRecipeBuilder.melting(
            Blocks.NETHERRACK, new FluidStack(Fluids.LAVA, 1000), CrucibleType.FIRED)
        .build(consumer, meltingLoc("netherrack"));
    MeltingRecipeBuilder.melting(
            Blocks.OBSIDIAN, new FluidStack(Fluids.LAVA, 1000), CrucibleType.FIRED)
        .build(consumer, meltingLoc("obsidian"));
    MeltingRecipeBuilder.melting(
            ItemTags.SAPLINGS, new FluidStack(Fluids.WATER, 250), CrucibleType.WOOD)
        .build(consumer, meltingLoc("saplings"));
    MeltingRecipeBuilder.melting(
            ItemTags.LEAVES, new FluidStack(Fluids.WATER, 250), CrucibleType.WOOD)
        .build(consumer, meltingLoc("leaves"));
  }

  private ResourceLocation meltingLoc(String id) {
    return new ResourceLocation(
        ExNihiloSequentia.MOD_ID, "melting/" + RecipeProviderUtilities.prependRecipePrefix(id));
  }
}
