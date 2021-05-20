package novamachina.exnihilosequentia.common.item.mesh;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

import net.minecraft.item.Item.Properties;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;

public class MeshItem extends Item {

    private final EnumMesh mesh;

    public MeshItem(EnumMesh mesh) {
        super(new Properties().tab(ExNihiloInitialization.ITEM_GROUP).durability(mesh.getMaxDamage()));
        this.mesh = mesh;
    }

    public EnumMesh getMesh() {
        return mesh;
    }
    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 100;
    }
}
