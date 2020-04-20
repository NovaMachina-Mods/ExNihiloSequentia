package com.novamachina.ens.common.registry;

import com.novamachina.ens.common.utility.Constants;

public class MasterRegistry extends IRegistry<IRegistry<?>> {

    public MasterRegistry() {
        initRegistry();
    }

    private static MasterRegistry INSTANCE;

    public static MasterRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MasterRegistry();
        }
        return INSTANCE;
    }

    @Override
    protected void useJsonRegistry() {
        useDefaultRegistry();
    }

    @Override
    protected void useDefaultRegistry() {
        register(Constants.Registry.CROOK_REGISTRY, new CrookRegistry());
        register(Constants.Registry.HAMMER_REGISTRY, new HammerRegistry());
        register(Constants.Registry.ORE_REGISTRY, new OreRegistry());
    }

    public IRegistry<?> getRegistry(String registryName) {
        return registry.get(registryName);
    }

    @Override
    public void initRegistry() {
        super.initRegistry();

        for (IRegistry<?> registry : registry.values()) {
            registry.initRegistry();
        }
    }
}
