package novamachina.exnihilosequentia.common.api.crafting.sieve;

import novamachina.exnihilosequentia.common.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.common.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class SieveRecipe extends SerializableRecipe {
    public static IRecipeType<SieveRecipe> TYPE = IRecipeType.register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":sieve");
    public static RegistryObject<RecipeSerializer<SieveRecipe>> SERIALIZER;

    private final Ingredient input;
    private final ItemStack drop;
    private final List<MeshWithChance> rolls;
    private final boolean isWaterlogged;
    private final ResourceLocation recipeId;

    public SieveRecipe(ResourceLocation id, Ingredient input, ItemStack drop, List<MeshWithChance> rolls, boolean isWaterlogged) {
        super(drop, TYPE, id);
        this.recipeId = id;
        this.input = input;
        this.drop = drop;
        this.rolls = rolls;
        this.isWaterlogged = isWaterlogged;
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getDrop() {
        return drop;
    }

    public List<MeshWithChance> getRolls() {
        return rolls;
    }

    @Override
    protected RecipeSerializer getENSerializer() {
        return SERIALIZER.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return drop;
    }

    public boolean isWaterlogged() {
        return isWaterlogged;
    }

    public SieveRecipe filterByMesh(EnumMesh meshType, boolean flattenRecipes) {
        List<MeshWithChance> possibleMeshes = new ArrayList<>();
        for(MeshWithChance mesh : rolls) {
            if(flattenRecipes) {
                if(mesh.getMesh().getId() <= meshType.getId()) {
                    possibleMeshes.add(mesh);
                }
            } else {
                if(mesh.getMesh().getId() == meshType.getId()) {
                    possibleMeshes.add(mesh);
                }
            }
        }
        return new SieveRecipe(recipeId, input, drop, possibleMeshes, isWaterlogged);
    }
}
