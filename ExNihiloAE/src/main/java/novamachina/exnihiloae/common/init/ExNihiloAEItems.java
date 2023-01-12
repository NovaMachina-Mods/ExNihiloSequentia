package novamachina.exnihiloae.common.init;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihiloae.common.utility.ExNihiloAEConstants;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class ExNihiloAEItems {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());
  private static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, ExNihiloAEConstants.ModIds.EX_NIHILO_AE);
  public static final RegistryObject<Item> CRUSHED_SKYSTONE =
      ITEMS.register(
          ExNihiloAEConstants.Blocks.CRUSHED_SKYSTONE,
          () ->
              new BlockItem(
                  ExNihiloAEBlocks.CRUSHED_SKYSTONE.get(),
                  new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));

  private ExNihiloAEItems() {}

  public static void init(IEventBus modEventBus) {
    logger.debug("Register items");
    ITEMS.register(modEventBus);
  }
}
