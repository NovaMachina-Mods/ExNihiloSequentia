package com.novamachina.exnihilosequentia.common.compat;

import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.ore.EnumModdedOre;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.tileentity.sieve.SieveDrops;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.block.Blocks;

public class ThermalExpansion implements IDefaultRegistry {
    public ThermalExpansion() {
        for(EnumModdedOre ore : EnumModdedOre.values()) {
            ore.setEnabled();
        }
    }

    @Override
    public void registerSieve(SieveDrops registry) {
        registry.addMultiMeshDrop(Blocks.SAND, ModItems.pieceMap.get(Constants.Ore.ALUMINUM).get(), null, 0.05F, 0.075F, null, false);
        registry.addMultiMeshDrop(ModBlocks.CRUSHED_END_STONE.get(), ModItems.pieceMap.get(Constants.Ore.ALUMINUM).get(), null, null, 0.15F, 0.25F, false);

        registry.addMultiMeshDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.COPPER).get(), null, 0.05F, 0.075F, null, false);

        registry.addDrop(Blocks.SAND, ModItems.pieceMap.get(Constants.Ore.SILVER).get(), 0.15F, EnumMesh.DIAMOND, false);
        registry.addMultiMeshDrop(ModBlocks.CRUSHED_END_STONE.get(), ModItems.pieceMap.get(Constants.Ore.SILVER).get(), null, null, 0.15F, 0.25F, false);

        registry.addMultiMeshDrop(Blocks.SAND, ModItems.pieceMap.get(Constants.Ore.TIN).get(), null, 0.05F, 0.075F, null, false);

        registry.addMultiMeshDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.NICKEL).get(), null, 0.05F, 0.075F, 0.15F, false);

        registry.addDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.PLATINUM).get(), 0.02F, EnumMesh.FLINT, false);
        registry.addDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.PLATINUM).get(), 0.04F, EnumMesh.IRON, false);
        registry.addDrop(Blocks.GRAVEL, ModItems.pieceMap.get(Constants.Ore.PLATINUM).get(), 0.12F, EnumMesh.DIAMOND, false);
    }
}
