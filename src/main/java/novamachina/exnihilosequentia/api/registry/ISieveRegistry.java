package novamachina.exnihilosequentia.api.registry;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.api.compat.jei.JEISieveRecipe;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;

import java.util.List;

public interface ISieveRegistry {
    void clearRecipes();

    List<SieveRecipe> getDrops(ItemLike input, EnumMesh meshType, boolean isWaterlogged);

    List<JEISieveRecipe> getDryRecipeList();

    List<JEISieveRecipe> getWetRecipeList();

    boolean isBlockSiftable(Block block, EnumMesh mesh, boolean isWaterlogged);

    void setRecipes(List<SieveRecipe> recipes);
}
