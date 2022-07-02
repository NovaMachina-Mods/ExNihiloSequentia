package novamachina.exnihilotinkers.common;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilotinkers.common.init.EXNTinkersBlocks;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import org.jetbrains.annotations.Nullable;

public class EXNTinkersBlockTags extends BlockTagsProvider {
    public EXNTinkersBlockTags(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ExNihiloTags.CRUCIBLES)
            .add(EXNTinkersBlocks.CRUCIBLE_BLOODSHROOM.get(),
                EXNTinkersBlocks.CRUCIBLE_GREENHEART.get(),
                EXNTinkersBlocks.CRUCIBLE_SKYROOT.get());
        tag(ExNihiloTags.SIEVES)
            .add(EXNTinkersBlocks.SIEVE_BLOODSHROOM.get(),
                EXNTinkersBlocks.SIEVE_GREENHEART.get(),
                EXNTinkersBlocks.SIEVE_SKYROOT.get());
        tag(ExNihiloTags.BARRELS)
            .add(EXNTinkersBlocks.BARREL_BLOODSHROOM.get(),
                EXNTinkersBlocks.BARREL_GREENHEART.get(),
                EXNTinkersBlocks.BARREL_SKYROOT.get());
    }
}
