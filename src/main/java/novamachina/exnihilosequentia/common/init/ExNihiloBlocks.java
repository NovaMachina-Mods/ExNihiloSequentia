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
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloBlocks {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, ModIds.EX_NIHILO_SEQUENTIA);
    //TODO Simplified due to no TileEntity or further usage
    public static final RegistryObject<BaseFallingBlock> DUST = createFallingBlocks(Blocks.DUST, SoundType.SAND);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_NETHERRACK = createFallingBlocks(Blocks.CRUSHED_NETHERRACK, SoundType.STONE);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_END_STONE = createFallingBlocks(Blocks.CRUSHED_END_STONE, SoundType.STONE);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_ANDESITE = createFallingBlocks(Blocks.CRUSHED_ANDESITE, SoundType.STONE);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_DIORITE = createFallingBlocks(Blocks.CRUSHED_DIORITE, SoundType.STONE);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_GRANITE = createFallingBlocks(Blocks.CRUSHED_GRANITE, SoundType.STONE);
    public static final RegistryObject<EndCakeBlock> END_CAKE = BLOCKS
            .register(Blocks.END_CAKE, EndCakeBlock::new);
    public static final RegistryObject<FlowingFluidBlock> WITCH_WATER = BLOCKS
            .register(Blocks.WITCH_WATER, WitchWaterBlock::new);
    public static final RegistryObject<FlowingFluidBlock> SEA_WATER = BLOCKS
            .register(Blocks.SEA_WATER, SeaWaterBlock::new);
    public static final RegistryObject<BaseBlock> INFESTING_LEAVES = BLOCKS
            .register(Blocks.INFESTING_LEAVES, InfestingLeavesBlock::new);
    public static final RegistryObject<BaseBlock> INFESTED_LEAVES = BLOCKS
            .register(Blocks.INFESTED_LEAVES, InfestedLeavesBlock::new);
    //TODO here are the sieves
    public static final RegistryObject<BlockSieve> SIEVE_ACACIA = createWoodSieve(Blocks.SIEVE_ACACIA);
    public static final RegistryObject<BlockSieve> SIEVE_BIRCH = createWoodSieve(Blocks.SIEVE_BIRCH);
    public static final RegistryObject<BlockSieve> SIEVE_DARK_OAK = createWoodSieve(Blocks.SIEVE_DARK_OAK);
    public static final RegistryObject<BlockSieve> SIEVE_JUNGLE = createWoodSieve(Blocks.SIEVE_JUNGLE);
    public static final RegistryObject<BlockSieve> SIEVE_SPRUCE = createWoodSieve(Blocks.SIEVE_SPRUCE);
    public static final RegistryObject<BlockSieve> SIEVE_OAK = createWoodSieve(Blocks.SIEVE_OAK);
    public static final RegistryObject<BlockSieve> SIEVE_CRIMSON = createNetherSieve(Blocks.SIEVE_CRIMSON);
    public static final RegistryObject<BlockSieve> SIEVE_WARPED = createNetherSieve(Blocks.SIEVE_WARPED);
    //TODO added Crucible
    //Wood
    public static final RegistryObject<BaseBlock> CRUCIBLE_ACACIA = createWoodCrucible(Blocks.CRUCIBLE_ACACIA);
    public static final RegistryObject<BaseBlock> CRUCIBLE_BIRCH = createWoodCrucible(Blocks.CRUCIBLE_BIRCH);
    public static final RegistryObject<BaseBlock> CRUCIBLE_DARK_OAK = createWoodCrucible(Blocks.CRUCIBLE_DARK_OAK);
    public static final RegistryObject<BaseBlock> CRUCIBLE_JUNGLE = createWoodCrucible(Blocks.CRUCIBLE_JUNGLE);
    public static final RegistryObject<BaseBlock> CRUCIBLE_SPRUCE = createWoodCrucible(Blocks.CRUCIBLE_SPRUCE);
    public static final RegistryObject<BaseBlock> CRUCIBLE_OAK = createWoodCrucible(Blocks.CRUCIBLE_OAK);
    //Fired
    public static final RegistryObject<BaseBlock> CRUCIBLE_CRIMSON = createNetherCrucible(Blocks.CRUCIBLE_CRIMSON);
    public static final RegistryObject<BaseBlock> CRUCIBLE_WARPED = createNetherCrucible(Blocks.CRUCIBLE_WARPED);
    public static final RegistryObject<BaseBlock> CRUCIBLE_UNFIRED = BLOCKS
            .register(Blocks.CRUCIBLE_UNFIRED, UnfiredCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_FIRED = BLOCKS
            .register(Blocks.CRUCIBLE_FIRED, FiredCrucibleBlock::new);
    //TODO here are the barrels
    //Wood
    public static final RegistryObject<BaseBlock> BARREL_ACACIA = createWoodBarrel(Blocks.BARREL_ACACIA);
    public static final RegistryObject<BaseBlock> BARREL_BIRCH = createWoodBarrel(Blocks.BARREL_BIRCH);
    public static final RegistryObject<BaseBlock> BARREL_DARK_OAK = createWoodBarrel(Blocks.BARREL_DARK_OAK);
    public static final RegistryObject<BaseBlock> BARREL_JUNGLE = createWoodBarrel(Blocks.BARREL_JUNGLE);
    public static final RegistryObject<BaseBlock> BARREL_SPRUCE = createWoodBarrel(Blocks.BARREL_SPRUCE);
    public static final RegistryObject<BaseBlock> BARREL_OAK = createWoodBarrel(Blocks.BARREL_OAK);
    //Fired
    public static final RegistryObject<BaseBlock> BARREL_STONE = createStoneBarrel(Blocks.BARREL_STONE);
    public static final RegistryObject<BaseBlock> BARREL_CRIMSON = createNetherBarrel(Blocks.BARREL_CRIMSON);
    public static final RegistryObject<BaseBlock> BARREL_WARPED = createNetherBarrel(Blocks.BARREL_WARPED);
    //Glass
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


    private ExNihiloBlocks() {

    }

    public static void init(IEventBus modEventBus) {
        logger.debug("Register blocks");
        BLOCKS.register(modEventBus);
    }

    private static RegistryObject<BaseFallingBlock> createFallingBlocks(String block, SoundType sound) {
        return BLOCKS.register(block, () -> new BaseFallingBlock(new BlockBuilder()
                        .properties(AbstractBlock.Properties.of(Material.SAND).strength(0.7F)
                                .sound(sound)).harvestLevel(ToolType.SHOVEL, 0)));
    }

    protected static RegistryObject<BaseBlock> createWoodCrucible(String crucible) {
        return createCrucible(crucible, Material.WOOD, SoundType.WOOD);
    }
    protected static RegistryObject<BaseBlock> createNetherCrucible(String crucible) {
        return createCrucible(crucible, Material.NETHER_WOOD, SoundType.STEM);
    }
    private static RegistryObject<BaseBlock> createCrucible(String crucible, Material material, SoundType sound) {
        return BLOCKS.register(crucible,
                () -> new CrucibleBaseBlock(new BlockBuilder().properties(
                        AbstractBlock.Properties.of(material).strength(.75F)
                                .sound(sound).noOcclusion()).harvestLevel(ToolType.AXE, 0)
                        .tileEntitySupplier(WoodCrucibleTile::new)));
    }

    protected static RegistryObject<BlockSieve> createWoodSieve(String sieve) {
        return createSieve(sieve, Material.WOOD, SoundType.WOOD);
    }

    protected static RegistryObject<BlockSieve> createNetherSieve(String sieve) {
        return createSieve(sieve, Material.NETHER_WOOD, SoundType.STEM);
    }
    private static RegistryObject<BlockSieve> createSieve(String sieve, Material material, SoundType sound) {
        return BLOCKS.register(sieve, () -> new BlockSieve(new BlockBuilder().properties(
                        AbstractBlock.Properties.of(material).strength(0.7F)
                                .sound(sound).noOcclusion().isRedstoneConductor((state, reader, pos) -> false)
                                .isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false))
                        .harvestLevel(ToolType.AXE, 0).tileEntitySupplier(SieveTile::new)));
    }

    protected static RegistryObject<BaseBlock> createWoodBarrel(String barrel) {
        return BLOCKS.register(barrel, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                        .properties(AbstractBlock.Properties.of(Material.WOOD).strength(0.75F)
                                .sound(SoundType.WOOD))
                        .tileEntitySupplier(WoodBarrelTile::new)));
    }

    protected static RegistryObject<BaseBlock> createNetherBarrel(String barrel) {
        return createStoneBarrels(barrel, ToolType.AXE, Material.NETHER_WOOD, SoundType.STEM);
    }

    protected static RegistryObject<BaseBlock> createGlassBarrel(String barrel) {
        //return createStoneBarrels(barrel, ToolType.PICKAXE, Material.GLASS, SoundType.GLASS);
        return BLOCKS.register(barrel,
                () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.PICKAXE, 0)
                        .properties(AbstractBlock.Properties.of(Material.GLASS).strength(0.75F).sound(SoundType.GLASS))
                        .tileEntitySupplier(GlassBarrelTile::new)));
    }

    protected static RegistryObject<BaseBlock> createStoneBarrel(String barrel) {
        return createStoneBarrels(barrel, ToolType.PICKAXE, Material.STONE, SoundType.STONE);
    }

    private static RegistryObject<BaseBlock> createStoneBarrels(String barrel, ToolType tool, Material material, SoundType sound) {
        return BLOCKS.register(barrel,
                () -> new BlockBarrel(new BlockBuilder().harvestLevel(tool, 0)
                        .properties(AbstractBlock.Properties.of(material).strength(0.75F).sound(sound))
                        .tileEntitySupplier(StoneBarrelTile::new)));
    }
}
