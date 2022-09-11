package novamachina.exnihilotinkers.client;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractBlockStateGenerator;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;

public class EXNTinkersBlockStates extends AbstractBlockStateGenerator {

  public EXNTinkersBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super(gen, EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS, exFileHelper);
  }

  public ResourceLocation tinkersLoc(String string) {
    return new ResourceLocation(EXNTinkersConstants.ModIds.TINKERS_MOD, "block/wood/" + string);
  }

  private void registerBarrels() {
    //    createBarrel(EXNTinkersBlocks.BARREL_BLOODSHROOM.get(), tinkersLoc("bloodshroom/planks"));
    //    createBarrel(EXNTinkersBlocks.BARREL_GREENHEART.get(), tinkersLoc("greenheart/planks"));
    //    createBarrel(EXNTinkersBlocks.BARREL_SKYROOT.get(), tinkersLoc("skyroot/planks"));
  }

  private void registerSieves() {
    //    createSieve(EXNTinkersBlocks.SIEVE_BLOODSHROOM.get(),
    // tinkersLoc("bloodshroom/stripped_log"));
    //    createSieve(EXNTinkersBlocks.SIEVE_GREENHEART.get(),
    // tinkersLoc("greenheart/stripped_log"));
    //    createSieve(EXNTinkersBlocks.SIEVE_SKYROOT.get(), tinkersLoc("skyroot/stripped_log"));
  }

  private void registerCrucibles() {
    //    createCrucible(EXNTinkersBlocks.CRUCIBLE_BLOODSHROOM.get(),
    // tinkersLoc("bloodshroom/log"));
    //    createCrucible(EXNTinkersBlocks.CRUCIBLE_GREENHEART.get(), tinkersLoc("greenheart/log"));
    //    createCrucible(EXNTinkersBlocks.CRUCIBLE_SKYROOT.get(), tinkersLoc("skyroot/log"));
  }

  @Override
  protected void registerStatesAndModels() {
    registerSieves();
    registerBarrels();
    registerCrucibles();
  }
}
