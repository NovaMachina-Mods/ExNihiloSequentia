package novamachina.exnihilosequentia.world.level.block.entity;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.core.registries.BlockEntityTypeRegistry;
import novamachina.novacore.world.level.block.BlockEntityTypeDefinition;

public class EXNBlockEntityTypes {

  private static final BlockEntityTypeRegistry BLOCK_ENTITY_TYPES =
      new BlockEntityTypeRegistry(ExNihiloSequentia.MOD_ID);

  private EXNBlockEntityTypes() {}

  public static List<BlockEntityTypeDefinition<? extends BlockEntity>> getDefinitions() {
    return BLOCK_ENTITY_TYPES.getRegistry();
  }

  @Nonnull
  public static final BlockEntityTypeDefinition<FiredCrucibleBlockBlockEntity>
      FIRED_CRUCIBLE_ENTITY =
          BLOCK_ENTITY_TYPES.create(
              "fired_crucible",
              FiredCrucibleBlockBlockEntity::new,
              EXNBlocks.FIRED_CRUCIBLE,
              EXNBlocks.CRIMSON_CRUCIBLE,
              EXNBlocks.WARPED_CRUCIBLE);

  @Nonnull
  public static final BlockEntityTypeDefinition<WoodCrucibleBlockEntity> WOODEN_CRUCIBLE_ENTITY =
      BLOCK_ENTITY_TYPES.create(
          "crucibles",
          WoodCrucibleBlockEntity::new,
          EXNBlocks.ACACIA_CRUCIBLE,
          EXNBlocks.BAMBOO_CRUCIBLE,
          EXNBlocks.BIRCH_CRUCIBLE,
          EXNBlocks.CHERRY_CRUCIBLE,
          EXNBlocks.DARK_OAK_CRUCIBLE,
          EXNBlocks.JUNGLE_CRUCIBLE,
          EXNBlocks.MANGROVE_CRUCIBLE,
          EXNBlocks.OAK_CRUCIBLE,
          EXNBlocks.SPRUCE_CRUCIBLE);

  @Nonnull
  public static final BlockEntityTypeDefinition<SieveBlockEntity> SIEVE_ENTITY =
      BLOCK_ENTITY_TYPES.create(
          "sieves",
          SieveBlockEntity::new,
          EXNBlocks.ACACIA_SIEVE,
          EXNBlocks.BAMBOO_SIEVE,
          EXNBlocks.BIRCH_SIEVE,
          EXNBlocks.CHERRY_SIEVE,
          EXNBlocks.DARK_OAK_SIEVE,
          EXNBlocks.JUNGLE_SIEVE,
          EXNBlocks.MANGROVE_SIEVE,
          EXNBlocks.OAK_SIEVE,
          EXNBlocks.SPRUCE_SIEVE,
          EXNBlocks.CRIMSON_SIEVE,
          EXNBlocks.WARPED_SIEVE);

  @Nonnull
  public static final BlockEntityTypeDefinition<InfestingLeavesBlockEntity>
      INFESTING_LEAVES_ENTITY =
          BLOCK_ENTITY_TYPES.create(
              "infesting_leaves", InfestingLeavesBlockEntity::new, EXNBlocks.INFESTING_LEAVES);

  @Nonnull
  public static final BlockEntityTypeDefinition<InfestedLeavesBlockEntity> INFESTED_LEAVES_ENTITY =
      BLOCK_ENTITY_TYPES.create(
          "infested_leaves", InfestedLeavesBlockEntity::new, EXNBlocks.INFESTED_LEAVES);

  @Nonnull
  public static final BlockEntityTypeDefinition<? extends BarrelBlockEntity> WOODEN_BARREL_ENTITY =
      BLOCK_ENTITY_TYPES.create(
          "barrels",
          WoodBarrelBlockEntity::new,
          EXNBlocks.ACACIA_BARREL,
          EXNBlocks.BAMBOO_BARREL,
          EXNBlocks.BIRCH_BARREL,
          EXNBlocks.CHERRY_BARREL,
          EXNBlocks.DARK_OAK_BARREL,
          EXNBlocks.JUNGLE_BARREL,
          EXNBlocks.OAK_BARREL,
          EXNBlocks.MANGROVE_BARREL,
          EXNBlocks.SPRUCE_BARREL);

  @Nonnull
  public static final BlockEntityTypeDefinition<? extends BarrelBlockEntity> STONE_BARREL_ENTITY =
      BLOCK_ENTITY_TYPES.create(
          "stone_barrel",
          StoneBarrelBlockEntity::new,
          EXNBlocks.STONE_BARREL,
          EXNBlocks.CRIMSON_BARREL,
          EXNBlocks.WARPED_BARREL);
}
