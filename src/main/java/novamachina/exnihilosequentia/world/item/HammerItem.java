package novamachina.exnihilosequentia.world.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.tags.ExNihiloTags;

public class HammerItem extends DiggerItem {

  public HammerItem(@Nonnull final Tier tier, final int maxDamage) {
    super(
        0.5F,
        0.5F,
        tier,
        ExNihiloTags.MINEABLE_WITH_HAMMER,
        new Item.Properties().defaultDurability(maxDamage));
  }

  @Override
  public boolean isCorrectToolForDrops(
      @Nonnull ItemStack itemStack, @Nonnull final BlockState blockIn) {
    if (ExNihiloRegistries.HAMMER_REGISTRY.isHammerable(blockIn.getBlock())) {
      return true;
    }
    return super.isCorrectToolForDrops(itemStack, blockIn);
  }

  @Override
  public int getBurnTime(
      @Nonnull final ItemStack itemStack, @Nullable final RecipeType<?> recipeType) {
    if (itemStack.getItem() == EXNItems.HAMMER_WOOD.asItem()) {
      return 200;
    } else {
      return 0;
    }
  }
}
