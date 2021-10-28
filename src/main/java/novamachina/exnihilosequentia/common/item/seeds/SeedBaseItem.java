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

public class SeedBaseItem extends Item implements IPlantable {

    private final BlockState plant;
    private PlantType type;

    public SeedBaseItem(BlockState plant) {
        super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
        this.plant = plant;
    }

    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos) {
        return plant;
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return type;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        if (!context.getClickedFace().equals(Direction.UP)) {
            return ActionResultType.PASS;
        }

        ItemStack item = context.getItemInHand();
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        World world = context.getLevel();
        if (player.mayUseItemAt(pos, direction, item) && player
                .mayUseItemAt(pos.offset(0, 1, 0), direction, item)) {

            BlockState soil;
            if (type == PlantType.WATER) {
                soil = world.getBlockState(context.getClickedPos().offset(0, 1, 0));
            } else {
                soil = world.getBlockState(context.getClickedPos());
            }

            boolean canSustain = soil.getBlock().canSustainPlant(soil, world, pos, Direction.UP, this);
            boolean blockEmpty = isBlockSpaceEmpty(world, pos, type);
            if (canSustain && blockEmpty && this.getPlant(world, pos) != null) {
                world.setBlockAndUpdate(pos.offset(0, 1, 0),
                        this.getPlant(world, pos));
                if (this.getPlant(world, pos).getBlock() instanceof DoublePlantBlock) {
                    world.setBlockAndUpdate(
                            pos.above(2),
                            this.getPlant(world, pos).setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                    );
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

    public SeedBaseItem setPlantType(PlantType type) {
        this.type = type;
        return this;
    }

    private boolean isBlockSpaceEmpty(World world, BlockPos pos, PlantType type) {
        if (type == PlantType.WATER) {
            return world.getBlockState(pos.offset(0, 1, 0)).getBlock() == Blocks.WATER;
        }

        return world.isEmptyBlock(pos.offset(0, 1, 0));
    }
}
