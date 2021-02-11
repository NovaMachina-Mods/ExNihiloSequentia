package novamachina.exnihilosequentia.common.item.mesh;

import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.fml.RegistryObject;

public enum EnumMesh implements IStringSerializable, IExtensibleEnum {
    // TODO Mesh damage is configurable
    NONE(0, "none", 0), STRING(1, "string", 59), FLINT(2, "flint", 131), IRON(3, "iron", 250), DIAMOND(4, "diamond", 1561), EMERALD(5, "emerald", 1561), NETHERITE(6, "netherite", 2031);

    private final int id;
    private final int maxDamage;
    private final String name;
    private RegistryObject<Item> registryObject;

    EnumMesh(int id, String name, int maxDamage) {
        this.id = id;
        this.name = name;
        this.maxDamage = maxDamage;
    }

    public static EnumMesh create(String enumName, int id, String name, int maxDamage) {
        throw new IllegalStateException("Enum not extended");
    }

    public static EnumMesh getMeshFromName(String name) {
        for (EnumMesh mesh : EnumMesh.values()) {
            if (mesh.getName().equals(name)) {
                return mesh;
            }
        }
        return NONE;
    }

    public int getId() {
        return id;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public String getMeshName() {
        return "mesh_" + name;
    }

    public String getName() {
        return name;
    }

    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }

    @Override
    public String getString() {
        return this.name;
    }
}
