package novamachina.exnihilosequentia.common.compat.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;

public class KubeJSExNihiloPlugin extends KubeJSPlugin {

  @Override
  public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
    event.register(exnResourceLocation("compost"), CompostRecipeJS.SCHEMA);
    event.register(exnResourceLocation("harvest"), SimpleChanceRecipeJS.SCHEMA);
    event.register(exnResourceLocation("melting"), MeltingRecipeJS.SCHEMA);
    event.register(exnResourceLocation("precipitate"), PrecipitateRecipeJS.SCHEMA);
    event.register(exnResourceLocation("solidifying"), SolidifyingRecipeJS.SCHEMA);
    event.register(exnResourceLocation("transition"), TransitionRecipeJS.SCHEMA);
    event.register(exnResourceLocation("crushing"), SimpleChanceRecipeJS.SCHEMA);
    event.register(exnResourceLocation("heat"), HeatRecipeJS.SCHEMA);
    event.register(exnResourceLocation("sifting"), SiftingRecipeJS.SCHEMA);
  }

  private ResourceLocation exnResourceLocation(String path) {
    return new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, path);
  }
}
