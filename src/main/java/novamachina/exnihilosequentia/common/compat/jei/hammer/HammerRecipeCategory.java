package novamachina.exnihilosequentia.common.compat.jei.hammer;

//TODO
/*import java.util.ArrayList;
import java.util.Arrays;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.hammer.HammerRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

 */

public class HammerRecipeCategory {//implements IRecipeCategory<HammerRecipe> {
    /*
    public static final ResourceLocation UID = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "hammer");
    private static final ResourceLocation texture = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");

    private final IDrawableStatic background;
    private final IDrawableStatic slotHighlight;

    public HammerRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 56, 166, 58);
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
    public Class getRecipeClass() {
        return HammerRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Hammer";
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public void setIngredients(HammerRecipe recipe, IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, new ArrayList<>(Arrays.asList(recipe.getInput().getItems())));
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputsWithoutChance());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, HammerRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 10, 38);
        recipeLayout.getItemStacks().set(0, new ArrayList<>(Arrays.asList(recipe.getInput().getItems())));

        IFocus<?> focus = recipeLayout.getFocus();

        int slotIndex = 1;
        for (int i = 0; i < recipe.getOutputsWithoutChance().size(); i++) {
            final int slotX = 38 + (i % 7 * 18);
            final int slotY = 2 + i / 7 * 18;

            ItemStack outputStack = recipe.getOutputsWithoutChance().get(i);

            recipeLayout.getItemStacks().init(slotIndex + i, false, slotX, slotY);
            recipeLayout.getItemStacks().set(slotIndex + i, outputStack);

            if (focus != null) {
                ItemStack focusStack = (ItemStack) focus.getValue();
                if (focus.getMode() == IFocus.Mode.OUTPUT && !focusStack.isEmpty() && focusStack
                        .getItem() == outputStack.getItem() && focusStack.getDamageValue() == outputStack.getDamageValue()) {
                    recipeLayout.getItemStacks().setBackground(i + slotIndex, slotHighlight);
                }
            }
        }
        recipeLayout.getItemStacks().addTooltipCallback(new HammerTooltipCallback(recipe));
    }

     */
}
