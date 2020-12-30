package novamachina.exnihilosequentia.api.crafting;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import novamachina.exnihilosequentia.common.utility.FluidStackUtils;
import net.minecraft.block.Block;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class ExNihiloFinishedRecipe<R extends ExNihiloFinishedRecipe<R>> implements IFinishedRecipe {
    protected JsonArray inputArray = null;
    protected int inputCount = 0;
    protected int maxInputCount = 1;
    protected JsonArray outputArray = null;
    protected int outputCount = 0;
    protected int maxOutputCount = 1;
    protected JsonArray conditions = null;
    private List<Consumer<JsonObject>> writerFunctions;
    private ResourceLocation id;
    private RecipeSerializer<?> serializer;

    protected ExNihiloFinishedRecipe(RecipeSerializer<?> serializer) {
        this.serializer = serializer;
        this.writerFunctions = new ArrayList<>();
    }

    @Override
    public void serialize(JsonObject json) {
        for (Consumer<JsonObject> writer : this.writerFunctions) {
            writer.accept(json);
        }
    }

    @Override
    public ResourceLocation getID() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return serializer;
    }

    @Override
    public JsonObject getAdvancementJson() {
        return null;
    }

    @Override
    public ResourceLocation getAdvancementID() {
        return null;
    }

    public void build(Consumer<IFinishedRecipe> out, ResourceLocation id) {
        Preconditions.checkArgument(isComplete(), "This recipe is incomplete.");
        this.id = id;
        out.accept(this);
    }

    protected boolean isComplete() {
        return true;
    }

    public R addResult(ItemStack itemStack) {
        if (outputArray != null) {
            return addMultiResult(serializeItemStack(itemStack));
        } else {
            return addItem("result", itemStack);
        }
    }

    public R addFluid(String id, FluidStack fluidStack) {
        return addWriter(jsonObject -> jsonObject.add(id, FluidStackUtils.jsonSerializeFluidStack(fluidStack)));
    }

    private R addItem(String key, ItemStack itemStack) {
        Preconditions.checkArgument(!itemStack.isEmpty(), "ItemStack cannot be empty.");
        return addWriter(jsonObj -> jsonObj.add(key, serializeItemStack(itemStack)));
    }

    private R addItem(String key, Ingredient ingredient) {
        return addWriter(jsonObj -> jsonObj.add(key, ingredient.serialize()));
    }

    public R addWriter(Consumer<JsonObject> writer) {
        Preconditions.checkArgument(id == null, "This recipe has already been finalized.");
        this.writerFunctions.add(writer);
        return (R) this;
    }

    public R setMultipleResults(int maxResultCount) {
        this.outputArray = new JsonArray();
        this.maxOutputCount = maxResultCount;
        return addWriter(jsonObject -> jsonObject.add("results", outputArray));
    }

    private R addMultiResult(JsonElement obj) {
        Preconditions.checkArgument(maxOutputCount > 1, "This recipe does not support multiple results.");
        Preconditions
            .checkArgument(outputCount < maxOutputCount, "This recipe can only have " + maxOutputCount + "results.");
        outputArray.add(obj);
        outputCount++;
        return (R) this;
    }

    private R addMultiInput(JsonElement obj) {
        Preconditions.checkArgument(maxInputCount > 1, "This recipe does not support multiple inputs.");
        Preconditions
            .checkArgument(inputCount < maxInputCount, "This recipe can only have " + maxInputCount + "inputs.");
        inputArray.add(obj);
        inputCount++;
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
            return addMultiInput(input.serialize());
        } else {
            return addItem(key, input);
        }
    }

    public R addResult(IItemProvider result) {
        return addResult(new ItemStack(result));
    }

    protected R addInput(IItemProvider input) {
        return addInput(new ItemStack(input));
    }

    protected R addInput(ITag.INamedTag<Item> tag) {
        return addInput(Ingredient.fromTag(tag));
    }

    protected R addResult(ItemStackWithChance itemStack) {
        if (outputArray != null) {
            return addMultiResult(itemStack.serialize());
        } else {
            return addItem("result", itemStack.serialize());
        }
    }

    private R addItem(String key, JsonElement obj) {
        return addWriter(jsonObj -> jsonObj.add(key, obj));
    }

    protected R addFluid(Fluid fluid) {
        return this.addFluid("fluid", new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME));
    }

    protected R addFluid(String id, Fluid fluid) {
        return this.addFluid(id, new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME));
    }

    protected R addInput(String id, IItemProvider block) {
        return this.addItem(id, new ItemStack(block));
    }

    protected R addBoolean(String key, boolean value) {
        return addWriter(jsonObject -> jsonObject.addProperty(key, value));
    }

    protected R addBlock(Block block) {
        return addWriter(jsonObject -> jsonObject.addProperty("block", block.getRegistryName().toString()));
    }
}
