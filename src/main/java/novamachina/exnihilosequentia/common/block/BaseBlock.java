package novamachina.exnihilosequentia.common.block;

import javax.annotation.Nonnull;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

public class BaseBlock extends Block {

  public BaseBlock(@Nonnull final BlockBuilder builder) {
    super(builder.getProperties());
  }
}
