package com.novamachina.exnihilosequentia.common.registries.defaults;

import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.ore.EnumModdedOre;
import com.novamachina.exnihilosequentia.common.registries.sieve.SieveRegistry;
import net.minecraft.block.Blocks;

public class ImmersiveEngineering implements IDefaultRegistry {
    @Override
    public void registerSieve(SieveRegistry registry) {
        if (!EnumModdedOre.ALUMINUM.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.SAND, EnumModdedOre.ALUMINUM.getPieceItem()
                .get(), null, 0.05F, 0.075F, null, false);
            registry.addMultiMeshDrop(ModBlocks.CRUSHED_END_STONE.get(), EnumModdedOre.ALUMINUM.getPieceItem()
                .get(), null, null, 0.15F, 0.25F, false);
            EnumModdedOre.ALUMINUM.setEnabled();
        }

        if (!EnumModdedOre.COPPER.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, EnumModdedOre.COPPER.getPieceItem()
                .get(), null, 0.05F, 0.075F, null, false);
            EnumModdedOre.COPPER.setEnabled();
        }

        if (!EnumModdedOre.SILVER.isEnabled()) {
            registry.addDrop(Blocks.SAND, EnumModdedOre.SILVER.getPieceItem()
                .get(), 0.15F, EnumMesh.DIAMOND, false);
            registry.addMultiMeshDrop(ModBlocks.CRUSHED_END_STONE.get(), EnumModdedOre.SILVER.getPieceItem()
                .get(), null, null, 0.15F, 0.25F, false);
        }

        if (!EnumModdedOre.NICKEL.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, EnumModdedOre.NICKEL.getPieceItem()
                .get(), null, 0.05F, 0.075F, 0.15F, false);
            EnumModdedOre.NICKEL.setEnabled();
        }

        if (!EnumModdedOre.LEAD.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, EnumModdedOre.LEAD.getPieceItem()
                .get(), null, 0.05F, 0.075F, 0.15F, false);
            EnumModdedOre.LEAD.setEnabled();
        }

        if (!EnumModdedOre.URANIUM.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, EnumModdedOre.URANIUM.getPieceItem()
                .get(), null, 0.05F, 0.075F, 0.15F, false);
            EnumModdedOre.URANIUM.setEnabled();
        }
    }
}
