package novamachina.exnihilosequentia.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class OreItem extends Item {

  private final Ore ore;

  public OreItem(Ore ore, Item.Properties properties) {
    super(properties);
    this.ore = ore;
  }

  public Ore getOre() {
    return ore;
  }

  public static class PieceOreItem extends OreItem {

    public PieceOreItem(Ore ore, Item.Properties properties) {
      super(ore, properties);
    }
  }

  public static class RawOreItem extends OreItem {

    public RawOreItem(Ore ore, Item.Properties properties) {
      super(ore, properties);
    }
  }

  public static class IngotOreItem extends OreItem {

    public IngotOreItem(Ore ore, Item.Properties properties) {
      super(ore, properties);
    }
  }

  public static class NuggetOreItem extends OreItem {

    public NuggetOreItem(Ore ore, Item.Properties properties) {
      super(ore, properties);
    }
  }

  @FunctionalInterface
  public interface OreItemFunction<T> {
    T apply(Ore ore, Item.Properties properties);
  }
}
