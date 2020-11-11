package novamachina.exnihilosequentia.common.item.mesh;

import novamachina.exnihilosequentia.common.init.ModInitialization;
import net.minecraft.item.Item;

public class MeshItem extends Item {

    private final EnumMesh mesh;

    public MeshItem(EnumMesh mesh) {
        super(new Item.Properties().group(ModInitialization.ITEM_GROUP));
        this.mesh = mesh;
    }

    public EnumMesh getMesh() {
        return mesh;
    }
}
