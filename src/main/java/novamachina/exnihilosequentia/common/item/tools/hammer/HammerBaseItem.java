package novamachina.exnihilosequentia.common.item.tools.hammer;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.utility.Config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HammerBaseItem extends ToolItem {

    //TODO
    /*@Nonnull private static final Set<Block> effectiveBlocksOn = Sets
            .newHashSet(Blocks.STONE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.GRANITE,
                    Blocks.NETHERRACK, Blocks.END_STONE, Blocks.COBBLESTONE,
                    ExNihiloBlocks.CRUSHED_DIORITE.get(), ExNihiloBlocks.CRUSHED_ANDESITE.get(),
                    ExNihiloBlocks.CRUSHED_GRANITE.get(), ExNihiloBlocks.CRUSHED_NETHERRACK.get(),
                    ExNihiloBlocks.CRUSHED_END_STONE.get(), Blocks.GRAVEL, Blocks.SAND,
                    Blocks.TUBE_CORAL_BLOCK, Blocks.BRAIN_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK,
                    Blocks.FIRE_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK,
                    Blocks.TUBE_CORAL, Blocks.BRAIN_CORAL, Blocks.BUBBLE_CORAL,
                    Blocks.FIRE_CORAL, Blocks.HORN_CORAL);
     */
    @Nonnull private static final Set<Block> effectiveBlocksOn = Sets.newHashSet();

    public HammerBaseItem(@Nonnull final IItemTier tier, final int maxDamage) {
        super(0.5F, 0.5F, tier, effectiveBlocksOn,
                new Item.Properties().defaultDurability(maxDamage).tab(ExNihiloInitialization.ITEM_GROUP)
                        .addToolType(ToolType.PICKAXE, tier.getLevel()));
    }

    @Override
    public boolean isCorrectToolForDrops(@Nonnull final BlockState blockIn) {
        if (ExNihiloRegistries.HAMMER_REGISTRY.isHammerable(blockIn.getBlock())) {
            return true;
        }
        return super.isCorrectToolForDrops(blockIn);
    }

    @Override
    public int getBurnTime(@Nonnull final ItemStack itemStack, @Nullable final IRecipeType<?> recipeType) {
        if (itemStack.getItem() == EnumHammer.WOOD.getRegistryObject().get()) {
            return 200;
        }
        else return 0;
    }

    static {
        List<? extends String> list = Config.getEffectiveBlocksOn();
        for (String blockString : list) {
            if (blockString != null) {
                String[] temp = blockString.split(":");
                ResourceLocation location = new ResourceLocation(temp[0], temp[1]);
                //TODO not 100% sure
                if (ForgeRegistries.BLOCKS.getValue(location) != null) {
                    effectiveBlocksOn.add(ForgeRegistries.BLOCKS.getValue(location));
                }
            }
        }
    }
}
