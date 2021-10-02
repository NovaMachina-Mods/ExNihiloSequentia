package novamachina.exnihilosequentia.api.utility;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class FindUtility {
    public static ItemStack getUnknownItem(String modid, String itemName) {
        ResourceLocation location = new ResourceLocation(modid, itemName);
        if (ForgeRegistries.ITEMS.containsKey(location)) {
            return new ItemStack(ForgeRegistries.ITEMS.getValue(location), 1);
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack getUnknownBlock(String modid, String blockName) {
        ResourceLocation location = new ResourceLocation(modid, blockName);
        if (ForgeRegistries.BLOCKS.containsKey(location)) {
            return new ItemStack(ForgeRegistries.BLOCKS.getValue(location), 1);
        }
        return ItemStack.EMPTY;
    }
}
