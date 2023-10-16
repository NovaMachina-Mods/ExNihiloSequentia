package novamachina.exnihilosequentia.common.compat.jei;

import mezz.jei.api.recipe.RecipeType;
import novamachina.exnihilosequentia.common.compat.jei.melting.JEICrucibleRecipe;
import novamachina.exnihilosequentia.common.compat.jei.sifting.JEISieveRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.crafting.CompostRecipe;
import novamachina.exnihilosequentia.world.item.crafting.CrushingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.HarvestRecipe;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;
import novamachina.exnihilosequentia.world.item.crafting.SolidifyingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.TransitionRecipe;

public class RecipeTypes {

  public static final RecipeType<CompostRecipe> COMPOST =
      RecipeType.create(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "compost", CompostRecipe.class);
  public static final RecipeType<HarvestRecipe> HARVEST =
      RecipeType.create(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "harvest", HarvestRecipe.class);
  public static final RecipeType<JEICrucibleRecipe> MELTING =
      RecipeType.create(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "melting", JEICrucibleRecipe.class);
  public static final RecipeType<JEICrucibleRecipe> FIRED_MELTING =
      RecipeType.create(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "fired_melting", JEICrucibleRecipe.class);
  public static final RecipeType<PrecipitateRecipe> PRECIPITATE =
      RecipeType.create(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "precipitate", PrecipitateRecipe.class);
  public static final RecipeType<SolidifyingRecipe> SOLIDIFYING =
      RecipeType.create(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "solidifying", SolidifyingRecipe.class);
  public static final RecipeType<TransitionRecipe> TRANSITION =
      RecipeType.create(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "transition", TransitionRecipe.class);
  public static final RecipeType<CrushingRecipe> CRUSHING =
      RecipeType.create(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "crushing", CrushingRecipe.class);
  public static final RecipeType<HeatRecipe> HEAT =
      RecipeType.create(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "heat", HeatRecipe.class);
  public static final RecipeType<JEISieveRecipe> DRY_SIFTING =
      RecipeType.create(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "dry_sifting", JEISieveRecipe.class);
  public static final RecipeType<JEISieveRecipe> WET_SIFTING =
      RecipeType.create(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "wet_sifting", JEISieveRecipe.class);

  private RecipeTypes() {}
}
