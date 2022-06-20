package novamachina.exnihilosequentia.common.compat.jei;

 import java.util.List;
 import java.util.stream.Collectors;
 import javax.annotation.Nonnull;
 import mezz.jei.api.IModPlugin;
 import mezz.jei.api.JeiPlugin;
 import mezz.jei.api.helpers.IGuiHelper;
 import mezz.jei.api.registration.IRecipeCatalystRegistration;
 import mezz.jei.api.registration.IRecipeCategoryRegistration;
 import mezz.jei.api.registration.IRecipeRegistration;
 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.world.item.ItemStack;
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
 import novamachina.exnihilosequentia.common.compat.jei.sieve.JEISieveRecipe;
 import novamachina.exnihilosequentia.common.compat.jei.sieve.DrySieveRecipeCategory;
 import novamachina.exnihilosequentia.common.compat.jei.sieve.WetSieveRecipeCategory;
 import novamachina.exnihilosequentia.common.crafting.compost.CompostRecipe;
 import novamachina.exnihilosequentia.common.crafting.crook.CrookRecipe;
 import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
 import novamachina.exnihilosequentia.common.crafting.fluiditem.FluidItemRecipe;
 import novamachina.exnihilosequentia.common.crafting.fluidontop.FluidOnTopRecipe;
 import novamachina.exnihilosequentia.common.crafting.fluidtransform.FluidTransformRecipe;
 import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
 import novamachina.exnihilosequentia.common.crafting.heat.HeatRecipe;
 import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
 import novamachina.exnihilosequentia.common.init.ExNihiloItems;
 import novamachina.exnihilosequentia.common.item.CrookBaseItem;
 import novamachina.exnihilosequentia.common.item.HammerBaseItem;
 import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
 import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
 import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
 import org.apache.logging.log4j.LogManager;

 @JeiPlugin
 @SuppressWarnings("unused")
 public class JEIPlugin implements IModPlugin {

  @Nonnull private static final ExNihiloLogger logger = new
 ExNihiloLogger(LogManager.getLogger());

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
    registerCookCatalyst(ExNihiloItems.CROOK_WOOD.get(), registration);
    registerCookCatalyst(ExNihiloItems.CROOK_STONE.get(), registration);
    registerCookCatalyst(ExNihiloItems.CROOK_ANDESITE.get(), registration);
    registerCookCatalyst(ExNihiloItems.CROOK_GRANITE.get(), registration);
    registerCookCatalyst(ExNihiloItems.CROOK_DIORITE.get(), registration);
    registerCookCatalyst(ExNihiloItems.CROOK_GOLD.get(), registration);
    registerCookCatalyst(ExNihiloItems.CROOK_IRON.get(), registration);
    registerCookCatalyst(ExNihiloItems.CROOK_DIAMOND.get(), registration);
    registerCookCatalyst(ExNihiloItems.CROOK_BONE.get(), registration);

//    registerHammerCatalyst(ExNihiloItems.HAMMER_ANDESITE.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_BASALT.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_BLACKSTONE.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_CALCITE.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_COPPER.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_CRIMSON_FUNGUS.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_DEEPSLATE.get(), registration);
    registerHammerCatalyst(ExNihiloItems.HAMMER_DIAMOND.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_DIORITE.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_DRIPSTONE.get(), registration);
    registerHammerCatalyst(ExNihiloItems.HAMMER_GOLD.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_GRANITE.get(), registration);
    registerHammerCatalyst(ExNihiloItems.HAMMER_IRON.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_NETHER_BRICK.get(), registration);
    registerHammerCatalyst(ExNihiloItems.HAMMER_NETHERITE.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_PRISMARINE.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_RED_NETHER_BRICK.get(), registration);
    registerHammerCatalyst(ExNihiloItems.HAMMER_STONE.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_TERRACOTTA.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_TUFF.get(), registration);
//    registerHammerCatalyst(ExNihiloItems.HAMMER_WARPED_FUNGUS.get(), registration);
    registerHammerCatalyst(ExNihiloItems.HAMMER_WOOD.get(), registration);

    registerCrucibles(registration);
    registerBarrels(registration);
    registerSieves(registration);
  }

  private void registerCookCatalyst(
      CrookBaseItem crook, @Nonnull final IRecipeCatalystRegistration registration) {
    registration.addRecipeCatalyst(new ItemStack(crook), RecipeTypes.CROOK);
  }

  private void registerHammerCatalyst(
      HammerBaseItem hammer, @Nonnull final IRecipeCatalystRegistration registration) {
    registration.addRecipeCatalyst(new ItemStack(hammer), RecipeTypes.HAMMER);
  }

  private void registerCrucibles(@Nonnull final IRecipeCatalystRegistration registration) {
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.CRUCIBLE_ACACIA.get()),
        RecipeTypes.CRUCIBLE,
        RecipeTypes.HEAT);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.CRUCIBLE_BIRCH.get()), RecipeTypes.CRUCIBLE,
 RecipeTypes.HEAT);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get()),
        RecipeTypes.CRUCIBLE,
        RecipeTypes.HEAT);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.CRUCIBLE_JUNGLE.get()),
        RecipeTypes.CRUCIBLE,
        RecipeTypes.HEAT);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.CRUCIBLE_OAK.get()), RecipeTypes.CRUCIBLE, RecipeTypes.HEAT);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.CRUCIBLE_SPRUCE.get()),
        RecipeTypes.CRUCIBLE,
        RecipeTypes.HEAT);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.CRUCIBLE_FIRED.get()),
        RecipeTypes.FIRED_CRUCIBLE,
        RecipeTypes.CRUCIBLE);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.CRUCIBLE_CRIMSON.get()),
        RecipeTypes.FIRED_CRUCIBLE,
        RecipeTypes.CRUCIBLE);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.CRUCIBLE_WARPED.get()),
        RecipeTypes.FIRED_CRUCIBLE,
        RecipeTypes.CRUCIBLE);
  }

  private void registerBarrels(@Nonnull final IRecipeCatalystRegistration registration) {
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.BARREL_ACACIA.get()),
        RecipeTypes.FLUID_ON_TOP,
        RecipeTypes.FLUID_TRANSFORM,
        RecipeTypes.FLUID_ITEM,
        RecipeTypes.COMPOST);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.BARREL_BIRCH.get()),
        RecipeTypes.FLUID_ON_TOP,
        RecipeTypes.FLUID_TRANSFORM,
        RecipeTypes.FLUID_ITEM,
        RecipeTypes.COMPOST);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.BARREL_DARK_OAK.get()),
        RecipeTypes.FLUID_ON_TOP,
        RecipeTypes.FLUID_TRANSFORM,
        RecipeTypes.FLUID_ITEM,
        RecipeTypes.COMPOST);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.BARREL_JUNGLE.get()),
        RecipeTypes.FLUID_ON_TOP,
        RecipeTypes.FLUID_TRANSFORM,
        RecipeTypes.FLUID_ITEM,
        RecipeTypes.COMPOST);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.BARREL_OAK.get()),
        RecipeTypes.FLUID_ON_TOP,
        RecipeTypes.FLUID_TRANSFORM,
        RecipeTypes.FLUID_ITEM,
        RecipeTypes.COMPOST);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.BARREL_SPRUCE.get()),
        RecipeTypes.FLUID_ON_TOP,
        RecipeTypes.FLUID_TRANSFORM,
        RecipeTypes.FLUID_ITEM,
        RecipeTypes.COMPOST);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.BARREL_STONE.get()),
        RecipeTypes.FLUID_ON_TOP,
        RecipeTypes.FLUID_TRANSFORM,
        RecipeTypes.FLUID_ITEM,
        RecipeTypes.COMPOST);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.BARREL_CRIMSON.get()),
        RecipeTypes.FLUID_ON_TOP,
        RecipeTypes.FLUID_TRANSFORM,
        RecipeTypes.FLUID_ITEM,
        RecipeTypes.COMPOST);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.BARREL_WARPED.get()),
        RecipeTypes.FLUID_ON_TOP,
        RecipeTypes.FLUID_TRANSFORM,
        RecipeTypes.FLUID_ITEM,
        RecipeTypes.COMPOST);
  }

  private void registerSieves(@Nonnull final IRecipeCatalystRegistration registration) {
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.SIEVE_ACACIA.get()),
        RecipeTypes.DRY_SIEVE,
        RecipeTypes.WET_SIEVE);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.SIEVE_BIRCH.get()),
        RecipeTypes.DRY_SIEVE,
        RecipeTypes.WET_SIEVE);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.SIEVE_DARK_OAK.get()),
        RecipeTypes.DRY_SIEVE,
        RecipeTypes.WET_SIEVE);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.SIEVE_JUNGLE.get()),
        RecipeTypes.DRY_SIEVE,
        RecipeTypes.WET_SIEVE);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.SIEVE_OAK.get()),
        RecipeTypes.DRY_SIEVE,
        RecipeTypes.WET_SIEVE);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.SIEVE_SPRUCE.get()),
        RecipeTypes.DRY_SIEVE,
        RecipeTypes.WET_SIEVE);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.SIEVE_CRIMSON.get()),
        RecipeTypes.DRY_SIEVE,
        RecipeTypes.WET_SIEVE);
    registration.addRecipeCatalyst(
        new ItemStack(ExNihiloBlocks.SIEVE_WARPED.get()),
        RecipeTypes.DRY_SIEVE,
        RecipeTypes.WET_SIEVE);
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
    final List<FluidOnTopRecipe> recipes =
 ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.getRecipeList();
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
    @Nonnull final List<HammerRecipe> recipes =
 ExNihiloRegistries.HAMMER_REGISTRY.getRecipeList();
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
