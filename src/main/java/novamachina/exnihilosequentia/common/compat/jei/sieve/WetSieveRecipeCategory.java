package novamachina.exnihilosequentia.common.compat.jei.sieve;

import javax.annotation.Nonnull;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.jetbrains.annotations.NotNull;

public class WetSieveRecipeCategory extends AbstractSieveRecipeCategory {

  @Nonnull
  public static final ResourceLocation UID =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "wet_sieve");

  public WetSieveRecipeCategory(@NotNull IGuiHelper guiHelper) {
    super(guiHelper, true);
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
}
