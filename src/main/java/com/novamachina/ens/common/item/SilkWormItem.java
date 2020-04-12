package com.novamachina.ens.common.item;

import com.novamachina.ens.common.setup.ModSetup;
import com.novamachina.ens.common.utility.LogUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class SilkWormItem extends Item {

    public SilkWormItem() {
        super(new Item.Properties().group(ModSetup.ITEM_GROUP));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (!context.getWorld().isRemote()) {
            BlockState state = context.getWorld().getBlockState(context.getPos());
            if (state.getBlock() instanceof LeavesBlock) {
                LogUtil.info("INFEST LEAVES");
                context.getItem().shrink(1);
                return ActionResultType.SUCCESS;
            }
            LogUtil.info("NOT LEAVES");
        }
        return ActionResultType.PASS;
    }
}
