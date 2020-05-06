package com.novamachina.ens.common.setup;

import com.novamachina.ens.common.item.CookedSilkwormItem;
import com.novamachina.ens.common.item.mesh.EnumMesh;
import com.novamachina.ens.common.item.mesh.MeshItem;
import com.novamachina.ens.common.item.ore.EnumOre;
import com.novamachina.ens.common.item.ore.OreItem;
import com.novamachina.ens.common.item.pebbles.EnumPebbleType;
import com.novamachina.ens.common.item.pebbles.PebbleItem;
import com.novamachina.ens.common.item.resources.EnumResource;
import com.novamachina.ens.common.item.resources.ResourceItem;
import com.novamachina.ens.common.item.seeds.EnumSeed;
import com.novamachina.ens.common.item.seeds.SeedBaseItem;
import com.novamachina.ens.common.item.tools.crook.CrookBaseItem;
import com.novamachina.ens.common.item.tools.crook.EnumCrook;
import com.novamachina.ens.common.item.tools.hammer.EnumHammer;
import com.novamachina.ens.common.item.tools.hammer.HammerBaseItem;
import com.novamachina.ens.common.utility.Constants;
import com.novamachina.ens.common.utility.Constants.Blocks;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
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
    public static final RegistryObject<Item> ITEM_SIEVE              = ITEMS.register(Blocks.SIEVE,
        () -> new BlockItem(ModBlocks.BLOCK_SIEVE.get(),
            new Properties().group(ModInitialization.ITEM_GROUP)));
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

        for (EnumOre ore : EnumOre.values()) {
            chunkMap.put(ore.getName(), ITEMS.register(ore.getChunkName(), () -> new OreItem(ore)));
            pieceMap.put(ore.getName(), ITEMS.register(ore.getPieceName(), () -> new OreItem(ore)));
        }

        for (EnumSeed seed : EnumSeed.values()) {
            ITEMS.register(seed.getSeedName(),
                () -> new SeedBaseItem(seed.getDefaultState()).setPlantType(seed.getPlantType()));
        }

        for (EnumResource resource : EnumResource.values()) {
            RegistryObject<Item> item = ITEMS.register(resource.getResourceName(),
                () -> new ResourceItem(resource.getResourceName()));
            resourceMap.put(resource.getResourceName(), item);
        }

        for (EnumPebbleType type : EnumPebbleType.values()) {
            pebbleMap
                .put(type.getType(), ITEMS.register(type.getType(), () -> new PebbleItem(type)));
        }

        for (EnumMesh mesh : EnumMesh.values()) {
            ITEMS.register(mesh.getMeshName(), () -> new MeshItem(mesh));
        }
    }

    public static void init(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
