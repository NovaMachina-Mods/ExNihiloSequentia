package novamachina.exnihilosequentia.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.InfestedLeavesTile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InfestedLeavesBlock extends BaseBlock implements IForgeShearable {

    public InfestedLeavesBlock() {
        super(new BlockBuilder().harvestLevel(ToolType.get("crook"), 0).properties(
                AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).sound(
                        SoundType.GRASS).noOcclusion()
                        .isValidSpawn(BaseBlock::never)).tileEntitySupplier(InfestedLeavesTile::new));
    }

    @Override
    public void playerDestroy(@Nonnull World world, PlayerEntity player, @Nonnull BlockPos pos, BlockState state, @Nullable TileEntity p_180657_5_, ItemStack itemStack) {
        if (itemStack.getItem() instanceof ShearsItem) {
            world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5F, pos.getY() + 1.1F,
                    pos.getZ() + 0.5F, new ItemStack(this)));
        }
    }
}
