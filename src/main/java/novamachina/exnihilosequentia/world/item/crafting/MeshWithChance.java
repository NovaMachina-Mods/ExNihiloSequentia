package novamachina.exnihilosequentia.world.item.crafting;

import com.google.common.base.Objects;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import javax.annotation.Nonnull;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import novamachina.exnihilosequentia.world.item.MeshType;

public class MeshWithChance {

  public static final Codec<MeshWithChance> CODEC =
      RecordCodecBuilder.create(
          instance ->
              instance
                  .group(
                      MeshType.CODEC.fieldOf("mesh").forGetter(recipe -> recipe.getMesh()),
                      Codec.FLOAT.fieldOf("chance").forGetter(recipe -> recipe.getChance()))
                  .apply(instance, MeshWithChance::new));

  @Nonnull private static final String CHANCE_KEY = "chance";
  @Nonnull private static final String MESH_KEY = "mesh";
  private final float chance;
  @Nonnull private final MeshType mesh;

  public MeshWithChance(@Nonnull final MeshType mesh, final float chance) {
    this.mesh = mesh;
    this.chance = chance;
  }

  @Nonnull
  public static MeshWithChance deserialize(@Nonnull final JsonElement json) {
    if (json.isJsonObject() && json.getAsJsonObject().has(MESH_KEY)) {
      final float chance = GsonHelper.getAsFloat(json.getAsJsonObject(), CHANCE_KEY, 1.0F);
      @Nonnull
      final MeshType mesh =
          MeshType.valueOf(GsonHelper.getAsString(json.getAsJsonObject(), MESH_KEY).toUpperCase());
      return new MeshWithChance(mesh, chance);
    } else {
      @Nonnull
      final MeshType mesh =
          MeshType.valueOf(GsonHelper.getAsString(json.getAsJsonObject(), MESH_KEY).toUpperCase());
      return new MeshWithChance(mesh, 1.0F);
    }
  }

  @Nonnull
  public static MeshWithChance read(@Nonnull final FriendlyByteBuf buffer) {
    @Nonnull final MeshType mesh = buffer.readEnum(MeshType.class);
    final float chance = buffer.readFloat();
    return new MeshWithChance(mesh, chance);
  }

  @Nonnull
  public JsonElement serialize() {
    @Nonnull final JsonObject json = new JsonObject();
    json.addProperty(CHANCE_KEY, getChance());
    json.addProperty(MESH_KEY, this.mesh.name().toLowerCase());
    return json;
  }

  public void write(@Nonnull final FriendlyByteBuf buffer) {
    buffer.writeEnum(this.mesh);
    buffer.writeFloat(getChance());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MeshWithChance that = (MeshWithChance) o;
    return Float.compare(getChance(), that.getChance()) == 0 && getMesh() == that.getMesh();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getChance(), getMesh());
  }

  public float getChance() {
    return this.chance;
  }

  @Nonnull
  public MeshType getMesh() {
    return this.mesh;
  }
}
