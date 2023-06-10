package novamachina.exnihilosequentia.datagen.api.datagen;

import com.mojang.datafixers.util.Either;
import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags.OreTag;
import novamachina.exnihilosequentia.common.item.OreItem;
import novamachina.exnihilosequentia.common.item.ore.Ore;
import novamachina.novacore.world.item.ItemDefinition;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractItemTagGenerator extends ItemTagsProvider {

  protected AbstractItemTagGenerator(
      @Nonnull final DataGenerator generator,
      @Nonnull final BlockTagsProvider blockTagsProvider,
      @Nonnull final String modId,
      @Nonnull final ExistingFileHelper existingFileHelper) {
    super(generator, blockTagsProvider, modId, existingFileHelper);
  }

  @Nonnull
  @Override
  public String getName() {
    return "Item Tags: " + modId;
  }

  protected void registerOre(@Nonnull final Ore ore, @Nonnull final OreTag tags) {
    registerIngot(ore, tags);
    registerPiece(ore);
    registerRaw(ore, tags);
    registerNugget(ore, tags);
  }

  private void registerRaw(Ore ore, OreTag tags) {
    Either<ItemDefinition<OreItem>, Item> either = ore.getRawOreItem();
    if (either.left().isPresent()) {
      tag(tags.getRawMaterialTag()).add(either.left().get().asItem());
      tag(Items.RAW_MATERIALS).addTag(tags.getRawMaterialTag());
    }
  }

  private void registerPiece(@Nonnull Ore ore) {
    tag(ExNihiloTags.PIECE).add(ore.getPieceItem());
  }

  private void registerIngot(@NotNull Ore ore, @NotNull ExNihiloTags.OreTag tags) {
    Either<ItemDefinition<OreItem>, Item> either = ore.getIngotItem();
    if (either.left().isPresent()) {
      tag(tags.getIngotTag()).add(either.left().get().asItem());
      tag(Items.INGOTS).addTag(tags.getIngotTag());
    }
  }

  private void registerNugget(@NotNull Ore ore, @NotNull ExNihiloTags.OreTag tags) {
    Either<ItemDefinition<OreItem>, Item> either = ore.getNuggetItem();
    if (either.left().isPresent()) {
      tag(tags.getNuggetTag()).add(either.left().get().asItem());
      tag(Items.NUGGETS).addTag(tags.getNuggetTag());
    }
  }
}
