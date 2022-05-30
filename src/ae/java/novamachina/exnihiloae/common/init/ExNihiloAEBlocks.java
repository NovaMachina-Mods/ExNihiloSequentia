package novamachina.exnihiloae.common.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihiloae.common.utility.ExNihiloAEConstants;
import novamachina.exnihilosequentia.common.block.BaseFallingBlock;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloAEBlocks {

  private static final DeferredRegister<Block> BLOCKS = DeferredRegister
      .create(ForgeRegistries.BLOCKS, ExNihiloAEConstants.ModIds.EX_NIHILO_AE);
  public static final RegistryObject<BaseFallingBlock> CRUSHED_SKYSTONE = BLOCKS
      .register(ExNihiloAEConstants.Blocks.CRUSHED_SKYSTONE,
          () -> new BaseFallingBlock(new BlockBuilder()
              .properties(BlockBehaviour.Properties.of(Material.SAND).strength(0.7F)
                  .sound(SoundType.GRAVEL))));
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  private ExNihiloAEBlocks() {
  }

  public static void init(IEventBus modEventBus) {
    logger.debug("Register blocks");
    BLOCKS.register(modEventBus);
  }
}
