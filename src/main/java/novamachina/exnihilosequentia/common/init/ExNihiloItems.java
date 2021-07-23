package novamachina.exnihilosequentia.common.init;

import java.util.EnumMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
    // Begin Block Items
    private static final Item.Properties tab = new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    public static final RegistryObject<Item> DUST = ITEMS
            .register(ExNihiloConstants.Blocks.DUST, () -> new BlockItem(ExNihiloBlocks.DUST.get(), tab));
    public static final RegistryObject<Item> CRUSHED_NETHERRACK = ITEMS
            .register(ExNihiloConstants.Blocks.CRUSHED_NETHERRACK,
                    () -> new BlockItem(ExNihiloBlocks.CRUSHED_NETHERRACK.get(), tab));
    public static final RegistryObject<Item> CRUSHED_END_STONE = ITEMS
            .register(ExNihiloConstants.Blocks.CRUSHED_END_STONE,
                    () -> new BlockItem(ExNihiloBlocks.CRUSHED_END_STONE.get(), tab));
    public static final RegistryObject<Item> CRUSHED_ANDESITE = ITEMS
            .register(ExNihiloConstants.Blocks.CRUSHED_ANDESITE,
                    () -> new BlockItem(ExNihiloBlocks.CRUSHED_ANDESITE.get(), tab));
    public static final RegistryObject<Item> CRUSHED_DIORITE = ITEMS
            .register(ExNihiloConstants.Blocks.CRUSHED_DIORITE,
                    () -> new BlockItem(ExNihiloBlocks.CRUSHED_DIORITE.get(), tab));
    public static final RegistryObject<Item> CRUSHED_GRANITE = ITEMS
            .register(ExNihiloConstants.Blocks.CRUSHED_GRANITE,
                    () -> new BlockItem(ExNihiloBlocks.CRUSHED_GRANITE.get(), tab));
    public static final RegistryObject<Item> END_CAKE = ITEMS
            .register(ExNihiloConstants.Blocks.END_CAKE, () -> new BlockItem(ExNihiloBlocks.END_CAKE.get(), tab));
    public static final RegistryObject<Item> INFESTING_LEAVES = ITEMS
            .register(Blocks.INFESTING_LEAVES, () -> new BlockItem(ExNihiloBlocks.INFESTING_LEAVES.get(), tab));
    public static final RegistryObject<Item> INFESTED_LEAVES = ITEMS
            .register(Blocks.INFESTED_LEAVES, () -> new BlockItem(ExNihiloBlocks.INFESTED_LEAVES.get(), tab));
    public static final RegistryObject<Item> SIEVE_ACACIA = ITEMS.register(Blocks.SIEVE_ACACIA,
            () -> createBurnableItem(ExNihiloBlocks.SIEVE_ACACIA.get()));
    public static final RegistryObject<Item> SIEVE_BIRCH = ITEMS.register(Blocks.SIEVE_BIRCH,
            () -> createBurnableItem(ExNihiloBlocks.SIEVE_BIRCH.get()));
    public static final RegistryObject<Item> SIEVE_DARK_OAK = ITEMS.register(Blocks.SIEVE_DARK_OAK,
            () -> createBurnableItem(ExNihiloBlocks.SIEVE_DARK_OAK.get()));
    public static final RegistryObject<Item> SIEVE_JUNGLE = ITEMS.register(Blocks.SIEVE_JUNGLE,
            () -> createBurnableItem(ExNihiloBlocks.SIEVE_JUNGLE.get()));
    public static final RegistryObject<Item> SIEVE_OAK = ITEMS.register(Blocks.SIEVE_OAK,
            () -> createBurnableItem(ExNihiloBlocks.SIEVE_OAK.get()));
    public static final RegistryObject<Item> SIEVE_SPRUCE = ITEMS.register(Blocks.SIEVE_SPRUCE,
            () -> createBurnableItem(ExNihiloBlocks.SIEVE_SPRUCE.get()));
    public static final RegistryObject<Item> SIEVE_CRIMSON = ITEMS.register(Blocks.SIEVE_CRIMSON,
            () -> createBurnableItem(ExNihiloBlocks.SIEVE_CRIMSON.get()));
    public static final RegistryObject<Item> SIEVE_WARPED = ITEMS.register(Blocks.SIEVE_WARPED,
            () -> createBurnableItem(ExNihiloBlocks.SIEVE_WARPED.get()));
    public static final RegistryObject<Item> CRUCIBLE_UNFIRED = ITEMS
            .register(Blocks.CRUCIBLE_UNFIRED, () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_UNFIRED.get(), tab));
    public static final RegistryObject<Item> CRUCIBLE_FIRED = ITEMS
            .register(Blocks.CRUCIBLE_FIRED, () -> new BlockItem(ExNihiloBlocks.CRUCIBLE_FIRED.get(), tab));
    public static final RegistryObject<Item> CRUCIBLE_ACACIA = ITEMS
            .register(Blocks.CRUCIBLE_ACACIA, () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_ACACIA.get()));
    public static final RegistryObject<Item> CRUCIBLE_BIRCH = ITEMS
            .register(Blocks.CRUCIBLE_BIRCH, () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_BIRCH.get()));
    public static final RegistryObject<Item> CRUCIBLE_DARK_OAK = ITEMS
            .register(Blocks.CRUCIBLE_DARK_OAK, () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get()));
    public static final RegistryObject<Item> CRUCIBLE_JUNGLE = ITEMS
            .register(Blocks.CRUCIBLE_JUNGLE, () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_JUNGLE.get()));
    public static final RegistryObject<Item> CRUCIBLE_OAK = ITEMS
            .register(Blocks.CRUCIBLE_OAK, () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_OAK.get()));
    public static final RegistryObject<Item> CRUCIBLE_SPRUCE = ITEMS
            .register(Blocks.CRUCIBLE_SPRUCE, () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_SPRUCE.get()));
    public static final RegistryObject<Item> CRUCIBLE_CRIMSON = ITEMS
            .register(Blocks.CRUCIBLE_CRIMSON, () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_CRIMSON.get()));
    public static final RegistryObject<Item> CRUCIBLE_WARPED = ITEMS
            .register(Blocks.CRUCIBLE_WARPED, () -> createBurnableItem(ExNihiloBlocks.CRUCIBLE_WARPED.get()));
    public static final RegistryObject<Item> BARREL_ACACIA = ITEMS
            .register(Blocks.BARREL_ACACIA, () -> createBurnableItem(ExNihiloBlocks.BARREL_ACACIA.get()));
    public static final RegistryObject<Item> BARREL_BIRCH = ITEMS
            .register(Blocks.BARREL_BIRCH, () -> createBurnableItem(ExNihiloBlocks.BARREL_BIRCH.get()));
    public static final RegistryObject<Item> BARREL_DARK_OAK = ITEMS
            .register(Blocks.BARREL_DARK_OAK, () -> createBurnableItem(ExNihiloBlocks.BARREL_DARK_OAK.get()));
    public static final RegistryObject<Item> BARREL_JUNGLE = ITEMS
            .register(Blocks.BARREL_JUNGLE, () -> createBurnableItem(ExNihiloBlocks.BARREL_JUNGLE.get()));
    public static final RegistryObject<Item> BARREL_OAK = ITEMS
            .register(Blocks.BARREL_OAK, () -> createBurnableItem(ExNihiloBlocks.BARREL_OAK.get()));
    public static final RegistryObject<Item> BARREL_SPRUCE = ITEMS
            .register(Blocks.BARREL_SPRUCE, () -> createBurnableItem(ExNihiloBlocks.BARREL_SPRUCE.get()));
    public static final RegistryObject<Item> BARREL_CRIMSON = ITEMS
            .register(Blocks.BARREL_CRIMSON, () -> createBurnableItem(ExNihiloBlocks.BARREL_CRIMSON.get()));
    public static final RegistryObject<Item> BARREL_WARPED = ITEMS
            .register(Blocks.BARREL_WARPED, () -> createBurnableItem(ExNihiloBlocks.BARREL_WARPED.get()));
    public static final RegistryObject<Item> BARREL_STONE = ITEMS
            .register(Blocks.BARREL_STONE, () -> new BlockItem(ExNihiloBlocks.BARREL_STONE.get(), tab));
    // Begin Items Only
    public static final RegistryObject<Item> COOKED_SILKWORM = ITEMS
            .register(ExNihiloConstants.Items.COOKED_SILKWORM, CookedSilkwormItem::new);
    public static final RegistryObject<Item> SILKWORM = ITEMS
            .register(ExNihiloConstants.Items.SILKWORM, () -> new ResourceItem(ExNihiloConstants.Items.SILKWORM));
    public static final RegistryObject<BucketItem> WITCH_WATER_BUCKET = ITEMS
            .register(ExNihiloConstants.Items.WITCH_WATER_BUCKET,
                    () -> new BucketItem(ExNihiloFluids.WITCH_WATER, tab.stacksTo(1)));
    public static final RegistryObject<BucketItem> SEA_WATER_BUCKET = ITEMS
            .register(ExNihiloConstants.Items.SEA_WATER_BUCKET,
                    () -> new BucketItem(ExNihiloFluids.SEA_WATER, tab.stacksTo(1)));
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

    private static BlockItem createBurnableItem(Block block) {
        return new BlockItem(block, tab){
            @Override
            public int getBurnTime(ItemStack itemStack) {
                return 400;
            }
        };
    }
}
