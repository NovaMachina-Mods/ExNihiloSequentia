package novamachina.exnihilosequentia.common.init;

import com.mojang.logging.LogUtils;
import javax.annotation.Nonnull;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.crafting.serializer.CompostRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.CrookRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.CrucibleRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.FluidItemRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.FluidOnTopRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.FluidTransformRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.HammerRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.HeatRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.SieveRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class ExNihiloSerializers {

  @Nonnull
  public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
      DeferredRegister.create(
          ForgeRegistries.RECIPE_SERIALIZERS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
  public static final RegistryObject<ExNihiloRecipeSerializer<HammerRecipe>>
      HAMMER_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("hammer", HammerRecipeSerializer::new);
  public static final RegistryObject<ExNihiloRecipeSerializer<CrookRecipe>>
      CROOK_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("crook", CrookRecipeSerializer::new);
  public static final RegistryObject<ExNihiloRecipeSerializer<CompostRecipe>>
      COMPOST_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("compost", CompostRecipeSerializer::new);
  public static final RegistryObject<ExNihiloRecipeSerializer<FluidItemRecipe>>
      FLUID_ITEM_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("fluid_item", FluidItemRecipeSerializer::new);
  public static final RegistryObject<ExNihiloRecipeSerializer<FluidOnTopRecipe>>
      FLUID_ON_TOP_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("fluid_on_top", FluidOnTopRecipeSerializer::new);
  public static final RegistryObject<ExNihiloRecipeSerializer<FluidTransformRecipe>>
      FLUID_TRANSFORM_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("fluid_transform", FluidTransformRecipeSerializer::new);
  public static final RegistryObject<ExNihiloRecipeSerializer<CrucibleRecipe>>
      CRUCIBLE_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("crucible", CrucibleRecipeSerializer::new);
  public static final RegistryObject<ExNihiloRecipeSerializer<HeatRecipe>> HEAT_RECIPE_SERIALIZER =
      RECIPE_SERIALIZERS.register("heat", HeatRecipeSerializer::new);
  public static final RegistryObject<ExNihiloRecipeSerializer<SieveRecipe>>
      SIEVE_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("sieve", SieveRecipeSerializer::new);
  @Nonnull
  private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  private ExNihiloSerializers() {
  }

  public static void init(IEventBus modEventBus) {
    logger.debug("Register recipe serializers");
    RECIPE_SERIALIZERS.register(modEventBus);
  }
}
