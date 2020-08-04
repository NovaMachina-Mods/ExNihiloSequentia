package com.novamachina.exnihilosequentia.common.tileentity.crucible;

public class HeatJson {
    private final String entry;
    private final int rate;

    public HeatJson(String entry, int rate) {
        this.entry = entry;
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public String getEntry() {
        return entry;
    }
}
