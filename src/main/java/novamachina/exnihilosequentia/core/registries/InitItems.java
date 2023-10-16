package novamachina.exnihilosequentia.core.registries;

import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;

public class InitItems {
  private InitItems() {}

  public static void init(IRegistry<ItemDefinition<?>> registry) {
    for (ItemDefinition<?> definition : EXNItems.getDefinitions()) {
      registry.register(definition);
    }
    for (BlockDefinition<?> definition : EXNBlocks.getDefinitions()) {
      registry.register(definition);
    }
  }
}
