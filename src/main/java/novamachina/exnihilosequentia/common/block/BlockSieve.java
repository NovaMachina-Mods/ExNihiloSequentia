package novamachina.exnihilosequentia.common.block;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockSieve extends BaseBlock implements IWaterLoggable, ITOPInfoProvider {
    @Nonnull public static final EnumProperty<EnumMesh> MESH = EnumProperty.create("mesh", EnumMesh.class);
    @Nonnull public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public BlockSieve(@Nonnull final BlockBuilder builder) {
        super(builder);
        this.registerDefaultState(this.stateDefinition.any().setValue(MESH, EnumMesh.NONE).setValue(WATERLOGGED, false));
    }

    public void activateBlock(@Nonnull final BlockState state,@Nonnull final  World worldIn,
                              @Nonnull final PlayerEntity player, @Nonnull final BlockPos pos,
                              @Nonnull final Hand handIn) {
        @Nonnull final ItemStack stack = player.getItemInHand(handIn);
        @Nullable final SieveTile sieveTile = (SieveTile) worldIn.getBlockEntity(pos);

        if (sieveTile == null)
            return;
        logger.debug("isReadyToSieve: " + sieveTile.isReadyToSieve());
        if (sieveTile.isReadyToSieve()) {
            sieveTile.activateSieve(player, state.getValue(WATERLOGGED));
        }
        if (!sieveTile.isReadyToSieve() && stack.getItem() instanceof BlockItem) {
            BlockItem blockItem = (BlockItem) stack.getItem();
            logger.debug("Is Block Siftable: " + ExNihiloRegistries.SIEVE_REGISTRY.isBlockSiftable(blockItem.getBlock(), sieveTile.getMesh(), state.getValue(WATERLOGGED)));
            if (ExNihiloRegistries.SIEVE_REGISTRY.isBlockSiftable(blockItem.getBlock(), sieveTile.getMesh(), state.getValue(WATERLOGGED))) {
                sieveTile.insertSiftableBlock(stack, player);
            }
        }
        worldIn.sendBlockUpdated(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
    }

    @Override
    public void addProbeInfo(@Nonnull final ProbeMode probeMode, @Nonnull final IProbeInfo iProbeInfo,
                             @Nonnull final PlayerEntity playerEntity, @Nonnull final World world,
                             @Nonnull final BlockState blockState, @Nonnull final IProbeHitData iProbeHitData) {
        @Nullable final SieveTile sieveTile = (SieveTile) world.getBlockEntity(iProbeHitData.getPos());
        if (sieveTile == null)
            return;
        if (!sieveTile.getBlockStack().isEmpty()) {
            if(probeMode == ProbeMode.EXTENDED) {
                iProbeInfo.text(new TranslationTextComponent("waila.progress", StringUtils
                        .formatPercent(sieveTile.getProgress())));
            }
            iProbeInfo.text(new TranslationTextComponent("waila.sieve.block", new TranslationTextComponent(sieveTile.getBlockStack().getDescriptionId())));
        }
        if (sieveTile.getMesh() != EnumMesh.NONE) {
            iProbeInfo.text(new TranslationTextComponent("waila.sieve.mesh", new TranslationTextComponent("item." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "." + sieveTile.getMesh().getMeshName())));
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Nonnull
    @Deprecated
    @Override
    public FluidState getFluidState(@Nonnull final BlockState state) {
        return Boolean.TRUE.equals(state.getValue(WATERLOGGED)) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    @Nonnull
    public BlockState getStateForPlacement(@Nonnull final BlockItemUseContext context) {
        @Nonnull final FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public void playerDestroy(@Nonnull final World worldIn, @Nonnull final PlayerEntity player,
                              @Nonnull final BlockPos pos, @Nonnull final BlockState state,
                              @Nullable final TileEntity te, @Nonnull final ItemStack stack) {
        super.playerDestroy(worldIn, player, pos, state, te, stack);
        if (!worldIn.isClientSide() && te instanceof SieveTile) {
            @Nonnull final SieveTile sieveTile = (SieveTile) te;
            sieveTile.removeMesh(false);
            if (!sieveTile.getBlockStack().isEmpty()) {
                worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5F, pos.getY() + 1.1F, pos
                        .getZ() + 0.5F, sieveTile.getBlockStack()));
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
        @Nullable final SieveTile sieveTile = (SieveTile) worldIn.getBlockEntity(pos);
        if (sieveTile == null) {
            return ActionResultType.PASS;
        }
        if (!worldIn.isClientSide()) {
            logger.debug("Sieve Activated");
            @Nonnull final ItemStack stack = player.getItemInHand(handIn);
            if (player.isShiftKeyDown() && stack.isEmpty()) {
                sieveTile.removeMesh(true);
            }

            for (BlockPos sievePos : getNearbySieves(worldIn, pos)) {
                @Nonnull final BlockState currentState = worldIn.getBlockState(sievePos);
                activateBlock(currentState, worldIn, player, sievePos, handIn);
            }

            if (stack.getItem() instanceof MeshItem) {
                sieveTile.insertMesh(stack, player);
            }
        }
        worldIn.sendBlockUpdated(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
        return ActionResultType.SUCCESS;
    }

    @Override
    public void setPlacedBy(@Nonnull final World worldIn, @Nonnull final BlockPos pos, @Nonnull final BlockState state,
                            @Nullable final LivingEntity placer, @Nonnull final ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isClientSide()) {
            @Nullable final SieveTile sieveTile = (SieveTile) worldIn.getBlockEntity(pos);
            if (sieveTile != null)
                sieveTile.setSieveState();
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Nonnull
    @Deprecated
    @Override
    public BlockState updateShape(@Nonnull final BlockState stateIn, @Nonnull final Direction facing,
                                  @Nonnull final BlockState facingState, @Nonnull final IWorld worldIn,
                                  @Nonnull final BlockPos currentPos, @Nonnull final BlockPos facingPos) {
        if (Boolean.TRUE.equals(stateIn.getValue(WATERLOGGED))) {
            worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }
        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull final Builder<Block, BlockState> builder) {
        builder.add(MESH, WATERLOGGED);
    }

    @Nonnull
    private List<BlockPos> getNearbySieves(@Nonnull final World world, @Nonnull final BlockPos pos) {
        @Nonnull final NonNullList<BlockPos> nearbySieves = NonNullList.create();

        BlockPos
                .betweenClosedStream(new BlockPos(pos.getX() - Config.getSieveRange(), pos.getY(), pos.getZ() - Config.getSieveRange()),
                        new BlockPos(pos.getX() + Config.getSieveRange(), pos.getY(), pos.getZ() + Config.getSieveRange()))
                .forEach(item -> {
                    if (world.getBlockState(item).getBlock() instanceof BlockSieve) {
                        nearbySieves.add(new BlockPos(item));
                    }
                });

        return nearbySieves;
    }

    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(@Nonnull final BlockState state, @Nonnull final IBlockReader worldIn,
                                    @Nonnull final BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(@Nonnull final BlockState state, @Nonnull final IBlockReader reader,
                                          @Nonnull final BlockPos pos) {
        return true;
    }
}