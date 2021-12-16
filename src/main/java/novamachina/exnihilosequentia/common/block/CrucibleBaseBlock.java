package novamachina.exnihilosequentia.common.block;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrucibleBaseBlock extends BaseBlock implements ITOPInfoProvider {

    public CrucibleBaseBlock(@Nonnull final BlockBuilder builder) {
        super(builder);
    }

    @Override
    public void addProbeInfo(@Nonnull final ProbeMode probeMode, @Nonnull final IProbeInfo probeInfo,
                             @Nonnull final PlayerEntity playerEntity, @Nonnull final World world,
                             @Nonnull final BlockState blockState, final @Nonnull IProbeHitData data) {
        if (probeMode == ProbeMode.EXTENDED) {
            @Nullable final BaseCrucibleTile crucibleTile = (BaseCrucibleTile) world.getBlockEntity(data.getPos());
            if (crucibleTile == null)
                return;
            if (crucibleTile.getSolidAmount() > 0) {
                @Nullable final ItemStack itemStack = crucibleTile.getCurrentItem();
                if (itemStack != null)
                    probeInfo.text(new TranslationTextComponent("waila.crucible.solid",
                            new TranslationTextComponent(itemStack.getItem().getDescriptionId()),
                            crucibleTile.getSolidAmount()));
            }
            if (crucibleTile.getFluidAmount() > 0) {
                @Nullable final Fluid fluid = crucibleTile.getFluid();
                if (fluid != null)
                    probeInfo.text(new TranslationTextComponent("waila.crucible.fluid",
                            new TranslationTextComponent(
                                    fluid.defaultFluidState().createLegacyBlock().getBlock().getDescriptionId()),
                            crucibleTile.getFluidAmount()));
            }
            if (crucibleTile.getHeat() == 0) {
                probeInfo.text(new TranslationTextComponent("waila.crucible.no_heat"));
            } else {
                probeInfo.text(new TranslationTextComponent("waila.crucible.heat", crucibleTile.getHeat()));
            }
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Nonnull
    @Deprecated
    @Override
    public ActionResultType use(@Nonnull final BlockState state, @Nonnull final World worldIn,
                                @Nonnull final BlockPos pos, @Nonnull final PlayerEntity player,
                                @Nonnull final Hand handIn, @Nonnull final BlockRayTraceResult hit) {
        if (worldIn.isClientSide()) {
            return ActionResultType.SUCCESS;
        }

        @Nullable final BaseCrucibleTile tile = (BaseCrucibleTile) worldIn.getBlockEntity(pos);

        if (tile != null) {
            IFluidHandler fluidHandler = tile
                    .getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getDirection())
                    .orElseThrow(() -> new RuntimeException("Missing Fluid Handler"));
            return tile.onBlockActivated(player, handIn, fluidHandler);
        }
        return ActionResultType.SUCCESS;
    }
}
