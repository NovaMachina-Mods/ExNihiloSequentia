package novamachina.exnihilosequentia.api.crafting.compost;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

import javax.annotation.Nonnull;

public class CompostRecipeBuilder extends ExNihiloFinishedRecipe<CompostRecipeBuilder> {
    private CompostRecipeBuilder() {
        super(CompostRecipe.getStaticSerializer().get());
    }

    @Nonnull
    public static CompostRecipeBuilder builder() {
        return new CompostRecipeBuilder();
    }

    @Nonnull
    public CompostRecipeBuilder amount(final int amount) {
        return addWriter(jsonObj -> jsonObj.addProperty("amount", amount));
    }

    @Nonnull
    public CompostRecipeBuilder input(@Nonnull final ITag.INamedTag<Item> tag) {
        return this.addInput(tag);
    }

    @Nonnull
    public CompostRecipeBuilder input(@Nonnull final IItemProvider input) {
        return this.addInput(input);
    }
}
