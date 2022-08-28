package novamachina.exnihilosequentia.common.crafting.hammer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.crafting.SingleItemInputRecipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class HammerRecipe extends SingleItemInputRecipe {

  @Nonnull
  public static final HammerRecipe EMPTY = new HammerRecipe(new ResourceLocation("empty"),
      Ingredient.EMPTY, new ArrayList<>());
  public static RecipeType<HammerRecipe> RECIPE_TYPE;
  @Nullable
  private static RegistryObject<ExNihiloRecipeSerializer<HammerRecipe>> serializer;
  @Nonnull
  private final List<ItemStackWithChance> output;

  public HammerRecipe(@Nonnull final ResourceLocation id, @Nonnull final Ingredient input,
      @Nonnull final List<ItemStackWithChance> output) {
    super(ItemStack.EMPTY, input, RECIPE_TYPE, id);
    this.output = output;
  }

  @Nullable
  public static RegistryObject<ExNihiloRecipeSerializer<HammerRecipe>> getStaticSerializer() {
    return serializer;
  }

  public static void setSerializer(
      @Nonnull final RegistryObject<ExNihiloRecipeSerializer<HammerRecipe>> serializer) {
    HammerRecipe.serializer = serializer;
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
    if (serializer == null) {
      return null;
    }
    return serializer.get();
  }
}
