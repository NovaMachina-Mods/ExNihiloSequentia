package novamachina.exnihilosequentia.common.compat.jei.sifting;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.MeshItem;
import novamachina.exnihilosequentia.world.item.crafting.MeshWithChance;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;
import novamachina.novacore.util.StringUtils;

public class SieveTooltipCallback implements IRecipeSlotTooltipCallback {

  private final JEISieveRecipe recipe;
  private final boolean isWaterlogged;

  public SieveTooltipCallback(JEISieveRecipe recipe, boolean isWaterlogged) {
    this.recipe = recipe;
    this.isWaterlogged = isWaterlogged;
  }

  @Override
  public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
    if (recipeSlotView.getRole() == RecipeIngredientRole.OUTPUT) {
      @Nonnull final Multiset<String> condensedTooltips = HashMultiset.create();
      @Nonnull
      final List<SiftingRecipe> drops =
          ExNihiloRegistries.SIEVE_REGISTRY.getDrops(
              recipe.getInputs().get(1).get(0).getItem(),
              ((MeshItem) recipe.getInputs().get(0).get(0).getItem()).getType(),
              this.isWaterlogged);
      for (@Nonnull final SiftingRecipe entry : drops) {
        @Nonnull final ItemStack drop = entry.getDrop();
        Optional<ITypedIngredient<?>> optional = recipeSlotView.getDisplayedIngredient();
        if (optional.isEmpty() || !drop.is(optional.get().getItemStack().get().getItem())) {
          continue;
        }
        for (MeshWithChance meshWithChance : entry.getRolls()) {
          condensedTooltips.add(StringUtils.formatPercent(meshWithChance.getChance()));
        }
      }
      tooltip.add(Component.translatable("jei.sieve.dropChance"));
      for (@Nonnull final String line : condensedTooltips.elementSet()) {
        tooltip.add(Component.literal(" * " + condensedTooltips.count(line) + "x " + line));
      }
    }
  }
}
