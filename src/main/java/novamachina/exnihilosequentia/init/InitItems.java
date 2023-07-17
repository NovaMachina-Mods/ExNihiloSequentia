package novamachina.exnihilosequentia.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;

public class InitItems {
  private InitItems() {}

  public static void init(IForgeRegistry<Item> registry) {
    for (ItemDefinition<?> definition : EXNItems.getDefinitions()) {
      registry.register(definition.getId(), definition.asItem());
    }
    for (BlockDefinition<?> definition : EXNBlocks.getDefinitions()) {
      registry.register(definition.getId(), definition.asItem());
    }
  }
}
