package novamachina.exnihilosequentia.core.registries;

import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.level.block.BlockDefinition;

public class InitBlocks {

  public static void init(IRegistry<BlockDefinition<?>> registry) {
    for (BlockDefinition<?> definition : EXNBlocks.getDefinitions()) {
      registry.register(definition);
    }
  }
}
