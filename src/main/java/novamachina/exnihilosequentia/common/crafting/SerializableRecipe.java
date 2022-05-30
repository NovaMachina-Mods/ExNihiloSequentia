package novamachina.exnihilosequentia.common.crafting;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class SerializableRecipe implements Recipe<Container> {

  @Nonnull
  protected final ResourceLocation id;
  @Nullable
  protected final ItemStack outputDummy;
  @Nonnull
  protected final RecipeType<?> type;

  protected SerializableRecipe(@Nullable final ItemStack outputDummy,
      @Nonnull final RecipeType<?> type,
      @Nonnull final ResourceLocation id) {
    this.outputDummy = outputDummy;
    this.type = type;
    this.id = id;
  }

  @Override
  public boolean canCraftInDimensions(final int width, final int height) {
    return false;
  }

  @Override
  @Nullable
  public ItemStack assemble(@Nonnull final Container inv) {
    return this.outputDummy;
  }

  @Override
  @Nonnull
  public ItemStack getToastSymbol() {
    return getENSerializer().getIcon();
  }

  @Override
  @Nonnull
  public ResourceLocation getId() {
    return this.id;
  }

  @Override
  @Nonnull
  public RecipeSerializer<?> getSerializer() {
    return getENSerializer();
  }

  @Override
  @Nonnull
  public RecipeType<?> getType() {
    return this.type;
  }

  @Override
  public boolean isSpecial() {
    return true;
  }

  @Override
  public boolean matches(@Nonnull final Container inv, @Nonnull final Level worldIn) {
    return false;
  }

  protected abstract ExNihiloRecipeSerializer<? extends SerializableRecipe> getENSerializer();
}
