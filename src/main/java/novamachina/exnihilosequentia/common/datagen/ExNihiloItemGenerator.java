package novamachina.exnihilosequentia.common.datagen;

import novamachina.exnihilosequentia.api.datagen.AbstractItemGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.dolls.DollEnum;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ExNihiloItemGenerator extends AbstractItemGenerator {
    private static final String ITEM_HANDHELD_TAG = "item/handheld";
    private static final String LAYER_0_TAG = "layer0";
    private static final String ITEMS_TAG = "items/";

    public ExNihiloItemGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTexture(ExNihiloItems.COOKED_SILKWORM.get().getRegistryName()
                .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
            LAYER_0_TAG, new ResourceLocation(modid, "items/cooked_silkworm"));
        singleTexture(ExNihiloItems.WITCH_WATER_BUCKET.get().getRegistryName()
                .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
            LAYER_0_TAG, new ResourceLocation(modid, "items/bucket_witchwater"));
        singleTexture(ExNihiloItems.SEA_WATER_BUCKET.get().getRegistryName()
                .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
            LAYER_0_TAG, new ResourceLocation(modid, "items/bucket_sea_water"));

        registerCrooks();
        registerHammers();
        registerSeeds();
        registerResources();
        registerPebbles();
        registerMeshes();
        registerDolls();
    }

    private void registerDolls() {
        for (DollEnum doll : DollEnum.values()) {
            singleTexture(doll.getRegistryObject().get().getRegistryName()
                    .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + doll
                    .getDollName()));
        }
    }

    private void registerMeshes() {
        for (EnumMesh mesh : EnumMesh.values()) {
            if (mesh != EnumMesh.NONE) {
                withExistingParent(mesh.getRegistryObject().get().getRegistryName()
                    .getPath(), new ResourceLocation(modid, "block/" + mesh
                    .getMeshName()));
            }
        }
    }

    private void registerPebbles() {
        for (EnumPebbleType pebble : EnumPebbleType.values()) {
            singleTexture(pebble.getRegistryObject().get().getRegistryName()
                    .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + pebble.getType()));
        }
    }

    private void registerResources() {
        for (EnumResource resource : EnumResource.values()) {
            singleTexture(resource.getRegistryObject().get().getRegistryName()
                    .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + resource
                    .getResourceName()));
        }
    }

    private void registerSeeds() {
        for (EnumSeed seed : EnumSeed.values()) {
            singleTexture(seed.getRegistryObject().get().getRegistryName()
                    .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + seed
                    .getSeedName()));
        }
    }

    private void registerHammers() {
        for (EnumHammer hammer : EnumHammer.values()) {
            singleTexture(hammer.getRegistryObject().get().getRegistryName()
                    .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                LAYER_0_TAG, new ResourceLocation(modid, "items/tools/hammer/" + hammer.hammerName));
        }
    }

    private void registerCrooks() {
        for (EnumCrook crook : EnumCrook.values()) {
            singleTexture(crook.getRegistryObject().get().getRegistryName()
                    .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                LAYER_0_TAG, new ResourceLocation(modid, "items/tools/crook/" + crook.crookName));
        }
    }
}
