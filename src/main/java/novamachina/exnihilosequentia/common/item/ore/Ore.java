package novamachina.exnihilosequentia.common.item.ore;

import com.mojang.datafixers.util.Either;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.item.OreItem;
import novamachina.exnihilosequentia.common.network.HandshakeMessages;
import org.jetbrains.annotations.NotNull;

public class Ore {

  private static Map<String, Boolean> enabledMap = new HashMap<>();
  @Nonnull private final String name;
  @Nullable private Either<RegistryObject<OreItem>, Item> ingotItem;
  @Nullable private Either<RegistryObject<OreItem>, Item> rawOreItem;
  @Nullable private final RegistryObject<OreItem> pieceItem;
  @Nullable private Either<RegistryObject<OreItem>, Item> nuggetItem;

  public Ore(
      @Nonnull String name,
      boolean enabled,
      Optional<Item> optionalRawItem,
      Optional<Item> optionalIngotItem,
      Optional<Item> optionalNuggetItem,
      DeferredRegister<Item> registry) {
    this.name = name;
    Ore.enabledMap.put(name, enabled);
    if (optionalIngotItem.isEmpty()) {
      ingotItem = Either.left(registry.register(this.getIngotName(), () -> new OreItem(this)));
    } else {
      ingotItem = Either.right(optionalIngotItem.get());
    }
    if (optionalRawItem.isEmpty()) {
      rawOreItem = Either.left(registry.register(this.getRawOreName(), () -> new OreItem(this)));
    } else {
      rawOreItem = Either.right(optionalRawItem.get());
    }
    pieceItem = registry.register(this.getPieceName(), () -> new OreItem(this));
    if (optionalNuggetItem.isEmpty()) {
      nuggetItem = Either.left(registry.register(this.getNuggetName(), () -> new OreItem(this)));
    } else {
      nuggetItem = Either.right(optionalNuggetItem.get());
    }
  }

  private String getNuggetName() {
    return String.format("%s_nugget", this.name);
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

  @Nullable
  public Either<RegistryObject<OreItem>, Item> getIngotItem() {
    return ingotItem;
  }

  @Nullable
  public Either<RegistryObject<OreItem>, Item> getNuggetItem() {
    return nuggetItem;
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
  public Either<RegistryObject<OreItem>, Item> getRawOreItem() {
    return rawOreItem;
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
