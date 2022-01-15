package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.ore.OreItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class AbstractItemTagGenerator extends ItemTagsProvider {
    public AbstractItemTagGenerator(@Nonnull final DataGenerator generator,
                                    @Nonnull final BlockTagsProvider blockTagsProvider, @Nonnull final String modId,
                                    @Nonnull final ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, modId, existingFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Item Tags: " + modId;
    }

    protected void registerOre(@Nonnull final EnumOre ore, @Nonnull final ExNihiloTags.OreTag tags) {
        if (ore.shouldGenerateIngot()) {
            @Nullable final RegistryObject<OreItem> ingotRegistry = ore.getIngotRegistryItem();
            @Nullable final Item ingotItem = ore.getIngotItem();
            @Nullable final Item ingot;
            if (ingotItem != null)
                ingot = ingotItem;
            else if (ingotRegistry != null)
                ingot = ingotRegistry.get();
            else
                return;
            tag(tags.getIngotTag()).add(ingot);
            tag(Tags.Items.INGOTS).addTag(tags.getIngotTag());

            @Nullable final RegistryObject<OreItem> chunkRegistry = ore.getChunkItem();
            @Nullable final Item chunk = (chunkRegistry == null) ? null : chunkRegistry.get();
            if (chunk == null)
                return;
            tag(tags.getOreTag()).add(chunk);
            tag(Tags.Items.ORES).addTag(tags.getOreTag());
        } else {
            @Nullable final RegistryObject<OreItem> chunkRegistry = ore.getChunkItem();
            @Nullable final Item chunk = (chunkRegistry == null) ? null : chunkRegistry.get();
            if (chunk == null)
                return;
            tag(tags.getOreTag()).add(chunk);
            tag(Tags.Items.ORES).addTag(tags.getOreTag());
        }
    }
}
