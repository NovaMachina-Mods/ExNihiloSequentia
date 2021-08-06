package novamachina.exnihilosequentia.api.compat.jei;

import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;

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

    public ItemStack getMesh() {
        return inputs.get(0).get(0);
    }

    public List<ItemStack> getResults() {
        return results;
    }

    public List<ItemStack> getSieveables() {
        return inputs.get(1);
    }
}
