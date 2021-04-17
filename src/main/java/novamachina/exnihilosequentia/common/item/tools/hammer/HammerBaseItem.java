package novamachina.exnihilosequentia.common.item.tools.hammer;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

public class HammerBaseItem extends ToolItem {

    private static final Set<Block> effectiveBlocksOn = Sets
            .newHashSet(Blocks.STONE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.GRANITE,
                    Blocks.NETHERRACK, Blocks.END_STONE, Blocks.COBBLESTONE,
                    ExNihiloBlocks.CRUSHED_DIORITE.get(), ExNihiloBlocks.CRUSHED_ANDESITE.get(),
                    ExNihiloBlocks.CRUSHED_GRANITE.get(), ExNihiloBlocks.CRUSHED_NETHERRACK.get(),
                    ExNihiloBlocks.CRUSHED_END_STONE.get(), Blocks.GRAVEL, Blocks.SAND,
                    Blocks.TUBE_CORAL_BLOCK, Blocks.BRAIN_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK,
                    Blocks.FIRE_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK,
                    Blocks.TUBE_CORAL, Blocks.BRAIN_CORAL, Blocks.BUBBLE_CORAL,
                    Blocks.FIRE_CORAL, Blocks.HORN_CORAL);

    public HammerBaseItem(IItemTier tier, int maxDamage) {
        super(0.5F, 0.5F, tier, effectiveBlocksOn,
                new Item.Properties().defaultDurability(maxDamage).tab(ExNihiloInitialization.ITEM_GROUP)
                        .addToolType(ToolType.PICKAXE, tier.getLevel()));
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState blockIn) {
        if (ExNihiloRegistries.HAMMER_REGISTRY.isHammerable(blockIn.getBlock())) {
            return true;
        }
        return super.isCorrectToolForDrops(blockIn);
    }
}
