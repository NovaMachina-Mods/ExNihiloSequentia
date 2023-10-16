package novamachina.exnihilosequentia.common.compat.jei.sifting;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;

public class JEISieveRecipe {

  @Nonnull private final List<List<ItemStack>> inputs;
  @Nonnull private final List<ItemStack> results;

  public JEISieveRecipe(
      @Nonnull final List<List<ItemStack>> input, @Nonnull final List<SiftingRecipe> results) {
    this.inputs = input;
    this.results =
        results.parallelStream().map(SiftingRecipe::getDrop).collect(Collectors.toList());
  }

  @Nonnull
  public List<List<ItemStack>> getInputs() {
    return inputs;
  }

  @Nonnull
  public ItemStack getMesh() {
    return inputs.get(0).get(0);
  }

  @Nonnull
  public List<ItemStack> getResults() {
    return results;
  }

  @Nonnull
  public List<ItemStack> getSieveables() {
    return inputs.get(1);
  }
}
