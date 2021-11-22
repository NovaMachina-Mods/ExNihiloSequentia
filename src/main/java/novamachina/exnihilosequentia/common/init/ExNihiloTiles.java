package novamachina.exnihilosequentia.common.init;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.block.barrels.NetherBarrelBlock;
import novamachina.exnihilosequentia.common.block.barrels.StoneBarrelBlock;
import novamachina.exnihilosequentia.common.block.barrels.WoodBarrelBlock;
import novamachina.exnihilosequentia.common.block.crucibles.FiredCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.NetherCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.WoodCrucibleBlock;
import novamachina.exnihilosequentia.common.block.sieves.WoodSieveBlock;
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

import java.util.ArrayList;
import java.util.List;

public class ExNihiloTiles {
    private static final DeferredRegister<TileEntityType<?>> TILES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    public static final RegistryObject<TileEntityType<FiredCrucibleTile>> CRUCIBLE_FIRED = TILES
            .register(Blocks.CRUCIBLE_FIRED, () -> TileEntityType.Builder
                    .of(FiredCrucibleTile::new, getFiredCrucibles()).build(null));
    public static final RegistryObject<TileEntityType<WoodCrucibleTile>> CRUCIBLE_WOOD = TILES
            .register(Blocks.CRUCIBLES, () -> TileEntityType.Builder
                    .of(WoodCrucibleTile::new, getWoodCrucibles()).build(null));
    public static final RegistryObject<TileEntityType<SieveTile>> SIEVE = TILES
            .register(Blocks.SIEVE_OAK, () -> TileEntityType.Builder
                    .of(SieveTile::new, getSieves()).build(null));
    public static final RegistryObject<TileEntityType<InfestingLeavesTile>> INFESTING_LEAVES = TILES
            .register(Blocks.INFESTING_LEAVES, () -> TileEntityType.Builder
                    .of(InfestingLeavesTile::new, ExNihiloBlocks.INFESTING_LEAVES.get()).build(null));
    public static final RegistryObject<TileEntityType<InfestedLeavesTile>> INFESTED_LEAVES = TILES
            .register(Blocks.INFESTED_LEAVES, () -> TileEntityType.Builder
                    .of(InfestedLeavesTile::new, ExNihiloBlocks.INFESTED_LEAVES.get()).build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARREL_WOOD = TILES
            .register(Blocks.BARRELS, () -> TileEntityType.Builder
                    .of(WoodBarrelTile::new, getWoodBarrels()).build(null));
    public static final RegistryObject<TileEntityType<? extends AbstractBarrelTile>> BARREL_STONE = TILES
            .register(Blocks.BARREL_STONE, () -> TileEntityType.Builder
                    .of(StoneBarrelTile::new, getStoneBarrels()).build(null));
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ExNihiloTiles() {
    }

    private static Block[] getWoodBarrels() {
        List<Block> blocksList = new ArrayList<>();
        ArrayList<Block> list = new ArrayList<>(ForgeRegistries.BLOCKS.getValues());
        for (Block block : list) {
            ResourceLocation name = block.getRegistryName();
            assert name != null;
            if (block instanceof WoodBarrelBlock) {
                blocksList.add(block);
            }
        }
        return blocksList.toArray(new Block[0]);
    }

    private static Block[] getStoneBarrels() {
        List<Block> blocksList = new ArrayList<>();
        ArrayList<Block> list = new ArrayList<>(ForgeRegistries.BLOCKS.getValues());
        for (Block block : list) {
            ResourceLocation name = block.getRegistryName();
            assert name != null;
            if (block instanceof StoneBarrelBlock || block instanceof NetherBarrelBlock) {
                blocksList.add(block);
            }
        }
        return blocksList.toArray(new Block[0]);
    }

    private static Block[] getWoodCrucibles() {
        List<Block> blocksList = new ArrayList<>();
        ArrayList<Block> list = new ArrayList<>(ForgeRegistries.BLOCKS.getValues());
        for (Block block : list) {
            ResourceLocation name = block.getRegistryName();
            assert name != null;
            if (block instanceof WoodCrucibleBlock) {
                blocksList.add(block);
            }
        }
        return blocksList.toArray(new Block[0]);
    }

    private static Block[] getFiredCrucibles() {
        List<Block> blocksList = new ArrayList<>();
        ArrayList<Block> list = new ArrayList<>(ForgeRegistries.BLOCKS.getValues());
        for (Block block : list) {
            ResourceLocation name = block.getRegistryName();
            assert name != null;
            if (block instanceof FiredCrucibleBlock || block instanceof NetherCrucibleBlock) {
                blocksList.add(block);
            }
        }
        return blocksList.toArray(new Block[0]);
    }

    private static Block[] getSieves() {
        List<Block> blocksList = new ArrayList<>();
        ArrayList<Block> list = new ArrayList<>(ForgeRegistries.BLOCKS.getValues());
        for (Block block : list) {
            ResourceLocation name = block.getRegistryName();
            assert name != null;
            if (block instanceof WoodSieveBlock) {
                blocksList.add(block);
            }
        }
        return blocksList.toArray(new Block[0]);
    }

    public static void init(IEventBus eventBus) {
        logger.debug("Register Tile Entities");
        TILES.register(eventBus);
    }
}
