package novamachina.exnihilosequentia.api.datagen;

import java.util.Objects;
import java.util.function.Consumer;


import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
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
import novamachina.exnihilosequentia.common.block.BaseBlock;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;

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

    protected ResourceLocation compostLoc(String id) {
        return new ResourceLocation(modId, "compost/" + prependRecipePrefix(id));
    }

    protected ResourceLocation crookLoc(String id) {
        return new ResourceLocation(modId, "crook/" + prependRecipePrefix(id));
    }

    protected ResourceLocation crucibleLoc(String id) {
        return new ResourceLocation(modId, "crucible/" + prependRecipePrefix(id));
    }

    protected ResourceLocation fluidItemLoc(String id) {
        return new ResourceLocation(modId, "fluid_item/" + prependRecipePrefix(id));
    }

    protected ResourceLocation fluidOnTopLoc(String id) {
        return new ResourceLocation(modId, "fluid_on_top/" + prependRecipePrefix(id));
    }

    protected ResourceLocation fluidTransformLoc(String id) {
        return new ResourceLocation(modId, "fluid_transform/" + prependRecipePrefix(id));
    }

    protected ResourceLocation hammerLoc(String id) {
        return new ResourceLocation(modId, "hammer/" + prependRecipePrefix(id));
    }

    protected ResourceLocation heatLoc(String id) {
        return new ResourceLocation(modId, "heat/" + prependRecipePrefix(id));
    }

    protected ResourceLocation sieveLoc(String id) {
        return new ResourceLocation(modId, "sieve/" + prependRecipePrefix(id));
    }

    protected void registerOre(EnumOre ore, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ore.getRawOreItem().get())
                .pattern("xx")
                .pattern("xx")
                .define('x', ore.getPieceItem().get())
                .group(this.modId)
                .unlockedBy("has_piece", InventoryChangeTrigger.TriggerInstance.hasItems(ore.getPieceItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getRawOreName())));
    }

    protected void registerSmelting(EnumOre ore, Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(ore.getRawOreItem().get()), ore.getIngotItem() != null ? ore.getIngotItem() : ore.getIngotRegistryItem().get(), 0.7F, 200)
                .unlockedBy(RAW_ORE_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(ore.getRawOreItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getIngotName())));
        SimpleCookingRecipeBuilder
                .blasting(Ingredient.of(ore.getRawOreItem().get()), ore.getIngotItem() != null ? ore.getIngotItem() : ore.getIngotRegistryItem().get(), 0.7F, 100)
                .unlockedBy(RAW_ORE_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(ore.getRawOreItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix("blast_" + ore.getIngotName())));
    }

    protected String prependRecipePrefix(String id) {
        return "ens_" + id;
    }

    protected ResourceLocation createSaveLocation(ResourceLocation location) {
        return new ResourceLocation(location.getNamespace(), prependRecipePrefix(location.getPath()));
    }
	
	public static void createMCCompost(Item item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    protected void createCompostRecipe(Consumer<FinishedRecipe> consumer, Item item, int amount, String string) {
        CompostRecipeBuilder.builder().input(item).amount(amount).build(consumer, compostLoc(string));
    }
    protected void createCompostRecipe(Consumer<FinishedRecipe> consumer, Block block, int amount, String string) {
        CompostRecipeBuilder.builder().input(block).amount(amount).build(consumer, compostLoc(string));
    }
    protected void createCompostRecipe(Consumer<FinishedRecipe> consumer, Tag.Named<Item> item, int amount, String string) {
        CompostRecipeBuilder.builder().input(item).amount(amount).build(consumer, compostLoc(string));
    }

    protected void createCrookRecipes(Consumer<FinishedRecipe> consumer, Tag.Named<Item> itemInput,
                                      Item itemDrop, float chance, String id) {
        CrookRecipeBuilder.builder().input(itemInput).addDrop(itemDrop, chance).build(consumer, crookLoc(id));
    }

    protected void createFiredCrucibleRecipes(Consumer<FinishedRecipe> consumer, Block block, int amount, String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(block)).amount(amount).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc(id));
    }
    protected void createFiredCrucibleRecipes(Consumer<FinishedRecipe> consumer, Tag.Named<Item> item, int amount, String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc(id));
    }
    protected void createWaterCrucibleRecipes(Consumer<FinishedRecipe> consumer, Tag.Named<Item> item, int amount, String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.WATER)
                .crucibleType(CrucilbeTypeEnum.WOOD).build(consumer, crucibleLoc(id));
    }
    protected void createWaterCrucibleRecipes(Consumer<FinishedRecipe> consumer, Item item, int amount, String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.WATER)
                .crucibleType(CrucilbeTypeEnum.WOOD).build(consumer, crucibleLoc(id));
    }

    protected void createFluidItemRecipes(Consumer<FinishedRecipe> consumer, Fluid fluidInput, Item itemInput, Block blockOutput, String id) {
        FluidItemRecipeBuilder.builder().fluidInBarrel(fluidInput).input(itemInput).result(blockOutput.asItem()).build(consumer, fluidItemLoc(id));
    }
    protected void createFluidItemRecipes(Consumer<FinishedRecipe> consumer, Fluid fluidInput, Tags.IOptionalNamedTag<Item> itemInput, Block blockOutput, String id) {
        FluidItemRecipeBuilder.builder().fluidInBarrel(fluidInput).input(itemInput).result(blockOutput.asItem()).build(consumer, fluidItemLoc(id));
    }

    protected void createFluidOnTopRecipes(Consumer<FinishedRecipe> consumer, Fluid fluidInTank, Fluid fluidOnTop, Block blockOutput, String id) {
        FluidOnTopRecipeBuilder.builder().fluidInTank(fluidInTank).fluidOnTop(fluidOnTop).result(blockOutput.asItem())
                .build(consumer, fluidOnTopLoc(id));
    }

    protected void createFluidTransformRecipes(Consumer<FinishedRecipe> consumer, Fluid fluidInTank,
                                               Tag.Named<Item> catalyst, Fluid fluidResult, String id) {
        FluidTransformRecipeBuilder.builder().fluidInTank(fluidInTank).catalyst(Ingredient.of(catalyst))
                .result(fluidResult).build(consumer, fluidTransformLoc(id));
    }

    protected void createFluidTransformRecipes(Consumer<FinishedRecipe> consumer, Fluid fluidInTank,
                                               Item catalyst, Fluid fluidResult, String id) {
        FluidTransformRecipeBuilder.builder().fluidInTank(fluidInTank).catalyst(Ingredient.of(catalyst))
                .result(fluidResult).build(consumer, fluidTransformLoc(id));
    }

    protected void createHammerRecipes(Consumer<FinishedRecipe> consumer, Block blockInput, Block blockOutput, String id) {
        HammerRecipeBuilder.builder().input(Ingredient.of(blockInput)).addDrop(blockOutput.asItem()).build(consumer, hammerLoc(id));
    }

    protected void createHeatRecipes(Consumer<FinishedRecipe> consumer, Block block, int amount, String id) {
        HeatRecipeBuilder.builder().input(block).amount(amount).build(consumer, heatLoc(id));
    }

    protected void createSmeltingRecipe(Consumer<FinishedRecipe> consumer, Item input, Item output, float xpSmelt, int durationSmelt, float xpBlast, int durationBlast, String condition, ResourceLocation rl) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), output, xpSmelt, durationSmelt)
                .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(rl));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), output, xpBlast, durationBlast)
                .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(rl.toString() + "_blast")));
    }

    protected void createCookingRecipe(Consumer<FinishedRecipe> consumer, Item input, Item output, float xpCampfire, int durationCampfire, float xpSmoker, int durationSmoker, String condition, ResourceLocation rl) {
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(input), output, xpCampfire, durationCampfire, RecipeSerializer.CAMPFIRE_COOKING_RECIPE)
                .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(rl.toString() + "_from_campfire")));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(input), output, xpSmoker, durationSmoker, RecipeSerializer.SMOKING_RECIPE)
                .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(rl.toString() + "_from_smoker")));
    }

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

    protected void createCrook(Item result, Item input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern(" x")
                .pattern(" x")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(Objects.requireNonNull(result.getRegistryName())));
    }

    protected void createCrook(Item result, Tag.Named<Item> input, Consumer<FinishedRecipe> consumer) {
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

    protected void createHammer(Item output, Tag.Named<Item> input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output)
                .pattern(" x ")
                .pattern(" -x")
                .pattern("-  ")
                .define('x', input)
                .define('-', Tags.Items.RODS)
                .unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.RODS).build()))
                .unlockedBy(MATERIAL_CONDITION, has(input))
                .save(consumer, createSaveLocation(Objects.requireNonNull(output.getRegistryName())));
    }

    protected void createPebbleBlock(Block result, Item input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern("xx")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(result.getRegistryName()));
    }

    protected void createMesh(Item output, Item inputMesh, Tags.IOptionalNamedTag<Item> inputItem, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output)
                .pattern("i i")
                .pattern("imi")
                .pattern("i i")
                .define('i', inputItem)
                .define('m', inputMesh)
                .unlockedBy("has_mesh", InventoryChangeTrigger.TriggerInstance.hasItems(inputMesh))
                .save(consumer, createSaveLocation(Objects.requireNonNull(output.getRegistryName())));
    }

    protected void createMesh(Item output, Item inputMesh, Item inputItem, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output)
                .pattern("i i")
                .pattern("imi")
                .pattern("i i")
                .define('i', inputItem)
                .define('m', inputMesh)
                .unlockedBy("has_mesh", InventoryChangeTrigger.TriggerInstance.hasItems(inputMesh))
                .save(consumer, createSaveLocation(Objects.requireNonNull(output.getRegistryName())));
    }
}
