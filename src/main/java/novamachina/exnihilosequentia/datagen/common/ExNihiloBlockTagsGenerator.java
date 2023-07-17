package novamachina.exnihilosequentia.datagen.common;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
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
        .add(EXNBlocks.STONE_BARREL.block(), EXNBlocks.FIRED_CRUCIBLE.block());
    tag(ExNihiloTags.MINEABLE_WITH_CROOK)
        .addTag(BlockTags.LEAVES)
        .add(EXNBlocks.INFESTED_LEAVES.block(), EXNBlocks.INFESTING_LEAVES.block());
    tag(BlockTags.MINEABLE_WITH_AXE)
        .add(
            EXNBlocks.ACACIA_BARREL.block(),
            EXNBlocks.BIRCH_BARREL.block(),
            EXNBlocks.CRIMSON_BARREL.block(),
            EXNBlocks.DARK_OAK_BARREL.block(),
            EXNBlocks.JUNGLE_BARREL.block(),
            EXNBlocks.MANGROVE_BARREL.block(),
            EXNBlocks.OAK_BARREL.block(),
            EXNBlocks.SPRUCE_BARREL.block(),
            EXNBlocks.WARPED_BARREL.block(),
            EXNBlocks.ACACIA_CRUCIBLE.block(),
            EXNBlocks.BIRCH_CRUCIBLE.block(),
            EXNBlocks.CRIMSON_CRUCIBLE.block(),
            EXNBlocks.DARK_OAK_CRUCIBLE.block(),
            EXNBlocks.JUNGLE_CRUCIBLE.block(),
            EXNBlocks.MANGROVE_CRUCIBLE.block(),
            EXNBlocks.OAK_CRUCIBLE.block(),
            EXNBlocks.SPRUCE_CRUCIBLE.block(),
            EXNBlocks.WARPED_CRUCIBLE.block(),
            EXNBlocks.ACACIA_SIEVE.block(),
            EXNBlocks.BIRCH_SIEVE.block(),
            EXNBlocks.CRIMSON_SIEVE.block(),
            EXNBlocks.DARK_OAK_SIEVE.block(),
            EXNBlocks.JUNGLE_SIEVE.block(),
            EXNBlocks.MANGROVE_SIEVE.block(),
            EXNBlocks.OAK_SIEVE.block(),
            EXNBlocks.SPRUCE_SIEVE.block(),
            EXNBlocks.WARPED_SIEVE.block());
    tag(BlockTags.MINEABLE_WITH_SHOVEL)
        .add(
            EXNBlocks.CRUSHED_ANDESITE.block(),
            EXNBlocks.CRUSHED_BASALT.block(),
            EXNBlocks.CRUSHED_BLACKSTONE.block(),
            EXNBlocks.CRUSHED_CALCITE.block(),
            EXNBlocks.CRUSHED_DEEPSLATE.block(),
            EXNBlocks.CRUSHED_DIORITE.block(),
            EXNBlocks.CRUSHED_DRIPSTONE.block(),
            EXNBlocks.CRUSHED_END_STONE.block(),
            EXNBlocks.CRUSHED_GRANITE.block(),
            EXNBlocks.CRUSHED_NETHERRACK.block(),
            EXNBlocks.CRUSHED_TUFF.block(),
            EXNBlocks.DUST.block(),
            EXNBlocks.UNFIRED_CRUCIBLE.block());
  }

  @Nonnull
  @Override
  public String getName() {
    return "Block Tags: " + modId;
  }
}
