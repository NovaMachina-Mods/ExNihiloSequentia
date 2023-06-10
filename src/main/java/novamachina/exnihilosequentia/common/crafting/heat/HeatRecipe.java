package novamachina.exnihilosequentia.common.crafting.heat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.world.item.crafting.NovaRecipeSerializer;
import novamachina.novacore.world.item.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import org.jetbrains.annotations.NotNull;

public class HeatRecipe extends SerializableRecipe {

  private int amount;
  @Nullable private Block input;
  @Nullable private StatePropertiesPredicate properties;

  public HeatRecipe(
      @Nonnull final ResourceLocation id, @Nullable final Block input, final int amount) {
    super(null, EXNRecipeTypes.HEAT_RECIPE_TYPE, id);
    this.input = input;
    this.amount = amount;
    this.properties = null;
  }

  public HeatRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final Block input,
      final int amount,
      @Nonnull final StatePropertiesPredicate properties) {
    super(null, EXNRecipeTypes.HEAT_RECIPE_TYPE, id);
    this.input = input;
    this.amount = amount;
    this.properties = properties;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(final int amount) {
    this.amount = amount;
  }

  @Nullable
  public Block getInput() {
    return input;
  }

  public void setInput(@Nonnull final Block input) {
    this.input = input;
  }

  @Nullable
  public StatePropertiesPredicate getProperties() {
    return this.properties;
  }

  public void setProperties(@Nonnull final StatePropertiesPredicate properties) {
    this.properties = properties;
  }

  public boolean isMatch(@Nonnull final BlockState state) {
    if (input == null) {
      return false;
    }
    @Nullable
    final ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(state.getBlock());
    if (resourceLocation == null) {
      return false;
    }
    return resourceLocation.equals(ForgeRegistries.BLOCKS.getKey(input))
        && (properties == null || properties.matches(state));
  }

  @Override
  @Nonnull
  public ItemStack getResultItem() {
    return ItemStack.EMPTY;
  }

  @Override
  public @NotNull ItemStack getToastSymbol() {
    return EXNBlocks.FIRED_CRUCIBLE.itemStack();
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.HEAT_RECIPE_SERIALIZER;
  }
}
