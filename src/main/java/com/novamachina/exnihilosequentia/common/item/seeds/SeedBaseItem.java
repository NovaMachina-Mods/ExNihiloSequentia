package com.novamachina.exnihilosequentia.common.item.seeds;

import com.novamachina.exnihilosequentia.common.setup.ModInitialization;
import net.minecraft.block.BlockState;
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
    private       PlantType  type;

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

        ItemStack    item      = context.getItem();
        PlayerEntity player    = context.getPlayer();
        BlockPos     pos       = context.getPos();
        Direction    direction = context.getFace();
        World        world     = context.getWorld();
        if (player.canPlayerEdit(pos, direction, item) && player
            .canPlayerEdit(pos.add(0, 1, 0), direction, item)) {
            BlockState soil = world.getBlockState(context.getPos());

            if (soil.getBlock().canSustainPlant(soil, world, pos, Direction.UP, this) && world
                .isAirBlock(pos.add(0, 1, 0)) && this.getPlant(world, pos) != null) {
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
}
