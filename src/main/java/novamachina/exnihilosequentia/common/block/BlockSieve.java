package novamachina.exnihilosequentia.common.block;

import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.Constants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
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
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.apache.logging.log4j.LogManager;

import java.util.List;

public class BlockSieve extends BaseBlock implements IWaterLoggable, ITOPInfoProvider {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public static final EnumProperty<EnumMesh> MESH = EnumProperty.create("mesh", EnumMesh.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BlockSieve() {
        super(new BlockBuilder().properties(
            Block.Properties.create(Material.WOOD).hardnessAndResistance(0.7F)
                .sound(SoundType.WOOD).notSolid().setOpaque((state, reader, pos) -> false)
                    .setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false))
                .harvestLevel(ToolType.AXE, 0).tileEntitySupplier(
            SieveTile::new));
        this.setDefaultState(this.stateContainer.getBaseState().with(MESH, EnumMesh.NONE).with(WATERLOGGED, false));
    }

    protected void fillStateContainer(Builder<Block, BlockState> builder) {
        builder.add(MESH, WATERLOGGED);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            logger.debug("Sieve Activated");
            SieveTile sieveTile = (SieveTile) worldIn.getTileEntity(pos);
            ItemStack stack = player.getHeldItem(handIn);

            for (BlockPos sievePos : getNearbySieves(worldIn, pos)) {
                BlockState currentState = worldIn.getBlockState(sievePos);
                activateBlock(currentState, worldIn, player, sievePos, handIn);
            }

            if (stack.isEmpty() && player.isSneaking()) {
                sieveTile.removeMesh(true);
            } else if (stack.getItem() instanceof MeshItem) {
                sieveTile.insertMesh(stack);
            }
        }
        worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
        return ActionResultType.SUCCESS;
    }

    public void activateBlock(BlockState state, World worldIn, PlayerEntity player, BlockPos pos, Hand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        SieveTile sieveTile = (SieveTile) worldIn.getTileEntity(pos);

        logger.debug("isReadyToSieve: " + sieveTile.isReadyToSieve());
        if (sieveTile.isReadyToSieve()) {
            sieveTile.activateSieve(state.get(WATERLOGGED));
        }
        if (!sieveTile.isReadyToSieve() && stack.getItem() instanceof BlockItem) {
            BlockItem blockItem = (BlockItem) stack.getItem();
            logger.debug("Is Block Siftable: " + ExNihiloRegistries.SIEVE_REGISTRY.isBlockSiftable(blockItem.getBlock(), sieveTile.getMesh(), state.get(WATERLOGGED)));
            if (ExNihiloRegistries.SIEVE_REGISTRY.isBlockSiftable(blockItem.getBlock(), sieveTile.getMesh(), state.get(WATERLOGGED))) {
                sieveTile.insertSiftableBlock(stack);
            }
        }
        worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidState = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData iProbeHitData) {
        SieveTile sieveTile = (SieveTile) world.getTileEntity(iProbeHitData.getPos());
        String block = I18n.format(sieveTile.getBlockStack().getTranslationKey());

        if (!sieveTile.getBlockStack().isEmpty()) {
            iProbeInfo.text(new TranslationTextComponent("waila.progress", StringUtils
                .formatPercent(sieveTile.getProgress() / 1.0F)));
            iProbeInfo.text(new TranslationTextComponent("waila.sieve.block", block));
        }
        if (sieveTile.getMesh() != EnumMesh.NONE) {
            String meshName = I18n.format("item." + Constants.ModIds.EX_NIHILO_SEQUENTIA + "." + sieveTile.getMesh().getMeshName());
            if(meshName.equals(sieveTile.getMesh().getMeshName()))
                meshName = sieveTile.getMesh().getName();
            iProbeInfo.text(new TranslationTextComponent("waila.sieve.mesh", meshName));
        }
    }

    private List<BlockPos> getNearbySieves(World world, BlockPos pos) {
        NonNullList<BlockPos> nearbySieves = NonNullList.create();

        BlockPos
            .getAllInBox(new BlockPos(pos.getX() - Config.SIEVE_RANGE.get(), pos.getY(), pos.getZ() - Config.SIEVE_RANGE
                    .get()),
                new BlockPos(pos.getX() + Config.SIEVE_RANGE.get(), pos.getY(), pos.getZ() + Config.SIEVE_RANGE.get()))
            .forEach(item -> {
                if (world.getBlockState(item).getBlock() instanceof BlockSieve) {
                    nearbySieves.add(new BlockPos(item));
                }
            });

        return nearbySieves;
    }
}
