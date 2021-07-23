package novamachina.exnihilosequentia.common.init;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.crafting.serializer.CompostRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.CrookRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.CrucibleRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.FluidItemRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.FluidOnTopRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.FluidTransformRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.HammerRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.HeatRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.SieveRecipeSerializer;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    static {
        HammerRecipe.setSerializer(RECIPE_SERIALIZERS.register("hammer", HammerRecipeSerializer::new));
        CrookRecipe.setSerializer(RECIPE_SERIALIZERS.register("crook", CrookRecipeSerializer::new));
        CompostRecipe.setSerializer(RECIPE_SERIALIZERS.register("compost", CompostRecipeSerializer::new));
        FluidItemRecipe.setSerializer(RECIPE_SERIALIZERS.register("fluid_item", FluidItemRecipeSerializer::new));
        FluidOnTopRecipe.setSerializer(RECIPE_SERIALIZERS.register("fluid_on_top", FluidOnTopRecipeSerializer::new));
        FluidTransformRecipe.setSerializer(RECIPE_SERIALIZERS.register("fluid_transform", FluidTransformRecipeSerializer::new));
        CrucibleRecipe.setSerializer(RECIPE_SERIALIZERS.register("crucible", CrucibleRecipeSerializer::new));
        HeatRecipe.setSerializer(RECIPE_SERIALIZERS.register("heat", HeatRecipeSerializer::new));
        SieveRecipe.setSerializer(RECIPE_SERIALIZERS.register("sieve", SieveRecipeSerializer::new));
    }

    private ExNihiloSerializers() {
    }

    public static void init(IEventBus modEventBus) {
        logger.debug("Register recipe serializers");
        RECIPE_SERIALIZERS.register(modEventBus);
    }
}
