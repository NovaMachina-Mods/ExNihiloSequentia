package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.RecipeProvider;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractRecipeGenerator extends RecipeProvider {
    private final String modId;

    protected AbstractRecipeGenerator(DataGenerator generator, String modId) {
        super(generator);
        this.modId = modId;
    }

    protected ResourceLocation sieveLoc(String id) {
        return new ResourceLocation(modId, "sieve/" + id);
    }

    protected ResourceLocation heatLoc(String id) {
        return new ResourceLocation(modId, "heat/" + id);
    }

    protected ResourceLocation crucibleLoc(String id) {
        return new ResourceLocation(modId, "crucible/" + id);
    }

    protected ResourceLocation fluidTransformLoc(String id) {
        return new ResourceLocation(modId, "fluid_transform/" + id);
    }

    protected ResourceLocation fluidOnTopLoc(String id) {
        return new ResourceLocation(modId, "fluid_on_top/" + id);
    }

    protected ResourceLocation fluidItemLoc(String id) {
        return new ResourceLocation(modId, "fluid_item/" + id);
    }

    protected ResourceLocation compostLoc(String id) {
        return new ResourceLocation(modId, "compost/" + id);
    }

    protected ResourceLocation crookLoc(String id) {
        return new ResourceLocation(modId, "crook/" + id);
    }

    protected ResourceLocation hammerLoc(String id) {
        return new ResourceLocation(modId, "hammer/" + id);
    }

    @Override
    public String getName() {
        return "Recipes: " + modId;
    }
}
