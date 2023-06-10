package novamachina.exnihilosequentia.common.crafting.sieve;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.NovaRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SingleItemSerializableRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;
import org.jetbrains.annotations.NotNull;

public class SieveRecipe extends SingleItemSerializableRecipe {

  @Nonnull private final ResourceLocation recipeId;
  @Nonnull private final List<MeshWithChance> rolls;
  @Nonnull private ItemStack drop;
  private boolean isWaterlogged;

  public SieveRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final Ingredient input,
      @Nonnull final ItemStack drop,
      @Nonnull final List<MeshWithChance> rolls,
      boolean isWaterlogged) {
    super(drop, input, EXNRecipeTypes.SIEVE_RECIPE_TYPE, id);
    this.recipeId = id;
    this.drop = drop;
    this.rolls = rolls;
    this.isWaterlogged = isWaterlogged;
  }

  public void addRoll(@Nonnull final String meshString, final float chance) {
    @Nonnull final MeshType mesh = MeshType.valueOf(meshString);
    addRoll(mesh, chance);
  }

  public void addRoll(@Nonnull final MeshType mesh, final float chance) {
    this.rolls.add(new MeshWithChance(mesh, chance));
  }

  @Nonnull
  public SieveRecipe filterByMesh(@Nonnull final MeshType meshType, final boolean flattenRecipes) {
    @Nonnull final List<MeshWithChance> possibleMeshes = new ArrayList<>();
    for (@Nonnull final MeshWithChance mesh : rolls) {
      if (flattenRecipes) {
        if (mesh.getMesh().getLevel() <= meshType.getLevel()) {
          possibleMeshes.add(mesh);
        }
      } else {
        if (mesh.getMesh().getLevel() == meshType.getLevel()) {
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
    return "SieveRecipe{"
        + "input="
        + input
        + ", drop="
        + drop
        + ", rolls="
        + rolls
        + ", isWaterlogged="
        + isWaterlogged
        + ", recipeId="
        + recipeId
        + '}';
  }

  @Override
  public @NotNull ItemStack getToastSymbol() {
    return EXNBlocks.OAK_SIEVE.itemStack();
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.SIEVE_RECIPE_SERIALIZER;
  }
}
