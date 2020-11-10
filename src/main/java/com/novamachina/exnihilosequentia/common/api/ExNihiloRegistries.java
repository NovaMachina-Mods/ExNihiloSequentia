package com.novamachina.exnihilosequentia.common.api;

import com.novamachina.exnihilosequentia.common.registries.barrel.compost.CompostRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.fluid.FluidBlockTransformRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.fluid.FluidOnTopRegistry;
import com.novamachina.exnihilosequentia.common.registries.barrel.transform.FluidTransformRegistry;
import com.novamachina.exnihilosequentia.common.registries.crook.CrookRegistry;
import com.novamachina.exnihilosequentia.common.registries.crucible.CrucibleRegistry;
import com.novamachina.exnihilosequentia.common.registries.crucible.HeatRegistry;
import com.novamachina.exnihilosequentia.common.registries.defaults.Create;
import com.novamachina.exnihilosequentia.common.registries.defaults.ExNihilo;
import com.novamachina.exnihilosequentia.common.api.compat.ore.IOreCompat;
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
    public static SieveRegistry SIEVE_REGISTRY = new SieveRegistry();
    public static HeatRegistry HEAT_REGISTRY = new HeatRegistry();
    public static CrucibleRegistry CRUCIBLE_REGISTRY = new CrucibleRegistry();
    public static FluidOnTopRegistry FLUID_ON_TOP_REGISTRY = new FluidOnTopRegistry();
    public static FluidTransformRegistry FLUID_TRANSFORM_REGISTRY = new FluidTransformRegistry();
    public static FluidBlockTransformRegistry FLUID_BLOCK_REGISTRY = new FluidBlockTransformRegistry();

    public static class ModBus {
        private final List<IOreCompat> oreCompats;

        public ModBus() {
            this.oreCompats = new ArrayList<>();
            registerOreCompat();
        }

        public List<IOreCompat> getOreCompats() {
            return oreCompats;
        }

        private void registerOreCompat() {
            this.oreCompats.add(new ExNihilo());
            if (ModList.get().isLoaded(Constants.ModIds.THERMAL_EXPANSION) || Config.ENABLE_THERMAL.get()) {
                this.oreCompats.add(new ThermalExpansion());
            }
            if (ModList.get().isLoaded(Constants.ModIds.IMMERSIVE_ENGINEERING) || Config.ENABLE_IMMERSIVE.get()) {
                this.oreCompats.add(new ImmersiveEngineering());
            }
            if (ModList.get().isLoaded(Constants.ModIds.MEKANISM) || Config.ENABLE_MEKANISM.get()) {
                this.oreCompats.add(new Mekanism());
            }
            if (ModList.get().isLoaded(Constants.ModIds.CREATE) || Config.ENABLE_CREATE.get()) {
                this.oreCompats.add(new Create());
            }
        }

        public void activateOreCompat() {
            oreCompats.forEach(IOreCompat::activateOres);
        }
    }
}
