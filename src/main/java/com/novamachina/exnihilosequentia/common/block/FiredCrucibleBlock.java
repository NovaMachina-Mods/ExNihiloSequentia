package com.novamachina.exnihilosequentia.common.block;

import com.novamachina.exnihilosequentia.common.builder.BlockBuilder;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class FiredCrucibleBlock extends BaseBlock {

    public FiredCrucibleBlock() {
        super(new BlockBuilder().properties(
            Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F)
                .sound(SoundType.STONE).notSolid()).harvestLevel(ToolType.PICKAXE, 0)
            .tileEntitySupplier(FiredCrucibleTile::new));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
        PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote()) {
            return ActionResultType.SUCCESS;
        }

        FiredCrucibleTile tile = (FiredCrucibleTile) worldIn.getTileEntity(pos);

        if (tile != null) {
            IFluidHandler fluidHandler = tile
                .getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getFace())
                .orElseThrow(() -> new RuntimeException("Missing Fluid Handler"));
            return tile.onBlockActivated(player, handIn, fluidHandler);
        }
        return ActionResultType.SUCCESS;
    }
}
