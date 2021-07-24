package novamachina.exnihilosequentia.common.item.tools.crook;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.tags.Tag;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.block.InfestedLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.utility.Config;

import javax.annotation.Nullable;

public class CrookBaseItem extends DiggerItem {

    private static final Set<Material> effectiveMaterialsOn = Sets
            .newHashSet(Material.LEAVES);

    private final Random random = new Random();

    public CrookBaseItem(Tiers tier, int maxDamage) {
        super(0.5F, 0.5F, tier, Tag.fromSet(Sets.newHashSet()),
                new Item.Properties().defaultDurability(maxDamage).tab(ExNihiloInitialization.ITEM_GROUP));
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        return effectiveMaterialsOn.contains(material) ? this.speed : super.getDestroySpeed(stack, state);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        if (itemStack.getItem() == EnumCrook.WOOD.getRegistryObject().get()) {
            return 200;
        }
        else return 0;
    }
}
