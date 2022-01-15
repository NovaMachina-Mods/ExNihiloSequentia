package novamachina.exnihilosequentia.common.compat.jei.compost;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CompostRecipeCategory implements IRecipeCategory<CompostRecipe> {
    @Nonnull public static final ResourceLocation UID =
            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "compost");
    @Nonnull private static final ResourceLocation texture =
            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");
    @Nonnull private final IDrawableStatic background;
    @Nonnull private final IDrawableStatic slotHighlight;

    public CompostRecipeCategory(@Nonnull final IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 168, 166, 58);
        this.slotHighlight = guiHelper.createDrawable(texture, 166, 0, 18, 18);
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Nonnull
    @Override
    public Class<? extends CompostRecipe> getRecipeClass() {
        return CompostRecipe.class;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return "Compost";
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public void setIngredients(@Nonnull CompostRecipe recipe, @Nonnull IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, recipe.getInputs());
        ingredients.setOutput(VanillaTypes.ITEM, new ItemStack(Blocks.DIRT));
    }

    @Override
    public void setRecipe(@Nonnull final IRecipeLayout recipeLayout, @Nonnull final CompostRecipe recipe,
                          @Nonnull final IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 2, 20);
        recipeLayout.getItemStacks().set(0, new ItemStack(Blocks.DIRT));

        @Nullable final IFocus<?> focus = recipeLayout.getFocus();

        final int slotIndex = 1;
        if(recipe.getInputs().size() > 21) {
            recipeLayout.getIngredientsGroup(VanillaTypes.ITEM).init(slotIndex, true, 38, 2);
            recipeLayout.getIngredientsGroup(VanillaTypes.ITEM).set(slotIndex, recipe.getInputs());
        } else {
            for (int i = 0; i < recipe.getInputs().size(); i++) {
                final int slotX = 38 + (i % 7 * 18);
                final int slotY = 2 + i / 7 * 18;

                @Nonnull final ItemStack stack = recipe.getInputs().get(i);

                recipeLayout.getItemStacks().init(i + slotIndex, true, slotX, slotY);
                recipeLayout.getItemStacks().set(i + slotIndex, stack);

                if (focus != null && stack.sameItem((ItemStack) focus.getValue())) {
                    recipeLayout.getItemStacks().setBackground(i + slotIndex, slotHighlight);
                }
            }
        }
        recipeLayout.getItemStacks().addTooltipCallback(new CompostTooltipCallback());
    }
}
