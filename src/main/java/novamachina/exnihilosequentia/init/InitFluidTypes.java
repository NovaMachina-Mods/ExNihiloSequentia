package novamachina.exnihilosequentia.init;

import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.IForgeRegistry;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.novacore.world.level.material.FluidDefinition;

public class InitFluidTypes {
  public static void init(IForgeRegistry<FluidType> registry) {
    for (FluidDefinition<?, ?, ?> definition : EXNFluids.getDefinitions()) {
      registry.register(definition.getId(), definition.getFluidType());
    }
  }
}
