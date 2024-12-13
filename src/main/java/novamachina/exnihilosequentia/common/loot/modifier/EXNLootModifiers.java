package novamachina.exnihilosequentia.common.loot.modifier;

import java.util.List;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.loot.LootModifier;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.novacore.common.loot.modifier.LootModifierDefinition;
import novamachina.novacore.core.registries.LootModifierRegistry;

public class EXNLootModifiers {
  public static LootModifierRegistry LOOT_MODIFIERS =
      new LootModifierRegistry(ExNihiloSequentia.MOD_ID);

  public static final LootModifierDefinition<UseHammerModifier> USE_HAMMER =
      LOOT_MODIFIERS.create(
          "use_hammer",
          UseHammerModifier.CODEC);
  public static final LootModifierDefinition<UseCrookModifier> USE_CROOK =
      LOOT_MODIFIERS.create(
          "use_crook",
          UseCrookModifier.CODEC);

  public static List<LootModifierDefinition<? extends LootModifier>> getDefinitions() {
    return LOOT_MODIFIERS.getRegistry();
  }
}
