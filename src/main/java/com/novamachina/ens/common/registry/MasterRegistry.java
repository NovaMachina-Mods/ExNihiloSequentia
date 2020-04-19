package com.novamachina.ens.common.registry;

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
    public void register(String key, IRegistry<?> value) {
        registry.put(key, value);
    }

    @Override
    protected void useDefaultRegistry() {
        register("CROOK_REGISTRY", new CrookRegistry());
        register("HAMMER_REGISTRY", new HammerRegistry());
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
