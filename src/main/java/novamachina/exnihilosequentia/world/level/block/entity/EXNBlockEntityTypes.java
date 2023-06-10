package novamachina.exnihilosequentia.world.level.block.entity;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.blockentity.InfestedLeavesEntity;
import novamachina.exnihilosequentia.common.blockentity.InfestingLeavesEntity;
import novamachina.exnihilosequentia.common.blockentity.SieveEntity;
import novamachina.exnihilosequentia.common.blockentity.barrel.AbstractBarrelEntity;
import novamachina.exnihilosequentia.common.blockentity.barrel.StoneBarrelEntity;
import novamachina.exnihilosequentia.common.blockentity.barrel.WoodBarrelEntity;
import novamachina.exnihilosequentia.common.blockentity.crucible.FiredCrucibleEntity;
import novamachina.exnihilosequentia.common.blockentity.crucible.WoodCrucibleEntity;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.registries.BlockEntityTypeRegistry;
import novamachina.novacore.world.level.block.BlockEntityTypeDefinition;

public class EXNBlockEntityTypes {

  private static final BlockEntityTypeRegistry BLOCK_ENTITY_TYPES =
      new BlockEntityTypeRegistry(ExNihiloSequentia.MOD_ID);

  private EXNBlockEntityTypes() {}

  public static List<BlockEntityTypeDefinition<? extends BlockEntity>> getDefinitions() {
    return BLOCK_ENTITY_TYPES.getRegistry();
  }

  @Nonnull
  public static final BlockEntityTypeDefinition<FiredCrucibleEntity> FIRED_CRUCIBLE_ENTITY =
      BLOCK_ENTITY_TYPES.create(
          "fired_crucible",
          FiredCrucibleEntity::new,
          EXNBlocks.FIRED_CRUCIBLE,
          EXNBlocks.CRIMSON_CRUCIBLE,
          EXNBlocks.WARPED_CRUCIBLE);

  @Nonnull
  public static final BlockEntityTypeDefinition<WoodCrucibleEntity> WOODEN_CRUCIBLE_ENTITY =
      BLOCK_ENTITY_TYPES.create(
          "crucibles",
          WoodCrucibleEntity::new,
          EXNBlocks.ACACIA_CRUCIBLE,
          EXNBlocks.BIRCH_CRUCIBLE,
          EXNBlocks.DARK_OAK_CRUCIBLE,
          EXNBlocks.JUNGLE_CRUCIBLE,
          EXNBlocks.MANGROVE_CRUCIBLE,
          EXNBlocks.OAK_CRUCIBLE,
          EXNBlocks.SPRUCE_CRUCIBLE);

  @Nonnull
  public static final BlockEntityTypeDefinition<SieveEntity> SIEVE_ENTITY =
      BLOCK_ENTITY_TYPES.create(
          "sieves",
          SieveEntity::new,
          EXNBlocks.ACACIA_SIEVE,
          EXNBlocks.BIRCH_SIEVE,
          EXNBlocks.DARK_OAK_SIEVE,
          EXNBlocks.JUNGLE_SIEVE,
          EXNBlocks.MANGROVE_SIEVE,
          EXNBlocks.OAK_SIEVE,
          EXNBlocks.SPRUCE_SIEVE,
          EXNBlocks.CRIMSON_SIEVE,
          EXNBlocks.WARPED_SIEVE);

  @Nonnull
  public static final BlockEntityTypeDefinition<InfestingLeavesEntity> INFESTING_LEAVES_ENTITY =
      BLOCK_ENTITY_TYPES.create(
          "infesting_leaves", InfestingLeavesEntity::new, EXNBlocks.INFESTING_LEAVES);

  @Nonnull
  public static final BlockEntityTypeDefinition<InfestedLeavesEntity> INFESTED_LEAVES_ENTITY =
      BLOCK_ENTITY_TYPES.create(
          "infested_leaves", InfestedLeavesEntity::new, EXNBlocks.INFESTED_LEAVES);

  @Nonnull
  public static final BlockEntityTypeDefinition<? extends AbstractBarrelEntity>
      WOODEN_BARREL_ENTITY =
          BLOCK_ENTITY_TYPES.create(
              "barrels",
              WoodBarrelEntity::new,
              EXNBlocks.ACACIA_BARREL,
              EXNBlocks.BIRCH_BARREL,
              EXNBlocks.DARK_OAK_BARREL,
              EXNBlocks.JUNGLE_BARREL,
              EXNBlocks.OAK_BARREL,
              EXNBlocks.SPRUCE_BARREL);

  @Nonnull
  public static final BlockEntityTypeDefinition<? extends AbstractBarrelEntity>
      STONE_BARREL_ENTITY =
          BLOCK_ENTITY_TYPES.create(
              "stone_barrel",
              StoneBarrelEntity::new,
              EXNBlocks.STONE_BARREL,
              EXNBlocks.CRIMSON_BARREL,
              EXNBlocks.WARPED_BARREL);
}
