package novamachina.exnihilosequentia.api.crafting.sieve;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SieveRecipe extends SerializableRecipe {
    @Nonnull public static final RecipeType<SieveRecipe> RECIPE_TYPE = RecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":sieve");
    @Nullable private static RegistryObject<ExNihiloRecipeSerializer<SieveRecipe>> serializer;
    @Nonnull private ItemStack drop;
    @Nonnull private Ingredient input;
    private boolean isWaterlogged;
    @Nonnull private final ResourceLocation recipeId;
    @Nonnull private final List<MeshWithChance> rolls;

    public SieveRecipe(@Nonnull final ResourceLocation id, @Nonnull final Ingredient input,
                       @Nonnull final ItemStack drop, @Nonnull final List<MeshWithChance> rolls,
                       boolean isWaterlogged) {
        super(drop, RECIPE_TYPE, id);
        this.recipeId = id;
        this.input = input;
        this.drop = drop;
        this.rolls = rolls;
        this.isWaterlogged = isWaterlogged;
    }

    @Nullable
    public static RegistryObject<ExNihiloRecipeSerializer<SieveRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(@Nonnull final RegistryObject<ExNihiloRecipeSerializer<SieveRecipe>> serializer) {
        SieveRecipe.serializer = serializer;
    }

    public void addRoll(@Nonnull final String meshString, final float chance) {
        @Nonnull final EnumMesh mesh = EnumMesh.getMeshFromName(meshString);
        addRoll(mesh, chance);
    }

    public void addRoll(@Nonnull final EnumMesh mesh, final float chance) {
        this.rolls.add(new MeshWithChance(mesh, chance));
    }

    @Nonnull
    public SieveRecipe filterByMesh(@Nonnull final EnumMesh meshType, final boolean flattenRecipes) {
        @Nonnull final List<MeshWithChance> possibleMeshes = new ArrayList<>();
        for (@Nonnull final MeshWithChance mesh : rolls) {
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

    @Nonnull
    public ItemStack getDrop() {
        return drop.copy();
    }

    public void setDrop(@Nonnull final ItemStack drop) {
        this.drop = drop;
    }

    @Nonnull
    public Ingredient getInput() {
        return input;
    }

    public void setInput(@Nonnull final Ingredient input) {
        this.input = input;
    }

    @Nonnull
    public ResourceLocation getRecipeId() {
        return recipeId;
    }

    @Override
    @Nonnull
    public ItemStack getResultItem() {
        return drop.copy();
    }

    @Nonnull
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
    @Nonnull
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
    @Nullable
    protected ExNihiloRecipeSerializer<SieveRecipe> getENSerializer() {
        if (serializer == null)
            return null;
        return serializer.get();
    }
}
