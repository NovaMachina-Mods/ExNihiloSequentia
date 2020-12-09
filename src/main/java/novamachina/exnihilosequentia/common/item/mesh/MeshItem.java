package novamachina.exnihilosequentia.common.item.mesh;

import net.minecraft.item.Item;
import novamachina.exnihilosequentia.common.init.ModInitialization;

public class MeshItem extends Item {

    private final EnumMesh mesh;

    public MeshItem(EnumMesh mesh) {
        super(new Properties().group(ModInitialization.ITEM_GROUP).maxDamage(mesh.getMaxDamage()));
        this.mesh = mesh;
    }

    public EnumMesh getMesh() {
        return mesh;
    }
}
