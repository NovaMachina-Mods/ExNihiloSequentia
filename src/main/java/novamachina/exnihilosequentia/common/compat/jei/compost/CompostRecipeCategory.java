package novamachina.exnihilosequentia.common.compat.jei.compost;

import novamachina.exnihilosequentia.common.api.crafting.compost.CompostRecipe;
import novamachina.exnihilosequentia.common.utility.Constants;
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

public class CompostRecipeCategory implements IRecipeCategory<CompostRecipe> {
    private static final ResourceLocation texture = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");
    public static ResourceLocation UID = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "compost");
    private final IDrawableStatic background;
    private final IDrawableStatic slotHighlight;

    public CompostRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 112, 166, 58);
        this.slotHighlight = guiHelper.createDrawable(texture, 166, 0, 18, 18);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends CompostRecipe> getRecipeClass() {
        return CompostRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Compost";
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
    public void setIngredients(CompostRecipe recipe, IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, recipe.getInputs());
        ingredients.setOutput(VanillaTypes.ITEM, new ItemStack(Blocks.DIRT));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CompostRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 10, 38);
        recipeLayout.getItemStacks().set(0, new ItemStack(Blocks.DIRT));

        IFocus<?> focus = recipeLayout.getFocus();

        int slotIndex = 1;
        for (int i = 0; i < recipe.getInputs().size(); i++) {
            final int slotX = 38 + (i % 7 * 18);
            final int slotY = 2 + i / 7 * 18;

            ItemStack stack = recipe.getInputs().get(i);

            recipeLayout.getItemStacks().init(i + slotIndex, true, slotX, slotY);
            recipeLayout.getItemStacks().set(i + slotIndex, stack);

            if (focus != null) {
                if (stack.isItemEqual((ItemStack) focus.getValue())) {
                    recipeLayout.getItemStacks().setBackground(i + slotIndex, slotHighlight);
                }
            }
        }
        recipeLayout.getItemStacks().addTooltipCallback(new CompostTooltipCallback());
    }
}
