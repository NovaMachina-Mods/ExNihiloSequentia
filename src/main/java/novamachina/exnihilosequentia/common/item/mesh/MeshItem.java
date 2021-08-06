package novamachina.exnihilosequentia.common.item.mesh;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

import net.minecraft.world.item.Item.Properties;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.utility.Config;

public class MeshItem extends Item {

    private final EnumMesh mesh;


    public MeshItem(EnumMesh mesh) {
        super(Config.enableMeshDurability() ?
                new Properties().tab(ExNihiloInitialization.ITEM_GROUP).durability(mesh.getMaxDamage())
                : new Properties().tab(ExNihiloInitialization.ITEM_GROUP).stacksTo(Config.getMeshStackSize())
        );

        this.mesh = mesh;

    }

    public EnumMesh getMesh() {
        return mesh;
    }
    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
        if (itemStack.getItem() == EnumMesh.STRING.getRegistryObject().get()) {
            return 200;
        } else return 0;
    }
}
