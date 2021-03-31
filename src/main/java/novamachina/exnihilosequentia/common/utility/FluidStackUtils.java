package novamachina.exnihilosequentia.common.utility;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.JsonUtils;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidStackUtils {
    private FluidStackUtils() {
    }

    public static FluidStack jsonDeserializeFluidStack(JsonObject jsonObject) {
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(JSONUtils.getAsString(jsonObject, "fluid")));
        FluidStack fluidStack = new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME);
        if (JSONUtils.isValidNode(jsonObject, "tag"))
            fluidStack.setTag(JsonUtils.readNBT(jsonObject, "tag"));
        return fluidStack;
    }

    public static JsonElement jsonSerializeFluidStack(FluidStack fluidStack) {
        if (fluidStack == null)
            return JsonNull.INSTANCE;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("fluid", fluidStack.getFluid().getRegistryName().toString());
        if (fluidStack.hasTag())
            jsonObject.addProperty("tag", fluidStack.getTag().toString());
        return jsonObject;
    }
}
