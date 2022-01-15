package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ExNihiloItemGenerator extends AbstractItemGenerator {
    private static final String ITEMS_TAG = "items/";
    private static final String ITEM_HANDHELD_TAG = "item/handheld";
    private static final String LAYER_0_TAG = "layer0";

    public ExNihiloItemGenerator(@Nonnull final DataGenerator generator,
                                 @Nonnull final ExistingFileHelper existingFileHelper) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        @Nullable final ResourceLocation cookedSilkworm = ExNihiloItems.COOKED_SILKWORM.get().getRegistryName();
        if (cookedSilkworm != null)
            singleTexture(cookedSilkworm.getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG, new ResourceLocation(modid, "items/cooked_silkworm"));
        @Nullable final ResourceLocation witchWaterBucket = ExNihiloItems.WITCH_WATER_BUCKET.get().getRegistryName();
        if (witchWaterBucket != null)
            singleTexture(witchWaterBucket.getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG, new ResourceLocation(modid, "items/bucket_witchwater"));
        @Nullable final ResourceLocation seaWaterBucket = ExNihiloItems.SEA_WATER_BUCKET.get().getRegistryName();
        if (seaWaterBucket != null)
            singleTexture(seaWaterBucket.getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG, new ResourceLocation(modid, "items/bucket_sea_water"));

        registerCrooks();
        registerHammers();
        registerSeeds();
        registerResources();
        registerPebbles();
        registerMeshes();
        registerDolls();
    }

    private void registerCrooks() {
        for (@Nonnull final EnumCrook crook : EnumCrook.values()) {
            @Nullable final RegistryObject<Item> registryObject = crook.getRegistryObject();
            if (registryObject == null)
                continue;
            @Nullable final ResourceLocation resourceLocation = registryObject.get().getRegistryName();
            if (resourceLocation == null)
                continue;
            singleTexture(resourceLocation.getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG, new ResourceLocation(modid, "items/tools/crook/" + crook.crookName));
        }
    }

    private void registerDolls() {
        for (@Nonnull final EnumDoll doll : EnumDoll.values()) {
            @Nullable final ResourceLocation resourceLocation = doll.getRegistryObject().get().getRegistryName();
            if (resourceLocation == null)
                continue;
            singleTexture(resourceLocation.getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + doll
                            .getDollName()));
        }
    }

    private void registerHammers() {
        for (@Nonnull final EnumHammer hammer : EnumHammer.values()) {
            @Nullable final RegistryObject<Item> registryObject = hammer.getRegistryObject();
            if (registryObject == null)
                continue;
            @Nullable final ResourceLocation resourceLocation = registryObject.get().getRegistryName();
            if (resourceLocation == null)
                continue;
            singleTexture(resourceLocation.getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG, new ResourceLocation(modid, "items/tools/hammer/" + hammer.hammerName));
        }
    }

    private void registerMeshes() {
        for (@Nonnull final EnumMesh mesh : EnumMesh.values()) {
            if (mesh != EnumMesh.NONE) {
                @Nullable final ResourceLocation resourceLocation = mesh.getRegistryObject().get().getRegistryName();
                if (resourceLocation == null)
                    continue;
                withExistingParent(resourceLocation.getPath(), new ResourceLocation(modid, "block/" + mesh
                        .getMeshName()));
            }
        }
    }

    private void registerPebbles() {
        for (@Nonnull final EnumPebbleType pebble : EnumPebbleType.values()) {
            @Nullable final RegistryObject<Item> registryObject = pebble.getRegistryObject();
            if (registryObject == null)
                continue;
            @Nullable final ResourceLocation resourceLocation = registryObject.get().getRegistryName();
            if (resourceLocation == null)
                continue;
            singleTexture(resourceLocation.getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + pebble.getType()));
        }
    }

    private void registerResources() {
        for (@Nonnull final EnumResource resource : EnumResource.values()) {
            @Nullable final RegistryObject<Item> registryObject = resource.getRegistryObject();
            if (registryObject == null)
                continue;
            @Nullable final ResourceLocation resourceLocation = registryObject.get().getRegistryName();
            if (resourceLocation == null)
                continue;
            singleTexture(resourceLocation.getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + resource
                            .getResourceName()));
        }
    }

    private void registerSeeds() {
        for (@Nonnull final EnumSeed seed : EnumSeed.values()) {
            @Nullable final RegistryObject<Item> registryObject = seed.getRegistryObject();
            if (registryObject == null)
                continue;
            @Nullable final ResourceLocation resourceLocation = registryObject.get().getRegistryName();
            if (resourceLocation == null)
                continue;
            singleTexture(resourceLocation.getPath(), new ResourceLocation(ITEM_HANDHELD_TAG),
                    LAYER_0_TAG, new ResourceLocation(modid, ITEMS_TAG + seed
                            .getSeedName()));
        }
    }
}
