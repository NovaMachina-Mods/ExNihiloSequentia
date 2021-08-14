package novamachina.exnihilosequentia.api.crafting.sieve;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;

public record MeshWithChance(EnumMesh mesh, float chance) {
    private static final String CHANCE_KEY = "chance";
    private static final String MESH_KEY = "mesh";

    public static MeshWithChance deserialize(JsonElement json) {
        if (json.isJsonObject() && json.getAsJsonObject().has(MESH_KEY)) {
            final float chance = GsonHelper.getAsFloat(json.getAsJsonObject(), CHANCE_KEY, 1.0F);
            final EnumMesh mesh = EnumMesh.getMeshFromName(GsonHelper.getAsString(json.getAsJsonObject(), MESH_KEY));
            return new MeshWithChance(mesh, chance);
        } else {
            final EnumMesh mesh = EnumMesh.getMeshFromName(GsonHelper.getAsString(json.getAsJsonObject(), MESH_KEY));
            return new MeshWithChance(mesh, 1.0F);
        }
    }

    public static MeshWithChance read(FriendlyByteBuf buffer) {
        final EnumMesh mesh = buffer.readEnum(EnumMesh.class);
        final float chance = buffer.readFloat();
        return new MeshWithChance(mesh, chance);
    }

    public float getChance() {
        return chance;
    }

    public EnumMesh getMesh() {
        return mesh;
    }

    public JsonElement serialize() {
        JsonObject json = new JsonObject();
        json.addProperty(CHANCE_KEY, getChance());
        json.addProperty(MESH_KEY, getMesh().getName());
        return json;
    }

    public void write(FriendlyByteBuf buffer) {
        buffer.writeEnum(getMesh());
        buffer.writeFloat(getChance());
    }
}
