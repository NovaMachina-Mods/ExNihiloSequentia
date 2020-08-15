package com.novamachina.exnihilosequentia.common.jei;

import com.novamachina.exnihilosequentia.common.utility.Constants;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CrookRecipeCategory implements IRecipeCategory<CrookRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "crook");
    private static final ResourceLocation texture = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");

    private final IDrawableStatic background;
    private final IDrawableStatic slotHighlight;

    public CrookRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 112, 166, 58);
        this.slotHighlight = guiHelper.createDrawable(texture, 166, 0, 18, 18);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends CrookRecipe> getRecipeClass() {
        return CrookRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Crook";
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void setIngredients(CrookRecipe crookRecipe, IIngredients iIngredients) {
        iIngredients.setInputs(VanillaTypes.ITEM, crookRecipe.getInputs());
        iIngredients.setOutputs(VanillaTypes.ITEM, crookRecipe.getOutputs());
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, CrookRecipe crookRecipe, IIngredients iIngredients) {
        iRecipeLayout.getItemStacks().init(0, true, 10, 38);
        iRecipeLayout.getItemStacks().set(0, crookRecipe.getInputs());

        IFocus<?> focus = iRecipeLayout.getFocus();

        int slotIndex = 1;
        for(int i = 0; i < crookRecipe.getOutputs().size(); i++) {
            final int slotX = 38 + (i % 7 * 18);
            final int slotY = 2 + i/7 * 18;

            ItemStack outputStack = crookRecipe.getOutputs().get(i);

            iRecipeLayout.getItemStacks().init(slotIndex + i, false, slotX, slotY);
            iRecipeLayout.getItemStacks().set(slotIndex + i, outputStack);

            if(focus != null) {
                ItemStack focusStack = (ItemStack) focus.getValue();
                if (focus.getMode() == IFocus.Mode.OUTPUT && !focusStack.isEmpty() && focusStack.getItem() == outputStack.getItem() && focusStack.getDamage() == outputStack.getDamage()) {
                    iRecipeLayout.getItemStacks().setBackground(i + slotIndex, slotHighlight);
                }
            }
        }
//        iRecipeLayout.getItemStacks().addTooltipCallback(new CrookTooltipCallback(crookRecipe));
    }
}
