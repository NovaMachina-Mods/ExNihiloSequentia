package com.novamachina.exnihilosequentia.common.setup;

import com.novamachina.exnihilosequentia.common.tileentity.crucible.HeatJson;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.CrucibleJson;

import java.util.List;

public class CrucibleRegistriesJSON {
    private final List<CrucibleJson> firedCrucibleRegistry;
    private final List<CrucibleJson> woodCrucibleRegistry;
    private final List<HeatJson> heatRegistry;

    public CrucibleRegistriesJSON(List<CrucibleJson> firedCrucibleRegistry, List<CrucibleJson> woodCrucibleRegistry, List<HeatJson> heatRegistry) {
        this.firedCrucibleRegistry = firedCrucibleRegistry;
        this.woodCrucibleRegistry = woodCrucibleRegistry;
        this.heatRegistry = heatRegistry;
    }

    public List<HeatJson> getHeatRegistry() {
        return heatRegistry;
    }

    public List<CrucibleJson> getFiredCrucibleRegistry() {
        return firedCrucibleRegistry;
    }

    public List<CrucibleJson> getWoodCrucibleRegistry() {
        return woodCrucibleRegistry;
    }
}
