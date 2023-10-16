package novamachina.exnihilosequentia.core.registries;

import net.minecraftforge.common.loot.LootModifier;
import novamachina.exnihilosequentia.common.loot.modifier.EXNLootModifiers;
import novamachina.novacore.common.loot.modifier.LootModifierDefinition;
import novamachina.novacore.core.IRegistry;

public class InitLootModifiers {
  private InitLootModifiers() {}

  public static void init(IRegistry<LootModifierDefinition<? extends LootModifier>> registry) {
    for (LootModifierDefinition<? extends LootModifier> definition :
        EXNLootModifiers.getDefinitions()) {
      registry.register(definition);
    }
  }
}
