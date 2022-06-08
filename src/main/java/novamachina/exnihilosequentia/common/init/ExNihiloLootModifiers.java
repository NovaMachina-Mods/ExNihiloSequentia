package novamachina.exnihilosequentia.common.init;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.loot.modifier.UseCrookModifier;
import novamachina.exnihilosequentia.common.loot.modifier.UseHammerModifier;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloLootModifiers {
  private static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS =
      DeferredRegister.create(Keys.LOOT_MODIFIER_SERIALIZERS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);

  public static final RegistryObject<GlobalLootModifierSerializer<?>> hammerModifier = LOOT_MODIFIERS.register("use_hammer",
      UseHammerModifier.Serializer::new);
  public static final RegistryObject<GlobalLootModifierSerializer<?>> crookModifier = LOOT_MODIFIERS.register("use_crook",
      UseCrookModifier.Serializer::new);

  public static void init(IEventBus modEventBus) {
    LOOT_MODIFIERS.register(modEventBus);
  }
}
