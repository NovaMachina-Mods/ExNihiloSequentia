package novamachina.exnihilosequentia.core.registries;

import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.level.material.FluidDefinition;

public class InitFluidTypes {
  public static void init(IRegistry<FluidDefinition<?, ?, ?>> registry) {
    for (FluidDefinition<?, ?, ?> definition : EXNFluids.getDefinitions()) {
      registry.register(definition);
    }
  }
}
