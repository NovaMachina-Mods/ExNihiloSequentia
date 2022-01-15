package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;

import javax.annotation.Nonnull;

public interface ICompostRegistry {
    void clearRecipes();

    boolean containsSolid(@Nonnull final IItemProvider item);

    @Nonnull List<CompostRecipe> getRecipeList();

    int getSolidAmount(@Nonnull final IItemProvider item);

    void setRecipes(@Nonnull final List<CompostRecipe> recipes);
}
