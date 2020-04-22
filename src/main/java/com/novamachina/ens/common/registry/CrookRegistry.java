package com.novamachina.ens.common.registry;

import com.novamachina.ens.ExNihiloSequentia;
import com.novamachina.ens.common.registry.registryitem.CrookRegistryItem;
import com.novamachina.ens.common.setup.Registration;
import com.novamachina.ens.common.utility.Config;
import com.novamachina.ens.common.utility.Constants;
import com.novamachina.ens.common.utility.Constants.Items;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrookRegistry extends IRegistry<CrookRegistryItem> {

    private final int numberOfTimesToTestVanillaDrops = Config.NUMBER_OF_TIMES_TO_TEST_VANILLA_DROPS
        .get();

    @Override
    protected void useJsonRegistry() {

    }

    @Override
    protected void useDefaultRegistry() {
        if (!ExNihiloSequentia.itemRegistrationFinished) {
            return;
        }
        register(Items.SILKWORM,
            new CrookRegistryItem(Registration.resourceMap.get(Items.SILKWORM).get(), 0.3));
    }

    public List<ItemStack> getLeavesDrops(World worldIn, BlockState state, BlockPos pos) {
        List<ItemStack> drops = new ArrayList<>();
        for (int i = 0; i < numberOfTimesToTestVanillaDrops + 1; i++) {
            List<ItemStack> items = Block
                .getDrops(state, worldIn.getServer().getWorld(worldIn.getDimension().getType()),
                    pos, null);
            drops.addAll(items);
        }

        for (CrookRegistryItem item : this.registry.values()) {
            Random random = new Random();
            if (random.nextDouble() <= item.getRarity()) {
                drops.add(new ItemStack(item.getItem()));
            }
        }

        return drops;
    }
}