package novamachina.exnihilosequentia.common.init;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
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
import novamachina.exnihilosequentia.common.compat.ThermalExpansion;
import novamachina.exnihilosequentia.common.compat.top.CompatTOP;
import novamachina.exnihilosequentia.common.tileentity.barrel.mode.BarrelModeRegistry;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.DataPackRegistries;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.registries.ObjectHolder;
import novamachina.exnihilosequentia.common.utility.LogUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = Constants.ModIds.EX_NIHILO_SEQUENTIA, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModInitialization {
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
        ModBlocks.init(modEventBus);
        ModItems.init(modEventBus);
        ModTiles.init(modEventBus);
        ModFluids.init(modEventBus);
        ModSerializers.init(modEventBus);
    }

    @SubscribeEvent
    public static void setupNonTagBasedRegistries(FMLCommonSetupEvent event) {
        BarrelModeRegistry.initialize();
    }

    @SubscribeEvent
    public static void onServerStart(FMLServerStartingEvent event) {
        registerOreCompat();
        activateOreCompat();
    }

    @SubscribeEvent
    public static void registerTOP(InterModEnqueueEvent event) {
        if (ModList.get().isLoaded(Constants.ModIds.TOP)) {
            CompatTOP.register();
        }
    }

    public static void loadRecipes(RecipesUpdatedEvent event){
        LogUtil.debug("Loading Recipes");
        Collection<IRecipe<?>> recipes = event.getRecipeManager().getRecipes();
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

    private static <R extends IRecipe<?>> List<R> filterRecipes(Collection<IRecipe<?>> recipes, Class<R> recipeClass, IRecipeType<R> recipeType)
    {
        return recipes.stream()
            .filter(iRecipe -> iRecipe.getType() == recipeType)
            .flatMap(Stream::of)
            .map(recipeClass::cast)
            .collect(Collectors.toList());
    }

    private static void activateOreCompat() {
        oreCompats.forEach(IOreCompat::activateOres);
    }

    private static void registerOreCompat() {
        oreCompats.add(new ExNihilo());
        if (ModList.get().isLoaded(Constants.ModIds.THERMAL_EXPANSION) || Config.ENABLE_THERMAL.get()) {
            oreCompats.add(new ThermalExpansion());
        }
        if (ModList.get().isLoaded(Constants.ModIds.IMMERSIVE_ENGINEERING) || Config.ENABLE_IMMERSIVE.get()) {
            oreCompats.add(new ImmersiveEngineering());
        }
        if (ModList.get().isLoaded(Constants.ModIds.MEKANISM) || Config.ENABLE_MEKANISM.get()) {
            oreCompats.add(new Mekanism());
        }
        if (ModList.get().isLoaded(Constants.ModIds.CREATE) || Config.ENABLE_CREATE.get()) {
            oreCompats.add(new Create());
        }
    }
}
