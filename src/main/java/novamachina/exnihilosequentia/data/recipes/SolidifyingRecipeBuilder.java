package novamachina.exnihilosequentia.data.recipes;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.novacore.data.recipes.RecipeBuilder;
import novamachina.novacore.util.FluidStackUtils;
import novamachina.novacore.util.ItemStackHelper;
import org.jetbrains.annotations.Nullable;

public class SolidifyingRecipeBuilder extends RecipeBuilder<SolidifyingRecipeBuilder> {
  private final FluidStack fluidInTank;
  private final FluidStack fluidOnTop;
  private final ItemStack result;

  protected SolidifyingRecipeBuilder(
      FluidStack fluidInTank, FluidStack fluidOnTop, ItemStack result) {
    //noinspection ConstantConditions
    super(EXNRecipeSerializers.SOLIDIFYING_RECIPE_SERIALIZER.recipeSerializer());
    this.fluidInTank = fluidInTank;
    this.fluidOnTop = fluidOnTop;
    this.result = result;
  }

  public static SolidifyingRecipeBuilder solidify(
      FluidStack fluidInTank, FluidStack fluidOnTop, ItemLike result) {
    return new SolidifyingRecipeBuilder(fluidInTank, fluidOnTop, new ItemStack(result));
  }

  @Override
  protected void validate(ResourceLocation id) {
    Preconditions.checkNotNull(fluidInTank, "Fluid in barrel cannot be null");
    Preconditions.checkArgument(!fluidInTank.isEmpty(), "Fluid in barrel amount cannot be 0");
    Preconditions.checkNotNull(fluidOnTop, "Fluid on top cannot be null");
    Preconditions.checkArgument(!fluidOnTop.isEmpty(), "Fluid on top amount cannot be 0");
    Preconditions.checkNotNull(result, "Result cannot be null.");
  }

  @Override
  protected SolidifyingRecipeResult getResult(ResourceLocation id) {
    return new SolidifyingRecipeResult(id);
  }

  public class SolidifyingRecipeResult extends RecipeResult {

    public SolidifyingRecipeResult(ResourceLocation id) {
      super(id);
    }

    @Override
    public void serializeRecipeData(JsonObject json) {
      json.add("fluidInTank", FluidStackUtils.serialize(fluidInTank));
      json.add("fluidOnTop", FluidStackUtils.serialize(fluidOnTop));
      json.add("result", ItemStackHelper.serialize(result));
    }

    @Nullable
    @Override
    public AdvancementHolder advancement() {
      return null;
    }
  }
}
