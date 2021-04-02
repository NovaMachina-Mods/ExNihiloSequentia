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
    public static final RegistryObject<TileEntityType<FiredCrucibleTile>> CRUCIBLES_FIRED = TILES
            .register("crucibles_fired", () -> TileEntityType.Builder.of(
                    FiredCrucibleTile::new, ExNihiloBlocks.CRUCIBLE_FIRED.get())
                    .build(null));
    public static final RegistryObject<TileEntityType<WoodCrucibleTile>> CRUCIBLES_WOOD = TILES
            .register("crucibles_wooden", () -> TileEntityType.Builder.of(
                    WoodCrucibleTile::new, ExNihiloBlocks.CRUCIBLE_OAK.get(), ExNihiloBlocks.CRUCIBLE_ACACIA.get(), ExNihiloBlocks.CRUCIBLE_BIRCH.get(),
                    ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(), ExNihiloBlocks.CRUCIBLE_JUNGLE.get(), ExNihiloBlocks.CRUCIBLE_SPRUCE.get(),
                    ExNihiloBlocks.CRUCIBLE_CRIMSON.get(), ExNihiloBlocks.CRUCIBLE_WARPED.get())
                    .build(null));
    public static final RegistryObject<TileEntityType<SieveTile>> SIEVES = TILES
            .register(Blocks.SIEVE_OAK, () -> TileEntityType.Builder.of(
                    SieveTile::new, ExNihiloBlocks.SIEVE_OAK.get(), ExNihiloBlocks.SIEVE_BIRCH.get(), ExNihiloBlocks.SIEVE_DARK_OAK.get(),
                    ExNihiloBlocks.SIEVE_ACACIA.get(), ExNihiloBlocks.SIEVE_SPRUCE.get(), ExNihiloBlocks.SIEVE_CRIMSON.get(),
                    ExNihiloBlocks.SIEVE_JUNGLE.get(), ExNihiloBlocks.SIEVE_WARPED.get())
                    .build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARRELS_WOOD = TILES
            .register(Blocks.BARREL_OAK, () -> TileEntityType.Builder.of(
                    WoodBarrelTile::new, ExNihiloBlocks.BARREL_OAK.get(), ExNihiloBlocks.BARREL_ACACIA.get(), ExNihiloBlocks.BARREL_BIRCH.get(),
                    ExNihiloBlocks.BARREL_DARK_OAK.get(), ExNihiloBlocks.BARREL_JUNGLE.get(), ExNihiloBlocks.BARREL_SPRUCE.get())
                    .build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARRELS_STONE = TILES
            .register(Blocks.BARREL_STONE, () -> TileEntityType.Builder.of(
                    StoneBarrelTile::new, ExNihiloBlocks.BARREL_STONE.get(), ExNihiloBlocks.BARREL_CRIMSON.get(), ExNihiloBlocks.BARREL_WARPED.get())
                    .build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARRELS_GLASS = TILES
            .register(Blocks.BARREL_GLASS, () -> TileEntityType.Builder.of(
                    GlassBarrelTile::new, ExNihiloBlocks.BARREL_GLASS.get(), ExNihiloBlocks.BARREL_GLASS_BLACK.get(),
                    ExNihiloBlocks.BARREL_GLASS_BLUE.get(), ExNihiloBlocks.BARREL_GLASS_BROWN.get(), ExNihiloBlocks.BARREL_GLASS_CYAN.get(),
                    ExNihiloBlocks.BARREL_GLASS_GRAY.get(), ExNihiloBlocks.BARREL_GLASS_GREEN.get(), ExNihiloBlocks.BARREL_GLASS_LIGHT_BLUE.get(),
                    ExNihiloBlocks.BARREL_GLASS_LIGHT_GRAY.get(), ExNihiloBlocks.BARREL_GLASS_LIME.get(), ExNihiloBlocks.BARREL_GLASS_MAGENTA.get(),
                    ExNihiloBlocks.BARREL_GLASS_ORANGE.get(), ExNihiloBlocks.BARREL_GLASS_PINK.get(), ExNihiloBlocks.BARREL_GLASS_PURPLE.get(),
                    ExNihiloBlocks.BARREL_GLASS_RED.get(), ExNihiloBlocks.BARREL_GLASS_WHITE.get(), ExNihiloBlocks.BARREL_GLASS_YELLOW.get())
                    .build(null));
    public static final RegistryObject<TileEntityType<InfestingLeavesTile>> INFESTING_LEAVES = TILES
            .register(Blocks.INFESTING_LEAVES, () -> TileEntityType.Builder
                    .of(InfestingLeavesTile::new, ExNihiloBlocks.INFESTING_LEAVES.get()).build(null));
    public static final RegistryObject<TileEntityType<InfestedLeavesTile>> INFESTED_LEAVES = TILES
            .register(Blocks.INFESTED_LEAVES, () -> TileEntityType.Builder
                    .of(InfestedLeavesTile::new, ExNihiloBlocks.INFESTED_LEAVES.get()).build(null));
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ExNihiloTiles() {
    }

    public static void init(IEventBus eventBus) {
        logger.debug("Register Tile Entities");
        TILES.register(eventBus);
    }
}
