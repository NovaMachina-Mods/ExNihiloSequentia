package novamachina.exnihilosequentia.data.recipes.providers;

import javax.annotation.Nonnull;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.data.recipes.CrushingRecipeBuilder;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.data.recipes.ISubRecipeProvider;

public class CrushingRecipes implements ISubRecipeProvider {
  @Override
  public void addRecipes(RecipeOutput consumer) {
    CrushingRecipeBuilder.crushing(Blocks.STONE)
        .addDrop(new ItemStack(Blocks.COBBLESTONE))
        .build(consumer, crushingLoc("cobblestone"));
    CrushingRecipeBuilder.crushing(Blocks.COBBLESTONE)
        .addDrop(new ItemStack(Blocks.GRAVEL))
        .build(consumer, crushingLoc("gravel"));
    CrushingRecipeBuilder.crushing(Blocks.GRAVEL)
        .addDrop(new ItemStack(Blocks.SAND))
        .build(consumer, crushingLoc("sand"));
    CrushingRecipeBuilder.crushing(Blocks.SAND)
        .addDrop(new ItemStack(EXNBlocks.DUST.block()))
        .build(consumer, crushingLoc("dust"));
    CrushingRecipeBuilder.crushing(Blocks.ANDESITE)
        .addDrop(new ItemStack(EXNBlocks.CRUSHED_ANDESITE.block()))
        .build(consumer, crushingLoc("andesite"));
    CrushingRecipeBuilder.crushing(Blocks.BASALT)
        .addDrop(new ItemStack(EXNBlocks.CRUSHED_BASALT.block()))
        .build(consumer, crushingLoc("basalt"));
    CrushingRecipeBuilder.crushing(Blocks.BLACKSTONE)
        .addDrop(new ItemStack(EXNBlocks.CRUSHED_BLACKSTONE.block()))
        .build(consumer, crushingLoc("blackstone"));
    CrushingRecipeBuilder.crushing(Blocks.CALCITE)
        .addDrop(new ItemStack(EXNBlocks.CRUSHED_CALCITE.block()))
        .build(consumer, crushingLoc("calcite"));
    CrushingRecipeBuilder.crushing(Blocks.DEEPSLATE)
        .addDrop(new ItemStack(EXNBlocks.CRUSHED_DEEPSLATE.block()))
        .build(consumer, crushingLoc("deepslate"));
    CrushingRecipeBuilder.crushing(Blocks.DIORITE)
        .addDrop(new ItemStack(EXNBlocks.CRUSHED_DIORITE.block()))
        .build(consumer, crushingLoc("diorite"));
    CrushingRecipeBuilder.crushing(Blocks.DRIPSTONE_BLOCK)
        .addDrop(new ItemStack(EXNBlocks.CRUSHED_DRIPSTONE.block()))
        .build(consumer, crushingLoc("dripstone"));
    CrushingRecipeBuilder.crushing(Blocks.END_STONE)
        .addDrop(new ItemStack(EXNBlocks.CRUSHED_END_STONE.block()))
        .build(consumer, crushingLoc("end_stone"));
    CrushingRecipeBuilder.crushing(Blocks.GRANITE)
        .addDrop(new ItemStack(EXNBlocks.CRUSHED_GRANITE.block()))
        .build(consumer, crushingLoc("granite"));
    CrushingRecipeBuilder.crushing(Blocks.NETHERRACK)
        .addDrop(new ItemStack(EXNBlocks.CRUSHED_NETHERRACK.block()))
        .build(consumer, crushingLoc("netherrack"));
    CrushingRecipeBuilder.crushing(Blocks.TUFF)
        .addDrop(new ItemStack(EXNBlocks.CRUSHED_TUFF.block()))
        .build(consumer, crushingLoc("tuff"));
    CrushingRecipeBuilder.crushing(Blocks.TUBE_CORAL_BLOCK)
        .addDrop(new ItemStack(Blocks.TUBE_CORAL))
        .build(consumer, crushingLoc("tube_coral"));
    CrushingRecipeBuilder.crushing(Blocks.BRAIN_CORAL_BLOCK)
        .addDrop(new ItemStack(Blocks.BRAIN_CORAL))
        .build(consumer, crushingLoc("brain_coral"));
    CrushingRecipeBuilder.crushing(Blocks.BUBBLE_CORAL_BLOCK)
        .addDrop(new ItemStack(Blocks.BUBBLE_CORAL))
        .build(consumer, crushingLoc("bubble_coral"));
    CrushingRecipeBuilder.crushing(Blocks.FIRE_CORAL_BLOCK)
        .addDrop(new ItemStack(Blocks.FIRE_CORAL))
        .build(consumer, crushingLoc("fire_coral"));
    CrushingRecipeBuilder.crushing(Blocks.HORN_CORAL_BLOCK)
        .addDrop(new ItemStack(Blocks.HORN_CORAL))
        .build(consumer, crushingLoc("horn_coral"));
    CrushingRecipeBuilder.crushing(Blocks.TUBE_CORAL)
        .addDrop(new ItemStack(Blocks.TUBE_CORAL_FAN))
        .build(consumer, crushingLoc("tube_coral_fan"));
    CrushingRecipeBuilder.crushing(Blocks.BRAIN_CORAL)
        .addDrop(new ItemStack(Blocks.BRAIN_CORAL_FAN))
        .build(consumer, crushingLoc("brain_coral_fan"));
    CrushingRecipeBuilder.crushing(Blocks.BUBBLE_CORAL)
        .addDrop(new ItemStack(Blocks.BUBBLE_CORAL_FAN))
        .build(consumer, crushingLoc("bubble_coral_fan"));
    CrushingRecipeBuilder.crushing(Blocks.FIRE_CORAL)
        .addDrop(new ItemStack(Blocks.FIRE_CORAL_FAN))
        .build(consumer, crushingLoc("fire_coral_fan"));
    CrushingRecipeBuilder.crushing(Blocks.HORN_CORAL)
        .addDrop(new ItemStack(Blocks.HORN_CORAL_FAN))
        .build(consumer, crushingLoc("horn_coral_fan"));
  }

  private ResourceLocation crushingLoc(@Nonnull final String id) {
    return new ResourceLocation(
        ExNihiloSequentia.MOD_ID, "crushing/" + RecipeProviderUtilities.prependRecipePrefix(id));
  }
}
