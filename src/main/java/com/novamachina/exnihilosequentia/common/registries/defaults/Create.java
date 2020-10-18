package com.novamachina.exnihilosequentia.common.registries.defaults;

import com.novamachina.exnihilosequentia.common.item.ore.EnumOre;
import com.novamachina.exnihilosequentia.common.registries.sieve.SieveRegistry;
import net.minecraft.block.Blocks;

public class Create implements IDefaultRegistry {
    @Override
    public void registerSieve(SieveRegistry registry) {
        if (!EnumOre.COPPER.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, EnumOre.COPPER.getPieceItem()
                .get(), null, 0.05F, 0.075F, null, false);
            EnumOre.COPPER.setEnabled();
        }

        if (!EnumOre.ZINC.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, EnumOre.ZINC.getPieceItem()
                .get(), null, 0.05F, 0.075F, null, false);
            EnumOre.ZINC.setEnabled();
        }
    }
}
