package novamachina.exnihilosequentia.world.item;

import javax.annotation.Nonnull;

import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.tags.ExNihiloTags;

public class HammerItem extends DiggerItem {

  public HammerItem(ToolMaterial tier, final float baseDamage, final float attackSpeed, Item.Properties properties) {
    super(
        tier,
        ExNihiloTags.MINEABLE_WITH_HAMMER,
        baseDamage,
        attackSpeed,
        properties);
  }

  @Override
  public boolean isCorrectToolForDrops(
      @Nonnull ItemStack itemStack, @Nonnull final BlockState blockIn) {
    if (ExNihiloRegistries.HAMMER_REGISTRY.isHammerable(blockIn.getBlock())) {
      return true;
    }
    return super.isCorrectToolForDrops(itemStack, blockIn);
  }

  @FunctionalInterface
  public interface HammerFunction {
    HammerItem apply(ToolMaterial tier, float baseDamage, float attackSpeed, Item.Properties properties);
  }
}
