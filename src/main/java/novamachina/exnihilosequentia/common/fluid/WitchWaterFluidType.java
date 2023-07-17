package novamachina.exnihilosequentia.common.fluid;

import java.util.function.Consumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class WitchWaterFluidType extends FluidType {

  public static final ResourceLocation STILL =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/witch_water");
  private static final ResourceLocation FLOW =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/witch_water_flow");

  public WitchWaterFluidType(Properties properties) {
    super(properties);
  }

  @Override
  public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
    consumer.accept(
        new IClientFluidTypeExtensions() {
          @Override
          public ResourceLocation getStillTexture() {
            return STILL;
          }

          @Override
          public ResourceLocation getFlowingTexture() {
            return FLOW;
          }
        });
  }
}
