package novamachina.exnihilosequentia.common.compat.jei.crucible;

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
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrucibleRecipeCategory implements IRecipeCategory<CrucibleRecipe> {
    @Nonnull private static final ResourceLocation texture =
            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");
    @Nonnull private final IDrawableStatic background;
    @Nonnull private final IDrawableStatic slotHighlight;
    @Nonnull private final ResourceLocation uid;

    public CrucibleRecipeCategory(@Nonnull final IGuiHelper guiHelper, @Nonnull final String uid) {
        this.background = guiHelper.createDrawable(texture, 0, 168, 166, 58);
        this.slotHighlight = guiHelper.createDrawable(texture, 166, 0, 18, 18);
        this.uid = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, uid);
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
    public Class<? extends CrucibleRecipe> getRecipeClass() {
        return CrucibleRecipe.class;
    }

    @Nonnull
    @Override
    public String getTitle() {
        if(uid.equals(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "crucible_fired"))) {
            return "Fired Crucible";
        } else {
            return "Crucible";
        }
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return uid;
    }

    @Override
    public void setIngredients(@Nonnull final CrucibleRecipe recipe, @Nonnull final IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, recipe.getInputs());
        ingredients.setOutput(VanillaTypes.FLUID, recipe.getResultFluid());
    }

    @Override
    public void setRecipe(@Nonnull final IRecipeLayout recipeLayout, @Nonnull final CrucibleRecipe recipe,
                          @Nonnull final IIngredients ingredients) {
        recipeLayout.getFluidStacks().init(0, true, 3, 21);
        if (recipe.getResultFluid().getAmount() != 1000) {
            recipe.getResultFluid().setAmount(1000);
        }
        recipeLayout.getFluidStacks().set(0, recipe.getResultFluid());

        @Nullable final IFocus<?> focus = recipeLayout.getFocus();

        final int slotIndex = 1;
        for (int i = 0; i < recipe.getInputs().size(); i++) {
            final int slotX = 38 + (i % 7 * 18);
            final int slotY = 2 + i / 7 * 18;

            @Nonnull final ItemStack stack = recipe.getInputs().get(i);

            recipeLayout.getItemStacks().init(i + slotIndex, true, slotX, slotY);
            recipeLayout.getItemStacks().set(i + slotIndex, stack);

            if (focus != null && focus.getValue() instanceof ItemStack && stack
                    .sameItem((ItemStack) focus.getValue())) {
                recipeLayout.getItemStacks().setBackground(i + slotIndex, slotHighlight);
            }
        }

        if (uid.equals(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "crucible_fired"))) {
            recipeLayout.getItemStacks().addTooltipCallback(new FiredCrucibleTooltipCallback());
        } else {
            recipeLayout.getItemStacks().addTooltipCallback(new WoodCrucibleTooltipCallback());
        }
    }
}
