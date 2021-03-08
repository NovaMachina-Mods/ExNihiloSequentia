package novamachina.exnihilosequentia.api.datagen;

import java.util.function.Consumer;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;

public abstract class AbstractRecipeGenerator extends RecipeProvider {
    protected static final String CHUNK_CONDITION = "has_chunk";
    private final String modId;

    protected AbstractRecipeGenerator(DataGenerator generator, String modId) {
        super(generator);
        this.modId = modId;
    }

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
        ShapedRecipeBuilder.shapedRecipe(ore.getChunkItem().get())
                .patternLine("xx")
                .patternLine("xx")
                .key('x', ore.getPieceItem().get())
                .setGroup(this.modId)
                .addCriterion("has_piece", InventoryChangeTrigger.Instance.forItems(ore.getPieceItem().get()))
                .build(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getChunkName())));
    }

    protected void registerSmelting(EnumOre ore, Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder
                .smeltingRecipe(Ingredient.fromItems(ore.getChunkItem().get()), ore.getIngotItem() != null ? ore.getIngotItem() : ore.getIngotRegistryItem().get(), 0.7F, 200)
                .addCriterion(CHUNK_CONDITION, InventoryChangeTrigger.Instance.forItems(ore.getChunkItem().get()))
                .build(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getIngotName())));
        CookingRecipeBuilder
                .blastingRecipe(Ingredient.fromItems(ore.getChunkItem().get()), ore.getIngotItem() != null ? ore.getIngotItem() : ore.getIngotRegistryItem().get(), 0.7F, 200)
                .addCriterion(CHUNK_CONDITION, InventoryChangeTrigger.Instance.forItems(ore.getChunkItem().get()))
                .build(consumer, new ResourceLocation(modId, prependRecipePrefix(ore.getIngotName())));
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
}
