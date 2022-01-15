package novamachina.exnihilosequentia.common.item.resources;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Items;

import javax.annotation.Nonnull;

public class ResourceItem extends Item {

    @Nonnull private final String resourceName;

    public ResourceItem(@Nonnull final String name) {
        super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
        this.resourceName = name;
    }

    @Nonnull
    public String getResourceName() {
        return resourceName;
    }

    @Override
    @Nonnull
    public ActionResultType useOn(@Nonnull final ItemUseContext context) {
        final @Nonnull ItemStack item = context.getItemInHand();
        if (!context.getLevel().isClientSide() && item.getItem() instanceof ResourceItem) {
            final @Nonnull ResourceItem resourceItem = (ResourceItem) item.getItem();

            final @Nonnull BlockState state = context.getLevel().getBlockState(context.getClickedPos());
            if (resourceItem.getResourceName().equals(Items.SILKWORM) && state.getBlock() instanceof LeavesBlock) {
                context.getItemInHand().shrink(1);
                InfestingLeavesBlock.normalToInfesting(context.getLevel(),
                        context.getClickedPos());
                return ActionResultType.SUCCESS;
            }
            if (resourceItem.getResourceName().equals(Items.ANCIENT_SPORE) || resourceItem.getResourceName()
                    .equals(Items.GRASS_SEED)) {
                if(state.getBlock().equals(Blocks.DIRT)) {
                    context.getItemInHand().shrink(1);
                    if (resourceItem.getResourceName().equals(Items.ANCIENT_SPORE) && state.getBlock().equals(Blocks.DIRT)) {
                        Block.updateOrDestroy(state,
                                Blocks.MYCELIUM.defaultBlockState(), context.getLevel(),
                                context.getClickedPos(), 1);
                        return ActionResultType.SUCCESS;
                    } else if (resourceItem.getResourceName().equals(Items.GRASS_SEED) && state.getBlock().equals(Blocks.DIRT)) {
                        Block.updateOrDestroy(state,
                                Blocks.GRASS_BLOCK.defaultBlockState(), context.getLevel(),
                                context.getClickedPos(), 1);
                        return ActionResultType.SUCCESS;
                    }
                }
                return ActionResultType.FAIL;
            }
        }
        return ActionResultType.PASS;
    }
}