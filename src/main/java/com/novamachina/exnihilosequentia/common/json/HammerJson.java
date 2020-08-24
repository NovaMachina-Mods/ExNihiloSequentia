package com.novamachina.exnihilosequentia.common.json;

public class HammerJson {
    @JsonRequired
    private final String input;
    @JsonRequired
    private final String output;

    public HammerJson(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }
}
