package novamachina.exnihilosequentia.common.compat.jei.sieve;
//
// import com.google.common.collect.HashMultiset;
// import com.google.common.collect.Multiset;
// import java.util.List;
// import java.util.Optional;
// import javax.annotation.Nonnull;
// import mezz.jei.api.gui.ingredient.IRecipeSlotTooltipCallback;
// import mezz.jei.api.gui.ingredient.IRecipeSlotView;
// import mezz.jei.api.ingredients.ITypedIngredient;
// import mezz.jei.api.recipe.RecipeIngredientRole;
// import net.minecraft.network.chat.Component;
// import net.minecraft.network.chat.TextComponent;
// import net.minecraft.network.chat.TranslatableComponent;
// import net.minecraft.world.item.ItemStack;
// import novamachina.exnihilosequentia.common.crafting.sieve.MeshWithChance;
// import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipe;
// import novamachina.exnihilosequentia.common.item.MeshItem;
// import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
// import novamachina.exnihilosequentia.common.utility.StringUtils;
//
// public class SieveTooltipCallback implements IRecipeSlotTooltipCallback {
//
//  private final JEISieveRecipe recipe;
//  private final boolean isWaterlogged;
//
//  public SieveTooltipCallback(JEISieveRecipe recipe, boolean isWaterlogged) {
//    this.recipe = recipe;
//    this.isWaterlogged = isWaterlogged;
//  }
//
//  @Override
//  public void onTooltip(IRecipeSlotView recipeSlotView, List<Component> tooltip) {
//    if (recipeSlotView.getRole() == RecipeIngredientRole.OUTPUT) {
//      @Nonnull final Multiset<String> condensedTooltips = HashMultiset.create();
//      @Nonnull
//      final List<SieveRecipe> drops =
//          ExNihiloRegistries.SIEVE_REGISTRY.getDrops(
//              recipe.getInputs().get(1).get(0).getItem(),
//              ((MeshItem) recipe.getInputs().get(0).get(0).getItem()).getType(),
//              this.isWaterlogged);
//      for (@Nonnull final SieveRecipe entry : drops) {
//        @Nonnull final ItemStack drop = entry.getDrop();
//        Optional<ITypedIngredient<?>> optional = recipeSlotView.getDisplayedIngredient();
//        if (optional.isEmpty() || !drop.sameItem((ItemStack) optional.get().getIngredient())) {
//          continue;
//        }
//        for (MeshWithChance meshWithChance : entry.getRolls()) {
//          condensedTooltips.add(StringUtils.formatPercent(meshWithChance.getChance()));
//        }
//      }
//      tooltip.add(new TranslatableComponent("jei.sieve.dropChance"));
//      for (@Nonnull final String line : condensedTooltips.elementSet()) {
//        tooltip.add(new TextComponent(" * " + condensedTooltips.count(line) + "x " + line));
//      }
//    }
//  }
// }
