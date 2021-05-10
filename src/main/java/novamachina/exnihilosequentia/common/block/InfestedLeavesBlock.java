package novamachina.exnihilosequentia.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.InfestedLeavesTile;

public class InfestedLeavesBlock extends BaseBlock {

    public InfestedLeavesBlock() {
        super(new BlockBuilder().properties(
                AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).sound(
                        SoundType.GRASS).noOcclusion().isValidSpawn(BaseBlock::never)).tileEntitySupplier(InfestedLeavesTile::new));
    }
}
