package novamachina.exnihilosequentia.common.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

public class SeedBaseItem extends Item implements IPlantable {

  @Nonnull
  private final BlockState plant;
  @Nullable
  private PlantType type;

  public SeedBaseItem(@Nonnull final BlockState plant, @Nonnull final PlantType type) {
    super(new Properties().tab(ExNihiloInitialization.ITEM_GROUP));
    this.plant = plant;
    this.type = type;
  }

  @Override
  @Nullable
  public BlockState getPlant(@Nonnull final BlockGetter world, @Nonnull final BlockPos pos) {
    return plant;
  }

  @Override
  @Nullable
  public PlantType getPlantType(@Nonnull final BlockGetter world, @Nonnull final BlockPos pos) {
    return type;
  }

  @Override
  @Nonnull
  public InteractionResult useOn(@Nonnull final UseOnContext context) {
    if (!context.getClickedFace().equals(Direction.UP)) {
      return InteractionResult.PASS;
    }

    @Nonnull final ItemStack item = context.getItemInHand();
    @Nullable final Player player = context.getPlayer();
    @Nonnull final BlockPos pos = context.getClickedPos();
    @Nonnull final Direction direction = context.getClickedFace();
    @Nonnull final Level world = context.getLevel();
    if (player != null && type != null && player.mayUseItemAt(pos, direction, item) && player
        .mayUseItemAt(pos.offset(0, 1, 0), direction, item)) {

      @Nullable final BlockState soil;
      if (type == PlantType.WATER) {
        soil = world.getBlockState(context.getClickedPos().offset(0, 1, 0));
      } else {
        soil = world.getBlockState(context.getClickedPos());
      }

      boolean canSustain = soil.getBlock().canSustainPlant(soil, world, pos, Direction.UP, this);
      boolean blockEmpty = isBlockSpaceEmpty(world, pos, type);
      @Nullable final BlockState plantAtPos = getPlant(world, pos);
      if (canSustain && blockEmpty && plantAtPos != null) {
        world.setBlockAndUpdate(pos.offset(0, 1, 0), plantAtPos);
        if (plantAtPos.getBlock() instanceof DoublePlantBlock) {
          world.setBlockAndUpdate(pos.above(2),
              plantAtPos.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
        }
        if (!player.isCreative()) {
          item.shrink(1);
        }
        world.playSound(player, player.blockPosition(), SoundEvents.GRASS_PLACE,
            SoundSource.AMBIENT, 1f, 1f);
        return InteractionResult.SUCCESS;
      }
    }
    return InteractionResult.PASS;
  }

  private boolean isBlockSpaceEmpty(@Nonnull final Level world, @Nonnull final BlockPos pos,
      @Nonnull final PlantType type) {
    if (type == PlantType.WATER) {
      return world.getBlockState(pos.above()).getBlock() == Blocks.WATER;
    }

    return world.isEmptyBlock(pos.above());
  }
}
