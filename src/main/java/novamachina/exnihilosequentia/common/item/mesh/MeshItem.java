package novamachina.exnihilosequentia.common.item.mesh;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

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

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment == Enchantments.BLOCK_EFFICIENCY || enchantment == Enchantments.BLOCK_FORTUNE;
    }
}
