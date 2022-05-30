package novamachina.exnihilothermal.common.init;

import javax.annotation.Nonnull;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.item.DollItem;
import novamachina.exnihilosequentia.common.item.ResourceItem;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilothermal.common.utility.ExNihiloThermalConstants;
import org.apache.logging.log4j.LogManager;

public class ExNihiloThermalItems {

  @Nonnull
  private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
      ForgeRegistries.ITEMS, ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL);
  public static final RegistryObject<DollItem> BASALZ_DOLL = ITEMS.register(
      ExNihiloThermalConstants.Items.DOLL_BASALZ,
      () -> new DollItem(ExNihiloThermalConstants.ModIds.THERMAL, "basalz",
          ExNihiloConstants.ModIds.MINECRAFT, "lava", 1.0D, "tooltip.doll.basalz"));
  public static final RegistryObject<DollItem> BLITZ_DOLL = ITEMS.register(
      ExNihiloThermalConstants.Items.DOLL_BLITZ,
      () -> new DollItem(ExNihiloThermalConstants.ModIds.THERMAL, "blitz",
          ExNihiloConstants.ModIds.MINECRAFT, "lava", 1.0D, "tooltip.doll.blitz"));
  public static final RegistryObject<DollItem> BLIZZ_DOLL = ITEMS.register(
      ExNihiloThermalConstants.Items.DOLL_BLIZZ,
      () -> new DollItem(ExNihiloThermalConstants.ModIds.THERMAL, "blizz",
          ExNihiloConstants.ModIds.MINECRAFT, "water", 1.0D, "tooltip.doll.blizz"));
  public static final RegistryObject<ResourceItem> DUST_OBSIDIAN = ITEMS.register(
      ExNihiloThermalConstants.Items.DUST_OBSIDIAN,
      () -> new ResourceItem(ExNihiloThermalConstants.Items.DUST_OBSIDIAN));
  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  public static void init(@Nonnull final IEventBus modEventBus) {
    logger.debug("Register items");
    ITEMS.register(modEventBus);
  }
}
