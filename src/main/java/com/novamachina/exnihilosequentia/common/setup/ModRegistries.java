package com.novamachina.exnihilosequentia.common.setup;

import com.novamachina.exnihilosequentia.common.compat.ExNihilo;
import com.novamachina.exnihilosequentia.common.compat.IDefaultRegistry;
import com.novamachina.exnihilosequentia.common.compat.ThermalExpansion;
import com.novamachina.exnihilosequentia.common.item.tools.crook.CrookDrops;
import com.novamachina.exnihilosequentia.common.item.tools.hammer.HammerDrops;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.compost.CompostRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid.FluidBlockTransformRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.fluid.FluidOnTopRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.barrel.transform.FluidTransformRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleMeltableItems;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleMeltableItems;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.HeatRegistry;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleMeltableItems;
import com.novamachina.exnihilosequentia.common.tileentity.sieve.SieveDrops;

import java.util.ArrayList;
import java.util.List;

public class ModRegistries {
    public static final ModBus BUS = new ModBus();

    public static CrookDrops CROOK = new CrookDrops(BUS);
    public static CompostRegistry COMPOST = new CompostRegistry(BUS);
    public static HammerDrops HAMMER = new HammerDrops(BUS);
    public static SieveDrops SIEVE = new SieveDrops(BUS);
    public static HeatRegistry HEAT = new HeatRegistry(BUS);
    public static BaseCrucibleMeltableItems FIRED_CRUCIBLE = new FiredCrucibleMeltableItems(BUS);
    public static BaseCrucibleMeltableItems WOODEN_CRUCIBLE = new WoodCrucibleMeltableItems(BUS);
    public static FluidOnTopRegistry FLUID_ON_TOP = new FluidOnTopRegistry(BUS);
    public static FluidTransformRegistry FLUID_TRANSFORM = new FluidTransformRegistry(BUS);
    public static FluidBlockTransformRegistry FLUID_BLOCK = new FluidBlockTransformRegistry(BUS);

    public static class ModBus {
        private final List<AbstractModRegistry> registries;
        private final List<IDefaultRegistry> defaults;

        public List<IDefaultRegistry> getDefaults() {
            return defaults;
        }

        public ModBus() {
            this.registries = new ArrayList<>();
            this.defaults = new ArrayList<>();
            registerDefaults();
        }

        private void registerDefaults() {
            this.defaults.add(new ExNihilo());
            this.defaults.add(new ThermalExpansion());
        }

        public void register(AbstractModRegistry registry) {
            this.registries.add(registry);
        }

        public void clearRegistries() {
            registries.forEach(AbstractModRegistry::clear);
        }

        public void useJson() {
            useDefault();
            registries.forEach(AbstractModRegistry::useJson);
        }

        public void useDefault() {
            defaults.forEach(registry -> {
                registry.registerCrook(CROOK);
                registry.registerCompost(COMPOST);
                registry.registerHammer(HAMMER);
                registry.registerSieve(SIEVE);
                registry.registerHeat(HEAT);
                registry.registerFiredCrucible(FIRED_CRUCIBLE);
                registry.registerWoodCrucible(WOODEN_CRUCIBLE);
                registry.registerFluidOnTop(FLUID_ON_TOP);
                registry.registerFluidTransform(FLUID_TRANSFORM);
                registry.registerFluidBlock(FLUID_BLOCK);
            });
        }
    }
}
