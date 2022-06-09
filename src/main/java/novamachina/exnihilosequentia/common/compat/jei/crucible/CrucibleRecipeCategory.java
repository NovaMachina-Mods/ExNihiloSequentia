package novamachina.exnihilosequentia.common.compat.jei.crucible;
//
// import javax.annotation.Nonnull;
// import javax.annotation.Nullable;
// import mezz.jei.api.forge.ForgeTypes;
// import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
// import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
// import mezz.jei.api.gui.drawable.IDrawable;
// import mezz.jei.api.gui.drawable.IDrawableStatic;
// import mezz.jei.api.helpers.IGuiHelper;
// import mezz.jei.api.recipe.IFocusGroup;
// import mezz.jei.api.recipe.RecipeIngredientRole;
// import mezz.jei.api.recipe.category.IRecipeCategory;
// import net.minecraft.network.chat.Component;
// import net.minecraft.network.chat.TextComponent;
// import net.minecraft.resources.ResourceLocation;
// import net.minecraft.world.item.ItemStack;
// import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
// import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
//
// public class CrucibleRecipeCategory implements IRecipeCategory<JEICrucibleRecipe> {
//
//  @Nonnull
//  private static final ResourceLocation texture =
//      new ResourceLocation(
//          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");
//
//  @Nonnull private final IDrawableStatic background;
//  @Nonnull private final IDrawableStatic slotHighlight;
//  @Nonnull private final ResourceLocation uid;
//
//  public CrucibleRecipeCategory(@Nonnull final IGuiHelper guiHelper, @Nonnull final String uid) {
//    this.background = guiHelper.createDrawable(texture, 0, 168, 166, 58);
//    this.slotHighlight = guiHelper.createDrawable(texture, 166, 0, 18, 18);
//    this.uid = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, uid);
//  }
//
//  @Nonnull
//  @Override
//  public IDrawable getBackground() {
//    return background;
//  }
//
//  @Nullable
//  @Override
//  public IDrawable getIcon() {
//    return null;
//  }
//
//  @Nonnull
//  @Override
//  public Class<? extends JEICrucibleRecipe> getRecipeClass() {
//    return JEICrucibleRecipe.class;
//  }
//
//  @Nonnull
//  @Override
//  public Component getTitle() {
//    if (uid.equals(
//        new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "crucible_fired"))) {
//      return new TextComponent("Fired Crucible");
//    } else {
//      return new TextComponent("Crucible");
//    }
//  }
//
//  @Nonnull
//  @Override
//  public ResourceLocation getUid() {
//    return uid;
//  }
//
//  @Override
//  public void setRecipe(IRecipeLayoutBuilder builder, JEICrucibleRecipe recipe, IFocusGroup
// focuses) {
//    IRecipeSlotBuilder output = builder.addSlot(RecipeIngredientRole.OUTPUT, 3, 21);
//    if (recipe.getResultFluid().getAmount() != 1000) {
//      recipe.getResultFluid().setAmount(1000);
//    }
//    output.addIngredient(ForgeTypes.FLUID_STACK, recipe.getResultFluid());
//
//    for (int i = 0; i < recipe.getInputs().size(); i++) {
//      final int slotX = 39 + (i % 7 * 18);
//      final int slotY = 3 + i / 7 * 18;
//
//      @Nonnull final ItemStack stack = recipe.getInputs().get(i);
//      IRecipeSlotBuilder input =
//          builder.addSlot(RecipeIngredientRole.INPUT, slotX, slotY).addItemStack(stack);
//
//      if (uid.equals(
//          new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "crucible_fired"))) {
//        input.addTooltipCallback(new FiredCrucibleTooltipCallback());
//      } else {
//        input.addTooltipCallback(new WoodCrucibleTooltipCallback());
//      }
//    }
//  }
// }
