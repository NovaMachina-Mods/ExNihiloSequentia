package novamachina.exnihilosequentia.common.block;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrucibleBaseBlock extends BaseBlock implements ITOPInfoProvider {

    public CrucibleBaseBlock(BlockBuilder builder) {
        super(builder);
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo probeInfo, Player playerEntity, Level world, BlockState blockState, IProbeHitData data) {
        BaseCrucibleTile crucibleTile = (BaseCrucibleTile) world.getBlockEntity(data.getPos());
        assert crucibleTile != null;
        if (crucibleTile.getSolidAmount() > 0) {
            probeInfo.text(new TranslatableComponent("waila.crucible.solid", new TranslatableComponent(crucibleTile.getCurrentItem().getItem().getDescriptionId()), crucibleTile.getSolidAmount()));
        }
        if (crucibleTile.getFluidAmount() > 0) {
            probeInfo.text(new TranslatableComponent("waila.crucible.fluid", new TranslatableComponent(crucibleTile.getFluid().defaultFluidState().createLegacyBlock().getBlock().getDescriptionId()), crucibleTile.getFluidAmount()));
        }
        if (crucibleTile.getHeat() == 0) {
            probeInfo.text(new TranslatableComponent("waila.crucible.no_heat"));
        } else {
            probeInfo.text(new TranslatableComponent("waila.crucible.heat", crucibleTile.getHeat()));
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Nonnull
    @Override
    public InteractionResult use(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos,
                                 @Nonnull Player player, @Nonnull InteractionHand handIn, @Nonnull BlockHitResult hit) {
        if (worldIn.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        BaseCrucibleTile tile = (BaseCrucibleTile) worldIn.getBlockEntity(pos);

        if (tile != null) {
            IFluidHandler fluidHandler = tile
                    .getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getDirection())
                    .orElseThrow(() -> new RuntimeException("Missing Fluid Handler"));
            return tile.onBlockActivated(player, handIn, fluidHandler);
        }
        return InteractionResult.SUCCESS;
    }
}
