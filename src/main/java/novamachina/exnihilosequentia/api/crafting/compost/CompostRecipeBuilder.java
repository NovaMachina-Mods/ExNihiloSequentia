package novamachina.exnihilosequentia.api.crafting.compost;

import net.minecraft.world.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.ItemLike;
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
    public CompostRecipeBuilder input(@Nonnull final Tag.Named<Item> tag) {
        return this.addInput(tag);
    }

    @Nonnull
    public CompostRecipeBuilder input(@Nonnull final ItemLike input) {
        return this.addInput(input);
    }
}
