package novamachina.exnihilosequentia.common.item.ore;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.awt.Color;

public class OreColor implements ItemColor {

    @Override
    public int getColor(@Nonnull ItemStack stack, int tintIndex) {
        return switch (tintIndex) {
            case 0 -> Color.WHITE.getRGB();
            case 1 -> getOreColor(stack);
            default ->
                    // oops! should never get here.
                    Color.BLACK.getRGB();
        };
    }

    private int getOreColor(ItemStack stack) {
        OreItem oreItem = (OreItem) stack.getItem();
        EnumOre ore = oreItem.getOre();
        return ore.getColor().toInt();
    }
}
