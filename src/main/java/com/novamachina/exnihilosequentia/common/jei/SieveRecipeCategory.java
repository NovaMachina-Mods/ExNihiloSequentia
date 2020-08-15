package com.novamachina.exnihilosequentia.common.jei;

import com.novamachina.exnihilosequentia.common.utility.Constants;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SieveRecipeCategory implements IRecipeCategory<SieveRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "sieve");
    private static final ResourceLocation texture = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");

    private final IDrawableStatic background;
    private final IDrawableStatic slotHighlight;

    public SieveRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 0, 166, 58);
        this.slotHighlight = guiHelper.createDrawable(texture, 166, 0, 18, 18);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends SieveRecipe> getRecipeClass() {
        return SieveRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Sieve";
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
    public void setIngredients(SieveRecipe recipe, IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, recipe.getInputs());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputs());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SieveRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 10, 38);
        recipeLayout.getItemStacks().set(0, recipe.getMesh());

        recipeLayout.getItemStacks().init(1, true, 10, 2);
        recipeLayout.getItemStacks().set(1, recipe.getSieveables());

        IFocus<?> focus = recipeLayout.getFocus();

        int slotIndex = 2;
        for(int i = 0; i < recipe.getOutputs().size(); i++) {
            final int slotX = 38 + (i %7 * 18);
            final int slotY = 2 + i / 7 * 18;

            ItemStack outputStack = recipe.getOutputs().get(i);
            recipeLayout.getItemStacks().init(slotIndex + i, false, slotX, slotY);
            recipeLayout.getItemStacks().set(slotIndex + i, outputStack);

            if (focus != null) {
                ItemStack focusStack = (ItemStack) focus.getValue();
                if (focus.getMode() == IFocus.Mode.OUTPUT
                    && !focusStack.isEmpty()
                    && focusStack.getItem() == outputStack.getItem()
                    && focusStack.getDamage() == outputStack.getDamage()) {
                    recipeLayout.getItemStacks().setBackground(slotIndex + i,slotHighlight);
                }
            }
        }
    }
}
