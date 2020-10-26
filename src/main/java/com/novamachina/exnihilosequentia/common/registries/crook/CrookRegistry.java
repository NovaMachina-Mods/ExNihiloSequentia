package com.novamachina.exnihilosequentia.common.registries.crook;

import com.novamachina.exnihilosequentia.common.api.crafting.crook.CrookRecipe;

import java.util.ArrayList;
import java.util.List;

public class CrookRegistry {

    private final List<CrookRecipe> recipeList = new ArrayList<>();

    public List<CrookRecipe> getDrops() {

        return recipeList;
    }
}