package novamachina.exnihilotinkers.common;

import net.minecraft.data.DataGenerator;
import novamachina.exnihilosequentia.api.datagen.AbstractLootTableGenerator;
import novamachina.exnihilotinkers.common.init.EXNTinkersBlocks;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import org.jetbrains.annotations.NotNull;

public class EXNTinkersLootTables extends AbstractLootTableGenerator {

  public EXNTinkersLootTables(@NotNull DataGenerator generator) {
    super(generator, EXNTinkersConstants.ModIds.TINKERS_MOD);
  }

  @Override
  protected void createLootTables() {
    registerSelfDrop(EXNTinkersBlocks.BARREL_BLOODSHROOM.get());
    registerSelfDrop(EXNTinkersBlocks.BARREL_GREENHEART.get());
    registerSelfDrop(EXNTinkersBlocks.BARREL_SKYROOT.get());
    registerSelfDrop(EXNTinkersBlocks.CRUCIBLE_BLOODSHROOM.get());
    registerSelfDrop(EXNTinkersBlocks.CRUCIBLE_GREENHEART.get());
    registerSelfDrop(EXNTinkersBlocks.CRUCIBLE_SKYROOT.get());
    registerSelfDrop(EXNTinkersBlocks.SIEVE_BLOODSHROOM.get());
    registerSelfDrop(EXNTinkersBlocks.SIEVE_GREENHEART.get());
    registerSelfDrop(EXNTinkersBlocks.SIEVE_SKYROOT.get());
  }
}
