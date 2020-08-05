package com.novamachina.exnihilosequentia.common.json;

import com.novamachina.exnihilosequentia.common.tileentity.crucible.Meltable;

public class CrucibleJson {
    @JsonRequired
    private final String entry;
    @JsonRequired
    private final String fluid;
    @JsonRequired
    private final int amount;

    public CrucibleJson(String entry, Meltable value) {
        this.entry = entry;
        this.fluid = value.getFluid().getRegistryName().toString();
        this.amount = value.getAmount();
    }

    public CrucibleJson(String entry, String fluid, int value) {
        this.entry = entry;
        this.fluid = fluid;
        this.amount = value;
    }

    public String getEntry() {
        return entry;
    }

    public String getFluid() {
        return fluid;
    }

    public int getAmount() {
        return amount;
    }
}
