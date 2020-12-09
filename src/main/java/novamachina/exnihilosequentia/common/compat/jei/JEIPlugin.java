package novamachina.exnihilosequentia.common.compat.jei;

import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.compat.jei.compost.CompostRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.crook.CrookRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.crucible.CrucibleRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.fluiditem.FluidBlockRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.fluidontop.FluidOnTopRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.fluidtransform.FluidTransformCategory;
import novamachina.exnihilosequentia.common.compat.jei.hammer.HammerRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.heat.HeatRecipeCategory;
import novamachina.exnihilosequentia.api.compat.jei.JEISieveRecipe;
import novamachina.exnihilosequentia.common.compat.jei.sieve.dry.DrySieveRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.sieve.wet.WetSieveRecipeCategory;
import novamachina.exnihilosequentia.common.init.ModBlocks;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import novamachina.exnihilosequentia.common.utility.Constants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Constants.ModIds.JEI, Constants.ModIds.EX_NIHILO_SEQUENTIA);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new CrookRecipeCategory(guiHelper));
        registration.addRecipeCategories(new DrySieveRecipeCategory(guiHelper));
        registration.addRecipeCategories(new WetSieveRecipeCategory(guiHelper));
        registration.addRecipeCategories(new HammerRecipeCategory(guiHelper));
        registration.addRecipeCategories(new FluidOnTopRecipeCategory(guiHelper));
        registration.addRecipeCategories(new FluidTransformCategory(guiHelper));
        registration.addRecipeCategories(new FluidBlockRecipeCategory(guiHelper));
        registration.addRecipeCategories(new CompostRecipeCategory(guiHelper));
        registration.addRecipeCategories(new CrucibleRecipeCategory(guiHelper, Constants.Blocks.CRUCIBLE_WOOD));
        registration.addRecipeCategories(new CrucibleRecipeCategory(guiHelper, Constants.Blocks.CRUCIBLE_FIRED));
        registration.addRecipeCategories(new HeatRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
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

    private void registerHeat(IRecipeRegistration registration) {
        List<HeatRecipe> recipes = ExNihiloRegistries.HEAT_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, HeatRecipeCategory.UID);
        logger.info("Heat Recipes Loaded: " + recipes.size());
    }

    private void registerWoodCrucible(IRecipeRegistration registration) {
        List<CrucibleRecipe> recipes = ExNihiloRegistries.CRUCIBLE_REGISTRY.getRecipeList().stream().filter(recipe -> recipe.getCrucibleType() == CrucilbeTypeEnum.WOOD).collect(Collectors.toList());
        registration.addRecipes(recipes, new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, Constants.Blocks.CRUCIBLE_WOOD));
        logger.info("Wooden Crucible Recipes Loaded: " + recipes.size());
    }

    private void registerFiredCrucible(IRecipeRegistration registration) {
        List<CrucibleRecipe> recipes = ExNihiloRegistries.CRUCIBLE_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, Constants.Blocks.CRUCIBLE_FIRED));
        logger.info("Fired Crucible Recipes Loaded: " + recipes.size());
    }

    private void registerCompost(IRecipeRegistration registration) {
        List<CompostRecipe> recipes = ExNihiloRegistries.COMPOST_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, CompostRecipeCategory.UID);
        logger.info("Compost Recipes Loaded: " + recipes.size());
    }

    private void registerFluidBlock(IRecipeRegistration registration) {
        List<FluidItemRecipe> recipes = ExNihiloRegistries.FLUID_BLOCK_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, FluidBlockRecipeCategory.UID);
        logger.info("Fluid Item Recipes Loaded: " + recipes.size());
    }

    private void registerFluidTransform(IRecipeRegistration registration) {
        List<FluidTransformRecipe> recipes = ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, FluidTransformCategory.UID);
        logger.info("Fluid Transform Recipes Loaded: " + recipes.size());
    }

    private void registerFluidOnTop(IRecipeRegistration registration) {
        List<FluidOnTopRecipe> recipes = ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, FluidOnTopRecipeCategory.UID);
        logger.info("Fluid On Top Recipes Loaded: " + recipes.size());
    }

    private void registerHammer(IRecipeRegistration registration) {
        List<HammerRecipe> recipes = ExNihiloRegistries.HAMMER_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, HammerRecipeCategory.UID);
        logger.info("Hammer Recipes Loaded: " + recipes.size());
    }

    private void registerSieve(IRecipeRegistration registration) {
        List<JEISieveRecipe> drySieveRecipes = ExNihiloRegistries.SIEVE_REGISTRY.getDryRecipeList();
        List<JEISieveRecipe> wetSieveRecipes = ExNihiloRegistries.SIEVE_REGISTRY.getWetRecipeList();
        registration.addRecipes(drySieveRecipes, DrySieveRecipeCategory.UID);
        registration.addRecipes(wetSieveRecipes, WetSieveRecipeCategory.UID);
        logger.info("Sieve Recipes Loaded: " + (drySieveRecipes.size() + wetSieveRecipes.size()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        for (EnumCrook crook : EnumCrook.values()) {
            registration
                .addRecipeCatalyst(new ItemStack(crook.getRegistryObject().get()), CrookRecipeCategory.UID);
        }
        for (EnumHammer hammer : EnumHammer.values()) {
            registration
                .addRecipeCatalyst(new ItemStack(hammer.getRegistryObject().get()), HammerRecipeCategory.UID);
        }

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SIEVE.get()), DrySieveRecipeCategory.UID, WetSieveRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BARREL_WOOD.get()), FluidOnTopRecipeCategory.UID, FluidTransformCategory.UID, FluidBlockRecipeCategory.UID, CompostRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BARREL_STONE.get()), FluidOnTopRecipeCategory.UID, FluidTransformCategory.UID, FluidBlockRecipeCategory.UID, CompostRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUCIBLE_WOOD.get()), new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, Constants.Blocks.CRUCIBLE_WOOD), HeatRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUCIBLE_FIRED.get()), new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, Constants.Blocks.CRUCIBLE_FIRED), HeatRecipeCategory.UID);
    }

    private void registerCrook(IRecipeRegistration registration) {
        List<CrookRecipe> crookRecipes = ExNihiloRegistries.CROOK_REGISTRY.getRecipeList();
        registration.addRecipes(crookRecipes, CrookRecipeCategory.UID);
        logger.info("Crook Recipes Loaded: " + crookRecipes.size());
    }
}
