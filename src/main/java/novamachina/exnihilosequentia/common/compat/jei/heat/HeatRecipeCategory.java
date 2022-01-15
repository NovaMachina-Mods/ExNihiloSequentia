package novamachina.exnihilosequentia.common.compat.jei.heat;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.api.crafting.heat.HeatRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.Arrays;

public class HeatRecipeCategory implements IRecipeCategory<HeatRecipe> {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    @Nonnull public static final ResourceLocation UID =
            new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "heat");
    @Nonnull private final IDrawableStatic background;

    public HeatRecipeCategory(@Nonnull final IGuiHelper guiHelper) {
        background = guiHelper
                .drawableBuilder(new ResourceLocation(ExNihiloConstants.ModIds.JEI, "textures/gui/gui_vanilla.png"),
                        0, 134, 18, 34).addPadding(0, 0, 0, 80).build();
    }

    @Override
    public void draw(@Nonnull final HeatRecipe recipe, @Nonnull final PoseStack matrixStack, final double mouseX,
                     final double mouseY) {
        @Nonnull final Minecraft minecraft = Minecraft.getInstance();
        minecraft.font.draw(matrixStack, recipe.getAmount() + "X",
                24, 12, Color.gray.getRGB());
        //TODO doing something better than just writing what it is

        @Nullable final Block block = recipe.getInput();
        if (block == Blocks.WALL_TORCH) {
            minecraft.font.draw(matrixStack, "Wall Torch",
                    24, 0, Color.DARK_GRAY.getRGB());
        } else if (block == Blocks.REDSTONE_WALL_TORCH) {
               minecraft.font.draw(matrixStack, "Redstone Wall Torch",
                       24, 0, Color.DARK_GRAY.getRGB());
            } else if (block != null) {
                minecraft.font.draw(matrixStack, block.getName(),
                        24, 0, Color.DARK_GRAY.getRGB());
            }
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
    public Class<? extends HeatRecipe> getRecipeClass() {
        return HeatRecipe.class;
    }

    @Nonnull
    @Override
    public net.minecraft.network.chat.Component getTitle() {
        return new TextComponent("Crucible Heat Sources");
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public void setIngredients(@Nonnull final HeatRecipe recipe, @Nonnull final IIngredients ingredients) {
        @Nullable final Block recipeInput = recipe.getInput();
        if (recipeInput == null)
            return;
        if (ForgeRegistries.FLUIDS.containsKey(recipeInput.getRegistryName())) {
            @Nullable final Fluid fluid = ForgeRegistries.FLUIDS.getValue(recipeInput.getRegistryName());
            if (fluid != null)
                ingredients.setInput(VanillaTypes.FLUID, new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME));
        } else {
            ingredients.setInputs(VanillaTypes.ITEM, Arrays.asList(Ingredient.of(recipe.getInput()).getItems()));
        }
    }

    @Override
    public void setRecipe(@Nonnull final IRecipeLayout recipeLayout, @Nonnull final HeatRecipe recipe,
                          @Nonnull final IIngredients ingredients) {
        @Nullable final Block recipeInput = recipe.getInput();
        if (recipeInput == null)
            return;
        if (ForgeRegistries.FLUIDS.containsKey(recipeInput.getRegistryName())) {
            recipeLayout.getFluidStacks().init(0, true, 1, 17);
            @Nullable final Fluid fluid = ForgeRegistries.FLUIDS.getValue(recipeInput.getRegistryName());
            if (fluid != null)
                recipeLayout.getFluidStacks().set(0, new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME));
        } else {
            @Nonnull ItemLike input = recipe.getInput();
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
