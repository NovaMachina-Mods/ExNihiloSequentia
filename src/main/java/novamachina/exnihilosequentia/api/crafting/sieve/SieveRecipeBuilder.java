package novamachina.exnihilosequentia.api.crafting.sieve;

import com.google.gson.JsonArray;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

import javax.annotation.Nonnull;

public class SieveRecipeBuilder extends ExNihiloFinishedRecipe<SieveRecipeBuilder> {
    @Nonnull private final JsonArray meshArray = new JsonArray();

    private SieveRecipeBuilder() throws NullPointerException {
        //noinspection ConstantConditions
        super(SieveRecipe.getStaticSerializer().get());

        addWriter(jsonObject -> jsonObject.add("rolls", meshArray));
    }

    @Nonnull
    public static SieveRecipeBuilder builder() {
        return new SieveRecipeBuilder();
    }

    @Nonnull
    public SieveRecipeBuilder addRoll(@Nonnull final MeshWithChance mesh) {
        meshArray.add(mesh.serialize());
        return this;
    }

    @Nonnull
    public SieveRecipeBuilder drop(@Nonnull final ItemLike drop) {
        return this.addResult(drop);
    }

    @Nonnull
    public SieveRecipeBuilder input(@Nonnull final Ingredient input) {
        return this.addInput(input);
    }

    @Nonnull
    public SieveRecipeBuilder isWaterlogged() {
        return this.addBoolean("waterlogged", true);
    }
}
