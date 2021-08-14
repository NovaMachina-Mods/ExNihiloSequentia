package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;

import javax.annotation.Nonnull;

public abstract class AbstractItemTagGenerator extends ItemTagsProvider {
    public AbstractItemTagGenerator(DataGenerator generator, BlockTagsProvider blockTagsProvider, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, modId, existingFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Item Tags: " + modId;
    }

    protected void registerOre(EnumOre ore, ExNihiloTags.OreTag tags) {
        if (ore.shouldGenerateIngot()) {
            Item ingot = ore.getIngotItem() != null ? ore.getIngotItem() : ore.getIngotRegistryItem().get();

            tag(tags.getIngotTag()).add(ingot);
            tag(Tags.Items.INGOTS).addTag(tags.getIngotTag());
        } else {
            Item raw = ore.getRawOreItem().get();
            tag(tags.getOreTag()).add(raw);
            tag(Tags.Items.ORES).addTag(tags.getOreTag());
        }
    }
}
