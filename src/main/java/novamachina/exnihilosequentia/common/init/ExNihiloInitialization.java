package novamachina.exnihilosequentia.common.init;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComposterBlock;
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
import novamachina.exnihilosequentia.common.blockentity.barrel.mode.BarrelModeRegistry;
//import novamachina.exnihilosequentia.common.compat.top.CompatTOP;
import novamachina.exnihilosequentia.common.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.network.PacketHandler;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(
    modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
    bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExNihiloInitialization {

  @Nonnull
  public static final CreativeModeTab ITEM_GROUP =
      new CreativeModeTab(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
          return new ItemStack(ExNihiloBlocks.SIEVE_OAK.get());
        }
      };

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  private ExNihiloInitialization() {}

  // MinecraftForge.EVENT_BUS
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void clearRegistries(@Nonnull final ClientPlayerNetworkEvent.LoggedOutEvent event) {
    logger.debug("Fired LoggedOutEvent");
    ExNihiloRegistries.clearRegistries();
  }

  public static void init(@Nonnull final IEventBus modEventBus) {
    logger.debug("Initializing modded items");
    ExNihiloBlocks.init(modEventBus);
    ExNihiloItems.init(modEventBus);
    ExNihiloBlockEntities.init(modEventBus);
    ExNihiloFluids.init(modEventBus);
    ExNihiloSerializers.init(modEventBus);
    ExNihiloLootModifiers.init(modEventBus);
    ExNihiloRecipeTypes.init(modEventBus);
    ExNihiloSounds.init(modEventBus);
  }

  // MinecraftForge.EVENT_BUS
  @SubscribeEvent
  public static void loadClientRecipes(@Nonnull final RecipesUpdatedEvent event) {
    ExNihiloRegistries.clearRegistries();
    loadRecipes(event.getRecipeManager());
  }

  // MinecraftForge.EVENT_BUS
  @SubscribeEvent
  public static void onPlayerLogin(@Nonnull final PlayerEvent.PlayerLoggedInEvent event) {
    logger.debug("Fired PlayerLoggedInEvent");
  }

  // MinecraftForge.EVENT_BUS
  @SubscribeEvent
  public static void onServerStart(@Nonnull final ServerStartingEvent event) {
    logger.debug("Fired FMLServerStartingEvent");
    registerOreCompat();
    overrideOres();
    if (event.getServer().isDedicatedServer()) {
      loadRecipes(event.getServer().getRecipeManager());
    }
  }

  // MinecraftForge.EVENT_BUS
  @SubscribeEvent
  public static void registerTOP(@Nonnull final InterModEnqueueEvent event) {
    logger.debug("The One Probe detected: " + ModList.get().isLoaded(ExNihiloConstants.ModIds.TOP));
    if (ModList.get().isLoaded(ExNihiloConstants.ModIds.TOP)) {
//      CompatTOP.register();
    }
  }

  // MinecraftForge.EVENT_BUS
  @SubscribeEvent
  public static void setupNonTagBasedRegistries(@Nonnull final FMLCommonSetupEvent event) {
    logger.debug("Fired FMLCommonSetupEvent");
    BarrelModeRegistry.initialize();
    PacketHandler.registerMessages();
    registerVanillaCompost();
    registerDispenserFluids();
    ExNihiloStats.register();
  }

  private static void registerDispenserFluids() {
    @Nonnull
    final DispenseItemBehavior idispenseitembehavior =
        new DefaultDispenseItemBehavior() {
          @Nonnull
          private final DefaultDispenseItemBehavior defaultDispenseItemBehavior =
              new DefaultDispenseItemBehavior();

          @Override
          @Nonnull
          public ItemStack execute(
              @Nonnull final BlockSource pSource, @Nonnull final ItemStack pStack) {
            @Nonnull final BucketItem bucketitem = (BucketItem) pStack.getItem();
            @Nonnull
            final BlockPos blockpos =
                pSource.getPos().relative(pSource.getBlockState().getValue(DispenserBlock.FACING));
            @Nullable final Level world = pSource.getLevel();
            if (bucketitem.emptyContents(null, world, blockpos, null)) {
              bucketitem.checkExtraContent(null, world, pStack, blockpos);
              return new ItemStack(Items.BUCKET);
            } else {
              return this.defaultDispenseItemBehavior.dispense(pSource, pStack);
            }
          }
        };
    DispenserBlock.registerBehavior(ExNihiloItems.SEA_WATER_BUCKET.get(), idispenseitembehavior);
    DispenserBlock.registerBehavior(ExNihiloItems.WITCH_WATER_BUCKET.get(), idispenseitembehavior);
  }

  private static void registerVanillaCompost() {
    createMCCompost(ExNihiloItems.SEED_OAK.get().asItem());
    createMCCompost(ExNihiloItems.SEED_SPRUCE.get().asItem());
    createMCCompost(ExNihiloItems.SEED_BIRCH.get().asItem());
    createMCCompost(ExNihiloItems.SEED_JUNGLE.get().asItem());
    createMCCompost(ExNihiloItems.SEED_ACACIA.get().asItem());
    createMCCompost(ExNihiloItems.SEED_DARK_OAK.get().asItem());
    createMCCompost(ExNihiloItems.SEED_CACTUS.get().asItem());
    createMCCompost(ExNihiloItems.SEED_SUGARCANE.get().asItem());
    createMCCompost(ExNihiloItems.SEED_CARROT.get().asItem());
    createMCCompost(ExNihiloItems.SEED_POTATO.get().asItem());
    createMCCompost(ExNihiloItems.SEED_SWEET_BERRY.get().asItem());
    createMCCompost(ExNihiloItems.SEED_KELP.get().asItem());
    createMCCompost(ExNihiloItems.SEED_PICKLE.get().asItem());
    createMCCompost(ExNihiloItems.SEED_BAMBOO.get().asItem());
    createMCCompost(ExNihiloItems.SEED_FERN.get().asItem());
    createMCCompost(ExNihiloItems.SEED_LARGE_FERN.get().asItem());
    createMCCompost(ExNihiloItems.GRASS_SEED.get().asItem());
    createMCCompost(ExNihiloItems.MYCELIUM_SPORE.get().asItem());
    //    createMCCompost(ExNihiloItems.CRIMSON_NYLIUM_SPORE.get().asItem());
    //    createMCCompost(ExNihiloItems.WARPED_NYLIUM_SPORE.get().asItem());
    createMCCompost(ExNihiloItems.SILKWORM.get());
    createMCCompost(ExNihiloItems.COOKED_SILKWORM.get());
  }

  private static void createMCCompost(Item item) {
    ComposterBlock.COMPOSTABLES.put(item, (float) 0.3);
  }

  private static <R extends Recipe<?>> List<R> filterRecipes(
      @Nonnull final Collection<Recipe<?>> recipes,
      @Nonnull final Class<R> recipeClass,
      @Nonnull final RecipeType<R> recipeType) {
    logger.debug("Filter Recipes, Class: " + recipeClass + ", Recipe Type: " + recipeType);
    return recipes.stream()
        .filter(iRecipe -> iRecipe.getType() == recipeType)
        .map(recipeClass::cast)
        .collect(Collectors.toList());
  }

  private static void loadRecipes(@Nonnull final RecipeManager manager) {
    logger.debug("Loading Recipes");
    @Nonnull final Collection<Recipe<?>> recipes = manager.getRecipes();
    if (recipes.isEmpty()) {
      return;
    }

    ExNihiloRegistries.HAMMER_REGISTRY.setRecipes(
        filterRecipes(recipes, HammerRecipe.class, ExNihiloRecipeTypes.HAMMER_RECIPE_TYPE.get()));
    ExNihiloRegistries.CROOK_REGISTRY.setRecipes(
        filterRecipes(recipes, CrookRecipe.class, ExNihiloRecipeTypes.CROOK_RECIPE_TYPE.get()));
    ExNihiloRegistries.COMPOST_REGISTRY.setRecipes(
        filterRecipes(recipes, CompostRecipe.class, ExNihiloRecipeTypes.COMPOST_RECIPE_TYPE.get()));
    ExNihiloRegistries.FLUID_BLOCK_REGISTRY.setRecipes(
        filterRecipes(recipes, FluidItemRecipe.class, ExNihiloRecipeTypes.FLUID_ITEM_RECIPE_TYPE.get()));
    ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.setRecipes(
        filterRecipes(recipes, FluidOnTopRecipe.class, ExNihiloRecipeTypes.FLUID_ON_TOP_RECIPE_TYPE.get()));
    ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.setRecipes(
        filterRecipes(recipes, FluidTransformRecipe.class, ExNihiloRecipeTypes.FLUID_TRANSFORM_RECIPE_TYPE.get()));
    ExNihiloRegistries.CRUCIBLE_REGISTRY.setRecipes(
        filterRecipes(recipes, CrucibleRecipe.class, ExNihiloRecipeTypes.CRUCIBLE_RECIPE_TYPE.get()));
    ExNihiloRegistries.HEAT_REGISTRY.setRecipes(
        filterRecipes(recipes, HeatRecipe.class, ExNihiloRecipeTypes.HEAT_RECIPE_TYPE.get()));
    ExNihiloRegistries.SIEVE_REGISTRY.setRecipes(
        filterRecipes(recipes, SieveRecipe.class, ExNihiloRecipeTypes.SIEVE_RECIPE_TYPE.get()));
  }

  private static void overrideOres() {
    if (Config.enableOreOverride()) {
      ExNihiloItems.COPPER.setEnabled(Config.enableCopper());
      ExNihiloItems.LEAD.setEnabled(Config.enableLead());
      ExNihiloItems.LEAD.setEnabled(Config.enableLead());
      ExNihiloItems.NICKEL.setEnabled(Config.enableNickel());
      ExNihiloItems.SILVER.setEnabled(Config.enableSilver());
      ExNihiloItems.TIN.setEnabled(Config.enableTin());
      ExNihiloItems.ALUMINUM.setEnabled(Config.enableAluminum());
      ExNihiloItems.PLATINUM.setEnabled(Config.enablePlatinum());
      ExNihiloItems.URANIUM.setEnabled(Config.enableUranium());
      ExNihiloItems.ZINC.setEnabled(Config.enableZinc());
      ExNihiloItems.IRON.setEnabled(Config.enableIron());
      ExNihiloItems.GOLD.setEnabled(Config.enableGold());
    }
  }

  private static void registerOreCompat() {
    logger.debug("Register ore compatibility");

    ExNihiloItems.IRON.setEnabled(true);
    ExNihiloItems.GOLD.setEnabled(true);

    logger.debug(
        "Immersive Engineering detected: "
            + ModList.get().isLoaded(ExNihiloConstants.ModIds.IMMERSIVE_ENGINEERING));
    if (ModList.get().isLoaded(ExNihiloConstants.ModIds.IMMERSIVE_ENGINEERING)) {
      logger.debug("Added Immersive Engineering");
      ExNihiloItems.ALUMINUM.setEnabled(true);
      ExNihiloItems.COPPER.setEnabled(true);
      ExNihiloItems.SILVER.setEnabled(true);
      ExNihiloItems.NICKEL.setEnabled(true);
      ExNihiloItems.LEAD.setEnabled(true);
      ExNihiloItems.URANIUM.setEnabled(true);
    }
    logger.debug("Create detected: " + ModList.get().isLoaded(ExNihiloConstants.ModIds.CREATE));
    if (ModList.get().isLoaded(ExNihiloConstants.ModIds.CREATE)) {
      logger.debug("Added Create");
      ExNihiloItems.COPPER.setEnabled(true);
      ExNihiloItems.ZINC.setEnabled(true);
    }
  }
}
