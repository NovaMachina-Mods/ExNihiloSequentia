package novamachina.exnihilosequentia.data.loot;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import novamachina.exnihilosequentia.data.loot.table.EXNBlockLootTable;
import novamachina.novacore.data.loot.LootProvider;

public class EXNLootProvider extends LootProvider {
  public EXNLootProvider(CompletableFuture<HolderLookup.Provider> lookupProvider, PackOutput output) {
    super(
        output, List.of(new SubProviderEntry(EXNBlockLootTable::new, LootContextParamSets.BLOCK)), lookupProvider);
  }
}
