package novamachina.exnihilosequentia.api.crafting.heat;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.item.crafting.Ingredient;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

import javax.annotation.Nonnull;

public class HeatRecipeBuilder extends ExNihiloFinishedRecipe<HeatRecipeBuilder> {
    private HeatRecipeBuilder() throws NullPointerException {
        //noinspection ConstantConditions
        super(HeatRecipe.getStaticSerializer().get());
    }

    public static HeatRecipeBuilder builder() {
        return new HeatRecipeBuilder();
    }

    @Nonnull
    public HeatRecipeBuilder amount(final int amount) {
        return addWriter(jsonObj -> jsonObj.addProperty("amount", amount));
    }

    @Nonnull
    public HeatRecipeBuilder input(@Nonnull final Ingredient input) {
        return this.addInput(input);
    }

    @Nonnull
    public HeatRecipeBuilder input(@Nonnull final Block block) {
        return this.addBlock(block);
    }

    @Nonnull
    public HeatRecipeBuilder properties(@Nonnull final StatePropertiesPredicate properties) {
        return this.addWriter(jsonObj -> jsonObj.add("state", properties.serializeToJson()));
    }
}
