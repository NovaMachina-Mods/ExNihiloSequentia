package com.novamachina.ens.common.registry.registryitem;

import net.minecraft.item.Item;

public abstract class IRegistryItem<T> {

    private final T registryItem;

    public IRegistryItem(T registryItem) {
        this.registryItem = registryItem;
    }

    public T getItem() {
        return registryItem;
    }
}
