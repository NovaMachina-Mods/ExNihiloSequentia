package novamachina.exnihilosequentia.data.loot;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.loot.modifier.UseCrookModifier;
import novamachina.exnihilosequentia.common.loot.modifier.UseHammerModifier;
import novamachina.exnihilosequentia.tags.ExNihiloTags;

import java.util.concurrent.CompletableFuture;

public class EXNLootModifierProvider extends GlobalLootModifierProvider {
  public EXNLootModifierProvider(CompletableFuture<HolderLookup.Provider> lookupProvider, PackOutput output) {
    super(output, lookupProvider, ExNihiloSequentia.MOD_ID);
  }

  @Override
  protected void start() {
    HolderGetter<Item> holderGetter = registries.lookupOrThrow(Registries.ITEM);
    add(
        "use_hammer",
        new UseHammerModifier(
            new LootItemCondition[] {
              MatchTool.toolMatches(ItemPredicate.Builder.item().of(holderGetter, ExNihiloTags.HAMMER)).build()
            }));
    add(
        "use_crook",
        new UseCrookModifier(
            new LootItemCondition[] {
              MatchTool.toolMatches(ItemPredicate.Builder.item().of(holderGetter, ExNihiloTags.CROOK)).build()
            }));
  }

  //  private void add(LootModifierDefinition<? extends LootModifier> definition) {
  //    this.add(definition.getId(), definition.getFactory().create(definition.getConditions()));
  //  }
}
