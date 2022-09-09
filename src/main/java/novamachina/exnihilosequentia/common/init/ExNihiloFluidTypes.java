package novamachina.exnihilosequentia.common.init;

import com.mojang.logging.LogUtils;
import javax.annotation.Nonnull;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.fluid.SeaWaterFluidType;
import novamachina.exnihilosequentia.common.fluid.WitchWaterFluidType;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class ExNihiloFluidTypes {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  private static final DeferredRegister<FluidType> FLUID_TYPES =
      DeferredRegister.create(Keys.FLUID_TYPES, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);

  public static final RegistryObject<FluidType> WITCH_WATER_FLUID_TYPE =
      FLUID_TYPES.register(
          "witch_water", () -> new WitchWaterFluidType(FluidType.Properties.create()));
  public static final RegistryObject<FluidType> SEA_WATER_FLUID_TYPE =
      FLUID_TYPES.register("sea_water", () -> new SeaWaterFluidType(FluidType.Properties.create()));

  public static void init(@Nonnull final IEventBus modEventBus) {
    logger.debug("Register fluid types");
    FLUID_TYPES.register(modEventBus);
  }
}
