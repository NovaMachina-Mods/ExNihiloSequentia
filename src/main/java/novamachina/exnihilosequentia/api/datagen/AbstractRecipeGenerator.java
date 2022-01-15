package novamachina.exnihilosequentia.api.datagen;

import java.util.Objects;
import java.util.function.Consumer;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
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

    @Nonnull
    protected ResourceLocation compostLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "compost/" + prependRecipePrefix(id));
    }

    @Nonnull
    protected ResourceLocation crookLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "crook/" + prependRecipePrefix(id));
    }

    @Nonnull
    protected ResourceLocation crucibleLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "crucible/" + prependRecipePrefix(id));
    }

    @Nonnull
    protected ResourceLocation fluidItemLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "fluid_item/" + prependRecipePrefix(id));
    }

    @Nonnull
    protected ResourceLocation fluidOnTopLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "fluid_on_top/" + prependRecipePrefix(id));
    }

    @Nonnull
    protected ResourceLocation fluidTransformLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "fluid_transform/" + prependRecipePrefix(id));
    }

    @Nonnull
    protected ResourceLocation hammerLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "hammer/" + prependRecipePrefix(id));
    }

    @Nonnull
    protected ResourceLocation heatLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "heat/" + prependRecipePrefix(id));
    }

    @Nonnull
    protected ResourceLocation sieveLoc(@Nonnull final String id) {
        return new ResourceLocation(modId, "sieve/" + prependRecipePrefix(id));
    }

    protected void registerOre(@Nonnull final EnumOre ore, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        @Nullable final RegistryObject<OreItem> chunk = ore.getChunkItem();
        @Nullable final RegistryObject<OreItem> piece = ore.getPieceItem();
        if (chunk == null || piece == null)
            return;
        ShapedRecipeBuilder.shaped(chunk.get())
                .pattern("xx")
                .pattern("xx")
                .define('x', piece.get())
                .group(this.modId)
                .unlockedBy("has_piece", InventoryChangeTrigger.Instance.hasItems(ore.getPieceItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getChunkName())));
    }

    @SuppressWarnings("unused")
    protected void registerSmelting(@Nonnull final EnumOre ore, @Nonnull final Consumer<IFinishedRecipe> consumer) {
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
        CookingRecipeBuilder
                .smelting(Ingredient.of(chunk.get()), ingot, 0.7F, 200)
                .unlockedBy(CHUNK_CONDITION, InventoryChangeTrigger.Instance.hasItems(ore.getChunkItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getIngotName())));
        CookingRecipeBuilder
                .blasting(Ingredient.of(chunk.get()), ingot, 0.7F, 100)
                .unlockedBy(CHUNK_CONDITION, InventoryChangeTrigger.Instance.hasItems(ore.getChunkItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix("blast_" + ore.getIngotName())));
    }

    protected String prependRecipePrefix(@Nonnull final String id) {
        return "ens_" + id;
    }

    protected ResourceLocation createSaveLocation(@Nonnull final ResourceLocation location) {
        return new ResourceLocation(location.getNamespace(), prependRecipePrefix(location.getPath()));
    }
	
	public static void createMCCompost(@Nonnull final IItemProvider item, final float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    protected void createCompostRecipe(@Nonnull final Consumer<IFinishedRecipe> consumer, @Nonnull final Item item,
                                       final int amount, @Nonnull final String string) {
        CompostRecipeBuilder.builder().input(item).amount(amount).build(consumer, compostLoc(string));
    }
    @SuppressWarnings("SameParameterValue")
    protected void createCompostRecipe(@Nonnull final Consumer<IFinishedRecipe> consumer, @Nonnull final Block block,
                                       final int amount, @Nonnull final String string) {
        CompostRecipeBuilder.builder().input(block).amount(amount).build(consumer, compostLoc(string));
    }
    protected void createCompostRecipe(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                       @Nonnull final ITag.INamedTag<Item> item, final int amount,
                                       @Nonnull final String string) {
        CompostRecipeBuilder.builder().input(item).amount(amount).build(consumer, compostLoc(string));
    }
    @SuppressWarnings("SameParameterValue")
    protected void createCrookRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                      @Nonnull final ITag.INamedTag<Item> itemInput,
                                      @Nonnull final IItemProvider itemDrop, final float chance,
                                      @Nonnull final String id) {
        CrookRecipeBuilder.builder().input(itemInput).addDrop(itemDrop, chance).build(consumer, crookLoc(id));
    }

    protected void createCrookRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer, @Nonnull final Block itemInput,
                                      @Nonnull final IItemProvider itemDrop, final float chance,
                                      @Nonnull final String id) {
        CrookRecipeBuilder.builder().input(itemInput).addDrop(itemDrop, chance).build(consumer, crookLoc(id));
    }

    protected void createFiredCrucibleRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                              @Nonnull final Block block, final int amount, @Nonnull final String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(block)).amount(amount).fluidResult(Fluids.LAVA)
                .crucibleType(CrucibleTypeEnum.FIRED).build(consumer, crucibleLoc(id));
    }
    @SuppressWarnings("unused")
    protected void createFiredCrucibleRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                              @Nonnull final ITag.INamedTag<Item> item, final int amount,
                                              @Nonnull final String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.LAVA)
                .crucibleType(CrucibleTypeEnum.FIRED).build(consumer, crucibleLoc(id));
    }
    @SuppressWarnings("SameParameterValue")
    protected void createWaterCrucibleRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                              @Nonnull final ITag.INamedTag<Item> item, final int amount,
                                              @Nonnull final String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.WATER)
                .crucibleType(CrucibleTypeEnum.WOOD).build(consumer, crucibleLoc(id));
    }
    @SuppressWarnings("unused")
    protected void createWaterCrucibleRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                              @Nonnull final Item item, final int amount, @Nonnull final String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.WATER)
                .crucibleType(CrucibleTypeEnum.WOOD).build(consumer, crucibleLoc(id));
    }

    protected void createFluidItemRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                          @Nonnull final Fluid fluidInput, @Nonnull final Item itemInput,
                                          @Nonnull final Block blockOutput, @Nonnull final String id) {
        FluidItemRecipeBuilder.builder().fluidInBarrel(fluidInput).input(itemInput).result(blockOutput).build(consumer, fluidItemLoc(id));
    }
    protected void createFluidItemRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                          @Nonnull final Fluid fluidInput,
                                          @Nonnull final Tags.IOptionalNamedTag<Item> itemInput,
                                          @Nonnull final Block blockOutput, @Nonnull final String id) {
        FluidItemRecipeBuilder.builder().fluidInBarrel(fluidInput).input(itemInput).result(blockOutput).build(consumer, fluidItemLoc(id));
    }

    protected void createFluidOnTopRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                           @Nonnull final Fluid fluidInTank, @Nonnull final Fluid fluidOnTop,
                                           @Nonnull final Block blockOutput, @Nonnull final String id) {
        FluidOnTopRecipeBuilder.builder().fluidInTank(fluidInTank).fluidOnTop(fluidOnTop).result(blockOutput)
                .build(consumer, fluidOnTopLoc(id));
    }

    protected void createFluidTransformRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                               @Nonnull final Fluid fluidInTank,
                                               @Nonnull final ITag.INamedTag<Item> catalyst,
                                               @Nonnull final Fluid fluidResult, @Nonnull final String id) {
        FluidTransformRecipeBuilder.builder().fluidInTank(fluidInTank).catalyst(Ingredient.of(catalyst))
                .result(fluidResult).build(consumer, fluidTransformLoc(id));
    }

    protected void createFluidTransformRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                               @Nonnull final Fluid fluidInTank, @Nonnull final Item catalyst,
                                               @Nonnull final Fluid fluidResult, @Nonnull final String id) {
        FluidTransformRecipeBuilder.builder().fluidInTank(fluidInTank).catalyst(Ingredient.of(catalyst))
                .result(fluidResult).build(consumer, fluidTransformLoc(id));
    }

    protected void createHammerRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                       @Nonnull final Block blockInput, @Nonnull final Block blockOutput,
                                       @Nonnull final String id) {
        HammerRecipeBuilder.builder().input(blockInput).addDrop(blockOutput).build(consumer, hammerLoc(id));
    }

    protected void createHeatRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer, @Nonnull final Block block,
                                     final int amount, @Nonnull final String id) {
        HeatRecipeBuilder.builder().input(block).amount(amount).build(consumer, heatLoc(id));
    }

    protected void createHeatRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer, @Nonnull final Block block,
                                     final int amount, @Nonnull final StatePropertiesPredicate properties,
                                     @Nonnull final String id) {
        HeatRecipeBuilder.builder().input(block).amount(amount).properties(properties).build(consumer, heatLoc(id));
    }

    @SuppressWarnings("SameParameterValue")
    protected void createSmeltingRecipe(@Nonnull final Consumer<IFinishedRecipe> consumer, @Nonnull final Item input,
                                        @Nonnull final Item output, final float xpSmelt, final int durationSmelt,
                                        final float xpBlast, final int durationBlast, @Nonnull final String condition,
                                        @Nonnull final ResourceLocation rl) {
        CookingRecipeBuilder.smelting(Ingredient.of(input), output, xpSmelt, durationSmelt)
                .unlockedBy(condition, InventoryChangeTrigger.Instance.hasItems(input))
                .save(consumer, createSaveLocation(rl));
        CookingRecipeBuilder.blasting(Ingredient.of(input), output, xpBlast, durationBlast)
                .unlockedBy(condition, InventoryChangeTrigger.Instance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(rl + "_blast")));
    }

    @SuppressWarnings("SameParameterValue")
    protected void createCookingRecipe(@Nonnull final Consumer<IFinishedRecipe> consumer, @Nonnull final Item input,
                                       @Nonnull final Item output, final float xpCampfire, final int durationCampfire,
                                       final float xpSmoker, final int durationSmoker, @Nonnull final String condition,
                                       @Nonnull final ResourceLocation rl) {
        CookingRecipeBuilder.cooking(Ingredient.of(input), output, xpCampfire, durationCampfire, IRecipeSerializer.CAMPFIRE_COOKING_RECIPE)
                .unlockedBy(condition, InventoryChangeTrigger.Instance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(rl + "_from_campfire")));
        CookingRecipeBuilder.cooking(Ingredient.of(input), output, xpSmoker, durationSmoker, IRecipeSerializer.SMOKING_RECIPE)
                .unlockedBy(condition, InventoryChangeTrigger.Instance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(rl + "_from_smoker")));
    }

    @SuppressWarnings("SameParameterValue")
    protected void createBarrel(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                @Nonnull final RegistryObject<BaseBlock> barrel,
                                @Nonnull final Tags.IOptionalNamedTag<Item> block, @Nonnull final Item slab) {
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

    protected void createBarrel(@Nonnull final Consumer<IFinishedRecipe> consumer,
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

    protected void createCrucible(@Nonnull final Consumer<IFinishedRecipe> consumer,
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

    @SuppressWarnings("unused")
    protected void createCrucible(@Nonnull final Consumer<IFinishedRecipe> consumer,
                                  @Nonnull final RegistryObject<BaseBlock> crucible,
                                  @Nonnull final ITag.INamedTag<Item> block, @Nonnull final ITag.INamedTag<Item> slab) {
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

    protected void createSieve(@Nonnull final Consumer<IFinishedRecipe> consumer,
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

    protected void createCrook(@Nonnull final Item result, @Nonnull final Item input,
                               @Nonnull final Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern(" x")
                .pattern(" x")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.Instance.hasItems(input))
                .save(consumer, createSaveLocation(Objects.requireNonNull(result.getRegistryName())));
    }

    protected void createCrook(@Nonnull final Item result, @Nonnull final ITag.INamedTag<Item> input,
                               @Nonnull final Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern(" x")
                .pattern(" x")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.Instance
                        .hasItems(ItemPredicate.Builder.item().of(input).build()))
                .save(consumer, createSaveLocation(Objects.requireNonNull(result.getRegistryName())));
    }

    protected void createHammer(@Nonnull final Item output, @Nonnull final ITag.INamedTag<Item> input,
                                @Nonnull final Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output)
                .pattern(" x ")
                .pattern(" -x")
                .pattern("-  ")
                .define('x', input)
                .define('-', Tags.Items.RODS)
                .unlockedBy("has_stick", InventoryChangeTrigger.Instance
                        .hasItems(ItemPredicate.Builder.item().of(Tags.Items.RODS).build()))
                .unlockedBy(MATERIAL_CONDITION, has(input))
                .save(consumer, createSaveLocation(Objects.requireNonNull(output.getRegistryName())));
    }

    protected void createPebbleBlock(@Nonnull final Block result, @Nonnull final Item input,
                                     @Nonnull final Consumer<IFinishedRecipe> consumer) {
        @Nullable final ResourceLocation resourceLocation = result.getRegistryName();
        if (resourceLocation == null)
            return;
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern("xx")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.Instance.hasItems(input))
                .save(consumer, createSaveLocation(resourceLocation));
    }

    protected void createMesh(@Nonnull final Item output, @Nonnull final Item inputMesh,
                              @Nonnull final Tags.IOptionalNamedTag<Item> inputItem,
                              @Nonnull final Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output)
                .pattern("i i")
                .pattern("imi")
                .pattern("i i")
                .define('i', inputItem)
                .define('m', inputMesh)
                .unlockedBy("has_mesh", InventoryChangeTrigger.Instance.hasItems(inputMesh))
                .save(consumer, createSaveLocation(Objects.requireNonNull(output.getRegistryName())));
    }

    @SuppressWarnings("SameParameterValue")
    protected void createMesh(@Nonnull final Item output, @Nonnull final Item inputMesh,@Nonnull final Item inputItem,
                              @Nonnull final Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output)
                .pattern("i i")
                .pattern("imi")
                .pattern("i i")
                .define('i', inputItem)
                .define('m', inputMesh)
                .unlockedBy("has_mesh", InventoryChangeTrigger.Instance.hasItems(inputMesh))
                .save(consumer, createSaveLocation(Objects.requireNonNull(output.getRegistryName())));
    }
}
