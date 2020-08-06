package com.novamachina.exnihilosequentia.common.setup;

import com.novamachina.exnihilosequentia.common.utility.LogUtil;

public abstract class AbstractModRegistry {
    public abstract void clear();

    public abstract Object toJSONReady();

    public void initialize(boolean useJson) {
        if (useJson) {
            LogUtil.info("Using JSON");
            useJson();
        } else {
            LogUtil.info("Using Defaults");
            useDefaults();
        }
    }

    protected abstract void useDefaults();

    protected abstract void useJson();
}
