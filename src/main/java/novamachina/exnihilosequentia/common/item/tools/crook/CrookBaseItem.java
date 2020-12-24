package novamachina.exnihilosequentia.common.item.tools.crook;

import com.google.common.collect.Sets;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.block.InfestedLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.utility.Config;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CrookBaseItem extends ToolItem {

    private static final Set<Material> effectiveMaterialsOn = Sets
        .newHashSet(Material.LEAVES);

    private final Random random = new Random();

    public CrookBaseItem(IItemTier tier, int maxDamage) {
        super(0.5F, 0.5F, tier, Sets.newHashSet(),
            new Item.Properties().defaultMaxDamage(maxDamage).group(ExNihiloInitialization.ITEM_GROUP));
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        return effectiveMaterialsOn.contains(material) ? this.efficiency : super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos,
                                    LivingEntity entityLiving) {
        super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
        List<ItemStack> itemDrops = new ArrayList<>();

        if (ExNihiloRegistries.CROOK_REGISTRY.isCrookable(state.getBlock())) {
            for (int i = 0; i < Config.getVanillaSimulateDropCount(); i++) {
                List<ItemStack> items = Block
                    .getDrops(state, worldIn.getServer().getWorld(worldIn.getDimensionKey()),
                        pos, null);
                itemDrops.addAll(items);
            }

            for (CrookRecipe recipe : ExNihiloRegistries.CROOK_REGISTRY.getDrops(state.getBlock())) {
                for (ItemStackWithChance result : recipe.getOutput()) {
                    if (random.nextFloat() <= result.getChance()) {
                        itemDrops.add(result.getStack());
                    }
                }
            }

            if (state.getBlock() instanceof InfestedLeavesBlock) {
                itemDrops.add(new ItemStack(Items.STRING, random
                    .nextInt(Config.getMaxBonusStringCount()) + Config.getMinStringCount()));
                if (random.nextDouble() <= 0.8) {
                    itemDrops
                        .add(new ItemStack(EnumResource.SILKWORM.getRegistryObject().get()));
                }
            }

            for (ItemStack item : itemDrops) {
                worldIn.addEntity(
                    new ItemEntity(worldIn, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F,
                        item));
            }
        }
        return true;
    }
}
