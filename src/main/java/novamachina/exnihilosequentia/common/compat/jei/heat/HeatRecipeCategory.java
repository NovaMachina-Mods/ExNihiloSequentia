package novamachina.exnihilosequentia.common.compat.jei.heat;

import java.awt.Color;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;

public class HeatRecipeCategory implements IRecipeCategory<HeatRecipe> {

  @Nonnull
  public static final ResourceLocation UID =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "heat");

  @Nonnull private final IDrawableStatic background;

  public HeatRecipeCategory(@Nonnull final IGuiHelper guiHelper) {
    background =
        guiHelper
            .drawableBuilder(
                new ResourceLocation(
                    ExNihiloConstants.ModIds.JEI, "textures/jei/gui/gui_vanilla.png"),
                0,
                134,
                18,
                34)
            .addPadding(0, 0, 0, 80)
            .build();
  }

  @Override
  public void draw(
      HeatRecipe recipe,
      IRecipeSlotsView recipeSlotsView,
      GuiGraphics stack,
      double mouseX,
      double mouseY) {
    @Nonnull final Minecraft minecraft = Minecraft.getInstance();
    stack.drawString(minecraft.font, recipe.getAmount() + "X", 24, 12, Color.white.getRGB());
    //    // TODO doing something better than just writing what it is
    //
    //    @Nullable final Ingredient block = recipe.getInput();
    //    if (block == Blocks.WALL_TORCH) {
    //      minecraft.font.draw(stack, "Wall Torch", 24, 0, Color.DARK_GRAY.getRGB());
    //    } else if (block == Blocks.REDSTONE_WALL_TORCH) {
    //      minecraft.font.draw(stack, "Redstone Wall Torch", 24, 0, Color.DARK_GRAY.getRGB());
    //    } else if (block != null) {
    //      minecraft.font.draw(stack, block.getName(), 24, 0, Color.DARK_GRAY.getRGB());
    //    }
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

  @Override
  public RecipeType<HeatRecipe> getRecipeType() {
    return new RecipeType<>(UID, HeatRecipe.class);
  }

  @Nonnull
  @Override
  public Component getTitle() {
    return Component.translatable("jei.category.heat");
  }

  @Override
  public void setRecipe(IRecipeLayoutBuilder builder, HeatRecipe recipe, IFocusGroup focuses) {
    Block blockInput = recipe.getInputBlock();
    if (blockInput == null) {
      return;
    }
    ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(blockInput);
    if (ForgeRegistries.FLUIDS.containsKey(blockId)) {
      Fluid fluid = ForgeRegistries.FLUIDS.getValue(blockId);
      if (fluid != null) {
        builder
            .addSlot(RecipeIngredientRole.INPUT, 1, 17)
            .addIngredient(ForgeTypes.FLUID_STACK, new FluidStack(fluid, 1000));
      }
    } else {
      @Nonnull ItemLike input = recipe.getInputBlock();
      if (input == Blocks.FIRE || input == Blocks.SOUL_FIRE) {
        input = Items.FLINT_AND_STEEL;
      }
      if (input instanceof LiquidBlock liquidBlock) {
        input = liquidBlock.getFluid().getBucket();
      }
      builder.addSlot(RecipeIngredientRole.INPUT, 1, 17).addIngredients(Ingredient.of(input));
    }
  }
}
