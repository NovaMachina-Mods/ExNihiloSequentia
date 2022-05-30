package novamachina.exnihilosequentia.common.item.ore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.item.OreItem;
import novamachina.exnihilosequentia.common.network.HandshakeMessages;
import novamachina.exnihilosequentia.common.utility.Color;
import org.jetbrains.annotations.NotNull;

public class Ore {

  private static Map<String, Boolean> enabledMap = new HashMap<>();
  private static Map<String, Boolean> oreMap = new HashMap<>();
  @Nonnull private final String name;
  @Nullable private final RegistryObject<OreItem> pieceItem;
  private final Color color;
  @Nullable private RegistryObject<OreItem> rawOreItem;
  @Nullable private RegistryObject<Item> ingotItem;

  public Ore(
      @Nonnull String name,
      boolean enabled,
      boolean generateRaw,
      boolean generateIngot,
      Color color,
      DeferredRegister<Item> registry) {
    this.name = name;
    this.color = color;
    Ore.oreMap.put(name, generateIngot);
    Ore.enabledMap.put(name, enabled);
    if (generateRaw) {
      rawOreItem = registry.register(this.getRawOreName(), () -> new OreItem(this));
    }
    pieceItem = registry.register(this.getPieceName(), () -> new OreItem(this));
    if (generateIngot) {
      ingotItem = registry.register(this.getIngotName(), () -> new OreItem(this));
    }
  }

  @OnlyIn(Dist.CLIENT)
  public static boolean updateEnabledOres(@Nonnull final HandshakeMessages.S2COreList message) {
    @Nullable final List<String> oreList = message.getOreList();
    Ore.enabledMap.replaceAll((k, v) -> false);
    if (oreList != null) {
      for (String ore : oreList) {
        Ore.enabledMap.put(ore, true);
      }
      return true;
    }
    return false;
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

  public static Map<String, Boolean> getOreMap() {
    return oreMap;
  }

  public Color getColor() {
    return color;
  }

  @Nullable
  public Item getIngotItem() {
    if (ingotItem == null) {
      return null;
    }
    return ingotItem.get();
  }

  public String getIngotName() {
    return name + "_ingot";
  }

  @Nonnull
  public String getOreName() {
    return name;
  }

  @Nullable
  public OreItem getPieceItem() {
    return pieceItem.get();
  }

  public String getPieceName() {
    return name + "_pieces";
  }

  @Nullable
  public OreItem getRawOreItem() {
    if (rawOreItem != null) {
      return rawOreItem.get();
    } else {
      return null;
    }
  }

  public String getRawOreName() {
    return "raw_" + name;
  }

  public boolean isEnabled() {
    return Ore.enabledMap.getOrDefault(name, false);
  }

  public void setEnabled(boolean enabled) {
    Ore.enabledMap.put(name, enabled);
  }
}
