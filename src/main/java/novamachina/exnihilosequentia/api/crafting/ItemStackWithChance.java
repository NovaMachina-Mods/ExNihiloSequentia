package novamachina.exnihilosequentia.api.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemStackWithChance {
    @Nonnull private static final String BASE_KEY = "item";
    @Nonnull private static final String CHANCE_KEY = "chance";
    @Nonnull private static final String COUNT_KEY = "count";
    private final float chance;
    @Nonnull private final ItemStack itemStack;

    public ItemStackWithChance(@Nonnull final ItemStack itemStack, final float chance) {
        this.itemStack = itemStack;
        this.chance = chance;
    }

    @Nonnull
    public static ItemStackWithChance deserialize(@Nonnull final JsonElement json) {
        if (json.isJsonObject() && json.getAsJsonObject().has(BASE_KEY)) {
            final float chance = JSONUtils.getAsFloat(json.getAsJsonObject(), CHANCE_KEY, 1.0F);
            @Nonnull final String itemString = JSONUtils.getAsString(json.getAsJsonObject(), BASE_KEY);
            int count = 1;
            if (json.getAsJsonObject().has(COUNT_KEY)) {
                count = json.getAsJsonObject().get(COUNT_KEY).getAsInt();
            }
            return new ItemStackWithChance(new ItemStack(ForgeRegistries.ITEMS
                    .getValue(new ResourceLocation(itemString)), count), chance);
        } else {
            @Nonnull final String itemString = JSONUtils.convertToString(json, BASE_KEY);
            return new ItemStackWithChance(new ItemStack(ForgeRegistries.ITEMS
                    .getValue(new ResourceLocation(itemString))), 1.0F);
        }
    }

    @Nonnull
    public static ItemStackWithChance read(@Nonnull final PacketBuffer buffer) {
        @Nonnull final ItemStack stack = buffer.readItem();
        final float chance = buffer.readFloat();
        return new ItemStackWithChance(stack, chance);
    }

    public float getChance() {
        return chance;
    }

    @Nonnull
    public ItemStack getStack() {
        return itemStack.copy();
    }

    @Nonnull
    public JsonElement serialize() {
        @Nonnull final JsonObject json = new JsonObject();
        json.addProperty(CHANCE_KEY, getChance());
        @Nullable final ResourceLocation resourceLocation = getStack().getItem().getRegistryName();
        if (resourceLocation != null)
            json.addProperty(BASE_KEY, resourceLocation.toString());
        json.addProperty(COUNT_KEY, getStack().getCount());
        return json;
    }

    public void write(@Nonnull final PacketBuffer buffer) {
        buffer.writeItem(getStack());
        buffer.writeFloat(getChance());
    }
}
