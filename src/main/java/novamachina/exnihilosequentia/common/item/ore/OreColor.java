package novamachina.exnihilosequentia.common.item.ore;

import java.awt.Color;
import javax.annotation.Nonnull;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.common.item.OreItem;

public class OreColor implements ItemColor {

  @Override
  public int getColor(@Nonnull final ItemStack stack, final int tintIndex) {
    return switch (tintIndex) {
      case 0 -> Color.WHITE.getRGB();
      case 1 -> getOreColor(stack);
      default ->
          // oops! should never get here.
          Color.BLACK.getRGB();
    };
  }

  private int getOreColor(@Nonnull final ItemStack stack) {
    @Nonnull final OreItem oreItem = (OreItem) stack.getItem();
    @Nonnull final Ore ore = oreItem.getOre();
    return ore.getColor().toInt();
  }
}
