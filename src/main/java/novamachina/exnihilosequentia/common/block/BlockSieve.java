package novamachina.exnihilosequentia.common.block;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nullable;
import java.util.List;

public class BlockSieve extends BaseBlock implements IWaterLoggable, ITOPInfoProvider {
    public static final EnumProperty<EnumMesh> MESH = EnumProperty.create("mesh", EnumMesh.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public BlockSieve(BlockBuilder builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(MESH, EnumMesh.NONE).with(WATERLOGGED, false));
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
                sieveTile.insertSiftableBlock(stack, player);
            }
        }
        worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData iProbeHitData) {
        SieveTile sieveTile = (SieveTile) world.getTileEntity(iProbeHitData.getPos());

        if (!sieveTile.getBlockStack().isEmpty()) {
            iProbeInfo.text(new TranslationTextComponent("waila.progress", StringUtils
                    .formatPercent(sieveTile.getProgress() / 1.0F)));
            iProbeInfo.text(new TranslationTextComponent("waila.sieve.block", new TranslationTextComponent(sieveTile.getBlockStack().getTranslationKey())));
        }
        if (sieveTile.getMesh() != EnumMesh.NONE) {
            iProbeInfo.text(new TranslationTextComponent("waila.sieve.mesh", new TranslationTextComponent("item." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "." + sieveTile.getMesh().getMeshName())));
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Override
    public FluidState getFluidState(BlockState state) {
        return Boolean.TRUE.equals(state.get(WATERLOGGED)) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidState = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        if (!worldIn.isRemote() && te instanceof SieveTile) {
            ((SieveTile) te).removeMesh(true);
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            logger.debug("Sieve Activated");
            SieveTile sieveTile = (SieveTile) worldIn.getTileEntity(pos);
            ItemStack stack = player.getHeldItem(handIn);
            if (player.isSneaking() && stack.isEmpty()) {
                sieveTile.removeMesh(true);
            }

            for (BlockPos sievePos : getNearbySieves(worldIn, pos)) {
                BlockState currentState = worldIn.getBlockState(sievePos);
                activateBlock(currentState, worldIn, player, sievePos, handIn);
            }

            if (stack.getItem() instanceof MeshItem) {
                sieveTile.insertMesh(stack, player);
            }
        }
        worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 2);
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote()) {
            SieveTile sieveTile = (SieveTile) worldIn.getTileEntity(pos);
            sieveTile.setSieveState();
        }
    }

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (Boolean.TRUE.equals(stateIn.get(WATERLOGGED))) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected void fillStateContainer(Builder<Block, BlockState> builder) {
        builder.add(MESH, WATERLOGGED);
    }

    private List<BlockPos> getNearbySieves(World world, BlockPos pos) {
        NonNullList<BlockPos> nearbySieves = NonNullList.create();

        BlockPos
                .getAllInBox(new BlockPos(pos.getX() - Config.getSieveRange(), pos.getY(), pos.getZ() - Config.getSieveRange()),
                        new BlockPos(pos.getX() + Config.getSieveRange(), pos.getY(), pos.getZ() + Config.getSieveRange()))
                .forEach(item -> {
                    if (world.getBlockState(item).getBlock() instanceof BlockSieve) {
                        nearbySieves.add(new BlockPos(item));
                    }
                });

        return nearbySieves;
    }

    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }
}