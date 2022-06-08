package novamachina.exnihilosequentia.common.crafting.hammer;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.common.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloRecipeTypes;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class HammerRecipe extends SerializableRecipe {

  @Nonnull
  public static final HammerRecipe EMPTY = new HammerRecipe(new ResourceLocation("empty"),
      Ingredient.EMPTY, new ArrayList<>());
  @Nonnull
  private final List<ItemStackWithChance> output;
  @Nonnull
  private Ingredient input;

  public HammerRecipe(@Nonnull final ResourceLocation id, @Nonnull final Ingredient input,
      @Nonnull final List<ItemStackWithChance> output) {
    super(ItemStack.EMPTY, ExNihiloRecipeTypes.HAMMER_RECIPE_TYPE.get(), id);
    this.input = input;
    this.output = output;
  }

  public void addOutput(@Nonnull final ItemStack output) {
    this.output.add(new ItemStackWithChance(output, 1.0F));
  }

  public void addOutput(@Nonnull final ItemStack output, final float chance) {
    this.output.add(new ItemStackWithChance(output, chance));
  }

  @Nonnull
  public Ingredient getInput() {
    return input;
  }

  public void setInput(@Nonnull final Ingredient input) {
    this.input = input;
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
