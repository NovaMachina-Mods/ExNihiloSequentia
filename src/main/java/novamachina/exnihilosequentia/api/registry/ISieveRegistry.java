package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.compat.jei.JEISieveRecipe;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;

import javax.annotation.Nonnull;

public interface ISieveRegistry {
    void clearRecipes();

    @Nonnull
    List<SieveRecipe> getDrops(@Nonnull final IItemProvider input, @Nonnull final EnumMesh meshType,
                               final boolean isWaterlogged);

    @Nonnull
    List<JEISieveRecipe> getDryRecipeList();

    @Nonnull
    List<JEISieveRecipe> getWetRecipeList();

    boolean isBlockSiftable(@Nonnull final Block block, @Nonnull final EnumMesh mesh, final boolean isWaterlogged);

    void setRecipes(@Nonnull final List<SieveRecipe> recipes);
}
