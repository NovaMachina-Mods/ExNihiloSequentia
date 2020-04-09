package com.novamachina.ens.common.setup;

import com.novamachina.ens.common.utility.Constants;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

    // Registries
    private static final DeferredRegister<Block> BLOCKS =
        new DeferredRegister<>(ForgeRegistries.BLOCKS, Constants.ModInfo.MOD_ID);
    private static final DeferredRegister<Item> ITEMS =
        new DeferredRegister<>(ForgeRegistries.ITEMS, Constants.ModInfo.MOD_ID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS =
        new DeferredRegister<>(ForgeRegistries.CONTAINERS, Constants.ModInfo.MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILES =
        new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Constants.ModInfo.MOD_ID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
