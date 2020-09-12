package com.novamachina.exnihilosequentia.common.registries.defaults;

import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.ore.EnumModdedOre;
import com.novamachina.exnihilosequentia.common.registries.sieve.SieveRegistry;
import net.minecraft.block.Blocks;

public class Mekanism implements IDefaultRegistry {
    @Override
    public void registerSieve(SieveRegistry registry) {
        if (!EnumModdedOre.TIN.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.SAND, EnumModdedOre.TIN.getPieceItem()
                .get(), null, 0.05F, 0.075F, null, false);
            EnumModdedOre.TIN.setEnabled();
        }

        if (!EnumModdedOre.COPPER.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, EnumModdedOre.COPPER.getPieceItem()
                .get(), null, 0.05F, 0.075F, null, false);
            EnumModdedOre.COPPER.setEnabled();
        }

        if (!EnumModdedOre.OSMIUM.isEnabled()) {
            registry.addDrop(Blocks.GRAVEL, EnumModdedOre.OSMIUM.getPieceItem().get(), 0.05F, EnumMesh.IRON, false);
            registry.addDrop(Blocks.GRAVEL, EnumModdedOre.OSMIUM.getPieceItem().get(), 0.1F, EnumMesh.DIAMOND, false);
            EnumModdedOre.OSMIUM.setEnabled();
        }
    }
}
