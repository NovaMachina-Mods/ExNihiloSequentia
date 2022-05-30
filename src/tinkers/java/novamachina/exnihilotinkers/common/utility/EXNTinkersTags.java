package novamachina.exnihilotinkers.common.utility;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class EXNTinkersTags {

  public static final TagKey<Item> MULTI_USE = ItemTags.create(
      new ResourceLocation(EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS,
          "casts/multi_use/crook_head"));
  public static final TagKey<Item> SINGLE_USE = ItemTags.create(
      new ResourceLocation(EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS,
          "casts/single_use/crook_head"));
}
