package novamachina.exnihilosequentia.common.init;

import novamachina.exnihilosequentia.common.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.api.crafting.fluidItem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.api.crafting.fluidontop.FluidOnTopRecipe;
import novamachina.exnihilosequentia.common.api.crafting.fluidtransform.FluidTransformRecipe;
import novamachina.exnihilosequentia.common.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.crafting.serializer.CompostRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.CrookRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.CrucibleRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.FluidItemRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.FluidOnTopRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.FluidTransformRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.HammerRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.HeatRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.serializer.SieveRecipeSerializer;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSerializers {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Constants.ModIds.EX_NIHILO_SEQUENTIA);

    static {
        HammerRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("hammer", HammerRecipeSerializer::new);
        CrookRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("crook", CrookRecipeSerializer::new);
        CompostRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("compost", CompostRecipeSerializer::new);
        FluidItemRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("fluid_item", FluidItemRecipeSerializer::new);
        FluidOnTopRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("fluid_on_top", FluidOnTopRecipeSerializer::new);
        FluidTransformRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("fluid_transform", FluidTransformRecipeSerializer::new);
        CrucibleRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("crucible", CrucibleRecipeSerializer::new);
        HeatRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("heat", HeatRecipeSerializer::new);
        SieveRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("sieve", SieveRecipeSerializer::new);
    }

    public static void init(IEventBus modEventBus) {
        RECIPE_SERIALIZERS.register(modEventBus);
    }
}
