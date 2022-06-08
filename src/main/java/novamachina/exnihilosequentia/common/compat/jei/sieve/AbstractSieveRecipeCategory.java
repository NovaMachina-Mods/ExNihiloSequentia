package novamachina.exnihilosequentia.common.compat.jei.sieve;
//
//import javax.annotation.Nonnull;
//import mezz.jei.api.constants.VanillaTypes;
//import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
//import mezz.jei.api.gui.drawable.IDrawable;
//import mezz.jei.api.gui.drawable.IDrawableStatic;
//import mezz.jei.api.helpers.IGuiHelper;
//import mezz.jei.api.recipe.IFocusGroup;
//import mezz.jei.api.recipe.RecipeIngredientRole;
//import mezz.jei.api.recipe.category.IRecipeCategory;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
//
//public abstract class AbstractSieveRecipeCategory implements IRecipeCategory<JEISieveRecipe> {
//
//  @Nonnull
//  private static final ResourceLocation texture =
//      new ResourceLocation(
//          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");
//
//  @Nonnull private final IDrawableStatic background;
//  private final boolean isWaterlogged;
//
//  protected AbstractSieveRecipeCategory(@Nonnull final IGuiHelper guiHelper, boolean isWaterlogged) {
//    this.background = guiHelper.createDrawable(texture, 0, 0, 166, 58);
//    this.isWaterlogged = isWaterlogged;
//  }
//
//  @Override
//  public IDrawable getBackground() {
//    return background;
//  }
//
//  @Override
//  public IDrawable getIcon() {
//    return null;
//  }
//
//  @Override
//  public Class<? extends JEISieveRecipe> getRecipeClass() {
//    return JEISieveRecipe.class;
//  }
//
//  @Override
//  public void setRecipe(IRecipeLayoutBuilder builder, JEISieveRecipe recipe, IFocusGroup focuses) {
//    builder.addSlot(RecipeIngredientRole.INPUT, 11, 39).addItemStack(recipe.getMesh());
//    builder
//        .addSlot(RecipeIngredientRole.INPUT, 11, 3)
//        .addIngredients(VanillaTypes.ITEM_STACK, recipe.getSieveables());
//
//    for (int i = 0; i < recipe.getResults().size(); i++) {
//      final int slotX = 39 + (i % 7 * 18);
//      final int slotY = 3 + i / 7 * 18;
//
//      @Nonnull final ItemStack outputStack = recipe.getResults().get(i);
//      builder
//          .addSlot(RecipeIngredientRole.OUTPUT, slotX, slotY)
//          .addItemStack(outputStack)
//          .addTooltipCallback(new SieveTooltipCallback(recipe, this.isWaterlogged));
//    }
//  }
//}
