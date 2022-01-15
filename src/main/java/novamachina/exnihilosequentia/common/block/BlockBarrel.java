package novamachina.exnihilosequentia.common.block;

import java.util.List;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
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
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockBarrel extends BaseBlock implements ITOPInfoProvider {
    @Nonnull protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public BlockBarrel(@Nonnull final BlockBuilder builder) {
        super(builder);
    }

    @Override
    public void addProbeInfo(@Nonnull final ProbeMode probeMode, @Nonnull final IProbeInfo probeInfo,
                             @Nonnull final PlayerEntity playerEntity, @Nonnull final World world,
                             @Nonnull final BlockState blockState, @Nonnull final IProbeHitData data) {

        @Nullable final AbstractBarrelTile barrelTile = (AbstractBarrelTile) world.getBlockEntity(data.getPos());
        if (barrelTile == null) {
            return;
        }
        if (probeMode == ProbeMode.EXTENDED) {
            probeInfo
                    .text(new TranslationTextComponent("top.barrel.mode",
                            barrelTile.getMode().getModeName().toUpperCase()).withStyle(style -> {
                        style.withColor(Color.fromLegacyFormat(TextFormatting.GREEN));
                        return style;
                    }));
        }

        @Nonnull final List<ITextComponent> info = barrelTile.getWailaInfo();
        for (ITextComponent tooltip : info) {
            probeInfo.text(tooltip);
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Nonnull
    @Deprecated
    @Override
    public VoxelShape getShape(@Nonnull final BlockState state, @Nonnull final IBlockReader worldIn,
                               @Nonnull final BlockPos pos, @Nonnull final ISelectionContext context) {
        return SHAPE;
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

        @Nullable final AbstractBarrelTile tile = (AbstractBarrelTile) worldIn.getBlockEntity(pos);

        if (tile != null) {
            @Nonnull final IFluidHandler fluidHandler = tile
                    .getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getDirection())
                    .orElseThrow(() -> new RuntimeException("Missing Fluid Handler"));
            @Nonnull final IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
                            hit.getDirection())
                    .orElseThrow(() -> new RuntimeException("Missing Item Handler"));
            return tile.onBlockActivated(player, handIn, fluidHandler, itemHandler);
        }

        return ActionResultType.SUCCESS;
    }
}
