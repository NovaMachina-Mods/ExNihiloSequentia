package novamachina.exnihilosequentia.common.registries;

import javax.annotation.Nonnull;

public class ExNihiloRegistries {

  @Nonnull public static final CompostRegistry COMPOST_REGISTRY = new CompostRegistry();
  @Nonnull public static final HarvestingRegistry CROOK_REGISTRY = new HarvestingRegistry();
  @Nonnull public static final MeltingRegistry CRUCIBLE_REGISTRY = new MeltingRegistry();

  @Nonnull public static final PrecipitateRegistry FLUID_BLOCK_REGISTRY = new PrecipitateRegistry();

  @Nonnull
  public static final SolidifyingRegistry FLUID_ON_TOP_REGISTRY = new SolidifyingRegistry();

  @Nonnull
  public static final TransitionRegistry FLUID_TRANSFORM_REGISTRY = new TransitionRegistry();

  @Nonnull public static final CrushingRegistry HAMMER_REGISTRY = new CrushingRegistry();
  @Nonnull public static final HeatRegistry HEAT_REGISTRY = new HeatRegistry();
  @Nonnull public static final SiftingRegistry SIEVE_REGISTRY = new SiftingRegistry();

  private ExNihiloRegistries() {}

  public static void clearRegistries() {
    CROOK_REGISTRY.clearRecipes();
    COMPOST_REGISTRY.clearRecipes();
    HAMMER_REGISTRY.clearRecipes();
    SIEVE_REGISTRY.clearRecipes();
    HEAT_REGISTRY.clearRecipes();
    CRUCIBLE_REGISTRY.clearRecipes();
    FLUID_ON_TOP_REGISTRY.clearRecipes();
    FLUID_TRANSFORM_REGISTRY.clearRecipes();
    FLUID_BLOCK_REGISTRY.clearRecipes();
  }
}
