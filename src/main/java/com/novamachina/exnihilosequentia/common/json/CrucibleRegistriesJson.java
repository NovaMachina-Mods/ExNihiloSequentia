package com.novamachina.exnihilosequentia.common.json;

import java.util.List;

public class CrucibleRegistriesJson {
    @JsonRequired
    private final List<CrucibleJson> firedCrucibleRegistry;
    @JsonRequired
    private final List<CrucibleJson> woodCrucibleRegistry;
    @JsonRequired
    private final List<HeatJson> heatRegistry;

    public CrucibleRegistriesJson(List<CrucibleJson> firedCrucibleRegistry, List<CrucibleJson> woodCrucibleRegistry, List<HeatJson> heatRegistry) {
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
