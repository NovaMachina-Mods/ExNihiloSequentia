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

public class CompostRecipeCategory implements IRecipeCategory<CompostRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "compost");
    private static final ResourceLocation texture = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");
    private final IDrawableStatic background;
    private final IDrawableStatic slotHighlight;

    public CompostRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 168, 166, 58);
        this.slotHighlight = guiHelper.createDrawable(texture, 166, 0, 18, 18);
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
    public Class<? extends CompostRecipe> getRecipeClass() {
        return CompostRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Compost";
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public void setIngredients(CompostRecipe recipe, IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, recipe.getInputs());
        ingredients.setOutput(VanillaTypes.ITEM, new ItemStack(Blocks.DIRT));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CompostRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 2, 20);
        recipeLayout.getItemStacks().set(0, new ItemStack(Blocks.DIRT));

        IFocus<?> focus = recipeLayout.getFocus();

        int slotIndex = 1;
        for (int i = 0; i < recipe.getInputs().size(); i++) {
            final int slotX = 38 + (i % 7 * 18);
            final int slotY = 2 + i / 7 * 18;

            ItemStack stack = recipe.getInputs().get(i);

            recipeLayout.getItemStacks().init(i + slotIndex, true, slotX, slotY);
            recipeLayout.getItemStacks().set(i + slotIndex, stack);

            if (focus != null && stack.sameItem((ItemStack) focus.getValue())) {
                recipeLayout.getItemStacks().setBackground(i + slotIndex, slotHighlight);
            }
        }
        recipeLayout.getItemStacks().addTooltipCallback(new CompostTooltipCallback());
    }
}
