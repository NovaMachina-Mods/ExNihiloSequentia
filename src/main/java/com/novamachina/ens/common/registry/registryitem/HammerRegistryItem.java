package com.novamachina.ens.common.registry.registryitem;

import net.minecraft.block.Block;

public class HammerRegistryItem extends IRegistryItem<Block> {

    private final Block inputBlock;

    public HammerRegistryItem(Block inputBlock, Block resultBlock, double rarity) {
        super(resultBlock, rarity);
        this.inputBlock = inputBlock;
    }

    public Block getInputBlock() {
        return inputBlock;
    }
}
