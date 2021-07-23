package novamachina.exnihilosequentia.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.IForgeShearable;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.InfestedLeavesTile;

public class InfestedLeavesBlock extends BaseBlock implements IForgeShearable {

    public InfestedLeavesBlock() {
        super(new BlockBuilder().properties(
                Block.Properties.of(Material.LEAVES).strength(0.2F).sound(
                        SoundType.GRASS).noOcclusion().isValidSpawn(BaseBlock::never)).tileEntitySupplier(InfestedLeavesTile::new));
    }
}
