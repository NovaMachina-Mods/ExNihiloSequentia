package novamachina.exnihilosequentia.common.crafting.heat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloRecipeTypes;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class HeatRecipe extends SerializableRecipe {

  private int amount;
  @Nullable private Block input;
  @Nullable private StatePropertiesPredicate properties;

  public HeatRecipe(
      @Nonnull final ResourceLocation id, @Nullable final Block input, final int amount) {
    super(null, ExNihiloRecipeTypes.HEAT_RECIPE_TYPE.get(), id);
    this.input = input;
    this.amount = amount;
    this.properties = null;
  }

  public HeatRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final Block input,
      final int amount,
      @Nonnull final StatePropertiesPredicate properties) {
    super(null, ExNihiloRecipeTypes.HEAT_RECIPE_TYPE.get(), id);
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
  @Nullable
  protected ExNihiloRecipeSerializer<HeatRecipe> getENSerializer() {
    return ExNihiloSerializers.HEAT_RECIPE_SERIALIZER.get();
  }
}
