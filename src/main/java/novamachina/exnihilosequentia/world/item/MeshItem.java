package novamachina.exnihilosequentia.world.item;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MeshItem extends Item {

  private static final Map<MeshType, MeshItem> meshItemMap = new HashMap<>();
  private final MeshType type;

  public MeshItem(MeshType type, Item.Properties properties) {
    super(properties);
    this.type = type;
    meshItemMap.put(type, this);
  }

  public static MeshItem getMesh(MeshType meshType) {
    return meshItemMap.get(meshType);
  }

  // TODO
  //  @Override
  //  public boolean canApplyAtEnchantingTable(
  //      @Nonnull final ItemStack stack, @Nonnull final Enchantment enchantment) {
  //    return enchantment == Enchantments.EFFICIENCY
  //        || enchantment == Enchantments.FORTUNE;
  //  }

  public int getLevel() {
    return type.getLevel();
  }

  public MeshType getType() {
    return type;
  }

  @Override
  public boolean isBookEnchantable(@Nonnull final ItemStack stack, @Nonnull final ItemStack book) {
    return true;
  }

  @FunctionalInterface
  public interface MeshFunction {

    MeshItem apply(MeshType meshType, Item.Properties properties);
  }
}
