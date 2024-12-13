package novamachina.exnihilosequentia.data;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.item.EXNItems;

public class ExNihiloFurnaceFuleGenerator extends DataMapProvider {
  public ExNihiloFurnaceFuleGenerator(
      PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
    super(packOutput, lookupProvider);
  }

  @Override
  protected void gather(HolderLookup.Provider provider) {
    builder(NeoForgeDataMaps.FURNACE_FUELS)
        .add(ExNihiloTags.WOOD_BARREL, new FurnaceFuel(400), false)
        .add(ExNihiloTags.WOOD_CRUCIBLE, new FurnaceFuel(400), false)
        .add(ExNihiloTags.SIEVE, new FurnaceFuel(400), false)
        .add(ExNihiloTags.WOOD_CROOK, new FurnaceFuel(200), false)
        .add(ExNihiloTags.WOOD_HAMMER, new FurnaceFuel(200), false)
        .add(EXNItems.MESH_STRING.asItem().builtInRegistryHolder(), new FurnaceFuel(200), false)
        .build();
  }
}
