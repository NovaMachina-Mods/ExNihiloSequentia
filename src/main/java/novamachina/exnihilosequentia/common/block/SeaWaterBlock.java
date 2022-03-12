package novamachina.exnihilosequentia.common.block;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Material;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;

import javax.annotation.Nonnull;
import java.util.Optional;

public class SeaWaterBlock extends LiquidBlock {
    public SeaWaterBlock() {
        super(ExNihiloFluids.SEA_WATER_STILL, BlockBehaviour.Properties.of(Material.WATER).noCollission()
                .strength(100.0F).noDrops());
    }

    @Nonnull
    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }
}
