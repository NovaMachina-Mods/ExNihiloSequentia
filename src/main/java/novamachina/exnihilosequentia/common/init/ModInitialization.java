package novamachina.exnihilosequentia.common.init;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.compat.ore.IOreCompat;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidItem.FluidItemRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.compat.Create;
import novamachina.exnihilosequentia.common.compat.ExNihilo;
import novamachina.exnihilosequentia.common.compat.ImmersiveEngineering;
import novamachina.exnihilosequentia.common.compat.Mekanism;
import novamachina.exnihilosequentia.common.compat.SilentMechanism;
import novamachina.exnihilosequentia.common.compat.ThermalExpansion;
import novamachina.exnihilosequentia.common.compat.top.CompatTOP;
import novamachina.exnihilosequentia.common.tileentity.barrel.mode.BarrelModeRegistry;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.registries.ObjectHolder;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = Constants.ModIds.EX_NIHILO_SEQUENTIA, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModInitialization {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    private static final List<IOreCompat> oreCompats = new ArrayList<>();

    public static final ItemGroup ITEM_GROUP = new ItemGroup(Constants.ModIds.EX_NIHILO_SEQUENTIA) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.SIEVE.get());
        }
    };

    @ObjectHolder(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":use_hammer")
    public static GlobalLootModifierSerializer<?> HAMMER_MODIFIER = null;

    public static void init(IEventBus modEventBus) {
        logger.debug("Initializing modded items");
        ModBlocks.init(modEventBus);
        ModItems.init(modEventBus);
        ModTiles.init(modEventBus);
        ModFluids.init(modEventBus);
        ModSerializers.init(modEventBus);
    }

    @SubscribeEvent
    public static void setupNonTagBasedRegistries(FMLCommonSetupEvent event) {
        logger.debug("Fired FMLCommonSetupEvent");
        BarrelModeRegistry.initialize();
    }

    @SubscribeEvent
    public static void onServerStart(FMLServerStartingEvent event) {
        logger.debug("Fired FMLServerStartingEvent");
        registerOreCompat();
        activateOreCompat();
        loadRecipes(event.getServer().getRecipeManager());
    }

    @SubscribeEvent
    public static void registerTOP(InterModEnqueueEvent event) {
        logger.debug("The One Probe detected: " + ModList.get().isLoaded(Constants.ModIds.TOP));
        if (ModList.get().isLoaded(Constants.ModIds.TOP)) {
            CompatTOP.register();
        }
    }

    private static void loadRecipes(RecipeManager manager) {
        logger.debug("Loading Recipes");
        Collection<IRecipe<?>> recipes = manager.getRecipes();
        if(recipes.size() == 0) {
            return;
        }

        ExNihiloRegistries.HAMMER_REGISTRY.setRecipes(filterRecipes(recipes, HammerRecipe.class, HammerRecipe.TYPE));
        ExNihiloRegistries.CROOK_REGISTRY.setRecipes(filterRecipes(recipes, CrookRecipe.class, CrookRecipe.TYPE));
        ExNihiloRegistries.COMPOST_REGISTRY.setRecipes(filterRecipes(recipes, CompostRecipe.class, CompostRecipe.TYPE));
        ExNihiloRegistries.FLUID_BLOCK_REGISTRY.setRecipes(filterRecipes(recipes, FluidItemRecipe.class, FluidItemRecipe.TYPE));
        ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.setRecipes(filterRecipes(recipes, FluidOnTopRecipe.class, FluidOnTopRecipe.TYPE));
        ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.setRecipes(filterRecipes(recipes, FluidTransformRecipe.class, FluidTransformRecipe.TYPE));
        ExNihiloRegistries.CRUCIBLE_REGISTRY.setRecipes(filterRecipes(recipes, CrucibleRecipe.class, CrucibleRecipe.TYPE));
        ExNihiloRegistries.HEAT_REGISTRY.setRecipes(filterRecipes(recipes, HeatRecipe.class, HeatRecipe.TYPE));
        ExNihiloRegistries.SIEVE_REGISTRY.setRecipes(filterRecipes(recipes, SieveRecipe.class, SieveRecipe.TYPE));
    }

    public static void loadClientRecipes(RecipesUpdatedEvent event){
        loadRecipes(event.getRecipeManager());
    }

    private static <R extends IRecipe<?>> List<R> filterRecipes(Collection<IRecipe<?>> recipes, Class<R> recipeClass, IRecipeType<R> recipeType)
    {
        logger.debug("Filter Recipes, Class: " + recipeClass + ", Recipe Type: " + recipeType);
        return recipes.stream()
            .filter(iRecipe -> iRecipe.getType() == recipeType)
            .flatMap(Stream::of)
            .map(recipeClass::cast)
            .collect(Collectors.toList());
    }

    private static void activateOreCompat() {
        logger.debug("Activating ore compat");
        oreCompats.forEach(IOreCompat::activateOres);
    }

    private static void registerOreCompat() {
        logger.debug("Register ore compatibility");

        oreCompats.add(new ExNihilo());

        logger.debug("Thermal Expansion detected: " + ModList.get().isLoaded(Constants.ModIds.THERMAL_EXPANSION));
        logger.debug("Thermal Expansion config enabled: " + Config.ENABLE_THERMAL.get());
        if (ModList.get().isLoaded(Constants.ModIds.THERMAL_EXPANSION) || Config.ENABLE_THERMAL.get()) {
            logger.debug("Added Thermal Expansion");
            oreCompats.add(new ThermalExpansion());
        }
        logger.debug("Immersive Engineering detected: " + ModList.get().isLoaded(Constants.ModIds.IMMERSIVE_ENGINEERING));
        logger.debug("Immersive Engineering config enabled: " + Config.ENABLE_IMMERSIVE.get());
        if (ModList.get().isLoaded(Constants.ModIds.IMMERSIVE_ENGINEERING) || Config.ENABLE_IMMERSIVE.get()) {
            logger.debug("Added Immersive Engineering");
            oreCompats.add(new ImmersiveEngineering());
        }
        logger.debug("Mekanism detected: " + ModList.get().isLoaded(Constants.ModIds.MEKANISM));
        logger.debug("Mekanism config enabled: " + Config.ENABLE_MEKANISM.get());
        if (ModList.get().isLoaded(Constants.ModIds.MEKANISM) || Config.ENABLE_MEKANISM.get()) {
            logger.debug("Added Mekanism");
            oreCompats.add(new Mekanism());
        }
        logger.debug("Create detected: " + ModList.get().isLoaded(Constants.ModIds.CREATE));
        logger.debug("Create config enabled: " + Config.ENABLE_CREATE.get());
        if (ModList.get().isLoaded(Constants.ModIds.CREATE) || Config.ENABLE_CREATE.get()) {
            logger.debug("Added Create");
            oreCompats.add(new Create());
        }
        logger.debug("Silent Mechanism detected: " + ModList.get().isLoaded(Constants.ModIds.SILENT_MECHANISM));
        logger.debug("Silent Mechanism enabled: " + Config.ENABLE_SILENT.get());
        if (ModList.get().isLoaded(Constants.ModIds.SILENT_MECHANISM) || Config.ENABLE_SILENT.get()) {
            logger.debug("Added Silent Mechanism");
            oreCompats.add(new SilentMechanism());
        }
    }
}
