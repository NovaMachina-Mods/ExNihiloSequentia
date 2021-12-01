package novamachina.exnihilosequentia.common.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.tileentity.InfestedLeavesTile;
import novamachina.exnihilosequentia.common.tileentity.InfestingLeavesTile;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.WoodBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;
import novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleTile;
import novamachina.exnihilosequentia.api.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloTiles {
    private static final DeferredRegister<BlockEntityType<?>> TILES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    public static final RegistryObject<BlockEntityType<FiredCrucibleTile>> CRUCIBLE_FIRED = TILES
            .register(Blocks.FIRED_CRUCIBLE, () -> BlockEntityType.Builder
                    .of(FiredCrucibleTile::new, ExNihiloBlocks.FIRED_CRUCIBLE.get(), ExNihiloBlocks.CRIMSON_CRUCIBLE.get(),
                            ExNihiloBlocks.WARPED_CRUCIBLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<WoodCrucibleTile>> CRUCIBLE_WOOD = TILES
            .register(Blocks.CRUCIBLES, () -> BlockEntityType.Builder
                    .of(WoodCrucibleTile::new, ExNihiloBlocks.ACACIA_CRUCIBLE.get(), ExNihiloBlocks.BIRCH_CRUCIBLE.get(),
                            ExNihiloBlocks.DARK_OAK_CRUCIBLE.get(), ExNihiloBlocks.JUNGLE_CRUCIBLE.get(),
                            ExNihiloBlocks.OAK_CRUCIBLE.get(), ExNihiloBlocks.SPRUCE_CRUCIBLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<SieveTile>> SIEVE = TILES
            .register(Blocks.SIEVES, () -> BlockEntityType.Builder
                    .of(SieveTile::new, ExNihiloBlocks.ACACIA_SIEVE.get(), ExNihiloBlocks.BIRCH_SIEVE.get(),
                            ExNihiloBlocks.DARK_OAK_SIEVE.get(), ExNihiloBlocks.JUNGLE_SIEVE.get(),
                            ExNihiloBlocks.OAK_SIEVE.get(), ExNihiloBlocks.SPRUCE_SIEVE.get(),
                            ExNihiloBlocks.CRIMSON_SIEVE.get(), ExNihiloBlocks.WARPED_SIEVE.get()).build(null));
    public static final RegistryObject<BlockEntityType<InfestingLeavesTile>> INFESTING_LEAVES = TILES
            .register(Blocks.INFESTING_LEAVES, () -> BlockEntityType.Builder
                    .of(InfestingLeavesTile::new, ExNihiloBlocks.INFESTING_LEAVES.get()).build(null));
    public static final RegistryObject<BlockEntityType<InfestedLeavesTile>> INFESTED_LEAVES = TILES
            .register(Blocks.INFESTED_LEAVES, () -> BlockEntityType.Builder
                    .of(InfestedLeavesTile::new, ExNihiloBlocks.INFESTED_LEAVES.get()).build(null));
    public static final RegistryObject<BlockEntityType<? extends AbstractBarrelTile>> BARREL_WOOD = TILES
            .register(Blocks.BARRELS, () -> BlockEntityType.Builder
                    .of(WoodBarrelTile::new, ExNihiloBlocks.ACACIA_BARREL.get(), ExNihiloBlocks.BIRCH_BARREL.get(),
                            ExNihiloBlocks.DARK_OAK_BARREL.get(), ExNihiloBlocks.JUNGLE_BARREL.get(),
                            ExNihiloBlocks.OAK_BARREL.get(), ExNihiloBlocks.SPRUCE_BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<? extends AbstractBarrelTile>> BARREL_STONE = TILES
            .register(Blocks.STONE_BARREL, () -> BlockEntityType.Builder
                    .of(StoneBarrelTile::new, ExNihiloBlocks.STONE_BARREL.get(), ExNihiloBlocks.CRIMSON_BARREL.get(),
                            ExNihiloBlocks.WARPED_BARREL.get()).build(null));
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ExNihiloTiles() {
    }

    public static void init(IEventBus eventBus) {
        logger.debug("Register Tile Entities");
        TILES.register(eventBus);
    }
}
