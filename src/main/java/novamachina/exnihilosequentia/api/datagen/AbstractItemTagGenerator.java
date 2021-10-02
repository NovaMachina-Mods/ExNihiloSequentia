package novamachina.exnihilosequentia.api.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.utility.ExNihiloTags.OreTag;
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

    /**
     * @param ore get ores from {@link novamachina.exnihilosequentia.common.item.ore.EnumOre EnumOre}
     * @param tags add ore to tag
     */
    protected void registerOre(EnumOre ore, OreTag tags) {
        if (ore.shouldGenerateIngot()) {
            Item ingot = ore.getIngotItem() != null ? ore.getIngotItem() : ore.getIngotRegistryItem().get();

            tag(tags.getIngotTag()).add(ingot);
            tag(Tags.Items.INGOTS).addTag(tags.getIngotTag());
        } else {
            Item raw;
            switch (ore) {
                case IRON -> raw = Items.RAW_IRON;
                case GOLD -> raw = Items.RAW_GOLD;
                case COPPER -> raw = Items.RAW_COPPER;
                default -> raw = ore.getRawOreItem().get();
            }
            tag(tags.getOreTag()).add(raw);
            tag(Tags.Items.ORES).addTag(tags.getOreTag());
        }
    }
}
