package com.novamachina.exnihilosequentia.common.init;

import com.novamachina.exnihilosequentia.common.item.CookedSilkwormItem;
import com.novamachina.exnihilosequentia.common.item.dolls.DollEnum;
import com.novamachina.exnihilosequentia.common.item.dolls.DollItem;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import com.novamachina.exnihilosequentia.common.item.ore.EnumModdedOre;
import com.novamachina.exnihilosequentia.common.item.ore.EnumOre;
import com.novamachina.exnihilosequentia.common.item.ore.IOre;
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
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class ModItems {

    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(
        ForgeRegistries.ITEMS, Constants.ModIds.EX_NIHILO_SEQUENTIA);
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
    public static final RegistryObject<Item> INFESTING_LEAVES = ITEMS
        .register(Blocks.INFESTING_LEAVES, () -> new BlockItem(ModBlocks.INFESTING_LEAVES.get(),
            new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> INFESTED_LEAVES = ITEMS
        .register(Blocks.INFESTED_LEAVES, () -> new BlockItem(ModBlocks.INFESTED_LEAVES.get(),
            new Item.Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> SIEVE = ITEMS.register(Blocks.SIEVE,
        () -> new BlockItem(ModBlocks.SIEVE.get(),
            new Properties().group(ModInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> COOKED_SILKWORM = ITEMS
        .register(Constants.Items.COOKED_SILKWORM, CookedSilkwormItem::new);
    public static final RegistryObject<BucketItem> WITCH_WATER_BUCKET = ITEMS
        .register(Constants.Items.WITCH_WATER_BUCKET,
            () -> new BucketItem(ModFluids.WITCH_WATER,
                new Properties().group(ModInitialization.ITEM_GROUP).maxStackSize(1)));
    public static final RegistryObject<BucketItem> SEA_WATER_BUCKET = ITEMS
        .register(Constants.Items.SEA_WATER_BUCKET,
            () -> new BucketItem(ModFluids.SEA_WATER,
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
    public static Map<String, RegistryObject<OreItem>> chunkMap = new HashMap<>();
    public static Map<String, RegistryObject<OreItem>> pieceMap = new HashMap<>();
    public static Map<String, RegistryObject<OreItem>> ingotMap = new HashMap<>();

    static {
        for (EnumCrook crook : EnumCrook.values()) {
            crook.setRegistryObject(ITEMS.register(crook.name, () -> new CrookBaseItem(crook.teir, crook.defaultDurability)));
        }

        for (EnumHammer hammer : EnumHammer.values()) {
            hammer.setRegistryObject(ITEMS.register(hammer.name, () -> new HammerBaseItem(hammer.teir, hammer.defaultDurability)));
        }

        for (EnumOre ore : EnumOre.values()) {
            chunkMap.put(ore.getName(), ITEMS.register(ore.getChunkName(), () -> new OreItem(ore)));
            pieceMap.put(ore.getName(), ITEMS.register(ore.getPieceName(), () -> new OreItem(ore)));
        }

        for (IOre ore : EnumModdedOre.values()) {
            chunkMap.put(ore.getName(), ITEMS.register(ore.getChunkName(), () -> new OreItem(ore)));
            pieceMap.put(ore.getName(), ITEMS.register(ore.getPieceName(), () -> new OreItem(ore)));
            ingotMap.put(ore.getName(), ITEMS.register(ore.getIngotName(), () -> new OreItem(ore)));
        }

        for (EnumSeed seed : EnumSeed.values()) {
            seed.setRegistryObject(ITEMS.register(seed.getSeedName(),
                () -> new SeedBaseItem(seed.getDefaultState()).setPlantType(seed.getPlantType())));
        }

        for (EnumResource resource : EnumResource.values()) {
            resource.setRegistryObject(ITEMS.register(resource.getResourceName(), () -> new ResourceItem(resource.getResourceName())));
        }

        for (EnumPebbleType type : EnumPebbleType.values()) {
            type.setRegistryObject(ITEMS.register(type.getType(), () -> new PebbleItem(type)));
        }

        for (EnumMesh mesh : EnumMesh.values()) {
            if (mesh != EnumMesh.NONE) {
                mesh.setRegistryObject(ITEMS.register(mesh.getMeshName(), () -> new MeshItem(mesh)));
            }
        }

        for (DollEnum doll : DollEnum.values()) {
            doll.setRegistryObject(ITEMS.register(doll.getDollName(), () -> new DollItem(doll)));
        }
    }

    public static void init(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
