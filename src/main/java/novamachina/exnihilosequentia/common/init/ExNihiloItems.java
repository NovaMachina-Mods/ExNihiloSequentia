package novamachina.exnihilosequentia.common.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.Blocks;
import novamachina.exnihilosequentia.common.item.CookedSilkwormItem;
import novamachina.exnihilosequentia.common.item.dolls.DollItem;
import novamachina.exnihilosequentia.common.item.dolls.EnumDoll;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.ore.OreItem;
import novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import novamachina.exnihilosequentia.common.item.pebbles.PebbleItem;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.item.resources.ResourceItem;
import novamachina.exnihilosequentia.common.item.tools.crook.CrookBaseItem;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.item.tools.hammer.HammerBaseItem;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

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
    public static final RegistryObject<Item> ACACIA_SIEVE = ITEMS.register(Blocks.ACACIA_SIEVE,
            () -> createBurnableItem(ExNihiloBlocks.ACACIA_SIEVE.get()));
    public static final RegistryObject<Item> BIRCH_SIEVE = ITEMS.register(Blocks.BIRCH_SIEVE,
            () -> createBurnableItem(ExNihiloBlocks.BIRCH_SIEVE.get()));
    public static final RegistryObject<Item> DARK_OAK_SIEVE = ITEMS.register(Blocks.DARK_OAK_SIEVE,
            () -> createBurnableItem(ExNihiloBlocks.DARK_OAK_SIEVE.get()));
    public static final RegistryObject<Item> JUNGLE_SIEVE = ITEMS.register(Blocks.JUNGLE_SIEVE,
            () -> createBurnableItem(ExNihiloBlocks.JUNGLE_SIEVE.get()));
    public static final RegistryObject<Item> OAK_SIEVE = ITEMS.register(Blocks.OAK_SIEVE,
            () -> createBurnableItem(ExNihiloBlocks.OAK_SIEVE.get()));
    public static final RegistryObject<Item> SPRUCE_SIEVE = ITEMS.register(Blocks.SPRUCE_SIEVE,
            () -> createBurnableItem(ExNihiloBlocks.SPRUCE_SIEVE.get()));
    public static final RegistryObject<Item> CRIMSON_SIEVE = ITEMS.register(Blocks.CRIMSON_SIEVE,
            () -> createBurnableItem(ExNihiloBlocks.CRIMSON_SIEVE.get()));
    public static final RegistryObject<Item> WARPED_SIEVE = ITEMS.register(Blocks.WARPED_SIEVE,
            () -> createBurnableItem(ExNihiloBlocks.WARPED_SIEVE.get()));
    public static final RegistryObject<Item> UNFIRED_CRUCIBLE = ITEMS
            .register(Blocks.UNFIRED_CRUCIBLE, () -> new BlockItem(ExNihiloBlocks.UNFIRED_CRUCIBLE.get(), tab));
    public static final RegistryObject<Item> FIRED_CRUCIBLE = ITEMS
            .register(Blocks.FIRED_CRUCIBLE, () -> new BlockItem(ExNihiloBlocks.FIRED_CRUCIBLE.get(), tab));
    public static final RegistryObject<Item> ACACIA_CRUCIBLE = ITEMS
            .register(Blocks.ACACIA_CRUCIBLE, () -> createBurnableItem(ExNihiloBlocks.ACACIA_CRUCIBLE.get()));
    public static final RegistryObject<Item> BIRCH_CRUCIBLE = ITEMS
            .register(Blocks.BIRCH_CRUCIBLE, () -> createBurnableItem(ExNihiloBlocks.BIRCH_CRUCIBLE.get()));
    public static final RegistryObject<Item> CRUCIBLE_DARK_OAK = ITEMS
            .register(Blocks.DARK_OAK_CRUCIBLE, () -> createBurnableItem(ExNihiloBlocks.DARK_OAK_CRUCIBLE.get()));
    public static final RegistryObject<Item> CRUCIBLE_JUNGLE = ITEMS
            .register(Blocks.JUNGLE_CRUCIBLE, () -> createBurnableItem(ExNihiloBlocks.JUNGLE_CRUCIBLE.get()));
    public static final RegistryObject<Item> CRUCIBLE_OAK = ITEMS
            .register(Blocks.OAK_CRUCIBLE, () -> createBurnableItem(ExNihiloBlocks.OAK_CRUCIBLE.get()));
    public static final RegistryObject<Item> CRUCIBLE_SPRUCE = ITEMS
            .register(Blocks.SPRUCE_CRUCIBLE, () -> createBurnableItem(ExNihiloBlocks.SPRUCE_CRUCIBLE.get()));
    public static final RegistryObject<Item> CRUCIBLE_CRIMSON = ITEMS
            .register(Blocks.CRIMSON_CRUCIBLE, () -> createBurnableItem(ExNihiloBlocks.CRIMSON_CRUCIBLE.get()));
    public static final RegistryObject<Item> CRUCIBLE_WARPED = ITEMS
            .register(Blocks.WARPED_CRUCIBLE, () -> createBurnableItem(ExNihiloBlocks.WARPED_CRUCIBLE.get()));
    public static final RegistryObject<Item> BARREL_ACACIA = ITEMS
            .register(Blocks.ACACIA_BARREL, () -> createBurnableItem(ExNihiloBlocks.ACACIA_BARREL.get()));
    public static final RegistryObject<Item> BARREL_BIRCH = ITEMS
            .register(Blocks.BIRCH_BARREL, () -> createBurnableItem(ExNihiloBlocks.BIRCH_BARREL.get()));
    public static final RegistryObject<Item> BARREL_DARK_OAK = ITEMS
            .register(Blocks.DARK_OAK_BARREL, () -> createBurnableItem(ExNihiloBlocks.DARK_OAK_BARREL.get()));
    public static final RegistryObject<Item> BARREL_JUNGLE = ITEMS
            .register(Blocks.JUNGLE_BARREL, () -> createBurnableItem(ExNihiloBlocks.JUNGLE_BARREL.get()));
    public static final RegistryObject<Item> BARREL_OAK = ITEMS
            .register(Blocks.OAK_BARREL, () -> createBurnableItem(ExNihiloBlocks.OAK_BARREL.get()));
    public static final RegistryObject<Item> BARREL_SPRUCE = ITEMS
            .register(Blocks.SPRUCE_BARREL, () -> createBurnableItem(ExNihiloBlocks.SPRUCE_BARREL.get()));
    public static final RegistryObject<Item> BARREL_CRIMSON = ITEMS
            .register(Blocks.CRIMSON_BARREL, () -> createBurnableItem(ExNihiloBlocks.CRIMSON_BARREL.get()));
    public static final RegistryObject<Item> BARREL_WARPED = ITEMS
            .register(Blocks.WARPED_BARREL, () -> createBurnableItem(ExNihiloBlocks.WARPED_BARREL.get()));
    public static final RegistryObject<Item> BARREL_STONE = ITEMS
            .register(Blocks.STONE_BARREL, () -> new BlockItem(ExNihiloBlocks.STONE_BARREL.get(), tab));
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
            ore.setPieceItem(ITEMS.register(ore.getPieceName(), () -> new OreItem(ore)));
            if (!ore.isVanilla()) {
                ore.setRawOreItem(ITEMS.register(ore.getRawOreName(), () -> new OreItem(ore)));
                if (ore.shouldGenerateIngot()) {
                    RegistryObject<OreItem> item = ITEMS.register(ore.getIngotName(), () -> new OreItem(ore));
                    ore.setIngotRegistryItem(item);
                    ingotMap.put(ore, item);
                }
            }
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
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return 400;
            }
        };
    }
}
