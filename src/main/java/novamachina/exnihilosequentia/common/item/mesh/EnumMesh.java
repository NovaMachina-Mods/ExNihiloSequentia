package novamachina.exnihilosequentia.common.item.mesh;

import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.RegistryObject;

public enum EnumMesh implements IStringSerializable {
    // TODO Mesh damage is configurable
    NONE(0, "none", 0), STRING(1, "string", 59), FLINT(2, "flint", 131), IRON(3, "iron", 250), DIAMOND(4, "diamond", 1561), EMERALD(5, "emerald", 1561), NETHERITE(6, "netherite", 2031);

    private final int id;
    private final String name;
    private final int maxDamage;
    private RegistryObject<Item> registryObject;

    EnumMesh(int id, String name, int maxDamage) {
        this.id = id;
        this.name = name;
        this.maxDamage = maxDamage;
    }

    public static EnumMesh getMeshFromName(String name) {
        for(EnumMesh mesh : EnumMesh.values()) {
            if(mesh.getName().equals(name)) {
                return mesh;
            }
        }
        return NONE;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMeshName() {
        return "mesh_" + name;
    }

    @Override
    public String getString() {
        return this.name;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }

    public int getMaxDamage() {
        return maxDamage;
    }
}
