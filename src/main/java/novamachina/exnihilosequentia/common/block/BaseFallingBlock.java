package novamachina.exnihilosequentia.common.block;

import javax.annotation.Nonnull;
import net.minecraft.world.level.block.FallingBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class BaseFallingBlock extends FallingBlock {

  public BaseFallingBlock(@Nonnull final BlockBuilder builder) {
    super(builder.getProperties());
  }
}
