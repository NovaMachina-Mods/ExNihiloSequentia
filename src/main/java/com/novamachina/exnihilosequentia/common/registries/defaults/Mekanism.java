package com.novamachina.exnihilosequentia.common.registries.defaults;

import com.novamachina.exnihilosequentia.common.init.ModItems;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.ore.EnumModdedOre;
import com.novamachina.exnihilosequentia.common.registries.sieve.SieveRegistry;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.block.Blocks;

public class Mekanism implements IDefaultRegistry {
    @Override
    public void registerSieve(SieveRegistry registry) {
        if (!EnumModdedOre.TIN.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.SAND, ModItems.pieceMap.get(Constants.Ore.TIN)
                .get(), null, 0.05F, 0.075F, null, false);
            EnumModdedOre.TIN.setEnabled();
        }

        if (!EnumModdedOre.COPPER.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.TIN)
                .get(), null, 0.05F, 0.075F, null, false);
            EnumModdedOre.COPPER.setEnabled();
        }

        if (!EnumModdedOre.LEAD.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.LEAD)
                .get(), null, 0.05F, 0.075F, null, false);
            EnumModdedOre.LEAD.setEnabled();
        }

        if (!EnumModdedOre.URANIUM.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.URANIUM)
                .get(), null, 0.05F, 0.075F, null, false);
            EnumModdedOre.URANIUM.setEnabled();
        }

        if (!EnumModdedOre.OSMIUM.isEnabled()) {
            registry
                .addDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.OSMIUM).get(), 0.05F, EnumMesh.IRON, false);
            registry.addDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.OSMIUM)
                .get(), 0.1F, EnumMesh.DIAMOND, false);
            EnumModdedOre.OSMIUM.setEnabled();
        }
    }
}
