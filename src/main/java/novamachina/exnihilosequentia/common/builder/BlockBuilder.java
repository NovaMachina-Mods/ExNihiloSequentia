package novamachina.exnihilosequentia.common.builder;

import java.util.function.Supplier;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class BlockBuilder {

    public static final BlockBehaviour.Properties DEFAULT =
            BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);
    private int harvestLevel = 0;
    private BlockBehaviour.Properties properties = DEFAULT;
    private Supplier<BlockEntity> tileEntitySupplier;
    private ToolAction toolType = ToolActions.PICKAXE_DIG;

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public BlockBehaviour.Properties getProperties() {
        return properties;
    }

    public Supplier<BlockEntity> getTileEntitySupplier() {
        return tileEntitySupplier;
    }

    public ToolAction getToolType() {
        return toolType;
    }

    public BlockBuilder harvestLevel(ToolAction type, int level) {
        this.toolType = type;
        this.harvestLevel = level;
        return this;
    }

    public BlockBuilder properties(BlockBehaviour.Properties properties) {
        this.properties = properties;
        return this;
    }

    public BlockBuilder tileEntitySupplier(Supplier<BlockEntity> supplier) {
        this.tileEntitySupplier = supplier;
        return this;
    }

}
