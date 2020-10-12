package com.novamachina.exnihilosequentia.common.datagen;

import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class LootTableGenerator implements IDataProvider {
    private final DataGenerator generator;
    private static final Gson GSON =(new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    protected final Map<ResourceLocation, LootTable> lootTables = new HashMap<>();

    public LootTableGenerator(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void act(DirectoryCache cache) {
        lootTables.clear();
        Path outFolder = generator.getOutputFolder();

        createLootTables();

        ValidationTracker validator = new ValidationTracker(LootParameterSets.GENERIC, (function) -> null, lootTables::get);
        lootTables.forEach((name, table) -> {
            LootTableManager.func_227508_a_(validator, name, table);
        });
        Multimap<String, String> problems = validator.getProblems();
        if(!problems.isEmpty()) {
            problems.forEach((name, table) -> {
                LogUtil.warn("Found validation problem in "+ name + ": " + table);
            });
            throw new IllegalStateException("Failed to validate loot tables, see logs");
        } else {
            lootTables.forEach((name, table) -> {
                Path out = getPath(outFolder, name);

                try {
                    IDataProvider.save(GSON, cache, LootTableManager.toJson(table), out);
                } catch (IOException e) {
                    LogUtil.error("Couldn't save loot table " + out);
                    LogUtil.error(Arrays.toString(e.getStackTrace()));
                }
            });
        }
    }

    private Path getPath(Path outFolder, ResourceLocation name) {
        return outFolder.resolve("data/" + name.getNamespace() + "/loot_tables/" + name.getPath() + ".json");
    }

    protected abstract void createLootTables();
}
