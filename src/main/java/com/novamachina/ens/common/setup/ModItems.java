package com.novamachina.ens.common.setup;

import com.novamachina.ens.common.item.CookedSilkwormItem;
import com.novamachina.ens.common.item.EnumPebbleType;
import com.novamachina.ens.common.item.PebbleItem;
import com.novamachina.ens.common.item.ResourceItem;
import com.novamachina.ens.common.item.SeedBaseItem;
import com.novamachina.ens.common.item.ore.Ore;
import com.novamachina.ens.common.item.ore.OreItem;
import com.novamachina.ens.common.item.tools.crook.CrookBaseItem;
import com.novamachina.ens.common.item.tools.crook.EnumCrook;
import com.novamachina.ens.common.item.tools.hammer.EnumHammer;
import com.novamachina.ens.common.item.tools.hammer.HammerBaseItem;
import com.novamachina.ens.common.registry.MasterRegistry;
import com.novamachina.ens.common.registry.OreRegistry;
import com.novamachina.ens.common.registry.registryitem.SeedRegistryItem;
import com.novamachina.ens.common.utility.Constants;
import com.novamachina.ens.common.utility.Constants.Registry;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(
        ForgeRegistries.ITEMS, Constants.ModInfo.MOD_ID);

    public static Map<String, RegistryObject<OreItem>> chunkMap    = new HashMap<>();
    public static Map<String, RegistryObject<OreItem>> pieceMap    = new HashMap<>();
    public static Map<String, RegistryObject<Item>>    resourceMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>>    pebbleMap   = new HashMap<>();

    public static final RegistryObject<Item> ITEM_DUST               = ITEMS
        .register(Constants.Blocks.DUST, () -> new BlockItem(ModBlocks.BLOCK_DUST.get(),
            new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> ITEM_CRUSHED_NETHERRACK = ITEMS
        .register(Constants.Blocks.CRUSHED_NETHERRACK,
            () -> new BlockItem(ModBlocks.BLOCK_CRUSHED_NETHERRACK.get(),
                new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> ITEM_CRUSHED_END_STONE  = ITEMS
        .register(Constants.Blocks.CRUSHED_END_STONE,
            () -> new BlockItem(ModBlocks.BLOCK_CRUSHED_END_STONE.get(),
                new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> ITEM_CRUSHED_ANDESITE   = ITEMS
        .register(Constants.Blocks.CRUSHED_ANDESITE,
            () -> new BlockItem(ModBlocks.BLOCK_CRUSHED_ANDESITE.get(),
                new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> ITEM_CRUSHED_DIORITE    = ITEMS
        .register(Constants.Blocks.CRUSHED_DIORITE,
            () -> new BlockItem(ModBlocks.BLOCK_CRUSHED_DIORITE.get(),
                new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> ITEM_CRUSHED_GRANITE    = ITEMS
        .register(Constants.Blocks.CRUSHED_GRANITE,
            () -> new BlockItem(ModBlocks.BLOCK_CRUSHED_GRANITE.get(),
                new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> ITEM_END_CAKE           = ITEMS
        .register(Constants.Blocks.END_CAKE, () -> new BlockItem(ModBlocks.BLOCK_END_CAKE.get(),
            new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> ITEM_COOKED_SILKWORM    = ITEMS
        .register(Constants.Items.COOKED_SILKWORM, CookedSilkwormItem::new);

    static {
        for (EnumCrook crook : EnumCrook.values()) {
            ITEMS
                .register(crook.name, () -> new CrookBaseItem(crook.teir, crook.defaultDurability));
        }

        for (EnumHammer hammer : EnumHammer.values()) {
            ITEMS.register(hammer.name,
                () -> new HammerBaseItem(hammer.teir, hammer.defaultDurability));
        }

        for (Ore ore : ((OreRegistry) MasterRegistry.getInstance()
            .getRegistry(Registry.ORE_REGISTRY)).getValues()) {
            chunkMap.put(ore.getName(), ITEMS.register(ore.getChunkName(), () -> new OreItem(ore)));
            pieceMap.put(ore.getName(), ITEMS.register(ore.getPieceName(), () -> new OreItem(ore)));
        }

        for (String key : MasterRegistry.getInstance().getRegistry(Registry.SEED_REGISTRY)
            .getKeys()) {
            SeedRegistryItem registryItem = (SeedRegistryItem) MasterRegistry.getInstance()
                .getRegistry(Registry.SEED_REGISTRY).getValue(key);
            ITEMS.register("item_" + key + "_seed",
                () -> new SeedBaseItem(registryItem.getBlockState())
                    .setPlantType(registryItem.getPlantType()));
        }

        for (String key : MasterRegistry.getInstance().getRegistry(Registry.RESOURCE_REGISTRY)
            .getKeys()) {
            RegistryObject<Item> item = ITEMS.register(key, () -> new ResourceItem(key));
            resourceMap.put(key, item);
        }

        for (EnumPebbleType type : EnumPebbleType.values()) {
            pebbleMap
                .put(type.getType(), ITEMS.register(type.getType(), () -> new PebbleItem(type)));
        }
    }

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
