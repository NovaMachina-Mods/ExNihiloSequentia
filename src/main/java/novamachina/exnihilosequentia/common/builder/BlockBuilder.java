package novamachina.exnihilosequentia.common.builder;

import java.util.function.Supplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockBuilder {

    @Nonnull public static final AbstractBlock.Properties DEFAULT =
            AbstractBlock.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);
    private int harvestLevel = 0;
    @Nonnull private AbstractBlock.Properties properties = DEFAULT;
    @Nullable private Supplier<TileEntity> tileEntitySupplier;
    @Nonnull private ToolType toolType = ToolType.PICKAXE;

    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Nonnull
    public AbstractBlock.Properties getProperties() {
        return properties;
    }

    @Nullable
    public Supplier<TileEntity> getTileEntitySupplier() {
        return tileEntitySupplier;
    }

    @Nonnull
    public ToolType getToolType() {
        return toolType;
    }

    @Nonnull
    public BlockBuilder harvestLevel(@Nonnull final ToolType type, final int level) {
        this.toolType = type;
        this.harvestLevel = level;
        return this;
    }

    @Nonnull
    public BlockBuilder properties(@Nonnull final AbstractBlock.Properties properties) {
        this.properties = properties;
        return this;
    }

    @Nonnull
    public BlockBuilder tileEntitySupplier(@Nonnull final Supplier<TileEntity> supplier) {
        this.tileEntitySupplier = supplier;
        return this;
    }

}
