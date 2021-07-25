package novamachina.exnihilosequentia.common.builder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockBuilder {

    public static final Block.Properties DEFAULT =
            Block.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);
    private int harvestLevel = 0;
    private Block.Properties properties = DEFAULT;
    private ToolType toolType = ToolType.PICKAXE;

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public Block.Properties getProperties() {
        return properties;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public BlockBuilder harvestLevel(ToolType type, int level) {
        this.toolType = type;
        this.harvestLevel = level;
        return this;
    }

    public BlockBuilder properties(Block.Properties properties) {
        this.properties = properties;
        return this;
    }

}
