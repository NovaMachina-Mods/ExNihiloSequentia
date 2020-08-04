package com.novamachina.exnihilosequentia.common.setup;

import com.novamachina.exnihilosequentia.common.item.CookedSilkwormItem;
import com.novamachina.exnihilosequentia.common.item.dolls.CraftingDollItem;
import com.novamachina.exnihilosequentia.common.item.dolls.DollEnum;
import com.novamachina.exnihilosequentia.common.item.dolls.DollItem;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import com.novamachina.exnihilosequentia.common.item.ore.EnumOre;
import com.novamachina.exnihilosequentia.common.item.ore.OreItem;
import com.novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import com.novamachina.exnihilosequentia.common.item.pebbles.PebbleItem;
import com.novamachina.exnihilosequentia.common.item.resources.EnumResource;
import com.novamachina.exnihilosequentia.common.item.resources.ResourceItem;
import com.novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import com.novamachina.exnihilosequentia.common.item.seeds.SeedBaseItem;
import com.novamachina.exnihilosequentia.common.item.tools.crook.CrookBaseItem;
import com.novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import com.novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import com.novamachina.exnihilosequentia.common.item.tools.hammer.HammerBaseItem;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.Constants.Blocks;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(
        ForgeRegistries.ITEMS, Constants.ModInfo.MOD_ID);

    public static Map<String, RegistryObject<OreItem>> chunkMap = new HashMap<>();
    public static Map<String, RegistryObject<OreItem>> pieceMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> resourceMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> pebbleMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> seedMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> dollMap = new HashMap<>();

    public static final RegistryObject<Item> DUST = ITEMS
        .register(Constants.Blocks.DUST, () -> new BlockItem(ModBlocks.DUST.get(),
            new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_NETHERRACK = ITEMS
        .register(Constants.Blocks.CRUSHED_NETHERRACK,
            () -> new BlockItem(ModBlocks.CRUSHED_NETHERRACK.get(),
                new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_END_STONE = ITEMS
        .register(Constants.Blocks.CRUSHED_END_STONE,
            () -> new BlockItem(ModBlocks.CRUSHED_END_STONE.get(),
                new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_ANDESITE = ITEMS
        .register(Constants.Blocks.CRUSHED_ANDESITE,
            () -> new BlockItem(ModBlocks.CRUSHED_ANDESITE.get(),
                new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_DIORITE = ITEMS
        .register(Constants.Blocks.CRUSHED_DIORITE,
            () -> new BlockItem(ModBlocks.CRUSHED_DIORITE.get(),
                new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_GRANITE = ITEMS
        .register(Constants.Blocks.CRUSHED_GRANITE,
            () -> new BlockItem(ModBlocks.CRUSHED_GRANITE.get(),
                new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> END_CAKE = ITEMS
        .register(Constants.Blocks.END_CAKE, () -> new BlockItem(ModBlocks.END_CAKE.get(),
            new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> SIEVE = ITEMS.register(Blocks.SIEVE,
        () -> new BlockItem(ModBlocks.SIEVE.get(),
            new Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> COOKED_SILKWORM = ITEMS
        .register(Constants.Items.COOKED_SILKWORM, CookedSilkwormItem::new);
    public static final RegistryObject<BucketItem> WITCH_WATER_BUCKET = ITEMS
        .register(Constants.Items.WITCH_WATER_BUCKET,
            () -> new BucketItem(ModFluids.WITCH_WATER_STILL,
                new Properties().group(ModInitialization.ITEM_GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> CRUCIBLE_UNFIRED = ITEMS
        .register(Blocks.CRUCIBLE_UNFIRED, () -> new BlockItem(ModBlocks.CRUCIBLE_UNFIRED.get(),
            new Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUCIBLE_FIRED = ITEMS
        .register(Blocks.CRUCIBLE_FIRED, () -> new BlockItem(ModBlocks.CRUCIBLE_FIRED.get(),
            new Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUCIBLE_WOOD = ITEMS
        .register(Blocks.CRUCIBLE_WOOD, () -> new BlockItem(ModBlocks.CRUCIBLE_WOOD.get(),
            new Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> BARREL_WOOD = ITEMS
        .register(Blocks.BARREL_WOOD, () -> new BlockItem(ModBlocks.BARREL_WOOD.get(),
            new Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRAFTING_DOLL = ITEMS
        .register(Constants.Items.CRAFTING_DOLL, CraftingDollItem::new);

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
            seedMap.put(seed.getSeedName(), ITEMS.register(seed.getSeedName(),
                () -> new SeedBaseItem(seed.getDefaultState()).setPlantType(seed.getPlantType())));
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
            if (mesh != EnumMesh.NONE) {
                ITEMS.register(mesh.getMeshName(), () -> new MeshItem(mesh));
            }
        }

        for (DollEnum doll : DollEnum.values()) {
            dollMap.put(doll.getEntityName(), ITEMS.register(doll.getDollName(), () -> new DollItem(doll)));
        }
    }

    public static void init(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
