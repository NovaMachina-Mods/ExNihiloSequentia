package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.datagen.AbstractItemGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.item.dolls.EnumDoll;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloItemGenerator extends AbstractItemGenerator {
    private static final String ITEMS_TAG = "items/";
    private static final String ITEM_HANDHELD_TAG = "item/handheld";
    private static final String LAYER_0_TAG = "layer0";

    public ExNihiloItemGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTexture(ExNihiloItems.COOKED_SILKWORM.get().getRegistryName()
                        .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + "cooked_silkworm"));
        singleTexture(ExNihiloItems.WITCH_WATER_BUCKET.get().getRegistryName()
                        .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + "bucket_witchwater"));
        singleTexture(ExNihiloItems.SEA_WATER_BUCKET.get().getRegistryName()
                        .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + "bucket_sea_water"));

        registerCrooks();
        registerHammers();
        registerSeeds();
        registerResources();
        registerPebbles();
        registerMeshes();
        registerDolls();
    }

    private void registerCrooks() {
        for (EnumCrook crook : EnumCrook.values()) {
            singleTexture(crook.getRegistryObject().get().getRegistryName()
                            .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + "tools/crook/" + crook.crookName));
        }
    }

    private void registerDolls() {
        for (EnumDoll doll : EnumDoll.values()) {
            singleTexture(doll.getRegistryObject().get().getRegistryName()
                            .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + doll
                            .getDollName()));
        }
    }

    private void registerHammers() {
        for (EnumHammer hammer : EnumHammer.values()) {
            singleTexture(hammer.getRegistryObject().get().getRegistryName()
                            .getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + "tools/hammer/" + hammer.hammerName));
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
}
