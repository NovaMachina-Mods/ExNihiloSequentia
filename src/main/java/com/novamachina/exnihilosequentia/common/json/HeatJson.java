package com.novamachina.exnihilosequentia.common.json;

public class HeatJson {
    @JsonRequired
    private final String entry;
    @JsonRequired
    private final Integer rate;

    public HeatJson(String entry, Integer rate) {
        this.entry = entry;
        this.rate = rate;
    }

    public Integer getRate() {
        return rate;
    }

    public String getEntry() {
        return entry;
    }
}
