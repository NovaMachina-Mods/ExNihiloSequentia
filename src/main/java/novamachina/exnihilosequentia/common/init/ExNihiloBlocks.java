package novamachina.exnihilosequentia.common.init;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.creation.AbstractBlockCreation;
import novamachina.exnihilosequentia.common.block.BaseBlock;
import novamachina.exnihilosequentia.common.block.BaseFallingBlock;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.block.EndCakeBlock;
import novamachina.exnihilosequentia.common.block.InfestedLeavesBlock;
import novamachina.exnihilosequentia.common.block.InfestingLeavesBlock;
import novamachina.exnihilosequentia.common.block.SeaWaterBlock;
import novamachina.exnihilosequentia.common.block.UnfiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.WitchWaterBlock;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloBlocks extends AbstractBlockCreation {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    //Misc
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

    //Falling Base Blocks
    public static final RegistryObject<BaseFallingBlock> DUST = createFallingBlock(
            Blocks.DUST, Material.SAND, 0.7F, SoundType.WOOL, ToolType.SHOVEL);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_NETHERRACK = createFallingBlock(
            Blocks.CRUSHED_NETHERRACK, Material.SAND, 0.7F, SoundType.GRAVEL, ToolType.SHOVEL);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_END_STONE = createFallingBlock(
            Blocks.CRUSHED_END_STONE, Material.SAND, 0.7F, SoundType.GRAVEL, ToolType.SHOVEL);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_ANDESITE = createFallingBlock(
            Blocks.CRUSHED_ANDESITE, Material.SAND, 0.7F, SoundType.GRAVEL, ToolType.SHOVEL);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_DIORITE = createFallingBlock(
            Blocks.CRUSHED_DIORITE, Material.SAND, 0.7F, SoundType.GRAVEL, ToolType.SHOVEL);
    public static final RegistryObject<BaseFallingBlock> CRUSHED_GRANITE = createFallingBlock(
            Blocks.CRUSHED_GRANITE, Material.SAND, 0.7F, SoundType.GRAVEL, ToolType.SHOVEL);
    //Sieves
    public static final RegistryObject<BlockSieve> SIEVE = createSieve(
            Blocks.SIEVE, Material.WOOD, SoundType.WOOD);
    public static final RegistryObject<BaseBlock> CRUCIBLE_FIRED = createFiredCrucibleBlock(
            Blocks.CRUCIBLE_FIRED, Material.STONE, 1.5F, SoundType.STONE, ToolType.PICKAXE);
    public static final RegistryObject<BaseBlock> CRUCIBLE_WOOD = createWoodenCrucibleBlock(
            Blocks.CRUCIBLE_WOOD, Material.WOOD, 0.75F, SoundType.WOOD);
    public static final RegistryObject<BaseBlock> BARREL_WOOD = createWoodenBarrelBlock(
            Blocks.BARREL_WOOD, Material.WOOD, 0.75F, SoundType.WOOD);
    public static final RegistryObject<BaseBlock> BARREL_STONE = createStoneBarrelBlock(
            Blocks.BARREL_STONE, Material.STONE, 0.75F, SoundType.STONE);

    private ExNihiloBlocks() {
    }

    public static void init(IEventBus modEventBus) {
        logger.debug("Register blocks");
        BLOCKS.register(modEventBus);
    }
}
