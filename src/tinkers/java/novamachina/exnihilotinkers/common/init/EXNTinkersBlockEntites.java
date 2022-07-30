package novamachina.exnihilotinkers.common.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilotinkers.common.blockentity.TinkersBarrelEntity;
import novamachina.exnihilotinkers.common.blockentity.TinkersCrucibleEntity;
import novamachina.exnihilotinkers.common.blockentity.TinkersSieveEntity;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import org.apache.logging.log4j.LogManager;

public class EXNTinkersBlockEntites {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
  private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
      DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,
          EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS);

  public static void init(IEventBus eventBus) {
    logger.debug("Register Block Entities");
    BLOCK_ENTITIES.register(eventBus);
  }  public static final RegistryObject<BlockEntityType<TinkersSieveEntity>> TINKERS_SIEVES = BLOCK_ENTITIES.register(
      "tinkers_sieves", () -> BlockEntityType.Builder.of(TinkersSieveEntity::new,
              EXNTinkersBlocks.SIEVE_BLOODSHROOM.get(),
              EXNTinkersBlocks.SIEVE_GREENHEART.get(), EXNTinkersBlocks.SIEVE_SKYROOT.get())
          .build(null)
  );
  public static final RegistryObject<BlockEntityType<TinkersCrucibleEntity>> TINKERS_CRUCIBLES = BLOCK_ENTITIES.register(
      "tinkers_crucibles", () -> BlockEntityType.Builder.of(TinkersCrucibleEntity::new,
              EXNTinkersBlocks.CRUCIBLE_BLOODSHROOM.get(),
              EXNTinkersBlocks.CRUCIBLE_GREENHEART.get(), EXNTinkersBlocks.CRUCIBLE_SKYROOT.get())
          .build(null)
  );
  public static final RegistryObject<BlockEntityType<TinkersBarrelEntity>> TINKERS_BARRELS = BLOCK_ENTITIES.register(
      "tinkers_barrels", () -> BlockEntityType.Builder.of(TinkersBarrelEntity::new,
              EXNTinkersBlocks.BARREL_BLOODSHROOM.get(),
              EXNTinkersBlocks.BARREL_GREENHEART.get(), EXNTinkersBlocks.BARREL_SKYROOT.get())
          .build(null)
  );


}
