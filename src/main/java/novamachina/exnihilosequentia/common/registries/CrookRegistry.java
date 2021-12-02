package novamachina.exnihilosequentia.common.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import com.google.common.collect.Lists;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
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

    private final Map<ItemLike, List<CrookRecipe>> recipeListByItemCache = new HashMap<>();

    @Override
    public boolean isCrookable(ItemLike block) {
        return !getDrops(block).isEmpty();
    }

    @Override
    public List<CrookRecipe> getDrops(ItemLike block) {
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