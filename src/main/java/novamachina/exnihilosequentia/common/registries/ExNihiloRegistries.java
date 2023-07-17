package novamachina.exnihilosequentia.common.registries;

import javax.annotation.Nonnull;

public class ExNihiloRegistries {

  @Nonnull public static final CompostRegistry COMPOST_REGISTRY = new CompostRegistry();
  @Nonnull public static final CrookRegistry CROOK_REGISTRY = new CrookRegistry();
  @Nonnull public static final CrucibleRegistry CRUCIBLE_REGISTRY = new CrucibleRegistry();

  @Nonnull
  public static final FluidItemTransformRegistry FLUID_BLOCK_REGISTRY =
      new FluidItemTransformRegistry();

  @Nonnull public static final FluidOnTopRegistry FLUID_ON_TOP_REGISTRY = new FluidOnTopRegistry();

  @Nonnull
  public static final FluidTransformRegistry FLUID_TRANSFORM_REGISTRY =
      new FluidTransformRegistry();

  @Nonnull public static final HammerRegistry HAMMER_REGISTRY = new HammerRegistry();
  @Nonnull public static final HeatRegistry HEAT_REGISTRY = new HeatRegistry();
  @Nonnull public static final SieveRegistry SIEVE_REGISTRY = new SieveRegistry();

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
