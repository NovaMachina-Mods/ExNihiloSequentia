package novamachina.exnihilosequentia.common.item.ore;

import java.awt.Color;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;

public class OreColor implements ItemColor {

    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        switch (tintIndex) {
            case 0:
                return Color.WHITE.getRGB();
            case 1:
                return getOreColor(stack);
            default:
                // oops! should never get here.
                return Color.BLACK.getRGB();
        }
    }

    private int getOreColor(ItemStack stack) {
        OreItem oreItem = (OreItem) stack.getItem();
        EnumOre ore = oreItem.getOre();
        return ore.getColor().toInt();
    }
}
