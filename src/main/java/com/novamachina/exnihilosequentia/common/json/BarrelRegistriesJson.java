package com.novamachina.exnihilosequentia.common.json;

import java.util.List;

public class BarrelRegistriesJson {
    private List<FluidOnTopJson> fluidOnTopRegistry;
    private List<FluidTransformJson> fluidTransformRegistry;
    private List<FluidBlockJson> fluidBlockRegistry;
    private List<CompostJson> compostRegistry;

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
