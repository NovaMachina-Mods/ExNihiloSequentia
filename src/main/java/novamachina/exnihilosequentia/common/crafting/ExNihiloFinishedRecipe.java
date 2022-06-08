package novamachina.exnihilosequentia.common.crafting;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

public abstract class ExNihiloFinishedRecipe<R extends ExNihiloFinishedRecipe<R>> implements
    FinishedRecipe {

  @Nonnull
  private final RecipeSerializer<?> serializer;
  @Nonnull
  private final List<Consumer<JsonObject>> writerFunctions;
  @Nullable
  protected JsonArray conditions = null;
  @Nullable
  protected JsonArray inputArray = null;
  protected int inputCount = 0;
  protected int maxInputCount = 1;
  protected int maxOutputCount = 1;
  @Nullable
  protected JsonArray outputArray = null;
  protected int outputCount = 0;
  @Nullable
  private ResourceLocation id;

  protected ExNihiloFinishedRecipe(@Nonnull final RecipeSerializer<?> serializer) {
    this.serializer = serializer;
    this.writerFunctions = new ArrayList<>();
  }

  @Nonnull
  public R addFluid(@Nonnull final String id, @Nonnull final FluidStack fluidStack) {
    return addWriter(
        jsonObject -> jsonObject.add(id, FluidStackUtils.jsonSerializeFluidStack(fluidStack)));
  }

  @Nonnull
  public R addResult(@Nonnull final ItemStack itemStack) {
    if (outputArray != null) {
      return addMultiResult(serializeItemStack(itemStack));
    } else {
      return addItem("result", itemStack);
    }
  }

  @Nonnull
  public R addResult(@Nonnull final ItemLike result) {
    return addResult(new ItemStack(result));
  }

  @Nonnull
  public R addWriter(@Nonnull final Consumer<JsonObject> writer) throws IllegalArgumentException {
    Preconditions.checkArgument(id == null, "This recipe has already been finalized.");
    this.writerFunctions.add(writer);
    //noinspection unchecked
    return (R) this;
  }

  public void build(@Nonnull final Consumer<FinishedRecipe> out, @Nonnull final ResourceLocation id)
      throws IllegalArgumentException {
    Preconditions.checkArgument(isComplete(), "This recipe is incomplete.");
    this.id = id;
    out.accept(this);
  }

  @Override
  @Nullable
  public ResourceLocation getAdvancementId() {
    return null;
  }

  @Override
  @Nullable
  public JsonObject serializeAdvancement() {
    return null;
  }

  @Nonnull
  @Override
  public ResourceLocation getId() throws NullPointerException {
    Preconditions.checkNotNull(id, "This recipe has no id");
    return id;
  }

  @Override
  @Nonnull
  public RecipeSerializer<?> getType() {
    return serializer;
  }

  @Override
  public void serializeRecipeData(@Nonnull final JsonObject json) {
    for (@Nonnull final Consumer<JsonObject> writer : this.writerFunctions) {
      writer.accept(json);
    }
  }

  @Nonnull
  @SuppressWarnings("UnusedReturnValue")
  public R setMultipleResults(final int maxResultCount) {
    this.outputArray = new JsonArray();
    this.maxOutputCount = maxResultCount;
    return addWriter(jsonObject -> jsonObject.add("results", outputArray));
  }

  @Nonnull
  protected R addBlock(@Nonnull final Block block) {
    @Nullable final ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(block);
    if (resourceLocation != null) {
      return addWriter(jsonObject -> jsonObject.addProperty("block", resourceLocation.toString()));
    }
    //noinspection unchecked
    return (R) this;
  }

  @Nonnull
  @SuppressWarnings("SameParameterValue")
  protected R addBoolean(@Nonnull final String key, final boolean value) {
    return addWriter(jsonObject -> jsonObject.addProperty(key, value));
  }

  @Nonnull
  protected R addFluid(@Nonnull final Fluid fluid) {
    return this.addFluid("fluid", new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME));
  }

  @Nonnull
  protected R addFluid(@Nonnull final String id, @Nonnull final Fluid fluid) {
    return this.addFluid(id, new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME));
  }

  @Nonnull
  protected R addInput(@Nonnull final ItemStack input) {
    if (inputArray != null) {
      return addMultiInput(serializeItemStack(input));
    } else {
      return addItem("input", input);
    }
  }

  @Nonnull
  protected R addInput(@Nonnull final Ingredient input) {
    return addInput("input", input);
  }

  @Nonnull
  protected R addInput(@Nonnull final String key, @Nonnull final Ingredient input) {
    if (inputArray != null) {
      return addMultiInput(input.toJson());
    } else {
      return addItem(key, input);
    }
  }

  @Nonnull
  protected R addInput(@Nonnull final ItemLike input) {
    return addInput(new ItemStack(input));
  }

  @Nonnull
  protected R addInput(@Nonnull final TagKey<Item> tag) {
    return addInput(Ingredient.of(tag));
  }

  @Nonnull
  @SuppressWarnings("unused")
  protected R addInput(@Nonnull final String id, @Nonnull final ItemLike block) {
    return this.addItem(id, new ItemStack(block));
  }

  @Nonnull
  protected R addResult(@Nonnull final ItemStackWithChance itemStack) {
    if (outputArray != null) {
      return addMultiResult(itemStack.serialize());
    } else {
      return addItem("result", itemStack.serialize());
    }
  }

  protected boolean isComplete() {
    return true;
  }

  @Nonnull
  private R addItem(@Nonnull final String key, @Nonnull final ItemStack itemStack)
      throws IllegalArgumentException {
    Preconditions.checkArgument(!itemStack.isEmpty(), "ItemStack cannot be empty.");
    return addWriter(jsonObj -> jsonObj.add(key, serializeItemStack(itemStack)));
  }

  @Nonnull
  private R addItem(@Nonnull final String key, @Nonnull final Ingredient ingredient) {
    return addWriter(jsonObj -> jsonObj.add(key, ingredient.toJson()));
  }

  @Nonnull
  @SuppressWarnings("SameParameterValue")
  private R addItem(@Nonnull final String key, @Nonnull final JsonElement obj) {
    return addWriter(jsonObj -> jsonObj.add(key, obj));
  }

  @Nonnull
  private R addMultiInput(@Nonnull final JsonElement obj) throws IllegalArgumentException {
    Preconditions.checkArgument(maxInputCount > 1, "This recipe does not support multiple inputs.");
    Preconditions
        .checkArgument(inputCount < maxInputCount,
            "This recipe can only have " + maxInputCount + "inputs.");
    if (inputArray != null) {
      inputArray.add(obj);
      inputCount++;
    }
    //noinspection unchecked
    return (R) this;
  }

  @Nonnull
  private R addMultiResult(@Nonnull final JsonElement obj) throws IllegalArgumentException {
    Preconditions.checkArgument(maxOutputCount > 1,
        "This recipe does not support multiple results.");
    Preconditions
        .checkArgument(outputCount < maxOutputCount,
            "This recipe can only have " + maxOutputCount + "results.");
    if (outputArray != null) {
      outputArray.add(obj);
      outputCount++;
    }
    //noinspection unchecked
    return (R) this;
  }

  @Nonnull
  private JsonObject serializeItemStack(@Nonnull final ItemStack itemStack) {
    @Nonnull final JsonObject obj = new JsonObject();
    @Nullable final ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(itemStack.getItem());
    if (resourceLocation != null) {
      obj.addProperty("item", resourceLocation.toString());
    }
    if (itemStack.getCount() > 1) {
      obj.addProperty("count", itemStack.getCount());
    }
    if (itemStack.hasTag() && itemStack.getTag() != null) {
      obj.addProperty("nbt", itemStack.getTag().toString());
    }
    return obj;
  }
}
