package novamachina.exnihilosequentia.common.crafting.sieve;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.annotation.Nonnull;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;

public class MeshWithChance {

  @Nonnull
  private static final String CHANCE_KEY = "chance";
  @Nonnull
  private static final String MESH_KEY = "mesh";
  private final float chance;
  @Nonnull
  private final MeshType mesh;

  public MeshWithChance(@Nonnull final MeshType mesh, final float chance) {
    this.mesh = mesh;
    this.chance = chance;
  }

  @Nonnull
  public static MeshWithChance deserialize(@Nonnull final JsonElement json) {
    if (json.isJsonObject() && json.getAsJsonObject().has(MESH_KEY)) {
      final float chance = GsonHelper.getAsFloat(json.getAsJsonObject(), CHANCE_KEY, 1.0F);
      @Nonnull final MeshType mesh = MeshType.valueOf(
          GsonHelper.getAsString(json.getAsJsonObject(), MESH_KEY).toUpperCase());
      return new MeshWithChance(mesh, chance);
    } else {
      @Nonnull final MeshType mesh = MeshType.valueOf(
          GsonHelper.getAsString(json.getAsJsonObject(), MESH_KEY).toUpperCase());
      return new MeshWithChance(mesh, 1.0F);
    }
  }

  @Nonnull
  public static MeshWithChance read(@Nonnull final FriendlyByteBuf buffer) {
    @Nonnull final MeshType mesh = buffer.readEnum(MeshType.class);
    final float chance = buffer.readFloat();
    return new MeshWithChance(mesh, chance);
  }

  public float getChance() {
    return chance;
  }

  @Nonnull
  public MeshType getMesh() {
    return mesh;
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
}
