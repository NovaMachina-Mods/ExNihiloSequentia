package novamachina.exnihilosequentia.world.level.block;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.compat.ITooltipProvider;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.MeshItem;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.level.block.entity.SieveBlockEntity;
import novamachina.novacore.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SieveBlock extends Block implements SimpleWaterloggedBlock, ITooltipProvider {

  private static Logger log = LoggerFactory.getLogger(SieveBlock.class);

  @Nonnull
  public static final EnumProperty<MeshType> MESH = EnumProperty.create("mesh", MeshType.class);

  @Nonnull public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

  public SieveBlock(Properties properties) {
    super(properties);
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
    @Nullable
    final SieveBlockEntity sieveBlockEntity = (SieveBlockEntity) worldIn.getBlockEntity(pos);

    if (sieveBlockEntity == null) {
      return;
    }
    log.debug("isReadyToSieve: " + sieveBlockEntity.isReadyToSieve());
    if (sieveBlockEntity.isReadyToSieve()) {
      sieveBlockEntity.activateSieve(worldIn, state.getValue(WATERLOGGED));
    }
    if (!sieveBlockEntity.isReadyToSieve() && stack.getItem() instanceof BlockItem blockItem) {
      log.debug(
          "Is Block Siftable: "
              + ExNihiloRegistries.SIEVE_REGISTRY.isBlockSiftable(
                  blockItem.getBlock(),
                  sieveBlockEntity.getMeshType(),
                  state.getValue(WATERLOGGED)));
      if (ExNihiloRegistries.SIEVE_REGISTRY.isBlockSiftable(
          blockItem.getBlock(), sieveBlockEntity.getMeshType(), state.getValue(WATERLOGGED))) {
        sieveBlockEntity.insertSiftableBlock(stack, player);
      }
    }
    worldIn.sendBlockUpdated(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
  }

  @Override
  public List<Component> getTooltipInfo(Level world, BlockPos pos) {
    List<Component> tooltip = new ArrayList<>();
    SieveBlockEntity sieveBlockEntity = (SieveBlockEntity) world.getBlockEntity(pos);
    if (sieveBlockEntity == null) {
      return tooltip;
    }

    if (!sieveBlockEntity.getBlockStack().isEmpty()) {
      tooltip.add(
          Component.translatable(
              "waila.sieve.block", sieveBlockEntity.getBlockStack().getDisplayName()));
    }
    if (sieveBlockEntity.getMeshType() != MeshType.NONE) {
      tooltip.add(
          Component.translatable(
              "waila.sieve.mesh",
              Component.translatable(
                  "item."
                      + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA
                      + "."
                      + sieveBlockEntity.getMeshType().getMeshName())));
    }
    return tooltip;
  }

  @Override
  public List<Component> getExpandedTooltipInfo(Level world, BlockPos pos) {
    List<Component> tooltip = new ArrayList<>();
    SieveBlockEntity sieveBlockEntity = (SieveBlockEntity) world.getBlockEntity(pos);
    if (sieveBlockEntity == null) {
      return tooltip;
    }

    if (!sieveBlockEntity.getBlockStack().isEmpty()) {
      tooltip.add(
          Component.translatable(
              "waila.progress", StringUtils.formatPercent(sieveBlockEntity.getProgress())));
    }
    tooltip.addAll(this.getTooltipInfo(world, pos));
    return tooltip;
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
              if (world.getBlockState(item).getBlock() instanceof SieveBlock) {
                nearbySieves.add(new BlockPos(item));
              }
            });

    return nearbySieves;
  }

  /**
   * @deprecated Ask Mojang
   */
  @OnlyIn(Dist.CLIENT)
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
    @Nonnull
    final FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
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
    if (!worldIn.isClientSide() && te instanceof SieveBlockEntity sieveBlockEntity) {
      sieveBlockEntity.removeMesh(false);
      if (!sieveBlockEntity.getBlockStack().isEmpty()) {
        worldIn.addFreshEntity(
            new ItemEntity(
                worldIn,
                pos.getX() + 0.5F,
                pos.getY() + 1.1F,
                pos.getZ() + 0.5F,
                sieveBlockEntity.getBlockStack()));
      }
    }
  }

  @Override
  public boolean propagatesSkylightDown(@Nonnull final BlockState state) {
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
      @Nullable
      final SieveBlockEntity sieveBlockEntity = (SieveBlockEntity) worldIn.getBlockEntity(pos);
      if (sieveBlockEntity != null) {
        sieveBlockEntity.setSieveState();
      }
    }
  }

  @Override
  @Nonnull
  public BlockState updateShape(
      BlockState stateIn,
      LevelReader worldIn,
      ScheduledTickAccess scheduledTickAccess,
      BlockPos currentPos,
      Direction facing,
      BlockPos facingPos,
      BlockState facingState,
      RandomSource randomSource) {
    if (stateIn.getValue(WATERLOGGED)) {
      scheduledTickAccess.scheduleTick(
          currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
    }
    return super.updateShape(
        stateIn,
        worldIn,
        scheduledTickAccess,
        currentPos,
        facing,
        facingPos,
        facingState,
        randomSource);
  }

  @Override
  protected InteractionResult useWithoutItem(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      Player player,
      BlockHitResult blockHitResult) {
    final SieveBlockEntity sieveBlockEntity = (SieveBlockEntity) level.getBlockEntity(blockPos);
    if (sieveBlockEntity == null) {
      return InteractionResult.PASS;
    }
    if (!level.isClientSide()) {
      log.debug("Sieve Activated without item");
      if (player.isShiftKeyDown()) {
        sieveBlockEntity.removeMesh(true);
      }
    }
    level.sendBlockUpdated(
        blockPos, level.getBlockState(blockPos), level.getBlockState(blockPos), 2);
    return InteractionResult.SUCCESS;
  }

  @Override
  protected InteractionResult useItemOn(
      ItemStack itemStack,
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      Player player,
      InteractionHand interactionHand,
      BlockHitResult blockHitResult) {
    SieveBlockEntity sieveBlockEntity = (SieveBlockEntity) level.getBlockEntity(blockPos);
    if (sieveBlockEntity == null) {
      return InteractionResult.PASS;
    }
    if (!level.isClientSide()) {
      log.debug("Sieve Activated with item");
      for (BlockPos sievePos : getNearbySieves(level, blockPos)) {
        @Nonnull final BlockState currentState = level.getBlockState(sievePos);
        activateBlock(currentState, level, player, sievePos, interactionHand);
      }

      if (itemStack.getItem() instanceof MeshItem) {
        sieveBlockEntity.insertMesh(itemStack, player);
      }
    }
    level.sendBlockUpdated(
        blockPos, level.getBlockState(blockPos), level.getBlockState(blockPos), 2);
    return InteractionResult.SUCCESS;
  }
}
