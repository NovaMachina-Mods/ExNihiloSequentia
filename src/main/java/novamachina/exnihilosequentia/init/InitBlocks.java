package novamachina.exnihilosequentia.init;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.level.block.BlockDefinition;

public class InitBlocks {
  private InitBlocks() {}

  public static void init(IForgeRegistry<Block> registry) {
    for (BlockDefinition<?> definition : EXNBlocks.getDefinitions()) {
      registry.register(definition.getId(), definition.block());
    }
  }
}
