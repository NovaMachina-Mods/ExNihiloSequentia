package com.novamachina.exnihilosequentia.common.api.crafting.sieve;

import com.google.gson.JsonArray;
import com.novamachina.exnihilosequentia.common.api.crafting.ExNihiloFinishedRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

public class SieveRecipeBuilder extends ExNihiloFinishedRecipe<SieveRecipeBuilder> {
    private JsonArray meshArray = new JsonArray();

    private SieveRecipeBuilder() {
        super(SieveRecipe.SERIALIZER.get());

        addWriter(jsonObject -> jsonObject.add("rolls", meshArray));
    }

    public static SieveRecipeBuilder builder() {
        return new SieveRecipeBuilder();
    }

    public SieveRecipeBuilder input(Ingredient input) {
        return this.addInput(input);
    }

    public SieveRecipeBuilder drop(IItemProvider drop) {
        return this.addResult(drop);
    }

    public SieveRecipeBuilder addRoll(MeshWithChance mesh) {
        meshArray.add(mesh.serialize());
        return this;
    }

    public SieveRecipeBuilder isWaterlogged() {
        return this.addBoolean("waterlogged", true);
    }
}
