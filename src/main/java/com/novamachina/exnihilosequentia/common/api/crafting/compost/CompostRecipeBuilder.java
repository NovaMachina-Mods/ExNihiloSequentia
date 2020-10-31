package com.novamachina.exnihilosequentia.common.api.crafting.compost;

import com.novamachina.exnihilosequentia.common.api.crafting.ExNihiloFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;

public class CompostRecipeBuilder extends ExNihiloFinishedRecipe<CompostRecipeBuilder> {
    public CompostRecipeBuilder() {
        super(CompostRecipe.SERIALIZER.get());
    }

    public static CompostRecipeBuilder builder() {
        return new CompostRecipeBuilder();
    }

    public CompostRecipeBuilder input(IItemProvider input) {
        return this.addInput(input);
    }

    public CompostRecipeBuilder input(ITag.INamedTag<Item> tag) {
        return this.addInput(tag);
    }

    public CompostRecipeBuilder amount(int amount) {
        return addWriter(jsonObj -> jsonObj.addProperty("amount", amount));
    }
}
