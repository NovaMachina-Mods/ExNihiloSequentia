package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.block.Block;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;

import javax.annotation.Nonnull;

public interface IHammerRegistry {
    void clearRecipes();

    @Nonnull
    HammerRecipe findRecipe(@Nonnull final Block block);

    @Nonnull
    List<HammerRecipe> getRecipeList();

    @Nonnull
    List<ItemStackWithChance> getResult(@Nonnull final Block input);

    boolean isHammerable(@Nonnull final Block block);

    void setRecipes(@Nonnull final List<HammerRecipe> recipes);
}
