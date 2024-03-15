package novamachina.exnihilosequentia.world.item;

import com.mojang.datafixers.util.Either;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import novamachina.exnihilosequentia.common.network.payload.OreConfigurationPayload;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.novacore.core.registries.ItemRegistry;
import novamachina.novacore.util.StringUtils;
import novamachina.novacore.world.item.ItemDefinition;
import org.jetbrains.annotations.NotNull;

public class Ore {

  private static Map<String, Boolean> enabledMap = new HashMap<>();
  private final String name;
  private Either<ItemDefinition<OreItem>, Item> ingotItem;
  private Either<ItemDefinition<OreItem>, Item> rawOreItem;
  private final ItemDefinition<OreItem> pieceItem;
  private Either<ItemDefinition<OreItem>, Item> nuggetItem;

  public Ore(
      @Nonnull String name,
      boolean enabled,
      Optional<Item> optionalRawItem,
      Optional<Item> optionalIngotItem,
      Optional<Item> optionalNuggetItem,
      ItemRegistry registry) {
    this.name = name;
    Ore.enabledMap.put(name, enabled);
    if (optionalIngotItem.isEmpty()) {
      ItemDefinition<OreItem> definition =
          registry.item(
              this.getEnglishName(this.getIngotId()),
              this.getIngotId(),
              () -> new OreItem.IngotOreItem(this),
              ItemDefinition.ItemType.OTHER);
      ingotItem = Either.left(definition);
    } else {
      ingotItem = Either.right(optionalIngotItem.get());
    }
    if (optionalRawItem.isEmpty()) {
      ItemDefinition<OreItem> definition =
          registry.item(
              this.getEnglishName(this.getRawOreId()),
              this.getRawOreId(),
              () -> new OreItem.RawOreItem(this),
              ItemDefinition.ItemType.OTHER);
      rawOreItem = Either.left(definition);
    } else {
      rawOreItem = Either.right(optionalRawItem.get());
    }
    pieceItem =
        registry.item(
            this.getEnglishName(this.getPieceId()),
            this.getPieceId(),
            () -> new OreItem.PieceOreItem(this),
            ItemDefinition.ItemType.OTHER);
    if (optionalNuggetItem.isEmpty()) {
      ItemDefinition<OreItem> definition =
          registry.item(
              this.getEnglishName(this.getNuggetId()),
              this.getNuggetId(),
              () -> new OreItem.NuggetOreItem(this),
              ItemDefinition.ItemType.OTHER);
      nuggetItem = Either.left(definition);
    } else {
      nuggetItem = Either.right(optionalNuggetItem.get());
    }
  }

  private ResourceLocation id(String path) {
    return new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, path);
  }

  private String getEnglishName(String id) {
    return StringUtils.capitalize(id.replace("_", " "));
  }

  private String getNuggetId() {
    return String.format("%s_nugget", this.name);
  }

  @OnlyIn(Dist.CLIENT)
  public static void updateEnabledOres(OreConfigurationPayload message) {
    List<String> oreList = message.oreList();
    Ore.enabledMap.replaceAll((k, v) -> false);
    if (oreList != null) {
      for (String ore : oreList) {
        Ore.enabledMap.put(ore, true);
      }
    } else {
      throw new RuntimeException("Failed to synchronize ore list from server.");
    }
  }

  public static @NotNull List<String> getEnabledOres() {
    List<String> enabledOres = new ArrayList<>();
    for (String key : Ore.enabledMap.keySet()) {
      if (Boolean.TRUE.equals(Ore.enabledMap.get(key))) {
        enabledOres.add(key);
      }
    }
    return enabledOres;
  }

  public Either<ItemDefinition<OreItem>, Item> getIngotItem() {
    return ingotItem;
  }

  public Either<ItemDefinition<OreItem>, Item> getNuggetItem() {
    return nuggetItem;
  }

  public String getIngotId() {
    return name + "_ingot";
  }

  public String getOreName() {
    return name;
  }

  public OreItem getPieceItem() {
    return pieceItem.asItem();
  }

  public String getPieceId() {
    return name + "_pieces";
  }

  public Either<ItemDefinition<OreItem>, Item> getRawOreItem() {
    return rawOreItem;
  }

  public String getRawOreId() {
    return "raw_" + name;
  }

  public boolean isEnabled() {
    return Ore.enabledMap.getOrDefault(name, false);
  }

  public void setEnabled(boolean enabled) {
    Ore.enabledMap.put(name, enabled);
  }
}
