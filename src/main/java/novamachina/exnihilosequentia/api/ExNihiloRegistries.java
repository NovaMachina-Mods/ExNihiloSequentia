package novamachina.exnihilosequentia.api;

import novamachina.exnihilosequentia.api.registry.ICompostRegistry;
import novamachina.exnihilosequentia.api.registry.ICrookRegistry;
import novamachina.exnihilosequentia.api.registry.ICrucibleRegistry;
import novamachina.exnihilosequentia.api.registry.IFluidItemTransformRegistry;
import novamachina.exnihilosequentia.api.registry.IFluidOnTopRegistry;
import novamachina.exnihilosequentia.api.registry.IFluidTransformRegistry;
import novamachina.exnihilosequentia.api.registry.IHammerRegistry;
import novamachina.exnihilosequentia.api.registry.IHeatRegistry;
import novamachina.exnihilosequentia.api.registry.ISieveRegistry;
import novamachina.exnihilosequentia.common.registries.CompostRegistry;
import novamachina.exnihilosequentia.common.registries.CrookRegistry;
import novamachina.exnihilosequentia.common.registries.CrucibleRegistry;
import novamachina.exnihilosequentia.common.registries.FluidItemTransformRegistry;
import novamachina.exnihilosequentia.common.registries.FluidOnTopRegistry;
import novamachina.exnihilosequentia.common.registries.FluidTransformRegistry;
import novamachina.exnihilosequentia.common.registries.HammerRegistry;
import novamachina.exnihilosequentia.common.registries.HeatRegistry;
import novamachina.exnihilosequentia.common.registries.SieveRegistry;

import javax.annotation.Nonnull;

public class ExNihiloRegistries {
    @Nonnull public static final ICompostRegistry COMPOST_REGISTRY = new CompostRegistry();
    @Nonnull public static final ICrookRegistry CROOK_REGISTRY = new CrookRegistry();
    @Nonnull public static final ICrucibleRegistry CRUCIBLE_REGISTRY = new CrucibleRegistry();
    @Nonnull public static final IFluidItemTransformRegistry FLUID_BLOCK_REGISTRY = new FluidItemTransformRegistry();
    @Nonnull public static final IFluidOnTopRegistry FLUID_ON_TOP_REGISTRY = new FluidOnTopRegistry();
    @Nonnull public static final IFluidTransformRegistry FLUID_TRANSFORM_REGISTRY = new FluidTransformRegistry();
    @Nonnull public static final IHammerRegistry HAMMER_REGISTRY = new HammerRegistry();
    @Nonnull public static final IHeatRegistry HEAT_REGISTRY = new HeatRegistry();
    @Nonnull public static final ISieveRegistry SIEVE_REGISTRY = new SieveRegistry();

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
