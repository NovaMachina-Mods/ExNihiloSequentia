package com.novamachina.exnihilosequentia.common.jei.sieve.wet;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import com.novamachina.exnihilosequentia.common.jei.sieve.SieveRecipe;
import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.tileentity.sieve.SieveDropEntry;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.StringUtils;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class WetSieveRecipeCategory implements IRecipeCategory<SieveRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "wet_sieve");
    private static final ResourceLocation texture = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");

    private final IDrawableStatic background;
    private final IDrawableStatic slotHighlight;

    public WetSieveRecipeCategory(IGuiHelper guiHelper) {
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
        return "Waterlogged Sieve";
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
        recipeLayout.getItemStacks().addTooltipCallback(new ITooltipCallback<ItemStack>() {
            @OnlyIn(Dist.CLIENT)
            @Override
            public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<String> tooltip) {
                if(!input) {
                    ItemStack mesh = recipe.getMesh();
                    Multiset<String> condensedTooltips = HashMultiset.create();
                    List<SieveDropEntry> drops = ModRegistries.SIEVE.getDrops(recipe.getInputs().get(1).get(0).getItem().getRegistryName(), ((MeshItem)recipe.getInputs().get(0).get(0).getItem()).getMesh(), false);
                    for(SieveDropEntry entry : drops) {
                        ItemStack drop = new ItemStack(ForgeRegistries.ITEMS.getValue(entry.getResult()));
                        if(!drop.isItemEqual(ingredient)) {
                            continue;
                        }
                        condensedTooltips.add(StringUtils.formatPercent(entry.getRarity()));
                    }
                    tooltip.add(I18n.format("jei.sieve.dropChance"));
                    for(String line : condensedTooltips.elementSet()) {
                        tooltip.add(" * " + condensedTooltips.count(line) + "x " + line);
                    }
                }
            }
        });
    }
}
