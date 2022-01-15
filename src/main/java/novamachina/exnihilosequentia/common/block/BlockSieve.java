package novamachina.exnihilosequentia.common.block;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockSieve extends BaseBlock implements SimpleWaterloggedBlock, ITOPInfoProvider, EntityBlock {
    public static final EnumProperty<EnumMesh> MESH = EnumProperty.create("mesh", EnumMesh.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public BlockSieve(BlockBuilder builder) {
        super(builder);
        this.registerDefaultState(this.stateDefinition.any().setValue(MESH, EnumMesh.NONE).setValue(WATERLOGGED, false));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new SieveTile(pos, state);
    }

    public void activateBlock(BlockState state, Level worldIn, Player player, BlockPos pos, InteractionHand handIn) {
        ItemStack stack = player.getItemInHand(handIn);
        SieveTile sieveTile = (SieveTile) worldIn.getBlockEntity(pos);

        assert sieveTile != null;
        logger.debug("isReadyToSieve: " + sieveTile.isReadyToSieve());
        if (sieveTile.isReadyToSieve()) {
            sieveTile.activateSieve(player, state.getValue(WATERLOGGED));
        }
        if (!sieveTile.isReadyToSieve() && stack.getItem() instanceof BlockItem blockItem) {
            logger.debug("Is Block Siftable: " + ExNihiloRegistries.SIEVE_REGISTRY.isBlockSiftable(blockItem.getBlock(), sieveTile.getMesh(), state.getValue(WATERLOGGED)));
            if (ExNihiloRegistries.SIEVE_REGISTRY.isBlockSiftable(blockItem.getBlock(), sieveTile.getMesh(), state.getValue(WATERLOGGED))) {
                sieveTile.insertSiftableBlock(stack, player);
            }
        }
        worldIn.sendBlockUpdated(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player playerEntity, Level world, BlockState blockState, IProbeHitData iProbeHitData) {
        SieveTile sieveTile = (SieveTile) world.getBlockEntity(iProbeHitData.getPos());

        assert sieveTile != null;
        if (!sieveTile.getBlockStack().isEmpty()) {
                iProbeInfo.text(new TranslatableComponent("waila.progress", StringUtils
                        .formatPercent(sieveTile.getProgress())));
            iProbeInfo.text(new TranslatableComponent("waila.sieve.block", new TranslatableComponent(sieveTile.getBlockStack().getDescriptionId())));
        }
        if (sieveTile.getMesh() != EnumMesh.NONE) {
            iProbeInfo.text(new TranslatableComponent("waila.sieve.mesh", new TranslatableComponent("item." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "." + sieveTile.getMesh().getMeshName())));
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return Boolean.TRUE.equals(state.getValue(WATERLOGGED)) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public void playerDestroy(@Nonnull Level worldIn, @Nonnull Player player, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable BlockEntity te, @Nonnull ItemStack stack) {
        super.playerDestroy(worldIn, player, pos, state, te, stack);
        if (!worldIn.isClientSide() && te instanceof SieveTile) {
            SieveTile sieveTile = (SieveTile) te;
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
    @Deprecated
    @Nonnull
    @Override
    public InteractionResult use(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand handIn, @Nonnull BlockHitResult hit) {
        SieveTile sieveTile = (SieveTile) worldIn.getBlockEntity(pos);
        if (sieveTile == null) {
            return InteractionResult.PASS;
        }
        if (!worldIn.isClientSide()) {
            logger.debug("Sieve Activated");
            ItemStack stack = player.getItemInHand(handIn);
            if (player.isShiftKeyDown() && stack.isEmpty()) {
                sieveTile.removeMesh(true);
            }

            for (BlockPos sievePos : getNearbySieves(worldIn, pos)) {
                BlockState currentState = worldIn.getBlockState(sievePos);
                activateBlock(currentState, worldIn, player, sievePos, handIn);
            }

            if (stack.getItem() instanceof MeshItem) {
                sieveTile.insertMesh(stack, player);
            }
        }
        worldIn.sendBlockUpdated(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void setPlacedBy(@Nonnull Level worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity placer, @Nonnull ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isClientSide()) {
            SieveTile sieveTile = (SieveTile) worldIn.getBlockEntity(pos);
            assert sieveTile != null;
            sieveTile.setSieveState();
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Nonnull
    @Override
    public BlockState updateShape(BlockState stateIn, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor worldIn, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        if (Boolean.TRUE.equals(stateIn.getValue(WATERLOGGED))) {
            worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }
        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(MESH, WATERLOGGED);
    }

    private List<BlockPos> getNearbySieves(Level world, BlockPos pos) {
        NonNullList<BlockPos> nearbySieves = NonNullList.create();

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
    public float getShadeBrightness(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
        return true;
    }
}