package com.novamachina.exnihilosequentia.common.json;

public class CompostJson {
    @JsonRequired
    private final String entry;
    @JsonRequired
    private final Integer amount;

    public CompostJson(String entry, Integer amount) {
        this.entry = entry;
        this.amount = amount;
    }

    public String getEntry() {
        return entry;
    }

    public Integer getAmount() {
        return amount;
    }
}
