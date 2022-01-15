package novamachina.exnihilosequentia.common.block;

import java.util.List;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
                             @Nonnull final Player playerEntity, @Nonnull final Level world,
                             @Nonnull final BlockState blockState, @Nonnull final IProbeHitData data) {

        @Nullable final AbstractBarrelTile barrelTile = (AbstractBarrelTile) world.getBlockEntity(data.getPos());
        if (barrelTile == null) {
            return;
        }
        if (probeMode == ProbeMode.EXTENDED) {
            probeInfo
                    .text(new TranslatableComponent("top.barrel.mode",
                            barrelTile.getMode().getModeName().toUpperCase()).withStyle(style -> {
                        style.withColor(TextColor.fromLegacyFormat(ChatFormatting.GREEN));
                        return style;
                    }));
        }

        @Nonnull final List<Component> info = barrelTile.getWailaInfo();
        for (Component tooltip : info) {
            probeInfo.text(tooltip);
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Nonnull
    @Deprecated
    @Override
    public VoxelShape getShape(@Nonnull final BlockState state, @Nonnull final BlockGetter worldIn,
                               @Nonnull final BlockPos pos, @Nonnull final CollisionContext context) {
        return SHAPE;
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

        return InteractionResult.SUCCESS;
    }
}
