package novamachina.exnihilosequentia.init;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.IForgeRegistry;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;
import novamachina.novacore.world.level.material.FluidDefinition;

public class InitFluids {
  public static void init(IForgeRegistry<Fluid> registry) {
    for (FluidDefinition<?, ?, ?> definition : EXNFluids.getDefinitions()) {
      registry.register(definition.getId(), definition.getStillFluid());
      registry.register(definition.getFlowingId(), definition.getFlowingFluid());
    }
  }
}
