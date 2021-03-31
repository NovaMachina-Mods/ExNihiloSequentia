package novamachina.exnihilosequentia.api.datagen;

import java.util.Objects;
import java.util.function.Consumer;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Block;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipeBuilder;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipeBuilder;
import novamachina.exnihilosequentia.common.block.BaseBlock;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;

public abstract class AbstractRecipeGenerator extends RecipeProvider {
    protected static final String CHUNK_CONDITION = "has_chunk";
    private static final String PEBBLE_CONDITION = "has_pebble";
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

    protected void registerOre(EnumOre ore, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ore.getChunkItem().get())
                .pattern("xx")
                .pattern("xx")
                .define('x', ore.getPieceItem().get())
                .group(this.modId)
                .unlockedBy("has_piece", InventoryChangeTrigger.Instance.hasItems(ore.getPieceItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getChunkName())));
    }

    protected void createSmeltingRecipe(Consumer<IFinishedRecipe> consumer, Item input, Item output, float xpSmelt, int durationSmelt, float xpBlast, int durationBlast, String condition, ResourceLocation rl) {
        CookingRecipeBuilder.smelting(Ingredient.of(input), output, xpSmelt, durationSmelt)
                .unlockedBy(condition, InventoryChangeTrigger.Instance.hasItems(input))
                .save(consumer, createSaveLocation(rl));
        CookingRecipeBuilder.blasting(Ingredient.of(input), output, xpBlast, durationBlast)
                .unlockedBy(condition, InventoryChangeTrigger.Instance.hasItems(input))
                .save(consumer, createSaveLocation(new ResourceLocation(rl.toString() + "_blast")));
    }

    protected ResourceLocation sieveLoc(String id) {
        return new ResourceLocation(modId, "sieve/" + prependRecipePrefix(id));
    }

    protected String prependRecipePrefix(String id) {
        return "ens_" + id;
    }

    protected ResourceLocation createSaveLocation(ResourceLocation location) {
        return new ResourceLocation(location.getNamespace(), prependRecipePrefix(location.getPath()));
    }

    protected void createBarrel(Consumer<IFinishedRecipe> consumer, RegistryObject<BaseBlock> barrel, Item block, Item slab) {
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

    protected void createBarrel(Consumer<IFinishedRecipe> consumer, RegistryObject<BaseBlock> barrel, ITag.INamedTag<Item> block, Item slab) {
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

    protected void createCrucible(Consumer<IFinishedRecipe> consumer, RegistryObject<BaseBlock> crucible, Item block, Item slab) {
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

    protected void createSieve(Consumer<IFinishedRecipe> consumer, RegistryObject<BlockSieve> sieve, Item block, Item slab) {
        ShapedRecipeBuilder.shaped(sieve.get())
                .pattern("p p")
                .pattern("plp")
                .pattern("s s")
                .define('p', block)
                .define('l', slab)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_plank", has(block))
                .save(consumer, createSaveLocation(sieve.getId()));
    }

    protected void createCompostRecipe(Consumer<IFinishedRecipe> consumer, Item item, int amount, String string) {
        CompostRecipeBuilder.builder().input(item).amount(amount).build(consumer, compostLoc(string));
    }
    protected void createCompostRecipe(Consumer<IFinishedRecipe> consumer, Block block, int amount, String string) {
        CompostRecipeBuilder.builder().input(block).amount(amount).build(consumer, compostLoc(string));
    }
    protected void createCompostRecipe(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> item, int amount, String string) {
        CompostRecipeBuilder.builder().input(item).amount(amount).build(consumer, compostLoc(string));
    }

    protected void registerCrook(Item result, Item input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern(" x")
                .pattern(" x")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.Instance.hasItems(input))
                .save(consumer, createSaveLocation(result.getRegistryName()));
    }

    protected void registerCrook(Item result, ITag.INamedTag<Item> input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern(" x")
                .pattern(" x")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.Instance
                        .hasItems(ItemPredicate.Builder.item().of(input).build()))
                .save(consumer, createSaveLocation(result.getRegistryName()));
    }

    protected void createFiredCrucibleRecipes(Consumer<IFinishedRecipe> consumer, Block block, int amount, String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(block)).amount(amount).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc(id));
    }
    protected void createFiredCrucibleRecipes(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> item, int amount, String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.LAVA)
                .crucibleType(CrucilbeTypeEnum.FIRED).build(consumer, crucibleLoc(id));
    }
    protected void createWaterCrucibleRecipes(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> item, int amount, String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.WATER)
                .crucibleType(CrucilbeTypeEnum.WOOD).build(consumer, crucibleLoc(id));
    }
    protected void createWaterCrucibleRecipes(Consumer<IFinishedRecipe> consumer, Item item, int amount, String id) {
        CrucibleRecipeBuilder.builder().input(Ingredient.of(item)).amount(amount).fluidResult(Fluids.WATER)
                .crucibleType(CrucilbeTypeEnum.WOOD).build(consumer, crucibleLoc(id));
    }

    protected void registerHammer(Item output, ITag.INamedTag<Item> input, Consumer<IFinishedRecipe> consumer) {
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

    protected void registerMesh(Item output, Item inputMesh, ITag.INamedTag<Item> inputItem, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output)
                .pattern("i i")
                .pattern("imi")
                .pattern("i i")
                .define('i', inputItem)
                .define('m', inputMesh)
                .unlockedBy("has_mesh", InventoryChangeTrigger.Instance.hasItems(inputMesh))
                .save(consumer, createSaveLocation(output.getRegistryName()));
    }

    protected void registerMesh(Item output, Item inputMesh, Item inputItem, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output)
                .pattern("i i")
                .pattern("imi")
                .pattern("i i")
                .define('i', inputItem)
                .define('m', inputMesh)
                .unlockedBy("has_mesh", InventoryChangeTrigger.Instance.hasItems(inputMesh))
                .save(consumer, createSaveLocation(output.getRegistryName()));
    }

    protected void createHeatRecipes(Consumer<IFinishedRecipe> consumer, Block block, int amount, String id) {
        HeatRecipeBuilder.builder().input(block).amount(amount).build(consumer, heatLoc(id));
    }


    protected void registerPebbleBlock(Block result, Item input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("xx")
                .pattern("xx")
                .define('x', input)
                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
                .unlockedBy(PEBBLE_CONDITION, InventoryChangeTrigger.Instance.hasItems(input))
                .save(consumer, createSaveLocation(result.getRegistryName()));
    }
}
