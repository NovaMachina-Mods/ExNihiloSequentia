package novamachina.exnihilosequentia.api.creation;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.ore.OreItem;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import java.util.EnumMap;
import java.util.Map;

public class AbstractItemCreation {
    public static final Map<EnumOre, RegistryObject<OreItem>> ingotMap = new EnumMap<>(EnumOre.class);
    public static void fillOreIngots() {
        for (EnumOre ore : EnumOre.values()) {
            if (ore.shouldGenerateIngot()) {
                ore.setIngotItem(ingotMap.get(ore).get());
            }
        }
    }
    public static Map<EnumOre, RegistryObject<OreItem>> getIngotMap() {
        return ingotMap;
    }

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);

    public static RegistryObject<Item> createBlockItem(String name, Block block) {
        return ITEMS.register(name, () -> new BlockItem(block, new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
    }
    public static RegistryObject<BucketItem> createBucketItem(String name, Fluid fluid) {
        return ITEMS.register(name, () -> new BucketItem(fluid.delegate, new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP).stacksTo(1)));
    }
    public static RegistryObject<Item> createFoodItem(String name, int nutrition, float saturation){
        return ITEMS.register(name, () -> new Item(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)
        .food(new Food.Builder().nutrition(nutrition).saturationMod(saturation).build())));
    }
}
