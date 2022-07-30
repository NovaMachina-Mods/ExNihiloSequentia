package novamachina.exnihilotinkers.common;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilotinkers.common.init.EXNTinkersBlocks;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import org.jetbrains.annotations.Nullable;

//TODO: Add to generators
public class EXNTinkersBlockTags extends BlockTagsProvider {
  public EXNTinkersBlockTags(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
    super(p_126511_, EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS, existingFileHelper);
  }

  @Override
  protected void addTags() {
    tag(BlockTags.MINEABLE_WITH_AXE)
        .add(EXNTinkersBlocks.CRUCIBLE_BLOODSHROOM.get(),
            EXNTinkersBlocks.CRUCIBLE_GREENHEART.get(),
            EXNTinkersBlocks.CRUCIBLE_SKYROOT.get(),
            EXNTinkersBlocks.SIEVE_BLOODSHROOM.get(),
            EXNTinkersBlocks.SIEVE_GREENHEART.get(),
            EXNTinkersBlocks.SIEVE_SKYROOT.get(),
            EXNTinkersBlocks.BARREL_BLOODSHROOM.get(),
            EXNTinkersBlocks.BARREL_GREENHEART.get(),
            EXNTinkersBlocks.BARREL_SKYROOT.get());
  }
}