package com.novamachina.exnihilosequentia.common.json;

import com.novamachina.exnihilosequentia.common.item.tools.crook.CrookDropEntry;

public class CrookJson {
    @JsonRequired
    private final String result;
    @JsonRequired
    private final Float rarity;

    public CrookJson(String result, Float rarity) {
        this.result = result;
        this.rarity = rarity;
    }

    public CrookJson(CrookDropEntry entry) {
        this.result = entry.getItem().toString();
        this.rarity = entry.getRarity();
    }

    public String getResult() {
        return result;
    }

    public Float getRarity() {
        return rarity;
    }
}
