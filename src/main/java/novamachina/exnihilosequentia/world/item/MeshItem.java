package novamachina.exnihilosequentia.world.item;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import novamachina.exnihilosequentia.common.Config;

public class MeshItem extends Item {

  private static final Map<MeshType, MeshItem> meshItemMap = new HashMap<>();
  private final String name;
  private final MeshType type;

  public MeshItem(String name, int maxDamage, MeshType type) {
    super(
        Config.enableMeshDurability()
            ? new Properties().durability(maxDamage)
            : new Properties().stacksTo(Config.getMeshStackSize()));
    this.name = name;
    this.type = type;
    meshItemMap.put(type, this);
  }

  public static MeshItem getMesh(MeshType meshType) {
    return meshItemMap.get(meshType);
  }

  @Override
  public boolean canApplyAtEnchantingTable(
      @Nonnull final ItemStack stack, @Nonnull final Enchantment enchantment) {
    return enchantment == Enchantments.BLOCK_EFFICIENCY
        || enchantment == Enchantments.BLOCK_FORTUNE;
  }

  @Override
  public int getBurnTime(
      @Nonnull final ItemStack itemStack, @Nullable final RecipeType<?> recipeType) {
    if (((MeshItem) itemStack.getItem()).getType() == MeshType.STRING) {
      return 200;
    } else {
      return 0;
    }
  }

  public int getLevel() {
    return type.getLevel();
  }

  public String getName() {
    return name;
  }

  public MeshType getType() {
    return type;
  }

  @Override
  public boolean isBookEnchantable(@Nonnull final ItemStack stack, @Nonnull final ItemStack book) {
    return true;
  }
}
