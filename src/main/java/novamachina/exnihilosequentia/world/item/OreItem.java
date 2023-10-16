package novamachina.exnihilosequentia.world.item;

import net.minecraft.world.item.Item;

public class OreItem extends Item {

  private final Ore ore;

  public OreItem(Ore ore) {
    super(new Item.Properties());
    this.ore = ore;
  }

  //  @Override
  //  protected boolean allowedIn(@Nonnull final CreativeModeTab group) {
  //    if (group == ExNihiloInitialization.ITEM_GROUP) {
  //      return ore.isEnabled();
  //    }
  //    return false;
  //  }

  public Ore getOre() {
    return ore;
  }

  public static class PieceOreItem extends OreItem {

    public PieceOreItem(Ore ore) {
      super(ore);
    }
  }

  public static class RawOreItem extends OreItem {

    public RawOreItem(Ore ore) {
      super(ore);
    }
  }

  public static class IngotOreItem extends OreItem {

    public IngotOreItem(Ore ore) {
      super(ore);
    }
  }

  public static class NuggetOreItem extends OreItem {

    public NuggetOreItem(Ore ore) {
      super(ore);
    }
  }
}
