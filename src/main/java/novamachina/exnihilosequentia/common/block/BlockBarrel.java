package novamachina.exnihilosequentia.common.block;

//TODO activate TOP when available
//import mcjty.theoneprobe.api.IProbeHitData;
//import mcjty.theoneprobe.api.IProbeInfo;
//import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;

import java.util.List;

public class BlockBarrel extends BaseBlock /*implements ITOPInfoProvider*/ {
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public BlockBarrel(BlockBuilder builder) {
        super(builder);
    }

    /*@Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo probeInfo, Player playerEntity, Level world, BlockState blockState, IProbeHitData data) {

        AbstractBarrelTile barrelTile = (AbstractBarrelTile) world.getBlockEntity(data.getPos());
        if (barrelTile == null) {
            return;
        }
        if (probeMode == ProbeMode.EXTENDED) {
            probeInfo
                    .text(new TranslatableComponent("top.barrel.mode", barrelTile.getMode().getModeName().toUpperCase()).withStyle(style -> {
                        style.withColor(TextColor.fromLegacyFormat(ChatFormatting.GREEN));
                        return style;
                    }));
        }

        List<Component> info = barrelTile.getWailaInfo();
        for (Component tooltip : info) {
            probeInfo.text(tooltip);
        }
    }*/

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (worldIn.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        AbstractBarrelTile tile = (AbstractBarrelTile) worldIn.getBlockEntity(pos);

        if (tile != null) {
            IFluidHandler fluidHandler = tile
                    .getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, hit.getDirection())
                    .orElseThrow(() -> new RuntimeException("Missing Fluid Handler"));
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, hit.getDirection())
                    .orElseThrow(() -> new RuntimeException("Missing Item Handler"));
            return tile.onBlockActivated(player, handIn, fluidHandler, itemHandler);
        }

        return InteractionResult.SUCCESS;
    }
}
