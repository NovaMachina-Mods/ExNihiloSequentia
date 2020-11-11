package novamachina.exnihilosequentia.common.item.ore;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

import java.awt.*;

public class OreColor implements IItemColor {

    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        {
            switch (tintIndex) {
                case 0:
                    return Color.WHITE.getRGB();
                case 1: {
                    OreItem oreItem = (OreItem) stack.getItem();
                    EnumOre ore = oreItem.getOre();
                    return ore.getColor().toInt();
                }
                default: {
                    // oops! should never get here.
                    return Color.BLACK.getRGB();
                }
            }
        }
    }
}
