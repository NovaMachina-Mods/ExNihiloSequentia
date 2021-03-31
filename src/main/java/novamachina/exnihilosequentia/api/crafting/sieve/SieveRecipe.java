package novamachina.exnihilosequentia.api.crafting.sieve;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;

public class SieveRecipe extends SerializableRecipe {
    public static final IRecipeType<SieveRecipe> RECIPE_TYPE = IRecipeType
            .register(ModIds.EX_NIHILO_SEQUENTIA + ":sieve");
    private static RegistryObject<RecipeSerializer<SieveRecipe>> serializer;
    private ItemStack drop;
    private Ingredient input;
    private boolean isWaterlogged;
    private final ResourceLocation recipeId;
    private final List<MeshWithChance> rolls;

    public SieveRecipe(ResourceLocation id, Ingredient input, ItemStack drop, List<MeshWithChance> rolls, boolean isWaterlogged) {
        super(drop, RECIPE_TYPE, id);
        this.recipeId = id;
        this.input = input;
        this.drop = drop;
        this.rolls = rolls;
        this.isWaterlogged = isWaterlogged;
    }

    public static RegistryObject<RecipeSerializer<SieveRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<RecipeSerializer<SieveRecipe>> serializer) {
        SieveRecipe.serializer = serializer;
    }

    public void addRoll(String meshString, float chance) {
        EnumMesh mesh = EnumMesh.getMeshFromName(meshString);
        addRoll(mesh, chance);
    }

    public void addRoll(EnumMesh mesh, float chance) {
        this.rolls.add(new MeshWithChance(mesh, chance));
    }

    public SieveRecipe filterByMesh(EnumMesh meshType, boolean flattenRecipes) {
        List<MeshWithChance> possibleMeshes = new ArrayList<>();
        for (MeshWithChance mesh : rolls) {
            if (flattenRecipes) {
                if (mesh.getMesh().getId() <= meshType.getId()) {
                    possibleMeshes.add(mesh);
                }
            } else {
                if (mesh.getMesh().getId() == meshType.getId()) {
                    possibleMeshes.add(mesh);
                }
            }
        }
        return new SieveRecipe(recipeId, input, drop, possibleMeshes, isWaterlogged);
    }

    public ItemStack getDrop() {
        return drop.copy();
    }

    public void setDrop(ItemStack drop) {
        this.drop = drop;
    }

    public Ingredient getInput() {
        return input;
    }

    public void setInput(Ingredient input) {
        this.input = input;
    }

    public ResourceLocation getRecipeId() {
        return recipeId;
    }

    @Override
    public ItemStack getResultItem() {
        return drop.copy();
    }

    public List<MeshWithChance> getRolls() {
        return rolls;
    }

    public boolean isWaterlogged() {
        return isWaterlogged;
    }

    public void setWaterlogged() {
        this.isWaterlogged = true;
    }

    @Override
    public String toString() {
        return "SieveRecipe{" +
                "input=" + input +
                ", drop=" + drop +
                ", rolls=" + rolls +
                ", isWaterlogged=" + isWaterlogged +
                ", recipeId=" + recipeId +
                '}';
    }

    @Override
    protected RecipeSerializer<SieveRecipe> getENSerializer() {
        return serializer.get();
    }
}
