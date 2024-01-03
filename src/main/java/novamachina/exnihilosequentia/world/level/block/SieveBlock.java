package novamachina.exnihilosequentia.world.level.block;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
      sieveBlockEntity.activateSieve(player, state.getValue(WATERLOGGED));
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
              "waila.sieve.block",
              Component.translatable(sieveBlockEntity.getBlockStack().getDescriptionId())));
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
      @Nullable
      final SieveBlockEntity sieveBlockEntity = (SieveBlockEntity) worldIn.getBlockEntity(pos);
      if (sieveBlockEntity != null) {
        sieveBlockEntity.setSieveState();
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
    @Nullable
    final SieveBlockEntity sieveBlockEntity = (SieveBlockEntity) worldIn.getBlockEntity(pos);
    if (sieveBlockEntity == null) {
      return InteractionResult.PASS;
    }
    if (!worldIn.isClientSide()) {
      log.debug("Sieve Activated");
      @Nonnull final ItemStack stack = player.getItemInHand(handIn);
      if (player.isShiftKeyDown() && stack.isEmpty()) {
        sieveBlockEntity.removeMesh(true);
      }

      for (BlockPos sievePos : getNearbySieves(worldIn, pos)) {
        @Nonnull final BlockState currentState = worldIn.getBlockState(sievePos);
        activateBlock(currentState, worldIn, player, sievePos, handIn);
      }

      if (stack.getItem() instanceof MeshItem) {
        sieveBlockEntity.insertMesh(stack, player);
      }
    }
    worldIn.sendBlockUpdated(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
    return InteractionResult.SUCCESS;
  }
}
