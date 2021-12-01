package novamachina.exnihilosequentia.common.init;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.block.*;
import novamachina.exnihilosequentia.common.block.barrels.AbstractBarrelBlock;
import novamachina.exnihilosequentia.common.block.barrels.NetherBarrelBlock;
import novamachina.exnihilosequentia.common.block.barrels.WoodBarrelBlock;
import novamachina.exnihilosequentia.common.block.crucibles.FiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.NetherCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.UnfiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.WoodCrucibleBlock;
import novamachina.exnihilosequentia.common.block.sieves.NetherSieveBlock;
import novamachina.exnihilosequentia.common.block.sieves.WoodSieveBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.api.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ExNihiloBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    public static final RegistryObject<BaseFallingBlock> DUST = BLOCKS
            .register(ExNihiloConstants.Blocks.DUST, () -> new BaseFallingBlock(new BlockBuilder().properties(
                    BlockBehaviour.Properties.of(Material.SAND).strength(0.7F)
                            .sound(SoundType.SAND)).harvestLevel(ToolActions.SHOVEL_DIG)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_NETHERRACK = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_NETHERRACK, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(BlockBehaviour.Properties.of(Material.SAND).strength(0.7F)
                            .sound(SoundType.GRAVEL)).harvestLevel(ToolActions.SHOVEL_DIG)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_END_STONE = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_END_STONE, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(BlockBehaviour.Properties.of(Material.SAND).strength(0.7F)
                            .sound(SoundType.GRAVEL)).harvestLevel(ToolActions.SHOVEL_DIG)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_ANDESITE = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_ANDESITE, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(BlockBehaviour.Properties.of(Material.SAND).strength(0.7F)
                            .sound(SoundType.GRAVEL)).harvestLevel(ToolActions.SHOVEL_DIG)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_DIORITE = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_DIORITE, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(BlockBehaviour.Properties.of(Material.SAND).strength(0.7F)
                            .sound(SoundType.GRAVEL)).harvestLevel(ToolActions.SHOVEL_DIG)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_GRANITE = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_GRANITE, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(BlockBehaviour.Properties.of(Material.SAND).strength(0.7F)
                            .sound(SoundType.GRAVEL)).harvestLevel(ToolActions.SHOVEL_DIG)));
    public static final RegistryObject<EndCakeBlock> END_CAKE = BLOCKS
            .register(ExNihiloConstants.Blocks.END_CAKE, EndCakeBlock::new);
    public static final RegistryObject<LiquidBlock> WITCH_WATER = BLOCKS
            .register(ExNihiloConstants.Fluids.WITCH_WATER_STILL, WitchWaterBlock::new);
    public static final RegistryObject<LiquidBlock> SEA_WATER = BLOCKS
            .register(ExNihiloConstants.Fluids.SEA_WATER_STILL, SeaWaterBlock::new);
    public static final RegistryObject<BaseBlock> INFESTING_LEAVES = BLOCKS
            .register(Blocks.INFESTING_LEAVES, InfestingLeavesBlock::new);
    public static final RegistryObject<BaseBlock> INFESTED_LEAVES = BLOCKS
            .register(Blocks.INFESTED_LEAVES, InfestedLeavesBlock::new);
    public static final RegistryObject<BaseBlock> UNFIRED_CRUCIBLE = BLOCKS
            .register(Blocks.UNFIRED_CRUCIBLE, UnfiredCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> FIRED_CRUCIBLE = BLOCKS
            .register(Blocks.FIRED_CRUCIBLE, FiredCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> ACACIA_CRUCIBLE = BLOCKS
            .register(Blocks.ACACIA_CRUCIBLE, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> BIRCH_CRUCIBLE = BLOCKS
            .register(Blocks.BIRCH_CRUCIBLE, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> DARK_OAK_CRUCIBLE = BLOCKS
            .register(Blocks.DARK_OAK_CRUCIBLE, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> JUNGLE_CRUCIBLE = BLOCKS
            .register(Blocks.JUNGLE_CRUCIBLE, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> OAK_CRUCIBLE = BLOCKS
            .register(Blocks.OAK_CRUCIBLE, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> SPRUCE_CRUCIBLE = BLOCKS
            .register(Blocks.SPRUCE_CRUCIBLE, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRIMSON_CRUCIBLE = BLOCKS
            .register(Blocks.CRIMSON_CRUCIBLE, NetherCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> WARPED_CRUCIBLE = BLOCKS
            .register(Blocks.WARPED_CRUCIBLE, NetherCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> ACACIA_BARREL = BLOCKS
            .register(Blocks.ACACIA_BARREL, WoodBarrelBlock::new);
    public static final RegistryObject<BaseBlock> BIRCH_BARREL = BLOCKS
            .register(Blocks.BIRCH_BARREL, WoodBarrelBlock::new);
    public static final RegistryObject<BaseBlock> DARK_OAK_BARREL = BLOCKS
            .register(Blocks.DARK_OAK_BARREL, WoodBarrelBlock::new);
    public static final RegistryObject<BaseBlock> JUNGLE_BARREL = BLOCKS
            .register(Blocks.JUNGLE_BARREL, WoodBarrelBlock::new);
    public static final RegistryObject<BaseBlock> OAK_BARREL = BLOCKS
            .register(Blocks.OAK_BARREL, WoodBarrelBlock::new);
    public static final RegistryObject<BaseBlock> SPRUCE_BARREL = BLOCKS
            .register(Blocks.SPRUCE_BARREL, WoodBarrelBlock::new);
    public static final RegistryObject<BaseBlock> CRIMSON_BARREL = BLOCKS
            .register(Blocks.CRIMSON_BARREL, NetherBarrelBlock::new);
    public static final RegistryObject<BaseBlock> WARPED_BARREL = BLOCKS
            .register(Blocks.WARPED_BARREL, NetherBarrelBlock::new);
    public static final RegistryObject<BaseBlock> STONE_BARREL = BLOCKS
            .register(Blocks.STONE_BARREL, () -> new AbstractBarrelBlock(new BlockBuilder().harvestLevel(ToolActions.PICKAXE_DIG)
                    .properties(BlockBehaviour.Properties.of(Material.STONE).strength(0.75F).sound(SoundType.STONE))) {
                @Nullable
                @Override
                public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
                    return new StoneBarrelTile(pos, state);
                }
            });
    public static final RegistryObject<WoodSieveBlock> ACACIA_SIEVE = BLOCKS
            .register(Blocks.ACACIA_SIEVE, WoodSieveBlock::new);
    public static final RegistryObject<WoodSieveBlock> BIRCH_SIEVE = BLOCKS
            .register(Blocks.BIRCH_SIEVE, WoodSieveBlock::new);
    public static final RegistryObject<WoodSieveBlock> DARK_OAK_SIEVE = BLOCKS
            .register(Blocks.DARK_OAK_SIEVE, WoodSieveBlock::new);
    public static final RegistryObject<WoodSieveBlock> JUNGLE_SIEVE = BLOCKS
            .register(Blocks.JUNGLE_SIEVE, WoodSieveBlock::new);
    public static final RegistryObject<WoodSieveBlock> OAK_SIEVE = BLOCKS
            .register(Blocks.OAK_SIEVE, WoodSieveBlock::new);
    public static final RegistryObject<WoodSieveBlock> SPRUCE_SIEVE = BLOCKS
            .register(Blocks.SPRUCE_SIEVE, WoodSieveBlock::new);
    public static final RegistryObject<NetherSieveBlock> CRIMSON_SIEVE = BLOCKS
            .register(Blocks.CRIMSON_SIEVE, NetherSieveBlock::new);
    public static final RegistryObject<NetherSieveBlock> WARPED_SIEVE = BLOCKS
            .register(Blocks.WARPED_SIEVE, NetherSieveBlock::new);
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ExNihiloBlocks() {

    }

    public static void init(IEventBus modEventBus) {
        logger.debug("Register blocks");
        BLOCKS.register(modEventBus);
    }
}
