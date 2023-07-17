package novamachina.exnihilosequentia.datagen.common;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;

public class ExNihiloFluidTagsGenerator extends FluidTagsProvider {

  public ExNihiloFluidTagsGenerator(
      @Nonnull final DataGenerator generator,
      @Nullable final ExistingFileHelper existingFileHelper) {
    super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
  }

  @Override
  protected void addTags() {
    tag(FluidTags.WATER)
        .add(EXNFluids.SEA_WATER.getFlowingFluid(), EXNFluids.SEA_WATER.getStillFluid());
  }
}
