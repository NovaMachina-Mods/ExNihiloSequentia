package com.novamachina.exnihilosequentia.common.block;

import com.novamachina.exnihilosequentia.common.builder.BlockBuilder;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.tileentity.sieve.SieveTile;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.StringUtils;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockSieve extends BaseBlock implements IWaterLoggable, IProbeInfoProvider {

    public static final EnumProperty<EnumMesh> MESH = EnumProperty.create("mesh", EnumMesh.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BlockSieve() {
        super(new BlockBuilder().properties(
            Block.Properties.create(Material.WOOD).hardnessAndResistance(0.7F)
                .sound(SoundType.WOOD).notSolid()).harvestLevel(ToolType.AXE, 0).tileEntitySupplier(
            SieveTile::new));
        this.setDefaultState(this.stateContainer.getBaseState().with(MESH, EnumMesh.NONE).with(WATERLOGGED, false));
    }

    protected void fillStateContainer(Builder<Block, BlockState> builder) {
        builder.add(MESH, WATERLOGGED);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
        PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            ItemStack stack     = player.getHeldItem(handIn);
            SieveTile sieveTile = (SieveTile) worldIn.getTileEntity(pos);

            if (sieveTile.isReadyToSieve()) {
                sieveTile.activateSieve(state.get(WATERLOGGED));
            } else if (stack.isEmpty() && player.isSneaking()) {
                sieveTile.removeMesh(true);
            } else if (stack.getItem() instanceof MeshItem) {
                sieveTile.insertMesh(stack);
            } else if (stack.getItem() instanceof BlockItem) {
                BlockItem blockItem = (BlockItem) stack.getItem();
                if (ModRegistries.SIEVE.isBlockSiftable(blockItem.getBlock(), sieveTile.getMesh(), state.get(WATERLOGGED))) {
                    sieveTile.insertSiftableBlock(stack);
                }
            }
        }
        worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
        return ActionResultType.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IFluidState fluidState = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if(stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public String getID() {
        return Constants.ModIds.EX_NIHILO_SEQUENTIA + ":sieve";
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData iProbeHitData) {
        SieveTile sieveTile = (SieveTile) world.getTileEntity(iProbeHitData.getPos());
        String block = I18n.format(sieveTile.getBlockStack().getTranslationKey());

        if(!sieveTile.getBlockStack().isEmpty()) {
            iProbeInfo.text(new TranslationTextComponent("waila.progress", StringUtils.formatPercent(sieveTile.getProgress()/ 1.0F)));
            iProbeInfo.text(new TranslationTextComponent("waila.sieve.block", block));
        }
        if(sieveTile.getMesh() != EnumMesh.NONE) {
            iProbeInfo.text(new TranslationTextComponent("waila.sieve.mesh", sieveTile.getMesh().getName()));
        }
    }
}
