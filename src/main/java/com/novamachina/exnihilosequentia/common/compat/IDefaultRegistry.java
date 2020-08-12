package com.novamachina.exnihilosequentia.common.compat;

import com.novamachina.exnihilosequentia.common.item.tools.crook.CrookDrops;
import com.novamachina.exnihilosequentia.common.item.tools.hammer.HammerDrops;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.compost.CompostRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid.FluidBlockTransformRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid.FluidOnTopRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.transform.FluidTransformRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleMeltableItems;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.HeatRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.sieve.SieveDrops;

public interface IDefaultRegistry {
    default void registerCrook(CrookDrops registry) {}
    default void registerHammer(HammerDrops registry) {}
    default void registerCompost(CompostRegistry registry) {}
    default void registerFluidBlock(FluidBlockTransformRegistry registry) {}
    default void registerFluidOnTop(FluidOnTopRegistry registry) {}
    default void registerFluidTransform(FluidTransformRegistry registry) {}
    default void registerFiredCrucible(BaseCrucibleMeltableItems registry) {}
    default void registerWoodCrucible(BaseCrucibleMeltableItems registry) {}
    default void registerHeat(HeatRegistry registry) {}
    default void registerSieve(SieveDrops registry) {}
}
