package com.novamachina.ens.common.registry.defaults;

import com.novamachina.ens.common.registry.registryitem.HammerRegistry;
import com.novamachina.ens.common.registry.registryitem.HammerRegistryItem;
import com.novamachina.ens.common.setup.Registration;
import net.minecraft.block.Blocks;

public class DefaultHammerRegistry implements IDefaultRegistry {

    private final HammerRegistry hammerRegistry;

    public DefaultHammerRegistry(HammerRegistry hammerRegistry) {
        this.hammerRegistry = hammerRegistry;
    }

    @Override
    public void init() {
        hammerRegistry.register(new HammerRegistryItem(Blocks.STONE, Blocks.COBBLESTONE, 1.0));
        hammerRegistry.register(new HammerRegistryItem(Blocks.COBBLESTONE, Blocks.GRAVEL, 1.0));
        hammerRegistry.register(new HammerRegistryItem(Blocks.GRAVEL, Blocks.SAND, 1.0));
        hammerRegistry
            .register(new HammerRegistryItem(Blocks.SAND, Registration.BLOCK_DUST.get(), 1.0));
        hammerRegistry.register(
            new HammerRegistryItem(Blocks.ANDESITE, Registration.BLOCK_CRUSHED_ANDESITE.get(),
                1.0));
        hammerRegistry.register(
            new HammerRegistryItem(Blocks.DIORITE, Registration.BLOCK_CRUSHED_DIORITE.get(), 1.0));
        hammerRegistry.register(
            new HammerRegistryItem(Blocks.GRANITE, Registration.BLOCK_CRUSHED_GRANITE.get(), 1.0));
        hammerRegistry.register(
            new HammerRegistryItem(Blocks.NETHERRACK, Registration.BLOCK_CRUSHED_NETHERRACK.get(),
                1.0));
        hammerRegistry.register(
            new HammerRegistryItem(Blocks.END_STONE, Registration.BLOCK_CRUSHED_END_STONE.get(),
                1.0));

        hammerRegistry
            .registerNonSilkTouch(new HammerRegistryItem(Blocks.GRASS_BLOCK, Blocks.DIRT, 1.0));
        hammerRegistry.registerNonSilkTouch(new HammerRegistryItem(Blocks.GLASS, Blocks.AIR, 1.0));
        hammerRegistry
            .registerNonSilkTouch(new HammerRegistryItem(Blocks.MYCELIUM, Blocks.DIRT, 1.0));
    }
}
