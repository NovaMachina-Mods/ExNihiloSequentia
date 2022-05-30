package novamachina.exnihilosequentia.common.compat.jei.sieve;

import javax.annotation.Nonnull;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.jetbrains.annotations.NotNull;

public class DrySieveRecipeCategory extends AbstractSieveRecipeCategory {

  @Nonnull
  public static final ResourceLocation UID =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "dry_sieve");

  public DrySieveRecipeCategory(@NotNull IGuiHelper guiHelper) {
    super(guiHelper, false);
  }

  @Nonnull
  @Override
  public Component getTitle() {
    return new TextComponent("Sieve");
  }

  @Nonnull
  @Override
  public ResourceLocation getUid() {
    return UID;
  }
}
