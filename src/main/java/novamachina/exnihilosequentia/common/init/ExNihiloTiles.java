package novamachina.exnihilosequentia.common.init;

import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.tileentity.InfestedLeavesTile;
import novamachina.exnihilosequentia.common.tileentity.InfestingLeavesTile;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.WoodBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;
import novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloTiles {
    private static final DeferredRegister<EntityType<?>> TILES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    public static final RegistryObject<EntityType<FiredCrucibleTile>> CRUCIBLE_FIRED = TILES
            .register(Blocks.FIRED_CRUCIBLE, () -> EntityType.Builder
                    .of(FiredCrucibleTile::new, ExNihiloBlocks.CRUCIBLE_FIRED.get(), ExNihiloBlocks.CRUCIBLE_CRIMSON.get(),
                            ExNihiloBlocks.CRUCIBLE_WARPED.get()).build(null));
    public static final RegistryObject<EntityType<WoodCrucibleTile>> CRUCIBLE_WOOD = TILES
            .register(Blocks.CRUCIBLES, () -> EntityType.Builder
                    .of(WoodCrucibleTile::new, ExNihiloBlocks.CRUCIBLE_ACACIA.get(), ExNihiloBlocks.CRUCIBLE_BIRCH.get(),
                            ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(), ExNihiloBlocks.CRUCIBLE_JUNGLE.get(),
                            ExNihiloBlocks.CRUCIBLE_OAK.get(), ExNihiloBlocks.CRUCIBLE_SPRUCE.get()).build(null));
    public static final RegistryObject<EntityType<SieveTile>> SIEVE = TILES
            .register(Blocks.SIEVES, () -> EntityType.Builder
                    .of(SieveTile::new, ExNihiloBlocks.SIEVE_ACACIA.get(), ExNihiloBlocks.SIEVE_BIRCH.get(),
                            ExNihiloBlocks.SIEVE_DARK_OAK.get(), ExNihiloBlocks.SIEVE_JUNGLE.get(),
                            ExNihiloBlocks.SIEVE_OAK.get(), ExNihiloBlocks.SIEVE_SPRUCE.get(),
                            ExNihiloBlocks.SIEVE_CRIMSON.get(), ExNihiloBlocks.SIEVE_WARPED.get()).build(null));
    public static final RegistryObject<EntityType<InfestingLeavesTile>> INFESTING_LEAVES = TILES
            .register(Blocks.INFESTING_LEAVES, () -> EntityType.Builder
                    .of(InfestingLeavesTile::new, ExNihiloBlocks.INFESTING_LEAVES.get()).build(null));
    public static final RegistryObject<EntityType<InfestedLeavesTile>> INFESTED_LEAVES = TILES
            .register(Blocks.INFESTED_LEAVES, () -> EntityType.Builder
                    .of(InfestedLeavesTile::new, ExNihiloBlocks.INFESTED_LEAVES.get()).build(null));
    public static final RegistryObject<EntityType<? extends AbstractBarrelTile>> BARREL_WOOD = TILES
            .register(Blocks.BARRELS, () -> EntityType.Builder
                    .of(WoodBarrelTile::new, ExNihiloBlocks.BARREL_ACACIA.get(), ExNihiloBlocks.BARREL_BIRCH.get(),
                            ExNihiloBlocks.BARREL_DARK_OAK.get(), ExNihiloBlocks.BARREL_JUNGLE.get(),
                            ExNihiloBlocks.BARREL_OAK.get(), ExNihiloBlocks.BARREL_SPRUCE.get()).build(null));
    public static final RegistryObject<EntityType<? extends AbstractBarrelTile>> BARREL_STONE = TILES
            .register(Blocks.STONE_BARREL, () -> EntityType.Builder
                    .of(StoneBarrelTile::new, ExNihiloBlocks.BARREL_STONE.get(), ExNihiloBlocks.BARREL_CRIMSON.get(),
                            ExNihiloBlocks.BARREL_WARPED.get()).build(null));
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ExNihiloTiles() {
    }

    public static void init(IEventBus eventBus) {
        logger.debug("Register Tile Entities");
        TILES.register(eventBus);
    }
}
