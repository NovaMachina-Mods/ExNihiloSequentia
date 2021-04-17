package novamachina.exnihilosequentia.api.datagen;

import java.util.function.Consumer;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.ComposterBlock;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;

import javax.annotation.Nonnull;

public abstract class AbstractRecipeGenerator extends RecipeProvider {
    protected static final String CHUNK_CONDITION = "has_chunk";
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

    protected void registerSmelting(EnumOre ore, Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder
                .smelting(Ingredient.of(ore.getChunkItem().get()), ore.getIngotItem() != null ? ore.getIngotItem() : ore.getIngotRegistryItem().get(), 0.7F, 200)
                .unlockedBy(CHUNK_CONDITION, InventoryChangeTrigger.Instance.hasItems(ore.getChunkItem().get()))
                .save(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getIngotName())));
        CookingRecipeBuilder
                .blasting(Ingredient.of(ore.getChunkItem().get()), ore.getIngotItem() != null ? ore.getIngotItem() : ore.getIngotRegistryItem().get(), 0.7F, 200)
                .unlockedBy(CHUNK_CONDITION, InventoryChangeTrigger.Instance.hasItems(ore.getChunkItem().get()))
                .save(consumer, new ResourceLocation(modId, "blast_" + prependRecipePrefix(ore.getIngotName())));
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
	
	public static void createMCCompost(IItemProvider item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }
}
