package novamachina.exnihilosequentia.common.crafting.hammer;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.novacore.world.item.crafting.NovaRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.crafting.SingleItemSerializableRecipe;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeTypes;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;
import org.jetbrains.annotations.NotNull;

public class HammerRecipe extends SingleItemSerializableRecipe {

  @Nonnull
  public static final HammerRecipe EMPTY =
      new HammerRecipe(new ResourceLocation("empty"), Ingredient.EMPTY, new ArrayList<>());

  @Nonnull private final List<ItemStackWithChance> output;

  public HammerRecipe(
      @Nonnull final ResourceLocation id,
      @Nonnull final Ingredient input,
      @Nonnull final List<ItemStackWithChance> output) {
    super(ItemStack.EMPTY, input, EXNRecipeTypes.HAMMER_RECIPE_TYPE, id);
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
  public @NotNull ItemStack getToastSymbol() {
    return EXNItems.HAMMER_DIAMOND.itemStack();
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return EXNRecipeSerializers.HAMMER_RECIPE_SERIALIZER.getRecipeSerializer();
  }
}
