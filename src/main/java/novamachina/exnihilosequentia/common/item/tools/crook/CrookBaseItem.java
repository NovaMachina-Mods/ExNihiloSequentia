package novamachina.exnihilosequentia.common.item.tools.crook;

import com.google.common.collect.Sets;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

import java.util.Random;
import java.util.Set;

public class CrookBaseItem extends DiggerItem {

    private static final Set<Material> effectiveMaterialsOn = Sets
            .newHashSet(Material.LEAVES);

    private final Random random = new Random();

    public CrookBaseItem(Tier tier, int maxDamage) {
        super(0.5F, 0.5F, tier, Tag.fromSet(Sets.newHashSet()),
                new Item.Properties().defaultDurability(maxDamage).tab(ExNihiloInitialization.ITEM_GROUP));
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        return effectiveMaterialsOn.contains(material) ? this.speed : super.getDestroySpeed(stack, state);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
        if (itemStack.getItem() == EnumCrook.WOOD.getRegistryObject().get()) {
            return 200;
        }
        else return 0;
    }
}
