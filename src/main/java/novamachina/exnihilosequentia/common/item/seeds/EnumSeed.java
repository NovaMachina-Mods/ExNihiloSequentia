package novamachina.exnihilosequentia.common.item.seeds;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum EnumSeed implements IExtensibleEnum {

    SEED_FERN(ExNihiloConstants.Items.FERN_SEED, Blocks.FERN.defaultBlockState(), PlantType.PLAINS),
    SEED_LARGE_FERN(ExNihiloConstants.Items.SEED_LARGE_FERN, Blocks.LARGE_FERN.defaultBlockState(), PlantType.PLAINS);

    @Nonnull private final BlockState defaultState;
    @Nonnull private final PlantType plantType;
    @Nonnull private final String seedName;
    @Nullable private RegistryObject<Item> registryObject;

    EnumSeed(@Nonnull final String seedName, @Nonnull final BlockState defaultState,
             @Nonnull final PlantType plantType) {
        this.seedName = seedName;
        this.defaultState = defaultState;
        this.plantType = plantType;
    }

    @Nonnull
    public static EnumSeed create(@Nonnull final String enumName, @Nonnull final String seedName,
                                  @Nonnull final BlockState defaultState, @Nonnull final PlantType plantType) {
        throw new IllegalStateException("Enum not extended");
    }

    @Nonnull
    public BlockState getDefaultState() {
        return defaultState;
    }

    @Nonnull
    public PlantType getPlantType() {
        return plantType;
    }

    @Nullable
    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(@Nonnull final RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }

    @Nonnull
    public String getSeedName() {
        return seedName + "_seed";
    }
}
