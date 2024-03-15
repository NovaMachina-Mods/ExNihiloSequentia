package novamachina.exnihilosequentia.events;

import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RecipesUpdatedEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import novamachina.exnihilosequentia.ExNihiloSequentia;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.crafting.CompostRecipe;
import novamachina.exnihilosequentia.world.item.crafting.CrushingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.item.crafting.HarvestRecipe;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.SolidifyingRecipe;
import novamachina.exnihilosequentia.world.item.crafting.TransitionRecipe;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(
    modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
    bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NeoForgeEventListeners {

  private NeoForgeEventListeners() {}

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(NeoForgeEventListeners.class);

  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void clearRegistries(@Nonnull final ClientPlayerNetworkEvent.LoggingOut event) {
    log.debug("Fired LoggedOutEvent");
    ExNihiloRegistries.clearRegistries();
  }

  @SubscribeEvent
  public static void loadClientRecipes(@Nonnull final RecipesUpdatedEvent event) {
    ExNihiloRegistries.clearRegistries();
    loadRecipes(event.getRecipeManager());
  }

  @SubscribeEvent
  public static void onPlayerLogin(@Nonnull final PlayerEvent.PlayerLoggedInEvent event) {
    log.debug("Fired PlayerLoggedInEvent");
    if (!ExNihiloSequentia.isRelease()) {
      MutableComponent text = Component.translatable(ExNihiloSequentia.MOD_ID + ".open_beta_text");
      MutableComponent link = Component.translatable(ExNihiloSequentia.MOD_ID + ".issue_collector");
      Style linkStyle =
          Style.EMPTY
              .withColor(ChatFormatting.BLUE)
              .withUnderlined(true)
              .withClickEvent(
                  new ClickEvent(
                      ClickEvent.Action.OPEN_URL,
                      "https://github.com/NovaMachina-Mods/ExNihiloSequentia/issues"));
      link.withStyle(linkStyle);
      text.append(link);
      event.getEntity().sendSystemMessage(text);
    }
  }

  @SubscribeEvent
  public static void onServerStart(@Nonnull final ServerStartingEvent event) {
    log.debug("Fired FMLServerStartingEvent");
    registerOreCompat();
    overrideOres();
    if (event.getServer().isDedicatedServer()) {
      loadRecipes(event.getServer().getRecipeManager());
    }
  }

  private static <R extends Recipe<?>> List<R> filterRecipes(
      @Nonnull final Collection<RecipeHolder<?>> recipes,
      @Nonnull final Class<R> recipeClass,
      @Nonnull final RecipeType<R> recipeType) {
    log.debug("Filter Recipes, Class: {}, Recipe Type: {}", recipeClass, recipeType);
    return recipes.stream()
        .filter(iRecipe -> iRecipe.value().getType() == recipeType)
        .map(RecipeHolder::value)
        .map(recipeClass::cast)
        .toList();
  }

  private static void loadRecipes(@Nonnull final RecipeManager manager) {
    log.debug("Loading Recipes");
    @Nonnull final Collection<RecipeHolder<?>> recipes = manager.getRecipes();
    if (recipes.isEmpty()) {
      return;
    }

    ExNihiloRegistries.HAMMER_REGISTRY.setRecipes(
        filterRecipes(recipes, CrushingRecipe.class, EXNRecipeTypes.CRUSHING));
    ExNihiloRegistries.CROOK_REGISTRY.setRecipes(
        filterRecipes(recipes, HarvestRecipe.class, EXNRecipeTypes.HARVEST));
    ExNihiloRegistries.COMPOST_REGISTRY.setRecipes(
        filterRecipes(recipes, CompostRecipe.class, EXNRecipeTypes.COMPOST));
    ExNihiloRegistries.FLUID_BLOCK_REGISTRY.setRecipes(
        filterRecipes(recipes, PrecipitateRecipe.class, EXNRecipeTypes.PRECIPITATE));
    ExNihiloRegistries.FLUID_ON_TOP_REGISTRY.setRecipes(
        filterRecipes(recipes, SolidifyingRecipe.class, EXNRecipeTypes.SOLIDIFYING));
    ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY.setRecipes(
        filterRecipes(recipes, TransitionRecipe.class, EXNRecipeTypes.TRANSITION));
    ExNihiloRegistries.CRUCIBLE_REGISTRY.setRecipes(
        filterRecipes(recipes, MeltingRecipe.class, EXNRecipeTypes.MELTING));
    ExNihiloRegistries.HEAT_REGISTRY.setRecipes(
        filterRecipes(recipes, HeatRecipe.class, EXNRecipeTypes.HEAT));
    ExNihiloRegistries.SIEVE_REGISTRY.setRecipes(
        filterRecipes(recipes, SiftingRecipe.class, EXNRecipeTypes.SIFTING));
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
    log.debug("Register ore compatibility");

    EXNItems.IRON.setEnabled(true);
    EXNItems.GOLD.setEnabled(true);

    log.debug(
        "Immersive Engineering detected: {}",
        ModList.get().isLoaded(ExNihiloConstants.ModIds.IMMERSIVE_ENGINEERING));
    if (ModList.get().isLoaded(ExNihiloConstants.ModIds.IMMERSIVE_ENGINEERING)) {
      log.debug("Added Immersive Engineering");
      EXNItems.ALUMINUM.setEnabled(true);
      EXNItems.COPPER.setEnabled(true);
      EXNItems.SILVER.setEnabled(true);
      EXNItems.NICKEL.setEnabled(true);
      EXNItems.LEAD.setEnabled(true);
      EXNItems.URANIUM.setEnabled(true);
    }
    log.debug("Create detected: {}", ModList.get().isLoaded(ExNihiloConstants.ModIds.CREATE));
    if (ModList.get().isLoaded(ExNihiloConstants.ModIds.CREATE)) {
      log.debug("Added Create");
      EXNItems.COPPER.setEnabled(true);
      EXNItems.ZINC.setEnabled(true);
    }
  }
}
