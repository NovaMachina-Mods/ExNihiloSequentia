package novamachina.exnihilosequentia.common.init;

import novamachina.exnihilosequentia.common.tileentity.InfestedLeavesTile;
import novamachina.exnihilosequentia.common.tileentity.InfestingLeavesTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.WoodBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;
import novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleTile;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.utility.Constants;
import novamachina.exnihilosequentia.common.utility.Constants.Blocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ModTiles {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    private static final DeferredRegister<TileEntityType<?>> TILES =
        DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Constants.ModIds.EX_NIHILO_SEQUENTIA);
    public static final RegistryObject<TileEntityType<FiredCrucibleTile>> CRUCIBLE_FIRED = TILES
        .register(Blocks.CRUCIBLE_UNFIRED, () -> TileEntityType.Builder
            .create(FiredCrucibleTile::new, ModBlocks.CRUCIBLE_FIRED.get()).build(null));
    public static final RegistryObject<TileEntityType<WoodCrucibleTile>> CRUCIBLE_WOOD = TILES
        .register(Blocks.CRUCIBLE_WOOD, () -> TileEntityType.Builder
            .create(WoodCrucibleTile::new, ModBlocks.CRUCIBLE_WOOD.get()).build(null));
    public static final RegistryObject<TileEntityType<SieveTile>> SIEVE = TILES
        .register(Blocks.SIEVE, () -> TileEntityType.Builder
            .create(SieveTile::new, ModBlocks.SIEVE.get()).build(null));
    public static final RegistryObject<TileEntityType<InfestingLeavesTile>> INFESTING_LEAVES = TILES
        .register(Blocks.INFESTING_LEAVES, () -> TileEntityType.Builder
            .create(InfestingLeavesTile::new, ModBlocks.INFESTING_LEAVES.get()).build(null));
    public static final RegistryObject<TileEntityType<InfestedLeavesTile>> INFESTED_LEAVES = TILES
        .register(Blocks.INFESTED_LEAVES, () -> TileEntityType.Builder
            .create(InfestedLeavesTile::new, ModBlocks.INFESTED_LEAVES.get()).build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARREL_WOOD = TILES
        .register(Blocks.BARREL_WOOD, () -> TileEntityType.Builder
            .create(WoodBarrelTile::new, ModBlocks.BARREL_WOOD.get()).build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARREL_STONE = TILES
        .register(Blocks.BARREL_STONE, () -> TileEntityType.Builder
            .create(StoneBarrelTile::new, ModBlocks.BARREL_STONE.get()).build(null));
    private ModTiles() {
    }

    public static void init(IEventBus eventBus) {
        logger.debug("Register Tile Entities");
        TILES.register(eventBus);
    }
}
