package com.novamachina.exnihilosequentia.common.item.resources;

import com.novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import com.novamachina.exnihilosequentia.common.setup.ModInitialization;
import com.novamachina.exnihilosequentia.common.utility.Constants.Items;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public class ResourceItem extends Item {

    private final String resourceName;

    public ResourceItem(String name) {
        super(new Item.Properties().group(ModInitialization.ITEM_GROUP));
        this.resourceName = name;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        ItemStack item = context.getItem();
        if (!context.getWorld().isRemote()) {
            if (item.getItem() instanceof ResourceItem) {
                ResourceItem resourceItem = (ResourceItem) item.getItem();

                BlockState state = context.getWorld().getBlockState(context.getPos());
                if (resourceItem.getResourceName().equals(Items.SILKWORM)) {
                    if (state.getBlock() instanceof LeavesBlock) {
                        LogUtil.info("INFEST LEAVES");
                        context.getItem().shrink(1);
                        // TODO: Replace dirt with infested leaves
//                        Block.replaceBlock(state,
//                            Blocks.DIRT.getDefaultState(), context.getWorld(), context.getPos(), 1);
                        InfestingLeavesBlock.normalToInfesting(context.getWorld(),
                            context.getWorld().getBlockState(context.getPos()), context.getPos());
                        return ActionResultType.SUCCESS;
                    }
                    LogUtil.info("NOT LEAVES");
                }
                if (resourceItem.getResourceName().equals(Items.ANCIENT_SPORE) || resourceItem
                    .getResourceName().equals(Items.GRASS_SEED)) {
                    if (state.getBlock().equals(Blocks.DIRT)) {
                        context.getItem().shrink(1);
                        if (resourceItem.getResourceName().equals(Items.ANCIENT_SPORE)) {
                            Block.replaceBlock(state,
                                Blocks.MYCELIUM.getDefaultState(), context.getWorld(),
                                context.getPos(), 1);
                        } else {
                            Block.replaceBlock(state,
                                Blocks.GRASS_BLOCK.getDefaultState(), context.getWorld(),
                                context.getPos(), 1);
                        }
                        return ActionResultType.SUCCESS;
                    }
                }
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn,
        LivingEntity target, Hand hand) {
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    public String getResourceName() {
        return resourceName;
    }
}