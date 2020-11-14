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
import novamachina.exnihilosequentia.common.registries.FluidItemTransformRegistry;
import novamachina.exnihilosequentia.common.registries.FluidOnTopRegistry;
import novamachina.exnihilosequentia.common.registries.FluidTransformRegistry;
import novamachina.exnihilosequentia.common.registries.CrookRegistry;
import novamachina.exnihilosequentia.common.registries.CrucibleRegistry;
import novamachina.exnihilosequentia.common.registries.HeatRegistry;
import novamachina.exnihilosequentia.common.registries.HammerRegistry;
import novamachina.exnihilosequentia.common.registries.SieveRegistry;

public class ExNihiloRegistries {
    public static ICrookRegistry CROOK_REGISTRY = new CrookRegistry();
    public static ICompostRegistry COMPOST_REGISTRY = new CompostRegistry();
    public static IHammerRegistry HAMMER_REGISTRY = new HammerRegistry();
    public static ISieveRegistry SIEVE_REGISTRY = new SieveRegistry();
    public static IHeatRegistry HEAT_REGISTRY = new HeatRegistry();
    public static ICrucibleRegistry CRUCIBLE_REGISTRY = new CrucibleRegistry();
    public static IFluidOnTopRegistry FLUID_ON_TOP_REGISTRY = new FluidOnTopRegistry();
    public static IFluidTransformRegistry FLUID_TRANSFORM_REGISTRY = new FluidTransformRegistry();
    public static IFluidItemTransformRegistry FLUID_BLOCK_REGISTRY = new FluidItemTransformRegistry();
}
