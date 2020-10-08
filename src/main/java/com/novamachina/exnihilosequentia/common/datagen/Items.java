package com.novamachina.exnihilosequentia.common.datagen;

import com.novamachina.exnihilosequentia.common.init.ModItems;
import com.novamachina.exnihilosequentia.common.item.dolls.DollEnum;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.ore.EnumModdedOre;
import com.novamachina.exnihilosequentia.common.item.ore.EnumOre;
import com.novamachina.exnihilosequentia.common.item.ore.IOre;
import com.novamachina.exnihilosequentia.common.item.pebbles.EnumPebbleType;
import com.novamachina.exnihilosequentia.common.item.resources.EnumResource;
import com.novamachina.exnihilosequentia.common.item.seeds.EnumSeed;
import com.novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import com.novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Items extends ItemModelProvider {
    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Constants.ModIds.EX_NIHILO_SEQUENTIA, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTexture(ModItems.COOKED_SILKWORM.get().getRegistryName().getPath(), new ResourceLocation("item/handheld"),
            "layer0", new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "items/cooked_silkworm"));
        singleTexture(ModItems.WITCH_WATER_BUCKET.get().getRegistryName()
                .getPath(), new ResourceLocation("item/handheld"),
            "layer0", new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "items/bucket_witchwater"));
        singleTexture(ModItems.SEA_WATER_BUCKET.get().getRegistryName()
                .getPath(), new ResourceLocation("item/handheld"),
            "layer0", new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "items/bucket_sea_water"));

        registerCrooks();
        registerHammers();
        registerOres();
        registerSeeds();
        registerResources();
        registerPebbles();
        registerMeshes();
        registerDolls();
    }

    private void registerDolls() {
        for(DollEnum doll : DollEnum.values()) {
            singleTexture(doll.getRegistryObject().get().getRegistryName().getPath(), new ResourceLocation("item/handheld"),
                "layer0", new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "items/" + doll.getDollName()));
        }
    }

    private void registerMeshes() {
        for(EnumMesh mesh : EnumMesh.values()) {
            if(mesh != EnumMesh.NONE) {
                withExistingParent(mesh.getRegistryObject().get().getRegistryName()
                    .getPath(), new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "block/" + mesh.getMeshName()));
            }
        }
    }

    private void registerPebbles() {
        for(EnumPebbleType pebble : EnumPebbleType.values()) {
            singleTexture(pebble.getRegistryObject().get().getRegistryName().getPath(), new ResourceLocation("item/handheld"),
                "layer0", new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "items/" + pebble.getType()));
        }
    }

    private void registerResources() {
        for(EnumResource resource : EnumResource.values()) {
            singleTexture(resource.getRegistryObject().get().getRegistryName().getPath(), new ResourceLocation("item/handheld"),
                "layer0", new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "items/" + resource.getResourceName()));
        }
    }

    private void registerSeeds() {
        for(EnumSeed seed : EnumSeed.values()) {
            singleTexture(seed.getRegistryObject().get().getRegistryName().getPath(), new ResourceLocation("item/handheld"),
                "layer0", new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "items/" + seed.getSeedName()));
        }
    }

    private void registerOres() {
        for (EnumOre ore : EnumOre.values()) {
            registerChunk(ore);
            registerPiece(ore);
        }

        for (EnumModdedOre ore : EnumModdedOre.values()) {
            registerChunk(ore);
            registerPiece(ore);
            registerIngot(ore);
        }
    }

    private void registerIngot(EnumModdedOre ore) {
        withExistingParent(ore.getIngotItem().get().getRegistryName()
            .getPath(), new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "item/ingot_ore"));
    }

    private void registerPiece(IOre ore) {
        withExistingParent(ore.getPieceItem().get().getRegistryName()
            .getPath(), new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "item/piece_ore"));
    }

    private void registerChunk(IOre ore) {
        withExistingParent(ore.getChunkItem().get().getRegistryName()
            .getPath(), new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "item/chunk_ore"));
    }

    private void registerHammers() {
        for (EnumHammer hammer : EnumHammer.values()) {
            singleTexture(hammer.getRegistryObject().get().getRegistryName()
                    .getPath(), new ResourceLocation("item/handheld"),
                "layer0", new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "items/tools/hammer/" + hammer.name));
        }
    }

    private void registerCrooks() {
        for (EnumCrook crook : EnumCrook.values()) {
            singleTexture(crook.getRegistryObject().get().getRegistryName()
                    .getPath(), new ResourceLocation("item/handheld"),
                "layer0", new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "items/tools/crook/" + crook.name));
        }
    }
}
