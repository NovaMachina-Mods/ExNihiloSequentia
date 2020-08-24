package com.novamachina.exnihilosequentia.common.registries.defaults;

import com.novamachina.exnihilosequentia.common.registries.barrel.compost.CompostRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.fluid.FluidBlockTransformRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.fluid.FluidOnTopRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.transform.FluidTransformRegistry;
import com.novamachina.exnihilosequentia.common.registries.crook.CrookRegistry;
import com.novamachina.exnihilosequentia.common.registries.crucible.CrucibleRegistry;
import com.novamachina.exnihilosequentia.common.registries.crucible.HeatRegistry;
import com.novamachina.exnihilosequentia.common.registries.hammer.HammerRegistry;
import com.novamachina.exnihilosequentia.common.registries.sieve.SieveRegistry;

public interface IDefaultRegistry {
    default void registerCrook(CrookRegistry registry) {
    }

    default void registerHammer(HammerRegistry registry) {
    }

    default void registerCompost(CompostRegistry registry) {
    }

    default void registerFluidBlock(FluidBlockTransformRegistry registry) {
    }

    default void registerFluidOnTop(FluidOnTopRegistry registry) {
    }

    default void registerFluidTransform(FluidTransformRegistry registry) {
    }

    default void registerFiredCrucible(CrucibleRegistry registry) {
    }

    default void registerWoodCrucible(CrucibleRegistry registry) {
    }

    default void registerHeat(HeatRegistry registry) {
    }

    default void registerSieve(SieveRegistry registry) {
    }
}
