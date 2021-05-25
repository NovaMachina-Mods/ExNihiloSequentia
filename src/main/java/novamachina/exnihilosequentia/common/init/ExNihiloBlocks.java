package novamachina.exnihilosequentia.common.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.block.*;
import novamachina.exnihilosequentia.common.block.barrels.NetherBarrelBlock;
import novamachina.exnihilosequentia.common.block.barrels.WoodBarrelBlock;
import novamachina.exnihilosequentia.common.block.crucibles.FiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.NetherCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.UnfiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.WoodCrucibleBlock;
import novamachina.exnihilosequentia.common.block.sieves.NetherSieveBlock;
import novamachina.exnihilosequentia.common.block.sieves.WoodSieveBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    public static final RegistryObject<BaseFallingBlock> DUST = BLOCKS
            .register(ExNihiloConstants.Blocks.DUST, () -> new BaseFallingBlock(new BlockBuilder().properties(
                    AbstractBlock.Properties.of(Material.SAND).strength(0.7F)
                            .sound(SoundType.WOOL)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_NETHERRACK = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_NETHERRACK, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(AbstractBlock.Properties.of(Material.SAND).strength(0.7F)
                            .sound(SoundType.GRAVEL)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_END_STONE = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_END_STONE, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(AbstractBlock.Properties.of(Material.SAND).strength(0.7F)
                            .sound(SoundType.GRAVEL)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_ANDESITE = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_ANDESITE, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(AbstractBlock.Properties.of(Material.SAND).strength(0.7F)
                            .sound(SoundType.GRAVEL)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_DIORITE = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_DIORITE, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(AbstractBlock.Properties.of(Material.SAND).strength(0.7F)
                            .sound(SoundType.GRAVEL)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_GRANITE = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_GRANITE, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(AbstractBlock.Properties.of(Material.SAND).strength(0.7F)
                            .sound(SoundType.GRAVEL)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<EndCakeBlock> END_CAKE = BLOCKS
            .register(ExNihiloConstants.Blocks.END_CAKE, EndCakeBlock::new);
    public static final RegistryObject<FlowingFluidBlock> WITCH_WATER = BLOCKS
            .register(ExNihiloConstants.Blocks.WITCH_WATER, WitchWaterBlock::new);
    public static final RegistryObject<FlowingFluidBlock> SEA_WATER = BLOCKS
            .register(ExNihiloConstants.Blocks.SEA_WATER, SeaWaterBlock::new);
    public static final RegistryObject<BaseBlock> INFESTING_LEAVES = BLOCKS
            .register(Blocks.INFESTING_LEAVES, InfestingLeavesBlock::new);
    public static final RegistryObject<BaseBlock> INFESTED_LEAVES = BLOCKS
            .register(Blocks.INFESTED_LEAVES, InfestedLeavesBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_UNFIRED = BLOCKS
            .register(Blocks.CRUCIBLE_UNFIRED, UnfiredCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_FIRED = BLOCKS
            .register(Blocks.CRUCIBLE_FIRED, FiredCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_ACACIA = BLOCKS
            .register(Blocks.CRUCIBLE_ACACIA, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_BIRCH = BLOCKS
            .register(Blocks.CRUCIBLE_BIRCH, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_DARK_OAK = BLOCKS
            .register(Blocks.CRUCIBLE_DARK_OAK, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_JUNGLE = BLOCKS
            .register(Blocks.CRUCIBLE_JUNGLE, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_OAK = BLOCKS
            .register(Blocks.CRUCIBLE_OAK, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_SPRUCE = BLOCKS
            .register(Blocks.CRUCIBLE_SPRUCE, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_CRIMSON = BLOCKS
            .register(Blocks.CRUCIBLE_CRIMSON, NetherCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_WARPED = BLOCKS
            .register(Blocks.CRUCIBLE_WARPED, NetherCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> BARREL_ACACIA = BLOCKS
            .register(Blocks.BARREL_ACACIA, WoodBarrelBlock::new);
    public static final RegistryObject<BaseBlock> BARREL_BIRCH = BLOCKS
            .register(Blocks.BARREL_BIRCH, WoodBarrelBlock::new);
    public static final RegistryObject<BaseBlock> BARREL_DARK_OAK = BLOCKS
            .register(Blocks.BARREL_DARK_OAK, WoodBarrelBlock::new);
    public static final RegistryObject<BaseBlock> BARREL_JUNGLE = BLOCKS
            .register(Blocks.BARREL_JUNGLE, WoodBarrelBlock::new);
    public static final RegistryObject<BaseBlock> BARREL_OAK = BLOCKS
            .register(Blocks.BARREL_OAK, WoodBarrelBlock::new);
    public static final RegistryObject<BaseBlock> BARREL_SPRUCE = BLOCKS
            .register(Blocks.BARREL_SPRUCE, WoodBarrelBlock::new);
    public static final RegistryObject<BaseBlock> BARREL_CRIMSON = BLOCKS
            .register(Blocks.BARREL_CRIMSON, NetherBarrelBlock::new);
    public static final RegistryObject<BaseBlock> BARREL_WARPED = BLOCKS
            .register(Blocks.BARREL_WARPED, NetherBarrelBlock::new);
    public static final RegistryObject<BaseBlock> BARREL_STONE = BLOCKS
            .register(Blocks.BARREL_STONE, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.PICKAXE, 0)
                    .properties(AbstractBlock.Properties.of(Material.STONE).strength(0.75F).sound(SoundType.STONE))
                    .tileEntitySupplier(StoneBarrelTile::new)));
    public static final RegistryObject<WoodSieveBlock> SIEVE_ACACIA = BLOCKS
            .register(Blocks.SIEVE_ACACIA, WoodSieveBlock::new);
    public static final RegistryObject<WoodSieveBlock> SIEVE_BIRCH = BLOCKS
            .register(Blocks.SIEVE_BIRCH, WoodSieveBlock::new);
    public static final RegistryObject<WoodSieveBlock> SIEVE_DARK_OAK = BLOCKS
            .register(Blocks.SIEVE_DARK_OAK, WoodSieveBlock::new);
    public static final RegistryObject<WoodSieveBlock> SIEVE_JUNGLE = BLOCKS
            .register(Blocks.SIEVE_JUNGLE, WoodSieveBlock::new);
    public static final RegistryObject<WoodSieveBlock> SIEVE_OAK = BLOCKS
            .register(Blocks.SIEVE_OAK, WoodSieveBlock::new);
    public static final RegistryObject<WoodSieveBlock> SIEVE_SPRUCE = BLOCKS
            .register(Blocks.SIEVE_SPRUCE, WoodSieveBlock::new);
    public static final RegistryObject<NetherSieveBlock> SIEVE_CRIMSON = BLOCKS
            .register(Blocks.SIEVE_CRIMSON, NetherSieveBlock::new);
    public static final RegistryObject<NetherSieveBlock> SIEVE_WARPED = BLOCKS
            .register(Blocks.SIEVE_WARPED, NetherSieveBlock::new);
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ExNihiloBlocks() {

    }

    public static void init(IEventBus modEventBus) {
        logger.debug("Register blocks");
        BLOCKS.register(modEventBus);
    }
}
