package com.novamachina.exnihilosequentia.common.block;

import com.novamachina.exnihilosequentia.common.builder.BlockBuilder;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.BarrelTile;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.swing.text.AttributeSet;
import java.util.List;

public class BlockBarrel extends BaseBlock implements IProbeInfoProvider {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public BlockBarrel(BlockBuilder builder) {
        super(builder);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote()) {
            return ActionResultType.SUCCESS;
        }

        BarrelTile tile = (BarrelTile) worldIn.getTileEntity(pos);

        if (tile != null) {
            IFluidHandler fluidHandler = tile
                .getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getFace())
                .orElseThrow(() -> new RuntimeException("Missing Fluid Handler"));
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, hit.getFace())
                .orElseThrow(() -> new RuntimeException("Missing Item Handler"));
            return tile.onBlockActivated(player, handIn, fluidHandler, itemHandler);
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public String getID() {
        return Constants.ModIds.EX_NIHILO_SEQUENTIA + ":barrel";
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo probeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData data) {
        BarrelTile barrelTile = (BarrelTile) world.getTileEntity(data.getPos());
        if(barrelTile == null) {
            return;
        }
        if(probeMode == ProbeMode.EXTENDED) {
            probeInfo.text(new TranslationTextComponent("top.barrel.mode", barrelTile.getMode().getModeName().toUpperCase()).setStyle(new Style().setColor(TextFormatting.GREEN)));
        }

        List<ITextComponent> info = barrelTile.getWailaInfo();
        for(ITextComponent tooltip : info) {
            probeInfo.text(tooltip);
        }
    }
}
