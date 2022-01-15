package novamachina.exnihilosequentia.common.utility;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    @Nonnull public static final ForgeConfigSpec COMMON_CONFIG;
    @Nonnull private static final String CATEGORY_BARREL = "barrel";
    @Nonnull private static final String CATEGORY_CROOK = "crook";
    @Nonnull private static final String CATEGORY_CRUCIBLE = "crucible";
    @Nonnull private static final String CATEGORY_DEBUG = "debug";
    @Nonnull private static final String CATEGORY_INFESTED_LEAVES = "infested_leaves";
    @Nonnull private static final String CATEGORY_ORE = "ore";
    @Nonnull private static final String CATEGORY_SIEVE = "sieve";
    @Nonnull private static final String CATEGORY_DURABILITY = "durability";
    @Nonnull private static final String CATEGORY_PEBBLE = "pebble";
    @Nonnull private static final ForgeConfigSpec.Builder COMMON_BUILDER = new Builder();
    @Nonnull private static final String SUBCATEGORY_BARREL_COMPOST = "compost";
    @Nonnull private static final String SUBCATEGORY_BARREL_FLUID = "fluid_transform";
    @Nonnull private static final String SUBCATEGORY_BARREL_MOB = "mob_spawn";
    @Nonnull private static final String SUBCATEGORY_CRUCIBLE_WOOD = "wood";
    @Nonnull private static final String SUBCATEGORY_CROOKS = "crook";
    @Nonnull private static final String SUBCATEGORY_HAMMERS = "hammer";
    @Nonnull private static final String SUBCATEGORY_MESHES = "mesh";
    // Debugging
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableDebugLogging;
    // Pebble
    @Nonnull private static final ForgeConfigSpec.IntValue pebbleDamage;
    // Ore
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableOreOverride;
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableAluminum;
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableCopper;
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableGold;
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableIron;
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableLead;
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableNickel;
    @Nonnull private static final ForgeConfigSpec.BooleanValue enablePlatinum;
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableSilver;
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableTin;
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableUranium;
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableZinc;
    // Sieve
    @Nonnull private static final ForgeConfigSpec.BooleanValue flattenSieveRecipes;
    @Nonnull private static final ForgeConfigSpec.IntValue sieveRange;
    @Nonnull private static final ForgeConfigSpec.IntValue maxSieveClicks;
    @Nonnull private static final ForgeConfigSpec.BooleanValue netherSieveSoundsEnabled;
    // Crook
    @Nonnull private static final ForgeConfigSpec.IntValue maxBonusStringCount;
    @Nonnull private static final ForgeConfigSpec.IntValue minStringCount;
    @Nonnull private static final ForgeConfigSpec.IntValue secondsToCompost;
    @Nonnull private static final ForgeConfigSpec.IntValue secondsToFluidTransform;
    // Durability Hammer
    @Nonnull private static final ForgeConfigSpec.IntValue hammerWoodValue;
    @Nonnull private static final ForgeConfigSpec.IntValue hammerStoneValue;
    @Nonnull private static final ForgeConfigSpec.IntValue hammerIronValue;
    @Nonnull private static final ForgeConfigSpec.IntValue hammerGoldValue;
    @Nonnull private static final ForgeConfigSpec.IntValue hammerDiamondValue;
    @Nonnull private static final ForgeConfigSpec.IntValue hammerNetheriteValue;
    // Durability Crooks
    @Nonnull private static final ForgeConfigSpec.IntValue crookWoodValue;
    @Nonnull private static final ForgeConfigSpec.IntValue crookStoneValue;
    @Nonnull private static final ForgeConfigSpec.IntValue crookAndesiteValue;
    @Nonnull private static final ForgeConfigSpec.IntValue crookGraniteValue;
    @Nonnull private static final ForgeConfigSpec.IntValue crookDioriteValue;
    @Nonnull private static final ForgeConfigSpec.IntValue crookGoldValue;
    @Nonnull private static final ForgeConfigSpec.IntValue crookIronValue;
    @Nonnull private static final ForgeConfigSpec.IntValue crookDiamondValue;
    @Nonnull private static final ForgeConfigSpec.IntValue crookBoneValue;
    // Durability Meshes
    @Nonnull private static final ForgeConfigSpec.BooleanValue enableMeshDurability;
    @Nonnull private static final ForgeConfigSpec.IntValue meshStackSize;
    @Nonnull private static final ForgeConfigSpec.IntValue meshStringValue;
    @Nonnull private static final ForgeConfigSpec.IntValue meshFlintValue;
    @Nonnull private static final ForgeConfigSpec.IntValue meshIronValue;
    @Nonnull private static final ForgeConfigSpec.IntValue meshDiamondValue;
    @Nonnull private static final ForgeConfigSpec.IntValue meshNetheriteValue;
    @Nonnull private static final ForgeConfigSpec.IntValue meshEmeraldValue;
    // Barrel
    @Nonnull private static final ForgeConfigSpec.IntValue barrelMaxSolidAmount;
    @Nonnull private static final ForgeConfigSpec.IntValue barrelNumberOfBuckets;
    @Nonnull private static final ForgeConfigSpec.IntValue secondsToSpawn;
    @Nonnull private static final ForgeConfigSpec.IntValue rainFillAmount;
    @Nonnull private static final ForgeConfigSpec.BooleanValue showParticles;
    @Nonnull private static final ForgeConfigSpec.BooleanValue netherBarrelSoundsEnabled;
    // Infested Leaves
    @Nonnull private static final ForgeConfigSpec.IntValue secondsToTransformLeaves;
    @Nonnull private static final ForgeConfigSpec.DoubleValue spreadChance;
    // Crucible
    @Nonnull private static final ForgeConfigSpec.IntValue crucibleNumberOfBuckets;
    @Nonnull private static final ForgeConfigSpec.IntValue ticksBetweenMelts;
    @Nonnull private static final ForgeConfigSpec.IntValue ticksBetweenSpreadAttempt;
    @Nonnull private static final ForgeConfigSpec.IntValue vanillaSimulateDropCount;
    @Nonnull private static final ForgeConfigSpec.IntValue woodBarrelMaxTemp;
    @Nonnull private static final ForgeConfigSpec.IntValue woodHeatRate;
    @Nonnull private static final ForgeConfigSpec.BooleanValue netherCrucibleSoundsEnabled;

    static {
        //pebbleConfigs
        COMMON_BUILDER.comment("Pebble Configs").push(CATEGORY_PEBBLE);
        pebbleDamage = COMMON_BUILDER.comment("How much half hearts damage a pebble should do. (Default: 0)")
                .defineInRange("pebbleDamage", 0, 0, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        //barrelConfigs
        COMMON_BUILDER.comment("Barrel Configs").push(CATEGORY_BARREL);
        barrelNumberOfBuckets = COMMON_BUILDER.comment("Number of buckets the barrel will hold (Default: 1)")
                .defineInRange("barrelNumberOfBuckets", 1, 1, Integer.MAX_VALUE);
        rainFillAmount = COMMON_BUILDER.comment("How much fluid rain will fill per iteration (Default: 2)")
                .defineInRange("rainFillAmount", 2, 1, Integer.MAX_VALUE);
        woodBarrelMaxTemp = COMMON_BUILDER
                .comment("The max temperature a barrel can accept; water is 300 (Default: 300)")
                .defineInRange("woodBarrelMaxTemp", 300, 0, Integer.MAX_VALUE);
        showParticles = COMMON_BUILDER.comment("Should Ex Nihilo show any Patricle?")
                .define("showParticles", true);
        netherBarrelSoundsEnabled = COMMON_BUILDER.comment("Should nether barrels sound like nether wood? (Default: true)")
                .comment("If set to false, the blocks have same sounds as wooden barrels")
                .define("netherBarrelSoundsEnabled", true);

        COMMON_BUILDER.comment("Mob Spawn Configs").push(SUBCATEGORY_BARREL_MOB);
        secondsToSpawn = COMMON_BUILDER.comment("Number of seconds to spawn mobs (Default: 10)")
                .defineInRange("secondsToSpawnMobs", 10, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Compost Configs").push(SUBCATEGORY_BARREL_COMPOST);
        barrelMaxSolidAmount = COMMON_BUILDER
                .comment("How much solids need to be in barrel before composting starts (Default: 1000)")
                .defineInRange("maxSolidAmount", 1000, 1, Integer.MAX_VALUE);
        secondsToCompost = COMMON_BUILDER.comment("Number of seconds to compost (Default: 10)")
                .defineInRange("secondsToCompost", 10, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Fluid Transform Configs").push(SUBCATEGORY_BARREL_FLUID);
        secondsToFluidTransform = COMMON_BUILDER.comment("Number of seconds to transform fluids (Default: 10)")
                .defineInRange("secondsToTransformFluid", 10, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.pop();

        //crookConfigs
        COMMON_BUILDER.comment("Crook Configs").push(CATEGORY_CROOK);
        maxBonusStringCount = COMMON_BUILDER
                .comment("Maximum additional string that a crook will drop from infested leaves in addition to the minimum string count (Default: 3)")
                .defineInRange("maxBonusStringCount", 3, 0, Integer.MAX_VALUE);
        minStringCount = COMMON_BUILDER
                .comment("Minimum string that a crook will drop from infested leaves (Default: 2)")
                .defineInRange("minStringCount", 2, 1, Integer.MAX_VALUE);
        vanillaSimulateDropCount = COMMON_BUILDER
                .comment("Number of times the crook will \"break\" a leaf block to get drops (Default: 3)")
                .defineInRange("vanillaDropSimulateCount", 3, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        //crucibleConfigs
        COMMON_BUILDER.comment("Crucible Configs").push(CATEGORY_CRUCIBLE);
        ticksBetweenMelts = COMMON_BUILDER.comment("Ticks between melting operations (Default: 20)")
                .defineInRange("ticksBetweenMelts", 20, 1, Integer.MAX_VALUE);
        crucibleNumberOfBuckets = COMMON_BUILDER.comment("Number of buckets the crucible will hold (Default: 4)")
                .defineInRange("crucibleNumberOfBuckets", 4, 1, Integer.MAX_VALUE);
        netherCrucibleSoundsEnabled = COMMON_BUILDER.comment("Should nether crucibles sound like nether wood? (Default: true)")
                .comment("If set to false, the blocks have same sounds as wooden crucibles")
                .define("netherCrucibleSoundsEnabled", true);

        COMMON_BUILDER.comment("Wooden Crucible Configs").push(SUBCATEGORY_CRUCIBLE_WOOD);
        woodHeatRate = COMMON_BUILDER
                .comment("Heat rate the Wood Crucible will use regardless of heat source below (Default: 2)")
                .defineInRange("woodHeatRate", 2, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.pop();

        //infestedLeavesConfigs
        COMMON_BUILDER.comment("Infested Leaves Configs").push(CATEGORY_INFESTED_LEAVES);
        secondsToTransformLeaves = COMMON_BUILDER
                .comment("Number of seconds to for leaves to become completely infested (Default: 10)")
                .defineInRange("secondsToTransformLeaves", 10, 1, Integer.MAX_VALUE);
        spreadChance = COMMON_BUILDER.comment("Percentage of the time that infested leaves will spread (Default: 0.3)")
                .defineInRange("spreadChance", 0.3, 0.001, 1.0);
        ticksBetweenSpreadAttempt = COMMON_BUILDER
                .comment("Number of ticks between infested leave spread attempts (Default: 100)")
                .defineInRange("ticksBetweenSpreadAttempt", 100, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        //sieveConfigs
        COMMON_BUILDER.comment("Sieve Configs").push(CATEGORY_SIEVE);
        flattenSieveRecipes = COMMON_BUILDER
                .comment("Sieve will get results for all mesh tiers below the one in the sieve (Default: true)")
                .define("flattenSieveRecipes", true);
        sieveRange = COMMON_BUILDER
                .comment("Defines the radius that a sieve will attempt to activate other sieves (Default: 2)")
                .defineInRange("sieveRange", 2, 0, 5);
        enableMeshDurability = COMMON_BUILDER
                .comment("Meshes will have durability and can break, but don't stack. (Default: false)")
                .define("enableMeshDurability", false);
        meshStackSize = COMMON_BUILDER
                .comment("Meshes will stack, but don't have durability. (Default: 64)")
                .defineInRange("meshStackSize", 64, 1, 64);
        maxSieveClicks = COMMON_BUILDER
                .comment("The number of sieve clicks required to sieve a block. (Default: 10)")
                .defineInRange("maxSieveClicks", 10, 1, 10);
        netherSieveSoundsEnabled = COMMON_BUILDER.comment("Should nether sieves sound like nether wood? (Default: true)")
                .comment("If set to false, the blocks have same sounds as wooden sieves")
                .define("netherSieveSoundsEnabled", true);
        COMMON_BUILDER.pop();

        //oreConfigs
        COMMON_BUILDER.comment("Ore Configs").push(CATEGORY_ORE);
        enableOreOverride = COMMON_BUILDER.comment("Allows ores to be enabled or disabled by this config file. (Default: false)")
                .define("enableOreOverride", false);
        enableCopper = COMMON_BUILDER.comment("Enable copper ore pieces, chunks and ingots if they exist. 'enableOreOverride' must be true for this to work. (Default: true)")
                .define("enableCopper", true);
        enableLead = COMMON_BUILDER.comment("Enable lead ore pieces, chunks and ingots if they exist. 'enableOreOverride' must be true for this to work. (Default: true)")
                .define("enableLead", true);
        enableNickel = COMMON_BUILDER.comment("Enable nickel ore pieces, chunks and ingots if they exist. 'enableOreOverride' must be true for this to work. (Default: true)")
                .define("enableNickel", true);
        enableSilver = COMMON_BUILDER.comment("Enable silver ore pieces, chunks and ingots if they exist. 'enableOreOverride' must be true for this to work. (Default: true)")
                .define("enableSilver", true);
        enableTin = COMMON_BUILDER.comment("Enable tin ore pieces, chunks and ingots if they exist. 'enableOreOverride' must be true for this to work. (Default: true)")
                .define("enableTin", true);
        enableAluminum = COMMON_BUILDER.comment("Enable aluminum ore pieces, chunks and ingots if they exist. 'enableOreOverride' must be true for this to work. (Default: true)")
                .define("enableAluminum", true);
        enablePlatinum = COMMON_BUILDER.comment("Enable platinum ore pieces, chunks and ingots if they exist. 'enableOreOverride' must be true for this to work. (Default: true)")
                .define("enablePlatinum", true);
        enableUranium = COMMON_BUILDER.comment("Enable uranium ore pieces, chunks and ingots if they exist. 'enableOreOverride' must be true for this to work. (Default: true)")
                .define("enableUranium", true);
        enableZinc = COMMON_BUILDER.comment("Enable zinc ore pieces, chunks and ingots if they exist. 'enableOreOverride' must be true for this to work. (Default: true)")
                .define("enableZinc", true);
        enableIron = COMMON_BUILDER.comment("Enable iron ore pieces, chunks and ingots if they exist. 'enableOreOverride' must be true for this to work. (Default: true)")
                .define("enableIron", true);
        enableGold = COMMON_BUILDER.comment("Enable gold ore pieces, chunks and ingots if they exist. 'enableOreOverride' must be true for this to work. (Default: true)")
                .define("enableGold", true);
        COMMON_BUILDER.pop();

        //debugConfigs
        COMMON_BUILDER.comment("Debug Configs").push(CATEGORY_DEBUG);
        enableDebugLogging = COMMON_BUILDER.comment("Enable extra logging? (Default: false)")
                .define("enableDebugLogging", false);
        COMMON_BUILDER.pop();

        //durabilityConfigs
        COMMON_BUILDER.comment("Durability Configs").push(CATEGORY_DURABILITY);
        COMMON_BUILDER.comment("Durability of Hammers").push(SUBCATEGORY_HAMMERS);
        hammerWoodValue = COMMON_BUILDER.comment("Durability of Wooden Hammer (Default: 128)")
                .defineInRange("hammerWoodValue", 128, 1, Integer.MAX_VALUE);
        hammerStoneValue = COMMON_BUILDER.comment("Durability of Stone Hammer (Default: 256)")
                .defineInRange("hammerStoneValue", 256, 1, Integer.MAX_VALUE);
        hammerIronValue = COMMON_BUILDER.comment("Durability of Iron Hammer (Default: 512)")
                .defineInRange("hammerIronValue", 512, 1, Integer.MAX_VALUE);
        hammerGoldValue = COMMON_BUILDER.comment("Durability of Gold Hammer (Default: 64)")
                .defineInRange("hammerGoldValue", 64, 1, Integer.MAX_VALUE);
        hammerDiamondValue = COMMON_BUILDER.comment("Durability of Diamond Hammer (Default: 4096)")
                .defineInRange("hammerDiamondValue", 4096, 1, Integer.MAX_VALUE);
        hammerNetheriteValue = COMMON_BUILDER.comment("Durability of Netherite Hammer (Default: 8192)")
                .defineInRange("hammerNetheriteValue", 8192, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Durability of Crooks").push(SUBCATEGORY_CROOKS);
        crookWoodValue = COMMON_BUILDER.comment("Durability of Wooden Crook (Default: 128)")
                .defineInRange("crookWoodValue", 128, 1, Integer.MAX_VALUE);
        crookStoneValue = COMMON_BUILDER.comment("Durability of Stone Crook (Default: 256)")
                .defineInRange("crookStoneValue", 256, 1, Integer.MAX_VALUE);
        crookAndesiteValue = COMMON_BUILDER.comment("Durability of Andesite Crook (Default: 256)")
                .defineInRange("crookAndesiteValue", 256, 1, Integer.MAX_VALUE);
        crookGraniteValue = COMMON_BUILDER.comment("Durability of Granite Crook (Default: 256)")
                .defineInRange("crookGraniteValue", 256, 1, Integer.MAX_VALUE);
        crookDioriteValue = COMMON_BUILDER.comment("Durability of Diorite Crook (Default: 256)")
                .defineInRange("crookDioriteValue", 256, 1, Integer.MAX_VALUE);
        crookGoldValue = COMMON_BUILDER.comment("Durability of Gold Crook (Default: 32)")
                .defineInRange("crookGoldValue", 64, 1, Integer.MAX_VALUE);
        crookIronValue = COMMON_BUILDER.comment("Durability of Iron Crook (Default: 256)")
                .defineInRange("crookIronValue", 512, 1, Integer.MAX_VALUE);
        crookDiamondValue = COMMON_BUILDER.comment("Durability of Diamond Crook (Default: 2048)")
                .defineInRange("crookDiamondValue", 2048, 1, Integer.MAX_VALUE);
        crookBoneValue = COMMON_BUILDER.comment("Durability of Bone Crook (Default: 256)")
                .defineInRange("crookBoneValue", 256, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Durability of Meshes").push(SUBCATEGORY_MESHES);
        meshStringValue = COMMON_BUILDER.comment("Durability of String Mesh (Only useful if enableMeshDurability is true) (Default: 59)")
                .defineInRange("meshStringValue", 59, 1, Integer.MAX_VALUE);
        meshFlintValue = COMMON_BUILDER.comment("Durability of Flint Mesh (Only useful if enableMeshDurability is true) (Default: 131)")
                .defineInRange("meshFlintValue", 131, 1, Integer.MAX_VALUE);
        meshIronValue = COMMON_BUILDER.comment("Durability of Iron Mesh (Only useful if enableMeshDurability is true) (Default: 250)")
                .defineInRange("meshIronValue", 250, 1, Integer.MAX_VALUE);
        meshDiamondValue = COMMON_BUILDER.comment("Durability of Diamond Mesh (Only useful if enableMeshDurability is true) (Default: 1561)")
                .defineInRange("meshDiamondValue", 1561, 1, Integer.MAX_VALUE);
        meshEmeraldValue = COMMON_BUILDER.comment("Durability of Emerald Mesh (Only useful if enableMeshDurability is true) (Default: 1561)")
                .defineInRange("meshEmeraldValue", 1561, 1, Integer.MAX_VALUE);
        meshNetheriteValue = COMMON_BUILDER.comment("Durability of Netherite Mesh (Only useful if enableMeshDurability is true) (Default: 2031)")
                .defineInRange("meshNetheriteValue", 2031, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private Config() {
    }

    // Crook
    public static int getCrookWoodDurability() { return crookWoodValue.get(); }

    public static int getCrookStoneDurability() { return crookStoneValue.get(); }

    public static int getCrookAndesiteDurability() { return crookAndesiteValue.get(); }

    public static int getCrookGraniteDurability() { return crookGraniteValue.get(); }

    public static int getCrookDioriteDurability() { return crookDioriteValue.get(); }

    public static int getCrookGoldDurability() { return crookGoldValue.get(); }

    public static int getCrookIronDurability() { return crookIronValue.get(); }

    public static int getCrookDiamondDurability() { return crookDiamondValue.get(); }

    public static int getCrookBoneDurability() { return crookBoneValue.get(); }

    public static int getVanillaSimulateDropCount() { return vanillaSimulateDropCount.get(); }

    // Hammer
    public static int getHammerWoodDurability() { return hammerWoodValue.get(); }

    public static int getHammerStoneDurability() { return hammerStoneValue.get(); }

    public static int getHammerIronDurability() { return hammerIronValue.get(); }

    public static int getHammerGoldDurability() { return hammerGoldValue.get(); }

    public static int getHammerDiamondDurability() { return hammerDiamondValue.get(); }

    public static int getHammerNetheriteDurability() { return hammerNetheriteValue.get(); }

    // Mesh
    public static boolean enableMeshDurability() { return enableMeshDurability.get(); }

    public static int getMeshStackSize() { return meshStackSize.get(); }

    public static int getMeshStringValue() { return meshStringValue.get(); }

    public static int getMeshFlintValue() { return meshFlintValue.get(); }

    public static int getMeshIronValue() { return meshIronValue.get(); }

    public static int getMeshEmeraldValue() { return meshEmeraldValue.get(); }

    public static int getMeshDiamondValue() { return meshDiamondValue.get(); }

    public static int getMeshNetheriteValue() { return meshNetheriteValue.get(); }

    // Debug
    public static boolean enableDebugLogging() { return enableDebugLogging.get(); }

    // Ore
    public static boolean enableOreOverride() { return enableOreOverride.get(); }

    public static boolean enableAluminum() { return enableAluminum.get(); }

    public static boolean enableCopper() { return enableCopper.get(); }

    public static boolean enableIron() { return enableIron.get(); }

    public static boolean enableLead() { return enableLead.get(); }

    public static boolean enableNickel() { return enableNickel.get(); }

    public static boolean enablePlatinum() { return enablePlatinum.get(); }

    public static boolean enableSilver() { return enableSilver.get(); }

    public static boolean enableTin() { return enableTin.get(); }

    public static boolean enableUranium() { return enableUranium.get(); }

    public static boolean enableZinc() { return enableZinc.get(); }

    public static boolean enableGold() { return enableGold.get(); }

    // Sieve
    public static boolean flattenSieveRecipes() { return flattenSieveRecipes.get(); }

    public static int getSieveRange() { return sieveRange.get(); }

    public static int getMaxSieveClicks() { return maxSieveClicks.get(); }

    public static boolean getNetherSieveSoundsEnabled() { return netherSieveSoundsEnabled.get(); }

    // Crucible
    public static int getCrucibleNumberOfBuckets() { return crucibleNumberOfBuckets.get(); }

    public static int getTicksBetweenMelts() { return ticksBetweenMelts.get(); }

    public static int getWoodHeatRate() { return woodHeatRate.get(); }

    public static boolean getNetherCrucibleSoundsEnabled() { return netherCrucibleSoundsEnabled.get(); }

    // Barrel
    public static int getBarrelMaxSolidAmount() { return barrelMaxSolidAmount.get(); }

    public static int getBarrelNumberOfBuckets() { return barrelNumberOfBuckets.get(); }

    public static int getSecondsToCompost() { return secondsToCompost.get(); }

    public static int getSecondsToFluidTransform() { return secondsToFluidTransform.get(); }

    public static int getSecondsToSpawn() { return secondsToSpawn.get(); }

    public static int getRainFillAmount() { return rainFillAmount.get(); }

    public static int getWoodBarrelMaxTemp() { return woodBarrelMaxTemp.get(); }

    public static boolean getShowParticles() { return showParticles.get(); }

    public static boolean getNetherBarrelSoundsEnabled() { return netherBarrelSoundsEnabled.get(); }

    // Infested Leaves
    public static int getMaxBonusStringCount() { return maxBonusStringCount.get(); }

    public static int getMinStringCount() { return minStringCount.get(); }

    public static int getSecondsToTransformLeaves() { return secondsToTransformLeaves.get(); }

    public static double getSpreadChance() { return spreadChance.get(); }

    public static int getTicksBetweenSpreadAttempt() { return ticksBetweenSpreadAttempt.get(); }

    // Pebble
    public static int getPebbleDamage() { return pebbleDamage.get(); }

    public static void loadConfig(@Nonnull final ForgeConfigSpec spec, @Nonnull final Path path) {
        @Nonnull final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();

        configData.load();
        spec.setConfig(configData);
    }
}
