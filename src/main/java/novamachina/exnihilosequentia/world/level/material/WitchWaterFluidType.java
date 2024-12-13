package novamachina.exnihilosequentia.world.level.material;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import novamachina.exnihilosequentia.ExNihiloSequentia;

public class WitchWaterFluidType extends FluidType {

  public static final ResourceLocation STILL =
      ResourceLocation.fromNamespaceAndPath(ExNihiloSequentia.MOD_ID, "block/witch_water");
  private static final ResourceLocation FLOW =
      ResourceLocation.fromNamespaceAndPath(ExNihiloSequentia.MOD_ID, "block/witch_water_flow");

  public WitchWaterFluidType(Properties properties) {
    super(properties);
  }

  public static IClientFluidTypeExtensions fluidTextures() {
    return new IClientFluidTypeExtensions() {
      @Override
      public ResourceLocation getStillTexture() {
        return STILL;
      }

      @Override
      public ResourceLocation getFlowingTexture() {
        return FLOW;
      }
    };
  }
}
