package com.novamachina.exnihilosequentia.common.registries.defaults;

import com.novamachina.exnihilosequentia.common.item.ore.EnumModdedOre;
import com.novamachina.exnihilosequentia.common.registries.sieve.SieveRegistry;
import net.minecraft.block.Blocks;

public class Create implements IDefaultRegistry {
    @Override
    public void registerSieve(SieveRegistry registry) {
        if (!EnumModdedOre.COPPER.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, EnumModdedOre.COPPER.getPieceItem()
                .get(), null, 0.05F, 0.075F, null, false);
            EnumModdedOre.COPPER.setEnabled();
        }

        if (!EnumModdedOre.ZINC.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, EnumModdedOre.ZINC.getPieceItem()
                .get(), null, 0.05F, 0.075F, null, false);
            EnumModdedOre.ZINC.setEnabled();
        }
    }
}
