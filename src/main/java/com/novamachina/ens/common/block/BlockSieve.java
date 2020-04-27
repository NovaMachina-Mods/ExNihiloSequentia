package com.novamachina.ens.common.block;

import com.novamachina.ens.common.builder.BlockBuilder;
import com.novamachina.ens.common.item.mesh.EnumMesh;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraftforge.common.ToolType;

public class BlockSieve extends BaseBlock {

    public static final EnumProperty<EnumMesh> MESH = EnumProperty.create("mesh", EnumMesh.class);

    public BlockSieve() {
        super(new BlockBuilder().properties(
            Block.Properties.create(Material.WOOD).hardnessAndResistance(0.7F)
                .sound(SoundType.WOOD).notSolid()).harvestLevel(ToolType.AXE, 0));
        this.setDefaultState(this.stateContainer.getBaseState().with(MESH, EnumMesh.NONE));
    }

    protected void fillStateContainer(Builder<Block, BlockState> builder) {
        builder.add(MESH);
    }
}
