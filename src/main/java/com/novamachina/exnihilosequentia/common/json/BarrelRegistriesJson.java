package com.novamachina.exnihilosequentia.common.json;

import java.util.List;

public class BarrelRegistriesJson {
    @JsonRequired
    private final List<FluidOnTopJson> fluidOnTopRegistry;
    @JsonRequired
    private final List<FluidTransformJson> fluidTransformRegistry;
    @JsonRequired
    private final List<FluidBlockJson> fluidBlockRegistry;
    @JsonRequired
    private final List<CompostJson> compostRegistry;

    public BarrelRegistriesJson(List<FluidOnTopJson> fluidOnTopRegistry, List<FluidTransformJson> fluidTransformRegistry,
                                List<FluidBlockJson> fluidBlockRegistry, List<CompostJson> compostRegistry) {
        this.fluidOnTopRegistry = fluidOnTopRegistry;
        this.fluidTransformRegistry = fluidTransformRegistry;
        this.fluidBlockRegistry = fluidBlockRegistry;
        this.compostRegistry = compostRegistry;
    }

    public List<FluidOnTopJson> getFluidOnTopRegistry() {
        return fluidOnTopRegistry;
    }

    public List<FluidTransformJson> getFluidTransformRegistry() {
        return fluidTransformRegistry;
    }

    public List<FluidBlockJson> getFluidBlockRegistry() {
        return fluidBlockRegistry;
    }

    public List<CompostJson> getCompostRegistry() {
        return compostRegistry;
    }
}
