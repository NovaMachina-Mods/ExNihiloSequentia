package novamachina.exnihilosequentia.common.init;

import java.util.EnumMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.item.CookedSilkwormItem;
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
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class ExNihiloItems {
    private static final Properties properties = new Properties().tab(ExNihiloInitialization.ITEM_GROUP);
    // Begin Block Items
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    public static final RegistryObject<Item> DUST = ITEMS.register(Blocks.DUST,
            () -> new BlockItem(ExNihiloBlocks.DUST.get(), properties));
    public static final RegistryObject<Item> CRUSHED_NETHERRACK = ITEMS.register(Blocks.CRUSHED_NETHERRACK,
            () -> new BlockItem(ExNihiloBlocks.CRUSHED_NETHERRACK.get(), properties));
    public static final RegistryObject<Item> CRUSHED_END_STONE = ITEMS.register(Blocks.CRUSHED_END_STONE,
            () -> new BlockItem(ExNihiloBlocks.CRUSHED_END_STONE.get(), properties));
    public static final RegistryObject<Item> CRUSHED_ANDESITE = ITEMS.register(Blocks.CRUSHED_ANDESITE,
            () -> new BlockItem(ExNihiloBlocks.CRUSHED_ANDESITE.get(), properties));
    public static final RegistryObject<Item> CRUSHED_DIORITE = ITEMS.register(Blocks.CRUSHED_DIORITE,
            () -> new BlockItem(ExNihiloBlocks.CRUSHED_DIORITE.get(), properties));
    public static final RegistryObject<Item> CRUSHED_GRANITE = ITEMS.register(Blocks.CRUSHED_GRANITE,
            () -> new BlockItem(ExNihiloBlocks.CRUSHED_GRANITE.get(), properties));
    public static final RegistryObject<Item> END_CAKE = ITEMS.register(Blocks.END_CAKE,
            () -> new BlockItem(ExNihiloBlocks.END_CAKE.get(), properties));
    public static final RegistryObject<Item> INFESTING_LEAVES = ITEMS.register(Blocks.INFESTING_LEAVES,
            () -> new BlockItem(ExNihiloBlocks.INFESTING_LEAVES.get(), properties));
    public static final RegistryObject<Item> INFESTED_LEAVES = ITEMS.register(Blocks.INFESTED_LEAVES,
            () -> new BlockItem(ExNihiloBlocks.INFESTED_LEAVES.get(), properties));
    // sieves
    public static final RegistryObject<Item> SIEVE_CRIMSON = ITEMS.register(Blocks.SIEVE_CRIMSON,
            () -> new BlockItem(ExNihiloBlocks.SIEVE_CRIMSON.get(), properties));
    public static final RegistryObject<Item> SIEVE_WARPED = ITEMS.register(Blocks.SIEVE_WARPED,
            () -> new BlockItem(ExNihiloBlocks.SIEVE_WARPED.get(), properties));
    public static final RegistryObject<Item> SIEVE_ACACIA = ITEMS.register(Blocks.SIEVE_ACACIA,
            () -> new BlockItem(ExNihiloBlocks.SIEVE_ACACIA.get(), properties));
    public static final RegistryObject<Item> SIEVE_BIRCH = ITEMS.register(Blocks.SIEVE_BIRCH,
            () -> new BlockItem(ExNihiloBlocks.SIEVE_BIRCH.get(), properties));
    public static final RegistryObject<Item> SIEVE_DARK_OAK = ITEMS.register(Blocks.SIEVE_DARK_OAK,
            () -> new BlockItem(ExNihiloBlocks.SIEVE_DARK_OAK.get(), properties));
    public static final RegistryObject<Item> SIEVE_JUNGLE = ITEMS.register(Blocks.SIEVE_JUNGLE,
            () -> new BlockItem(ExNihiloBlocks.SIEVE_JUNGLE.get(), properties));
    public static final RegistryObject<Item> SIEVE_SPRUCE = ITEMS.register(Blocks.SIEVE_SPRUCE,
            () -> new BlockItem(ExNihiloBlocks.SIEVE_SPRUCE.get(), properties));
    public static final RegistryObject<Item> SIEVE_OAK = ITEMS.register(Blocks.SIEVE_OAK,
            () -> new BlockItem(ExNihiloBlocks.SIEVE_OAK.get(), properties));
    public static final RegistryObject<Item> CRUCIBLE_UNFIRED = ITEMS.register(Blocks.CRUCIBLE_UNFIRED,
            () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_UNFIRED.get(), properties));
    public static final RegistryObject<Item> CRUCIBLE_FIRED = ITEMS.register(Blocks.CRUCIBLE_FIRED,
            () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_FIRED.get(), properties));
    // Wooden Crucibles
    public static final RegistryObject<Item> CRUCIBLE_ACACIA = ITEMS.register(Blocks.CRUCIBLE_ACACIA,
            () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_ACACIA.get(), properties));
    public static final RegistryObject<Item> CRUCIBLE_BIRCH = ITEMS.register(Blocks.CRUCIBLE_BIRCH,
            () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_BIRCH.get(), properties));
    public static final RegistryObject<Item> CRUCIBLE_DARK_OAK = ITEMS.register(Blocks.CRUCIBLE_DARK_OAK,
            () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get(), properties));
    public static final RegistryObject<Item> CRUCIBLE_JUNGLE = ITEMS.register(Blocks.CRUCIBLE_JUNGLE,
            () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_JUNGLE.get(), properties));
    public static final RegistryObject<Item> CRUCIBLE_SPRUCE = ITEMS.register(Blocks.CRUCIBLE_SPRUCE,
            () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_SPRUCE.get(), properties));
    public static final RegistryObject<Item> CRUCIBLE_OAK = ITEMS.register(Blocks.CRUCIBLE_OAK,
            () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_OAK.get(), properties));
    public static final RegistryObject<Item> CRUCIBLE_CRIMSON = ITEMS.register(Blocks.CRUCIBLE_CRIMSON,
            () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_CRIMSON.get(), properties));
    public static final RegistryObject<Item> CRUCIBLE_WARPED = ITEMS.register(Blocks.CRUCIBLE_WARPED,
            () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_WARPED.get(), properties));
    // Wooden Barrels
    public static final RegistryObject<Item> BARREL_ACACIA = ITEMS.register(Blocks.BARREL_ACACIA,
            () -> new BlockItem(ExNihiloBlocks.BARREL_ACACIA.get(), properties));
    public static final RegistryObject<Item> BARREL_BIRCH = ITEMS.register(Blocks.BARREL_BIRCH,
            () -> new BlockItem(ExNihiloBlocks.BARREL_BIRCH.get(), properties));
    public static final RegistryObject<Item> BARREL_DARK_OAK = ITEMS.register(Blocks.BARREL_DARK_OAK,
            () -> new BlockItem(ExNihiloBlocks.BARREL_DARK_OAK.get(), properties));
    public static final RegistryObject<Item> BARREL_JUNGLE = ITEMS.register(Blocks.BARREL_JUNGLE,
            () -> new BlockItem(ExNihiloBlocks.BARREL_JUNGLE.get(), properties));
    public static final RegistryObject<Item> BARREL_SPRUCE = ITEMS.register(Blocks.BARREL_SPRUCE,
            () -> new BlockItem(ExNihiloBlocks.BARREL_SPRUCE.get(), properties));
    public static final RegistryObject<Item> BARREL_OAK = ITEMS.register(Blocks.BARREL_OAK,
            () -> new BlockItem(ExNihiloBlocks.BARREL_OAK.get(), properties));
    public static final RegistryObject<Item> BARREL_CRIMSON = ITEMS.register(Blocks.BARREL_CRIMSON,
            () -> new BlockItem(ExNihiloBlocks.BARREL_CRIMSON.get(), properties));
    public static final RegistryObject<Item> BARREL_WARPED = ITEMS.register(Blocks.BARREL_WARPED,
            () -> new BlockItem(ExNihiloBlocks.BARREL_WARPED.get(), properties));
    public static final RegistryObject<Item> BARREL_STONE = ITEMS.register(Blocks.BARREL_STONE,
            () -> new BlockItem(ExNihiloBlocks.BARREL_STONE.get(), properties));
    /*public static final RegistryObject<Item> BARREL_GLASS = ITEMS.register(Blocks.BARREL_GLASS,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_WHITE = ITEMS.register(Blocks.BARREL_GLASS_WHITE,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_WHITE.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_GRAY = ITEMS.register(Blocks.BARREL_GLASS_GRAY,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_GRAY.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_LIGHT_GRAY = ITEMS.register(Blocks.BARREL_GLASS_LIGHT_GRAY,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_LIGHT_GRAY.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_BLACK = ITEMS.register(Blocks.BARREL_GLASS_BLACK,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_BLACK.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_BROWN = ITEMS.register(Blocks.BARREL_GLASS_BROWN,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_BROWN.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_RED = ITEMS.register(Blocks.BARREL_GLASS_RED,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_RED.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_MAGENTA = ITEMS.register(Blocks.BARREL_GLASS_MAGENTA,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_MAGENTA.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_ORANGE = ITEMS.register(Blocks.BARREL_GLASS_ORANGE,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_ORANGE.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_PINK = ITEMS.register(Blocks.BARREL_GLASS_PINK,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_PINK.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_PURPLE = ITEMS.register(Blocks.BARREL_GLASS_PURPLE,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_PURPLE.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_YELLOW = ITEMS.register(Blocks.BARREL_GLASS_YELLOW,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_YELLOW.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_LIME = ITEMS.register(Blocks.BARREL_GLASS_LIME,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_LIME.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_GREEN = ITEMS.register(Blocks.BARREL_GLASS_GREEN,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_GREEN.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_LIGHT_BLUE = ITEMS.register(Blocks.BARREL_GLASS_LIGHT_BLUE,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_LIGHT_BLUE.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_CYAN = ITEMS.register(Blocks.BARREL_GLASS_CYAN,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_CYAN.get(), properties));
    public static final RegistryObject<Item> BARREL_GLASS_BLUE = ITEMS.register(Blocks.BARREL_GLASS_BLUE,
            () -> new BlockItem(ExNihiloBlocks.BARREL_GLASS_BLUE.get(), properties));*/

    // Begin Items Only
    public static final RegistryObject<Item> COOKED_SILKWORM = ITEMS
            .register(ExNihiloConstants.Items.COOKED_SILKWORM, CookedSilkwormItem::new);
    public static final RegistryObject<BucketItem> WITCH_WATER_BUCKET = ITEMS
            .register(ExNihiloConstants.Items.WITCH_WATER_BUCKET,
                    () -> new BucketItem(ExNihiloFluids.WITCH_WATER,
                            properties.stacksTo(1)));
    public static final RegistryObject<BucketItem> SEA_WATER_BUCKET = ITEMS
            .register(ExNihiloConstants.Items.SEA_WATER_BUCKET,
                    () -> new BucketItem(ExNihiloFluids.SEA_WATER,
                            properties.stacksTo(1)));
    private static final Map<EnumOre, RegistryObject<OreItem>> ingotMap = new EnumMap<>(EnumOre.class);
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

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
                ingotMap.put(ore, item);
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

    public static void fillOreIngots() {
        for (EnumOre ore : EnumOre.values()) {
            if (ore.shouldGenerateIngot()) {
                ore.setIngotItem(ingotMap.get(ore).get());
            }
        }
    }

    public static Map<EnumOre, RegistryObject<OreItem>> getIngotMap() {
        return ingotMap;
    }

    public static void init(IEventBus modEventBus) {
        logger.debug("Register items");
        ITEMS.register(modEventBus);
    }
}
