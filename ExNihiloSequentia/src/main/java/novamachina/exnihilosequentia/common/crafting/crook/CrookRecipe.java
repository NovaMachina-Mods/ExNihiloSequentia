package novamachina.exnihilosequentia.common.crafting.crook;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.crafting.SingleItemSerializableRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloRecipeTypes;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class CrookRecipe extends SingleItemSerializableRecipe {
  @Nonnull private final List<ItemStackWithChance> output;

  public CrookRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final Ingredient input,
      @Nonnull final List<ItemStackWithChance> output) {
    super(
        output.isEmpty() ? ItemStack.EMPTY : output.get(0).getStack(),
        input,
        ExNihiloRecipeTypes.CROOK_RECIPE_TYPE.get(),
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
  @Nullable
  protected ExNihiloRecipeSerializer<CrookRecipe> getENSerializer() {
    return ExNihiloSerializers.CROOK_RECIPE_SERIALIZER.get();
  }
}
