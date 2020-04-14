package com.novamachina.ens.common.registry.registryitem;

import com.novamachina.ens.common.registry.IRegistry;
import com.novamachina.ens.common.registry.defaults.DefaultHammerRegistry;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;

public class HammerRegistry extends IRegistry<HammerRegistryItem> {

    private final Map<Block, Block> blockMap        = new HashMap<>();
    private final Map<Block, Block> nonSilkTouchMap = new HashMap<>();

    @Override
    public void register(HammerRegistryItem value) {
        blockMap.put(value.getInputBlock(), value.getItem());
    }

    public void registerNonSilkTouch(HammerRegistryItem value) {
        nonSilkTouchMap.put(value.getInputBlock(), value.getItem());
    }

    @Override
    protected void useDefaultRegistry() {
        new DefaultHammerRegistry(this).init();
    }

    public Block getResult(Block input) {
        if (blockMap.containsKey(input)) {
            return blockMap.get(input);
        }
        //TODO: Figure out how to do this base on block tags
        if (nonSilkTouchMap.containsKey(input)) {
            return nonSilkTouchMap.get(input);
        }
        return input;
    }
}
