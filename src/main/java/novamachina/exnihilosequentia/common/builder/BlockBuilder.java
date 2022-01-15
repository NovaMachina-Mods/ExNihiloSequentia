package novamachina.exnihilosequentia.common.builder;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.function.Supplier;

public class BlockBuilder {

    public static final BlockBehaviour.Properties DEFAULT =
            BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);
    private BlockBehaviour.Properties properties = DEFAULT;
    private ToolAction toolType = ToolActions.PICKAXE_DIG;

    public BlockBehaviour.Properties getProperties() {
        return properties;
    }

    public ToolAction getToolType() {
        return toolType;
    }

    public BlockBuilder harvestLevel(ToolAction type) {
        this.toolType = type;
        return this;
    }

    public BlockBuilder properties(BlockBehaviour.Properties properties) {
        this.properties = properties;
        return this;
    }

}
