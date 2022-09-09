package novamachina.exnihilomekanism.common.init;

import com.mojang.logging.LogUtils;
import javax.annotation.Nonnull;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilomekanism.common.utility.ExNihiloMekanismConstants;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class ExNihiloMekanismItems {

  private static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(
          ForgeRegistries.ITEMS, ExNihiloMekanismConstants.ModIds.EX_NIHILO_MEKANISM);
  public static final Ore OSMIUM =
      new Ore(ExNihiloMekanismConstants.Ore.OSMIUM, true, false, false, new Color("BBDDFF"), ITEMS);
  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  public static void init(IEventBus modEventBus) {
    logger.debug("Register items");
    ITEMS.register(modEventBus);
  }
}
