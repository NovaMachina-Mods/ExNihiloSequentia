package novamachina.exnihilosequentia.api.crafting;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;

public abstract class ExNihiloFinishedRecipe<R extends ExNihiloFinishedRecipe<R>> implements FinishedRecipe {
    protected JsonArray conditions = null;
    protected JsonArray inputArray = null;
    protected int inputCount = 0;
    protected int maxInputCount = 1;
    protected int maxOutputCount = 1;
    protected JsonArray outputArray = null;
    protected int outputCount = 0;
    private ResourceLocation id;
    private final RecipeSerializer<?> serializer;
    private final List<Consumer<JsonObject>> writerFunctions;

    protected ExNihiloFinishedRecipe(RecipeSerializer<?> serializer) {
        this.serializer = serializer;
        this.writerFunctions = new ArrayList<>();
    }

    public R addFluid(String id, FluidStack fluidStack) {
        return addWriter(jsonObject -> jsonObject.add(id, FluidStackUtils.jsonSerializeFluidStack(fluidStack)));
    }

    public R addResult(ItemStack itemStack) {
        if (outputArray != null) {
            return addMultiResult(serializeItemStack(itemStack));
        } else {
            return addItem("result", itemStack);
        }
    }

    public R addResult(Item result) {
        return addResult(new ItemStack(result));
    }

    public R addWriter(Consumer<JsonObject> writer) {
        Preconditions.checkArgument(id == null, "This recipe has already been finalized.");
        this.writerFunctions.add(writer);
        return (R) this;
    }

    public void build(Consumer<FinishedRecipe> out, ResourceLocation id) {
        Preconditions.checkArgument(isComplete(), "This recipe is incomplete.");
        this.id = id;
        out.accept(this);
    }

    @Override
    public ResourceLocation getAdvancementId() {
        return null;
    }

    @Override
    public JsonObject serializeAdvancement() {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getType() {
        return serializer;
    }

    @Override
    public void serializeRecipeData(JsonObject json) {
        for (Consumer<JsonObject> writer : this.writerFunctions) {
            writer.accept(json);
        }
    }

    public R setMultipleResults(int maxResultCount) {
        this.outputArray = new JsonArray();
        this.maxOutputCount = maxResultCount;
        return addWriter(jsonObject -> jsonObject.add("results", outputArray));
    }

    protected R addBlock(Block block) {
        return addWriter(jsonObject -> jsonObject.addProperty("block", block.getRegistryName().toString()));
    }

    protected R addBoolean(String key, boolean value) {
        return addWriter(jsonObject -> jsonObject.addProperty(key, value));
    }

    protected R addFluid(Fluid fluid) {
        return this.addFluid("fluid", new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME));
    }

    protected R addFluid(String id, Fluid fluid) {
        return this.addFluid(id, new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME));
    }

    protected R addInput(ItemStack input) {
        if (inputArray != null) {
            return addMultiInput(serializeItemStack(input));
        } else {
            return addItem("input", input);
        }
    }

    protected R addInput(Ingredient input) {
        return addInput("input", input);
    }

    protected R addInput(String key, Ingredient input) {
        if (inputArray != null) {
            return addMultiInput(input.toJson());
        } else {
            return addItem(key, input);
        }
    }

    protected R addInput(Item input) {
        return addInput(new ItemStack(input));
    }

    protected R addInput(Tag.Named<Item> tag) {
        return addInput(Ingredient.of(tag));
    }

    protected R addInput(String id, Item block) {
        return this.addItem(id, new ItemStack(block));
    }

    protected R addResult(ItemStackWithChance itemStack) {
        if (outputArray != null) {
            return addMultiResult(itemStack.serialize());
        } else {
            return addItem("result", itemStack.serialize());
        }
    }

    protected boolean isComplete() {
        return true;
    }

    private R addItem(String key, ItemStack itemStack) {
        Preconditions.checkArgument(!itemStack.isEmpty(), "ItemStack cannot be empty.");
        return addWriter(jsonObj -> jsonObj.add(key, serializeItemStack(itemStack)));
    }

    private R addItem(String key, Ingredient ingredient) {
        return addWriter(jsonObj -> jsonObj.add(key, ingredient.toJson()));
    }

    private R addItem(String key, JsonElement obj) {
        return addWriter(jsonObj -> jsonObj.add(key, obj));
    }

    private R addMultiInput(JsonElement obj) {
        Preconditions.checkArgument(maxInputCount > 1, "This recipe does not support multiple inputs.");
        Preconditions
                .checkArgument(inputCount < maxInputCount, "This recipe can only have " + maxInputCount + "inputs.");
        inputArray.add(obj);
        inputCount++;
        return (R) this;
    }

    private R addMultiResult(JsonElement obj) {
        Preconditions.checkArgument(maxOutputCount > 1, "This recipe does not support multiple results.");
        Preconditions
                .checkArgument(outputCount < maxOutputCount, "This recipe can only have " + maxOutputCount + "results.");
        outputArray.add(obj);
        outputCount++;
        return (R) this;
    }

    private JsonObject serializeItemStack(ItemStack itemStack) {
        JsonObject obj = new JsonObject();
        obj.addProperty("item", itemStack.getItem().getRegistryName().toString());
        if (itemStack.getCount() > 1) {
            obj.addProperty("count", itemStack.getCount());
        }
        if (itemStack.hasTag()) {
            obj.addProperty("nbt", itemStack.getTag().toString());
        }
        return obj;
    }
}
