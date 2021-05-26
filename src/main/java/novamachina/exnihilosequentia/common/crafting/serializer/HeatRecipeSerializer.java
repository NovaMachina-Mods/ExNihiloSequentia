package novamachina.exnihilosequentia.common.crafting.serializer;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;

import java.util.Map;

public class HeatRecipeSerializer extends RecipeSerializer<HeatRecipe> {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(ExNihiloBlocks.CRUCIBLE_FIRED.get());
    }

    @Override
    public HeatRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
        Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(buffer.readUtf()));
        int amount = buffer.readInt();
        boolean isLiquid = buffer.readBoolean();

        if(isLiquid) {
            Map<String, Integer> heatByLevel = decodeHeatByLevel(buffer.readUtf());
            return new HeatRecipe(recipeId, input, amount, true, heatByLevel);
        }

        return new HeatRecipe(recipeId, input, amount);
    }

    @Override
    public void toNetwork(PacketBuffer buffer, HeatRecipe recipe) {
        buffer.writeUtf(recipe.getInput().getRegistryName().toString());
        buffer.writeInt(recipe.getAmount());
        buffer.writeBoolean(recipe.isLiquid());
        buffer.writeUtf(encodeHeatByLevel(recipe.getHeatByLevel()));
    }

    @Override
    protected HeatRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(json.get("block").getAsString()));
        int amount = json.get("amount").getAsInt();

        if(json.has("isLiquid") && json.has("heatByLevel")) {
            boolean isLiquid = JSONUtils.getAsBoolean(json, "isLiquid");
            Map<String, Integer> heatByLevel = deserializeHeatByLevel(JSONUtils.getAsJsonObject(json, "heatByLevel"));
            return new HeatRecipe(recipeId, input, amount, isLiquid, heatByLevel);
        }

        return new HeatRecipe(recipeId, input, amount);
    }

    private Map<String, Integer> deserializeHeatByLevel(JsonObject heatByLevel) {
        Map<String, Integer> map = Maps.newHashMap();

        for (Map.Entry<String, JsonElement> entry : heatByLevel.entrySet()) {
            if(!map.containsKey(entry.getKey())) {
                if(entry.getKey().length() != 1 || !Character.isDigit(entry.getKey().charAt(0))) {
                    throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "'. Only one character and needs to be a digit");
                }
                map.put(entry.getKey(), entry.getValue().getAsInt());
            } else {
                System.err.println("Key: " + entry.getKey() + " is already registered. Check the recipe.");
            }
        }
        return map;
    }

    private String encodeHeatByLevel(Map<String, Integer> map) {
        StringBuilder builder = new StringBuilder();
        map.forEach((k, v) -> builder.append(k).append(":").append(v).append(";"));
        return builder.toString();
    }

    private Map<String, Integer> decodeHeatByLevel(String encoded) {
        Map<String, Integer> map = Maps.newHashMap();
        String[] entries = encoded.split(";");
        for (String entry : entries) {
            map.put(String.valueOf(entry.charAt(0)), Integer.parseInt(String.valueOf(entry.charAt(2))));
        }
        return map;
    }
}
