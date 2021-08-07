package novamachina.exnihilosequentia.common.compat.jei.heat;

import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.awt.*;
import java.util.Arrays;

public class HeatRecipeCategory implements IRecipeCategory<HeatRecipe> {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    public static final ResourceLocation UID = new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "heat");
    private final IDrawableStatic background;

    public HeatRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper
                .drawableBuilder(new ResourceLocation(ExNihiloConstants.ModIds.JEI, "textures/gui/gui_vanilla.png"),
                        0, 134, 18, 34).addPadding(0, 0, 0, 80).build();
    }

    @Override
    public void draw(HeatRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        Minecraft.getInstance().font.draw(matrixStack, recipe.getAmount() + "X",
                24, 12, Color.gray.getRGB());

        Block block = recipe.getInput();
        if (block == Blocks.WALL_TORCH) {
            Minecraft.getInstance().font.draw(matrixStack, "Wall Torch",
                    24, 0, Color.DARK_GRAY.getRGB());
        } else {
            Minecraft.getInstance().font.draw(matrixStack, block.getName(),
                    24, 0, Color.DARK_GRAY.getRGB());
        }
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
            IItemProvider input = recipe.getInput();
			//TODO doing something else, both show flint and steel
            if(input == Blocks.FIRE || input == Blocks.SOUL_FIRE) {
                input = Items.FLINT_AND_STEEL;
            }
			if (input instanceof FlowingFluidBlock) {
				input = ((FlowingFluidBlock) input).getFluid().getBucket();
			}
            recipeLayout.getItemStacks().init(0, true, 0, 16);
            recipeLayout.getItemStacks().set(0, Arrays.asList(Ingredient.of(input).getItems()));
        }
    }
}
