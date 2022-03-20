package novamachina.exnihilosequentia.api.datagen;

import java.util.Objects;
import java.util.function.Consumer;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;
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
import novamachina.exnihilosequentia.common.item.ore.OreItem;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class AbstractRecipeGenerator extends RecipeProvider {
    @Nonnull protected static final String CHUNK_CONDITION = "has_chunk";
    @Nonnull public static final String PEBBLE_CONDITION = "has_pebble";
    @Nonnull private static final String MATERIAL_CONDITION = "has_material";
    @Nonnull private final String modId;

    protected AbstractRecipeGenerator(@Nonnull final DataGenerator generator, @Nonnull final String modId) {
        super(generator);
        this.modId = modId;
    }

	@Nonnull
    @Override
    public String getName() {
        return "Recipes: " + modId;
    }

    /**
     * @param id how should the compost recipe be named
     * @return returns compost recipe as resource location
     */
    @Nonnull
    protected ResourceLocation compostLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "compost/" + prependRecipePrefix(id));
    }

    /**
     * @param id how should the crook be named
     * @return returns crook as resource location
     */
    @Nonnull
    protected ResourceLocation crookLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "crook/" + prependRecipePrefix(id));
    }

    /**
     * @param id how should the crucible be named
     * @return returns crucible as resource location
     */
    @Nonnull
    protected ResourceLocation crucibleLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "crucible/" + prependRecipePrefix(id));
    }

    /**
     * @param id how should the fluid item to block recipe be named
     * @return returns fluid item to block recipe as resource location
     */
    @Nonnull
    protected ResourceLocation fluidItemLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "fluid_item/" + prependRecipePrefix(id));
    }

    /**
     * @param id how should the fluid on top recipe be named
     * @return returns fluid on top recipe as resource location
     */
    @Nonnull
    protected ResourceLocation fluidOnTopLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "fluid_on_top/" + prependRecipePrefix(id));
    }

    /**
     * @param id how should the fluid transformation recipe be named
     * @return returns fluid transformation recipe as resource location
     */
    @Nonnull
    protected ResourceLocation fluidTransformLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "fluid_transform/" + prependRecipePrefix(id));
    }

    /**
     * @param id how should the hammer be named
     * @return returns hammer as resource location
     */
    @Nonnull
    protected ResourceLocation hammerLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "hammer/" + prependRecipePrefix(id));
    }

    /**
     * @param id how should the heat recipe be named
     * @return returns heat recipe as resource location
     */
    @Nonnull
    protected ResourceLocation heatLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "heat/" + prependRecipePrefix(id));
    }

    /**
     * @param id how should the sieve recipe be named
     * @return returns sieve recipe as resource location
     */
    @Nonnull
    protected ResourceLocation sieveLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "sieve/" + prependRecipePrefix(id));
    }

    /**
     * @param ore get ore for adding piece to chunk recipe
     * @param consumer get consumer
     */
    protected void registerOre(@Nonnull final EnumOre ore, @Nonnull final Consumer<FinishedRecipe> consumer) {
        @Nullable final RegistryObject<OreItem> chunk = ore.getChunkItem();
        @Nullable final RegistryObject<OreItem> piece = ore.getPieceItem();
        if (chunk == null || piece == null)
            return;
        ShapedRecipeBuilder.shaped(chunk.get())
                .pattern("xx")
                .pattern("xx")
                .define('x', piece.get())
                .group(this.modId)
                .unlockedBy("has_piece", InventoryChangeTrigger.TriggerInstance.hasItems(ore.getPieceItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getChunkName())));
    }

    /**
     * @param ore get ore for adding chunk to ingot recipe (smelting)
     * @param consumer get consumer
     */
    @SuppressWarnings("unused")
    protected void registerSmelting(@Nonnull final EnumOre ore, @Nonnull final Consumer<FinishedRecipe> consumer) {
        @Nullable final RegistryObject<OreItem> chunk = ore.getChunkItem();
        if (chunk == null)
            return;
        @Nullable Item ingot = ore.getIngotItem();
        @Nullable RegistryObject<OreItem> ingotRegistryItem = ore.getIngotRegistryItem();
        if (ingot == null) {
            if (ingotRegistryItem != null)
                ingot = ingotRegistryItem.get();
            if (ingot == null)
                return;
        }
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(chunk.get()), ingot, 0.7F, 200)
                .unlockedBy(CHUNK_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(ore.getChunkItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getIngotName())));
        SimpleCookingRecipeBuilder
                .blasting(Ingredient.of(chunk.get()), ingot, 0.7F, 100)
                .unlockedBy(CHUNK_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(ore.getChunkItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix("blast_" + ore.getIngotName())));
    }

    /**
     * @param id adds "ens_" to any other method (most used in resource location methods)
     * @return returns string for other method
     */
    protected String prependRecipePrefix(@Nonnull final String id) {
        return "ens_" + id;
    }

    /**
     * @param location how should the sieve recipe be named
     * @return returns save location
     */
    protected ResourceLocation createSaveLocation(@Nonnull final ResourceLocation location) {
        return new ResourceLocation(location.getNamespace(), prependRecipePrefix(location.getPath()));
    }

    /**
     * @param item adds item to minecraft's composter
     * @param chance chance to successfully add item to composter
     */
	public static void createMCCompost(@Nonnull final ItemLike item, final float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    /**
     * @param item get item for compost recipe
     * @param amount how much compost the inserted item should make
     * @param consumer get consumer
     * @param id name of the item to save to
     */
    protected void createCompostRecipe(@Nonnull final Consumer<FinishedRecipe> consumer, @Nonnull final Item item,
                                       final int amount, @Nonnull final String id) {
        CompostRecipeBuilder.builder().input(item).amount(amount).build(consumer, compostLoc(id));
    }

    /**
     * @param block get block for compost recipe
     * @param amount how much compost the inserted item should make
     * @param consumer get consumer
     * @param id name of the item to save to
     */
    @SuppressWarnings("SameParameterValue")
    protected void createCompostRecipe(@Nonnull final Consumer<FinishedRecipe> consumer, @Nonnull final Block block,
                                       final int amount, @Nonnull final String id) {
        CompostRecipeBuilder.builder().input(block).amount(amount).build(consumer, compostLoc(id));
    }

    /**
     * @param item get item tag for compost recipe
     * @param amount how much compost the inserted item should make
     * @param consumer get consumer
     * @param id name of the item to save to
     */
    protected void createCompostRecipe(@Nonnull final Consumer<FinishedRecipe> consumer,
                                       @Nonnull final TagKey<Item> item, final int amount,
                                       @Nonnull final String id) {
        CompostRecipeBuilder.builder().input(item).amount(amount).build(consumer, compostLoc(id));
    }

    /**
     * @param itemInput get item or block tag for crooking
     * @param itemDrop what item should drop by breaking with crook
     * @param chance chance to drop item
     * @param consumer get consumer
     * @param id name of the item to save to
     */
    @SuppressWarnings("SameParameterValue")
    protected void createCrookRecipes(@Nonnull final Consumer<FinishedRecipe> consumer,
                                      @Nonnull final TagKey<Item> itemInput,
                                      @Nonnull final ItemLike itemDrop, final float chance,
                                      @Nonnull final String id) {
        CrookRecipeBuilder.builder().input(itemInput).addDrop(itemDrop, chance).build(consumer, crookLoc(id));
    }

    /**
     * @param itemInput get block for crooking
     * @param itemDrop what item should drop by breaking with crook
     * @param chance chance to drop item
     * @param consumer get consumer
     * @param id name of the item to save to
     */
    protected void createCrookRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, @Nonnull final Block itemInput,
                                      @Nonnull final ItemLike itemDrop, final float chance,
                                      @Nonnull final String id) {
        CrookRecipeBuilder.builder().input(itemInput).addDrop(itemDrop, chance).build(consumer, crookLoc(id));
    }

    /**
     * @param block get block to add to crucible
     * @param amount amount for fluid to create
     * @param consumer get consumer
     * @param id name of the block to save to
     */
    protected void createFiredCrucibleRecipes(@Nonnull final Consumer<FinishedRecipe> consumer,
                                              @Nonnull final Block block, final int amount, @Nonnull final String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(block)).amount(amount).fluidResult(Fluids.LAVA)
                .crucibleType(CrucibleTypeEnum.FIRED).build(consumer, crucibleLoc(id));
    }

    /**
     * @param item get item tag to add to crucible
     * @param amount amount for fluid to create
     * @param consumer get consumer
     * @param id name of the block to save to
     */
    @SuppressWarnings("unused")
    protected void createFiredCrucibleRecipes(@Nonnull final Consumer<FinishedRecipe> consumer,
                                              @Nonnull final TagKey<Item> item, final int amount,
                                              @Nonnull final String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.LAVA)
                .crucibleType(CrucibleTypeEnum.FIRED).build(consumer, crucibleLoc(id));
    }

    /**
     * @param item get item tag to add to crucible
     * @param amount amount for fluid to create
     * @param consumer get consumer
     * @param id name of the block to save to
     */
    @SuppressWarnings("SameParameterValue")
    protected void createWaterCrucibleRecipes(@Nonnull final Consumer<FinishedRecipe> consumer,
                                              @Nonnull final TagKey<Item> item, final int amount,
                                              @Nonnull final String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.WATER)
                .crucibleType(CrucibleTypeEnum.WOOD).build(consumer, crucibleLoc(id));
    }

    /**
     * @param item get block to add to crucible
     * @param amount amount for fluid to create
     * @param consumer get consumer
     * @param id name of the block to save to
     */
    @SuppressWarnings("unused")
    protected void createWaterCrucibleRecipes(@Nonnull final Consumer<FinishedRecipe> consumer,
                                              @Nonnull final Item item, final int amount, @Nonnull final String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.WATER)
                .crucibleType(CrucibleTypeEnum.WOOD).build(consumer, crucibleLoc(id));
    }

    /**
     * @param fluidInput get fluid to convert to block
     * @param itemInput get item to convert fluid to block
     * @param blockOutput get block that gets converted by fluid and item
     * @param consumer get consumer
     * @param id name of the block to save to
     */
    protected void createFluidItemRecipes(@Nonnull final Consumer<FinishedRecipe> consumer,
                                          @Nonnull final Fluid fluidInput, @Nonnull final Item itemInput,
                                          @Nonnull final Block blockOutput, @Nonnull final String id) {
        FluidItemRecipeBuilder.builder().fluidInBarrel(fluidInput).input(itemInput).result(blockOutput).build(consumer, fluidItemLoc(id));
    }

    /**
     * @param fluidInput get fluid to convert to block
     * @param itemInput get item tag to convert fluid to block
     * @param blockOutput get block that gets converted by fluid and item
     * @param consumer get consumer
     * @param id name of the block to save to
     */
    protected void createFluidItemRecipes(@Nonnull final Consumer<FinishedRecipe> consumer,
                                          @Nonnull final Fluid fluidInput,
                                          @Nonnull final TagKey<Item> itemInput,
                                          @Nonnull final Block blockOutput, @Nonnull final String id) {
        FluidItemRecipeBuilder.builder().fluidInBarrel(fluidInput).input(itemInput).result(blockOutput).build(consumer, fluidItemLoc(id));
    }

    /**
     * @param fluidInTank get fluid in tank to convert to block
     * @param fluidOnTop get fluid on top of tank to convert fluid to block
     * @param blockOutput get block that gets converted by fluid and fluid on top
     * @param consumer get consumer
     * @param id name of the block to save to
     */
    protected void createFluidOnTopRecipes(@Nonnull final Consumer<FinishedRecipe> consumer,
                                           @Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop,
                                           @Nonnull final Block blockOutput, @Nonnull final String id) {
        FluidOnTopRecipeBuilder.builder().fluidInTank(fluidInTank).fluidOnTop(fluidOnTop).result(blockOutput)
                .build(consumer, fluidOnTopLoc(id));
    }

    /**
     * @param fluidInTank get fluid to convert to new fluid
     * @param catalyst get item to convert fluid to new fluid
     * @param fluidResult get new fluid that gets converted by fluid and item
     * @param consumer get consumer
     * @param id name of the fluid to save to
     */
    protected void createFluidTransformRecipes(@Nonnull final Consumer<FinishedRecipe> consumer,
                                               @Nonnull final Fluid fluidInTank,
                                               @Nonnull final TagKey<Item> catalyst,
                                               @Nonnull final Fluid fluidResult, @Nonnull final String id) {
        FluidTransformRecipeBuilder.builder().fluidInTank(fluidInTank).catalyst(Ingredient.of(catalyst))
                .result(fluidResult).build(consumer, fluidTransformLoc(id));
    }

    /**
     * @param fluidInTank get fluid to convert to block
     * @param catalyst get item to convert fluid to block
     * @param fluidResult get block that got converted by fluid and item
     * @param consumer get consumer
     * @param id name of the fluid to save to
     */
    protected void createFluidTransformRecipes(@Nonnull final Consumer<FinishedRecipe> consumer,
                                               @Nonnull final Fluid fluidInTank, @Nonnull final Item catalyst,
                                               @Nonnull final Fluid fluidResult, @Nonnull final String id) {
        FluidTransformRecipeBuilder.builder().fluidInTank(fluidInTank).catalyst(Ingredient.of(catalyst))
                .result(fluidResult).build(consumer, fluidTransformLoc(id));
    }

    /**
     * @param blockInput get block that should be broken by hammer
     * @param blockOutput get block dropped after breaking input by hammer
     * @param consumer get consumer
     * @param id name of the output block to save to
     */
    protected void createHammerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer,
                                       @Nonnull final Block blockInput, @Nonnull final Block blockOutput,
                                       @Nonnull final String id) {
        HammerRecipeBuilder.builder().input(blockInput).addDrop(blockOutput).build(consumer, hammerLoc(id));
    }

    /**
     * @param block get block that should give heat to crucible above
     * @param amount get amount of heat
     * @param consumer get consumer
     * @param id name of the block to save to
     */
    protected void createHeatRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, @Nonnull final Block block,
                                        final int amount, @Nonnull final String id) {
        HeatRecipeBuilder.builder().input(block).amount(amount).build(consumer, heatLoc(id));
    }

    /**
     * @param block get block that should give heat to crucible above
     * @param amount get amount of heat
     * @param consumer get consumer
     * @param id name of the block to save to
     */
    protected void createLitHeatRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, @Nonnull final Block block,
                                        final int amount, @Nonnull final String id) {
        StatePropertiesPredicate lit = StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.LIT, true).build();
        HeatRecipeBuilder.builder().input(block).amount(amount).properties(lit).build(consumer, heatLoc(id));
    }

    /**
     * @param input get item to smelt
     * @param output get smelted output item
     * @param xpSmelt get xp by smelting
     * @param durationSmelt get duration by smelting
     * @param xpBlast get xp by blasting
     * @param durationBlast get duration by blasting
     * @param condition get condition (no explanation yet)
     * @param consumer get consumer
     * @param id name of block to save to
     */
    @SuppressWarnings("SameParameterValue")
    protected void createSmeltingRecipe(@Nonnull final Consumer<FinishedRecipe> consumer, @Nonnull final Item input,
                                        @Nonnull final Item output, final float xpSmelt, final int durationSmelt,
                                        final float xpBlast, final int durationBlast, @Nonnull final String condition,
                                        @Nonnull final ResourceLocation id) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), output, xpSmelt, durationSmelt)
                .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(id));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), output, xpBlast, durationBlast)
                .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(id + "_blast")));
    }

    /**
     * @param input get item to smelt
     * @param output get smelted output item
     * @param xpCampfire get xp by campfire
     * @param durationCampfire get duration by campfire
     * @param xpSmoker get xp by smoker
     * @param durationSmoker get duration by smoker
     * @param condition get condition (no explanation yet)
     * @param consumer get consumer
     * @param id name of block to save to
     */
    @SuppressWarnings("SameParameterValue")
    protected void createCookingRecipe(@Nonnull final Consumer<FinishedRecipe> consumer, @Nonnull final Item input,
                                       @Nonnull final Item output, final float xpCampfire, final int durationCampfire,
                                       final float xpSmoker, final int durationSmoker, @Nonnull final String condition,
                                       @Nonnull final ResourceLocation id) {
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(input), output, xpCampfire, durationCampfire, RecipeSerializer.CAMPFIRE_COOKING_RECIPE)
                .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(id + "_from_campfire")));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(input), output, xpSmoker, durationSmoker, RecipeSerializer.SMOKING_RECIPE)
                .unlockedBy(condition, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(id + "_from_smoker")));
    }

    /**
     * @param barrel get barrel to create to
     * @param block get block to make barrel
     * @param slab get slab to make barrel
     * @param consumer get consumer
     */
    @SuppressWarnings("SameParameterValue")
    protected void createBarrel(@Nonnull final Consumer<FinishedRecipe> consumer,
                                @Nonnull final RegistryObject<BaseBlock> barrel,
                                @Nonnull final TagKey<Item> block, @Nonnull final Item slab) {
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
     * @param barrel get barrel to create to
     * @param block get block to make barrel
     * @param slab get slab to make barrel
     * @param consumer get consumer
     */
    protected void createBarrel(@Nonnull final Consumer<FinishedRecipe> consumer,
                                @Nonnull final RegistryObject<BaseBlock> barrel, @Nonnull final Item block,
                                @Nonnull final Item slab) {
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
     * @param crucible get crucible to create to
     * @param block get block to make crucible
     * @param slab get slab to make crucible
     * @param consumer get consumer
     */
    protected void createCrucible(@Nonnull final Consumer<FinishedRecipe> consumer,
                                  @Nonnull final RegistryObject<BaseBlock> crucible, @Nonnull final Item block,
                                  @Nonnull final Item slab) {
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
     * @param crucible get crucible to create to
     * @param block get block to make crucible
     * @param slab get slab to make crucible
     * @param consumer get consumer
     */
    @SuppressWarnings("unused")
    protected void createCrucible(@Nonnull final Consumer<FinishedRecipe> consumer,
                                  @Nonnull final RegistryObject<BaseBlock> crucible,
                                  @Nonnull final TagKey<Item> block, @Nonnull final TagKey<Item> slab) {
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
     * @param sieve get sieve to create to
     * @param block get block to make sieve
     * @param slab get slab to make sieve
     * @param consumer get consumer
     */
    protected void createSieve(@Nonnull final Consumer<FinishedRecipe> consumer,
                               @Nonnull final RegistryObject<?> sieve, @Nonnull final Item block,
                               @Nonnull final Item slab) {
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
     * @param crook get crook to create to
     * @param input get item to create crook
     * @param consumer get consumer
     */
    protected void createCrook(@Nonnull final Item crook, @Nonnull final Item input,
                               @Nonnull final Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(crook)
                .pattern("xx")
                .pattern(" x")
                .pattern(" x")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(Objects.requireNonNull(crook.getRegistryName())));
    }

    /**
     * @param crook get crook to create to
     * @param input get item tag to create crook
     * @param consumer get consumer
     */
    protected void createCrook(@Nonnull final Item crook, @Nonnull final TagKey<Item> input,
                               @Nonnull final Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(crook)
                .pattern("xx")
                .pattern(" x")
                .pattern(" x")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(input).build()))
                .save(consumer, createSaveLocation(Objects.requireNonNull(crook.getRegistryName())));
    }

    /**
     * @param hammer get crook to create to
     * @param input get item tag to create crook
     * @param consumer get consumer
     */
    protected void createHammer(@Nonnull final Item hammer, @Nonnull final TagKey<Item> input,
                                @Nonnull final Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(hammer)
                .pattern(" x ")
                .pattern(" -x")
                .pattern("-  ")
                .define('x', input)
                .define('-', Tags.Items.RODS)
                .unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.RODS).build()))
                .unlockedBy(MATERIAL_CONDITION, has(input))
                .save(consumer, createSaveLocation(Objects.requireNonNull(hammer.getRegistryName())));
    }

    /**
     * @param result get block to create to
     * @param input get pebble to create block
     * @param consumer get consumer
     */
    protected void createPebbleBlock(@Nonnull final Block result, @Nonnull final Item input,
                                     @Nonnull final Consumer<FinishedRecipe> consumer) {
        @Nullable final ResourceLocation resourceLocation = result.getRegistryName();
        if (resourceLocation == null)
            return;
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern("xx")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.TriggerInstance.hasItems(input))
                .save(consumer, createSaveLocation(resourceLocation));
    }

    /**
     * @param outputMesh get mesh to create to
     * @param inputMesh get mesh to create output mesh
     * @param inputItem get item tag to create output mesh
     * @param consumer get consumer
     */
    protected void createMesh(@Nonnull final Item outputMesh, @Nonnull final Item inputMesh,
                              @Nonnull final TagKey<Item> inputItem,
                              @Nonnull final Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(outputMesh)
                .pattern("i i")
                .pattern("imi")
                .pattern("i i")
                .define('i', inputItem)
                .define('m', inputMesh)
                .unlockedBy("has_mesh", InventoryChangeTrigger.TriggerInstance.hasItems(inputMesh))
                .save(consumer, createSaveLocation(Objects.requireNonNull(outputMesh.getRegistryName())));
    }

    /**
     * @param outputMesh get mesh to create to
     * @param inputMesh get mesh to create output mesh
     * @param inputItem get item to create output mesh
     * @param consumer get consumer
     */
    @SuppressWarnings("SameParameterValue")
    protected void createMesh(@Nonnull final Item outputMesh, @Nonnull final Item inputMesh,@Nonnull final Item inputItem,
                              @Nonnull final Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(outputMesh)
                .pattern("i i")
                .pattern("imi")
                .pattern("i i")
                .define('i', inputItem)
                .define('m', inputMesh)
                .unlockedBy("has_mesh", InventoryChangeTrigger.TriggerInstance.hasItems(inputMesh))
                .save(consumer, createSaveLocation(Objects.requireNonNull(outputMesh.getRegistryName())));
    }
}
