package novamachina.exnihilosequentia.api.crafting.compost;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

public class CompostRecipeBuilder extends ExNihiloFinishedRecipe<CompostRecipeBuilder> {
    private CompostRecipeBuilder() {
        super(CompostRecipe.getStaticSerializer().get());
    }

    public static CompostRecipeBuilder builder() {
        return new CompostRecipeBuilder();
    }

    public CompostRecipeBuilder amount(int amount) {
        return addWriter(jsonObj -> jsonObj.addProperty("amount", amount));
    }

    public CompostRecipeBuilder input(ITag.INamedTag<Item> tag) {
        return this.addInput(tag);
    }

    public CompostRecipeBuilder input(IItemProvider input) {
        return this.addInput(input);
    }
}
