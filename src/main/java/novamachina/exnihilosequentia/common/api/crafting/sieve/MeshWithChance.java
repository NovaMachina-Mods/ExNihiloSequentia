package novamachina.exnihilosequentia.common.api.crafting.sieve;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;

public class MeshWithChance {
    private static final String CHANCE_KEY = "chance";
    private static final String MESH_KEY = "mesh";
    private final EnumMesh mesh;
    private final float chance;

    public MeshWithChance(EnumMesh mesh, float chance) {
        this.mesh = mesh;
        this.chance = chance;
    }

    public static MeshWithChance read(PacketBuffer buffer) {
        final EnumMesh mesh = buffer.readEnumValue(EnumMesh.class);
        final float chance = buffer.readFloat();
        return new MeshWithChance(mesh, chance);
    }

    public static MeshWithChance deserialize(JsonElement json) {
        if (json.isJsonObject() && json.getAsJsonObject().has(MESH_KEY)) {
            final float chance = JSONUtils.getFloat(json.getAsJsonObject(), CHANCE_KEY, 1.0F);
            final EnumMesh mesh = EnumMesh.getMeshFromName(JSONUtils.getString(json.getAsJsonObject(), MESH_KEY));
            return new MeshWithChance(mesh, chance);
        } else {
            final EnumMesh mesh = EnumMesh.getMeshFromName(JSONUtils.getString(json.getAsJsonObject(), MESH_KEY));
            return new MeshWithChance(mesh, 1.0F);
        }
    }

    public EnumMesh getMesh() {
        return mesh;
    }

    public float getChance() {
        return chance;
    }

    public void write(PacketBuffer buffer) {
        buffer.writeEnumValue(getMesh());
        buffer.writeFloat(getChance());
    }

    public JsonElement serialize() {
        JsonObject json = new JsonObject();
        json.addProperty(CHANCE_KEY, getChance());
        json.addProperty(MESH_KEY, getMesh().getName());
        return json;
    }
}
