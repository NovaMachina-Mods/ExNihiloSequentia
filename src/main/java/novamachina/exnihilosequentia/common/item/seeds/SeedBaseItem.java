package novamachina.exnihilosequentia.common.item.seeds;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SeedBaseItem extends Item implements IPlantable {

    @Nonnull private final BlockState plant;
    @Nullable private PlantType type;

    public SeedBaseItem(@Nonnull final BlockState plant) {
        super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
        this.plant = plant;
    }

    @Override
    @Nullable
    public BlockState getPlant(@Nonnull final IBlockReader world, @Nonnull final BlockPos pos) {
        return plant;
    }

    @Override
    @Nullable
    public PlantType getPlantType(@Nonnull final IBlockReader world, @Nonnull final BlockPos pos) {
        return type;
    }

    @Override
    @Nonnull
    public ActionResultType useOn(@Nonnull final ItemUseContext context) {
        if (!context.getClickedFace().equals(Direction.UP)) {
            return ActionResultType.PASS;
        }

        @Nonnull final ItemStack item = context.getItemInHand();
        @Nullable final PlayerEntity player = context.getPlayer();
        @Nonnull final BlockPos pos = context.getClickedPos();
        @Nonnull final Direction direction = context.getClickedFace();
        @Nonnull final World world = context.getLevel();
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
            @Nullable final BlockState plant = getPlant(world, pos);
            if (canSustain && blockEmpty && plant != null) {
                world.setBlockAndUpdate(pos.offset(0, 1, 0), plant);
                if (plant.getBlock() instanceof DoublePlantBlock) {
                    world.setBlockAndUpdate(pos.above(2), plant.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
                }
                if (!player.isCreative()) {
                    item.shrink(1);
                }
                world.playSound(player, player.blockPosition(), SoundEvents.GRASS_PLACE, SoundCategory.AMBIENT, 1f, 1f);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    @Nonnull
    public SeedBaseItem setPlantType(@Nonnull final PlantType type) {
        this.type = type;
        return this;
    }

    private boolean isBlockSpaceEmpty(@Nonnull final World world, @Nonnull final BlockPos pos,
                                      @Nonnull final PlantType type) {
        if (type == PlantType.WATER) {
            return world.getBlockState(pos.above()).getBlock() == Blocks.WATER;
        }

        return world.isEmptyBlock(pos.above());
    }
}
