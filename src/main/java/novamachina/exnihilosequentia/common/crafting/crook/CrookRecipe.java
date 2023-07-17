package novamachina.exnihilosequentia.common.crafting.crook;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.crafting.SingleItemSerializableRecipe;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import org.jetbrains.annotations.NotNull;

public class CrookRecipe extends SingleItemSerializableRecipe {
  @Nonnull private final List<ItemStackWithChance> output;

  public CrookRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final Ingredient input,
      @Nonnull final List<ItemStackWithChance> output) {
    super(
        output.isEmpty() ? ItemStack.EMPTY : output.get(0).getStack(),
        input,
        EXNRecipeTypes.CROOK_RECIPE_TYPE,
        id);
    this.output = output;
  }

  public void addOutput(@Nonnull final ItemStack item, final float chance) {
    output.add(new ItemStackWithChance(item, chance));
  }

  @Nonnull
  public List<ItemStackWithChance> getOutput() {
    return output;
  }

  @Nonnull
  public List<ItemStack> getOutputsWithoutChance() {
    @Nonnull final List<ItemStack> returnList = new ArrayList<>();
    output.forEach(stack -> returnList.add(stack.getStack()));
    return returnList;
  }

  @Override
  @Nonnull
  public ItemStack getResultItem() {
    return output.isEmpty() ? ItemStack.EMPTY : output.get(0).getStack().copy();
  }

  @Override
  public @NotNull ItemStack getToastSymbol() {
    return EXNItems.CROOK_WOOD.itemStack();
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.CROOK_RECIPE_SERIALIZER;
  }
}
