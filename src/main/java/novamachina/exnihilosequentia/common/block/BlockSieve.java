package novamachina.exnihilosequentia.common.block;

import com.mojang.logging.LogUtils;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
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
import novamachina.exnihilosequentia.common.blockentity.SieveEntity;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.item.MeshItem;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.common.utility.StringUtils;

public class BlockSieve extends BaseBlock
    implements SimpleWaterloggedBlock, ITOPInfoProvider {

  @Nonnull
  public static final EnumProperty<MeshType> MESH = EnumProperty.create("mesh", MeshType.class);

  @Nonnull
  public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  public BlockSieve(@Nonnull final BlockBuilder builder) {
    super(builder);
    this.registerDefaultState(
        this.stateDefinition.any().setValue(MESH, MeshType.NONE).setValue(WATERLOGGED, false));
  }

  public void activateBlock(
      @Nonnull final BlockState state,
      @Nonnull final Level worldIn,
      @Nonnull final Player player,
      @Nonnull final BlockPos pos,
      @Nonnull final InteractionHand handIn) {
    @Nonnull final ItemStack stack = player.getItemInHand(handIn);
    @Nullable final SieveEntity sieveEntity = (SieveEntity) worldIn.getBlockEntity(pos);

    if (sieveEntity == null) {
      return;
    }
    logger.debug("isReadyToSieve: " + sieveEntity.isReadyToSieve());
    if (sieveEntity.isReadyToSieve()) {
      sieveEntity.activateSieve(player, state.getValue(WATERLOGGED));
    }
    if (!sieveEntity.isReadyToSieve() && stack.getItem() instanceof BlockItem blockItem) {
      logger.debug(
          "Is Block Siftable: "
              + ExNihiloRegistries.SIEVE_REGISTRY.isBlockSiftable(
              blockItem.getBlock(), sieveEntity.getMeshType(), state.getValue(WATERLOGGED)));
      if (ExNihiloRegistries.SIEVE_REGISTRY.isBlockSiftable(
          blockItem.getBlock(), sieveEntity.getMeshType(), state.getValue(WATERLOGGED))) {
        sieveEntity.insertSiftableBlock(stack, player);
      }
    }
    worldIn.sendBlockUpdated(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
  }

  @Override
  public void addProbeInfo(
      @Nonnull final ProbeMode probeMode,
      @Nonnull final IProbeInfo iProbeInfo,
      @Nonnull final Player playerEntity,
      @Nonnull final Level world,
      @Nonnull final BlockState blockState,
      @Nonnull final IProbeHitData iProbeHitData) {
    @Nullable final SieveEntity sieveEntity = (SieveEntity) world.getBlockEntity(
        iProbeHitData.getPos());
    if (sieveEntity == null) {
      return;
    }
    if (!sieveEntity.getBlockStack().isEmpty()) {
      if (probeMode == ProbeMode.EXTENDED) {
        iProbeInfo.text(
            Component.translatable(
                "waila.progress", StringUtils.formatPercent(sieveEntity.getProgress())));
      }
      iProbeInfo.text(
          Component.translatable(
              "waila.sieve.block",
              Component.translatable(sieveEntity.getBlockStack().getDescriptionId())));
    }
    if (sieveEntity.getMeshType() != MeshType.NONE) {
      iProbeInfo.text(
          Component.translatable(
              "waila.sieve.mesh",
              Component.translatable(
                  "item."
                      + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA
                      + "."
                      + sieveEntity.getMeshType().getMeshName())));
    }
  }

  @Override
  protected void createBlockStateDefinition(@Nonnull final Builder<Block, BlockState> builder) {
    builder.add(MESH, WATERLOGGED);
  }

  /**
   * @deprecated Ask Mojang
   */
  @Nonnull
  @Deprecated(forRemoval = false)
  @Override
  public FluidState getFluidState(@Nonnull final BlockState state) {
    return Boolean.TRUE.equals(state.getValue(WATERLOGGED))
        ? Fluids.WATER.getSource(false)
        : super.getFluidState(state);
  }

  @Nonnull
  private List<BlockPos> getNearbySieves(@Nonnull final Level world, @Nonnull final BlockPos pos) {
    @Nonnull final NonNullList<BlockPos> nearbySieves = NonNullList.create();

    BlockPos.betweenClosedStream(
            new BlockPos(
                pos.getX() - Config.getSieveRange(),
                pos.getY(),
                pos.getZ() - Config.getSieveRange()),
            new BlockPos(
                pos.getX() + Config.getSieveRange(),
                pos.getY(),
                pos.getZ() + Config.getSieveRange()))
        .forEach(
            item -> {
              if (world.getBlockState(item).getBlock() instanceof BlockSieve) {
                nearbySieves.add(new BlockPos(item));
              }
            });

    return nearbySieves;
  }

  /**
   * @deprecated Ask Mojang
   */
  @OnlyIn(Dist.CLIENT)
  @Deprecated(forRemoval = false)
  @Override
  public float getShadeBrightness(
      @Nonnull final BlockState state,
      @Nonnull final BlockGetter worldIn,
      @Nonnull final BlockPos pos) {
    return 1.0F;
  }

  @Override
  @Nonnull
  public BlockState getStateForPlacement(@Nonnull final BlockPlaceContext context) {
    @Nonnull final FluidState fluidState = context.getLevel()
        .getFluidState(context.getClickedPos());
    return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
  }

  @Override
  public void playerDestroy(
      @Nonnull final Level worldIn,
      @Nonnull final Player player,
      @Nonnull final BlockPos pos,
      @Nonnull final BlockState state,
      @Nullable final BlockEntity te,
      @Nonnull final ItemStack stack) {
    super.playerDestroy(worldIn, player, pos, state, te, stack);
    if (!worldIn.isClientSide() && te instanceof SieveEntity sieveEntity) {
      sieveEntity.removeMesh(false);
      if (!sieveEntity.getBlockStack().isEmpty()) {
        worldIn.addFreshEntity(
            new ItemEntity(
                worldIn,
                pos.getX() + 0.5F,
                pos.getY() + 1.1F,
                pos.getZ() + 0.5F,
                sieveEntity.getBlockStack()));
      }
    }
  }

  @Override
  public boolean propagatesSkylightDown(
      @Nonnull final BlockState state,
      @Nonnull final BlockGetter reader,
      @Nonnull final BlockPos pos) {
    return true;
  }

  @Override
  public void setPlacedBy(
      @Nonnull final Level worldIn,
      @Nonnull final BlockPos pos,
      @Nonnull final BlockState state,
      @Nullable final LivingEntity placer,
      @Nonnull final ItemStack stack) {
    super.setPlacedBy(worldIn, pos, state, placer, stack);
    if (!worldIn.isClientSide()) {
      @Nullable final SieveEntity sieveEntity = (SieveEntity) worldIn.getBlockEntity(pos);
      if (sieveEntity != null) {
        sieveEntity.setSieveState();
      }
    }
  }

  /**
   * @deprecated Ask Mojang
   */
  @Nonnull
  @Deprecated(forRemoval = false)
  @Override
  public BlockState updateShape(
      @Nonnull final BlockState stateIn,
      @Nonnull final Direction facing,
      @Nonnull final BlockState facingState,
      @Nonnull final LevelAccessor worldIn,
      @Nonnull final BlockPos currentPos,
      @Nonnull final BlockPos facingPos) {
    if (Boolean.TRUE.equals(stateIn.getValue(WATERLOGGED))) {
      worldIn.getFluidTicks().hasScheduledTick(currentPos, Fluids.WATER);
    }
    return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
  }

  /**
   * @deprecated Ask Mojang
   */
  @Nonnull
  @Deprecated(forRemoval = false)
  @Override
  public InteractionResult use(
      @Nonnull final BlockState state,
      @Nonnull final Level worldIn,
      @Nonnull final BlockPos pos,
      @Nonnull final Player player,
      @Nonnull final InteractionHand handIn,
      @Nonnull final BlockHitResult hit) {
    @Nullable final SieveEntity sieveEntity = (SieveEntity) worldIn.getBlockEntity(pos);
    if (sieveEntity == null) {
      return InteractionResult.PASS;
    }
    if (!worldIn.isClientSide()) {
      logger.debug("Sieve Activated");
      @Nonnull final ItemStack stack = player.getItemInHand(handIn);
      if (player.isShiftKeyDown() && stack.isEmpty()) {
        sieveEntity.removeMesh(true);
      }

      for (BlockPos sievePos : getNearbySieves(worldIn, pos)) {
        @Nonnull final BlockState currentState = worldIn.getBlockState(sievePos);
        activateBlock(currentState, worldIn, player, sievePos, handIn);
      }

      if (stack.getItem() instanceof MeshItem) {
        sieveEntity.insertMesh(stack, player);
      }
    }
    worldIn.sendBlockUpdated(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
    return InteractionResult.SUCCESS;
  }
}
