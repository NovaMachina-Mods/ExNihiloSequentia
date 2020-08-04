package com.novamachina.exnihilosequentia.common.setup;

import com.novamachina.exnihilosequentia.common.tileentity.barrel.compost.CompostJSON;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid.FluidBlockJSON;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid.FluidOnTopJSON;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.transform.FluidTransformJSON;

import java.util.List;

public class BarrelRegistriesJSON {
    private List<FluidOnTopJSON> fluidOnTopRegistry;
    private List<FluidTransformJSON> fluidTransformRegistry;
    private List<FluidBlockJSON> fluidBlockRegistry;
    private List<CompostJSON> compostRegistry;

    public BarrelRegistriesJSON(List<FluidOnTopJSON> fluidOnTopRegistry, List<FluidTransformJSON> fluidTransformRegistry,
                                List<FluidBlockJSON> fluidBlockRegistry, List<CompostJSON> compostRegistry) {
        this.fluidOnTopRegistry = fluidOnTopRegistry;
        this.fluidTransformRegistry = fluidTransformRegistry;
        this.fluidBlockRegistry = fluidBlockRegistry;
        this.compostRegistry = compostRegistry;
    }

    public List<FluidOnTopJSON> getFluidOnTopRegistry() {
        return fluidOnTopRegistry;
    }

    public List<FluidTransformJSON> getFluidTransformRegistry() {
        return fluidTransformRegistry;
    }

    public List<FluidBlockJSON> getFluidBlockRegistry() {
        return fluidBlockRegistry;
    }

    public List<CompostJSON> getCompostRegistry() {
        return compostRegistry;
    }
}
