package com.novamachina.ens.common.block;

import com.novamachina.ens.common.builder.BlockBuilder;
import com.novamachina.ens.common.item.mesh.EnumMesh;
import com.novamachina.ens.common.item.mesh.MeshItem;
import com.novamachina.ens.common.tileentity.sieve.SieveDrops;
import com.novamachina.ens.common.tileentity.sieve.SieveTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockSieve extends BaseBlock {

    public static final EnumProperty<EnumMesh> MESH = EnumProperty.create("mesh", EnumMesh.class);

    public BlockSieve() {
        super(new BlockBuilder().properties(
            Block.Properties.create(Material.WOOD).hardnessAndResistance(0.7F)
                .sound(SoundType.WOOD).notSolid()).harvestLevel(ToolType.AXE, 0).tileEntitySupplier(
            SieveTile::new));
        this.setDefaultState(this.stateContainer.getBaseState().with(MESH, EnumMesh.NONE));
    }

    protected void fillStateContainer(Builder<Block, BlockState> builder) {
        builder.add(MESH);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
        PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            ItemStack stack     = player.getHeldItem(handIn);
            SieveTile sieveTile = (SieveTile) worldIn.getTileEntity(pos);

            if (sieveTile.isReadyToSieve()) {
                sieveTile.activateSieve();
            } else if (stack.isEmpty() && player.isSneaking()) {
                sieveTile.removeMesh(true);
            } else if (stack.getItem() instanceof MeshItem) {
                sieveTile.insertMesh(stack);
            } else if (stack.getItem() instanceof BlockItem) {
                BlockItem blockItem = (BlockItem) stack.getItem();
                if (SieveDrops.isBlockSiftable(blockItem.getBlock(), sieveTile.getMesh())) {
                    sieveTile.insertSiftableBlock(stack);
                }
            }
        }
        worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
        return ActionResultType.SUCCESS;
    }
}
