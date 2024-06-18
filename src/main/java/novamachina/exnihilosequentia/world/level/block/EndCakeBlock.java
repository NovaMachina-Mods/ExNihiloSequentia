package novamachina.exnihilosequentia.world.level.block;

import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.EndPlatformFeature;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class EndCakeBlock extends CakeBlock {

  public EndCakeBlock() {
    super(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.WOOL));
  }

  @Override
  protected ItemInteractionResult useItemOn(
      ItemStack itemStack,
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      Player player,
      InteractionHand interactionHand,
      BlockHitResult blockHitResult) {
    final int bites = blockState.getValue(BITES);

    if (itemStack.getItem() == Items.ENDER_EYE && bites > 0) {
      if (!level.isClientSide()) {
        level.setBlockAndUpdate(blockPos, blockState.setValue(BITES, bites - 1));
        itemStack.shrink(1);
      }
      return ItemInteractionResult.SUCCESS;
    }
    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
  }

  @Override
  protected InteractionResult useWithoutItem(
      BlockState blockState,
      Level level,
      BlockPos blockPos,
      Player player,
      BlockHitResult blockHitResult) {
    return eatCake(level, blockPos, blockState, player);
  }

  private InteractionResult eatCake(
      @Nonnull final Level worldIn,
      @Nonnull final BlockPos pos,
      @Nonnull final BlockState state,
      @Nonnull final Player player) {
    InteractionResult result = InteractionResult.SUCCESS;
    if (!worldIn.isClientSide()
        && player.getVehicle() == null
        && player.isCreative()
        && worldIn instanceof ServerLevel serverLevel
        && !player.isPassenger()) {
      result = teleportPlayer(worldIn, player, serverLevel);
      if (result == InteractionResult.FAIL) {
        return result;
      }
    }

    if (!player.canEat(true) || player.getCommandSenderWorld().dimension() == Level.END) {
      return InteractionResult.FAIL;
    } else {
      consumeCake(worldIn, pos, state, player);

      if (!worldIn.isClientSide()
          && player.getVehicle() == null
          && worldIn instanceof ServerLevel serverLevel
          && !player.isPassenger()) {
        result = teleportPlayer(worldIn, player, serverLevel);
      }
    }
    return result;
  }

  private void consumeCake(
      @NotNull Level worldIn,
      @NotNull BlockPos pos,
      @NotNull BlockState state,
      @NotNull Player player) {
    player.awardStat(Stats.EAT_CAKE_SLICE);
    player.getFoodData().eat(2, 0.1F);
    int i = state.getValue(BITES);

    if (i < 6) {
      worldIn.setBlockAndUpdate(pos, state.setValue(BITES, i + 1));
    } else {
      worldIn.removeBlock(pos, false);
    }
  }

  private InteractionResult teleportPlayer(Level worldIn, Player player, ServerLevel serverLevel) {
    ResourceKey<Level> registryKey =
        worldIn.dimension() == Level.OVERWORLD ? Level.END : Level.OVERWORLD;
    ServerLevel serverWorld = serverLevel.getServer().getLevel(registryKey);
    if (serverWorld == null) {
      return InteractionResult.FAIL;
    }

    DimensionTransition transition = getPortalDestination(serverLevel, player, player.getOnPos());

    if (transition == null) {
      return InteractionResult.FAIL;
    }
    player.changeDimension(transition);
    return InteractionResult.SUCCESS;
  }

  private DimensionTransition getPortalDestination(ServerLevel serverLevel, Entity entity, BlockPos blockPos) {
    ResourceKey<Level> resourcekey = serverLevel.dimension() == Level.END ? Level.OVERWORLD : Level.END;
    ServerLevel serverlevel = serverLevel.getServer().getLevel(resourcekey);
    if (serverlevel == null) {
      return null;
    } else {
      boolean flag = resourcekey == Level.END;
      BlockPos blockpos = flag ? ServerLevel.END_SPAWN_POINT : serverlevel.getSharedSpawnPos();
      Vec3 vec3 = blockpos.getBottomCenter();
      float f = entity.getYRot();
      if (flag) {
        EndPlatformFeature.createEndPlatform(serverlevel, BlockPos.containing(vec3).below(), true);
        f = Direction.WEST.toYRot();
        if (entity instanceof ServerPlayer) {
          vec3 = vec3.subtract(0.0, 1.0, 0.0);
        }
      } else {
        if (entity instanceof ServerPlayer serverplayer) {
          return serverplayer.findRespawnPositionAndUseSpawnBlock(false, DimensionTransition.DO_NOTHING);
        }

        vec3 = entity.adjustSpawnLocation(serverlevel, blockpos).getBottomCenter();
      }

      return new DimensionTransition(
          serverlevel,
          vec3,
          entity.getDeltaMovement(),
          f,
          entity.getXRot(),
          DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET)
      );
    }
  }
}
