package com.novamachina.exnihilosequentia.common.json;

public class CompostJson {
    private final String entry;
    private final int amount;

    public CompostJson(String entry, int amount) {
        this.entry = entry;
        this.amount = amount;
    }

    public String getEntry() {
        return entry;
    }

    public int getAmount() {
        return amount;
    }
}
