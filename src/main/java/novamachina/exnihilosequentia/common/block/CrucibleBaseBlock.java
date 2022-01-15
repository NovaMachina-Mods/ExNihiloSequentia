package novamachina.exnihilosequentia.common.block;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrucibleBaseBlock extends BaseBlock implements ITOPInfoProvider {

    public CrucibleBaseBlock(@Nonnull final BlockBuilder builder) {
        super(builder);
    }

    @Override
    public void addProbeInfo(@Nonnull final ProbeMode probeMode, @Nonnull final IProbeInfo probeInfo,
                             @Nonnull final Player playerEntity, @Nonnull final Level world,
                             @Nonnull final BlockState blockState, final @Nonnull IProbeHitData data) {
        if (probeMode == ProbeMode.EXTENDED) {
            @Nullable final BaseCrucibleTile crucibleTile = (BaseCrucibleTile) world.getBlockEntity(data.getPos());
            if (crucibleTile == null)
                return;
            if (crucibleTile.getSolidAmount() > 0) {
                @Nullable final ItemStack itemStack = crucibleTile.getCurrentItem();
                if (itemStack != null)
                    probeInfo.text(new TranslatableComponent("waila.crucible.solid",
                            new TranslatableComponent(itemStack.getItem().getDescriptionId()),
                            crucibleTile.getSolidAmount()));
            }
            if (crucibleTile.getFluidAmount() > 0) {
                @Nullable final Fluid fluid = crucibleTile.getFluid();
                if (fluid != null)
                    probeInfo.text(new TranslatableComponent("waila.crucible.fluid",
                            new TranslatableComponent(
                                    fluid.defaultFluidState().createLegacyBlock().getBlock().getDescriptionId()),
                            crucibleTile.getFluidAmount()));
            }
            if (crucibleTile.getHeat() == 0) {
                probeInfo.text(new TranslatableComponent("waila.crucible.no_heat"));
            } else {
                probeInfo.text(new TranslatableComponent("waila.crucible.heat", crucibleTile.getHeat()));
            }
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Nonnull
    @Deprecated
    @Override
    public InteractionResult use(@Nonnull final BlockState state, @Nonnull final Level worldIn,
                                @Nonnull final BlockPos pos, @Nonnull final Player player,
                                @Nonnull final InteractionHand handIn, @Nonnull final BlockHitResult hit) {
        if (worldIn.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        @Nullable final BaseCrucibleTile tile = (BaseCrucibleTile) worldIn.getBlockEntity(pos);

        if (tile != null) {
            IFluidHandler fluidHandler = tile
                    .getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getDirection())
                    .orElseThrow(() -> new RuntimeException("Missing Fluid Handler"));
            return tile.onBlockActivated(player, handIn, fluidHandler);
        }
        return InteractionResult.SUCCESS;
    }
}
