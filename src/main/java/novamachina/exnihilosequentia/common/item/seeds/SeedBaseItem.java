package novamachina.exnihilosequentia.common.item.seeds;

import novamachina.exnihilosequentia.common.init.ModInitialization;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class SeedBaseItem extends Item implements IPlantable {

    private final BlockState plant;
    private PlantType type;

    public SeedBaseItem(BlockState plant) {
        super(new Item.Properties().group(ModInitialization.ITEM_GROUP));
        this.plant = plant;
    }

    public SeedBaseItem setPlantType(PlantType type) {
        this.type = type;
        return this;
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return type;
    }

    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos) {
        return plant;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (!context.getFace().equals(Direction.UP)) {
            return ActionResultType.PASS;
        }

        ItemStack item = context.getItem();
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getPos();
        Direction direction = context.getFace();
        World world = context.getWorld();
        if (player.canPlayerEdit(pos, direction, item) && player
            .canPlayerEdit(pos.add(0, 1, 0), direction, item)) {

            BlockState soil;
            if (type == PlantType.WATER) {
                soil = world.getBlockState(context.getPos().add(0, 1, 0));
            } else {
                soil = world.getBlockState(context.getPos());
            }

            boolean canSustain = soil.getBlock().canSustainPlant(soil, world, pos, Direction.UP, this);
            boolean blockEmpty = isBlockSpaceEmpty(world, pos, type);
            if (canSustain && blockEmpty && this.getPlant(world, pos) != null) {
                world.setBlockState(pos.add(0, 1, 0),
                    this.getPlant(world, pos));
                if (!player.isCreative()) {
                    item.shrink(1);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    private boolean isBlockSpaceEmpty(World world, BlockPos pos, PlantType type) {
        if (type == PlantType.WATER) {
            return world.getBlockState(pos.add(0, 1, 0)).getBlock() == Blocks.WATER;
        }

        return world.isAirBlock(pos.add(0, 1, 0));
    }
}
