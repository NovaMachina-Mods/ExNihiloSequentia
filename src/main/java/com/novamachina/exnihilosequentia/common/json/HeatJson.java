package com.novamachina.exnihilosequentia.common.json;

public class HeatJson {
    @JsonRequired
    private final String entry;
    @JsonRequired
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
