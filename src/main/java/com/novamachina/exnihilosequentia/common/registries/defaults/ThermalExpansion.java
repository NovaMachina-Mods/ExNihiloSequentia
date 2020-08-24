package com.novamachina.exnihilosequentia.common.registries.defaults;

import com.novamachina.exnihilosequentia.common.init.ModBlocks;
import com.novamachina.exnihilosequentia.common.init.ModItems;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.ore.EnumModdedOre;
import com.novamachina.exnihilosequentia.common.registries.sieve.SieveRegistry;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.block.Blocks;

public class ThermalExpansion implements IDefaultRegistry {

    @Override
    public void registerSieve(SieveRegistry registry) {
        if (!EnumModdedOre.ALUMINUM.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.SAND, ModItems.pieceMap.get(Constants.Ore.ALUMINUM)
                .get(), null, 0.05F, 0.075F, null, false);
            registry.addMultiMeshDrop(ModBlocks.CRUSHED_END_STONE.get(), ModItems.pieceMap.get(Constants.Ore.ALUMINUM)
                .get(), null, null, 0.15F, 0.25F, false);
            EnumModdedOre.ALUMINUM.setEnabled();
        }

        if (!EnumModdedOre.COPPER.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.COPPER)
                .get(), null, 0.05F, 0.075F, null, false);
            EnumModdedOre.COPPER.setEnabled();
        }

        if (!EnumModdedOre.SILVER.isEnabled()) {
            registry.addDrop(Blocks.SAND, ModItems.pieceMap.get(Constants.Ore.SILVER)
                .get(), 0.15F, EnumMesh.DIAMOND, false);
            registry.addMultiMeshDrop(ModBlocks.CRUSHED_END_STONE.get(), ModItems.pieceMap.get(Constants.Ore.SILVER)
                .get(), null, null, 0.15F, 0.25F, false);
        }

        if (!EnumModdedOre.TIN.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.SAND, ModItems.pieceMap.get(Constants.Ore.TIN)
                .get(), null, 0.05F, 0.075F, null, false);
            EnumModdedOre.TIN.setEnabled();
        }

        if (!EnumModdedOre.NICKEL.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.NICKEL)
                .get(), null, 0.05F, 0.075F, 0.15F, false);
            EnumModdedOre.NICKEL.setEnabled();
        }

        if (!EnumModdedOre.LEAD.isEnabled()) {
            registry.addMultiMeshDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.LEAD)
                .get(), null, 0.05F, 0.075F, 0.15F, false);
            EnumModdedOre.LEAD.setEnabled();
        }

        if (!EnumModdedOre.PLATINUM.isEnabled()) {
            registry.addDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.PLATINUM)
                .get(), 0.02F, EnumMesh.FLINT, false);
            registry.addDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.PLATINUM)
                .get(), 0.04F, EnumMesh.IRON, false);
            registry.addDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.PLATINUM)
                .get(), 0.12F, EnumMesh.DIAMOND, false);
            EnumModdedOre.PLATINUM.setEnabled();
        }
    }
}
