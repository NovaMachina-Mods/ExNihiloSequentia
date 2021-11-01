package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fmllegacy.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.fluiditem.FluidItemRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.fluidontop.FluidOnTopRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.fluidtransform.FluidTransformRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipeBuilder;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.block.BaseBlock;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucibleTypeEnum;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class AbstractRecipeGenerator extends RecipeProvider {
    protected static final String RAW_ORE_CONDITION = "has_raw_ore";
    public static final String PEBBLE_CONDITION = "has_pebble";
    private static final String MATERIAL_CONDITION = "has_material";
    private final String modId;

    protected AbstractRecipeGenerator(DataGenerator generator, String modId) {
        super(generator);
        this.modId = modId;
    }

	@Nonnull
    @Override
    public String getName() {
        return "Recipes: " + modId;
    }

    private ResourceLocation compostLoc(String id) {
        return new ResourceLocation(modId, "compost/" + prependRecipePrefix(id));
    }

    private ResourceLocation crookLoc(String id) {
        return new ResourceLocation(modId, "crook/" + prependRecipePrefix(id));
    }

    private ResourceLocation crucibleLoc(String id) {
        return new ResourceLocation(modId, "crucible/" + prependRecipePrefix(id));
    }

    private ResourceLocation fluidItemLoc(String id) {
        return new ResourceLocation(modId, "fluid_item/" + prependRecipePrefix(id));
    }

    private ResourceLocation fluidOnTopLoc(String id) {
        return new ResourceLocation(modId, "fluid_on_top/" + prependRecipePrefix(id));
    }

    private ResourceLocation fluidTransformLoc(String id) {
        return new ResourceLocation(modId, "fluid_transform/" + prependRecipePrefix(id));
    }

    private ResourceLocation hammerLoc(String id) {
        return new ResourceLocation(modId, "hammer/" + prependRecipePrefix(id));
    }

    private ResourceLocation heatLoc(String id) {
        return new ResourceLocation(modId, "heat/" + prependRecipePrefix(id));
    }

    private ResourceLocation sieveLoc(String id) {
        return new ResourceLocation(modId, "sieve/" + prependRecipePrefix(id));
    }

    /**
     * @param ore creates raw ore out of piece
     */
    protected void createRawOreRecipe(EnumOre ore, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ore.getRawOreItem().get())
                .pattern("xx")
                .pattern("xx")
                .define('x', ore.getPieceItem().get())
                .group(this.modId)
                .unlockedBy("has_piece", InventoryChangeTrigger.TriggerInstance.hasItems(ore.getPieceItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getRawOreName())));
    }

    protected void createVanillaOreRecipe(EnumOre ore, Consumer<FinishedRecipe> consumer) {
        Item result = switch (ore.getOreName()) {
            case "iron" -> Items.RAW_IRON;
            case "gold" -> Items.RAW_GOLD;
            default -> Items.RAW_COPPER;
        };
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern("xx")
                .define('x', ore.getPieceItem().get())
                .group(this.modId)
                .unlockedBy("has_piece", InventoryChangeTrigger.TriggerInstance.hasItems(ore.getPieceItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getRawOreName())));
    }

    private String prependRecipePrefix(String id) {
        return "ens_" + id;
    }

    protected ResourceLocation createSaveLocation(ResourceLocation location) {
        return new ResourceLocation(location.getNamespace(), prependRecipePrefix(location.getPath()));
    }

    /**
     * @param item item to put in mc composter
     * @param chance chance to successfully put item in mc composter
     */
	public static void createMCCompost(ItemLike item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    /**
     * @param item item as input
     * @param amount how much compost it should generate
     * @param string name of the recipe
     */
    protected void createCompostRecipe(Consumer<FinishedRecipe> consumer, Item item, int amount, String string) {
        CompostRecipeBuilder.builder().input(item).amount(amount).build(consumer, compostLoc(string));
    }

    /**
     * @param block item as input
     * @param amount how much compost it should generate
     * @param string name of the recipe
     */
    protected void createCompostRecipe(Consumer<FinishedRecipe> consumer, Block block, int amount, String string) {
        CompostRecipeBuilder.builder().input(block).amount(amount).build(consumer, compostLoc(string));
    }

    /**
     * @param item item as input
     * @param amount how much compost it should generate
     * @param string name of the recipe
     */
    protected void createCompostRecipe(Consumer<FinishedRecipe> consumer, Tag.Named<Item> item, int amount, String string) {
        CompostRecipeBuilder.builder().input(item).amount(amount).build(consumer, compostLoc(string));
    }

    /**
     * @param itemInput item as input
     * @param itemDrop what should be dropped
     * @param chance chance to get dropped item
     * @param id name of the recipe
     */
    protected void createCrookRecipes(Consumer<FinishedRecipe> consumer, Tag.Named<Item> itemInput,
                                      ItemLike itemDrop, float chance, String id) {
        CrookRecipeBuilder.builder().input(itemInput).addDrop(itemDrop, chance).build(consumer, crookLoc(id));
    }

    /**
     * @param itemInput item as input
     * @param itemDrop what should be dropped
     * @param chance chance to get dropped item
     * @param id name of the recipe
     */
    protected void createCrookRecipes(Consumer<FinishedRecipe> consumer, Block itemInput,
                                      ItemLike itemDrop, float chance, String id) {
        CrookRecipeBuilder.builder().input(itemInput).addDrop(itemDrop, chance).build(consumer, crookLoc(id));
    }

    /**
     * @param block item as input
     * @param amount how much fluid should be generated
     * @param id name of the recipe
     */
    protected void createFiredCrucibleRecipes(Consumer<FinishedRecipe> consumer, Block block, int amount, String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(block)).amount(amount).fluidResult(Fluids.LAVA)
                .crucibleType(CrucibleTypeEnum.FIRED).build(consumer, crucibleLoc(id));
    }

    /**
     * @param item item as input
     * @param amount how much fluid should be generated
     * @param id name of the recipe
     */
    protected void createFiredCrucibleRecipes(Consumer<FinishedRecipe> consumer, Tag.Named<Item> item, int amount, String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.LAVA)
                .crucibleType(CrucibleTypeEnum.FIRED).build(consumer, crucibleLoc(id));
    }

    /**
     * @param item item as input
     * @param amount how much fluid should be generated
     * @param id name of the recipe
     */
    protected void createWaterCrucibleRecipes(Consumer<FinishedRecipe> consumer, Tag.Named<Item> item, int amount, String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.WATER)
                .crucibleType(CrucibleTypeEnum.WOOD).build(consumer, crucibleLoc(id));
    }

    /**
     * @param item item as input
     * @param amount how much fluid should be generated
     * @param id name of the recipe
     */
    protected void createWaterCrucibleRecipes(Consumer<FinishedRecipe> consumer, Item item, int amount, String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.WATER)
                .crucibleType(CrucibleTypeEnum.WOOD).build(consumer, crucibleLoc(id));
    }

    /**
     * @param fluidInput fluid as input
     * @param itemInput item as input
     * @param blockOutput block that generates out of fuildInput and itemInput
     * @param id name of the recipe
     */
    protected void createFluidItemRecipes(Consumer<FinishedRecipe> consumer, Fluid fluidInput, Item itemInput, Block blockOutput, String id) {
        FluidItemRecipeBuilder.builder().fluidInBarrel(fluidInput).input(itemInput).result(blockOutput).build(consumer, fluidItemLoc(id));
    }

    /**
     * @param fluidInput fluid as input in barrel
     * @param itemInput item as input in barrel
     * @param blockOutput block that generates out of fluidInput and itemInput
     * @param id name of the recipe
     */
    protected void createFluidItemRecipes(Consumer<FinishedRecipe> consumer, Fluid fluidInput, Tags.IOptionalNamedTag<Item> itemInput, Block blockOutput, String id) {
        FluidItemRecipeBuilder.builder().fluidInBarrel(fluidInput).input(itemInput).result(blockOutput).build(consumer, fluidItemLoc(id));
    }

    /**
     * @param fluidInTank fluid as input in barrel
     * @param fluidOnTop fluid on top of barrel
     * @param blockOutput block that generates out of fluidInTank and fluidOnTop
     * @param id name of the recipe
     */
    protected void createFluidOnTopRecipes(Consumer<FinishedRecipe> consumer, Fluid fluidInTank, Fluid fluidOnTop, Block blockOutput, String id) {
        FluidOnTopRecipeBuilder.builder().fluidInTank(fluidInTank).fluidOnTop(fluidOnTop).result(blockOutput)
                .build(consumer, fluidOnTopLoc(id));
    }

    /**
     * @param fluidInTank fluid as input in barrel
     * @param catalyst block underneath barrel
     * @param fluidResult fluid as result
     * @param id name of the recipe
     */
    protected void createFluidTransformRecipes(Consumer<FinishedRecipe> consumer, Fluid fluidInTank,
                                               Tag.Named<Item> catalyst, Fluid fluidResult, String id) {
        FluidTransformRecipeBuilder.builder().fluidInTank(fluidInTank).catalyst(Ingredient.of(catalyst))
                .result(fluidResult).build(consumer, fluidTransformLoc(id));
    }

    /**
     * @param fluidInTank fluid as input in barrel
     * @param catalyst block underneath barrel
     * @param fluidResult fluid as result
     * @param id name of the recipe
     */
    protected void createFluidTransformRecipes(Consumer<FinishedRecipe> consumer, Fluid fluidInTank,
                                               Item catalyst, Fluid fluidResult, String id) {
        FluidTransformRecipeBuilder.builder().fluidInTank(fluidInTank).catalyst(Ingredient.of(catalyst))
                .result(fluidResult).build(consumer, fluidTransformLoc(id));
    }

    /**
     * @param blockInput block that gets destroyed
     * @param blockOutput block that drops
     * @param id name of the recipe
     */
    protected void createHammerRecipes(Consumer<FinishedRecipe> consumer, Block blockInput, Block blockOutput, String id) {
        HammerRecipeBuilder.builder().input(blockInput).addDrop(blockOutput).build(consumer, hammerLoc(id));
    }

    /**
     * @param block block underneath crucible
     * @param amount heat of block
     * @param id name of the recipe
     */
    protected void createHeatRecipes(Consumer<FinishedRecipe> consumer, Block block, int amount, String id) {
        HeatRecipeBuilder.builder().input(block).amount(amount).build(consumer, heatLoc(id));
    }

    /**
     * @param block block underneath crucible
     * @param amount heat of block
     * @param properties is the block lit or not?
     * @param id name of the recipe
     */
    protected void createHeatRecipes(Consumer<FinishedRecipe> consumer, Block block, int amount, StatePropertiesPredicate properties, String id) {
        HeatRecipeBuilder.builder().input(block).amount(amount).properties(properties).build(consumer, heatLoc(id));
    }

    /**
     * @param input item as input
     * @param output item generated out of input item
     * @param xpSmelt xp generated when smelting
     * @param durationSmelt duration of smelting
     * @param xpBlast xp generated when blasting
     * @param durationBlast duration of blasting
     * @param condition the condition
     * @param rl name of the recipe
     */
    protected void createSmeltingRecipe(Consumer<FinishedRecipe> consumer, Item input, Item output, float xpSmelt, int durationSmelt, float xpBlast, int durationBlast, String condition, ResourceLocation rl) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), output, xpSmelt, durationSmelt)
                .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(rl));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), output, xpBlast, durationBlast)
                .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(rl + "_from_blaster")));
    }

    /**
     * @param input item as input
     * @param output item generated out of input item
     * @param xpCampfire xp generated when using campfire
     * @param durationCampfire duration when using campfire
     * @param xpSmoker xp generated when using smoker
     * @param durationSmoker duration of smoker
     * @param condition the condition
     * @param rl name of the recipe
     */
    protected void createCookingRecipe(Consumer<FinishedRecipe> consumer, Item input, Item output, float xpCampfire, int durationCampfire, float xpSmoker, int durationSmoker, String condition, ResourceLocation rl) {
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(input), output, xpCampfire, durationCampfire, RecipeSerializer.CAMPFIRE_COOKING_RECIPE)
                .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(rl.toString() + "_from_campfire")));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(input), output, xpSmoker, durationSmoker, RecipeSerializer.SMOKING_RECIPE)
                .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(rl + "_from_smoker")));
    }

    /**
     * @param input item that should be sieved
     * @param output item that should be dropped
     * @param id name of the recipe
     * @param outputChances use multiple chances to get item 
     * (Best use {@link AbstractRecipeGenerator#stringChance(float)}, {@link AbstractRecipeGenerator#flintChance(float)}, {@link AbstractRecipeGenerator#ironChance(float)}, {@link AbstractRecipeGenerator#diamondChance(float)}, {@link AbstractRecipeGenerator#emeraldChance(float)}, {@link AbstractRecipeGenerator#netheriteChance(float)} or use new {@link MeshWithChance#MeshWithChance(EnumMesh, float)})
     */
    protected void createSieveRecipe(Consumer<FinishedRecipe> consumer, Block input, Item output, String id, MeshWithChance... outputChances) {
        SieveRecipeBuilder.builder().input(Ingredient.of(input))
                .drop(output)
                .addRolls(outputChances)
                .build(consumer, sieveLoc(id));
    }

    /**
     * @param input item that should be sieved
     * @param output item that should be dropped
     * @param id name of the recipe
     * @param outputChances use multiple chances to get item
     * (Best use {@link AbstractRecipeGenerator#stringChance(float)}, {@link AbstractRecipeGenerator#flintChance(float)}, {@link AbstractRecipeGenerator#ironChance(float)}, {@link AbstractRecipeGenerator#diamondChance(float)}, {@link AbstractRecipeGenerator#emeraldChance(float)}, {@link AbstractRecipeGenerator#netheriteChance(float)} or use new {@link MeshWithChance#MeshWithChance(EnumMesh, float)})
     */
    protected void createSieveRecipe(Consumer<FinishedRecipe> consumer, Tag.Named<Item> input, Item output, String id, MeshWithChance... outputChances) {
        SieveRecipeBuilder.builder().input(Ingredient.of(input))
                .drop(output)
                .addRolls(outputChances)
                .build(consumer, sieveLoc(id));
    }

    /**
     * @param barrel barrel as output
     * @param block block to be used for barrel as wall
     * @param slab block to be used for barrel as base
     */
    protected void createBarrel(Consumer<FinishedRecipe> consumer, RegistryObject<BaseBlock> barrel, Tags.IOptionalNamedTag<Item> block, Item slab) {
        ShapedRecipeBuilder.shaped(barrel.get())
                .pattern("x x")
                .pattern("x x")
                .pattern("x-x")
                .define('x', block)
                .define('-', slab)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy("has_walls", has(block))
                .unlockedBy("has_base", has(slab))
                .save(consumer, createSaveLocation(barrel.getId()));
    }

    /**
     * @param barrel barrel as output
     * @param block block to be used for barrel as wall
     * @param slab block to be used for barrel as base
     */
    protected void createBarrel(Consumer<FinishedRecipe> consumer, RegistryObject<BaseBlock> barrel, Item block, Item slab) {
        ShapedRecipeBuilder.shaped(barrel.get())
                .pattern("x x")
                .pattern("x x")
                .pattern("x-x")
                .define('x', block)
                .define('-', slab)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy("has_walls", has(block))
                .unlockedBy("has_base", has(slab))
                .save(consumer, createSaveLocation(barrel.getId()));
    }

    /**
     * @param crucible crucible as output
     * @param block block to be used for crucible as wall
     * @param slab block to be used for crucible as base
     */
    protected void createCrucible(Consumer<FinishedRecipe> consumer, RegistryObject<BaseBlock> crucible, Item block, Item slab) {
        ShapedRecipeBuilder.shaped(crucible.get())
                .pattern("c c")
                .pattern("clc")
                .pattern("s s")
                .define('c', block)
                .define('l', slab)
                .define('s', Tags.Items.RODS_WOODEN)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy("has_logs", has(block))
                .save(consumer, createSaveLocation(crucible.getId()));
    }

    /**
     * @param crucible crucible as output
     * @param block block to be used for crucible as wall
     * @param slab block to be used for crucible as base
     */
    protected void createCrucible(Consumer<FinishedRecipe> consumer, RegistryObject<BaseBlock> crucible, Tag.Named<Item> block, Tag.Named<Item> slab) {
        ShapedRecipeBuilder.shaped(crucible.get())
                .pattern("c c")
                .pattern("clc")
                .pattern("s s")
                .define('c', block)
                .define('l', slab)
                .define('s', Tags.Items.RODS_WOODEN)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy("has_logs", has(block))
                .save(consumer, createSaveLocation(crucible.getId()));
    }

    /**
     * @param sieve sieve as output
     * @param block block to be used for sieve as wall
     * @param slab block to be used for sieve as base
     */
    protected void createSieve(Consumer<FinishedRecipe> consumer, RegistryObject<?> sieve, Item block, Item slab) {
        ShapedRecipeBuilder.shaped((BlockSieve) sieve.get())
                .pattern("p p")
                .pattern("plp")
                .pattern("s s")
                .define('p', block)
                .define('l', slab)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_plank", has(block))
                .save(consumer, createSaveLocation(sieve.getId()));
    }

    /**
     * @param input item as input
     * @param result crook to be created
     */
    protected void createCrook(Consumer<FinishedRecipe> consumer, Item result, Item input) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern(" x")
                .pattern(" x")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(Objects.requireNonNull(result.getRegistryName())));
    }

    /**
     * @param input item as input
     * @param result crook to be created
     */
    protected void createCrook(Consumer<FinishedRecipe> consumer, Item result, Tag.Named<Item> input) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern(" x")
                .pattern(" x")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(input).build()))
                .save(consumer, createSaveLocation(Objects.requireNonNull(result.getRegistryName())));
    }

    /**
     * @param input item as input
     * @param result hammer to be created
     */
    protected void createHammer(Consumer<FinishedRecipe> consumer, Item result, Tag.Named<Item> input) {
        ShapedRecipeBuilder.shaped(result)
                .pattern(" x ")
                .pattern(" -x")
                .pattern("-  ")
                .define('x', input)
                .define('-', Tags.Items.RODS)
                .unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.RODS).build()))
                .unlockedBy(MATERIAL_CONDITION, has(input))
                .save(consumer, createSaveLocation(Objects.requireNonNull(result.getRegistryName())));
    }

    /**
     * @param input pebble as input
     * @param result pebble block to be created
     */
    protected void createPebbleBlock(Consumer<FinishedRecipe> consumer, Block result, Item input) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern("xx")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(Objects.requireNonNull(result.getRegistryName())));
    }

    /**
     * @param output upgraded mesh as result
     * @param inputMesh mesh as input
     * @param inputItem item to upgrade mesh
     */
    protected void createMesh(Consumer<FinishedRecipe> consumer, Item output, Item inputMesh, Tags.IOptionalNamedTag<Item> inputItem) {
        ShapedRecipeBuilder.shaped(output)
                .pattern("i i")
                .pattern("imi")
                .pattern("i i")
                .define('i', inputItem)
                .define('m', inputMesh)
                .unlockedBy("has_mesh", InventoryChangeTrigger.TriggerInstance.hasItems(inputMesh))
                .save(consumer, createSaveLocation(Objects.requireNonNull(output.getRegistryName())));
    }

    /**
     * @param output upgraded mesh as result
     * @param inputMesh mesh as input
     * @param inputItem item to upgrade mesh
     */
    protected void createMesh(Consumer<FinishedRecipe> consumer, Item output, Item inputMesh, Item inputItem) {
        ShapedRecipeBuilder.shaped(output)
                .pattern("i i")
                .pattern("imi")
                .pattern("i i")
                .define('i', inputItem)
                .define('m', inputMesh)
                .unlockedBy("has_mesh", InventoryChangeTrigger.TriggerInstance.hasItems(inputMesh))
                .save(consumer, createSaveLocation(Objects.requireNonNull(output.getRegistryName())));
    }

    /**
     * @param chance chance to get item out of string mesh (use 0 to definitly create 1 item)
     * @return used in {@link AbstractRecipeGenerator#createSieveRecipe(Consumer, Block, Item, String, MeshWithChance...)}
     */
    protected MeshWithChance stringChance(float chance) {
        if (chance == 0F) {
            chance = 1.0F;
        }
        return new MeshWithChance(EnumMesh.STRING, chance);
    }

    /**
     * @param chance chance to get item out of string mesh (use 0 to definitly create 1 item)
     * @return used in {@link AbstractRecipeGenerator#createSieveRecipe(Consumer, Block, Item, String, MeshWithChance...)}
     */
    protected MeshWithChance flintChance(float chance) {
        if (chance == 0F) {
            chance = 1.0F;
        }
        return new MeshWithChance(EnumMesh.FLINT, chance);
    }

    /**
     * @param chance chance to get item out of string mesh (use 0 to definitly create 1 item)
     * @return used in {@link AbstractRecipeGenerator#createSieveRecipe(Consumer, Block, Item, String, MeshWithChance...)}
     */
    protected MeshWithChance ironChance(float chance) {
        if (chance == 0F) {
            chance = 1.0F;
        }
        return new MeshWithChance(EnumMesh.IRON, chance);
    }

    /**
     * @param chance chance to get item out of string mesh (use 0 to definitly create 1 item)
     * @return used in {@link AbstractRecipeGenerator#createSieveRecipe(Consumer, Block, Item, String, MeshWithChance...)}
     */
    protected MeshWithChance diamondChance(float chance) {
        if (chance == 0F) {
            chance = 1.0F;
        }
        return new MeshWithChance(EnumMesh.DIAMOND, chance);
    }

    /**
     * @param chance chance to get item out of string mesh (use 0 to definitly create 1 item)
     * @return used in {@link AbstractRecipeGenerator#createSieveRecipe(Consumer, Block, Item, String, MeshWithChance...)}
     */
    protected MeshWithChance emeraldChance(float chance) {
        if (chance == 0F) {
            chance = 1.0F;
        }
        return new MeshWithChance(EnumMesh.EMERALD, chance);
    }

    /**
     * @param chance chance to get item out of string mesh (use 0 to definitly create 1 item)
     * @return used in {@link AbstractRecipeGenerator#createSieveRecipe(Consumer, Block, Item, String, MeshWithChance...)}
     */
    protected MeshWithChance netheriteChance(float chance) {
        if (chance == 0) {
            chance = 1.0F;
        }
        return new MeshWithChance(EnumMesh.NETHERITE, chance);
    }
}
