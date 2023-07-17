package novamachina.exnihilosequentia.common.compat.jei;

import com.mojang.logging.LogUtils;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.block.BlockBarrel;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.block.CrucibleBaseBlock;
import novamachina.exnihilosequentia.common.blockentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.common.compat.jei.compost.CompostRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.crook.CrookRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.crucible.CrucibleRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.crucible.JEICrucibleRecipe;
import novamachina.exnihilosequentia.common.compat.jei.fluiditem.FluidBlockRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.fluidontop.FluidOnTopRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.fluidtransform.FluidTransformCategory;
import novamachina.exnihilosequentia.common.compat.jei.hammer.HammerRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.heat.HeatRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.sieve.DrySieveRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.sieve.JEISieveRecipe;
import novamachina.exnihilosequentia.common.compat.jei.sieve.WetSieveRecipeCategory;
import novamachina.exnihilosequentia.common.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.item.CrookItem;
import novamachina.exnihilosequentia.common.item.HammerItem;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.ItemDefinition;
import novamachina.novacore.world.level.block.BlockDefinition;

@JeiPlugin
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  @Nonnull
  private static final ResourceLocation CRUCIBLES =
      new ResourceLocation(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Blocks.CRUCIBLES);

  @Nonnull
  private static final ResourceLocation FIRED_CRUCIBLES =
      new ResourceLocation(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.Blocks.FIRED_CRUCIBLE);

  @Nonnull
  @Override
  public ResourceLocation getPluginUid() {
    return new ResourceLocation(
        ExNihiloConstants.ModIds.JEI, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
  }

  @Override
  public void registerCategories(@Nonnull final IRecipeCategoryRegistration registration) {
    IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

    registration.addRecipeCategories(new CrookRecipeCategory(guiHelper));
    registration.addRecipeCategories(new DrySieveRecipeCategory(guiHelper));
    registration.addRecipeCategories(new WetSieveRecipeCategory(guiHelper));
    registration.addRecipeCategories(new HammerRecipeCategory(guiHelper));
    registration.addRecipeCategories(new FluidOnTopRecipeCategory(guiHelper));
    registration.addRecipeCategories(new FluidTransformCategory(guiHelper));
    registration.addRecipeCategories(new FluidBlockRecipeCategory(guiHelper));
    registration.addRecipeCategories(new CompostRecipeCategory(guiHelper));
    registration.addRecipeCategories(
        new CrucibleRecipeCategory(guiHelper, ExNihiloConstants.Blocks.CRUCIBLES));
    registration.addRecipeCategories(
        new CrucibleRecipeCategory(guiHelper, ExNihiloConstants.Blocks.FIRED_CRUCIBLE));
    registration.addRecipeCategories(new HeatRecipeCategory(guiHelper));
  }

  @Override
  public void registerRecipeCatalysts(@Nonnull final IRecipeCatalystRegistration registration) {
    registerHammerCatalyst(registration);
    registerCrookCatalyst(registration);
    registerCrucibles(registration);
    registerBarrels(registration);
    registerSieves(registration);
  }

  private void registerCrookCatalyst(@Nonnull final IRecipeCatalystRegistration registration) {
    Set<ItemDefinition<CrookItem>> crooks =
        Set.of(
            EXNItems.CROOK_ANDESITE,
            EXNItems.CROOK_BASALT,
            EXNItems.CROOK_BLACKSTONE,
            EXNItems.CROOK_BONE,
            EXNItems.CROOK_CALCITE,
            EXNItems.CROOK_COPPER,
            EXNItems.CROOK_DEEPSLATE,
            EXNItems.CROOK_DIAMOND,
            EXNItems.CROOK_DIORITE,
            EXNItems.CROOK_DRIPSTONE,
            EXNItems.CROOK_GOLD,
            EXNItems.CROOK_GRANITE,
            EXNItems.CROOK_IRON,
            EXNItems.CROOK_NETHER_BRICK,
            EXNItems.CROOK_NETHERITE,
            EXNItems.CROOK_RED_NETHER_BRICK,
            EXNItems.CROOK_STONE,
            EXNItems.CROOK_TERRACOTTA,
            EXNItems.CROOK_TUFF,
            EXNItems.CROOK_WOOD);
    for (ItemDefinition<CrookItem> crook : crooks) {
      registration.addRecipeCatalyst(crook.itemStack(), RecipeTypes.CROOK);
    }
  }

  private void registerHammerCatalyst(@Nonnull final IRecipeCatalystRegistration registration) {
    Set<ItemDefinition<HammerItem>> hammers =
        Set.of(
            EXNItems.HAMMER_ANDESITE,
            EXNItems.HAMMER_BASALT,
            EXNItems.HAMMER_BLACKSTONE,
            EXNItems.HAMMER_BONE,
            EXNItems.HAMMER_CALCITE,
            EXNItems.HAMMER_COPPER,
            EXNItems.HAMMER_DEEPSLATE,
            EXNItems.HAMMER_DIAMOND,
            EXNItems.HAMMER_DIORITE,
            EXNItems.HAMMER_DRIPSTONE,
            EXNItems.HAMMER_GOLD,
            EXNItems.HAMMER_GRANITE,
            EXNItems.HAMMER_IRON,
            EXNItems.HAMMER_NETHER_BRICK,
            EXNItems.HAMMER_NETHERITE,
            EXNItems.HAMMER_RED_NETHER_BRICK,
            EXNItems.HAMMER_STONE,
            EXNItems.HAMMER_TERRACOTTA,
            EXNItems.HAMMER_TUFF,
            EXNItems.HAMMER_WOOD);

    for (ItemDefinition<HammerItem> hammer : hammers) {
      registration.addRecipeCatalyst(hammer.itemStack(), RecipeTypes.HAMMER);
    }
  }

  private void registerCrucibles(@Nonnull final IRecipeCatalystRegistration registration) {
    List<BlockDefinition<CrucibleBaseBlock>> nonFiredCrucibles =
        List.of(
            EXNBlocks.ACACIA_CRUCIBLE,
            EXNBlocks.BIRCH_CRUCIBLE,
            EXNBlocks.DARK_OAK_CRUCIBLE,
            EXNBlocks.JUNGLE_CRUCIBLE,
            EXNBlocks.MANGROVE_CRUCIBLE,
            EXNBlocks.OAK_CRUCIBLE,
            EXNBlocks.SPRUCE_CRUCIBLE);
    for (BlockDefinition<CrucibleBaseBlock> blockDefinition : nonFiredCrucibles) {
      registration.addRecipeCatalyst(
          blockDefinition.itemStack(), RecipeTypes.CRUCIBLE, RecipeTypes.HEAT);
    }

    List<BlockDefinition<CrucibleBaseBlock>> firedCrucibles =
        List.of(EXNBlocks.FIRED_CRUCIBLE, EXNBlocks.CRIMSON_CRUCIBLE, EXNBlocks.WARPED_CRUCIBLE);
    for (BlockDefinition<CrucibleBaseBlock> blockDefinition : nonFiredCrucibles) {
      registration.addRecipeCatalyst(
          blockDefinition.itemStack(),
          RecipeTypes.FIRED_CRUCIBLE,
          RecipeTypes.CRUCIBLE,
          RecipeTypes.HEAT);
    }
  }

  private void registerBarrels(@Nonnull final IRecipeCatalystRegistration registration) {

    List<BlockDefinition<BlockBarrel>> barrels =
        List.of(
            EXNBlocks.ACACIA_BARREL,
            EXNBlocks.BIRCH_BARREL,
            EXNBlocks.DARK_OAK_BARREL,
            EXNBlocks.JUNGLE_BARREL,
            EXNBlocks.MANGROVE_BARREL,
            EXNBlocks.OAK_BARREL,
            EXNBlocks.SPRUCE_BARREL,
            EXNBlocks.STONE_BARREL,
            EXNBlocks.CRIMSON_BARREL,
            EXNBlocks.WARPED_BARREL);
    for (BlockDefinition<BlockBarrel> blockDefinition : barrels) {
      registration.addRecipeCatalyst(
          blockDefinition.itemStack(),
          RecipeTypes.FLUID_ON_TOP,
          RecipeTypes.FLUID_TRANSFORM,
          RecipeTypes.FLUID_ITEM,
          RecipeTypes.COMPOST);
    }
  }

  private void registerSieves(@Nonnull final IRecipeCatalystRegistration registration) {
    List<BlockDefinition<BlockSieve>> sieves =
        List.of(
            EXNBlocks.ACACIA_SIEVE,
            EXNBlocks.BIRCH_SIEVE,
            EXNBlocks.DARK_OAK_SIEVE,
            EXNBlocks.JUNGLE_SIEVE,
            EXNBlocks.MANGROVE_SIEVE,
            EXNBlocks.OAK_SIEVE,
            EXNBlocks.SPRUCE_SIEVE,
            EXNBlocks.CRIMSON_SIEVE,
            EXNBlocks.WARPED_SIEVE);
    for (BlockDefinition<BlockSieve> blockDefinition : sieves) {
      registration.addRecipeCatalyst(
          blockDefinition.itemStack(), RecipeTypes.DRY_SIEVE, RecipeTypes.WET_SIEVE);
    }
  }

  @Override
  public void registerRecipes(@Nonnull final IRecipeRegistration registration) {
    registerCrook(registration);
    registerSieve(registration);
    registerHammer(registration);
    registerFluidOnTop(registration);
    registerFluidTransform(registration);
    registerFluidBlock(registration);
    registerCompost(registration);
    registerFiredCrucible(registration);
    registerWoodCrucible(registration);
    registerHeat(registration);
  }

  private void registerCompost(@Nonnull final IRecipeRegistration registration) {
    @Nonnull
    final List<CompostRecipe> recipes = ExNihiloRegistries.COMPOST_REGISTRY.getRecipeList();
    registration.addRecipes(RecipeTypes.COMPOST, recipes);
    logger.info("Compost Recipes Loaded: " + recipes.size());
  }

  private void registerCrook(@Nonnull final IRecipeRegistration registration) {
    @Nonnull
    final List<CrookRecipe> crookRecipes = ExNihiloRegistries.CROOK_REGISTRY.getRecipeList();
    registration.addRecipes(RecipeTypes.CROOK, crookRecipes);
    logger.info("Crook Recipes Loaded: " + crookRecipes.size());
  }

  private void registerFiredCrucible(@Nonnull final IRecipeRegistration registration) {
    @Nonnull
    final List<JEICrucibleRecipe> recipes = ExNihiloRegistries.CRUCIBLE_REGISTRY.getRecipeList();
    registration.addRecipes(RecipeTypes.FIRED_CRUCIBLE, recipes);
    logger.info("Fired Crucible Recipes Loaded: " + recipes.size());
  }

  private void registerFluidBlock(@Nonnull final IRecipeRegistration registration) {
    @Nonnull
    final List<FluidItemRecipe> recipes = ExNihiloRegistries.FLUID_BLOCK_REGISTRY.getRecipeList();
    registration.addRecipes(RecipeTypes.FLUID_ITEM, recipes);
    logger.info("Fluid Item Recipes Loaded: " + recipes.size());
  }

  private void registerFluidOnTop(@Nonnull final IRecipeRegistration registration) {
    @Nonnull
    final List<FluidOnTopRecipe> recipes = ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.getRecipeList();
    registration.addRecipes(RecipeTypes.FLUID_ON_TOP, recipes);
    logger.info("Fluid On Top Recipes Loaded: " + recipes.size());
  }

  private void registerFluidTransform(@Nonnull final IRecipeRegistration registration) {
    @Nonnull
    final List<FluidTransformRecipe> recipes =
        ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.getRecipeList();
    registration.addRecipes(RecipeTypes.FLUID_TRANSFORM, recipes);
    logger.info("Fluid Transform Recipes Loaded: " + recipes.size());
  }

  private void registerHammer(@Nonnull final IRecipeRegistration registration) {
    @Nonnull final List<HammerRecipe> recipes = ExNihiloRegistries.HAMMER_REGISTRY.getRecipeList();
    registration.addRecipes(RecipeTypes.HAMMER, recipes);
    logger.info("Hammer Recipes Loaded: " + recipes.size());
  }

  private void registerHeat(@Nonnull final IRecipeRegistration registration) {
    @Nonnull final List<HeatRecipe> recipes = ExNihiloRegistries.HEAT_REGISTRY.getRecipeList();
    registration.addRecipes(RecipeTypes.HEAT, recipes);
    logger.info("Heat Recipes Loaded: " + recipes.size());
  }

  private void registerSieve(@Nonnull final IRecipeRegistration registration) {
    @Nonnull
    final List<JEISieveRecipe> drySieveRecipes =
        ExNihiloRegistries.SIEVE_REGISTRY.getDryRecipeList();
    @Nonnull
    final List<JEISieveRecipe> wetSieveRecipes =
        ExNihiloRegistries.SIEVE_REGISTRY.getWetRecipeList();
    registration.addRecipes(RecipeTypes.DRY_SIEVE, drySieveRecipes);
    registration.addRecipes(RecipeTypes.WET_SIEVE, wetSieveRecipes);
    logger.info("Sieve Recipes Loaded: " + (drySieveRecipes.size() + wetSieveRecipes.size()));
  }

  private void registerWoodCrucible(@Nonnull final IRecipeRegistration registration) {
    @Nonnull
    final List<JEICrucibleRecipe> recipes =
        ExNihiloRegistries.CRUCIBLE_REGISTRY.getRecipeList().stream()
            .filter(recipe -> recipe.getCrucibleType() == CrucibleTypeEnum.WOOD)
            .collect(Collectors.toList());
    registration.addRecipes(RecipeTypes.CRUCIBLE, recipes);
    logger.info("Wooden Crucible Recipes Loaded: " + recipes.size());
  }
}
