package novamachina.exnihilosequentia.common.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.registries.ObjectHolder;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.compat.ore.IOreCompat;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.compat.Create;
import novamachina.exnihilosequentia.common.compat.ExNihilo;
import novamachina.exnihilosequentia.common.compat.ImmersiveEngineering;
import novamachina.exnihilosequentia.common.compat.SilentMechanism;
import novamachina.exnihilosequentia.common.compat.ThermalExpansion;
import novamachina.exnihilosequentia.common.compat.top.CompatTOP;
import novamachina.exnihilosequentia.common.network.PacketHandler;
import novamachina.exnihilosequentia.common.tileentity.barrel.mode.BarrelModeRegistry;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExNihiloInitialization {
    public static final ItemGroup ITEM_GROUP = new ItemGroup(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ExNihiloBlocks.SIEVE.get());
        }
    };
    @ObjectHolder(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":use_hammer")
    public static final GlobalLootModifierSerializer<?> hammerModifier = null;
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    private static final List<IOreCompat> oreCompats = new ArrayList<>();

    private ExNihiloInitialization() {
    }

    public static void init(IEventBus modEventBus) {
        logger.debug("Initializing modded items");
        ExNihiloBlocks.init(modEventBus);
        ExNihiloItems.init(modEventBus);
        ExNihiloTiles.init(modEventBus);
        ExNihiloFluids.init(modEventBus);
        ExNihiloSerializers.init(modEventBus);
    }

    @SubscribeEvent
    public static void setupNonTagBasedRegistries(FMLCommonSetupEvent event) {
        logger.debug("Fired FMLCommonSetupEvent");
        BarrelModeRegistry.initialize();
        PacketHandler.registerMessages();
    }

    @SubscribeEvent
    public static void onServerStart(FMLServerStartingEvent event) {
        logger.debug("Fired FMLServerStartingEvent");
        registerOreCompat();
        activateOreCompat();
        if (event.getServer().isDedicatedServer()) {
            loadRecipes(event.getServer().getRecipeManager());
        }
    }

    public static void loadClientRecipes(RecipesUpdatedEvent event) {
        loadRecipes(event.getRecipeManager());
    }

    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        logger.debug("Fired PlayerLoggedInEvent");
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void clearRegistries(ClientPlayerNetworkEvent.LoggedOutEvent event) {
        logger.debug("Fired LoggedOutEvent");
        ExNihiloRegistries.HAMMER_REGISTRY.clearRecipes();
        ExNihiloRegistries.CROOK_REGISTRY.clearRecipes();
        ExNihiloRegistries.COMPOST_REGISTRY.clearRecipes();
        ExNihiloRegistries.FLUID_BLOCK_REGISTRY.clearRecipes();
        ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.clearRecipes();
        ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.clearRecipes();
        ExNihiloRegistries.CRUCIBLE_REGISTRY.clearRecipes();
        ExNihiloRegistries.HEAT_REGISTRY.clearRecipes();
        ExNihiloRegistries.SIEVE_REGISTRY.clearRecipes();
    }

    @SubscribeEvent
    public static void registerTOP(InterModEnqueueEvent event) {
        logger.debug("The One Probe detected: " + ModList.get().isLoaded(ExNihiloConstants.ModIds.TOP));
        if (ModList.get().isLoaded(ExNihiloConstants.ModIds.TOP)) {
            CompatTOP.register();
        }
    }

    private static void loadRecipes(RecipeManager manager) {
        logger.debug("Loading Recipes");
        Collection<IRecipe<?>> recipes = manager.getRecipes();
        if (recipes.isEmpty()) {
            return;
        }

        ExNihiloRegistries.HAMMER_REGISTRY
            .setRecipes(filterRecipes(recipes, HammerRecipe.class, HammerRecipe.RECIPE_TYPE));
        ExNihiloRegistries.CROOK_REGISTRY
            .setRecipes(filterRecipes(recipes, CrookRecipe.class, CrookRecipe.RECIPE_TYPE));
        ExNihiloRegistries.COMPOST_REGISTRY
            .setRecipes(filterRecipes(recipes, CompostRecipe.class, CompostRecipe.RECIPE_TYPE));
        ExNihiloRegistries.FLUID_BLOCK_REGISTRY
            .setRecipes(filterRecipes(recipes, FluidItemRecipe.class, FluidItemRecipe.RECIPE_TYPE));
        ExNihiloRegistries.FLUID_ON_TOP_REGISTRY
            .setRecipes(filterRecipes(recipes, FluidOnTopRecipe.class, FluidOnTopRecipe.RECIPE_TYPE));
        ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY
            .setRecipes(filterRecipes(recipes, FluidTransformRecipe.class, FluidTransformRecipe.RECIPE_TYPE));
        ExNihiloRegistries.CRUCIBLE_REGISTRY
            .setRecipes(filterRecipes(recipes, CrucibleRecipe.class, CrucibleRecipe.RECIPE_TYPE));
        ExNihiloRegistries.HEAT_REGISTRY.setRecipes(filterRecipes(recipes, HeatRecipe.class, HeatRecipe.RECIPE_TYPE));
        ExNihiloRegistries.SIEVE_REGISTRY.setRecipes(filterRecipes(recipes, SieveRecipe.class, SieveRecipe.RECIPE_TYPE));
    }

    private static <R extends IRecipe<?>> List<R> filterRecipes(Collection<IRecipe<?>> recipes, Class<R> recipeClass, IRecipeType<R> recipeType) {
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

        logger.debug("Thermal Expansion detected: " + ModList.get().isLoaded(ExNihiloConstants.ModIds.THERMAL_EXPANSION));
        logger.debug("Thermal Expansion config enabled: " + Config.getEnableThermal());
        if (ModList.get().isLoaded(ExNihiloConstants.ModIds.THERMAL_EXPANSION) || Config.getEnableThermal()) {
            logger.debug("Added Thermal Expansion");
            oreCompats.add(new ThermalExpansion());
        }
        logger
            .debug("Immersive Engineering detected: " + ModList.get().isLoaded(ExNihiloConstants.ModIds.IMMERSIVE_ENGINEERING));
        logger.debug("Immersive Engineering config enabled: " + Config.getEnableImmersive());
        if (ModList.get().isLoaded(ExNihiloConstants.ModIds.IMMERSIVE_ENGINEERING) || Config.getEnableImmersive()) {
            logger.debug("Added Immersive Engineering");
            oreCompats.add(new ImmersiveEngineering());
        }
        logger.debug("Create detected: " + ModList.get().isLoaded(ExNihiloConstants.ModIds.CREATE));
        logger.debug("Create config enabled: " + Config.getEnableMekanism());
        if (ModList.get().isLoaded(ExNihiloConstants.ModIds.CREATE) || Config.getEnableCreate()) {
            logger.debug("Added Create");
            oreCompats.add(new Create());
        }
        logger.debug("Silent Mechanism detected: " + ModList.get().isLoaded(ExNihiloConstants.ModIds.SILENT_MECHANISM));
        logger.debug("Silent Mechanism enabled: " + Config.getEnableSilent());
        if (ModList.get().isLoaded(ExNihiloConstants.ModIds.SILENT_MECHANISM) || Config.getEnableSilent()) {
            logger.debug("Added Silent Mechanism");
            oreCompats.add(new SilentMechanism());
        }
    }
}
