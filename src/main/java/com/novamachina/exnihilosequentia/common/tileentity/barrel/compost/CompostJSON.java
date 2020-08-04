package com.novamachina.exnihilosequentia.common.tileentity.barrel.compost;

public class CompostJSON {
    private final String entry;
    private final int amount;

    public CompostJSON(String entry, int amount) {
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
