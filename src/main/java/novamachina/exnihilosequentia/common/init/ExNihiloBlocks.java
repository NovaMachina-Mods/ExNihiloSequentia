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
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.*;
import novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    public static final RegistryObject<BaseFallingBlock> DUST = BLOCKS
            .register(ExNihiloConstants.Blocks.DUST, () -> new BaseFallingBlock(new BlockBuilder().properties(
                    AbstractBlock.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                            .sound(SoundType.CLOTH)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_NETHERRACK = createCrushedBlocks(ExNihiloConstants.Blocks.CRUSHED_NETHERRACK);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_END_STONE = createCrushedBlocks(ExNihiloConstants.Blocks.CRUSHED_END_STONE);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_ANDESITE = createCrushedBlocks(ExNihiloConstants.Blocks.CRUSHED_ANDESITE);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_DIORITE = createCrushedBlocks(ExNihiloConstants.Blocks.CRUSHED_DIORITE);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_GRANITE = createCrushedBlocks(ExNihiloConstants.Blocks.CRUSHED_GRANITE);
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
    //TODO here are the sieves
    public static final RegistryObject<BlockSieve> SIEVE_ACACIA = createSieve(Blocks.SIEVE_ACACIA);
    public static final RegistryObject<BlockSieve> SIEVE_BIRCH = createSieve(Blocks.SIEVE_BIRCH);
    public static final RegistryObject<BlockSieve> SIEVE_DARK_OAK = createSieve(Blocks.SIEVE_DARK_OAK);
    public static final RegistryObject<BlockSieve> SIEVE_JUNGLE = createSieve(Blocks.SIEVE_JUNGLE);
    public static final RegistryObject<BlockSieve> SIEVE_SPRUCE = createSieve(Blocks.SIEVE_SPRUCE);
    public static final RegistryObject<BlockSieve> SIEVE_WOOD = createSieve(Blocks.SIEVE_OAK);
    public static final RegistryObject<BlockSieve> SIEVE_CRIMSON = BLOCKS.register(Blocks.SIEVE_CRIMSON,
            () -> new BlockSieve(new BlockBuilder().properties(AbstractBlock.Properties.create(Material.NETHER_WOOD).hardnessAndResistance(0.7F)
                    .sound(SoundType.HYPHAE).notSolid().setOpaque((state, reader, pos) -> false)
                    .setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false))
                    .harvestLevel(ToolType.AXE, 0).tileEntitySupplier(SieveTile::new)));
    public static final RegistryObject<BlockSieve> SIEVE_WARPED = BLOCKS.register(Blocks.SIEVE_WARPED,
            () -> new BlockSieve(new BlockBuilder().properties(AbstractBlock.Properties.create(Material.NETHER_WOOD).hardnessAndResistance(0.7F)
                            .sound(SoundType.HYPHAE).notSolid().setOpaque((state, reader, pos) -> false)
                            .setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false))
                    .harvestLevel(ToolType.AXE, 0).tileEntitySupplier(SieveTile::new)));
    //TODO added Crucible
    public static final RegistryObject<BaseBlock> CRUCIBLE_ACACIA = createWoodCrucible(Blocks.CRUCIBLE_ACACIA);
    public static final RegistryObject<BaseBlock> CRUCIBLE_BIRCH = createWoodCrucible(Blocks.CRUCIBLE_BIRCH);
    public static final RegistryObject<BaseBlock> CRUCIBLE_DARK_OAK = createWoodCrucible(Blocks.CRUCIBLE_DARK_OAK);
    public static final RegistryObject<BaseBlock> CRUCIBLE_JUNGLE = createWoodCrucible(Blocks.CRUCIBLE_JUNGLE);
    public static final RegistryObject<BaseBlock> CRUCIBLE_SPRUCE = createWoodCrucible(Blocks.CRUCIBLE_SPRUCE);
    public static final RegistryObject<BaseBlock> CRUCIBLE_WOOD = createWoodCrucible(Blocks.CRUCIBLE_OAK);
    public static final RegistryObject<BaseBlock> CRUCIBLE_CRIMSON = BLOCKS.register(Blocks.CRUCIBLE_CRIMSON,
            () -> new CrucibleBaseBlock(new BlockBuilder().properties(
                    AbstractBlock.Properties.create(Material.NETHER_WOOD).hardnessAndResistance(.75F)
                            .sound(SoundType.HYPHAE).notSolid()).harvestLevel(ToolType.AXE, 0)
                    .tileEntitySupplier(WoodCrucibleTile::new)));
    public static final RegistryObject<BaseBlock> CRUCIBLE_WARPED = BLOCKS.register(Blocks.CRUCIBLE_WARPED,
            () -> new CrucibleBaseBlock(new BlockBuilder().properties(
            AbstractBlock.Properties.create(Material.NETHER_WOOD).hardnessAndResistance(.75F)
                    .sound(SoundType.HYPHAE).notSolid()).harvestLevel(ToolType.AXE, 0)
            .tileEntitySupplier(WoodCrucibleTile::new)));
    //TODO here are the barrels
    public static final RegistryObject<BaseBlock> BARREL_ACACIA = createWoodBarrel(Blocks.BARREL_ACACIA);
    public static final RegistryObject<BaseBlock> BARREL_BIRCH = createWoodBarrel(Blocks.BARREL_BIRCH);
    public static final RegistryObject<BaseBlock> BARREL_DARK_OAK = createWoodBarrel(Blocks.BARREL_DARK_OAK);
    public static final RegistryObject<BaseBlock> BARREL_JUNGLE = createWoodBarrel(Blocks.BARREL_JUNGLE);
    public static final RegistryObject<BaseBlock> BARREL_SPRUCE = createWoodBarrel(Blocks.BARREL_SPRUCE);
    public static final RegistryObject<BaseBlock> BARREL_WOOD = createWoodBarrel(Blocks.BARREL_OAK);
    public static final RegistryObject<BaseBlock> BARREL_STONE = BLOCKS.register(Blocks.BARREL_STONE,
            () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.PICKAXE, 0)
                    .properties(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(0.75F).sound(SoundType.STONE))
                    .tileEntitySupplier(StoneBarrelTile::new)));
    public static final RegistryObject<BaseBlock> BARREL_CRIMSON = BLOCKS.register(Blocks.BARREL_CRIMSON,
            () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                    .properties(AbstractBlock.Properties.create(Material.NETHER_WOOD).hardnessAndResistance(0.75F).sound(SoundType.HYPHAE))
                    .tileEntitySupplier(StoneBarrelTile::new)));
    public static final RegistryObject<BaseBlock> BARREL_WARPED = BLOCKS.register(Blocks.BARREL_WARPED,
            () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                    .properties(AbstractBlock.Properties.create(Material.NETHER_WOOD).hardnessAndResistance(0.75F).sound(SoundType.HYPHAE))
                    .tileEntitySupplier(StoneBarrelTile::new)));
    public static final RegistryObject<BaseBlock> BARREL_GLASS = createGlassBarrel(Blocks.BARREL_GLASS);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_WHITE = createGlassBarrel(Blocks.BARREL_GLASS_WHITE);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_GRAY = createGlassBarrel(Blocks.BARREL_GLASS_GRAY);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_LIGHT_GRAY = createGlassBarrel(Blocks.BARREL_GLASS_LIGHT_GRAY);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_BLACK = createGlassBarrel(Blocks.BARREL_GLASS_BLACK);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_BROWN = createGlassBarrel(Blocks.BARREL_GLASS_BROWN);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_RED = createGlassBarrel(Blocks.BARREL_GLASS_RED);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_MAGENTA = createGlassBarrel(Blocks.BARREL_GLASS_MAGENTA);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_ORANGE = createGlassBarrel(Blocks.BARREL_GLASS_ORANGE);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_PINK = createGlassBarrel(Blocks.BARREL_GLASS_PINK);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_PURPLE = createGlassBarrel(Blocks.BARREL_GLASS_PURPLE);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_YELLOW = createGlassBarrel(Blocks.BARREL_GLASS_YELLOW);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_LIME = createGlassBarrel(Blocks.BARREL_GLASS_LIME);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_GREEN = createGlassBarrel(Blocks.BARREL_GLASS_GREEN);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_LIGHT_BLUE = createGlassBarrel(Blocks.BARREL_GLASS_LIGHT_BLUE);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_CYAN = createGlassBarrel(Blocks.BARREL_GLASS_CYAN);
    public static final RegistryObject<BaseBlock> BARREL_GLASS_BLUE = createGlassBarrel(Blocks.BARREL_GLASS_BLUE);
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ExNihiloBlocks() {

    }

    public static void init(IEventBus modEventBus) {
        logger.debug("Register blocks");
        BLOCKS.register(modEventBus);
    }

    private static RegistryObject<BaseFallingBlock> createCrushedBlocks(String block) {
        return BLOCKS.register(block, () -> new BaseFallingBlock(new BlockBuilder()
                        .properties(AbstractBlock.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                                .sound(SoundType.GROUND)).harvestLevel(ToolType.SHOVEL, 0)));
    }

    private static RegistryObject<BaseBlock> createWoodCrucible(String crucible) {
        return BLOCKS.register(crucible, WoodCrucibleBlock::new);
    }

    private static RegistryObject<BlockSieve> createSieve(String sieve) {
        return BLOCKS.register(sieve, () -> new BlockSieve(new BlockBuilder().properties(
                AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.7F)
                        .sound(SoundType.WOOD).notSolid().setOpaque((state, reader, pos) -> false)
                        .setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false))
                .harvestLevel(ToolType.AXE, 0).tileEntitySupplier(SieveTile::new)));
    }

    private static RegistryObject<BaseBlock> createWoodBarrel(String barrel) {
        return BLOCKS.register(barrel, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                        .properties(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.75F).sound(SoundType.WOOD))
                        .tileEntitySupplier(WoodBarrelTile::new)));
    }

    private static RegistryObject<BaseBlock> createGlassBarrel(String barrel) {
        return BLOCKS.register(barrel, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.PICKAXE, 0)
                        .properties(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.75F).sound(SoundType.GLASS))
                        .tileEntitySupplier(StoneBarrelTile::new)));
    }
}
