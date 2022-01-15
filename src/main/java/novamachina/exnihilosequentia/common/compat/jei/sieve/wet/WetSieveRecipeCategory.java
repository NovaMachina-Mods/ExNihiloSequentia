package novamachina.exnihilosequentia.common.compat.jei.sieve.wet;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.List;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.compat.jei.JEISieveRecipe;
import novamachina.exnihilosequentia.api.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WetSieveRecipeCategory implements IRecipeCategory<JEISieveRecipe> {
    @Nonnull public static final ResourceLocation UID =
            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "wet_sieve");
    @Nonnull private static final ResourceLocation texture =
            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");

    @Nonnull private final IDrawableStatic background;
    @Nonnull private final IDrawableStatic slotHighlight;

    public WetSieveRecipeCategory(@Nonnull final IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 0, 166, 58);
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
    public Class<? extends JEISieveRecipe> getRecipeClass() {
        return JEISieveRecipe.class;
    }

    @Nonnull
    @Override
    public Component getTitle() {
        return new TextComponent("Waterlogged Sieve");
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public void setIngredients(@Nonnull final JEISieveRecipe recipe, @Nonnull final IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, recipe.getInputs());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getResults());
    }

    @Override
    public void setRecipe(@Nonnull final IRecipeLayout recipeLayout, @Nonnull final JEISieveRecipe recipe,
                          @Nonnull final IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 10, 38);
        recipeLayout.getItemStacks().set(0, recipe.getMesh());

        recipeLayout.getItemStacks().init(1, true, 10, 2);
        recipeLayout.getItemStacks().set(1, recipe.getSieveables());

        @Nullable final IFocus<?> focus = recipeLayout.getFocus(VanillaTypes.ITEM);

        final int slotIndex = 2;
        for (int i = 0; i < recipe.getResults().size(); i++) {
            final int slotX = 38 + (i % 7 * 18);
            final int slotY = 2 + i / 7 * 18;

            @Nonnull final ItemStack outputStack = recipe.getResults().get(i);
            recipeLayout.getItemStacks().init(slotIndex + i, false, slotX, slotY);
            recipeLayout.getItemStacks().set(slotIndex + i, outputStack);

            if (focus != null) {
                @Nonnull final ItemStack focusStack = (ItemStack) focus.getValue();
                if (focus.getMode() == IFocus.Mode.OUTPUT
                        && !focusStack.isEmpty()
                        && focusStack.getItem() == outputStack.getItem()
                        && focusStack.getDamageValue() == outputStack.getDamageValue()) {
                    recipeLayout.getItemStacks().setBackground(slotIndex + i, slotHighlight);
                }
            }
        }
        recipeLayout.getItemStacks().addTooltipCallback(new ITooltipCallback<ItemStack>() {
            @OnlyIn(Dist.CLIENT)
            @Override
            public void onTooltip(final int slotIndex, final boolean input, @Nonnull final ItemStack ingredient,
                                  @Nonnull final List<Component> tooltip) {
                if (!input) {
                    @Nonnull final Multiset<String> condensedTooltips = HashMultiset.create();
                    @Nonnull final List<SieveRecipe> drops = ExNihiloRegistries.SIEVE_REGISTRY
                            .getDrops(recipe.getInputs().get(1).get(0).getItem(), ((MeshItem) recipe
                                    .getInputs().get(0).get(0).getItem()).getMesh(), true);
                    for (@Nonnull final SieveRecipe entry : drops) {
                        @Nonnull final ItemStack drop = entry.getDrop();
                        if (!drop.sameItem(ingredient)) {
                            continue;
                        }
                        for (@Nonnull final MeshWithChance meshWithChance : entry.getRolls()) {
                            condensedTooltips.add(StringUtils.formatPercent(meshWithChance.getChance()));
                        }
                    }
                    tooltip.add(new TranslatableComponent("jei.sieve.dropChance"));
                    for (@Nonnull final String line : condensedTooltips.elementSet()) {
                        tooltip.add(new TextComponent(" * " + condensedTooltips.count(line) + "x " + line));
                    }
                }
            }
        });
    }
}
