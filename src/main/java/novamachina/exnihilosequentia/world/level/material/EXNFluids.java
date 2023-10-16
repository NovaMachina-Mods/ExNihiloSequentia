package novamachina.exnihilosequentia.world.level.material;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.core.registries.FluidRegistry;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;
import novamachina.novacore.world.level.material.FluidDefinition;

public class EXNFluids {

  private static final FluidRegistry FLUIDS =
      new FluidRegistry(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);

  @Nonnull
  public static final FluidDefinition<
          FlowingFluid, BlockDefinition<? extends LiquidBlock>, ItemDefinition<? extends Item>>
      WITCH_WATER =
          FLUIDS.fluid(
              "Witch Water",
              "witch_water",
              () -> new WitchWaterFluid.Source(WitchWaterFluid.WITCH_WATER_PROPS),
              () -> new WitchWaterFluid.Flowing(WitchWaterFluid.WITCH_WATER_PROPS),
              () -> EXNBlocks.WITCH_WATER,
              () -> EXNItems.WITCH_WATER_BUCKET);

  @Nonnull
  public static final FluidDefinition<
          FlowingFluid, BlockDefinition<? extends LiquidBlock>, ItemDefinition<? extends Item>>
      SEA_WATER =
          FLUIDS.fluid(
              "Sea Water",
              "sea_water",
              () -> new SeaWaterFluid.Source(SeaWaterFluid.SEA_WATER_PROPS),
              () -> new SeaWaterFluid.Flowing(SeaWaterFluid.SEA_WATER_PROPS),
              () -> EXNBlocks.SEA_WATER,
              () -> EXNItems.SEA_WATER_BUCKET);

  private EXNFluids() {}

  public static List<FluidDefinition<?, ?, ?>> getDefinitions() {
    return FLUIDS.getRegistry();
  }
}
