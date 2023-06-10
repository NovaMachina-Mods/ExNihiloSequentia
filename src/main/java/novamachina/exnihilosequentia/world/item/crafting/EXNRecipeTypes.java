package novamachina.exnihilosequentia.world.item.crafting;

import java.util.List;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipe;
import novamachina.novacore.registries.RecipeTypeRegistry;

public class EXNRecipeTypes {

  private static final RecipeTypeRegistry RECIPE_TYPES =
      new RecipeTypeRegistry(ExNihiloSequentia.MOD_ID);

  public static List<RecipeType<?>> getDefinitions() {
    return RECIPE_TYPES.getRegistry();
  }

  public static final RecipeType<CompostRecipe> COMPOST_RECIPE_TYPE =
      RECIPE_TYPES.register("compost");
  public static final RecipeType<CrookRecipe> CROOK_RECIPE_TYPE = RECIPE_TYPES.register("crook");
  public static final RecipeType<CrucibleRecipe> CRUCIBLE_RECIPE_TYPE =
      RECIPE_TYPES.register("crucible");
  public static final RecipeType<FluidItemRecipe> FLUID_ITEM_RECIPE_TYPE =
      RECIPE_TYPES.register("fluid_item");
  public static final RecipeType<FluidOnTopRecipe> FLUID_ON_TOP_RECIPE_TYPE =
      RECIPE_TYPES.register("fluid_on_top");
  public static final RecipeType<FluidTransformRecipe> FLUID_TRANSFORM_RECIPE_TYPE =
      RECIPE_TYPES.register("fluid_transform");
  public static final RecipeType<HammerRecipe> HAMMER_RECIPE_TYPE = RECIPE_TYPES.register("hammer");
  public static final RecipeType<HeatRecipe> HEAT_RECIPE_TYPE = RECIPE_TYPES.register("heat");
  public static final RecipeType<SieveRecipe> SIEVE_RECIPE_TYPE = RECIPE_TYPES.register("sieve");

  private EXNRecipeTypes() {}
}
