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
        return new ResourceLocation(modId, "compost/" + id);
    }

    protected ResourceLocation crookLoc(String id) {
        return new ResourceLocation(modId, "crook/" + id);
    }

    protected ResourceLocation crucibleLoc(String id) {
        return new ResourceLocation(modId, "crucible/" + id);
    }

    protected ResourceLocation fluidItemLoc(String id) {
        return new ResourceLocation(modId, "fluid_item/" + id);
    }

    protected ResourceLocation fluidOnTopLoc(String id) {
        return new ResourceLocation(modId, "fluid_on_top/" + id);
    }

    protected ResourceLocation fluidTransformLoc(String id) {
        return new ResourceLocation(modId, "fluid_transform/" + id);
    }

    protected ResourceLocation hammerLoc(String id) {
        return new ResourceLocation(modId, "hammer/" + id);
    }

    protected ResourceLocation heatLoc(String id) {
        return new ResourceLocation(modId, "heat/" + id);
    }

    protected void registerOre(EnumOre ore, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ore.getChunkItem().get())
                .patternLine("xx")
                .patternLine("xx")
                .key('x', ore.getPieceItem().get())
                .setGroup(this.modId)
                .addCriterion("has_piece", InventoryChangeTrigger.Instance.forItems(ore.getPieceItem().get()))
                .build(consumer, new ResourceLocation(modId, ore.getChunkName()));
    }

    protected void registerSmelting(EnumOre ore, Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder
                .smeltingRecipe(Ingredient.fromItems(ore.getChunkItem().get()), ore.getIngotItem() != null ? ore.getIngotItem() : ore.getIngotRegistryItem().get(), 0.7F, 200)
                .addCriterion(CHUNK_CONDITION, InventoryChangeTrigger.Instance.forItems(ore.getChunkItem().get()))
                .build(consumer, new ResourceLocation(modId, ore.getIngotName()));
    }

    protected ResourceLocation sieveLoc(String id) {
        return new ResourceLocation(modId, "sieve/" + id);
    }
}
