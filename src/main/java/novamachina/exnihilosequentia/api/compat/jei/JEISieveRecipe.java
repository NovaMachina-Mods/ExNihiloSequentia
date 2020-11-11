package novamachina.exnihilosequentia.api.compat.jei;

import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

public class JEISieveRecipe {
    private final List<List<ItemStack>> inputs;
    private final List<ItemStack> results;

    public JEISieveRecipe(List<List<ItemStack>> input, List<SieveRecipe> results) {
        this.inputs = input;
        this.results = results.parallelStream().map(SieveRecipe::getDrop).collect(Collectors.toList());
    }

    public List<List<ItemStack>> getInputs() {
        return inputs;
    }

    public List<ItemStack> getResults() {
        return results;
    }

    public ItemStack getMesh() {
        return inputs.get(0).get(0);
    }

    public List<ItemStack> getSieveables() {
        return inputs.get(1);
    }
}
