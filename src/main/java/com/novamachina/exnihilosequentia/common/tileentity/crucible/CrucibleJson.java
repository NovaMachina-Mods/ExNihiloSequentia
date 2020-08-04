package com.novamachina.exnihilosequentia.common.tileentity.crucible;

public class CrucibleJson {
    private final String entry;
    private final String fluid;
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
