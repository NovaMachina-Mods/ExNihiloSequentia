package novamachina.exnihilosequentia.common.crafting.hammer;

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

public class HammerRecipe extends SingleItemSerializableRecipe {

  @Nonnull
  public static final HammerRecipe EMPTY =
      new HammerRecipe(new ResourceLocation("empty"), Ingredient.EMPTY, new ArrayList<>());

  @Nonnull private final List<ItemStackWithChance> output;

  public HammerRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final Ingredient input,
      @Nonnull final List<ItemStackWithChance> output) {
    super(ItemStack.EMPTY, input, ExNihiloRecipeTypes.HAMMER_RECIPE_TYPE.get(), id);
    this.output = output;
  }

  public void addOutput(@Nonnull final ItemStack output) {
    this.output.add(new ItemStackWithChance(output, 1.0F));
  }

  public void addOutput(@Nonnull final ItemStack output, final float chance) {
    this.output.add(new ItemStackWithChance(output, chance));
  }

  @Nonnull
  public List<ItemStackWithChance> getOutput() {
    return output;
  }

  @Nonnull
  public List<ItemStack> getOutputsWithoutChance() {
    List<ItemStack> returnList = new ArrayList<>();
    output.forEach(stack -> returnList.add(stack.getStack()));
    return returnList;
  }

  @Override
  @Nonnull
  public ItemStack getResultItem() {
    return ItemStack.EMPTY;
  }

  @Override
  @Nullable
  protected ExNihiloRecipeSerializer<HammerRecipe> getENSerializer() {
    return ExNihiloSerializers.HAMMER_RECIPE_SERIALIZER.get();
  }
}
