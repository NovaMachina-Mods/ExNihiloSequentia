package com.novamachina.exnihilosequentia.common.block;

import com.novamachina.exnihilosequentia.common.builder.BlockBuilder;
import com.novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class CrucibleBaseBlock extends BaseBlock implements ITOPInfoProvider {

    public CrucibleBaseBlock(BlockBuilder builder) {
        super(builder);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote()) {
            return ActionResultType.SUCCESS;
        }

        BaseCrucibleTile tile = (BaseCrucibleTile) worldIn.getTileEntity(pos);

        if (tile != null) {
            IFluidHandler fluidHandler = tile
                .getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getFace())
                .orElseThrow(() -> new RuntimeException("Missing Fluid Handler"));
            return tile.onBlockActivated(player, handIn, fluidHandler);
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo probeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData data) {
        BaseCrucibleTile crucibleTile = (BaseCrucibleTile) world.getTileEntity(data.getPos());
        if (crucibleTile.getSolidAmount() > 0) {
            String itemName = I18n.format(crucibleTile.getCurrentItem().getItem().getTranslationKey());
            probeInfo.text(new TranslationTextComponent("waila.crucible.solid", itemName, crucibleTile.getSolidAmount()));
        }
        if (crucibleTile.getFluidAmount() > 0) {
            String fluidName = I18n.format(crucibleTile.getFluid().getDefaultState().getBlockState().getBlock().getTranslationKey());
            probeInfo.text(new TranslationTextComponent("waila.crucible.fluid", fluidName, crucibleTile.getFluidAmount()));
        }
            probeInfo.text(new TranslationTextComponent("waila.crucible.heat", crucibleTile.getHeat()));
    }
}
