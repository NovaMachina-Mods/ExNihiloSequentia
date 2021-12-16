package novamachina.exnihilosequentia.api.crafting.sieve;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;

import javax.annotation.Nonnull;

public class MeshWithChance {
    @Nonnull private static final String CHANCE_KEY = "chance";
    @Nonnull private static final String MESH_KEY = "mesh";
    private final float chance;
    @Nonnull private final EnumMesh mesh;

    public MeshWithChance(@Nonnull final EnumMesh mesh, final float chance) {
        this.mesh = mesh;
        this.chance = chance;
    }

    @Nonnull
    public static MeshWithChance deserialize(@Nonnull final JsonElement json) {
        if (json.isJsonObject() && json.getAsJsonObject().has(MESH_KEY)) {
            final float chance = JSONUtils.getAsFloat(json.getAsJsonObject(), CHANCE_KEY, 1.0F);
            @Nonnull final EnumMesh mesh = EnumMesh.getMeshFromName(JSONUtils.getAsString(json.getAsJsonObject(), MESH_KEY));
            return new MeshWithChance(mesh, chance);
        } else {
            @Nonnull final EnumMesh mesh = EnumMesh.getMeshFromName(JSONUtils.getAsString(json.getAsJsonObject(), MESH_KEY));
            return new MeshWithChance(mesh, 1.0F);
        }
    }

    @Nonnull
    public static MeshWithChance read(@Nonnull final PacketBuffer buffer) {
        @Nonnull final EnumMesh mesh = buffer.readEnum(EnumMesh.class);
        final float chance = buffer.readFloat();
        return new MeshWithChance(mesh, chance);
    }

    public float getChance() {
        return chance;
    }

    @Nonnull
    public EnumMesh getMesh() {
        return mesh;
    }

    @Nonnull
    public JsonElement serialize() {
        @Nonnull final JsonObject json = new JsonObject();
        json.addProperty(CHANCE_KEY, getChance());
        json.addProperty(MESH_KEY, getMesh().getName());
        return json;
    }

    public void write(@Nonnull final PacketBuffer buffer) {
        buffer.writeEnum(getMesh());
        buffer.writeFloat(getChance());
    }
}
