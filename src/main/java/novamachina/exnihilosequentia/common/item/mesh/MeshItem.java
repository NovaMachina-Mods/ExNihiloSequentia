package novamachina.exnihilosequentia.common.item.mesh;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.utility.Config;

import javax.annotation.Nullable;

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
    public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType) {
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
