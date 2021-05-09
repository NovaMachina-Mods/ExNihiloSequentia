package novamachina.exnihilosequentia.common.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.creation.AbstractItemCreation;
import novamachina.exnihilosequentia.common.item.dolls.EnumDoll;
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
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Items;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloItems extends AbstractItemCreation {

    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    // Begin Block Items
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, ModIds.EX_NIHILO_SEQUENTIA);
    public static final RegistryObject<Item> DUST = ITEMS
            .register(Blocks.DUST, () -> new BlockItem(ExNihiloBlocks.DUST.get(),
                    new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_NETHERRACK = ITEMS
            .register(Blocks.CRUSHED_NETHERRACK,
                    () -> new BlockItem(ExNihiloBlocks.CRUSHED_NETHERRACK.get(),
                            new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_END_STONE = ITEMS
            .register(Blocks.CRUSHED_END_STONE,
                    () -> new BlockItem(ExNihiloBlocks.CRUSHED_END_STONE.get(),
                            new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_ANDESITE = ITEMS
            .register(Blocks.CRUSHED_ANDESITE,
                    () -> new BlockItem(ExNihiloBlocks.CRUSHED_ANDESITE.get(),
                            new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_DIORITE = ITEMS
            .register(Blocks.CRUSHED_DIORITE,
                    () -> new BlockItem(ExNihiloBlocks.CRUSHED_DIORITE.get(),
                            new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> CRUSHED_GRANITE = ITEMS
            .register(Blocks.CRUSHED_GRANITE,
                    () -> new BlockItem(ExNihiloBlocks.CRUSHED_GRANITE.get(),
                            new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> END_CAKE = ITEMS
            .register(Blocks.END_CAKE, () -> new BlockItem(ExNihiloBlocks.END_CAKE.get(),
                    new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> INFESTING_LEAVES = ITEMS
            .register(Blocks.INFESTING_LEAVES, () -> new BlockItem(ExNihiloBlocks.INFESTING_LEAVES.get(),
                    new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> INFESTED_LEAVES = ITEMS
            .register(Blocks.INFESTED_LEAVES, () -> new BlockItem(ExNihiloBlocks.INFESTED_LEAVES.get(),
                    new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP)));
    public static final RegistryObject<Item> SIEVE = createBlockItem(
            Blocks.SIEVE, ExNihiloBlocks.SIEVE.get());
    public static final RegistryObject<Item> CRUCIBLE_UNFIRED = createBlockItem(
            Blocks.CRUCIBLE_UNFIRED, ExNihiloBlocks.CRUCIBLE_UNFIRED.get());
    public static final RegistryObject<Item> CRUCIBLE_FIRED = createBlockItem(
            Blocks.CRUCIBLE_FIRED, ExNihiloBlocks.CRUCIBLE_FIRED.get());
    public static final RegistryObject<Item> CRUCIBLE_WOOD = createBlockItem(
            Blocks.CRUCIBLE_WOOD, ExNihiloBlocks.CRUCIBLE_WOOD.get());
    public static final RegistryObject<Item> BARREL_WOOD = createBlockItem(
            Blocks.BARREL_WOOD, ExNihiloBlocks.BARREL_WOOD.get());
    public static final RegistryObject<Item> BARREL_STONE = createBlockItem(
            Blocks.BARREL_STONE, ExNihiloBlocks.BARREL_STONE.get());
    // Begin Items Only
    public static final RegistryObject<Item> COOKED_SILKWORM = createFoodItem(
            Items.COOKED_SILKWORM, 2, 0.6F);
    public static final RegistryObject<BucketItem> WITCH_WATER_BUCKET = createBucketItem(
            Items.WITCH_WATER_BUCKET, ExNihiloFluids.WITCH_WATER.get());
    public static final RegistryObject<BucketItem> SEA_WATER_BUCKET = createBucketItem(
            Items.SEA_WATER_BUCKET, ExNihiloFluids.SEA_WATER.get());

    static {
        for (EnumCrook crook : EnumCrook.values()) {
            crook.setRegistryObject(ITEMS
                    .register(crook.crookName, () -> new CrookBaseItem(crook.tier, crook.maxDamage)));
        }

        for (EnumHammer hammer : EnumHammer.values()) {
            hammer.setRegistryObject(ITEMS.register(hammer.hammerName,
                    () -> new HammerBaseItem(hammer.tier, hammer.maxDamage)));
        }

        for (EnumOre ore : EnumOre.values()) {
            ore.setChunkItem(ITEMS.register(ore.getChunkName(), () -> new OreItem(ore)));
            ore.setPieceItem(ITEMS.register(ore.getPieceName(), () -> new OreItem(ore)));
            if (ore.shouldGenerateIngot()) {
                RegistryObject<OreItem> item = ITEMS.register(ore.getIngotName(), () -> new OreItem(ore));
                ore.setIngotRegistryItem(item);
                AbstractItemCreation.ingotMap.put(ore, item);
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

        for (EnumDoll doll : EnumDoll.values()) {
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
