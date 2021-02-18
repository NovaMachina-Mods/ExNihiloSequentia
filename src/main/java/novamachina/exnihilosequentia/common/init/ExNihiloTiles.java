package novamachina.exnihilosequentia.common.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.tileentity.InfestedLeavesTile;
import novamachina.exnihilosequentia.common.tileentity.InfestingLeavesTile;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.*;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;
import novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloTiles {
    private static final DeferredRegister<TileEntityType<?>> TILES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    public static final RegistryObject<TileEntityType<FiredCrucibleTile>> CRUCIBLE_FIRED = TILES
            .register(Blocks.CRUCIBLE_UNFIRED, () -> TileEntityType.Builder
                    .create(FiredCrucibleTile::new, ExNihiloBlocks.CRUCIBLE_FIRED.get()).build(null));
    public static final RegistryObject<TileEntityType<WoodCrucibleTile>> CRUCIBLE_WOOD = TILES
            .register(Blocks.CRUCIBLE_WOOD, () -> TileEntityType.Builder
                    .create(WoodCrucibleTile::new, ExNihiloBlocks.CRUCIBLE_WOOD.get()).build(null));
    public static final RegistryObject<TileEntityType<SieveTile>> SIEVE = TILES
            .register(Blocks.SIEVE, () -> TileEntityType.Builder
                    .create(SieveTile::new, ExNihiloBlocks.SIEVE.get()).build(null));
    public static final RegistryObject<TileEntityType<InfestingLeavesTile>> INFESTING_LEAVES = TILES
            .register(Blocks.INFESTING_LEAVES, () -> TileEntityType.Builder
                    .create(InfestingLeavesTile::new, ExNihiloBlocks.INFESTING_LEAVES.get()).build(null));
    public static final RegistryObject<TileEntityType<InfestedLeavesTile>> INFESTED_LEAVES = TILES
            .register(Blocks.INFESTED_LEAVES, () -> TileEntityType.Builder
                    .create(InfestedLeavesTile::new, ExNihiloBlocks.INFESTED_LEAVES.get()).build(null));
    //TODO Able to Simplify???
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARREL_ACACIA = TILES
            .register(Blocks.BARREL_ACACIA, () -> TileEntityType.Builder
                    .create(AcaciaBarrelTile::new, ExNihiloBlocks.BARREL_ACACIA.get()).build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARREL_BIRCH = TILES
            .register(Blocks.BARREL_BIRCH, () -> TileEntityType.Builder
                    .create(BirchBarrelTile::new, ExNihiloBlocks.BARREL_BIRCH.get()).build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARREL_DARK_OAK = TILES
            .register(Blocks.BARREL_DARK_OAK, () -> TileEntityType.Builder
                    .create(DarkOakBarrelTile::new, ExNihiloBlocks.BARREL_DARK_OAK.get()).build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARREL_JUNGLE = TILES
            .register(Blocks.BARREL_JUNGLE, () -> TileEntityType.Builder
                    .create(JungleBarrelTile::new, ExNihiloBlocks.BARREL_JUNGLE.get()).build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARREL_SPRUCE = TILES
            .register(Blocks.BARREL_SPRUCE, () -> TileEntityType.Builder
                    .create(SpruceBarrelTile::new, ExNihiloBlocks.BARREL_SPRUCE.get()).build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARREL_OAK = TILES
            .register(Blocks.BARREL_OAK, () -> TileEntityType.Builder
                    .create(OakBarrelTile::new, ExNihiloBlocks.BARREL_OAK.get()).build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARREL_STONE = TILES
            .register(Blocks.BARREL_STONE, () -> TileEntityType.Builder
                    .create(StoneBarrelTile::new, ExNihiloBlocks.BARREL_STONE.get()).build(null));
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ExNihiloTiles() {
    }

    public static void init(IEventBus eventBus) {
        logger.debug("Register Tile Entities");
        TILES.register(eventBus);
    }
}
