package novamachina.exnihilosequentia.common.compat.jei.sifting;

import javax.annotation.Nonnull;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.jetbrains.annotations.NotNull;

public class WetSiftingRecipeCategory extends AbstractSieveRecipeCategory {

  @Nonnull
  public static final ResourceLocation UID =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "wet_sifting");

  public WetSiftingRecipeCategory(@NotNull IGuiHelper guiHelper) {
    super(guiHelper, true);
  }

  @Override
  public RecipeType<JEISieveRecipe> getRecipeType() {
    return new RecipeType<>(UID, JEISieveRecipe.class);
  }

  @Nonnull
  @Override
  public Component getTitle() {
    return Component.translatable("jei.category.wet_sifting");
  }
}
