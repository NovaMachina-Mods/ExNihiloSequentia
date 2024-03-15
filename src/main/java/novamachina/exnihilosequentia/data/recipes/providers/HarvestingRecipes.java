package novamachina.exnihilosequentia.data.recipes.providers;

import javax.annotation.Nonnull;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.data.recipes.HarvestRecipeBuilder;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.data.recipes.ISubRecipeProvider;

public class HarvestingRecipes implements ISubRecipeProvider {
  @Override
  public void addRecipes(RecipeOutput consumer) {
    HarvestRecipeBuilder.harvest(ItemTags.LEAVES)
        .addDrop(new ItemStack(EXNItems.SILKWORM.asItem()), 0.1F)
        .build(consumer, harvestLoc("leaves"));
    HarvestRecipeBuilder.harvest(EXNBlocks.INFESTED_LEAVES.block())
        .addDrop(new ItemStack(EXNItems.SILKWORM.asItem()), 0.2F)
        .build(consumer, harvestLoc("silkworm"));
    HarvestRecipeBuilder.harvest(EXNBlocks.INFESTED_LEAVES.block())
        .addDrop(new ItemStack(Items.STRING), 0.5F)
        .build(consumer, harvestLoc("string"));
  }

  private ResourceLocation harvestLoc(@Nonnull final String id) {
    return new ResourceLocation(
        ExNihiloSequentia.MOD_ID, "harvest/" + RecipeProviderUtilities.prependRecipePrefix(id));
  }
}
