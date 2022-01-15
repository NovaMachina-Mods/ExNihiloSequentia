package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;

import javax.annotation.Nonnull;

public interface ICrookRegistry {
    void clearRecipes();

    @Nonnull
    List<CrookRecipe> getDrops(@Nonnull final ItemLike block);

    @Nonnull
    List<CrookRecipe> getRecipeList();

    boolean isCrookable(@Nonnull final ItemLike block);

    void setRecipes(@Nonnull final List<CrookRecipe> recipes);
}
