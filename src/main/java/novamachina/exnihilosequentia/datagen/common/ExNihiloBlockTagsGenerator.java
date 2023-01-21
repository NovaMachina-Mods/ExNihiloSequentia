package novamachina.exnihilosequentia.datagen.common;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.jetbrains.annotations.Nullable;

public class ExNihiloBlockTagsGenerator extends BlockTagsProvider {

  public ExNihiloBlockTagsGenerator(
      DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
    super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
  }

  @Override
  protected void addTags() {
    tag(ExNihiloTags.MINEABLE_WITH_HAMMER)
        .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
        .add(
            Blocks.GRAVEL,
            Blocks.SAND,
            Blocks.TUBE_CORAL_BLOCK,
            Blocks.BRAIN_CORAL_BLOCK,
            Blocks.BUBBLE_CORAL_BLOCK,
            Blocks.FIRE_CORAL_BLOCK,
            Blocks.HORN_CORAL_BLOCK,
            Blocks.TUBE_CORAL,
            Blocks.BRAIN_CORAL,
            Blocks.BUBBLE_CORAL,
            Blocks.FIRE_CORAL,
            Blocks.HORN_CORAL)
        .add(ExNihiloBlocks.BARREL_STONE.get(), ExNihiloBlocks.CRUCIBLE_FIRED.get());
    tag(ExNihiloTags.MINEABLE_WITH_CROOK)
        .addTag(BlockTags.LEAVES)
        .add(ExNihiloBlocks.INFESTED_LEAVES.get(), ExNihiloBlocks.INFESTING_LEAVES.get());
    tag(BlockTags.MINEABLE_WITH_AXE)
        .add(
            ExNihiloBlocks.BARREL_ACACIA.get(),
            ExNihiloBlocks.BARREL_BIRCH.get(),
            ExNihiloBlocks.BARREL_CRIMSON.get(),
            ExNihiloBlocks.BARREL_DARK_OAK.get(),
            ExNihiloBlocks.BARREL_JUNGLE.get(),
            ExNihiloBlocks.BARREL_OAK.get(),
            ExNihiloBlocks.BARREL_SPRUCE.get(),
            ExNihiloBlocks.BARREL_WARPED.get(),
            ExNihiloBlocks.CRUCIBLE_ACACIA.get(),
            ExNihiloBlocks.CRUCIBLE_BIRCH.get(),
            ExNihiloBlocks.CRUCIBLE_CRIMSON.get(),
            ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(),
            ExNihiloBlocks.CRUCIBLE_JUNGLE.get(),
            ExNihiloBlocks.CRUCIBLE_OAK.get(),
            ExNihiloBlocks.CRUCIBLE_SPRUCE.get(),
            ExNihiloBlocks.CRUCIBLE_WARPED.get(),
            ExNihiloBlocks.SIEVE_ACACIA.get(),
            ExNihiloBlocks.SIEVE_BIRCH.get(),
            ExNihiloBlocks.SIEVE_CRIMSON.get(),
            ExNihiloBlocks.SIEVE_DARK_OAK.get(),
            ExNihiloBlocks.SIEVE_JUNGLE.get(),
            ExNihiloBlocks.SIEVE_OAK.get(),
            ExNihiloBlocks.SIEVE_SPRUCE.get(),
            ExNihiloBlocks.SIEVE_WARPED.get());
    tag(BlockTags.MINEABLE_WITH_SHOVEL)
        .add(
            ExNihiloBlocks.CRUSHED_ANDESITE.get(),
            ExNihiloBlocks.CRUSHED_DIORITE.get(),
            ExNihiloBlocks.CRUSHED_END_STONE.get(),
            ExNihiloBlocks.CRUSHED_GRANITE.get(),
            ExNihiloBlocks.CRUSHED_NETHERRACK.get(),
            ExNihiloBlocks.DUST.get(),
            ExNihiloBlocks.CRUCIBLE_UNFIRED.get());
  }

  @Nonnull
  @Override
  public String getName() {
    return "Block Tags: " + modId;
  }
}
