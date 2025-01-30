package novamachina.exnihilosequentia.data.recipes.providers;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.data.recipes.RecipeProviderUtilities;
import novamachina.exnihilosequentia.data.recipes.SolidifyingRecipeBuilder;
import novamachina.novacore.data.recipes.ISubRecipeProvider;

public class SolidifyingRecipes implements ISubRecipeProvider {

  private static final FluidStack lava = new FluidStack(Fluids.LAVA, FluidType.BUCKET_VOLUME);
  private static final FluidStack water = new FluidStack(Fluids.WATER, FluidType.BUCKET_VOLUME);

  @Override
  public void addRecipes(HolderGetter.Provider holderGetter, RecipeOutput consumer) {
    SolidifyingRecipeBuilder.solidify(lava, water, Blocks.OBSIDIAN)
        .build(consumer, solidifyLoc("obsidian"));
    SolidifyingRecipeBuilder.solidify(water, lava, Blocks.COBBLESTONE)
        .build(consumer, solidifyLoc("cobblestone"));
  }

  private ResourceKey<Recipe<?>> solidifyLoc(String id) {

    ResourceLocation rl =
        ResourceLocation.fromNamespaceAndPath(
            ExNihiloSequentia.MOD_ID,
            "solidify/" + RecipeProviderUtilities.prependRecipePrefix(id));
    return ResourceKey.create(Registries.RECIPE, rl);
  }
}
