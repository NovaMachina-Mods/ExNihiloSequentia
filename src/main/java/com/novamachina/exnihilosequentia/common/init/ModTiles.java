package com.novamachina.exnihilosequentia.common.init;

import com.novamachina.exnihilosequentia.common.tileentity.InfestedLeavesTile;
import com.novamachina.exnihilosequentia.common.tileentity.InfestingLeavesTile;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.BarrelTile;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleTile;
import com.novamachina.exnihilosequentia.common.tileentity.sieve.SieveTile;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.Constants.Blocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTiles {

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
    public static final RegistryObject<TileEntityType<BarrelTile>> BARREL = TILES
        .register(Blocks.BARREL_WOOD, () -> TileEntityType.Builder
            .create(BarrelTile::new, ModBlocks.BARREL_WOOD.get()).build(null));

    public static void init(IEventBus eventBus) {
        TILES.register(eventBus);
    }
}
