package novamachina.exnihilosequentia.api.registry;

import net.minecraft.block.Block;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.api.compat.jei.JEISieveRecipe;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;

import java.util.List;

public interface ISieveRegistry {
    List<SieveRecipe> getDrops(IItemProvider input, EnumMesh meshType, boolean isWaterlogged);

    boolean isBlockSiftable(Block block, EnumMesh mesh, boolean isWaterlogged);

    List<JEISieveRecipe> getDryRecipeList();

    List<JEISieveRecipe> getWetRecipeList();

    void setRecipes(List<SieveRecipe> recipes);

    void clearRecipes();
}
