package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;

import javax.annotation.Nonnull;

public interface ICompostRegistry {
    void clearRecipes();

    boolean containsSolid(@Nonnull final ItemLike item);

    @Nonnull List<CompostRecipe> getRecipeList();

    int getSolidAmount(@Nonnull final ItemLike item);

    void setRecipes(@Nonnull final List<CompostRecipe> recipes);
}
