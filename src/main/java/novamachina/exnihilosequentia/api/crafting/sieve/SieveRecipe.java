package novamachina.exnihilosequentia.api.crafting.sieve;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SieveRecipe extends SerializableRecipe {
    public static final RecipeType<SieveRecipe> RECIPE_TYPE = RecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":sieve");
    private static RegistryObject<IRecipeSerializer<SieveRecipe>> serializer;
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

    public static RegistryObject<IRecipeSerializer<SieveRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<IRecipeSerializer<SieveRecipe>> serializer) {
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

    @Nonnull
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
    protected IRecipeSerializer<SieveRecipe> getENSerializer() {
        return serializer.get();
    }
}
