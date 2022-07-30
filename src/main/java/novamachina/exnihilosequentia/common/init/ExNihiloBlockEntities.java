package novamachina.exnihilosequentia.common.init;

import javax.annotation.Nonnull;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.blockentity.InfestedLeavesEntity;
import novamachina.exnihilosequentia.common.blockentity.InfestingLeavesEntity;
import novamachina.exnihilosequentia.common.blockentity.SieveEntity;
import novamachina.exnihilosequentia.common.blockentity.barrel.AbstractBarrelEntity;
import novamachina.exnihilosequentia.common.blockentity.barrel.StoneBarrelEntity;
import novamachina.exnihilosequentia.common.blockentity.barrel.WoodBarrelEntity;
import novamachina.exnihilosequentia.common.blockentity.crucible.FiredCrucibleEntity;
import novamachina.exnihilosequentia.common.blockentity.crucible.WoodCrucibleEntity;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloBlockEntities {

  @Nonnull
  private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
      DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());  @Nonnull
  public static final RegistryObject<BlockEntityType<FiredCrucibleEntity>> FIRED_CURICLBE_ENTITY = BLOCK_ENTITIES
      .register(Blocks.FIRED_CRUCIBLE, () -> BlockEntityType.Builder
          .of(FiredCrucibleEntity::new, ExNihiloBlocks.CRUCIBLE_FIRED.get(),
              ExNihiloBlocks.CRUCIBLE_CRIMSON.get(),
              ExNihiloBlocks.CRUCIBLE_WARPED.get()).build(null));
  private ExNihiloBlockEntities() {
  }  @Nonnull
  public static final RegistryObject<BlockEntityType<WoodCrucibleEntity>> WOODEN_CRUCIBLE_ENTITY = BLOCK_ENTITIES
      .register(Blocks.CRUCIBLES, () -> BlockEntityType.Builder
          .of(WoodCrucibleEntity::new, ExNihiloBlocks.CRUCIBLE_ACACIA.get(),
              ExNihiloBlocks.CRUCIBLE_BIRCH.get(),
              ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(), ExNihiloBlocks.CRUCIBLE_JUNGLE.get(),
              ExNihiloBlocks.CRUCIBLE_OAK.get(), ExNihiloBlocks.CRUCIBLE_SPRUCE.get()).build(null));

  public static void init(IEventBus eventBus) {
    logger.debug("Register Block Entities");
    BLOCK_ENTITIES.register(eventBus);
  }  @Nonnull
  public static final RegistryObject<BlockEntityType<SieveEntity>> SIEVE_ENTITY = BLOCK_ENTITIES
      .register(Blocks.SIEVES, () -> BlockEntityType.Builder
          .of(SieveEntity::new, ExNihiloBlocks.SIEVE_ACACIA.get(), ExNihiloBlocks.SIEVE_BIRCH.get(),
              ExNihiloBlocks.SIEVE_DARK_OAK.get(), ExNihiloBlocks.SIEVE_JUNGLE.get(),
              ExNihiloBlocks.SIEVE_OAK.get(), ExNihiloBlocks.SIEVE_SPRUCE.get(),
              ExNihiloBlocks.SIEVE_CRIMSON.get(), ExNihiloBlocks.SIEVE_WARPED.get()).build(null));
  @Nonnull
  public static final RegistryObject<BlockEntityType<InfestingLeavesEntity>> INFESTING_LEAVES_ENTITY = BLOCK_ENTITIES
      .register(Blocks.INFESTING_LEAVES, () -> BlockEntityType.Builder
          .of(InfestingLeavesEntity::new, ExNihiloBlocks.INFESTING_LEAVES.get()).build(null));
  @Nonnull
  public static final RegistryObject<BlockEntityType<InfestedLeavesEntity>> INFESTED_LEAVES_ENTITY = BLOCK_ENTITIES
      .register(Blocks.INFESTED_LEAVES, () -> BlockEntityType.Builder
          .of(InfestedLeavesEntity::new, ExNihiloBlocks.INFESTED_LEAVES.get()).build(null));
  @Nonnull
  public static final RegistryObject<BlockEntityType<? extends AbstractBarrelEntity>> WOODEN_BARREL_ENTITY = BLOCK_ENTITIES
      .register(Blocks.BARRELS, () -> BlockEntityType.Builder
          .of(WoodBarrelEntity::new, ExNihiloBlocks.BARREL_ACACIA.get(),
              ExNihiloBlocks.BARREL_BIRCH.get(),
              ExNihiloBlocks.BARREL_DARK_OAK.get(), ExNihiloBlocks.BARREL_JUNGLE.get(),
              ExNihiloBlocks.BARREL_OAK.get(), ExNihiloBlocks.BARREL_SPRUCE.get()).build(null));
  @Nonnull
  public static final RegistryObject<BlockEntityType<? extends AbstractBarrelEntity>> STONE_BARREL_ENTITY = BLOCK_ENTITIES
      .register(Blocks.STONE_BARREL, () -> BlockEntityType.Builder
          .of(StoneBarrelEntity::new, ExNihiloBlocks.BARREL_STONE.get(),
              ExNihiloBlocks.BARREL_CRIMSON.get(),
              ExNihiloBlocks.BARREL_WARPED.get()).build(null));





}
