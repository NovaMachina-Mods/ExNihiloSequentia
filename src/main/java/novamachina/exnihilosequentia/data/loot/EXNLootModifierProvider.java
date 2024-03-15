package novamachina.exnihilosequentia.data.loot;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.loot.modifier.UseCrookModifier;
import novamachina.exnihilosequentia.common.loot.modifier.UseHammerModifier;
import novamachina.exnihilosequentia.tags.ExNihiloTags;

public class EXNLootModifierProvider extends GlobalLootModifierProvider {
  public EXNLootModifierProvider(PackOutput output) {
    super(output, ExNihiloSequentia.MOD_ID);
  }

  @Override
  protected void start() {
    add(
        "use_hammer",
        new UseHammerModifier(
            new LootItemCondition[] {
              MatchTool.toolMatches(ItemPredicate.Builder.item().of(ExNihiloTags.HAMMER)).build()
            }));
    add(
        "use_crook",
        new UseCrookModifier(
            new LootItemCondition[] {
              MatchTool.toolMatches(ItemPredicate.Builder.item().of(ExNihiloTags.CROOK)).build()
            }));
  }

  //  private void add(LootModifierDefinition<? extends LootModifier> definition) {
  //    this.add(definition.getId(), definition.getFactory().create(definition.getConditions()));
  //  }
}
