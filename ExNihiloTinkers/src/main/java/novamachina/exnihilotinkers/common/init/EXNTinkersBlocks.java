package novamachina.exnihilotinkers.common.init;

import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.block.BaseBlock;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilotinkers.common.block.TinkersBarrelBlock;
import novamachina.exnihilotinkers.common.block.TinkersCrucibleBlock;
import novamachina.exnihilotinkers.common.block.TinkersSieveBlock;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;

public class EXNTinkersBlocks {

  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS);
  public static final RegistryObject<BlockSieve> SIEVE_BLOODSHROOM =
      BLOCKS.register("bloodshroom_sieve", TinkersSieveBlock::new);
  public static final RegistryObject<BlockSieve> SIEVE_GREENHEART =
      BLOCKS.register("greenheart_sieve", TinkersSieveBlock::new);
  public static final RegistryObject<BlockSieve> SIEVE_SKYROOT =
      BLOCKS.register("skyroot_sieve", TinkersSieveBlock::new);
  public static final RegistryObject<BaseBlock> BARREL_BLOODSHROOM =
      BLOCKS.register("bloodshroom_barrel", TinkersBarrelBlock::new);
  public static final RegistryObject<BaseBlock> BARREL_GREENHEART =
      BLOCKS.register("greenheart_barrel", TinkersBarrelBlock::new);
  public static final RegistryObject<BaseBlock> BARREL_SKYROOT =
      BLOCKS.register("skyroot_barrel", TinkersBarrelBlock::new);
  public static final RegistryObject<BaseBlock> CRUCIBLE_BLOODSHROOM =
      BLOCKS.register("bloodshroom_crucible", TinkersCrucibleBlock::new);
  public static final RegistryObject<BaseBlock> CRUCIBLE_GREENHEART =
      BLOCKS.register("greenheart_crucible", TinkersCrucibleBlock::new);
  public static final RegistryObject<BaseBlock> CRUCIBLE_SKYROOT =
      BLOCKS.register("skyroot_crucible", TinkersCrucibleBlock::new);
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  public static void init(IEventBus modEventBus) {
    logger.debug("Register blocks");
    BLOCKS.register(modEventBus);
  }
}
