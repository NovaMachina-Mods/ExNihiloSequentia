package com.novamachina.exnihilosequentia.common.init;

import com.novamachina.exnihilosequentia.common.api.crafting.HammerRecipe;
import com.novamachina.exnihilosequentia.common.crafting.serializer.HammerRecipeSerializer;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSerializers {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Constants.ModIds.EX_NIHILO_SEQUENTIA);

    static {
        HammerRecipe.SERIALIZER = RECIPE_SERIALIZERS.register("hammer", HammerRecipeSerializer::new);
    }

    public static void init(IEventBus modEventBus) {
        RECIPE_SERIALIZERS.register(modEventBus);
    }
}
