package novamachina.exnihilosequentia.common.init;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.BlockHitResult;
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
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
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
import novamachina.exnihilosequentia.common.compat.top.CompatTOP;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.network.PacketHandler;
import novamachina.exnihilosequentia.common.tileentity.barrel.mode.BarrelModeRegistry;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static novamachina.exnihilosequentia.api.datagen.AbstractRecipeGenerator.createMCCompost;

@Mod.EventBusSubscriber(modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExNihiloInitialization {
    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(EnumCrook.WOOD.getRegistryObject().get());
        }
    };
    @ObjectHolder(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":use_hammer")
    public static final GlobalLootModifierSerializer<?> hammerModifier = null;

    @ObjectHolder(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":use_crook")
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
    public static void onServerStart(FMLServerStartingEvent event) {
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
        logger.debug("The One Probe detected: " + ModList.get().isLoaded(ExNihiloConstants.ModIds.TOP));
        if (ModList.get().isLoaded(ExNihiloConstants.ModIds.TOP)) {
            CompatTOP.register();
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
        DispenseItemBehavior idispenseitembehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(BlockSource p_82487_1_, ItemStack p_82487_2_) {
                BucketItem bucketitem = (BucketItem)p_82487_2_.getItem();
                BlockPos blockpos = p_82487_1_.getPos().relative(p_82487_1_.getBlockState().getValue(DispenserBlock.FACING));
                Level world = p_82487_1_.getLevel();
                if (bucketitem.emptyContents((Player)null, world, blockpos, (BlockHitResult)null)) {
                    bucketitem.checkExtraContent(null, world, p_82487_2_, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultDispenseItemBehavior.dispense(p_82487_1_, p_82487_2_);
                }
            }
        };
        DispenserBlock.registerBehavior(ExNihiloItems.SEA_WATER_BUCKET.get(), idispenseitembehavior);
        DispenserBlock.registerBehavior(ExNihiloItems.WITCH_WATER_BUCKET.get(), idispenseitembehavior);
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
                .flatMap(Stream::of)
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

        logger.debug("Immersive Engineering detected: " + ModList.get().isLoaded(ExNihiloConstants.ModIds.IMMERSIVE_ENGINEERING));
        if (ModList.get().isLoaded(ExNihiloConstants.ModIds.IMMERSIVE_ENGINEERING)) {
            logger.debug("Added Immersive Engineering");
            EnumOre.ALUMINUM.setEnabled(true);
            EnumOre.SILVER.setEnabled(true);
            EnumOre.NICKEL.setEnabled(true);
            EnumOre.LEAD.setEnabled(true);
            EnumOre.URANIUM.setEnabled(true);
        }
        logger.debug("Create detected: " + ModList.get().isLoaded(ExNihiloConstants.ModIds.CREATE));
        if (ModList.get().isLoaded(ExNihiloConstants.ModIds.CREATE)) {
            logger.debug("Added Create");
            EnumOre.ZINC.setEnabled(true);
        }
    }
}
