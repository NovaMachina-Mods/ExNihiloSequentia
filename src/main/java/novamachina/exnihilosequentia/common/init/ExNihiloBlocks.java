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
import novamachina.exnihilosequentia.common.block.BaseBlock;
import novamachina.exnihilosequentia.common.block.BaseFallingBlock;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.block.EndCakeBlock;
import novamachina.exnihilosequentia.common.block.FiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.InfestedLeavesBlock;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.block.SeaWaterBlock;
import novamachina.exnihilosequentia.common.block.UnfiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.WitchWaterBlock;
import novamachina.exnihilosequentia.common.block.WoodCrucibleBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.barrel.*;
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
    public static final RegistryObject<BaseFallingBlock> CRUSHED_NETHERRACK = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_NETHERRACK, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(AbstractBlock.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                            .sound(SoundType.GROUND)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_END_STONE = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_END_STONE, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(AbstractBlock.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                            .sound(SoundType.GROUND)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_ANDESITE = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_ANDESITE, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(AbstractBlock.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                            .sound(SoundType.GROUND)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_DIORITE = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_DIORITE, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(AbstractBlock.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                            .sound(SoundType.GROUND)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_GRANITE = BLOCKS
            .register(ExNihiloConstants.Blocks.CRUSHED_GRANITE, () -> new BaseFallingBlock(new BlockBuilder()
                    .properties(AbstractBlock.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                            .sound(SoundType.GROUND)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<EndCakeBlock> END_CAKE = BLOCKS
            .register(ExNihiloConstants.Blocks.END_CAKE, EndCakeBlock::new);
    //TODO here are the sieves
    public static final RegistryObject<BlockSieve> SIEVE_ACACIA = BLOCKS
            .register(Blocks.SIEVE_ACACIA, BlockSieve::new);
    public static final RegistryObject<BlockSieve> SIEVE_BIRCH = BLOCKS
            .register(Blocks.SIEVE_BIRCH, BlockSieve::new);
    public static final RegistryObject<BlockSieve> SIEVE_DARK_OAK = BLOCKS
            .register(Blocks.SIEVE_DARK_OAK, BlockSieve::new);
    public static final RegistryObject<BlockSieve> SIEVE_JUNGLE = BLOCKS
            .register(Blocks.SIEVE_JUNGLE, BlockSieve::new);
    public static final RegistryObject<BlockSieve> SIEVE_SPRUCE = BLOCKS
            .register(Blocks.SIEVE_SPRUCE, BlockSieve::new);
    public static final RegistryObject<BlockSieve> SIEVE_OAK = BLOCKS
            .register(Blocks.SIEVE_OAK, BlockSieve::new);
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
    //TODO added Crucible
    public static final RegistryObject<BaseBlock> CRUCIBLE_ACACIA = BLOCKS
            .register(Blocks.CRUCIBLE_ACACIA, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_BIRCH = BLOCKS
            .register(Blocks.CRUCIBLE_BIRCH, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_DARK_OAK = BLOCKS
            .register(Blocks.CRUCIBLE_DARK_OAK, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_JUNGLE = BLOCKS
            .register(Blocks.CRUCIBLE_JUNGLE, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_SPRUCE = BLOCKS
            .register(Blocks.CRUCIBLE_SPRUCE, WoodCrucibleBlock::new);
    public static final RegistryObject<BaseBlock> CRUCIBLE_OAK = BLOCKS
            .register(Blocks.CRUCIBLE_OAK, WoodCrucibleBlock::new);
    //TODO here are the barrels
    public static final RegistryObject<BaseBlock> BARREL_ACACIA = BLOCKS
            .register(Blocks.BARREL_ACACIA, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                    .properties(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.75F).sound(SoundType.WOOD))
                    .tileEntitySupplier(WoodBarrelTile::new)));
    public static final RegistryObject<BaseBlock> BARREL_BIRCH = BLOCKS
            .register(Blocks.BARREL_BIRCH, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                    .properties(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.75F).sound(SoundType.WOOD))
                    .tileEntitySupplier(WoodBarrelTile::new)));
    public static final RegistryObject<BaseBlock> BARREL_DARK_OAK = BLOCKS
            .register(Blocks.BARREL_DARK_OAK, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                    .properties(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.75F).sound(SoundType.WOOD))
                    .tileEntitySupplier(WoodBarrelTile::new)));
    public static final RegistryObject<BaseBlock> BARREL_JUNGLE = BLOCKS
            .register(Blocks.BARREL_JUNGLE, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                    .properties(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.75F).sound(SoundType.WOOD))
                    .tileEntitySupplier(WoodBarrelTile::new)));
    public static final RegistryObject<BaseBlock> BARREL_SPRUCE = BLOCKS
            .register(Blocks.BARREL_SPRUCE, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                    .properties(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.75F).sound(SoundType.WOOD))
                    .tileEntitySupplier(WoodBarrelTile::new)));
    public static final RegistryObject<BaseBlock> BARREL_OAK = BLOCKS
            .register(Blocks.BARREL_OAK, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                    .properties(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.75F).sound(SoundType.WOOD))
                    .tileEntitySupplier(WoodBarrelTile::new)));
    public static final RegistryObject<BaseBlock> BARREL_STONE = BLOCKS
            .register(Blocks.BARREL_STONE, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.PICKAXE, 0)
                    .properties(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(0.75F).sound(SoundType.STONE))
                    .tileEntitySupplier(StoneBarrelTile::new)));
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ExNihiloBlocks() {

    }

    public static void init(IEventBus modEventBus) {
        logger.debug("Register blocks");
        BLOCKS.register(modEventBus);
    }
}
