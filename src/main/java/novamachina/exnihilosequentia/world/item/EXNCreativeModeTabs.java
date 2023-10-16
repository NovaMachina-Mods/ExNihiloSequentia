package novamachina.exnihilosequentia.world.item;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.core.registries.CreativeModeTabRegistry;
import novamachina.novacore.world.item.CreativeModeTabDefinition;

public class EXNCreativeModeTabs {

  private static final CreativeModeTabRegistry CREATIVE_MODE_TABS =
      new CreativeModeTabRegistry(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);

  public static List<CreativeModeTabDefinition> getDefinitions() {
    return CREATIVE_MODE_TABS.getRegistry();
  }

  @Nonnull
  public static final CreativeModeTabDefinition EXN =
      CREATIVE_MODE_TABS.creativeModeTab(
          "creative_tab",
          CreativeModeTab.builder()
              .icon(() -> EXNBlocks.OAK_SIEVE.itemStack())
              .title(Component.literal("Ex Nihilo: Sequentia"))
              .displayItems(
                  (parameters, output) -> {
                    EXNItems.getDefinitions().forEach(output::accept);
                    EXNBlocks.getDefinitions()
                        .forEach(
                            blockDefinition -> {
                              if (blockDefinition != EXNBlocks.WITCH_WATER
                                  && blockDefinition != EXNBlocks.SEA_WATER) {
                                output.accept(blockDefinition);
                              }
                            });
                  })
              .build());
}
