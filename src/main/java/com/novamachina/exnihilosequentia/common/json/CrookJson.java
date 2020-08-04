package com.novamachina.exnihilosequentia.common.json;

import com.novamachina.exnihilosequentia.common.item.tools.crook.CrookDropEntry;

public class CrookJson {
    private final String result;
    private final double rarity;

    public CrookJson(String result, double rarity) {
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

    public double getRarity() {
        return rarity;
    }
}
