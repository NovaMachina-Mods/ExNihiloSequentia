package novamachina.exnihilosequentia.core.registries;

import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.level.block.BlockEntityTypeDefinition;

public class InitBlockEntityTypes {
  private InitBlockEntityTypes() {}

  public static void init(IRegistry<BlockEntityTypeDefinition<? extends BlockEntity>> registry) {
    for (BlockEntityTypeDefinition<? extends BlockEntity> definition :
        EXNBlockEntityTypes.getDefinitions()) {
      registry.register(definition);
    }
  }
}
