package novamachina.exnihilosequentia.common.compat.jei.crook;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrookRecipeCategory implements IRecipeCategory<CrookRecipe> {
    @Nonnull public static final ResourceLocation UID =
            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "crook");
    @Nonnull private static final ResourceLocation texture =
            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");

    @Nonnull private final IDrawableStatic background;
    @Nonnull private final IDrawableStatic slotHighlight;

    public CrookRecipeCategory(@Nonnull final IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 112, 166, 58);
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
    public Class<? extends CrookRecipe> getRecipeClass() {
        return CrookRecipe.class;
    }

    @Nonnull
    @Override
    public Component getTitle() {
        return new TextComponent("Crook");
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public void setIngredients(@Nonnull final CrookRecipe crookRecipe, @Nonnull final IIngredients iIngredients) {
        iIngredients.setInputs(VanillaTypes.ITEM, crookRecipe.getInputs());
        iIngredients.setOutputs(VanillaTypes.ITEM, crookRecipe.getOutputsWithoutChance());
    }

    @Override
    public void setRecipe(@Nonnull final IRecipeLayout recipeLayout, @Nonnull final CrookRecipe crookRecipe,
                          @Nonnull final IIngredients iIngredients) {
        recipeLayout.getItemStacks().init(0, true, 10, 38);
        recipeLayout.getItemStacks().set(0, crookRecipe.getInputs());

        @Nullable final IFocus<?> focus = recipeLayout.getFocus(VanillaTypes.ITEM);

        final int slotIndex = 1;
        for (int i = 0; i < crookRecipe.getOutputsWithoutChance().size(); i++) {
            final int slotX = 38 + (i % 7 * 18);
            final int slotY = 2 + i / 7 * 18;

            @Nonnull final ItemStack outputStack = crookRecipe.getOutputsWithoutChance().get(i);

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
        recipeLayout.getItemStacks().addTooltipCallback(new CrookTooltipCallback(crookRecipe));
    }
}
