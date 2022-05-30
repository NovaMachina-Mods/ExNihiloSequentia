package novamachina.exnihilosequentia.common.init;

import javax.annotation.Nonnull;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import novamachina.exnihilosequentia.common.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.common.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExNihiloRecipeTypes {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  private ExNihiloRecipeTypes() {}

  @SubscribeEvent
  public static void registerRecipeType(RegistryEvent.Register<Block> event) {
    logger.debug("Registering RecipeTypes");
    CompostRecipe.RECIPE_TYPE =
        RecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":compost");
    CrookRecipe.RECIPE_TYPE =
        RecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":crook");
    CrucibleRecipe.RECIPE_TYPE =
        RecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":crucible");
    FluidItemRecipe.RECIPE_TYPE =
        RecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_item");
    FluidOnTopRecipe.RECIPE_TYPE =
        RecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_on_top");
    FluidTransformRecipe.RECIPE_TYPE =
        RecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":fluid_transform");
    HammerRecipe.RECIPE_TYPE =
        RecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":hammer");
    HeatRecipe.RECIPE_TYPE =
        RecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":heat");
    SieveRecipe.RECIPE_TYPE =
        RecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":sieve");
  }
}
