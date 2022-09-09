package novamachina.exnihilotinkers.common.init;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;

public class EXNTinkersItems {

  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());
  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS);
  public static final Ore COBALT =
      new Ore("cobalt", true, false, false, new Color("2376DD"), ITEMS);
  public static final RegistryObject<Item> SIEVE_BLOODSHROOM =
      ITEMS.register(
          "bloodshroom_sieve",
          () ->
              new BlockItem(
                  EXNTinkersBlocks.SIEVE_BLOODSHROOM.get(),
                  new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
  public static final RegistryObject<Item> SIEVE_GREENHEART =
      ITEMS.register(
          "greenheart_sieve",
          () ->
              new BlockItem(
                  EXNTinkersBlocks.SIEVE_GREENHEART.get(),
                  new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
  public static final RegistryObject<Item> SIEVE_SKYROOT =
      ITEMS.register(
          "skyroot_sieve",
          () ->
              new BlockItem(
                  EXNTinkersBlocks.SIEVE_SKYROOT.get(),
                  new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
  public static final RegistryObject<Item> BARREL_BLOODSHROOM =
      ITEMS.register(
          "bloodshroom_barrel",
          () ->
              new BlockItem(
                  EXNTinkersBlocks.BARREL_BLOODSHROOM.get(),
                  new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
  public static final RegistryObject<Item> BARREL_GREENHEART =
      ITEMS.register(
          "greenheart_barrel",
          () ->
              new BlockItem(
                  EXNTinkersBlocks.BARREL_GREENHEART.get(),
                  new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
  public static final RegistryObject<Item> BARREL_SKYROOT =
      ITEMS.register(
          "skyroot_barrel",
          () ->
              new BlockItem(
                  EXNTinkersBlocks.BARREL_SKYROOT.get(),
                  new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
  public static final RegistryObject<Item> CRUCIBLE_BLOODSHROOM =
      ITEMS.register(
          "bloodshroom_crucible",
          () ->
              new BlockItem(
                  EXNTinkersBlocks.CRUCIBLE_BLOODSHROOM.get(),
                  new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
  public static final RegistryObject<Item> CRUCIBLE_GREENHEART =
      ITEMS.register(
          "greenheart_crucible",
          () ->
              new BlockItem(
                  EXNTinkersBlocks.CRUCIBLE_GREENHEART.get(),
                  new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
  public static final RegistryObject<Item> CRUCIBLE_SKYROOT =
      ITEMS.register(
          "skyroot_crucible",
          () ->
              new BlockItem(
                  EXNTinkersBlocks.CRUCIBLE_SKYROOT.get(),
                  new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));

  public static void init(IEventBus modEventBus) {
    logger.debug("Register items");
    ITEMS.register(modEventBus);
  }
}
