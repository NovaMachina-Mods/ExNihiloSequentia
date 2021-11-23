package novamachina.exnihilosequentia.common.registries;

import com.google.common.collect.Lists;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.registry.ICrookRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CrookRegistry implements ICrookRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final List<CrookRecipe> recipeList = new ArrayList<>();

    private final Map<IItemProvider, List<CrookRecipe>> recipeListByItemCache = new HashMap<>();

    @Override
    public boolean isCrookable(IItemProvider block) {
        return !getDrops(block).isEmpty();
    }

    @Override
    public List<CrookRecipe> getDrops(IItemProvider block) {
        return recipeListByItemCache.computeIfAbsent(block, k -> {
            final ItemStack itemStack = new ItemStack(block);
            return recipeList
                    .stream()
                    .filter(crookRecipe -> crookRecipe.getInput().test(itemStack))
                    .collect(Collectors.toList());
        });
    }

    @Override
    public void setRecipes(List<CrookRecipe> recipes) {
        logger.debug("Crook Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);

        recipeListByItemCache.clear();
    }

    @Override
    public List<CrookRecipe> getRecipeList() {
        return recipeList
                .stream()
                .flatMap(crookRecipe -> {
                    if (crookRecipe.getOutput().size() <= 21)
                        return Stream.of(crookRecipe);
                    final List<List<ItemStackWithChance>> partitions = Lists.partition(crookRecipe.getOutput(), 21);
                    return IntStream
                            .range(0, partitions.size())
                            .mapToObj(i -> {
                                final ResourceLocation crookRecipeId = crookRecipe.getId();
                                final ResourceLocation newId = new ResourceLocation(crookRecipeId.getNamespace(), crookRecipeId.getPath() + i);
                                return new CrookRecipe(newId, crookRecipe.getInput(), partitions.get(i));
                            });
                })
                .collect(Collectors.toList());
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();

        recipeListByItemCache.clear();
    }
}