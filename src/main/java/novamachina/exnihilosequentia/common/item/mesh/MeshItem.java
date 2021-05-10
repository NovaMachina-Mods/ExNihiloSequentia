package novamachina.exnihilosequentia.common.item.mesh;

import net.minecraft.item.Item;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

import net.minecraft.item.Item.Properties;

public class MeshItem extends Item {

    private final EnumMesh mesh;

    public MeshItem(EnumMesh mesh) {
        super(new Properties().tab(ExNihiloInitialization.ITEM_GROUP).durability(mesh.getMaxDamage()));
        this.mesh = mesh;
    }

    public EnumMesh getMesh() {
        return mesh;
    }
}
