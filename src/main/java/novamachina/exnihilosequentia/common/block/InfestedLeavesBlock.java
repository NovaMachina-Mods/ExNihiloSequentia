package novamachina.exnihilosequentia.common.block;

import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.InfestedLeavesTile;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class InfestedLeavesBlock extends BaseBlock {

    public InfestedLeavesBlock() {
        super(new BlockBuilder().properties(
            Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(
                SoundType.PLANT).notSolid()).tileEntitySupplier(InfestedLeavesTile::new));
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

    }
}
