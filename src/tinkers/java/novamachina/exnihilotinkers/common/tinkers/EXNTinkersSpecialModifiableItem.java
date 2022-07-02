package novamachina.exnihilotinkers.common.tinkers;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class EXNTinkersSpecialModifiableItem extends ModifiableItem {
    private final TagKey<Block> tagKey;
    public EXNTinkersSpecialModifiableItem(Properties properties, ToolDefinition toolDefinition, TagKey<Block> tagKey) {
        super(properties, toolDefinition);
        this.tagKey = tagKey;
    }

    //TODO not modifiable due to Tinkers' setting this final D:
    //@Override
    //public float getDestroySpeed(ItemStack pStack, BlockState pState) {
    //    return pState.is(tagKey) ? super.getDestroySpeed(pStack, pState) + 1.0F : super.getDestroySpeed(pStack, pState);
    //}

    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.is(tagKey) && !ToolStack.from(stack).isBroken();
    }
}
