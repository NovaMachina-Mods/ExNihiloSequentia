package novamachina.exnihilosequentia.common.builder;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nonnull;

public class BlockBuilder {

    @Nonnull public static final BlockBehaviour.Properties DEFAULT =
            BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);
    @Nonnull private BlockBehaviour.Properties properties = DEFAULT;

    @Nonnull
    public BlockBehaviour.Properties getProperties() {
        return properties;
    }


    @Nonnull
    public BlockBuilder properties(@Nonnull final BlockBehaviour.Properties properties) {
        this.properties = properties;
        return this;
    }

}
