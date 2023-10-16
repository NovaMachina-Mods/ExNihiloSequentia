package novamachina.exnihilosequentia.core.registries;

import novamachina.exnihilosequentia.world.item.EXNCreativeModeTabs;
import novamachina.novacore.core.IRegistry;
import novamachina.novacore.world.item.CreativeModeTabDefinition;

public class InitCreativeTabs {
  public static void init(IRegistry<CreativeModeTabDefinition> registry) {
    for (CreativeModeTabDefinition definition : EXNCreativeModeTabs.getDefinitions()) {
      registry.register(definition);
    }
  }
}
