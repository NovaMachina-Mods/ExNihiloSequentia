package novamachina.exnihilosequentia.common.init;

import com.mojang.logging.LogUtils;
import javax.annotation.Nonnull;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class ExNihiloRecipeTypes {

  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
      DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, ModIds.EX_NIHILO_SEQUENTIA);
  public static final RegistryObject<RecipeType<CompostRecipe>> COMPOST_RECIPE_TYPE =
      RECIPE_TYPES.register("compost", () -> new RecipeType<CompostRecipe>() {
      });
  public static final RegistryObject<RecipeType<CrookRecipe>> CROOK_RECIPE_TYPE =
      RECIPE_TYPES.register("crook", () -> new RecipeType<CrookRecipe>() {
      });
  public static final RegistryObject<RecipeType<CrucibleRecipe>> CRUCIBLE_RECIPE_TYPE =
      RECIPE_TYPES.register("crucible", () -> new RecipeType<CrucibleRecipe>() {
      });
  public static final RegistryObject<RecipeType<FluidItemRecipe>> FLUID_ITEM_RECIPE_TYPE =
      RECIPE_TYPES.register("fluid_item", () -> new RecipeType<FluidItemRecipe>() {
      });
  public static final RegistryObject<RecipeType<FluidOnTopRecipe>> FLUID_ON_TOP_RECIPE_TYPE =
      RECIPE_TYPES.register("fluid_on_top", () -> new RecipeType<FluidOnTopRecipe>() {
      });
  public static final RegistryObject<RecipeType<FluidTransformRecipe>> FLUID_TRANSFORM_RECIPE_TYPE =
      RECIPE_TYPES.register("fluid_transform", () -> new RecipeType<FluidTransformRecipe>() {
      });
  public static final RegistryObject<RecipeType<HammerRecipe>> HAMMER_RECIPE_TYPE =
      RECIPE_TYPES.register("hammer", () -> new RecipeType<HammerRecipe>() {
      });
  public static final RegistryObject<RecipeType<HeatRecipe>> HEAT_RECIPE_TYPE =
      RECIPE_TYPES.register("heat", () -> new RecipeType<HeatRecipe>() {
      });
  public static final RegistryObject<RecipeType<SieveRecipe>> SIEVE_RECIPE_TYPE =
      RECIPE_TYPES.register("sieve", () -> new RecipeType<SieveRecipe>() {
      });

  private ExNihiloRecipeTypes() {
  }

  public static void init(IEventBus modEventBus) {
    RECIPE_TYPES.register(modEventBus);
  }
}
