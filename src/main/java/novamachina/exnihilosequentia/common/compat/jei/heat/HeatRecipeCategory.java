package novamachina.exnihilosequentia.common.compat.jei.heat;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import java.awt.*;
import java.util.Arrays;

public class HeatRecipeCategory implements IRecipeCategory<HeatRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "heat");
    private final IDrawableStatic background;

    public HeatRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper
                .drawableBuilder(new ResourceLocation(ExNihiloConstants.ModIds.JEI, "textures/gui/gui_vanilla.png"),
                        0, 134, 18, 34).addPadding(0, 0, 0, 80).build();
    }

    @Override
    public void draw(HeatRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY) {
        Minecraft.getInstance().font
                .draw(matrixStack, recipe.getAmount() + "X", 24, 12, Color.gray.getRGB());
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
    public Class<? extends HeatRecipe> getRecipeClass() {
        return HeatRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Crucible Heat Sources";
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public void setIngredients(HeatRecipe recipe, IIngredients ingredients) {
        if (ForgeRegistries.FLUIDS.containsKey(recipe.getInput().getRegistryName())) {
            ingredients.setInput(VanillaTypes.FLUID, new FluidStack(ForgeRegistries.FLUIDS.getValue(recipe.getInput().getRegistryName()), FluidAttributes.BUCKET_VOLUME));
        } else {
            ingredients.setInputs(VanillaTypes.ITEM, Arrays.asList(Ingredient.of(recipe.getInput()).getItems()));
        }
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, HeatRecipe recipe, IIngredients ingredients) {
        if (ForgeRegistries.FLUIDS.containsKey(recipe.getInput().getRegistryName())) {
            recipeLayout.getFluidStacks().init(0, true, 1, 17);
            recipeLayout.getFluidStacks().set(0, new FluidStack(ForgeRegistries.FLUIDS.getValue(recipe.getInput().getRegistryName()), FluidAttributes.BUCKET_VOLUME));
        } else {
            ItemLike input = recipe.getInput();
			//TODO doing something else, both show flint and steel
            if(input == Blocks.FIRE || input == Blocks.SOUL_FIRE) {
                input = Items.FLINT_AND_STEEL;
            }
			if (input instanceof LiquidBlock) {
				input = ((LiquidBlock) input).getFluid().getBucket();
			}
            recipeLayout.getItemStacks().init(0, true, 0, 16);
            recipeLayout.getItemStacks().set(0, Arrays.asList(Ingredient.of(input).getItems()));
        }
    }
}
