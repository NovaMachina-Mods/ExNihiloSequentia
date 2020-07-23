package com.novamachina.exnihilosequentia.common.item.tools.crook;

import com.novamachina.exnihilosequentia.ExNihiloSequentia;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants.Items;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrookDrops {

    public static final int numberOfTimesToTestVanillaDrops = Config.NUMBER_OF_TIMES_TO_TEST_VANILLA_DROPS
        .get();

    private static final List<CrookDropEntry> crookDrops  = new ArrayList<>();

    public static void initialize() {
        addDrop(ModItems.resourceMap.get(Items.SILKWORM).get(), 0.1F);
    }

    public static List<ItemStack> getDrops() {
        List<ItemStack> drops = new ArrayList<>();

        for (CrookDropEntry item : crookDrops) {
            Random random = new Random();
            if (random.nextDouble() <= item.getRarity()) {
                drops.add(new ItemStack(item.getItem()));
            }
        }

        return drops;
    }

    public static void addDrop(Item item, float rarity) {
        crookDrops.add(new CrookDropEntry(item, rarity));
    }
}