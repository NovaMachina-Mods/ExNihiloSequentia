package novamachina.exnihilosequentia.common.builder;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

public class BlockBuilder {

    public static final AbstractBlock.Properties DEFAULT =
        AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD);

    private AbstractBlock.Properties properties = DEFAULT;
    private Supplier<TileEntity> tileEntitySupplier;
    private ToolType toolType = ToolType.PICKAXE;
    private int harvestLevel = 0;

    public AbstractBlock.Properties getProperties() {
        return properties;
    }

    public Supplier<TileEntity> getTileEntitySupplier() {
        return tileEntitySupplier;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public BlockBuilder properties(AbstractBlock.Properties properties) {
        this.properties = properties;
        return this;
    }

    public BlockBuilder tileEntitySupplier(Supplier<TileEntity> supplier) {
        this.tileEntitySupplier = supplier;
        return this;
    }

    public BlockBuilder harvestLevel(ToolType type, int level) {
        this.toolType = type;
        this.harvestLevel = level;
        return this;
    }

}
