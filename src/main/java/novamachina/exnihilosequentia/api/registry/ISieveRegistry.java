package novamachina.exnihilosequentia.api.registry;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.compat.jei.JEISieveRecipe;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;

public interface ISieveRegistry {
    void clearRecipes();

    List<SieveRecipe> getDrops(IItemProvider input, EnumMesh meshType, boolean isWaterlogged);

    List<JEISieveRecipe> getDryRecipeList();

    List<JEISieveRecipe> getWetRecipeList();

    boolean isBlockSiftable(Block block, EnumMesh mesh, boolean isWaterlogged);

    void setRecipes(List<SieveRecipe> recipes);
}
