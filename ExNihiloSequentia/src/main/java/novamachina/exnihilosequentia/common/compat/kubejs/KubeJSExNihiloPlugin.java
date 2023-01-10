package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeTypesEvent;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;

public class KubeJSExNihiloPlugin extends KubeJSPlugin {

  @Override
  public void registerRecipeTypes(RegisterRecipeTypesEvent event) {
    event.register(exnResourceLocation("compost"), CompostRecipeJS::new);
    event.register(exnResourceLocation("crook"), SimpleChanceRecipeJS::new);
    event.register(exnResourceLocation("crucible"), CrucibleRecipeJS::new);
    event.register(exnResourceLocation("fluid_item"), FluidItemRecipeJS::new);
    event.register(exnResourceLocation("fluid_on_top"), FluidOnTopRecipeJS::new);
    event.register(exnResourceLocation("fluid_transform"), FluidTransformRecipeJS::new);
    event.register(exnResourceLocation("hammer"), SimpleChanceRecipeJS::new);
    event.register(exnResourceLocation("heat"), HeatRecipeJS::new);
    event.register(exnResourceLocation("sieve"), SieveRecipeJS::new);
  }

  private ResourceLocation exnResourceLocation(String path) {
    return new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, path);
  }
}
