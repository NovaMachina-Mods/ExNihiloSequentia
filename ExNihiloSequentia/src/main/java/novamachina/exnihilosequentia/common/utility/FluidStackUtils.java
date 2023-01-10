package novamachina.exnihilosequentia.common.utility;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.util.JsonUtils;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidStackUtils {

  private FluidStackUtils() {}

  @Nullable
  public static FluidStack jsonDeserializeFluidStack(@Nonnull final JsonObject jsonObject) {
    @Nullable
    final Fluid fluid =
        ForgeRegistries.FLUIDS.getValue(
            new ResourceLocation(GsonHelper.getAsString(jsonObject, "fluid")));
    if (fluid == null) {
      return null;
    }
    FluidStack fluidStack = new FluidStack(fluid, FluidType.BUCKET_VOLUME);
    if (GsonHelper.isValidNode(jsonObject, "tag")) {
      fluidStack.setTag(JsonUtils.readNBT(jsonObject, "tag"));
    }
    return fluidStack;
  }

  @Nonnull
  public static JsonElement jsonSerializeFluidStack(@Nullable final FluidStack fluidStack) {
    if (fluidStack == null) {
      return JsonNull.INSTANCE;
    }
    JsonObject jsonObject = new JsonObject();
    if (ForgeRegistries.FLUIDS.getKey(fluidStack.getFluid()) != null) {
      jsonObject.addProperty(
          "fluid", ForgeRegistries.FLUIDS.getKey(fluidStack.getFluid()).toString());
    }
    if (fluidStack.hasTag()) {
      jsonObject.addProperty("tag", fluidStack.getTag().toString());
    }
    return jsonObject;
  }
}
