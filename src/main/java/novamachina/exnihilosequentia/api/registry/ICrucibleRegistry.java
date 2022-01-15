package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ICrucibleRegistry {
    void clearRecipes();

    @Nonnull
    @SuppressWarnings("unused")
    CrucibleRecipe findRecipe(@Nonnull final ItemLike item);

    @Nullable
    CrucibleRecipe findRecipeByItemStack(@Nonnull final ItemStack itemStack);

    @Nonnull
    CrucibleRecipe findRecipeByItem(@Nonnull final Item item);

    @Nonnull
    List<CrucibleRecipe> getRecipeList();

    @SuppressWarnings("unused")
    boolean isMeltable(@Nonnull final ItemLike item, final int level);

    boolean isMeltableByItem(@Nonnull final Item item, final int level);

    boolean isMeltableByItemStack(@Nonnull final ItemStack item, final int level);

    void setRecipes(@Nonnull final List<CrucibleRecipe> recipes);
}
