package novamachina.exnihilosequentia.api.datagen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilosequentia.common.item.ore.Ore;

public abstract class AbstractItemTagGenerator extends ItemTagsProvider {

  protected AbstractItemTagGenerator(@Nonnull final DataGenerator generator,
      @Nonnull final BlockTagsProvider blockTagsProvider, @Nonnull final String modId,
      @Nonnull final ExistingFileHelper existingFileHelper) {
    super(generator, blockTagsProvider, modId, existingFileHelper);
  }

  @Nonnull
  @Override
  public String getName() {
    return "Item Tags: " + modId;
  }

  protected void registerOre(@Nonnull final Ore ore, @Nonnull final ExNihiloTags.OreTag tags) {
    if (ore.getIngotItem() != null) {
      @Nullable final Item ingotItem = ore.getIngotItem();
      @Nullable final Item ingot;
      if (ingotItem != null) {
        ingot = ingotItem;
      } else {
        return;
      }
      tag(tags.getIngotTag()).add(ingot);
      tag(Tags.Items.INGOTS).addTag(tags.getIngotTag());

    }

    @Nullable final Item piece = ore.getPieceItem();
    if (piece != null) {
      tag(ExNihiloTags.PIECE).add(piece);
    }

    @Nullable final Item chunk = ore.getRawOreItem();
    if (chunk == null) {
      return;
    }
    tag(tags.getOreTag()).add(chunk);
    tag(Tags.Items.ORES).addTag(tags.getOreTag());
  }
}
