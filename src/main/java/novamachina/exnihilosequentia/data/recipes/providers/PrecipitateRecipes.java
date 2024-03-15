package novamachina.exnihilosequentia.data.recipes.providers;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.data.recipes.PrecipitateRecipeBuilder;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.novacore.data.recipes.ISubRecipeProvider;

public class PrecipitateRecipes implements ISubRecipeProvider {

  private static final FluidStack lava = new FluidStack(Fluids.LAVA, FluidType.BUCKET_VOLUME);
  private static final FluidStack seawater =
      new FluidStack(EXNFluids.SEA_WATER.getStillFluid(), FluidType.BUCKET_VOLUME);
  private static final FluidStack water = new FluidStack(Fluids.WATER, FluidType.BUCKET_VOLUME);
  private static final FluidStack witchwater =
      new FluidStack(EXNFluids.WITCH_WATER.getStillFluid(), FluidType.BUCKET_VOLUME);

  @Override
  public void addRecipes(RecipeOutput consumer) {
    PrecipitateRecipeBuilder.precipitate(water, EXNBlocks.DUST, Blocks.CLAY)
        .build(consumer, precipitateLoc("clay"));
    PrecipitateRecipeBuilder.precipitate(water, Blocks.DIRT, Blocks.MUD)
        .build(consumer, precipitateLoc("mud"));
    PrecipitateRecipeBuilder.precipitate(lava, Tags.Items.DUSTS_REDSTONE, Blocks.NETHERRACK)
        .build(consumer, precipitateLoc("netherrack"));
    PrecipitateRecipeBuilder.precipitate(lava, Tags.Items.DUSTS_GLOWSTONE, Blocks.END_STONE)
        .build(consumer, precipitateLoc("end_stone"));
    PrecipitateRecipeBuilder.precipitate(witchwater, Tags.Items.SAND, Blocks.SOUL_SAND)
        .build(consumer, precipitateLoc("soul_sand"));
    PrecipitateRecipeBuilder.precipitate(witchwater, Items.COARSE_DIRT, Blocks.SOUL_SOIL)
        .build(consumer, precipitateLoc("soul_soil"));
    PrecipitateRecipeBuilder.precipitate(witchwater, Tags.Items.MUSHROOMS, Blocks.SLIME_BLOCK)
        .build(consumer, precipitateLoc("slime"));
    PrecipitateRecipeBuilder.precipitate(
            seawater, EXNItems.TUBE_CORAL_LARVA, Blocks.TUBE_CORAL_BLOCK)
        .build(consumer, precipitateLoc("tube_coral"));
    PrecipitateRecipeBuilder.precipitate(
            seawater, EXNItems.FIRE_CORAL_LARVA, Blocks.FIRE_CORAL_BLOCK)
        .build(consumer, precipitateLoc("fire_coral"));
    PrecipitateRecipeBuilder.precipitate(
            seawater, EXNItems.BRAIN_CORAL_LARVA, Blocks.BRAIN_CORAL_BLOCK)
        .build(consumer, precipitateLoc("brain_coral"));
    PrecipitateRecipeBuilder.precipitate(
            seawater, EXNItems.BUBBLE_CORAL_LARVA, Blocks.BUBBLE_CORAL_BLOCK)
        .build(consumer, precipitateLoc("bubble_coral"));
    PrecipitateRecipeBuilder.precipitate(
            seawater, EXNItems.HORN_CORAL_LARVA, Blocks.HORN_CORAL_BLOCK)
        .build(consumer, precipitateLoc("horn_coral"));
    PrecipitateRecipeBuilder.precipitate(
            witchwater, EXNItems.MYCELIUM_SPORE, Blocks.BROWN_MUSHROOM_BLOCK)
        .build(consumer, precipitateLoc("brown_mushroom"));
    PrecipitateRecipeBuilder.precipitate(
            witchwater, Blocks.BROWN_MUSHROOM_BLOCK, Blocks.RED_MUSHROOM_BLOCK)
        .build(consumer, precipitateLoc("red_mushroom"));
    PrecipitateRecipeBuilder.precipitate(
            water, EXNBlocks.CRUSHED_DRIPSTONE, Blocks.POINTED_DRIPSTONE)
        .build(consumer, precipitateLoc("pointed_dripstone"));
  }

  private ResourceLocation precipitateLoc(String id) {
    return new ResourceLocation(
        ExNihiloSequentia.MOD_ID, "precipitate/" + RecipeProviderUtilities.prependRecipePrefix(id));
  }
}
