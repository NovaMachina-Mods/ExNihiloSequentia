package novamachina.exnihilosequentia.common.loot.modifier;

import java.util.List;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.loot.LootModifier;
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
          new LootItemCondition[] {
            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ExNihiloTags.HAMMER)).build()
          },
          UseHammerModifier.CODEC.get(),
          UseHammerModifier::new);
  public static final LootModifierDefinition<UseCrookModifier> USE_CROOK =
      LOOT_MODIFIERS.create(
          "use_crook",
          new LootItemCondition[] {
            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ExNihiloTags.CROOK)).build()
          },
          UseCrookModifier.CODEC.get(),
          UseCrookModifier::new);

  public static List<LootModifierDefinition<? extends LootModifier>> getDefinitions() {
    return LOOT_MODIFIERS.getRegistry();
  }
}
