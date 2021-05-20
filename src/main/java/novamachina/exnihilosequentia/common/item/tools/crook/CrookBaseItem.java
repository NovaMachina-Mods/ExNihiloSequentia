package novamachina.exnihilosequentia.common.item.tools.crook;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.block.InfestedLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.utility.Config;

public class CrookBaseItem extends ToolItem {

    private static final Set<Material> effectiveMaterialsOn = Sets
            .newHashSet(Material.LEAVES);

    private final Random random = new Random();

    public CrookBaseItem(IItemTier tier, int maxDamage) {
        super(0.5F, 0.5F, tier, Sets.newHashSet(),
                new Item.Properties().defaultDurability(maxDamage).tab(ExNihiloInitialization.ITEM_GROUP));
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        return effectiveMaterialsOn.contains(material) ? this.speed : super.getDestroySpeed(stack, state);
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        if (itemStack.getItem() == EnumCrook.WOOD.getRegistryObject().get()) {
            return 200;
        }
        else return 0;
    }
}
