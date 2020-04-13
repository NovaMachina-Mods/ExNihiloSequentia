package com.novamachina.ens.common.registry.registryitem;

import net.minecraft.item.Item;

public abstract class IRegistryItem<T> {

    private final T registryItem;

    private final double rarity;

    public double getRarity() {
        return rarity;
    }

    public IRegistryItem(T registryItem, double rarity) {
        this.registryItem = registryItem;
        this.rarity       = rarity;
    }

    public T getItem() {
        return registryItem;
    }
}
