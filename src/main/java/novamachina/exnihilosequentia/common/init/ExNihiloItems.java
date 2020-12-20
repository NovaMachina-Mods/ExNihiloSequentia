package novamachina.exnihilosequentia.common.init;

import novamachina.exnihilosequentia.common.item.CookedSilkwormItem;
import novamachina.exnihilosequentia.common.item.dolls.DollEnum;
import novamachina.exnihilosequentia.common.item.dolls.DollItem;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.ore.OreItem;
import novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import novamachina.exnihilosequentia.common.item.pebbles.PebbleItem;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.item.resources.ResourceItem;
import novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import novamachina.exnihilosequentia.common.item.seeds.SeedBaseItem;
import novamachina.exnihilosequentia.common.item.tools.crook.CrookBaseItem;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.item.tools.hammer.HammerBaseItem;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloItems {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    // Begin Block Items
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
        ForgeRegistries.ITEMS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    public static final RegistryObject<Item> DUST = ITEMS
        .register(ExNihiloConstants.Blocks.DUST, () -> new BlockItem(ExNihiloBlocks.DUST.get(),
            new Item.Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_NETHERRACK = ITEMS
        .register(ExNihiloConstants.Blocks.CRUSHED_NETHERRACK,
            () -> new BlockItem(ExNihiloBlocks.CRUSHED_NETHERRACK.get(),
                new Item.Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_END_STONE = ITEMS
        .register(ExNihiloConstants.Blocks.CRUSHED_END_STONE,
            () -> new BlockItem(ExNihiloBlocks.CRUSHED_END_STONE.get(),
                new Item.Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_ANDESITE = ITEMS
        .register(ExNihiloConstants.Blocks.CRUSHED_ANDESITE,
            () -> new BlockItem(ExNihiloBlocks.CRUSHED_ANDESITE.get(),
                new Item.Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_DIORITE = ITEMS
        .register(ExNihiloConstants.Blocks.CRUSHED_DIORITE,
            () -> new BlockItem(ExNihiloBlocks.CRUSHED_DIORITE.get(),
                new Item.Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_GRANITE = ITEMS
        .register(ExNihiloConstants.Blocks.CRUSHED_GRANITE,
            () -> new BlockItem(ExNihiloBlocks.CRUSHED_GRANITE.get(),
                new Item.Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_SKYSTONE = ITEMS
        .register(ExNihiloConstants.Blocks.CRUSHED_SKYSTONE,
            () -> new BlockItem(ExNihiloBlocks.CRUSHED_SKYSTONE.get(),
                new Item.Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> END_CAKE = ITEMS
        .register(ExNihiloConstants.Blocks.END_CAKE, () -> new BlockItem(ExNihiloBlocks.END_CAKE.get(),
            new Item.Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> INFESTING_LEAVES = ITEMS
        .register(Blocks.INFESTING_LEAVES, () -> new BlockItem(ExNihiloBlocks.INFESTING_LEAVES.get(),
            new Item.Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> INFESTED_LEAVES = ITEMS
        .register(Blocks.INFESTED_LEAVES, () -> new BlockItem(ExNihiloBlocks.INFESTED_LEAVES.get(),
            new Item.Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> SIEVE = ITEMS.register(Blocks.SIEVE,
        () -> new BlockItem(ExNihiloBlocks.SIEVE.get(),
            new Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUCIBLE_UNFIRED = ITEMS
        .register(Blocks.CRUCIBLE_UNFIRED, () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_UNFIRED.get(),
            new Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUCIBLE_FIRED = ITEMS
        .register(Blocks.CRUCIBLE_FIRED, () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_FIRED.get(),
            new Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUCIBLE_WOOD = ITEMS
        .register(Blocks.CRUCIBLE_WOOD, () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_WOOD.get(),
            new Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> BARREL_WOOD = ITEMS
        .register(Blocks.BARREL_WOOD, () -> new BlockItem(ExNihiloBlocks.BARREL_WOOD.get(),
            new Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> BARREL_STONE = ITEMS
        .register(Blocks.BARREL_STONE, () -> new BlockItem(ExNihiloBlocks.BARREL_STONE.get(),
            new Properties().group(ExNihiloInitialization.ITEM_GROUP)));
    // Begin Items Only
    public static final RegistryObject<Item> COOKED_SILKWORM = ITEMS
        .register(ExNihiloConstants.Items.COOKED_SILKWORM, CookedSilkwormItem::new);
    public static final RegistryObject<BucketItem> WITCH_WATER_BUCKET = ITEMS
        .register(ExNihiloConstants.Items.WITCH_WATER_BUCKET,
            () -> new BucketItem(ExNihiloFluids.WITCH_WATER,
                new Properties().group(ExNihiloInitialization.ITEM_GROUP).maxStackSize(1)));
    public static final RegistryObject<BucketItem> SEA_WATER_BUCKET = ITEMS
        .register(ExNihiloConstants.Items.SEA_WATER_BUCKET,
            () -> new BucketItem(ExNihiloFluids.SEA_WATER,
                new Properties().group(ExNihiloInitialization.ITEM_GROUP).maxStackSize(1)));

    static {
        for (EnumCrook crook : EnumCrook.values()) {
            crook.setRegistryObject(ITEMS
                .register(crook.crookName, () -> new CrookBaseItem(crook.tier, crook.defaultDurability)));
        }

        for (EnumHammer hammer : EnumHammer.values()) {
            hammer.setRegistryObject(ITEMS.register(hammer.hammerName,
                () -> new HammerBaseItem(hammer.tier, hammer.defaultDurability)));
        }

        for (EnumOre ore : EnumOre.values()) {
            ore.setChunkItem(ITEMS.register(ore.getChunkName(), () -> new OreItem(ore)));
            ore.setPieceItem(ITEMS.register(ore.getPieceName(), () -> new OreItem(ore)));
            if (!ore.isVanilla()) {
                ore.setIngotItem(ITEMS.register(ore.getIngotName(), () -> new OreItem(ore)));
            }
        }

        for (EnumSeed seed : EnumSeed.values()) {
            seed.setRegistryObject(ITEMS.register(seed.getSeedName(),
                () -> new SeedBaseItem(seed.getDefaultState()).setPlantType(seed.getPlantType())));
        }

        for (EnumResource resource : EnumResource.values()) {
            resource.setRegistryObject(ITEMS.register(resource.getResourceName(),
                () -> new ResourceItem(resource.getResourceName())));
        }

        for (EnumPebbleType type : EnumPebbleType.values()) {
            type.setRegistryObject(ITEMS.register(type.getType(), PebbleItem::new));
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

    private ExNihiloItems() {
    }

    public static void init(IEventBus modEventBus) {
        logger.debug("Register items");
        ITEMS.register(modEventBus);
    }
}
