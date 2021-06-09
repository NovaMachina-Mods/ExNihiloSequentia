package novamachina.exnihilosequentia.common.block;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;

public class CrucibleBaseBlock extends BaseBlock implements ITOPInfoProvider {

    public CrucibleBaseBlock(BlockBuilder builder) {
        super(builder);
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo probeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData data) {
        if(probeMode == ProbeMode.EXTENDED) {
            BaseCrucibleTile crucibleTile = (BaseCrucibleTile) world.getBlockEntity(data.getPos());
            if (crucibleTile.getSolidAmount() > 0) {
                probeInfo.text(new TranslationTextComponent("waila.crucible.solid", new TranslationTextComponent(crucibleTile.getCurrentItem().getItem().getDescriptionId()), crucibleTile.getSolidAmount()));
            }
            if (crucibleTile.getFluidAmount() > 0) {
                probeInfo.text(new TranslationTextComponent("waila.crucible.fluid", new TranslationTextComponent(crucibleTile.getFluid().defaultFluidState().createLegacyBlock().getBlock().getDescriptionId()), crucibleTile.getFluidAmount()));
            }
            probeInfo.text(new TranslationTextComponent("waila.crucible.heat", crucibleTile.getHeat()));
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isClientSide()) {
            return ActionResultType.SUCCESS;
        }

        BaseCrucibleTile tile = (BaseCrucibleTile) worldIn.getBlockEntity(pos);

        if (tile != null) {
            IFluidHandler fluidHandler = tile
                    .getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getDirection())
                    .orElseThrow(() -> new RuntimeException("Missing Fluid Handler"));
            return tile.onBlockActivated(player, handIn, fluidHandler);
        }
        return ActionResultType.SUCCESS;
    }
}
