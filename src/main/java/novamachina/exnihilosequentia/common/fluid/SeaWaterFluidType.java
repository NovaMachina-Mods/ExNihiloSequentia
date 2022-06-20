package novamachina.exnihilosequentia.common.fluid;

import java.util.function.Consumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.IFluidTypeRenderProperties;
import net.minecraftforge.fluids.FluidType;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class SeaWaterFluidType extends FluidType {

  public static final ResourceLocation STILL = new ResourceLocation(
      ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/sea_water");
  private static final ResourceLocation FLOW = new ResourceLocation(
      ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "block/sea_water_flow");

  public SeaWaterFluidType(Properties properties) {
    super(properties);
  }

  @Override
  public void initializeClient(Consumer<IFluidTypeRenderProperties> consumer) {
    consumer.accept(new IFluidTypeRenderProperties() {
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
