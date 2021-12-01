package novamachina.exnihilosequentia.common.init;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.registries.ObjectHolder;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.api.utility.Config;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.common.compat.top.CompatTOP;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.network.PacketHandler;
import novamachina.exnihilosequentia.common.tileentity.barrel.mode.BarrelModeRegistry;
import novamachina.exnihilosequentia.api.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static novamachina.exnihilosequentia.api.datagen.AbstractRecipeGenerator.createMCCompost;

@Mod.EventBusSubscriber(modid = ModIds.EX_NIHILO_SEQUENTIA, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExNihiloInitialization {
    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(ModIds.EX_NIHILO_SEQUENTIA) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(EnumCrook.WOOD.getRegistryObject().get());
        }
    };
    @ObjectHolder(ModIds.EX_NIHILO_SEQUENTIA + ":use_hammer")
    public static final GlobalLootModifierSerializer<?> hammerModifier = null;

    @ObjectHolder(ModIds.EX_NIHILO_SEQUENTIA + ":use_crook")
    public static final GlobalLootModifierSerializer<?> crookModifier = null;

    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private ExNihiloInitialization() {
    }

    // MinecraftForge.EVENT_BUS
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void clearRegistries(ClientPlayerNetworkEvent.LoggedOutEvent event) {
        logger.debug("Fired LoggedOutEvent");
        ExNihiloRegistries.clearRegistries();
    }

    public static void init(IEventBus modEventBus) {
        logger.debug("Initializing modded items");
        ExNihiloBlocks.init(modEventBus);
        ExNihiloItems.init(modEventBus);
        ExNihiloTiles.init(modEventBus);
        ExNihiloFluids.init(modEventBus);
        ExNihiloSerializers.init(modEventBus);
    }

    // MinecraftForge.EVENT_BUS
    @SubscribeEvent
    public static void loadClientRecipes(RecipesUpdatedEvent event) {
        ExNihiloRegistries.clearRegistries();
        loadRecipes(event.getRecipeManager());
    }

    // MinecraftForge.EVENT_BUS
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        logger.debug("Fired PlayerLoggedInEvent");
    }

    // MinecraftForge.EVENT_BUS
    @SubscribeEvent
    public static void onServerStart(ServerStartingEvent event) {
        logger.debug("Fired FMLServerStartingEvent");
        registerOreCompat();
        overrideOres();
        if (event.getServer().isDedicatedServer()) {
            loadRecipes(event.getServer().getRecipeManager());
        }
    }

    // MinecraftForge.EVENT_BUS
    @SubscribeEvent
    public static void registerTOP(InterModEnqueueEvent event) {
        logger.debug("The One Probe detected: " + ModList.get().isLoaded(ModIds.TOP));
        if (ModList.get().isLoaded(ModIds.TOP)) {
            //CompatTOP.register();
        }
    }

    // MinecraftForge.EVENT_BUS
    @SubscribeEvent
    public static void setupNonTagBasedRegistries(FMLCommonSetupEvent event) {
        logger.debug("Fired FMLCommonSetupEvent");
        ExNihiloItems.fillOreIngots();
        BarrelModeRegistry.initialize();
        PacketHandler.registerMessages();
        registerVanillaCompost();
        registerDispenserFluids();
        ExNihiloStats.register();
    }

    private static void registerDispenserFluids() {
        DispenseItemBehavior iDispenseItemBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            @Nonnull
            public ItemStack execute(BlockSource source, ItemStack itemStack) {
                BucketItem bucketitem = (BucketItem)itemStack.getItem();
                BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                Level world = source.getLevel();
                if (bucketitem.emptyContents(null, world, blockpos, null)) {
                    bucketitem.checkExtraContent(null, world, itemStack, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultDispenseItemBehavior.dispense(source, itemStack);
                }
            }
        };
        DispenserBlock.registerBehavior(ExNihiloItems.SEA_WATER_BUCKET.get(), iDispenseItemBehavior);
        DispenserBlock.registerBehavior(ExNihiloItems.WITCH_WATER_BUCKET.get(), iDispenseItemBehavior);
    }

    private static void registerVanillaCompost() {
        createMCCompost(EnumResource.GRASS_SEED.getRegistryObject().get().asItem(), 0.3F);
        createMCCompost(EnumResource.ANCIENT_SPORE.getRegistryObject().get().asItem(), 0.3F);
		createMCCompost(ExNihiloItems.SILKWORM.get(), 0.3F);
        createMCCompost(ExNihiloItems.COOKED_SILKWORM.get(), 0.3F);
    }

    private static <R extends Recipe<?>> List<R> filterRecipes(Collection<Recipe<?>> recipes, Class<R> recipeClass, RecipeType<R> recipeType) {
        logger.debug("Filter Recipes, Class: " + recipeClass + ", Recipe Type: " + recipeType);
        return recipes.stream()
                .filter(iRecipe -> iRecipe.getType() == recipeType)
                .map(recipeClass::cast)
                .collect(Collectors.toList());
    }

    private static void loadRecipes(RecipeManager manager) {
        logger.debug("Loading Recipes");
        Collection<Recipe<?>> recipes = manager.getRecipes();
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

    private static void overrideOres() {
        if (Config.enableOreOverride()) {
            EnumOre.LEAD.setEnabled(Config.enableLead());
            EnumOre.NICKEL.setEnabled(Config.enableNickel());
            EnumOre.SILVER.setEnabled(Config.enableSilver());
            EnumOre.TIN.setEnabled(Config.enableTin());
            EnumOre.ALUMINUM.setEnabled(Config.enableAluminum());
            EnumOre.PLATINUM.setEnabled(Config.enablePlatinum());
            EnumOre.URANIUM.setEnabled(Config.enableUranium());
            EnumOre.ZINC.setEnabled(Config.enableZinc());
            EnumOre.IRON.setEnabled(Config.enableIron());
            EnumOre.GOLD.setEnabled(Config.enableGold());
            EnumOre.COPPER.setEnabled(Config.enableCopper());
        }
    }

    private static void registerOreCompat() {
        logger.debug("Register ore compatibility");

        EnumOre.IRON.setEnabled(true);
        EnumOre.GOLD.setEnabled(true);
        EnumOre.COPPER.setEnabled(true);

        logger.debug("Immersive Engineering detected: " + ModList.get().isLoaded(ModIds.IMMERSIVE_ENGINEERING));
        if (ModList.get().isLoaded(ModIds.IMMERSIVE_ENGINEERING)) {
            logger.debug("Added Immersive Engineering");
            EnumOre.ALUMINUM.setEnabled(true);
            EnumOre.SILVER.setEnabled(true);
            EnumOre.NICKEL.setEnabled(true);
            EnumOre.LEAD.setEnabled(true);
            EnumOre.URANIUM.setEnabled(true);
        }
        logger.debug("Create detected: " + ModList.get().isLoaded(ModIds.CREATE));
        if (ModList.get().isLoaded(ModIds.CREATE)) {
            logger.debug("Added Create");
            EnumOre.ZINC.setEnabled(true);
        }
    }
}
