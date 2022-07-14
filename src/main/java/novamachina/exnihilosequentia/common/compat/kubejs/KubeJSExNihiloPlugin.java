package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeHandlersEvent;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class KubeJSExNihiloPlugin extends KubeJSPlugin {

  @Override
  public void addRecipes(RegisterRecipeHandlersEvent event) {
    event.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":compost", CompostRecipeJS::new);
    event.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":crook", SimpleChanceRecipeJS::new);
    event.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":crucible", CrucibleRecipeJS::new);
    event.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_item", FluidItemRecipeJS::new);
    event.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_on_top", FluidOnTopRecipeJS::new);
    event.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_transform", FluidTransformRecipeJS::new);
    event.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":hammer", SimpleChanceRecipeJS::new);
    event.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":heat", HeatRecipeJS::new);
    event.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":sieve", SieveRecipeJS::new);
  }
}
