package novamachina.exnihilosequentia.common.item.mesh;

import net.minecraft.world.item.Item;
import net.minecraft.util.StringRepresentable;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.utility.Config;

import javax.annotation.Nonnull;

public enum EnumMesh implements StringRepresentable, IExtensibleEnum {
    NONE(0, "none", 0), STRING(1, "string", Config.getMeshStringValue()), FLINT(2, "flint", Config.getMeshFlintValue()), IRON(3, "iron", Config.getMeshIronValue()), DIAMOND(4, "diamond", Config.getMeshDiamondValue()), EMERALD(5, "emerald", Config.getMeshEmeraldValue()), NETHERITE(6, "netherite", Config.getMeshNetheriteValue());

    private final int id;
    private final int maxDamage;
    @Nonnull private final String name;
    private RegistryObject<Item> registryObject;

    EnumMesh(final int id, @Nonnull final String name, final int maxDamage) {
        this.id = id;
        this.name = name;
        this.maxDamage = maxDamage;
    }

    public static EnumMesh create(@Nonnull final String enumName, final int id, @Nonnull final String name,
                                  final int maxDamage) {
        throw new IllegalStateException("Enum not extended");
    }

    public static EnumMesh getMeshFromName(@Nonnull final String name) {
        for (@Nonnull final EnumMesh mesh : EnumMesh.values()) {
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

    @Nonnull
    public String getMeshName() {
        return "mesh_" + name;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public RegistryObject<Item> getRegistryObject() {
        return registryObject;
    }

    public void setRegistryObject(@Nonnull final RegistryObject<Item> registryObject) {
        this.registryObject = registryObject;
    }

    @Override
    @Nonnull
    public String getSerializedName() {
        return this.name;
    }
}
