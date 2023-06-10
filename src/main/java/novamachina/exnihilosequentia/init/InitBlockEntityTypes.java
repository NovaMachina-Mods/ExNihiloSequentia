package novamachina.exnihilosequentia.init;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.IForgeRegistry;
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;
import novamachina.novacore.world.level.block.BlockEntityTypeDefinition;

public class InitBlockEntityTypes {
  private InitBlockEntityTypes() {}

  public static void init(IForgeRegistry<BlockEntityType<?>> registry) {
    for (BlockEntityTypeDefinition<? extends BlockEntity> definition :
        EXNBlockEntityTypes.getDefinitions()) {
      registry.register(definition.getId(), definition.getType());
    }
  }
}
