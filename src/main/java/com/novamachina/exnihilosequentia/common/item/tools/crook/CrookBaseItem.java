package com.novamachina.exnihilosequentia.common.item.tools.crook;

import com.google.common.collect.Sets;
import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.block.InfestedLeavesBlock;
import com.novamachina.exnihilosequentia.common.init.ModInitialization;
import com.novamachina.exnihilosequentia.common.item.resources.EnumResource;
import com.novamachina.exnihilosequentia.common.registries.crook.CrookDropEntry;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CrookBaseItem extends ToolItem {

    private static final Set<Block> effectiveBlocksOn = Sets
        .newHashSet(BlockTags.LEAVES.getAllElements());

    public CrookBaseItem(IItemTier tier, int maxDamage) {
        super(0.5F, 0.5F, tier, effectiveBlocksOn,
            new Item.Properties().defaultMaxDamage(maxDamage).group(ModInitialization.ITEM_GROUP));
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos,
                                    LivingEntity entityLiving) {
        super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
        List<ItemStack> itemDrops = new ArrayList<>();
        Collection<ResourceLocation> tags = TagUtils.getTags(state.getBlock());

        if (tags.contains(new ResourceLocation("minecraft:leaves"))) {
            for (int i = 0; i < Config.VANILLA_SIMULATE_DROP_COUNT.get(); i++) {
                List<ItemStack> items = Block
                    .getDrops(state, worldIn.getServer().getWorld(worldIn.getDimension().getType()),
                        pos, null);
                itemDrops.addAll(items);
            }
            Random random = new Random();
            for (CrookDropEntry entry : ExNihiloRegistries.CROOK_REGISTRY.getDrops()) {
                if (random.nextFloat() <= entry.getRarity()) {
                    itemDrops.add(new ItemStack(ForgeRegistries.ITEMS.getValue(entry.getItem())));
                }
            }
        }
        if (state.getBlock() instanceof InfestedLeavesBlock) {
            Random random = new Random();

            itemDrops.add(new ItemStack(Items.STRING, random
                .nextInt(Config.MAX_BONUS_STRING_COUNT.get()) + Config.MIN_STRING_COUNT.get()));
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
        return false;
    }
}
