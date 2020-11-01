package com.novamachina.exnihilosequentia.common.api;

import com.novamachina.exnihilosequentia.common.registries.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.compost.CompostRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.fluid.FluidBlockTransformRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.fluid.FluidOnTopRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.transform.FluidTransformRegistry;
import com.novamachina.exnihilosequentia.common.registries.crook.CrookRegistry;
import com.novamachina.exnihilosequentia.common.registries.crucible.CrucibleRegistry;
import com.novamachina.exnihilosequentia.common.registries.crucible.HeatRegistry;
import com.novamachina.exnihilosequentia.common.registries.defaults.Create;
import com.novamachina.exnihilosequentia.common.registries.defaults.ExNihilo;
import com.novamachina.exnihilosequentia.common.registries.defaults.IDefaultRegistry;
import com.novamachina.exnihilosequentia.common.registries.defaults.ImmersiveEngineering;
import com.novamachina.exnihilosequentia.common.registries.defaults.Mekanism;
import com.novamachina.exnihilosequentia.common.registries.defaults.ThermalExpansion;
import com.novamachina.exnihilosequentia.common.registries.hammer.HammerRegistry;
import com.novamachina.exnihilosequentia.common.registries.sieve.SieveRegistry;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.List;

public class ExNihiloRegistries {
    public static final ModBus BUS = new ModBus();

    public static CrookRegistry CROOK_REGISTRY = new CrookRegistry();
    public static CompostRegistry COMPOST_REGISTRY = new CompostRegistry();
    public static HammerRegistry HAMMER_REGISTRY = new HammerRegistry();
    public static SieveRegistry SIEVE_REGISTRY = new SieveRegistry(BUS);
    public static HeatRegistry HEAT_REGISTRY = new HeatRegistry(BUS);
    public static CrucibleRegistry CRUCIBLE_REGISTRY = new CrucibleRegistry(BUS);
    public static FluidOnTopRegistry FLUID_ON_TOP_REGISTRY = new FluidOnTopRegistry(BUS);
    public static FluidTransformRegistry FLUID_TRANSFORM_REGISTRY = new FluidTransformRegistry(BUS);
    public static FluidBlockTransformRegistry FLUID_BLOCK_REGISTRY = new FluidBlockTransformRegistry();

    public static class ModBus {
        private final List<AbstractModRegistry> registries;
        private final List<IDefaultRegistry> defaults;

        public ModBus() {
            this.registries = new ArrayList<>();
            this.defaults = new ArrayList<>();
            registerDefaults();
        }

        public List<IDefaultRegistry> getDefaults() {
            return defaults;
        }

        private void registerDefaults() {
            this.defaults.add(new ExNihilo());
            if (ModList.get().isLoaded(Constants.ModIds.THERMAL_EXPANSION) || Config.ENABLE_THERMAL.get()) {
                this.defaults.add(new ThermalExpansion());
            }
            if (ModList.get().isLoaded(Constants.ModIds.IMMERSIVE_ENGINEERING) || Config.ENABLE_IMMERSIVE.get()) {
                this.defaults.add(new ImmersiveEngineering());
            }
            if (ModList.get().isLoaded(Constants.ModIds.MEKANISM) || Config.ENABLE_MEKANISM.get()) {
                this.defaults.add(new Mekanism());
            }
            if (ModList.get().isLoaded(Constants.ModIds.CREATE) || Config.ENABLE_CREATE.get()) {
                this.defaults.add(new Create());
            }
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
                registry.registerCrook(CROOK_REGISTRY);
                registry.registerCompost(COMPOST_REGISTRY);
                registry.registerHammer(HAMMER_REGISTRY);
                registry.registerSieve(SIEVE_REGISTRY);
                registry.registerHeat(HEAT_REGISTRY);
                registry.registerFiredCrucible(CRUCIBLE_REGISTRY);
                registry.registerWoodCrucible(CRUCIBLE_REGISTRY);
                registry.registerFluidOnTop(FLUID_ON_TOP_REGISTRY);
                registry.registerFluidTransform(FLUID_TRANSFORM_REGISTRY);
                registry.registerFluidBlock(FLUID_BLOCK_REGISTRY);
            });
        }
    }
}
