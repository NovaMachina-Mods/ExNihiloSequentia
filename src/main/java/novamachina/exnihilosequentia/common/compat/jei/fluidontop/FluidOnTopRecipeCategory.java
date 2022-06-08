package novamachina.exnihilosequentia.common.compat.jei.fluidontop;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//import mezz.jei.api.forge.ForgeTypes;
//import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
//import mezz.jei.api.gui.drawable.IDrawable;
//import mezz.jei.api.gui.drawable.IDrawableStatic;
//import mezz.jei.api.helpers.IGuiHelper;
//import mezz.jei.api.recipe.IFocusGroup;
//import mezz.jei.api.recipe.RecipeIngredientRole;
//import mezz.jei.api.recipe.category.IRecipeCategory;
//import net.minecraft.network.chat.Component;
//import net.minecraft.network.chat.TextComponent;
//import net.minecraft.resources.ResourceLocation;
//import novamachina.exnihilosequentia.common.crafting.fluidontop.FluidOnTopRecipe;
//import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
//
//public class FluidOnTopRecipeCategory implements IRecipeCategory<FluidOnTopRecipe> {
//
//  @Nonnull
//  public static final ResourceLocation UID =
//      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "fluidontop");
//
//  @Nonnull
//  private static final ResourceLocation texture =
//      new ResourceLocation(
//          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_fluid_on_top.png");
//
//  @Nonnull private final IDrawableStatic background;
//
//  public FluidOnTopRecipeCategory(@Nonnull final IGuiHelper guiHelper) {
//    this.background = guiHelper.createDrawable(texture, 0, 0, 166, 63);
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
//  public Class<? extends FluidOnTopRecipe> getRecipeClass() {
//    return FluidOnTopRecipe.class;
//  }
//
//  @Nonnull
//  @Override
//  public Component getTitle() {
//    return new TextComponent("Fluid On Top");
//  }
//
//  @Nonnull
//  @Override
//  public ResourceLocation getUid() {
//    return UID;
//  }
//
//  @Override
//  public void setRecipe(
//      IRecipeLayoutBuilder builder, FluidOnTopRecipe recipe, IFocusGroup focuses) {
//    builder
//        .addSlot(RecipeIngredientRole.INPUT, 48, 37)
//        .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFluidInTank());
//    builder
//        .addSlot(RecipeIngredientRole.INPUT, 75, 10)
//        .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFluidOnTop());
//    builder.addSlot(RecipeIngredientRole.OUTPUT, 102, 37).addItemStack(recipe.getResultItem());
//  }
//}
