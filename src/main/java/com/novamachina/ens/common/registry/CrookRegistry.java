package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.registry.defaults.DefaultCrookRegistry;
import com.novamachina.ens.common.registry.registryitem.CrookRegistryItem;
import com.novamachina.ens.common.utility.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrookRegistry extends IRegistry<CrookRegistryItem> {

    private double vanillaDropChance               = 0.0;
    private int    numberOfTimesToTestVanillaDrops = 0;

    @Override
    public void register(CrookRegistryItem value) {
        registrySet.add(value);
    }

    @Override
    protected void useDefaultRegistry() {
        new DefaultCrookRegistry(this).init();
    }

    public List<ItemStack> getLeavesDrops(World worldIn, BlockState state, BlockPos pos) {
        List<ItemStack> drops = new ArrayList<>();
        for (int i = 0; i < numberOfTimesToTestVanillaDrops + 1; i++) {
            List<ItemStack> items = Block
                .getDrops(state, worldIn.getServer().getWorld(worldIn.getDimension().getType()),
                    pos, null);
            drops.addAll(items);
        }

        for (CrookRegistryItem item : this.registrySet) {
            Random random = new Random();
            if (random.nextDouble() <= item.getRarity()) {
                drops.add(new ItemStack(item.getItem()));
            }
        }

        return drops;
    }

    public void setVanillaDropChance(double vanillaDropChance) {
        this.vanillaDropChance = vanillaDropChance;
    }

    public void setNumberOfTimesToTestVanillaDrops(int numberOfTimesToTestVanillaDrops) {
        this.numberOfTimesToTestVanillaDrops = numberOfTimesToTestVanillaDrops;
    }
}