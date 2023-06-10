package novamachina.exnihilosequentia.init;

import com.mojang.logging.LogUtils;
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
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import novamachina.exnihilosequentia.common.blockentity.barrel.mode.BarrelModeRegistry;
import novamachina.exnihilosequentia.common.compat.top.CompatTOP;
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
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.ItemDefinition;

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
          return EXNBlocks.OAK_SIEVE.itemStack();
        }
      };

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  private ExNihiloInitialization() {}

  // MinecraftForge.EVENT_BUS
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void clearRegistries(@Nonnull final ClientPlayerNetworkEvent.LoggingOut event) {
    logger.debug("Fired LoggedOutEvent");
    ExNihiloRegistries.clearRegistries();
  }

  public static void init(@Nonnull final IEventBus modEventBus) {
    logger.debug("Initializing modded items");
    ExNihiloLootModifiers.getRegistry().addToBus(modEventBus);
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
      CompatTOP.register();
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
//    EXNStats.register();
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
    DispenserBlock.registerBehavior(EXNItems.SEA_WATER_BUCKET, idispenseitembehavior);
    DispenserBlock.registerBehavior(EXNItems.WITCH_WATER_BUCKET, idispenseitembehavior);
  }

  private static void registerVanillaCompost() {
    //    createMCCompost(ExNihiloItems.SEED_OAK.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_SPRUCE.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_BIRCH.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_JUNGLE.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_ACACIA.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_DARK_OAK.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_CACTUS.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_SUGARCANE.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_CARROT.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_POTATO.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_SWEET_BERRY.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_KELP.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_PICKLE.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_BAMBOO.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_FERN.get().asItem());
    //    createMCCompost(ExNihiloItems.SEED_LARGE_FERN.get().asItem());
    createMCCompost(EXNItems.GRASS_SEED);
    createMCCompost(EXNItems.MYCELIUM_SPORE);
    //    createMCCompost(ExNihiloItems.CRIMSON_NYLIUM_SPORE.get().asItem());
    //    createMCCompost(ExNihiloItems.WARPED_NYLIUM_SPORE.get().asItem());
    createMCCompost(EXNItems.SILKWORM);
    createMCCompost(EXNItems.COOKED_SILKWORM);
  }

  private static void createMCCompost(ItemDefinition<? extends Item> item) {
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
        filterRecipes(recipes, HammerRecipe.class, EXNRecipeTypes.HAMMER_RECIPE_TYPE));
    ExNihiloRegistries.CROOK_REGISTRY.setRecipes(
        filterRecipes(recipes, CrookRecipe.class, EXNRecipeTypes.CROOK_RECIPE_TYPE));
    ExNihiloRegistries.COMPOST_REGISTRY.setRecipes(
        filterRecipes(recipes, CompostRecipe.class, EXNRecipeTypes.COMPOST_RECIPE_TYPE));
    ExNihiloRegistries.FLUID_BLOCK_REGISTRY.setRecipes(
        filterRecipes(
            recipes, FluidItemRecipe.class, EXNRecipeTypes.FLUID_ITEM_RECIPE_TYPE));
    ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.setRecipes(
        filterRecipes(
            recipes, FluidOnTopRecipe.class, EXNRecipeTypes.FLUID_ON_TOP_RECIPE_TYPE));
    ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.setRecipes(
        filterRecipes(
            recipes,
            FluidTransformRecipe.class,
            EXNRecipeTypes.FLUID_TRANSFORM_RECIPE_TYPE));
    ExNihiloRegistries.CRUCIBLE_REGISTRY.setRecipes(
        filterRecipes(
            recipes, CrucibleRecipe.class, EXNRecipeTypes.CRUCIBLE_RECIPE_TYPE));
    ExNihiloRegistries.HEAT_REGISTRY.setRecipes(
        filterRecipes(recipes, HeatRecipe.class, EXNRecipeTypes.HEAT_RECIPE_TYPE));
    ExNihiloRegistries.SIEVE_REGISTRY.setRecipes(
        filterRecipes(recipes, SieveRecipe.class, EXNRecipeTypes.SIEVE_RECIPE_TYPE));
  }

  private static void overrideOres() {
    if (Config.enableOreOverride()) {
      EXNItems.COPPER.setEnabled(Config.enableCopper());
      EXNItems.LEAD.setEnabled(Config.enableLead());
      EXNItems.LEAD.setEnabled(Config.enableLead());
      EXNItems.NICKEL.setEnabled(Config.enableNickel());
      EXNItems.SILVER.setEnabled(Config.enableSilver());
      EXNItems.TIN.setEnabled(Config.enableTin());
      EXNItems.ALUMINUM.setEnabled(Config.enableAluminum());
      EXNItems.PLATINUM.setEnabled(Config.enablePlatinum());
      EXNItems.URANIUM.setEnabled(Config.enableUranium());
      EXNItems.ZINC.setEnabled(Config.enableZinc());
      EXNItems.IRON.setEnabled(Config.enableIron());
      EXNItems.GOLD.setEnabled(Config.enableGold());
    }
  }

  private static void registerOreCompat() {
    logger.debug("Register ore compatibility");

    EXNItems.IRON.setEnabled(true);
    EXNItems.GOLD.setEnabled(true);

    logger.debug(
        "Immersive Engineering detected: "
            + ModList.get().isLoaded(ExNihiloConstants.ModIds.IMMERSIVE_ENGINEERING));
    if (ModList.get().isLoaded(ExNihiloConstants.ModIds.IMMERSIVE_ENGINEERING)) {
      logger.debug("Added Immersive Engineering");
      EXNItems.ALUMINUM.setEnabled(true);
      EXNItems.COPPER.setEnabled(true);
      EXNItems.SILVER.setEnabled(true);
      EXNItems.NICKEL.setEnabled(true);
      EXNItems.LEAD.setEnabled(true);
      EXNItems.URANIUM.setEnabled(true);
    }
    logger.debug("Create detected: " + ModList.get().isLoaded(ExNihiloConstants.ModIds.CREATE));
    if (ModList.get().isLoaded(ExNihiloConstants.ModIds.CREATE)) {
      logger.debug("Added Create");
      EXNItems.COPPER.setEnabled(true);
      EXNItems.ZINC.setEnabled(true);
    }
  }
}
