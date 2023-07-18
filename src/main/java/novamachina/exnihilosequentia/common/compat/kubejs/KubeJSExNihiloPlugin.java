package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;

public class KubeJSExNihiloPlugin extends KubeJSPlugin {

  @Override
  public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
    event.register(exnResourceLocation("compost"), CompostRecipeJS.SCHEMA);
    event.register(exnResourceLocation("crook"), SimpleChanceRecipeJS.SCHEMA);
    event.register(exnResourceLocation("crucible"), CrucibleRecipeJS.SCHEMA);
    event.register(exnResourceLocation("fluid_item"), FluidItemRecipeJS.SCHEMA);
    event.register(exnResourceLocation("fluid_on_top"), FluidOnTopRecipeJS.SCHEMA);
    event.register(exnResourceLocation("fluid_transform"), FluidTransformRecipeJS.SCHEMA);
    event.register(exnResourceLocation("hammer"), SimpleChanceRecipeJS.SCHEMA);
    event.register(exnResourceLocation("heat"), HeatRecipeJS.SCHEMA);
    event.register(exnResourceLocation("sieve"), SieveRecipeJS.SCHEMA);
  }

  private ResourceLocation exnResourceLocation(String path) {
    return new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, path);
  }
}
