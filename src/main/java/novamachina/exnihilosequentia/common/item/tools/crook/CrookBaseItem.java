package novamachina.exnihilosequentia.common.item.tools.crook;

import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class CrookBaseItem extends ToolItem {

    @Nonnull private static final Set<Material> effectiveMaterialsOn = Sets.newHashSet(Material.LEAVES);

    public CrookBaseItem(@Nonnull final IItemTier tier, final int maxDamage) {
        super(0.5F, 0.5F, tier, Sets.newHashSet(),
                new Item.Properties().defaultDurability(maxDamage).addToolType(ToolType.get("crook"), 0)
                        .tab(ExNihiloInitialization.ITEM_GROUP));
    }

    @Override
    public float getDestroySpeed(@Nonnull final ItemStack stack, @Nonnull final BlockState state) {
        @Nonnull final Material material = state.getMaterial();
        return effectiveMaterialsOn.contains(material) ? this.speed : super.getDestroySpeed(stack, state);
    }

    @Override
    public int getBurnTime(@Nonnull final ItemStack itemStack, @Nullable final IRecipeType<?> recipeType) {
        @Nullable final RegistryObject<Item> woodRegistryObject = EnumCrook.WOOD.getRegistryObject();
        if (woodRegistryObject != null && itemStack.getItem() == woodRegistryObject.get()) {
            return 200;
        }
        else return 0;
    }
}
