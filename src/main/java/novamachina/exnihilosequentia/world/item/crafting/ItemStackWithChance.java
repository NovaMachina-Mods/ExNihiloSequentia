package novamachina.exnihilosequentia.world.item.crafting;

import com.google.common.base.Objects;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.slf4j.Logger;

public class ItemStackWithChance {

  private static final String BASE_KEY = "item";
  private static final String CHANCE_KEY = "chance";
  private static final String COUNT_KEY = "count";

  public static final Codec<ItemStackWithChance> CODEC =
      Codec.lazyInitialized(
          () ->
              RecordCodecBuilder.create(
                  instance ->
                      instance
                          .group(
                              ItemStack.CODEC
                                  .fieldOf(BASE_KEY)
                                  .forGetter(recipe -> recipe.getStack()),
                              Codec.FLOAT
                                  .fieldOf(CHANCE_KEY)
                                  .forGetter(recipe -> recipe.getChance()))
                          .apply(instance, ItemStackWithChance::new)));
  public static final StreamCodec<RegistryFriendlyByteBuf, ItemStackWithChance> STREAM_CODEC =
      new StreamCodec<RegistryFriendlyByteBuf, ItemStackWithChance>() {
        public ItemStackWithChance decode(RegistryFriendlyByteBuf buff) {
          ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buff);
          if (itemstack.isEmpty()) {
            throw new DecoderException("Empty ItemStack not allowed");
          }
          float chance = buff.readFloat();
          return new ItemStackWithChance(itemstack, chance);
        }

        public void encode(RegistryFriendlyByteBuf buff, ItemStackWithChance itemStackWithChance) {
          ItemStack itemstack = itemStackWithChance.getStack();
          if (itemstack.isEmpty()) {
            throw new EncoderException("Empty ItemStack not allowed");
          }
          ItemStack.STREAM_CODEC.encode(buff, itemstack);
          buff.writeFloat(itemStackWithChance.getChance());
        }
      };

  private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(ItemStackWithChance.class);
  private final float chance;
  private final ItemStack itemStack;

  private ItemStackWithChance(ItemStack itemStack, float chance) {
    this.itemStack = itemStack;
    this.chance = chance;
  }

  public static ItemStackWithChance of(ItemStack itemStack) {
    return of(itemStack, 1.0F);
  }

  public static ItemStackWithChance of(ItemStack itemStack, int count) {
    itemStack.setCount(count);
    return of(itemStack, 1.0F);
  }

  public static ItemStackWithChance of(ItemStack itemStack, int count, float chance) {
    itemStack.setCount(count);
    return of(itemStack, chance);
  }

  public static ItemStackWithChance of(ItemStack itemStack, float chance) {
    return new ItemStackWithChance(itemStack, chance);
  }

  @NonNull
  public static ItemStackWithChance deserialize(JsonElement json) {
    if (json.isJsonObject() && json.getAsJsonObject().has(BASE_KEY)) {
      final float chance = GsonHelper.getAsFloat(json.getAsJsonObject(), CHANCE_KEY, 1.0F);
      String itemString = GsonHelper.getAsString(json.getAsJsonObject(), BASE_KEY);
      int count = 1;
      if (json.getAsJsonObject().has(COUNT_KEY)) {
        count = json.getAsJsonObject().get(COUNT_KEY).getAsInt();
      }
      return of(
          new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemString)), count),
          chance);
    } else {
      String itemString = GsonHelper.convertToString(json, BASE_KEY);
      return of(new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemString))), 1.0F);
    }
  }

  @NonNull
  public static ItemStackWithChance read(RegistryFriendlyByteBuf buffer) {
    return ItemStackWithChance.STREAM_CODEC.decode(buffer);
  }

  @NonNull
  public ItemStack getStack() {
    return itemStack.copy();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemStackWithChance that = (ItemStackWithChance) o;
    return Float.compare(getChance(), that.getChance()) == 0
        && ItemStack.matches(itemStack, that.itemStack);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getChance(), itemStack);
  }

  public float getChance() {
    return this.chance;
  }
}
