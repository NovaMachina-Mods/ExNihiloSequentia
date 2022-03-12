package novamachina.exnihilosequentia.common.compat.jei;

import java.util.List;
import java.util.stream.Collectors;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.compat.jei.JEISieveRecipe;
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
import novamachina.exnihilosequentia.common.compat.jei.sieve.dry.DrySieveRecipeCategory;
import novamachina.exnihilosequentia.common.compat.jei.sieve.wet.WetSieveRecipeCategory;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@JeiPlugin
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    @Nonnull private static final ResourceLocation CRUCIBLES = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.BlockEntities.CRUCIBLES);
    @Nonnull private static final ResourceLocation FIRED_CRUCIBLES = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, ExNihiloConstants.BlockEntities.FIRED_CRUCIBLES);

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ExNihiloConstants.ModIds.JEI, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
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
        registration.addRecipeCategories(new CrucibleRecipeCategory(guiHelper, ExNihiloConstants.BlockEntities.CRUCIBLES));
        registration.addRecipeCategories(new CrucibleRecipeCategory(guiHelper, ExNihiloConstants.BlockEntities.FIRED_CRUCIBLES));
        registration.addRecipeCategories(new HeatRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipeCatalysts(@Nonnull final IRecipeCatalystRegistration registration) {
        for (@Nonnull final EnumCrook crook : EnumCrook.values()) {
            @Nullable final RegistryObject<Item> crookRegistryObject = crook.getRegistryObject();
            if (crookRegistryObject != null)
                registration.addRecipeCatalyst(new ItemStack(crookRegistryObject.get()), CrookRecipeCategory.UID);
        }
        for (@Nonnull final EnumHammer hammer : EnumHammer.values()) {
            @Nullable final RegistryObject<Item> hammerRegistryObject = hammer.getRegistryObject();
            if (hammerRegistryObject != null)
                registration.addRecipeCatalyst(new ItemStack(hammerRegistryObject.get()), HammerRecipeCategory.UID);
        }

        registerCrucibles(registration);
        registerBarrels(registration);
        registerSieves(registration);
    }

    private void registerCrucibles(@Nonnull final IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.CRUCIBLE_ACACIA.get()), CRUCIBLES, HeatRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.CRUCIBLE_BIRCH.get()), CRUCIBLES, HeatRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get()), CRUCIBLES, HeatRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.CRUCIBLE_JUNGLE.get()), CRUCIBLES, HeatRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.CRUCIBLE_OAK.get()), CRUCIBLES, HeatRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.CRUCIBLE_SPRUCE.get()), CRUCIBLES, HeatRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.CRUCIBLE_FIRED.get()), FIRED_CRUCIBLES, HeatRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.CRUCIBLE_CRIMSON.get()), FIRED_CRUCIBLES, HeatRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.CRUCIBLE_WARPED.get()), FIRED_CRUCIBLES, HeatRecipeCategory.UID);
    }

    private void registerBarrels(@Nonnull final IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.BARREL_ACACIA.get()), FluidOnTopRecipeCategory.UID, FluidTransformCategory.UID, FluidBlockRecipeCategory.UID, CompostRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.BARREL_BIRCH.get()), FluidOnTopRecipeCategory.UID, FluidTransformCategory.UID, FluidBlockRecipeCategory.UID, CompostRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.BARREL_DARK_OAK.get()), FluidOnTopRecipeCategory.UID, FluidTransformCategory.UID, FluidBlockRecipeCategory.UID, CompostRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.BARREL_JUNGLE.get()), FluidOnTopRecipeCategory.UID, FluidTransformCategory.UID, FluidBlockRecipeCategory.UID, CompostRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.BARREL_OAK.get()), FluidOnTopRecipeCategory.UID, FluidTransformCategory.UID, FluidBlockRecipeCategory.UID, CompostRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.BARREL_SPRUCE.get()), FluidOnTopRecipeCategory.UID, FluidTransformCategory.UID, FluidBlockRecipeCategory.UID, CompostRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.BARREL_STONE.get()), FluidOnTopRecipeCategory.UID, FluidTransformCategory.UID, FluidBlockRecipeCategory.UID, CompostRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.BARREL_CRIMSON.get()), FluidOnTopRecipeCategory.UID, FluidTransformCategory.UID, FluidBlockRecipeCategory.UID, CompostRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.BARREL_WARPED.get()), FluidOnTopRecipeCategory.UID, FluidTransformCategory.UID, FluidBlockRecipeCategory.UID, CompostRecipeCategory.UID);
    }

    private void registerSieves(@Nonnull final IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.SIEVE_ACACIA.get()), DrySieveRecipeCategory.UID, WetSieveRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.SIEVE_BIRCH.get()), DrySieveRecipeCategory.UID, WetSieveRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.SIEVE_DARK_OAK.get()), DrySieveRecipeCategory.UID, WetSieveRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.SIEVE_JUNGLE.get()), DrySieveRecipeCategory.UID, WetSieveRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.SIEVE_OAK.get()), DrySieveRecipeCategory.UID, WetSieveRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.SIEVE_SPRUCE.get()), DrySieveRecipeCategory.UID, WetSieveRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.SIEVE_CRIMSON.get()), DrySieveRecipeCategory.UID, WetSieveRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ExNihiloBlocks.SIEVE_WARPED.get()), DrySieveRecipeCategory.UID, WetSieveRecipeCategory.UID);
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
        @Nonnull final List<CompostRecipe> recipes = ExNihiloRegistries.COMPOST_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, CompostRecipeCategory.UID);
        logger.info("Compost Recipes Loaded: " + recipes.size());
    }

    private void registerCrook(@Nonnull final IRecipeRegistration registration) {
        @Nonnull final List<CrookRecipe> crookRecipes = ExNihiloRegistries.CROOK_REGISTRY.getRecipeList();
        registration.addRecipes(crookRecipes, CrookRecipeCategory.UID);
        logger.info("Crook Recipes Loaded: " + crookRecipes.size());
    }

    private void registerFiredCrucible(@Nonnull final IRecipeRegistration registration) {
        @Nonnull final List<CrucibleRecipe> recipes = ExNihiloRegistries.CRUCIBLE_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, FIRED_CRUCIBLES);
        logger.info("Fired Crucible Recipes Loaded: " + recipes.size());
    }

    private void registerFluidBlock(@Nonnull final IRecipeRegistration registration) {
        @Nonnull final List<FluidItemRecipe> recipes = ExNihiloRegistries.FLUID_BLOCK_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, FluidBlockRecipeCategory.UID);
        logger.info("Fluid Item Recipes Loaded: " + recipes.size());
    }

    private void registerFluidOnTop(@Nonnull final IRecipeRegistration registration) {
        @Nonnull final List<FluidOnTopRecipe> recipes = ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, FluidOnTopRecipeCategory.UID);
        logger.info("Fluid On Top Recipes Loaded: " + recipes.size());
    }

    private void registerFluidTransform(@Nonnull final IRecipeRegistration registration) {
        @Nonnull final List<FluidTransformRecipe> recipes = ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, FluidTransformCategory.UID);
        logger.info("Fluid Transform Recipes Loaded: " + recipes.size());
    }

    private void registerHammer(@Nonnull final IRecipeRegistration registration) {
        @Nonnull final List<HammerRecipe> recipes = ExNihiloRegistries.HAMMER_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, HammerRecipeCategory.UID);
        logger.info("Hammer Recipes Loaded: " + recipes.size());
    }

    private void registerHeat(@Nonnull final IRecipeRegistration registration) {
        @Nonnull final List<HeatRecipe> recipes = ExNihiloRegistries.HEAT_REGISTRY.getRecipeList();
        registration.addRecipes(recipes, HeatRecipeCategory.UID);
        logger.info("Heat Recipes Loaded: " + recipes.size());
    }

    private void registerSieve(@Nonnull final IRecipeRegistration registration) {
        @Nonnull final List<JEISieveRecipe> drySieveRecipes = ExNihiloRegistries.SIEVE_REGISTRY.getDryRecipeList();
        @Nonnull final List<JEISieveRecipe> wetSieveRecipes = ExNihiloRegistries.SIEVE_REGISTRY.getWetRecipeList();
        registration.addRecipes(drySieveRecipes, DrySieveRecipeCategory.UID);
        registration.addRecipes(wetSieveRecipes, WetSieveRecipeCategory.UID);
        logger.info("Sieve Recipes Loaded: " + (drySieveRecipes.size() + wetSieveRecipes.size()));
    }

    private void registerWoodCrucible(@Nonnull final IRecipeRegistration registration) {
        @Nonnull final List<CrucibleRecipe> recipes = ExNihiloRegistries.CRUCIBLE_REGISTRY.getRecipeList().stream().filter(recipe -> recipe.getCrucibleType() == CrucibleTypeEnum.WOOD).collect(Collectors.toList());
        registration.addRecipes(recipes, CRUCIBLES);
        logger.info("Wooden Crucible Recipes Loaded: " + recipes.size());
    }
}
