package novamachina.exnihilosequentia.api;

import novamachina.exnihilosequentia.api.registry.*;
import novamachina.exnihilosequentia.common.registries.*;

public class ExNihiloRegistries {
    public static final ICompostRegistry COMPOST_REGISTRY = new CompostRegistry();
    public static final ICrookRegistry CROOK_REGISTRY = new CrookRegistry();
    public static final ICrucibleRegistry CRUCIBLE_REGISTRY = new CrucibleRegistry();
    public static final IFluidItemTransformRegistry FLUID_BLOCK_REGISTRY = new FluidItemTransformRegistry();
    public static final IFluidOnTopRegistry FLUID_ON_TOP_REGISTRY = new FluidOnTopRegistry();
    public static final IFluidTransformRegistry FLUID_TRANSFORM_REGISTRY = new FluidTransformRegistry();
    public static final IHammerRegistry HAMMER_REGISTRY = new HammerRegistry();
    public static final IHeatRegistry HEAT_REGISTRY = new HeatRegistry();
    public static final ISieveRegistry SIEVE_REGISTRY = new SieveRegistry();

    private ExNihiloRegistries() {
    }

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
